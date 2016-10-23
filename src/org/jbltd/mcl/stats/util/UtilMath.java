package org.jbltd.mcl.stats.util;

import java.text.DecimalFormat;

public class UtilMath {

    public static String getKDR(PlayerData input) {

	DecimalFormat df = new DecimalFormat("#.###");

	try {
	    double kd = (double) input.getKills() / input.getDeaths();
	    return df.format(kd);
	} catch (ArithmeticException e) {
	    return df.format(input.getKills() / (input.getDeaths() + 1));
	}

    }

    
    public static String getHeadshotRatio(PlayerData input)
    {
	
	if(input.getHeadshots() == 0)
	{
	    return "0";
	}
	
	DecimalFormat df = new DecimalFormat("#.##");

	try {
	    double kd = (double) input.getKills() / input.getHeadshots();
	    return df.format(kd);
	} catch (ArithmeticException e) {
	    return "0";
	}
	
    }
    
}
