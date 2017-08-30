package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	
	
	public static Socket s = null;
	public static void main(String[] args)throws Exception {

		String msg = "anderes wort";
		String ip = "192.168.60.132";
		int port = 8887;
		connect(ip, port);
		System.out.println("Connected!");
		if(s == null){
			System.out.println("Socket is null");
		}
		BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		System.out.println(r.readLine());
		
		System.out.println("sent: " + msg);
		out.writeBytes(msg);
		s.close();
		System.out.println("Socket closed");
		
	}
	static void connect(String ip, int PORT){
		

		try {
			s = new Socket(ip , PORT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection failed!");
		}
		
	}
	
	

}
