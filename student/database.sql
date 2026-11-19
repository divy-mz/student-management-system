CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;
CREATE TABLE IF NOT EXISTS students (
 student_id INT PRIMARY KEY,
 student_name VARCHAR(255), email VARCHAR(255), course_name VARCHAR(255),
 programming_score DOUBLE NOT NULL CHECK (programming_score BETWEEN 0 AND 100),
 mathematics_score DOUBLE NOT NULL CHECK (mathematics_score BETWEEN 0 AND 100)
);
CREATE TABLE IF NOT EXISTS student_skills (
 student_id INT NOT NULL, skill VARCHAR(255),
 FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);
