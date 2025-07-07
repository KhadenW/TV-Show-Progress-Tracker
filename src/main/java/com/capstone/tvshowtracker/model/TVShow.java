package com.capstone.tvshowtracker.model;

public class TVShow {
    private int id;
    private String title;
    private int totalEpisodes;

    public TVShow() {}
    public TVShow(int id, String title, int totalEpisodes) {
        this.id = id;
        this.title = title;
        this.totalEpisodes = totalEpisodes;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getTotalEpisodes() { return totalEpisodes; }
    public void setTotalEpisodes(int totalEpisodes) { this.totalEpisodes = totalEpisodes; }
} 