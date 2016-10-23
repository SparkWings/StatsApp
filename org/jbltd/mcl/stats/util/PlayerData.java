package org.jbltd.mcl.stats.util;

public class PlayerData {

    private String _name;
    private String _team;
    private int _kills;
    private int _deaths;
    private int _headshots;

    public PlayerData(String name, String team, int kills, int deaths, int headshots) {
	this._name = name;
	this.setTeam(team);
	this.setKillCount(kills);
	this.setDeathCount(deaths);
	this.setHeadshotCount(headshots);
    }

    public String getPlayerName() {
	return _name;

    }

    public String getTeam() {
	return _team;
    }

    public void setTeam(String _team) {
	this._team = _team;
    }

    public int getKills() {
	return _kills;
    }

    public void setKillCount(int _kills) {
	this._kills = _kills;
    }

    public int getDeaths() {
	return _deaths;
    }

    public void setDeathCount(int _deaths) {
	this._deaths = _deaths;
    }

    public int getHeadshots() {
	return _headshots;
    }

    public void setHeadshotCount(int _headshots) {
	this._headshots = _headshots;
    }

    public void incrementKillCount() {
	setKillCount(_kills + 1);
    }

    public void incrementDeathCount() {
	setDeathCount(_deaths + 1);
    }

    public void incrementHeadshotCount()
    {
	setHeadshotCount(_headshots + 1);
    }
    
}
