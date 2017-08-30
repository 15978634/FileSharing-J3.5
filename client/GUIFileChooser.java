package client;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class GUIFileChooser {
	JFileChooser jfc;
	
	public GUIFileChooser()
	{
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);
		File selectedfile = jfc.getSelectedFile();
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			System.out.println(selectedfile.getAbsolutePath());
		}
	}
	
	public File GetSelectedFile()
	{
		return jfc.getSelectedFile();
	}
	
	
}
