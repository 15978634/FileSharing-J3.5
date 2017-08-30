package server;

import java.net.Socket;

public class ClientDownload implements Runnable {

	private Socket fileshareSocket;
	private int fileId;
	public ClientDownload(Socket fileshareSocket, int fileId) {
		this.fileshareSocket = fileshareSocket;
		this.fileId = fileId;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
