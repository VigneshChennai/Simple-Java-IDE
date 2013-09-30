package actionControllers;

import java.awt.event.ActionEvent;
import main.MainInterface;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.swing.JTextArea;

import base.CompilerOption;

import myEditor.MyEditor;

public class Run implements ActionListener,Runnable {
	private myEditor.MyEditor textArea;
	private JTextArea outputArea;
	private MainInterface __mainInterface;
	private String __temp="";
	private BufferedWriter bw;
	private boolean __keyeventRegistered = false;
	private Process p=null;

	public Run(MainInterface m,MyEditor t, JTextArea o) {
		textArea = t;
		outputArea = o;
		__mainInterface = m;
	}
	public void actionPerformed(ActionEvent arg0) {
		new Thread(this).start();
	}
	public void run() {
		File file = textArea.getSavedFile();
		if(p!=null) {
			p.destroy();
			p=null;
			return;
		}
		if (file != null) {
			__mainInterface.disableButton(MainInterface.RUN_BUTTON);
			try {
				String line, errorLine = null;
				outputArea.setText("");
				System.out.println(CompilerOption.getInterpretorPath());
				p = new ProcessBuilder(
						CompilerOption.getInterpretorPath(), "-classpath",
						file.getParent(), file.getName().split("\\.")[0])
						.start();
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				BufferedReader error = new BufferedReader(
						new InputStreamReader(p.getErrorStream()));
				OutputStream ostream = p.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(ostream));
				if(!__keyeventRegistered) {
					outputArea.addKeyListener(new KeyAdapter() {
						
						public void keyTyped(KeyEvent arg0) {
								try {
									if(arg0.getKeyChar() == '\b') {
										__temp = __temp.substring(0, __temp.length()-2);
									} else {
										__temp = __temp +new String(new char[] {arg0.getKeyChar()});
									}
									if(arg0.getKeyChar() == '\n') {
										bw.write(__temp);
										bw.flush();
										__temp ="";
									}
								} catch (IOException e) {
									//e.printStackTrace();
								}
						}
					});
					__keyeventRegistered = true;
				}
				while ((line = input.readLine()) != null
						|| (errorLine = error.readLine()) != null) {
					if (line != null)
						outputArea.append(line + "\n");
					if (errorLine != null)
						outputArea.append(errorLine + "\n");
				}
				input.close();
			} catch (Exception err) {
				//err.printStackTrace();
			}
			finally {
				__mainInterface.enableButton(MainInterface.RUN_BUTTON);
			}
		}
	}

}
