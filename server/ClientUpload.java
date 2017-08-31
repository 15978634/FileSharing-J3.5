package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientUpload implements Runnable {

	private Socket fileshareSocket;
	private int fileId;
	private DataInputStream inputStream;
	public ClientUpload(Socket fileshareSocket, int fileId) {
		
		this.fileshareSocket = fileshareSocket;
		this.fileId = fileId;
		
		try {
			inputStream = new DataInputStream(fileshareSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
	}

}
