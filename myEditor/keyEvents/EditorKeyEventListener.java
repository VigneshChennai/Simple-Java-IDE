package myEditor.keyEvents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorKeyEventListener implements KeyListener,Runnable {
	private myEditor.MyEditor jep;
	private KeyEvent __keyEvent;
	
	public EditorKeyEventListener(myEditor.MyEditor editor) {
		jep = editor;
	}

	public void keyTyped(KeyEvent e) {
		__keyEvent = e;
		(new Thread(this)).start();
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void run() {
		if(__keyEvent.getKeyChar() == ' ' || __keyEvent.getKeyChar() == '\n' || __keyEvent.getKeyChar() == '\b'
			|| __keyEvent.getKeyChar() == '.' ||__keyEvent.getKeyChar() == '{' || __keyEvent.getKeyChar() == '}'
			||	__keyEvent.getKeyChar() == '(' || __keyEvent.getKeyChar() == ')') {
			jep.backup();
		}
		if(!((int)__keyEvent.getKeyChar() == 19))
			jep.contentChanged();
	}
	
}
