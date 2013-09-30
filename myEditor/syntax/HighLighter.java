package myEditor.syntax;
import java.awt.Color;

import java.util.ArrayList;
import java.util.regex.*;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import base.*;

public class HighLighter {
	private static class PrivatePainter extends DefaultHighlighter.DefaultHighlightPainter {
		PrivatePainter(Color c) {
			super(c);
		}
	}
	synchronized public static void highLight(JTextComponent jtc,String pattern,Color color) {
		//removeAllHighLights(jtc);
		try {
			Pattern patt = Pattern.compile(pattern);
			Matcher m = patt.matcher(jtc.getText());
	        Highlighter hilite = jtc.getHighlighter();
	        while(m.find()) {
	        	hilite.addHighlight(m.start(), m.end(), new PrivatePainter(color));
	        }
	    } catch (BadLocationException e) {
	    	e.printStackTrace();
	    }
	}
	synchronized public static void highLight(JTextComponent jtc,ArrayList<Pair<Integer>> pairList) {
		removeAllHighLights(jtc);
		try {
	        Highlighter hilite = jtc.getHighlighter();
	        for(Pair<Integer> pair : pairList) {
	        	hilite.addHighlight(pair.getFirst(), pair.getFirst()+pair.getSecond(), new PrivatePainter(Color.YELLOW));
	        }
	    } catch (BadLocationException e) {
	    	e.printStackTrace();
	    }
	}
	public static void removeAllHighLights(JTextComponent jtc) {
		Highlighter hilite = jtc.getHighlighter();
	    Highlighter.Highlight[] hilites = hilite.getHighlights();

	    for (Highlighter.Highlight hl: hilites) {
	        if (hl.getPainter() instanceof PrivatePainter) {
	            hilite.removeHighlight(hl);
	        }
	    }
	}
}
