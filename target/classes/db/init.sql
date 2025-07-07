CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN DEFAULT FALSE
);

CREATE TABLE Show (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    total_episodes INT NOT NULL
);

CREATE TABLE Tracker (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    show_id INT,
    episodes_watched INT DEFAULT 0,
    status VARCHAR(20),
    rating INT,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (show_id) REFERENCES Show(id)
);

-- Sample data
INSERT INTO User (username, password, is_admin) VALUES ('admin', 'adminpass', TRUE);
INSERT INTO User (username, password, is_admin) VALUES ('khaden', 'watson', FALSE);
INSERT INTO Show (title, total_episodes) VALUES
('Breaking Bad', 62),
('Game of Thrones', 73),
('Stranger Things', 34),
('The Office', 201),
('Friends', 236),
('The Mandalorian', 24),
('The Crown', 50),
('The Witcher', 16),
('The Boys', 24),
('Better Call Saul', 63),
('Example Show 1', 24),
('Example Show 2', 12); 