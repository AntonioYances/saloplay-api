package com.saloplay.dto;

import java.util.List;

public class MatchDto {

    private int id;
    private String name;
    private String time;
    private List<OddDto> odds;

    public MatchDto() {}

    public MatchDto(int id, String name, String time, List<OddDto> odds) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.odds = odds;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getTime() { return time; }
    public List<OddDto> getOdds() { return odds; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTime(String time) { this.time = time; }
    public void setOdds(List<OddDto> odds) { this.odds = odds; }

}
