DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
  (100000, '2018-05-01 09:00:00', 'Завтрак', 500),
  (100000, '2018-05-01 12:00:00', 'Обед', 500),
  (100000, '2018-05-01 18:00:00', 'Ужин', 500),
  (100000, '2018-05-02 09:00:00', 'Завтрак', 500),
  (100000, '2018-05-02 12:00:00', 'Обед', 500),
  (100000, '2018-05-02 18:00:00', 'Ужин', 510);