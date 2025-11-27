CREATE INDEX idx_ingredient_name ON ingredient(name);

ALTER TABLE ingredient
ALTER COLUMN name SET NOT NULL;

ALTER TABLE ingredient
ADD CONSTRAINT unique_ingredient_name UNIQUE (name);


CREATE INDEX idx_pizza_name ON pizza(name);

ALTER TABLE pizza
ALTER COLUMN name SET NOT NULL;

ALTER TABLE pizza
ADD CONSTRAINT unique_pizza_name UNIQUE (name);