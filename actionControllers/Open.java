package actionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import uiComponents.SideBarList;

import myEditor.MyEditor;

public class Open implements ActionListener {
	private JFrame __frame;
	private myEditor.MyEditor textArea;
	private SideBarList sbl;

	public Open(JFrame f,SideBarList s,MyEditor t) {
		__frame = f;
		textArea = t;
		sbl = s;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(__frame) == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			InputStream is = null;
			try {
				is = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			String tmp = "";
			try {
				String tmp1;
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
			sbl.changeListContent(file,textArea);
		}

	}

}
