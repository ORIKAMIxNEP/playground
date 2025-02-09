CREATE TABLE deadlines
(
    task_id            VARCHAR(26) REFERENCES tasks (task_id) ON DELETE CASCADE,
    due_date           TIMESTAMP NOT NULL,
    postpone_count     INTEGER   NOT NULL,
    max_postpone_count INTEGER   NOT NULL,
    PRIMARY KEY (task_id)
);

INSERT INTO deadlines(task_id,
                      due_date,
                      postpone_count,
                      max_postpone_count)
VALUES ('0200ABCDEFGHJKMNPQRSTVWXYZ',
        '2000-01-01T00:00:00Z',
        0,
        0),
       ('0201ABCDEFGHJKMNPQRSTVWXYZ',
        '2000-01-01T00:00:00Z',
        0,
        1)
