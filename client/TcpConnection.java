package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpConnection implements Runnable{
	Socket connection;
	DataInputStream input;
	DataOutputStream output;
	String recmsg;
	public TcpConnection(Socket s){
		connection = s;
	}
	@Override
	public void run() {
		
		try {
			input = new DataInputStream(connection.getInputStream());
			output = new DataOutputStream(connection.getOutputStream());
		}
        catch (IOException e) {
        e.printStackTrace();
        }
		//try catch
		while(!Thread.currentThread().isInterrupted()){
			try{
				// available check
			recmsg = input.readUTF();
				if(recmsg != null){
					//handlemsg
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		// in finally socket u. streams schlieﬂen
    }

	void sendMessage(String message){
		try {
			output.writeBytes(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
