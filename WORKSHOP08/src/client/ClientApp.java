package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientApp extends Application {
	// params
	String address = "localhost";
	int port = 44444;
	Socket socket = new Socket();
	BufferedReader socketRead;
	PrintWriter socketWrite;
	volatile boolean exit = false;
	Thread incoming = new Thread() {

		public void run() {
			try {
				socket.connect(new InetSocketAddress(address, port));
				socketRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				socketWrite = new PrintWriter(socket.getOutputStream(), true);

				display(String.format("Successfully connected to: %s\n", socket.getInetAddress()));

				String response = "";
				while (!exit && (response = socketRead.readLine()) != null) {
					display(String.format("->%s", response));
				}
				display("*Disconnected from server");
				sendFld.setDisable(true);
				sendBtn.setDisable(true);

				// resource
				socket.shutdownInput();
				socket.shutdownOutput();
				socketRead.close();
				socketWrite.close();
				socket.close();
			} catch (Exception e) {
				display("Error receiving data from server...");
			}
		}
	};

	@FXML
	private TextArea displayFld;

	@FXML
	private TextField sendFld;

	@FXML
	private Button sendBtn;

	void display(String s) {
		displayFld.appendText(String.format("\n%s", s));
	}

	@FXML
	void initialize() throws Exception {

		incoming.setDaemon(true);
		incoming.start();
	}

	@FXML
	void sendMsg() {
		String msg = sendFld.getText();

		// if client wants to exit
		msg = msg.trim();
		if (msg.equals("/exit")) {
			exit = true;
		}

		socketWrite.println(msg);
		display(String.format("<-%s", msg));
		sendFld.clear();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene((Parent) FXMLLoader.load(getClass().getResource("ClientUI.fxml"))));
		primaryStage.show();
	}
}