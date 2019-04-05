package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.regex.Pattern;

public class ClientThread extends Thread {

	static List<ClientThread> clientList;

	double clientId;
	String clientName;
	Socket socket;
	PrintWriter socketWrite;

	public ClientThread(Socket socket, List<ClientThread> clientList) {

		ClientThread.clientList = clientList;
		this.socket = socket;

		try {
			socketWrite = new PrintWriter(this.socket.getOutputStream(), true);
			socketWrite.println("Welcome to jChat!");
		} catch (IOException e) {
			System.out.println("*Error initializing socketWrite");
		}

		clientId = Math.random();
		clientName = Double.toString(clientId);

		// log
		System.out.format("Client %f has connected.\n", clientId);
	}

	@Override
	public void run() {
		try (BufferedReader socketRead = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			String socketMsg = "";

			while ((socketMsg = socketRead.readLine()) != null) {

				// log
				System.out.format("From %s: %s\n", clientName, socketMsg);

				if (socketMsg.trim().equals("/exit")) {
					break;
				}

				socketMsg = processMsg(socketMsg);
				if (socketMsg.startsWith("SERVER:")) {
					socketMsg = socketMsg.replaceFirst("SERVER: ", "");
					socketWrite.println(socketMsg);

					// log
					System.out.format("To %s: %s\n", clientName, socketMsg);
				}
			}

			// close resource
			socket.shutdownInput();
			socket.shutdownOutput();
			socketRead.close();
			socketWrite.close();
			socket.close();
			clientList.remove(this);

			// log
			System.out.format("Client %f has disconnected.\n", clientId);
		} catch (IOException e) {
			System.out.println("*Error initializing socketRead");
		}
	}

	String processMsg(String socketMsg) {
		socketMsg = socketMsg.trim();

		if (socketMsg.equals("/help")) {
			return "SERVER: Commands available\n" + "'/setName [name]' - set client name\n"
					+ "'/name' - view client name or id\n" + "'@[name/id] [msg]' - send another client msg\n"
					+ "'/clients' - returns list of connected clients\n" + "'/all' - send message to all clients\n";
		}
		// set client name
		else if (socketMsg.matches(Pattern.compile("^/setName\\s\\S+$").toString())) {
			return clientName = socketMsg.replaceFirst("/setName ", "");
		}
		// return client name
		else if (socketMsg.equals("/name")) {
			return String.format("SERVER: %s", clientName);
		}
		// find player
		else if (socketMsg.matches(Pattern.compile("^@\\S+\\s.+$").toString())) {
			String recipientName = ((socketMsg.split(" "))[0]).substring(1);
			for (ClientThread client : clientList) {
				if (client.clientName.equals(recipientName) || Double.toString(client.clientId).equals(recipientName)) {
					socketMsg = socketMsg.substring(recipientName.length() + 2);
					client.socketWrite.println(String.format("%s: %s", clientName, socketMsg));

					// log
					System.out.format("From '%s' to '%s': %s\n", clientName, client.clientName, socketMsg);

					return "SUCCESS";
				}
			}

			return "SERVER: Client not found!";
		}
		// return users
		else if (socketMsg.equals("/clients")) {
			socketMsg = String.format("SERVER: %d other clients connected\n", clientList.size() - 1);
			for (int i = 0, j = 0; i < clientList.size(); ++i) {
				if (clientList.get(i).clientId != clientId) {
					socketMsg += String.format("Client #%d: %s\n", ++j, clientList.get(i).clientName);
				}
			}
			return socketMsg;
		}
		// to all
		else if (socketMsg.matches(Pattern.compile("^/all\\s.+$").toString())) {
			socketMsg = socketMsg.replaceFirst("/all ", "");
			for (ClientThread client : clientList) {
				if (client.clientId != clientId) {
					client.socketWrite.println(String.format("%s: %s", clientName, socketMsg));
				}
			}
			return "SUCCESS";
		}
		// else
		else {
			return "SERVER: Invalid command or Incorrect syntax";
		}
	}
}
