package com.saloplay.dto;

public class BetDto {

    private String username;
    private int matchId;
    private String matchName;
    private String selection;
    private double odd;
    private double stake;
    private String status;
    private String date;

    public BetDto() {}

    public BetDto(String username, int matchId, String matchName, String selection, double odd,
                  double stake, String status, String date) {
        this.username = username;
        this.matchId = matchId;
        this.matchName = matchName;
        this.selection = selection;
        this.odd = odd;
        this.stake = stake;
        this.status = status;
        this.date = date;
    }

    public String getUsername() { return username; }
    public int getMatchId() { return matchId; }
    public String getMatchName() { return matchName; }
    public String getSelection() { return selection; }
    public double getOdd() { return odd; }
    public double getStake() { return stake; }
    public String getStatus() { return status; }
    public String getDate() { return date; }

    public void setUsername(String username) { this.username = username; }
    public void setMatchId(int matchId) { this.matchId = matchId; }
    public void setMatchName(String matchName) { this.matchName = matchName; }
    public void setSelection(String selection) { this.selection = selection; }
    public void setOdd(double odd) { this.odd = odd; }
    public void setStake(double stake) { this.stake = stake; }
    public void setStatus(String status) { this.status = status; }
    public void setDate(String date) { this.date = date; }
}
