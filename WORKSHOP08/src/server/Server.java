package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

	static int promptInt(Scanner in) {
		int i = -1;
		while (i < 0) {
			while (!in.hasNextInt()) {
				System.out.println("Enter a valid integer: ");
				in.next();
			}
			i = in.nextInt();
		}
		return i;
	}

	public static void main(String[] args) {
//		System.out.println("Setup initial server settings:\n");
//		System.out.print("Enter port number: ");
//		Scanner in = new Scanner(System.in);
//		final int port = promptInt(in);
//		System.out.print("Enter the max number of clients allowed to connect: ");
//		final int maxClients = promptInt(in);
//		System.out.println("\n***TYPE 'exit' TO QUIT***\n");

		final int port = 44444, maxClients = 10;

		// start server
		try (ServerSocket server = new ServerSocket(port, maxClients)) {
			// log
			System.out.println("Server is running...");

			// client list
			List<ClientThread> clients = new ArrayList<ClientThread>();

			Socket client = null;
			while ((clients.size() <= maxClients)) {
				try {
					client = server.accept();
					clients.add(new ClientThread(client, clients));
					clients.get(clients.size() - 1).start();
				} catch (Exception e) {
					System.out.println("*Error with client connection attempt");
				}
			}

		} catch (IOException e) {
			System.out.println("*Error starting server");
		}

	}

}
