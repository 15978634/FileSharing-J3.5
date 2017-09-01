package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
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
				if (!file.getName().equals("Thumbs.db")) {
					outputStream.writeUTF(file.getName());
					outputStream.writeInt(file.getId());
					outputStream.writeLong(file.getSize());

				} else {
					File f = new File(Server.getSourceDirectory() +"/Thumbs.db");
					System.out.println(f.toString());
					f.delete();
				}
			}
			while(!Thread.currentThread().isInterrupted()) {
				if (timeoutCounter < 1000) {
					
					if (inputStream.available() > 0) {
						int code = inputStream.readInt();
						Socket fileshareSocket;
						switch(code) {						
							case 1: int fileId = inputStream.readInt();
									fileshareSocket = server.acceptFileshare();
									server.pushToThreadPool(new ClientDownload(fileshareSocket, fileId));
									break; //client will file runterladen
							
							case 2:fileshareSocket = server.acceptFileshare();
									long length = inputStream.readLong();
									String name = inputStream.readUTF();
									server.pushToThreadPool(new ClientUpload(fileshareSocket, mQ, length, name));
									break; //client will file hochladen
									
							case 3: break; //heartbeat
						}
						timeoutCounter = 0;
					} else {
						timeoutCounter++;
					}
					
					
					if (!mQ.isEmpty()) {
						ServerMessage m = mQ.poll();
						outputStream.writeInt(m.getCode());
						outputStream.writeUTF(m.getMessage());
						outputStream.writeInt(m.getId());
						outputStream.writeLong(m.getSize());
					}
					
				
				} else {
					Thread.currentThread().interrupt();
				}
				if (!Thread.currentThread().isInterrupted()) Thread.sleep(10);
			
			}
		} catch (Exception e) {
			
		} finally {
			try {
				outputStream.writeInt(2);
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
