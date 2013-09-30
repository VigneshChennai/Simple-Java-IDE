package actionControllers.sidebarControllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import myEditor.MyEditor;
import uiComponents.SideBarItem;

public class ListItemSelected implements ListSelectionListener {

	public void valueChanged(ListSelectionEvent arg0) {
		JList jl = (JList)(arg0.getSource());
		if(jl.getSelectedIndex() <0)
			return;
		SideBarItem sbi = (SideBarItem)jl.getModel().getElementAt(jl.getSelectedIndex());
		MyEditor textArea= sbi.getEditor();
		File file = sbi.getFile();
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		String tmp = "";
		try {
			String tmp1="";
			while ((tmp1 = bf.readLine()) != null) {
				tmp = tmp + tmp1 + "\n";
			}
			textArea.setText(tmp);
			is.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		textArea.contentSavedTo(file);
		textArea.colorifyTokens();
	}

}
