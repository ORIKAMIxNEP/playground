CREATE TABLE tasks
(
    task_id            VARCHAR(26) NOT NULL,
    task_name          TEXT        NOT NULL,
    task_status        TEXT        NOT NULL,
    user_id            VARCHAR(26),
    due_date           TEXT,
    postpone_count     INTEGER,
    max_postpone_count INTEGER,
    PRIMARY KEY (task_id)
);

INSERT INTO tasks (task_id,
                   task_name,
                   task_status,
                   user_id, due_date,
                   postpone_count,
                   max_postpone_count)
VALUES ('1123456789ABCDEFGHJKMNPQRS',
        'TaskName',
        'UNDONE',
        '0123456789ABCDEFGHJKMNPQRS',
        'DueDate',
        0,
        10);
