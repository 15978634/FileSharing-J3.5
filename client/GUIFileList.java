package client;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUIFileList {

	public JFrame FileList;
	
	public GUIFileList(ArrayList<SharedFile> array)
	{
		FileList = new JFrame();
		FileList.setSize(300,45 + array.size()* 25);
		
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
			tempid.setBounds(100, i * 25, 200, 25);
			tempid.setText("" + tempfile.getId());
			
			JLabel tempsize = new JLabel();
			tempsize.setBounds(150, i * 25, 200, 25);
			tempsize.setText("" + tempfile.getSize());
			
			FileList.add(tempname);
			FileList.add(tempid);
			FileList.add(tempsize);
		}
	}
	
}
