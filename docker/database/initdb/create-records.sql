CREATE TABLE records
(
    record_id SERIAL,
    record1   VARCHAR(10) NOT NULL,
    record2   VARCHAR(10) NOT NULL,
    PRIMARY KEY (record_id),
    UNIQUE (record1)
);

INSERT INTO records (record1, record2)
VALUES ('record1', 'record2');

