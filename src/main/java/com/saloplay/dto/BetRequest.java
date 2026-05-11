package com.saloplay.dto;

public class BetRequest {

    private String username;
    private int matchId;
    private String selection;
    private double odd;
    private double stake;

    public BetRequest() {}

    public String getUsername() { return username; }
    public int getMatchId() { return matchId; }
    public String getSelection() { return selection; }
    public double getOdd() { return odd; }
    public double getStake() { return stake; }

    public void setUsername(String username) { this.username = username; }
    public void setMatchId(int matchId) { this.matchId = matchId; }
    public void setSelection(String selection) { this.selection = selection; }
    public void setOdd(double odd) { this.odd = odd; }
    public void setStake(double stake) { this.stake = stake; }
}
