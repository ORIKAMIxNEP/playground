CREATE TABLE records
(
    record_id uuid     NOT NULL,
    column1   SMALLINT NOT NULL,
    column2   TEXT     NOT NULL,
    PRIMARY KEY (record_id)
);
CREATE INDEX ON records (record_id);

INSERT INTO records (record_id, column1, column2)
VALUES ('01234567-89ab-cdef-0123-456789abcdef', 0, 'a');
