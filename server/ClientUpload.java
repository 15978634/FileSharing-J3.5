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
	long length;
	String name;
	
	public ClientUpload(Socket fileshareSocket, ArrayBlockingQueue<ServerMessage> mQ, long length, String name) {
		this.fileshareSocket = fileshareSocket;
		this.length = length;
		this.name = name;
		System.out.println(name +";"+length);
		try {
			inputStream = new DataInputStream(fileshareSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.mQ = mQ;
	}
	
	@Override
	public void run() {
		System.out.println("hi");
		try {
			byte[] content = new byte[(int) length];
			inputStream.read(content);
			System.out.println("hi");
			newFile = new File(Server.getSourceDirectory() + "/"+ name);
			System.out.println(newFile.toString());
			newFile.createNewFile();
			FileOutputStream fOutputStream = new FileOutputStream(newFile);
			
			int id = Server.getCurrentId();
			Server.getFiles().add(new ServerFile(name, newFile.toString(), id, newFile.length()));
			Server.setCurrentId(Server.getCurrentId() + 1);
			
			fOutputStream.write(content);
			fOutputStream.close();
			inputStream.close();
			fileshareSocket.close();
			
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
