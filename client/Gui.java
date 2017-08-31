package client;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Gui extends JFrame{
	
	public JFrame frame;

	public Gui()
	{
		initUI();
	}
	
	private void initUI()
	{	
		Border emptyBorder = BorderFactory.createEmptyBorder();
		frame = new JFrame();
		frame.setLayout(null);
		
		
		String[] title = new String[]{
				"FileName", "File-ID", "File-Size"
		};
		
		JTable filelist = new JTable();
		
		JLabel ip = new JLabel("IP:");
		ip.setForeground(Color.WHITE);
		ip.setBounds(120, 70, 120, 25);
		ip.setBorder(emptyBorder);
		
		JLabel port = new JLabel("PORT:");
		port.setForeground(Color.WHITE);
		port.setBounds(100, 100, 120, 25);
		port.setBorder(emptyBorder);
		
		JLabel id = new JLabel("ID:");
		id.setForeground(Color.WHITE);
		id.setBounds(330, 170, 120, 25);
		id.setBorder(emptyBorder);
		
		JTextField FileID = new JTextField();
		FileID.setBackground(Color.WHITE);
		FileID.setForeground(Color.decode("#2196F3"));
		FileID.setBounds(350, 170, 100, 25);
		FileID.setBorder(emptyBorder);
		FileID.setHorizontalAlignment(JTextField.CENTER);
		FileID.addActionListener((ActionEvent e) -> {
			
        });
		
		JTextField portfield = new JTextField();
		portfield.setBackground(Color.WHITE);
		portfield.setBounds(140, 100, 120, 25);
		portfield.setText("50002");
		portfield.setForeground(Color.decode("#2196F3"));
		portfield.setBorder(emptyBorder);
		portfield.setHorizontalAlignment(JTextField.CENTER);
		portfield.addActionListener((ActionEvent e) -> {
        });
		
		JTextField ipfield = new JTextField();
		ipfield.setBackground(Color.WHITE);
		ipfield.setBounds(140, 70, 120, 25);
		ipfield.setForeground(Color.decode("#2196F3"));
		ipfield.setBorder(emptyBorder);
		ipfield.setHorizontalAlignment(JTextField.CENTER);
		ipfield.addActionListener((ActionEvent e) -> {
			ipfield.getText();
            Client.connectSocket(ipfield.getText(), Integer.parseInt(portfield.getText()));
        });
		
		JButton btn = new JButton("Connect");
		btn.setBounds(150, 150, 100, 40);
		btn.setBackground(Color.WHITE);
		btn.setForeground(Color.decode("#2196F3"));
		btn.setBorder(emptyBorder);
		btn.addActionListener((ActionEvent e) -> {
			ipfield.getText();
            Client.connectSocket(ipfield.getText(), Integer.parseInt(portfield.getText()));
        });
		
		JButton disconnect = new JButton("Disconnect");
		disconnect.setBounds(150, 200, 100, 40);
		disconnect.setBackground(Color.WHITE);
		disconnect.setForeground(Color.decode("#2196F3"));
		disconnect.setBorder(emptyBorder);
		disconnect.addActionListener((ActionEvent e) -> {
			TcpConnection.interrupt();
			System.out.println("Socket Disconnected");
        });
		
		JButton download = new JButton("Download");
		download.setBounds(350, 200, 100, 40);
		download.setBackground(Color.WHITE);
		download.setForeground(Color.decode("#2196F3"));
		download.setBorder(emptyBorder);
		download.addActionListener((ActionEvent e) -> {
			TcpConnection.downloadFile(Integer.parseInt(FileID.getText()));
        });
		
		JButton upload = new JButton("Upload");
		upload.setBounds(550, 200, 100, 40);
		upload.setBackground(Color.WHITE);
		upload.setForeground(Color.decode("#2196F3"));
		upload.setBorder(emptyBorder);;
		upload.addActionListener((ActionEvent e) -> {
			GUIFileChooser file = new GUIFileChooser();
			File file2 = file.GetSelectedFile();
		});
		
		frame.add(filelist);
		frame.add(id);
		frame.add(FileID);
		frame.add(ip);
		frame.add(port);
		frame.add(disconnect);
		frame.add(download);
		frame.add(upload);
		frame.add(btn);
		frame.add(ipfield);
		frame.add(portfield);
		
		frame.getContentPane().setBackground(Color.decode("#2196F3"));
		frame.setTitle("FileSharing");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
