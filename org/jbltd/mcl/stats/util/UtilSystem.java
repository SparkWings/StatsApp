package org.jbltd.mcl.stats.util;

import java.io.File;

/**
 * OutdatedVersion
 * Oct/15/2016 (1:04 PM)
 */

public class UtilSystem
{

    /**
     * Attempts to find the user's {@code logs}
     * directory based on their operating system.
     *
     * @return the logs dir or the fallback
     */
    public static File logDirectory()
    {
        final String _osName = System.getProperty("os.name").toLowerCase();
        final String _userHome = System.getProperty("user.home", ".");

        if (_osName.contains("win"))
        {
            final String _appData = System.getProperty("appdata");

            // this is a hacky way to do it that really isn't a good idea
            // but the environment variable above doesn't seem to be reliable
            final String _folderName = _appData == null ? (_userHome.concat("\\AppData\\Roaming")) : _appData;

            return new File(_folderName, ".minecraft/logs");
        }
        else if (_osName.contains("mac"))
            return new File(_userHome, "Library/Application Support/.minecraft/logs");
        else
            return new File(_userHome, ".minecraft/logs");
    }

}
