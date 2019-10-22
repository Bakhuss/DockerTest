CREATE TABLE IF NOT EXISTS image (
  id        INTEGER       PRIMARY KEY AUTO_INCREMENT
                          NOT NULL,
  version   INTEGER,
  img       LONGBLOB
);

CREATE TABLE IF NOT EXISTS book (
    id      INTEGER       PRIMARY KEY AUTO_INCREMENT
                          NOT NULL,
    version INTEGER,
    name    VARCHAR (256)
);

CREATE TABLE IF NOT EXISTS person (
    id              INTEGER      PRIMARY KEY AUTO_INCREMENT
                                 NOT NULL,
    version         INTEGER,
    first_name      VARCHAR (50),
    second_name     VARCHAR (50),
    surname         VARCHAR (50),
    birthday        DATE,
    phone           VARCHAR (15),
    email           VARCHAR (50),
    description     VARCHAR (255),
    main_image_id   INTEGER REFERENCES image(id)
);

CREATE TABLE IF NOT EXISTS book_writer (
  book_id   INTEGER REFERENCES book(id),
  person_id INTEGER REFERENCES person(id)
);

CREATE TABLE IF NOT EXISTS catalog (
    id          INTEGER     PRIMARY KEY AUTO_INCREMENT
                            NOT NULL,
    version     INTEGER,
    book_id     INTEGER     REFERENCES  book(id),
    isbn        VARCHAR(15),
    description TEXT,
    total_count INTEGER
);
