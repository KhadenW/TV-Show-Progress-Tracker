package com.capstone.tvshowtracker.dao;

import com.capstone.tvshowtracker.model.Show;
import java.util.List;

public interface ShowDao {
    List<Show> getAllShows();
    Show getShowById(int id);
    boolean addShow(Show show);
    // Add more methods as needed
} 