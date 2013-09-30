package uiComponents;

import java.io.File;

import myEditor.MyEditor;

public class SideBarItem {
	private static final long serialVersionUID = 2605390333369340753L;
	private File file;
	private MyEditor textArea;
	public SideBarItem(File f,MyEditor ta) {
		file=f;
		textArea = ta;
	}
	public File getFile() {
		return file;
	}
	public MyEditor getEditor() {
		return textArea;
	}
	public String toString() {
		return file.getName();
		
	}
}
