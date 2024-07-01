CREATE TABLE due_date_details
(
    task_id            VARCHAR(26) REFERENCES tasks (task_id) ON DELETE CASCADE,
    due_date           TIMESTAMP NOT NULL,
    postpone_count     INTEGER   NOT NULL,
    max_postpone_count INTEGER   NOT NULL,
    PRIMARY KEY (task_id)
);
INSERT INTO due_date_details(task_id,
                             due_date,
                             postpone_count,
                             max_postpone_count)
VALUES ('2123456789ABCDEFGHJKMNPQRS',
        '2000-01-01T00:00:00Z',
        0,
        10)
