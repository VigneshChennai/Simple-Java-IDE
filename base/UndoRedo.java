package base;

import java.util.ArrayList;

public class UndoRedo {
	private ArrayList<String> list = new ArrayList<String>();
	private final int max=1000;
	private int top=-1;
	public UndoRedo() {
		this.add("");
	}
	public synchronized void add(String str) {
		if(top+1 == max)
			list.remove(0);
		if(list.size() > top+1) {
			
				for(int i=list.size()-1;i>top;i--) {
					list.remove(i);
				}
				for(int i=0;i<top;i++) {
					list.remove(0);
				}
				top=0;
		}
		list.add(str);
		top++;
		if(top >= max)
			top = max-1;
	}
	public synchronized String previous() {
		if(top != 0)
			return list.get(--top);
		return null;
	}
	public synchronized String next() {
		if(top +1 < list.size())
			return list.get(++top);
		return null;
	}
}
