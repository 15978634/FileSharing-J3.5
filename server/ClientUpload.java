package server;

import java.net.Socket;

public class ClientUpload implements Runnable {

	private Socket fileshareSocket;
	private int fileId;
	public ClientUpload(Socket fileshareSocket, int fileId) {
		this.fileshareSocket = fileshareSocket;
		this.fileId = fileId;
	}
	
	@Override
	public void run() {
		
		
	}

}
