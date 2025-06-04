INSERT INTO persons (username, age, email, phone_number, password, roles, created_at, removed_at, created_user, removed_user, books) VALUES
('Weiss', 27, 'weiss@example.com', '1234567890', '4221', 'ROLE_ADMIN', NOW(), NULL, 'Person 1', NULL, NULL),
('Oliver', 18, 'peter@example.com', '0987654321', '5343', 'ROLE_USER', NOW(), NULL, 'Person 2', NULL, NULL);