package client;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Download implements Runnable {
	Socket fileTransfer;
	FileOutputStream Writer;
	DataInputStream input;
	File savedFile;
	SharedFile file;
	byte[] content;
	
	Download(Socket fileTransfer, SharedFile file){
		this.file = file;
		this.fileTransfer = fileTransfer;
		try {
			input = new DataInputStream(fileTransfer.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		savedFile = new File("//SLEEPLESS/homes$/jannvita17/Documents/FileSharing/" +file.getName());
		
		content = new byte[(int) this.file.getSize()];

	}
	@Override
	public void run() {
		try {
			input.readFully(content);
		} catch (IOException e) {
		}
		try {
			savedFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Writer = new FileOutputStream(savedFile);
		} catch (FileNotFoundException e) {
		}
		try {
			Writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
