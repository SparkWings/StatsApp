package org.jbltd.mcl.stats.util;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.jbltd.mcl.stats.Main;

public class UpdateThread implements Runnable {

    private Main mainThread;

    public UpdateThread(Main main) {

	this.mainThread = main;

	run();
    }

    @Override
    public void run() {

	System.out.println("| UPDATE THREAD - Calling Update Manager");
	File thisF = new File(".");
	

	synchronized (this) {

	    try {
		URL url = new URL("https://s3.amazonaws.com/sparkwings/STATSAPP_CURRENT.dat");
		Scanner s = new Scanner(url.openStream());

		while (s.hasNext()) {
		    String n = s.next();
		    double version = Double.parseDouble(n);

		    if (version > Main.version) {

			JOptionPane.showMessageDialog(mainThread,
				"Your application version is outdated. Lets fix that! :D \n Your application will now auto-update and close.");

			FileUtils.copyURLToFile(new URL("https://s3.amazonaws.com/sparkwings/STAT_TRACKER_LATEST.jar"),
				new File(thisF.getAbsolutePath(), "MCSL Stat Tracker - UPDATED.jar"));

			System.exit(0);

		    }
		    else
		    {
			System.out.println("| UPDATE THREAD - No update found.");
		    }
		}

		s.close();

	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}

    }

}
