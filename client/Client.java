package client;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
	
	public static Socket socket;
	TcpConnection clientConnection;
	public static void main(String[] args){
		EventQueue.invokeLater(() ->{
			Gui gui = new Gui();
			gui.frame.setVisible(true);
			
			/**ArrayList<SharedFile> array = new ArrayList<SharedFile>();
			SharedFile file = new SharedFile("test.txt", 3, 3);
			SharedFile file2 = new SharedFile("test2.txt", 33, 33);
			SharedFile file3 = new SharedFile("test3.txt", 44, 44);
			array.add(file);
			array.add(file2);
			array.add(file3);
			**/
			
			//Get FileList
			//GUIFileList filelist = new GUIFileList(array);
			//filelist.FileList.setVisible(true);
			
		});
	}
	public static void connectSocket(String ip, int port){
		try {
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
		new TcpConnection(s);
	}
}
