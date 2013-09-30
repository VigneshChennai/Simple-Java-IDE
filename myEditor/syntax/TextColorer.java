package myEditor.syntax;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import base.Pair;

public class TextColorer {
	private JTextPane me;
	public TextColorer(JTextPane m) {
		me = m;
	}
	public synchronized void setColor(ArrayList<Pair<Integer>> pairList) {
		
		StyledDocument doc = me.getStyledDocument();
		//doc.setCharacterAttributes(0, doc.getLength(), me.getStyle("tokens:black"), false);
		for(Pair<Integer> pair : pairList)
			doc.setCharacterAttributes(pair.getFirst(),pair.getSecond(), me.getStyle("Keyword:blue"), false);
	}
public synchronized void setColor(ArrayList<ArrayList<Pair<Integer>>> pairListList,String[] StyleKey) {
		
		StyledDocument doc = me.getStyledDocument();
		//doc.setCharacterAttributes(0, doc.getLength(), me.getStyle("tokens:black"), false);
		int i=0;
		removeColor(me);
		for(ArrayList<Pair<Integer>> pairList : pairListList) {
			for(Pair<Integer> pair : pairList)
				doc.setCharacterAttributes(pair.getFirst(),pair.getSecond(), me.getStyle(StyleKey[i]), false);
			i++;
		}
	}
	public synchronized void setColor(String[] pattern,String[] StyleKey) {
		
		StyledDocument doc = me.getStyledDocument();
		ArrayList<ArrayList<Pair<Integer>>> arraylist = new ArrayList<ArrayList<Pair<Integer>>>();
		for(int i=0;i<pattern.length;i++) {
			Pattern patt = Pattern.compile(pattern[i]);
			Matcher m = null;
			try {
				m = patt.matcher(me.getText());
			}
			catch(java.lang.NullPointerException e) {
				e.printStackTrace();
				return;
			}
			ArrayList<Pair<Integer>> al = new ArrayList<Pair<Integer>>(); 
			while(m.find()) {
				al.add(new Pair<Integer>(m.start(),m.end()-m.start()));
			}
			arraylist.add(al);
		}
		
		removeColor(me);
		for(int i=0;i<pattern.length;i++)
			for(Pair<Integer> pair:arraylist.get(i))
				doc.setCharacterAttributes(pair.getFirst(),pair.getSecond(), me.getStyle(StyleKey[i]), false);
	}
	public synchronized void removeColor(JTextPane me) {
		StyledDocument doc = me.getStyledDocument();
		doc.setCharacterAttributes(0, doc.getLength(), me.getStyle("tokens:black"), false);
	}
}
