package client;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Download implements Runnable {
	Socket fileTransfer;
	private DataInputStream input;
	File file;
	
	Download(Socket fileTransfer, SharedFile file){
		this.fileTransfer = fileTransfer;
		try {
			input = new DataInputStream(fileTransfer.getInputStream());
		} catch (IOException e) {
			
		}
	}
	@Override
	public void run() {
		
		
	}

}
