DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALtER SEQUENCE global_meals_seq RESTART WITH 1;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
INSERT INTO meals (userId, dateTime, description, calories) VALUES
  (100000, '2018-04-02 09:00:00', 'Завтрак', 500),
  (100000, '2018-04-02 12:00:00', 'Обед', 500),
  (100001, '2018-04-02 09:00:00', 'Завтрак', 600),
  (100001, '2018-04-02 12:00:00', 'Обед', 500)