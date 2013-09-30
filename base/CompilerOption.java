package base;

public class CompilerOption {
	private static String compiler="javac";
	private static String interpretor="java";
	public static void setCompilerPath(String path) {
		compiler = path;
		interpretor =path;
		interpretor = interpretor.replace("javac", "java");
	}
	public static String getCompilerPath() {
		return compiler;
	}
	public static String getInterpretorPath() {
		return interpretor;
		
	}
}
