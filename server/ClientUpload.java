package server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class ClientUpload implements Runnable {

	private Socket fileshareSocket;
	private DataInputStream inputStream;
	File newFile;
	ArrayBlockingQueue<ServerMessage> mQ;
	public ClientUpload(Socket fileshareSocket, ArrayBlockingQueue<ServerMessage> mQ) {
		try {
			this.fileshareSocket = fileshareSocket;
		inputStream = new DataInputStream(fileshareSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.mQ = mQ;
	}
	
	@Override
	public void run() {
		try {
			long length = inputStream.readLong();
			String name = inputStream.readUTF();
			byte[] content = new byte[(int) length];
			inputStream.read(content);
			newFile = new File(Server.getSourceDirectory() + "/" + name);
			newFile.createNewFile();
			FileOutputStream fOutputStream = new FileOutputStream(newFile);
			fOutputStream.write(content);
			fOutputStream.close();
			inputStream.close();
			int id = Server.getCurrentId();
			Server.getFiles().add(new ServerFile(name, newFile.toString(), id, newFile.length()));
			Server.setCurrentId(Server.getCurrentId() + 1);
			try {
				mQ.put(new ServerMessage(1, name, id, newFile.length()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	catch (IOException e) {
			e.printStackTrace();
		}
	}

}
