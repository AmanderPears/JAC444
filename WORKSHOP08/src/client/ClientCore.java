package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientCore {

	public static void main(String[] args) {
		new ClientCore("localhost", 44444);
	}

	Socket socket;
	BufferedReader socketRead;
	PrintWriter socketWrite;
	Scanner scanner;

	public ClientCore(String address, int port) {
		try {
			socket = new Socket(address, port);

			System.out.format("Successfully connected to: %s\n", socket.getInetAddress());

			socketRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketWrite = new PrintWriter(socket.getOutputStream(), true);
			Scanner scanner = new Scanner(System.in);

			Thread incoming = new Thread() {
				public void run() {
					try {
						String response;
						// isConnected, isClosed, isinputclose
						while (((response = socketRead.readLine()) != null)) {
							System.out.println(response);
						}
						System.out.println("*Disconnected from server");

					} catch (Exception e) {
						System.out.println("Error receiving data from server...");
					}
				}
			};

			Thread outgoing = new Thread() {
				public void run() {
					try {
						String send;
						do {
							send = scanner.nextLine();
							socketWrite.println(send);
						} while (!send.equals("exit"));
						scanner.close();
					} catch (Exception e) {
						System.out.println("Error sending data to server...");
					}
				}
			};

			incoming.start();
			outgoing.start();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error... Client ended.");
		}
	}
}
