package com.capstone.tvshowtracker.dao;

import com.capstone.tvshowtracker.model.Tracker;
import java.util.List;

public interface TrackerDao {
    List<Tracker> getTrackersByUserId(int userId);
    Tracker getTracker(int userId, int showId);
    boolean addOrUpdateTracker(Tracker tracker);
    boolean deleteTracker(int trackerId);
    List<Tracker> getTrackersByShowId(int showId);
    // Add more methods as needed
} 