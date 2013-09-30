package actionControllers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import uiComponents.SideBarList;

import myEditor.MyEditor;

public class Save extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -965327735052681034L;
	private JFrame __frame;
	private myEditor.MyEditor textArea;
	private SideBarList sbl;
	public Save(JFrame f,SideBarList s,MyEditor t) {
		__frame = f;
		textArea = t;
		sbl=s;
	}
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new JFileChooser();
		File file = textArea.getSavedFile();
		if (file == null) {
			if (jfc.showSaveDialog(__frame) == JFileChooser.APPROVE_OPTION) {
				file = jfc.getSelectedFile();
				textArea.contentSavedTo(file);
				sbl.changeListContent(file,textArea);
			}
		}
		if (file != null) {
			OutputStream os = null;
			try {
				os = new FileOutputStream(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			PrintStream ps = new PrintStream(os);
			ps.print(textArea.getText());
			try {
				os.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			textArea.ContentSaved();
		}
		
	}

}
