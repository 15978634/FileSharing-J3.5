package client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class Upload implements Runnable{
	FileInputStream Reader;
	DataOutputStream output;
	byte[] content;
	File file;
	Socket fileTransfer;
	Upload(Socket fileTransfer, File file){
		this.file = file;
		this.fileTransfer = fileTransfer;
		content = new byte[(int) file.getTotalSpace()];
		try {
			Reader = new FileInputStream(this.file);
		} catch (FileNotFoundException e) {
		}
		try {
			output = new DataOutputStream(fileTransfer.getOutputStream());
		} catch (IOException e) {
		}
		
	}
	@Override
	public void run() {
		try {
			Reader.read(content);
		} catch (IOException e) {
		}
		try {
			System.out.println(content.length);
			output.write(content);

			System.out.println("content sent");
		} catch (IOException e) {
		}
		try {
			output.close();
			Reader.close();
			fileTransfer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
