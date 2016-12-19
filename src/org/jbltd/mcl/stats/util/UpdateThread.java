package org.jbltd.mcl.stats.util;

import org.apache.commons.io.FileUtils;
import org.jbltd.mcl.stats.Main;

import javax.swing.*;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class UpdateThread implements Runnable
{

    /** where this app was initially executed */
    private final String WORKING_DIRECTORY = Paths.get(".").toAbsolutePath().normalize().toString();

    private Main mainThread;

    public UpdateThread(Main main)
    {
        this.mainThread = main;
    }

    @Override
    public void run()
    {
        System.out.println("| UPDATE THREAD - Calling Update Manager");

        try
        {
            URL url = new URL("https://s3.amazonaws.com/sparkwings/STATSAPP_CURRENT.dat");
            Scanner s = new Scanner(url.openStream());

            while (s.hasNext())
            {
                String n = s.next();
                double version = Double.parseDouble(n);

                if (version > Main.version)
                {
                    JOptionPane.showMessageDialog(mainThread, "Your application version is outdated. Lets fix that! :D \n Your application will now auto-update and close.");

                    final File _updateFile = new File(WORKING_DIRECTORY, "MCSL Stat Tracker - UPDATED.jar");

                    FileUtils.copyURLToFile(new URL("https://s3.amazonaws.com/sparkwings/STAT_TRACKER_LATEST.jar"), _updateFile);

                    // automatically restart the app - OutdatedVersion
                    final ProcessBuilder _processBuilder = new ProcessBuilder("java", "-jar", _updateFile.getAbsolutePath());

                    // continue using the same terminal session
                    _processBuilder.inheritIO();

                    final Process _process = _processBuilder.start();

                    // wait for the new instance to be up..
                    _process.waitFor();

                    // close out this outdated one
                    System.exit(_process.exitValue());
                }
                else
                {
                    System.out.println("| UPDATE THREAD - No update found.");
                }
            }

            s.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
