package client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Gui extends JFrame{
	
	static Vector data;
	Vector File;
	public static JTable filelist;
	
	public JFrame frame;

	public Gui()
	{
		initUI();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initUI()
	{	
		Border emptyBorder = BorderFactory.createEmptyBorder();
		frame = new JFrame();
		frame.setLayout(null);
		
		 data = new Vector();
			File = new Vector();
				File.add("FILE_NAME");
				File.add("FILE_ID");
				File.add("FILE_SIZE");
				
			data.add(File);
		
		Vector title = new Vector();
			title.add("FILENAME");
			title.add("FILEID");
			title.add("FILESIZE");
		
		JLabel id = new JLabel("ID:");
		id.setForeground(Color.WHITE);
		id.setBounds(210, 510, 120, 25);
		id.setBorder(emptyBorder);
		
		JTextField FileID = new JTextField();
		FileID.setBackground(Color.WHITE);
		FileID.setForeground(Color.decode("#2196F3"));
		FileID.setBounds(230, 510, 100, 25);
		FileID.setBorder(emptyBorder);
		FileID.setHorizontalAlignment(JTextField.CENTER);
		FileID.addActionListener((ActionEvent e) -> {
			
        });
			
		filelist = new JTable(data, title);
		filelist.setBounds(210, 20, 550, 475);
		filelist.setForeground(Color.decode("#2196F3"));
		filelist.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int row = filelist.rowAtPoint(evt.getPoint());
			    int col = filelist.columnAtPoint(evt.getPoint());
			    	if(filelist.getValueAt(row, 1) != null)
			    	FileID.setText("" +filelist.getValueAt(row, 1));
			    //System.out.println(col + " " + row);
			 }
		});

		JLabel ip = new JLabel("IP:");
		ip.setForeground(Color.WHITE);
		ip.setBounds(20, 70, 120, 25);
		ip.setBorder(emptyBorder);
		
		JLabel port = new JLabel("PORT:");
		port.setForeground(Color.WHITE);
		port.setBounds(0, 100, 120, 25);
		port.setBorder(emptyBorder);
		
		
		JTextField portfield = new JTextField();
		portfield.setBackground(Color.WHITE);
		portfield.setBounds(40, 100, 120, 25);
		portfield.setText("50002");
		portfield.setForeground(Color.decode("#2196F3"));
		portfield.setBorder(emptyBorder);
		portfield.setHorizontalAlignment(JTextField.CENTER);
		portfield.addActionListener((ActionEvent e) -> {
        });
		
		JTextField ipfield = new JTextField();
		ipfield.setBackground(Color.WHITE);
		ipfield.setBounds(40, 70, 120, 25);
		ipfield.setForeground(Color.decode("#2196F3"));
		ipfield.setBorder(emptyBorder);
		ipfield.setHorizontalAlignment(JTextField.CENTER);
		ipfield.addActionListener((ActionEvent e) -> {
			ipfield.getText();
            Client.connectSocket(ipfield.getText(), Integer.parseInt(portfield.getText()));
        });
		
		JButton btn = new JButton("Connect");
		btn.setBounds(50, 150, 100, 40);
		btn.setBackground(Color.WHITE);
		btn.setForeground(Color.decode("#2196F3"));
		btn.setBorder(emptyBorder);
		btn.addActionListener((ActionEvent e) -> {
			ipfield.getText();
            Client.connectSocket(ipfield.getText(), Integer.parseInt(portfield.getText()));
        });
		
		JButton disconnect = new JButton("Disconnect");
		disconnect.setBounds(50, 400, 100, 40);
		disconnect.setBackground(Color.WHITE);
		disconnect.setForeground(Color.decode("#2196F3"));
		disconnect.setBorder(emptyBorder);
		disconnect.addActionListener((ActionEvent e) -> {
			TcpConnection.interrupt();
			System.out.println("Socket Disconnected");
        });
		
		JButton download = new JButton("Download");
		download.setBounds(350, 500, 100, 40);
		download.setBackground(Color.WHITE);
		download.setForeground(Color.decode("#2196F3"));
		download.setBorder(emptyBorder);
		download.addActionListener((ActionEvent e) -> {
			TcpConnection.downloadFile(Integer.parseInt(FileID.getText()));
        });
		
		JButton upload = new JButton("Upload");
		upload.setBounds(550, 500, 100, 40);
		upload.setBackground(Color.WHITE);
		upload.setForeground(Color.decode("#2196F3"));
		upload.setBorder(emptyBorder);;
		upload.addActionListener((ActionEvent e) -> {
			GUIFileChooser file = new GUIFileChooser();
			File file2 = file.GetSelectedFile();
			TcpConnection.uploadFile(file2.getName(), file2);
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void ShowFiles(ArrayList<SharedFile> files)
	{
		
		for(int i = 0; i < files.size(); i++){
			SharedFile tempfile = null;
			
			int x = 0;
			for (SharedFile file : files) {
			    if (x == i) {
			        tempfile = file;
			        x = 0;
			        break;
			    }
			    x++;
			}
			
			Vector tempFile = new Vector();
			tempFile.add(tempfile.getName());
			tempFile.add(tempfile.getId());
			tempFile.add(tempfile.getSize());
			
			data.add(tempFile);
			filelist.repaint();
		}
	}
}
