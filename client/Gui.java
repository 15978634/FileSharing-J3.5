package client;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
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
		
		JTextField pfield = new JTextField();
		pfield.setBackground(Color.WHITE);
		pfield.setBounds(140, 100, 120, 25);
		pfield.setText("50002");
		pfield.setForeground(Color.decode("#2196F3"));
		pfield.setBorder(emptyBorder);
		pfield.setHorizontalAlignment(JTextField.CENTER);
		pfield.addActionListener((ActionEvent e) -> {
        });
		
		JTextField field = new JTextField();
		field.setBackground(Color.WHITE);
		field.setBounds(140, 70, 120, 25);
		field.setForeground(Color.decode("#2196F3"));
		field.setBorder(emptyBorder);
		field.setHorizontalAlignment(JTextField.CENTER);
		field.addActionListener((ActionEvent e) -> {
        });
		
		JButton btn = new JButton("Connect");
		btn.setBounds(150, 150, 100, 40);
		btn.setBackground(Color.WHITE);
		btn.setForeground(Color.decode("#2196F3"));
		btn.setBorder(emptyBorder);
		btn.addActionListener((ActionEvent e) -> {
			field.getText();
            Client.connectSocket(field.getText(), Integer.parseInt(pfield.getText()));
        });
		
		JButton disconnect = new JButton("Disconnect");
		disconnect.setBounds(150, 200, 100, 40);
		disconnect.setBackground(Color.WHITE);
		disconnect.setForeground(Color.decode("#2196F3"));
		disconnect.setBorder(emptyBorder);
		
		JButton download = new JButton("Download");
		download.setBounds(350, 150, 100, 40);
		download.setBackground(Color.WHITE);
		download.setForeground(Color.decode("#2196F3"));
		download.setBorder(emptyBorder);
		download.addActionListener((ActionEvent e) -> {
        });
		
		JButton upload = new JButton("Upload");
		upload.setBounds(550, 150, 100, 40);
		upload.setBackground(Color.WHITE);
		upload.setForeground(Color.decode("#2196F3"));
		upload.setBorder(emptyBorder);;
		upload.addActionListener((ActionEvent e) -> {
			GUIFileChooser file = new GUIFileChooser();
			File file2 = file.GetSelectedFile();
		});
		
		frame.add(disconnect);
		frame.add(download);
		frame.add(upload);
		frame.add(btn);
		frame.add(field);
		frame.add(pfield);
		
		frame.getContentPane().setBackground(Color.decode("#2196F3"));
		frame.setTitle("FileSharing");
		frame.setSize(800, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
