package uiComponents;

import java.io.File;
import java.io.FileFilter;
import javax.swing.DefaultListModel;

import myEditor.MyEditor;

public class SideBarList extends DefaultListModel {
	private static final long serialVersionUID = -1745005884948756451L;
	private File[] files;
	MyEditor textArea;
	public SideBarList() {
	}
	public SideBarList(File file,MyEditor ta) {
		textArea =ta;
		initialize(file);
	}
	public void initialize(File file) {
		File dir = file.getParentFile();
		FileFilter fileFilter = new FileFilter() {
		    public boolean accept(File file) {
		        return file.isFile();
		    }
		};
		this.clear();
		files = dir.listFiles(fileFilter);
		for(File f:files) {
			String name = f.getName();
			if(name.matches(".*\\.java"))
				this.addElement((new SideBarItem(f,textArea)));
		}
	}
	public void changeListContent(File file,MyEditor ta) {
		textArea = ta;
		initialize(file);
	}
	
}
