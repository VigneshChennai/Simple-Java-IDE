package stringParser;

public class TagRemover {
	public static String removeTags(String str) {
		String tmp = str.replaceAll("<br>", "\n");
		tmp =  tmp.replaceAll("<.*>\n", "");
		tmp = tmp.replace("\n", "<br>");
		return tmp;
	}
}
