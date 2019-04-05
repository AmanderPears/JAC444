package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		Socket connection = new Socket("localhost", 44444);

		// socket io
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		PrintWriter toServer = new PrintWriter(connection.getOutputStream(), true);

		Scanner scanner = new Scanner(System.in);

		// incoming
		Thread incoming = new Thread() {
			public void run() {
//				String receive = "";
//				while (receive != null) {
//					try {
//						receive = fromServer.readLine();
//						System.out.println(receive);
//					} catch (Exception e) {
//						// exit loop if connection issues
//						break;
//					}
//				}

				try {
					String receive = "";
					while ((receive = fromServer.readLine()) != null) {
						System.out.println(receive);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		incoming.start();

		// outgoing
		Thread outgoing = new Thread() {
			public void run() {
				String send = "";
				do {
					// check if server connected
					if (connection.isClosed())
						break;

					send = scanner.nextLine();
					toServer.println(send);
				} while (!send.equals("exit"));

				scanner.close();
			}
		};
		outgoing.start();

		if (!incoming.isAlive()) {
			outgoing.stop();
			connection.close();
		}
		if (!outgoing.isAlive()) {
			outgoing.stop();
			connection.close();
		}

	}

}
