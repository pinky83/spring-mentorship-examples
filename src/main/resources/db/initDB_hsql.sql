DROP TABLE user_roles IF EXISTS;
DROP TABLE questions IF EXISTS;
DROP TABLE answers IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255),
  email            VARCHAR(255)         NOT NULL,
  password         VARCHAR(255)         NOT NULL,
  registered       TIMESTAMP DEFAULT now(),
  enabled          BOOLEAN   DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY ( user_id ) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE answers
(
  id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  description VARCHAR(255) NOT NULL,
  iscorrect   BOOLEAN DEFAULT false,
  score       FLOAT NOT NULL
);
CREATE UNIQUE INDEX answers_unique_description_score_idx ON answers (description, score)

CREATE TABLE questions
(
  id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  date_time   TIMESTAMP    NOT NULL,
  description VARCHAR(255) NOT NULL,
  answer_id     INTEGER      NOT NULL,
  FOREIGN KEY ( answer_id ) REFERENCES ANSWERS (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX questions_unique_description_idx ON questions (answer_id, description)

