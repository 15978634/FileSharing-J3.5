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
	
	public ClientConnection (Socket clientSocket) throws IOException{
		mQ = new ArrayBlockingQueue<ServerMessage>(5);
		inputStream = new DataInputStream(clientSocket.getInputStream());
		outputStream = new DataOutputStream(clientSocket.getOutputStream());
		timeoutCounter = 0;
		this.clientSocket = clientSocket;
		}
	
	public ArrayBlockingQueue<ServerMessage> getQ() {
		return mQ;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				if (timeoutCounter < 1000) {
					if (inputStream.available() > 0) {
						int code = inputStream.readInt();
						switch(code) {
							case 1: break; //client will file runterladen
							case 2: break; //client will file hochladen
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

}
