package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Main {

	public static final int DEFAULT_PORT = 8887;
	public static void main(String[] args) {
		ServerSocket listener = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			listener = new ServerSocket(DEFAULT_PORT);
		} catch (IOException e) {
			System.err.println(e);
		}
		
			try {
				while(true) {
					clientSocket = listener.accept();
					if (clientSocket != null) {
						//while(true) {
						
						System.out.println(clientSocket.getInetAddress() + "connected to the Server");
						out = new PrintWriter(clientSocket.getOutputStream(), true);
						out.println("lars ist ein lappen");
						in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						System.out.println(in.readLine());
						clientSocket.close();
						//}
					}
				}
			} catch (IOException e) {
				System.err.println(e);
		}
	}
}
