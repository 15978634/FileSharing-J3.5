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
			System.out.println("1");
		} catch (IOException e) {
		}
		try {
			savedFile.createNewFile();
			System.out.println("2");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Writer = new FileOutputStream(savedFile);
			System.out.println("3");
		} catch (FileNotFoundException e) {
		}
		try {
			Writer.write(content);
			System.out.println("4");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
