package uiComponents.dialogBoxes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

public class AboutDialogBox extends JDialog {
	private static final long serialVersionUID = -4979386411951081018L;
	public AboutDialogBox() {
		initialize();
	}
	private void initialize() {
		this.setPreferredSize(new Dimension(500,200));
		this.setResizable(false);
		this.setTitle("About");
		this.setAlwaysOnTop(true);

		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2 - 200;
		final int y = (screenSize.height - this.getHeight()) / 2 - 100;
		this.setLocation(x, y);

		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		JEditorPane jta = new JEditorPane("text/html","<br><h2>&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A Simple JAVA IDE</h2>" +
				"&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;Created by students of CSE Third Year 2011<br>" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;" +
				"Valliammai Engineering College");
		jta.setEditable(false);
		this.getContentPane().add(jta,BorderLayout.CENTER);
		JButton jbtOK = new JButton("Ok");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
		this.getContentPane().add(panel,BorderLayout.SOUTH);
		panel.add(jbtOK);
		jbtOK.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		this.pack();
		
	}
}
