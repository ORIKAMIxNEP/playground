CREATE TABLE tasks
(
    task_id    VARCHAR(26),
    project_id VARCHAR(26) REFERENCES projects (project_id) ON DELETE CASCADE,
    task_name  TEXT    NOT NULL,
    status     TEXT    NOT NULL,
    index      INTEGER NOT NULL,
    PRIMARY KEY (task_id)
);
CREATE INDEX task_name_index ON tasks (task_name);
INSERT INTO tasks(task_id,
                  project_id,
                  task_name,
                  status,
                  index)
VALUES ('2123456789ABCDEFGHJKMNPQRS',
        '1123456789ABCDEFGHJKMNPQRS',
        'TaskName',
        'UNDONE',
        0);
