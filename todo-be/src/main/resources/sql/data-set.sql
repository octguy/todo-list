INSERT INTO "user" (username, password, email) VALUES
('alice', 'hashed_password_1', 'alice@example.com'),
('bob',   'hashed_password_2', 'bob@example.com'),
('charlie','hashed_password_3', 'charlie@example.com');

-- Roles
INSERT INTO "role" (name) VALUES
('ROLE_ADMIN'),
('ROLE_USER');

-- Role assignments
INSERT INTO role_user (role_id, user_id) VALUES
(1, 1), -- alice = admin
(2, 1), -- alice = user
(2, 2), -- bob = user
(2, 3); -- charlie = user

-- Tasks
INSERT INTO task (user_id, title, description, deadline, is_completed) VALUES
(1, 'Finish project', 'Complete the backend service', '2025-09-15 18:00:00', FALSE),
(1, 'Buy groceries', 'Milk, Bread, Eggs', '2025-09-01 10:00:00', TRUE),
(2, 'Write report', 'Quarterly financial summary', '2025-09-05 09:00:00', FALSE),
(3, 'Plan trip', 'Vacation to Da Nang', '2025-09-20 20:00:00', FALSE);