package com.capstone.tvshowtracker.model;

public class Tracker {
    private int id;
    private int userId;
    private int showId;
    private int episodesWatched;
    private String status; // Not Started, In Progress, Completed
    private int rating; // 1-5 stars

    public Tracker() {}
    public Tracker(int id, int userId, int showId, int episodesWatched, String status, int rating) {
        this.id = id;
        this.userId = userId;
        this.showId = showId;
        this.episodesWatched = episodesWatched;
        this.status = status;
        this.rating = rating;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getShowId() { return showId; }
    public void setShowId(int showId) { this.showId = showId; }
    public int getEpisodesWatched() { return episodesWatched; }
    public void setEpisodesWatched(int episodesWatched) { this.episodesWatched = episodesWatched; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
} 