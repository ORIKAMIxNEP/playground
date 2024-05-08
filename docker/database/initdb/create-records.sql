CREATE TABLE records
(
    record_id BIGSERIAL,
    record1   SMALLINT    NOT NULL,
    record2   VARCHAR(10) NOT NULL,
    PRIMARY KEY (record_id),
    UNIQUE (record1)
);

INSERT INTO records (record1, record2)
VALUES (1, 'record2');

