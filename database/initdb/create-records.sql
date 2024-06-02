CREATE TABLE tasks
(
    task_id            VARCHAR(26) NOT NULL,
    task_nane          TEXT        NOT NULL,
    task_status        TEXT        NOT NULL,
    user_id            VARCHAR(26) NOT NULL,
    due_date           TEXT        NOT NULL,
    postpone_count     INTEGER     NOT NULL,
    max_postpone_count INTEGER     NOT NULL,
    PRIMARY KEY (task_id)
);

INSERT INTO tasks (task_id, task_nane, task_status, user_id, due_date, postpone_count, max_postpone_count)
VALUES ('1123456789ABCDEFGHJKMNPQRS', 'task', 'UNDONE', '0123456789ABCDEFGHJKMNPQRS', 'a', 0, 10);
