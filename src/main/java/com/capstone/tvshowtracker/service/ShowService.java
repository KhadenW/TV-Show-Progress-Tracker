package com.capstone.tvshowtracker.service;

import com.capstone.tvshowtracker.dao.ShowDao;
import com.capstone.tvshowtracker.model.Show;
import java.util.List;

public class ShowService {
    private final ShowDao showDao;

    public ShowService(ShowDao showDao) {
        this.showDao = showDao;
    }

    public List<Show> getAllShows() {
        return showDao.getAllShows();
    }

    public Show getShowById(int id) {
        return showDao.getShowById(id);
    }

    public boolean addShow(Show show) {
        return showDao.addShow(show);
    }
} 