package org.jbltd.mcl.stats.util;

import java.util.ArrayList;

public class TeamManager {

    public ArrayList<PlayerData> TEAM_ONE;
    public ArrayList<PlayerData> TEAM_TWO;
    public ArrayList<TeamData> MasterTeamData;

    public TeamManager() {
	TEAM_ONE = new ArrayList<PlayerData>();
	TEAM_TWO = new ArrayList<PlayerData>();
	MasterTeamData = new ArrayList<TeamData>();
    }

}
