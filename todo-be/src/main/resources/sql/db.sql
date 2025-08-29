-- Remember which the connections you are in before dropping DB
drop database if exists "todo-app" with (force);

create database "todo-app";

-- Change connection to todo-app/postgres@PostgreSQLXX before creating tables
CREATE TABLE "user" (
    user_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE task (
    task_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(200),
    deadline TIMESTAMP NOT NULL,
    is_completed BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_at TIMESTAMP DEFAULT NULL,
    CONSTRAINT fk_task_user FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE CASCADE
);

CREATE TABLE "role" (
    role_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE role_user (
    role_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (role_id, user_id),
    CONSTRAINT fk_roleuser_role FOREIGN KEY (role_id) REFERENCES "role"(role_id) ON DELETE CASCADE,
    CONSTRAINT fk_roleuser_user FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE CASCADE
);

