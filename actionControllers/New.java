package actionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import myEditor.MyEditor;

public class New implements ActionListener {
	private myEditor.MyEditor textArea;

	public New(MyEditor t) {
		textArea = t;
	}
	public void actionPerformed(ActionEvent arg0) {
		textArea.setText("");
		textArea.contentSavedTo(null);
		textArea.contentChanged();
	}

}
