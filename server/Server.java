package server;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private static final int DEFAULT_PORT = 50002;
	private ServerSocket listener;
	private ExecutorService executor;
	private ArrayList<ClientConnection> clients;
	private static ArrayList<ServerFile> files;
	private ServerSocket fileshare;
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
	
	private void initializeFiles() throws IOException{
		for (File f : sourceDirectory.listFiles()) {
			/*String fileName = f.toString();
			FileInputStream fInputStream = new FileInputStream(f);
			fInputStream.
			/*int length = fInputStream.available();
			byte[] content = new byte[length];
				fInputStream.read(content);*/
			files.add(new ServerFile(f.getName(), f.toString(), files.indexOf(f), (int) f.length()));
		}
	}
	
	
	
	public Server() {
		files = new ArrayList<ServerFile>();
		clients = new ArrayList<ClientConnection>();
		
		try {
			initializeFiles();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		executor = Executors.newCachedThreadPool();
		
		try {
			listener = new ServerSocket(DEFAULT_PORT);
			fileshare = new ServerSocket(DEFAULT_PORT + 1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while(true) {
				Socket clientSocket = listener.accept();
				if (clientSocket != null) {
					ClientConnection connection = new ClientConnection(clientSocket, this);
					System.out.println(clientSocket.getInetAddress() + " connected");
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
	
	public Socket acceptFileshare() throws IOException {
		synchronized(fileshare) {
			return fileshare.accept();
		}
	}
	
	public static ArrayList<ServerFile> getFiles() {
		return files;
	}

	public static void setFiles(ArrayList<ServerFile> files) {
		Server.files = files;
	}
	
	public void pushToThreadPool(Runnable r) {
		executor.execute(r);
	}
}
