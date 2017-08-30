package client;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUIFileList {

	public GUIFileList(ArrayList<SharedFile> array)
	{
		JFrame FileList = new JFrame();
		FileList.setSize(300, array.size() * 25);
		
		for(int i = 0; i < array.size(); i++){
			SharedFile tempfile = null;
			
			int x = 0;
			for (SharedFile file : array) {
			    if (x == i) {
			        tempfile = file;
			        x = 0;
			        break;
			    }
			    x++;
			}
			
			
			JLabel tempname = new JLabel();
			tempname.setBounds(0, i * 25, 200, 25);
			tempname.setText(tempfile.getName());
			
			JLabel tempid= new JLabel();
			tempid.setBounds(0, i * 25, 200, 25);
			tempid.setText(tempfile.getName());
			
			JLabel tempsize = new JLabel();
			tempsize.setBounds(0, i * 25, 200, 25);
			tempsize.setText(tempfile.getName());
		}
	}
	
}
