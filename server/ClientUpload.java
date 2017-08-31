package server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientUpload implements Runnable {

	private Socket fileshareSocket;
	private int fileId;
	private DataInputStream inputStream;
	File newFile;
	public ClientUpload(Socket fileshareSocket, int fileId) {
		
		this.fileshareSocket = fileshareSocket;
		this.fileId = fileId;
		try {
			inputStream = new DataInputStream(fileshareSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			long length = inputStream.readLong();
			String name = inputStream.readUTF();
			byte[] content = new byte[(int) length];
			inputStream.read(content);
			newFile = new File(Server.getSourceDirectory() + "" + name);
			newFile.createNewFile();
			FileOutputStream fOutputStream = new FileOutputStream(newFile);
			fOutputStream.write(content);
			fOutputStream.close();
			inputStream.close();
			Server.getFiles().add(new ServerFile(name, newFile.toString(), Server.getCurrentId(), newFile.length()));
		}	catch (IOException e) {
			e.printStackTrace();
		}
	}

}
