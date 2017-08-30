package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private static final int DEFAULT_PORT = 8887;
	private static String ip;
	public static Socket socket;
	TcpConnection clientConnection;
	public static void main(String[] args){
		
	}
	private void connectSocket(String ip){
		try {
			socket = new Socket(ip , DEFAULT_PORT);
			connectionInit(socket);	
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void connectionInit(Socket s) throws IOException{
		new TcpConnection(s);
	}
}
