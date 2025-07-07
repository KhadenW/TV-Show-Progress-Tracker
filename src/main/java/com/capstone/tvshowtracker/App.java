package com.capstone.tvshowtracker;

import com.capstone.tvshowtracker.dao.impl.UserDaoImpl;
import com.capstone.tvshowtracker.dao.impl.ShowDaoImpl;
import com.capstone.tvshowtracker.dao.impl.TrackerDaoImpl;
import com.capstone.tvshowtracker.model.User;
import com.capstone.tvshowtracker.model.Show;
import com.capstone.tvshowtracker.model.Tracker;
import com.capstone.tvshowtracker.service.UserService;
import com.capstone.tvshowtracker.service.ShowService;
import com.capstone.tvshowtracker.service.TrackerService;
import com.capstone.tvshowtracker.exception.AuthenticationException;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService(new UserDaoImpl());
    private static final ShowService showService = new ShowService(new ShowDaoImpl());
    private static final TrackerService trackerService = new TrackerService(new TrackerDaoImpl());

    public static void main(String[] args) {
        System.out.println("Welcome to the TV Show Progress Tracker!");
        while (true) {
            System.out.println("\n1. Login\n2. Register\n3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    loginMenu();
                    break;
                case "2":
                    registerMenu();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void loginMenu() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        try {
            User user = userService.login(username, password);
            if (user.isAdmin()) {
                adminMenu(user);
            } else {
                userMenu(user);
            }
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void registerMenu() {
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();
        System.out.print("Choose a password: ");
        String password = scanner.nextLine();
        User user = new User(0, username, password, false);
        try {
            if (userService.register(user)) {
                System.out.println("Registration successful! You can now log in.");
            } else {
                System.out.println("Registration failed.");
            }
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View My Trackers");
            System.out.println("2. Add/Update Tracker");
            System.out.println("3. Delete Tracker");
            System.out.println("4. View All Shows");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewTrackers(user);
                    break;
                case "2":
                    addOrUpdateTracker(user);
                    break;
                case "3":
                    deleteTracker(user);
                    break;
                case "4":
                    viewAllShows();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void adminMenu(User user) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View All Shows");
            System.out.println("2. Add Show");
            System.out.println("3. Edit Show");
            System.out.println("4. Delete Show");
            System.out.println("5. View Show Stats");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewAllShows();
                    break;
                case "2":
                    addShow();
                    break;
                case "3":
                    editShow();
                    break;
                case "4":
                    deleteShow();
                    break;
                case "5":
                    viewShowStats();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void viewTrackers(User user) {
        List<Tracker> trackers = trackerService.getTrackersByUserId(user.getId());
        if (trackers.isEmpty()) {
            System.out.println("No trackers found.");
            return;
        }
        System.out.println("\nYour Trackers:");
        for (Tracker t : trackers) {
            Show show = showService.getShowById(t.getShowId());
            String progress = t.getStatus() + " (" + t.getEpisodesWatched() + "/" + show.getTotalEpisodes() + ")";
            String rating = t.getRating() > 0 ? (t.getRating() + "/5") : "Not rated";
            System.out.printf("%d. %s - %s, Rating: %s\n", t.getId(), show.getTitle(), progress, rating);
        }
    }

    private static void addOrUpdateTracker(User user) {
        viewAllShows();
        System.out.print("Enter Show ID to track: ");
        int showId = Integer.parseInt(scanner.nextLine());
        Show show = showService.getShowById(showId);
        if (show == null) {
            System.out.println("Show not found.");
            return;
        }
        System.out.print("Episodes watched (0-" + show.getTotalEpisodes() + "): ");
        int watched = Integer.parseInt(scanner.nextLine());
        String status = watched == 0 ? "Not Started" : (watched == show.getTotalEpisodes() ? "Completed" : "In Progress");
        System.out.print("Rating (1-5, 0 for none): ");
        int rating = Integer.parseInt(scanner.nextLine());
        Tracker tracker = trackerService.getTrackersByUserId(user.getId()).stream()
                .filter(t -> t.getShowId() == showId).findFirst()
                .orElse(new Tracker(0, user.getId(), showId, 0, "Not Started", 0));
        tracker.setEpisodesWatched(watched);
        tracker.setStatus(status);
        tracker.setRating(rating);
        if (trackerService.addOrUpdateTracker(tracker)) {
            System.out.println("Tracker updated.");
        } else {
            System.out.println("Failed to update tracker.");
        }
    }

    private static void deleteTracker(User user) {
        viewTrackers(user);
        System.out.print("Enter Tracker ID to delete: ");
        int trackerId = Integer.parseInt(scanner.nextLine());
        if (trackerService.deleteTracker(trackerId)) {
            System.out.println("Tracker deleted.");
        } else {
            System.out.println("Failed to delete tracker.");
        }
    }

    private static void viewAllShows() {
        List<Show> shows = showService.getAllShows();
        System.out.println("\nAvailable Shows:");
        for (Show s : shows) {
            System.out.printf("%d. %s (%d episodes)\n", s.getId(), s.getTitle(), s.getTotalEpisodes());
        }
    }

    private static void addShow() {
        System.out.print("Show title: ");
        String title = scanner.nextLine();
        System.out.print("Total episodes: ");
        int episodes = Integer.parseInt(scanner.nextLine());
        Show show = new Show(0, title, episodes);
        if (showService.addShow(show)) {
            System.out.println("Show added.");
        } else {
            System.out.println("Failed to add show.");
        }
    }

    private static void editShow() {
        viewAllShows();
        System.out.print("Enter Show ID to edit: ");
        int showId = Integer.parseInt(scanner.nextLine());
        Show show = showService.getShowById(showId);
        if (show == null) {
            System.out.println("Show not found.");
            return;
        }
        System.out.print("New title (leave blank to keep): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) show.setTitle(title);
        System.out.print("New total episodes (0 to keep): ");
        int episodes = Integer.parseInt(scanner.nextLine());
        if (episodes > 0) show.setTotalEpisodes(episodes);
        // For simplicity, delete and re-add
        showService.addShow(show);
        System.out.println("Show updated.");
    }

    private static void deleteShow() {
        viewAllShows();
        System.out.print("Enter Show ID to delete: ");
        int showId = Integer.parseInt(scanner.nextLine());
        // Not implemented: would require ShowDao.deleteShow
        System.out.println("Feature not implemented in this prototype.");
    }

    private static void viewShowStats() {
        viewAllShows();
        System.out.print("Enter Show ID for stats: ");
        int showId = Integer.parseInt(scanner.nextLine());
        List<Tracker> trackers = trackerService.getTrackersByShowId(showId);
        int completed = 0, inProgress = 0, notStarted = 0, total = 0, ratingSum = 0, ratingCount = 0;
        for (Tracker t : trackers) {
            total++;
            switch (t.getStatus()) {
                case "Completed": completed++; break;
                case "In Progress": inProgress++; break;
                case "Not Started": notStarted++; break;
            }
            if (t.getRating() > 0) {
                ratingSum += t.getRating();
                ratingCount++;
            }
        }
        System.out.printf("Completed: %d, In Progress: %d, Not Started: %d\n", completed, inProgress, notStarted);
        if (ratingCount > 0) {
            System.out.printf("Average Rating: %.2f/5\n", (double)ratingSum/ratingCount);
        } else {
            System.out.println("No ratings yet.");
        }
    }
} 