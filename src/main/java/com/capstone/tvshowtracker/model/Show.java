package com.capstone.tvshowtracker.model;

public class Show {
    private int id;
    private String title;
    private int totalEpisodes;

    public Show() {}
    public Show(int id, String title, int totalEpisodes) {
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