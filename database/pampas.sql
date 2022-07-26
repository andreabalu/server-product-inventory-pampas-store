BEGIN TRANSACTION;

DROP TABLE IF EXISTS colors, sizes, types, packages CASCADE;

CREATE TABlE colors (
                        id serial PRIMARY KEY,
                        color varchar(50) NOT NULL
);

CREATE TABLE sizes (
                       id serial PRIMARY KEY,
                       inches decimal NOT NULL
);

CREATE TABLE types (
                       id serial PRIMARY KEY,
                       pampas_type varchar(50) NOT NULL
);

CREATE TABLE packages (
                          id serial PRIMARY KEY,
                          name varchar(50),
                          description TEXT,
                          stems int NOT NULL,
                          color_id int NOT NULL,
                          size_id int NOT NULL,
                          type_id int NOT NULL,
                          quantity int NOT NULL,
                          price decimal NOT NULL,
                          FOREIGN KEY (size_id) REFERENCES sizes (id),
                          FOREIGN KEY (type_id) REFERENCES types (id),
                          FOREIGN KEY (color_id) REFERENCES colors(id),
                          UNIQUE (stems, color_id, size_id, type_id)
);

INSERT INTO colors (color) VALUES ('pink');
INSERT INTO colors (color) VALUES ('ivory');
INSERT INTO colors (color) VALUES ('beige');
INSERT INTO colors (color) VALUES ('brown');

INSERT INTO sizes (inches) VALUES (18);
INSERT INTO sizes (inches) VALUES (25);
INSERT INTO sizes (inches) VALUES (45);
INSERT INTO sizes (inches) VALUES (60);

INSERT INTO types (pampas_type) VALUES ('faux');
INSERT INTO types (pampas_type) VALUES ('natural dried');

COMMIT;