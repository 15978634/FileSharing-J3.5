package server;

import java.net.Socket;

public class ClientDownload implements Runnable {

private Socket downloadSocket;
	
	public ClientDownload(Socket downloadSocket) {
		this.downloadSocket = downloadSocket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
