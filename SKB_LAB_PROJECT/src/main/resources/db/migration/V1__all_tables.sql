CREATE TABLE user_app (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE user_order (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_app(id) ON DELETE CASCADE
);

CREATE TABLE pizza (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    size INTEGER
);

CREATE TABLE ingredient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE order_pizza (
    order_id BIGINT,
    pizza_id BIGINT,
    PRIMARY KEY (order_id, pizza_id),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES user_order(id) ON DELETE CASCADE,
    CONSTRAINT fk_pizza FOREIGN KEY (pizza_id) REFERENCES pizza(id) ON DELETE CASCADE
);

CREATE TABLE pizza_ingredient (
    pizza_id BIGINT,
    ingredients_id BIGINT,
    PRIMARY KEY (pizza_id, ingredients_id),
    CONSTRAINT fk_pizza_ingredient_pizza FOREIGN KEY (pizza_id) REFERENCES pizza(id) ON DELETE CASCADE,
    CONSTRAINT fk_pizza_ingredient_ingredient FOREIGN KEY (ingredients_id) REFERENCES ingredient(id) ON DELETE CASCADE
);



INSERT INTO ingredient (name) VALUES
('Сыр моцарелла'),
('Томатный соус'),
('Пепперони'),
('Ветчина'),
('Грибы'),
('Оливки'),
('Ананас');

INSERT INTO pizza (name, size) VALUES
('Маргарита', 30),
('Пепперони', 30),
('Гавайская', 35),
('Четыре сезона', 40);

INSERT INTO pizza_ingredient (pizza_id, ingredients_id) VALUES
(1, 1),
(1, 2);

INSERT INTO pizza_ingredient (pizza_id, ingredients_id) VALUES
(2, 1),
(2, 2),
(2, 3);

INSERT INTO pizza_ingredient (pizza_id, ingredients_id) VALUES
(3, 1),
(3, 4),
(3, 7);

INSERT INTO pizza_ingredient (pizza_id, ingredients_id) VALUES
(4, 1),
(4, 2),
(4, 4),
(4, 5),
(4, 6);
