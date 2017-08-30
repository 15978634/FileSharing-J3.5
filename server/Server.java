package server;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private static final int DEFAULT_PORT = 8887;
	private ServerSocket listener;
	private ExecutorService executor;
	private ArrayList<ClientConnection> clients;
	
	public static void main(String[] args) {
		new Server();
		
	}
	
	public void sendToAllClients(ServerMessage message) throws InterruptedException {
		synchronized(clients) {
			for (ClientConnection client : clients) {
				client.getQ().put(message);
			}
		}
	}
	
	
	public Server() {
		
		executor = Executors.newCachedThreadPool();
		
		try {
			listener = new ServerSocket(DEFAULT_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while(true) {
				Socket clientSocket = listener.accept();
				if (clientSocket != null) {
					ClientConnection connection = new ClientConnection(clientSocket);
					synchronized (clients) {
						clients.add(connection);
					}
					executor.execute(connection);
				}
			}
			
			//listener.close();
		} catch (IOException e) {
				System.err.println(e);
		}
	}
}
