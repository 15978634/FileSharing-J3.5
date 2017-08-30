package server;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private static final int DEFAULT_PORT = 8887;
	private ServerSocket listener;
	private ExecutorService executor;
	private ArrayList<ClientConnection> clients;
	private static ArrayList<ServerFile> files;
	private Socket uploadSocket;
	private Socket downloadSocket;
	private File sourceDirectory = new File("//SLEEPLESS/homes$/lukaweis17/Documents/GitHub/FileSharing Project/FileSharing/FileSharing-J3.5/ServerFiles");
	
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
	
	private void initializeFiles(){
		for (File f : sourceDirectory.listFiles()) {
			String fileName = f.getName();
			FileReader fr = null;
			try {
				fr = new FileReader(fileName);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CharBuffer c = null ;
			try {
				fr.read(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
			files.add(new ServerFile(f.getName(), c.toString()));
			System.out.println("Name: " + f.getName() + " \n Content:" + c.toString());
		}
	}
	
	public Server() {
		//initializeFiles();
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
	public static ArrayList<ServerFile> getFiles() {
		return files;
	}

	public static void setFiles(ArrayList<ServerFile> files) {
		Server.files = files;
	}
}
