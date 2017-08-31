package client;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	public static String IP;
	public static Socket socket;
	TcpConnection clientConnection;
	public static void main(String[] args){
		EventQueue.invokeLater(() ->{
			Gui gui = new Gui();
			gui.frame.setVisible(true);
			
		});
	}
	public static void connectSocket(String ip, int port){
		try {
			IP = ip;
			socket = new Socket(ip , port);
			connectionInit(socket);	
			System.out.println("Socket connected!");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void connectionInit(Socket s) throws IOException{
		
		new Thread(new TcpConnection(s)).start();
	}
}
