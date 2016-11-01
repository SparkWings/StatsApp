package org.jbltd.mcl.stats.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jbltd.mcl.stats.Main;

public class UtilFileReader {

    private PlayerManager manager;
    private TeamManager tmanager;
    private Main main;

    
    private boolean secondHalf = false;

    public static String transfer = "";

    public UtilFileReader(PlayerManager manager, TeamManager tmanager, String filePath, Main main) {
	this.manager = manager;
	this.tmanager = tmanager;
	this.main = main;

	readFile(filePath);
    }

    public void readFile(String path) {

	StringBuilder sb = new StringBuilder();

	try {

	    BufferedReader br = new BufferedReader(new FileReader(path));

	    String current;

	    while ((current = br.readLine()) != null) {

		if (current.contains("Death>")) {

		    current = current.substring(47);

		    for (PlayerData d : manager.MasterData) {

			if (current.contains(d.getPlayerName() + " killed by")) {
			    d.incrementDeathCount();
			} else if (current.contains("killed by " + d.getPlayerName())) {
			    d.incrementKillCount();

			    if (current.contains("Headshot")) {
				d.incrementHeadshotCount();
			    }

			}

		    }

		}

		if (current.contains("won the round")) {
		    current = current.substring(40);

		    if (secondHalf) {
			if (current.contains("Bombers")) {
			    for (TeamData td : tmanager.MasterTeamData) {

				if (td.getTeamColor().equalsIgnoreCase("blue")) {
				    td.incrementWins();
				    System.out.println("Point BLUE");
				} else
				    continue;
			    }
			}

			if (current.contains("SWAT")) {
			    for (TeamData td : tmanager.MasterTeamData) {
				if (td.getTeamColor().equalsIgnoreCase("red")) {
				    td.incrementWins();
				    System.out.println("Point RED");
				} else
				    continue;
			    }
			}
		    }

		    else {
			if (current.contains("Bombers")) {
			    for (TeamData td : tmanager.MasterTeamData) {

				if (td.getTeamColor().equalsIgnoreCase("red")) {
				    td.incrementWins();
				    System.out.println("Point RED");
				} else
				    continue;
			    }
			}

			if (current.contains("SWAT")) {
			    for (TeamData td : tmanager.MasterTeamData) {
				if (td.getTeamColor().equalsIgnoreCase("blue")) {
				    td.incrementWins();
				    System.out.println("Point BLUE");
				} else
				    continue;
			    }
			}

		    }
		}

		if (current.contains("no longer invisible")) {
		    secondHalf = true;
		    System.out.println("| HALFTIME - Switching sides");
		}

		if (current.contains("end log")) {
		    System.out.println("| System exit");
		    break;
		}

	    }

	    br.close();

	    sb.append(tmanager.MasterTeamData.get(0).getTeamName() + " - " + tmanager.MasterTeamData.get(0).getWins()
		    + " wins \n");
	    for (PlayerData pd : tmanager.TEAM_ONE) {
		sb.append(pd.getPlayerName() + " - Kills: " + pd.getKills() + ", Deaths: " + pd.getDeaths() + ", KDR: "
			+ UtilMath.getKDR(pd) + ", Headshot Percentage: " + UtilMath.getHeadshotRatio(pd) + "%" + "\n");
	    }

	    sb.append("\n");

	    sb.append(tmanager.MasterTeamData.get(1).getTeamName() + " - " + tmanager.MasterTeamData.get(1).getWins()
		    + " wins \n");
	    for (PlayerData pd : tmanager.TEAM_TWO) {
		sb.append(pd.getPlayerName() + " - Kills: " + pd.getKills() + ", Deaths: " + pd.getDeaths() + ", KDR: "
			+ UtilMath.getKDR(pd) + ", Headshot Percentage: " + UtilMath.getHeadshotRatio(pd) + "% \n");
	    }

	    transfer = sb.toString();
	    new ResultsPage().createAndShowGUI();

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
