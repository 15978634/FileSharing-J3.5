package client;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Gui extends JFrame{
	
	public JFrame frame;

	public Gui()
	{
		initUI();
	}
	
	private void initUI()
	{	
		 frame = new JFrame();
	
		JFileChooser downloadchooser = new JFileChooser();
		downloadchooser.setBackground(Color.WHITE);
		downloadchooser.setBounds(350, 100, 200, 200);
		downloadchooser.setVisible(false);
		downloadchooser.showOpenDialog(frame.getParent());
		
		frame.setLayout(null);
		JTextField pfield = new JTextField();
		pfield.setBackground(Color.WHITE);
		pfield.setBounds(30, 40, 200, 25);
		pfield.addActionListener((ActionEvent e) -> {
        });
		
		JTextField field = new JTextField();
		field.setBackground(Color.WHITE);
		field.setBounds(30, 10, 200, 25);
		field.addActionListener((ActionEvent e) -> {
        });
		
		JButton btn = new JButton("Connect");
		btn.setBounds(150, 150, 100, 40);
		btn.setBackground(Color.WHITE);
		btn.addActionListener((ActionEvent e) -> {
			field.getText();
            Client.connectSocket(field.getText(), Integer.parseInt(pfield.getText()));
        });
		
		JButton download = new JButton("Download");
		download.setBounds(350, 150, 100, 40);
		download.setBackground(Color.WHITE);
		download.addActionListener((ActionEvent e) -> {
			downloadchooser.setVisible(true);
        });
		
		JButton upload = new JButton("Upload");
		upload.setBounds(550, 150, 100, 40);
		upload.setBackground(Color.WHITE);
		upload.addActionListener((ActionEvent e) -> {
			field.getText();
        });
		
		
		frame.add(downloadchooser);
		frame.add(download);
		frame.add(upload);
		frame.add(btn);
		frame.add(field);
		frame.add(pfield);
		
		frame.getContentPane().setBackground( Color.decode("#2196F3"));
		frame.setTitle("FileSharing");
		frame.setSize(800, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createLayout(JComponent...arg)
	{
		JPanel pane = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(200)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(120)
        );

        pack();
	}
}
