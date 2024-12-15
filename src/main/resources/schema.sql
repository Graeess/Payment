-- Таблица пользователей
CREATE TABLE IF NOT EXISTS app_user (  -- Переименованная таблица
                                        id SERIAL PRIMARY KEY,
                                        username VARCHAR(255) NOT NULL UNIQUE,
                                        password VARCHAR(255) NOT NULL
);

-- Таблица ролей
CREATE TABLE IF NOT EXISTS app_role (
                                        id SERIAL PRIMARY KEY,
                                        role_name VARCHAR(50) NOT NULL UNIQUE
);

-- Таблица связей пользователей и их ролей
CREATE TABLE IF NOT EXISTS user_roles (
                                          app_user_id BIGINT NOT NULL,  -- Ссылка на пользователя
                                          app_role_id BIGINT NOT NULL,  -- Ссылка на роль
                                          PRIMARY KEY (app_user_id, app_role_id),
                                          FOREIGN KEY (app_user_id) REFERENCES app_user(id) ON DELETE CASCADE,  -- Удаление пользователя удаляет его связи
                                          FOREIGN KEY (app_role_id) REFERENCES app_role(id) ON DELETE CASCADE  -- Удаление роли удаляет связь
);

-- Таблица платежей
CREATE TABLE IF NOT EXISTS payments (
                                        id SERIAL PRIMARY KEY,
                                        app_user_id BIGINT NOT NULL,  -- Ссылка на пользователя
                                        amount DECIMAL(10, 2) NOT NULL,
                                        payment_method VARCHAR(255) NOT NULL,
                                        payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (app_user_id) REFERENCES app_user(id) ON DELETE CASCADE  -- Удаление пользователя удаляет его платежи
);

-- Создание индексов для таблицы платежей
CREATE INDEX IF NOT EXISTS idx_app_user_id ON payments(app_user_id);  -- Индекс для app_user_id
CREATE INDEX IF NOT EXISTS idx_payment_method ON payments(payment_method);  -- Индекс для payment_method
CREATE INDEX IF NOT EXISTS idx_payment_date ON payments(payment_date);  -- Индекс для payment_date

-- Вставка ролей (USER, ADMIN)
INSERT INTO app_role (role_name) VALUES
                                     ('USER'),
                                     ('ADMIN');

-- Вставка пользователей (с паролем, зашифрованным с использованием BCrypt)
INSERT INTO app_user (username, password) VALUES  -- Вставка пользователей
                                                  ('Admin', '{bcrypt}$2a$10$9F12wZxBz1flowImcOQFjeHhgB/JMyqHoTS0wTjyfg7yLFNsXex6O'),
                                                  ('User', '{bcrypt}$2a$10$9F12wZxBz1flowImcOQFjeHhgB/JMyqHoTS0wTjyfg7yLFNsXex6O');

-- Вставка связей пользователей с их ролями
INSERT INTO user_roles (app_user_id, app_role_id)  -- Вставка связей пользователей и ролей
SELECT u.id, r.id
FROM app_user u, app_role r
WHERE u.username = 'Admin' AND r.role_name = 'ADMIN';

INSERT INTO user_roles (app_user_id, app_role_id)
SELECT u.id, r.id
FROM app_user u, app_role r
WHERE u.username = 'User' AND r.role_name = 'USER';
