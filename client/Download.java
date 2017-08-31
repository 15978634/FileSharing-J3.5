package client;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Download implements Runnable {
	Socket fileTransfer;
	private DataInputStream input;
	File savedFile;
	SharedFile file;
	byte[] content;
	
	Download(Socket fileTransfer, SharedFile file){
		this.file = file;
		this.fileTransfer = fileTransfer;
		try {
			input = new DataInputStream(fileTransfer.getInputStream());
		} catch (IOException e) {
			
		}
		content = new byte[this.file.getSize()];
	}
	@Override
	public void run() {
		try {
			input.read(content);
		} catch (IOException e) {
		}
		
	}

}
