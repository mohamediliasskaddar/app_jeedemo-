CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    age INTEGER
    );

INSERT INTO users (nom, email, age) VALUES
     ('iliass ', 'iliass@example.com', 12),
     ('Mohamed ', 'mohamed@example.com', 11),
     ('Kaddar ', 'Kaddar@example.com', 13),
     ('imk ', 'imk@example.com', 13);
