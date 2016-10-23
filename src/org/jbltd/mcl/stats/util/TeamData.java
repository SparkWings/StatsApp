package org.jbltd.mcl.stats.util;

public class TeamData {

    private String _name;
    private String _teamColor;
    private int _wins;

    public TeamData(String name, String teamColor, int wins) {

	this._name = name;
	this._teamColor = teamColor;
	this._wins = wins;

    }

    public String getTeamName() {
	return _name;
    }

    public void setTeamName(String name) {
	this._name = name;
    }

    public String getTeamColor() {
	return _teamColor;
    }

    public void setTeamColor(String _teamColor) {
	this._teamColor = _teamColor;
    }

    public int getWins() {
	return _wins;
    }

    public void setWinCount(int _wins) {
	this._wins = _wins;
    }

    public void incrementWins() {

	this.setWinCount(_wins + 1);

    }

}
