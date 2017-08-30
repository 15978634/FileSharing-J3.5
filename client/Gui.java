package client;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Gui extends JFrame{

	public Gui()
	{
		initUI();
	}
	
	private void initUI()
	{	
		
		setLayout(null);
		JTextField field = new JTextField();
		field.setBackground(Color.WHITE);
		field.setBounds(30, 10, 200, 25);
		field.addActionListener((ActionEvent e) -> {
			field.getText();
            Client.connectSocket(field.getText());
        });
		
		JButton btn = new JButton("Connect");
		btn.setBounds(30, 50, 100, 40);
		btn.setBackground(Color.WHITE);
		btn.setToolTipText("A Tooltip");
		btn.addActionListener((ActionEvent e) -> {
			field.getText();
            Client.connectSocket(field.getText());
        });
		
		add(btn);
		add(field);
		
		setTitle("FileSharing");
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createLayout(JComponent...arg)
	{
		JPanel pane = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        pane.setToolTipText("Content pane");

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
