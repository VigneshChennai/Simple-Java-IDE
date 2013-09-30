package myEditor.syntax;
import java.util.*;
import base.*;

public class Syntax {
	private static ArrayList<String> __JkeywordList = null;
	private static boolean mapInitialized = false;
	public static void initialized() {
		if(mapInitialized)
			return;
		 __JkeywordList = new ArrayList<String>();
		String[] key = new String[] {"abstract","assert","boolean","break","byte",
				"case","catch","char","class","const","continue","default","do","double",
				"else","enum","extends","final","finally","float","for","goto","if","implements",
				"import","instanceof","int","interface","long","native","new","package",
				"private","protected","public","return","short","static","strictfp","super",
				"switch","synchronized","this","throw","throws","transient","try","void","volatile",
				"while","false","null","true"};
		for(String k: key)
			__JkeywordList.add(k);
		__JkeywordList.trimToSize();
		mapInitialized = true;
	}
	public static boolean isInitialized() {
		return mapInitialized;
	}
	private static Boolean checkAWordWithoutInitCheck(String str) {
		return __JkeywordList.contains(str);
	}
	public static ArrayList<Pair<Integer>> formatString(String str) {
		if(!mapInitialized) {
			initialized();
		}
		ArrayList<Pair<Integer>> result = new ArrayList<Pair<Integer>>();
		int count=0;
		String tmp[] = str.split("[ ;\\)\\(:\\{\\}\n\t]");
		for(String tmp1 : tmp) {
			if (checkAWordWithoutInitCheck(tmp1)) {
				int temp = count;
				count+=tmp1.length();
				result.add(new Pair<Integer>(new Integer(temp),new Integer(tmp1.length())));
			}else {
				count+=tmp1.length();
			}
			count++;
		}
		return result;
	}
}
