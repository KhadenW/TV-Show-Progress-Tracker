package com.capstone.tvshowtracker.dao.impl;

import com.capstone.tvshowtracker.dao.ShowDao;
import com.capstone.tvshowtracker.model.Show;
import com.capstone.tvshowtracker.util.DBUtil;
import java.sql.*;
import java.util.*;

public class ShowDaoImpl implements ShowDao {
    @Override
    public List<Show> getAllShows() {
        List<Show> shows = new ArrayList<>();
        String sql = "SELECT * FROM `Show`";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                shows.add(new Show(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("total_episodes")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shows;
    }

    @Override
    public Show getShowById(int id) {
        String sql = "SELECT * FROM `Show` WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Show(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("total_episodes")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addShow(Show show) {
        String sql = "INSERT INTO `Show` (title, total_episodes) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, show.getTitle());
            stmt.setInt(2, show.getTotalEpisodes());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
} 