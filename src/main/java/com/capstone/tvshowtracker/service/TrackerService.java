package com.capstone.tvshowtracker.service;

import com.capstone.tvshowtracker.dao.TrackerDao;
import com.capstone.tvshowtracker.model.Tracker;
import java.util.List;

public class TrackerService {
    private final TrackerDao trackerDao;

    public TrackerService(TrackerDao trackerDao) {
        this.trackerDao = trackerDao;
    }

    public List<Tracker> getTrackersByUserId(int userId) {
        return trackerDao.getTrackersByUserId(userId);
    }

    public boolean addOrUpdateTracker(Tracker tracker) {
        return trackerDao.addOrUpdateTracker(tracker);
    }

    public boolean deleteTracker(int trackerId) {
        return trackerDao.deleteTracker(trackerId);
    }

    public List<Tracker> getTrackersByShowId(int showId) {
        return trackerDao.getTrackersByShowId(showId);
    }
} 