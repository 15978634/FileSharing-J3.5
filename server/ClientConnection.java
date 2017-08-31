package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class ClientConnection implements Runnable {

	private Socket clientSocket;
	private ArrayBlockingQueue<ServerMessage> mQ;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private int timeoutCounter;
	private Server server;
	public ClientConnection (Socket clientSocket, Server server) throws IOException{
		mQ = new ArrayBlockingQueue<ServerMessage>(5);
		inputStream = new DataInputStream(clientSocket.getInputStream());
		outputStream = new DataOutputStream(clientSocket.getOutputStream());
		timeoutCounter = 0;
		this.clientSocket = clientSocket;
		this.server = server;
	}
	
	
	public ArrayBlockingQueue<ServerMessage> getQ() {
		return mQ;
	}
	
	@Override
	public void run() {
		try {
			
			outputStream.writeInt(Server.getFiles().size());
			
			for (ServerFile file : Server.getFiles()) {
				outputStream.writeUTF(file.getName());
			}
			
			while(!Thread.currentThread().isInterrupted()) {
				if (timeoutCounter < 1000) {
					
					
					if (inputStream.available() > 0) {
						int code = inputStream.readInt();
						switch(code) {
							case 1: int fileId = inputStream.readInt();
									Socket fileshareSocket = server.acceptFileshare();
									server.pushToThreadPool(new ClientDownload(fileshareSocket, fileId));
									break; //client will file runterladen
							
							case 2: fileDownload();
									break; //client will file hochladen
									
							case 3: System.out.println("heartbeat");
									break; //heartbeat
						}
						timeoutCounter = 0;
					} else {
						timeoutCounter++;
					}
					
					
					if (!mQ.isEmpty()) {
						ServerMessage m = mQ.poll();
						outputStream.writeInt(m.getCode());
						outputStream.writeUTF(m.getMessage());
					}
					
				
				} else {
					Thread.currentThread().interrupt();
				}
				if (!Thread.currentThread().isInterrupted()) Thread.sleep(10);
			
			}
		} catch (Exception e) {
			
		} finally {
			try {
				clientSocket.close();
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				
			}
		}
	}
	
	public void fileUpload() {
		
	}
	
	public void fileDownload() {
		
	}
}
