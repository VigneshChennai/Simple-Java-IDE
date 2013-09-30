package myEditor;

import java.awt.*;

import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import base.Pair;
import main.MainInterface;

import myEditor.keyEvents.EditorKeyEventListener;
import myEditor.syntax.HighLighter;
import myEditor.syntax.Syntax;

class ColorifyOnTime implements Runnable {
	private MyEditor me;

	public ColorifyOnTime(MyEditor m) {
		me = m;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (!me.istextColored())
				me.colorifyTokens();
		}
	}

}

public class MyEditor extends JTextPane {
	private static final long serialVersionUID = -464515284669232442L;
	private boolean __contentChanged = false;
	private boolean __textColored = true;
	private File file = null;
	private MainInterface __mainInterface;
	private boolean __initialized = false;
	private base.UndoRedo __undoRedo;
	private myEditor.syntax.TextColorer __textColorer;

	public MyEditor() {
	}

	public MyEditor(MainInterface m) {
		initialize(m);
	}

	public void initialize(MainInterface m) {
		__mainInterface = m;
		__initialized = true;
		__undoRedo = new base.UndoRedo();
		__textColorer = new myEditor.syntax.TextColorer(this);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		this.addKeyListener(new EditorKeyEventListener(this));
		new Thread(new ColorifyOnTime(this)).start();

		Style style = this.addStyle("Keyword:blue", null);
		StyleConstants.setForeground(style, Color.BLUE);
		Style style1 = this.addStyle("tokens:black", null);
		StyleConstants.setForeground(style1, Color.BLACK);
		Style style2 = this.addStyle("quotes:green", null);
		StyleConstants.setForeground(style2, Color.GREEN);
		Style style3 = this.addStyle("quotes:gray", null);
		StyleConstants.setForeground(style3, Color.LIGHT_GRAY);
	}

	public boolean isContentChanged() {
		return __contentChanged;
	}
	public boolean istextColored() {
		return __textColored;
	}
	public void contentChanged() {
		__contentChanged = true;
		__textColored = false;
		__mainInterface.enableButton(MainInterface.SAVE_BUTTON);
	}

	public void backup() {
		__undoRedo.add(this.getText());
	}

	public void ContentSaved() {
		__contentChanged = false;
		__mainInterface.disableButton(MainInterface.SAVE_BUTTON);

	}

	public void contentSavedTo(File f) {
		file = f;
	}

	public File getSavedFile() {
		return file;
	}

	public boolean isInitialised() {
		return __initialized;
	}

	public void undoLastAction() {
		String tmp = __undoRedo.previous();
		if (tmp != null) {
			this.setText(tmp);
		}

	}

	public void redoLastAction() {
		String tmp = __undoRedo.next();
		if (tmp != null) {
			this.setText(tmp);
		}
	}

	public void setColor(ArrayList<Pair<Integer>> pairList) {
		__textColorer.setColor(pairList);
	}

	public void setColor(
			ArrayList<ArrayList<Pair<Integer>>> pairListList, String[] StyleKey) {
		__textColorer.setColor(pairListList, StyleKey);
	}

	public  void setColor(String[] pattern, String[] StyleKey) {
		__textColorer.setColor(pattern, StyleKey);
	}

	public void removeColor(JTextPane me) {
		__textColorer.removeColor(me);
	}

	public void colorifyTokens() {
		String editorContent = this.getText();
		this.setColor(new String[] { "\'.\'", "\".*\"", "//.*",
				"/\\*(\n|.)*\\*/" }, new String[] { "quotes:green",
				"quotes:green", "quotes:gray", "quotes:gray" });
		this.setColor(Syntax.formatString(editorContent));
		/*
		 * ColorTracer.traceOut(editorContent);
		 * ArrayList<ArrayList<Pair<Integer>>> al_al = new
		 * ArrayList<ArrayList<Pair<Integer>>>();
		 * al_al.add(Syntax.formatString(editorContent));
		 * al_al.add(ColorTracer.getCommentsLocs());
		 * al_al.add(ColorTracer.getDocuCommentsLocs());
		 * al_al.add(ColorTracer.getDoubleQuotesLocs());
		 * al_al.add(ColorTracer.getSingleQuotesLocs());
		 * TextColorer.setColor(jep,al_al,new
		 * String[]{"Keyword:blue","quotes:gray"
		 * ,"quotes:gray","quotes:green","quotes:green"});
		 */
		__textColored =true;
		HighLighter.removeAllHighLights(this);
	}
}
