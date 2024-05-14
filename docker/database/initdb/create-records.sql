CREATE TABLE records
(
    record_id BIGSERIAL,
    column1   SMALLINT    NOT NULL,
    column2   VARCHAR(10) NOT NULL,
    PRIMARY KEY (record_id)
);

INSERT INTO records (column1, column2)
VALUES (0, 'a');

