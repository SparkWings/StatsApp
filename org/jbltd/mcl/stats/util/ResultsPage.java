package org.jbltd.mcl.stats.util;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ResultsPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public ResultsPage() {

	this.getContentPane().setLayout(new BorderLayout());

	JTextArea field = new JTextArea();
	field.append(UtilFileReader.transfer);
	

	field.setEditable(false);
	add(field);

	
    }

    public void createAndShowGUI() {


	JFrame frame = new ResultsPage();

	frame.pack();
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}