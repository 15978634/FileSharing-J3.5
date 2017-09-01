package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class TcpConnection implements Runnable{
	private static Socket fileTransfer;
	private Socket connection;
	private DataInputStream input;
	private static DataOutputStream output;
	private static int timeout;
	private static ArrayList<SharedFile> files;

	
	public static ArrayList<SharedFile> getFiles() {
		synchronized(files){
		return files;
		}
	}
	public TcpConnection(Socket connection) throws IOException{
		this.connection = connection;
		input = new DataInputStream(connection.getInputStream());
		output = new DataOutputStream(connection.getOutputStream());
		files = new ArrayList<SharedFile>();
	}
	@Override
	public void run() {
		try{
			System.out.println("test");
			int length = input.readInt();
			System.out.println(length); 
			for(int i = 0; i < length; i++){
				String name = input.readUTF();
				int id = input.readInt();
				long size = input.readLong();
				files.add(new SharedFile(name,id,size));
				System.out.println(name + id + size);
				    
			}
			Gui.ShowFiles(files);
			
			while(!Thread.currentThread().isInterrupted()){
				if(input.available()>0){
					int code = input.readInt();
					switch(code){ 
					case 1:{ //new file
						String name = input.readUTF();
						int id = input.readInt();
						long size = input.readLong();
						files.add(new SharedFile(name, id, size));
						Gui.ShowFiles(files);
					break; 
					}
					case 2:{ //Server shutdown
					Thread.currentThread().interrupt();
					break;
					}
					case 3: break;
					}
					System.out.println("received: " + code);
				}
				if(timeout>500){
					sendCode(3);
					timeout = 0;
				}
				if(!Thread.currentThread().isInterrupted()){
					Thread.sleep(10);
					timeout++;
				}
			}
		}
		catch(Exception e){
			
		}
		finally{
			try {
				connection.close();
				input.close();
				output.close();
			} catch (IOException e) {
			}
		}
	}

	static void sendMessage(String message){
		try {
			output.writeUTF(message);
			System.out.println("sent: " + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	static void sendCode (int code){
		try {
			output.writeInt(code);
			System.out.println("sent: " + code);
		} catch (IOException e) {
			e.printStackTrace();
		}
		timeout = 0;
	}

	static void sendCode (long code){
		try {
			output.writeLong(code);
			System.out.println("sent: " + code);
		} catch (IOException e) {
			e.printStackTrace();
		}
		timeout = 0;
	}
	public static synchronized void downloadFile(int id){
		sendCode(1);
		sendCode(id);
		SharedFile download = null;
		for(SharedFile f : files){
			if(f.getId() == id){
				download = new SharedFile(f.getName(), f.getId(), f.getSize());
			}
		}
		try {
			fileTransfer = new Socket(Client.IP, 50003);
		} catch (IOException e) {
		}
		if(files != null && download != null){
			new Thread(new Download(fileTransfer, download)).start();
			System.out.println("Download started!");
		}else{
			System.out.println("This file does not exist!");
		}
	}
	public static synchronized void uploadFile(String name, File file){
		sendCode(2);
		sendCode(file.length());
		sendMessage(name);

		try {
			fileTransfer = new Socket(Client.IP, 50003);
		} catch (IOException e) {
		}
		new Thread(new Upload(fileTransfer, file)).start();
		
	}
	public static synchronized void interrupt(){
		Thread.currentThread().interrupt();
	}
}
