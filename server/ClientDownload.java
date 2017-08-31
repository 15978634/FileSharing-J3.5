package server;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientDownload implements Runnable {

	private Socket fileshareSocket;
	private int fileId;
	private DataOutputStream outputStream;
	
	public ClientDownload(Socket fileshareSocket, int fileId) {
		this.fileshareSocket = fileshareSocket;
		this.fileId = fileId;
		try {
			outputStream = new DataOutputStream(fileshareSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		for (ServerFile f : Server.getFiles()) {
			if (f.getId() == fileId) {
				File file = new File(f.getLocation());
				FileInputStream fInputStream;
				try {
					fInputStream = new FileInputStream(file);
					int length = fInputStream.available();
					byte[] content = new byte[length];
					fInputStream.read(content);
					outputStream.write(content);
					fInputStream.close();
					outputStream.close();
					fileshareSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/*String fileName = f.toString();
	FileInputStream fInputStream = new FileInputStream(f);
	fInputStream.
	/*int length = fInputStream.available();
	byte[] content = new byte[length];
		fInputStream.read(content);*/
}
