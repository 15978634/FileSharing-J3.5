package server;

import java.net.Socket;

public class ClientUpload implements Runnable {

	private Socket uploadSocket;
	
	public ClientUpload(Socket uploadSocket) {
		this.uploadSocket = uploadSocket;
	}
	
	@Override
	public void run() {
		
		
	}

}
