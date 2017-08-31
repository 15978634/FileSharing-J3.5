package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class TcpConnection implements Runnable{
	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private int timeout;
	private ArrayList<SharedFile> files;
	
	public ArrayList<SharedFile> getFiles() {
		synchronized(files){
		return files;
		}
	}
	public TcpConnection(Socket connection) throws IOException{
		this.connection = connection;
		input = new DataInputStream(connection.getInputStream());
		output = new DataOutputStream(connection.getOutputStream());
	}
	@Override
	public void run() {
		try{
			System.out.println("test");
			int length = input.readInt();
			System.out.println(length); 
			for(int i = 0; i<length; i++){
				String name = input.readUTF();
				int id = input.readInt();
				int size = input.readInt();
				files.add(new SharedFile(name,id,size));
				    
			}
			
			while(!Thread.currentThread().isInterrupted()){
				if(input.available()>0){
					int code = input.readInt();
					String msg = input.readUTF();
					switch(code){ 
					case 1:{ //new file
					break; 
					}
					case 2:{ //Server shutdown
					Thread.currentThread().interrupt();
					break;
					}
					case 3: break;
					}
					System.out.println("received: " + code + " message: " + msg);
				}
				if(timeout>5000){
					this.sendCode(3);
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

	void sendMessage(String message){
		try {
			output.writeBytes(message);
			System.out.println("sent: " + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	void sendCode (int code){
		try {
			output.writeInt(code);
			System.out.println("sent: " + code);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//langer Kommentar
	
	
	
}
