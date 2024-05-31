CREATE TABLE records
(
    record_id VARCHAR(26) NOT NULL,
    column1   SMALLINT    NOT NULL,
    column2   TEXT        NOT NULL,
    PRIMARY KEY (record_id)
);
CREATE INDEX ON records (record_id);

INSERT INTO records (record_id, column1, column2)
VALUES ('0123456789ABCDEFGHJKMNPQRS', 0, 'a');
