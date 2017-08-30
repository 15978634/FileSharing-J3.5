package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpConnection implements Runnable{
	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private int timeout;
	
	public TcpConnection(Socket connection) throws IOException{
		this.connection = connection;
		input = new DataInputStream(connection.getInputStream());
		output = new DataOutputStream(connection.getOutputStream());
		this.run();
	}
	@Override
	public void run() {
		try{
			
			while(!Thread.currentThread().isInterrupted()){
				if(input.available()>0){
					int code = input.readInt();
					String msg = input.readUTF();
					switch(code){
					case 1:{
					break; //file list
					}
					case 2: break;//server shutdown
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

}
