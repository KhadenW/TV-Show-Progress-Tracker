package com.capstone.tvshowtracker.dao.impl;

import com.capstone.tvshowtracker.dao.TrackerDao;
import com.capstone.tvshowtracker.model.Tracker;
import com.capstone.tvshowtracker.util.DBUtil;
import java.sql.*;
import java.util.*;

public class TrackerDaoImpl implements TrackerDao {
    @Override
    public List<Tracker> getTrackersByUserId(int userId) {
        List<Tracker> trackers = new ArrayList<>();
        String sql = "SELECT * FROM Tracker WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                trackers.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trackers;
    }

    @Override
    public Tracker getTracker(int userId, int showId) {
        String sql = "SELECT * FROM Tracker WHERE user_id = ? AND show_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, showId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addOrUpdateTracker(Tracker tracker) {
        String sql = "REPLACE INTO Tracker (id, user_id, show_id, episodes_watched, status, rating) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tracker.getId());
            stmt.setInt(2, tracker.getUserId());
            stmt.setInt(3, tracker.getShowId());
            stmt.setInt(4, tracker.getEpisodesWatched());
            stmt.setString(5, tracker.getStatus());
            stmt.setInt(6, tracker.getRating());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteTracker(int trackerId) {
        String sql = "DELETE FROM Tracker WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, trackerId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Tracker> getTrackersByShowId(int showId) {
        List<Tracker> trackers = new ArrayList<>();
        String sql = "SELECT * FROM Tracker WHERE show_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, showId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                trackers.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trackers;
    }

    private Tracker mapRow(ResultSet rs) throws SQLException {
        return new Tracker(
            rs.getInt("id"),
            rs.getInt("user_id"),
            rs.getInt("show_id"),
            rs.getInt("episodes_watched"),
            rs.getString("status"),
            rs.getInt("rating")
        );
    }
} 