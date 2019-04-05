package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class Connections extends Thread {

	static List<Connections> ClientList;

	Socket socket;
	PrintWriter pw;
	double clientId;
	String clientName;

	Connections(Socket s, List<Connections> cl) throws Exception {
		socket = s;
		clientId = Math.random();
		clientName = Double.toString(this.clientId);
		Connections.ClientList = cl;
		pw = new PrintWriter(this.socket.getOutputStream(), true);
		this.pw.println("SERVER: Welcome to iChat\n");

		// log
		System.out.format("Client %f has connected.\n", clientId);
	}

	@Override
	public void run() {
		// in and out from client
		try (BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

			String receive = "";
			while ((receive = inputFromClient.readLine()) != null) {

				// log
				System.out.format("From %s: %s\n", clientName, receive);

				receive = processInput(receive);

				if (receive.contains("SERVER")) {
					pw.println(receive);

					// log
					System.out.format("To %s: %s\n", clientName, receive);
				}
			}

			// log
			System.out.format("Client %f has disconnected.\n", clientId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {

			}
		}

	}

	void sendMsgToClient(String s) {
		pw.println(s);
	}

	String processInput(String s) {
		if (s.equals("/help")) {
			return "SERVER:\nsetName - set client name\nname - view client name or id\n@[clientName/ID] - send another client msg";
		}
		// set client name
		else if (s.matches(Pattern.compile("^/setName \\S*$").toString())) {
			return clientName = s.replaceFirst(Pattern.compile("^/setName ").toString(), "");
		}
		// return client name
		else if (s.equals("/name")) {
			return String.format("SERVER: %s", clientName);
		}
		// find player
		else if (s.matches(Pattern.compile("^@\\S*\\s.*$").toString())) {
			String n = (s.split(" "))[0];
			n = n.substring(1);
			for (Connections c : ClientList) {
				if (c.clientName.equals(n) || Double.toString(c.clientId).equals(n)) {
					String ss = s.substring(n.length() + 2);
					c.sendMsgToClient(String.format("%s: %s", this.clientName, ss));

					// log
					System.out.format("From '%s' to '%s': %s", clientName, c.clientName, ss);

					return "SUCCESS";
				}
			}

			return "SERVER: Client not found!";
		}
		// return users
		else if (s.equals("/clients")) {
			String res = "SERVER: Clients connected\n";
			for (Connections client : ClientList) {
				if (client.clientId != clientId) {
					res += client.clientName + "\n";
				}
			}
			return res;
		}
		// to all
		else if (s.matches(Pattern.compile("^/all\\s.*$").toString())) {

			String n = (s.split(" "))[0];
			n = n.substring(1);
			String ss = s.substring(n.length() + 2);
			for (Connections client : ClientList) {
				if (client.clientId != clientId) {
					client.sendMsgToClient(String.format("%s: %s", clientName, ss));
				}
			}

			return "SUCCESS";
		}
		// else
		else {
			return "SERVER: Invalid command or Incorrect syntax";
		}
	}

	// is the client active
	public boolean isClientConnected() {
		return !socket.isClosed();
	}

}

public class Main {

	public static void main(String[] args) throws Exception {
		// server params
		final int portNumber = 44444, numOfClients = 10;

		// start server
		ServerSocket serverSocket = new ServerSocket(portNumber, numOfClients);

		// client list
		List<Connections> Clients = new ArrayList<Connections>();

		System.out.format("Server is running...\n");

		Socket client = null;
		while (Clients.size() <= numOfClients) {
			client = serverSocket.accept();
			Clients.add(new Connections(client, Clients));
			Clients.get(Clients.size() - 1).start();

			// remove disconnected
			Clients.removeIf(c -> !c.isClientConnected());
		}

	}

}
