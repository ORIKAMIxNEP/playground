CREATE TABLE accounts
(
    account_id   VARCHAR(26),
    account_name TEXT NOT NULL,
    password     TEXT NOT NULL,
    PRIMARY KEY (account_id),
    UNIQUE (account_name)
);
INSERT INTO accounts(account_id,
                     account_name,
                     password)
VALUES ('0123456789ABCDEFGHJKMNPQRS',
        'AccountName',
        'Password');

CREATE TABLE projects
(
    project_id   VARCHAR(26),
    project_name TEXT NOT NULL,
    PRIMARY KEY (project_id)
);
CREATE INDEX index_project_name ON projects (project_name);
INSERT INTO projects(project_id,
                     project_name)
VALUES ('1123456789ABCDEFGHJKMNPQRS',
        'ProjectName');

CREATE TABLE project_participants
(
    project_id               VARCHAR(26) REFERENCES projects (project_id) ON DELETE CASCADE,
    participating_account_id VARCHAR(26) REFERENCES accounts (account_id),
    PRIMARY KEY (project_id, participating_account_id)
);
INSERT INTO project_participants(project_id,
                                 participating_account_id)
VALUES ('1123456789ABCDEFGHJKMNPQRS',
        '0123456789ABCDEFGHJKMNPQRS');

CREATE TABLE tasks
(
    task_id       VARCHAR(26),
    in_project_id VARCHAR(26) REFERENCES projects (project_id) ON DELETE CASCADE,
    task_name     TEXT    NOT NULL,
    status        TEXT    NOT NULL,
    index         INTEGER NOT NULL,
    PRIMARY KEY (task_id)
);
CREATE INDEX index_task_name ON tasks (task_name);
INSERT INTO tasks(task_id,
                  in_project_id,
                  task_name,
                  status,
                  index)
VALUES ('2123456789ABCDEFGHJKMNPQRS',
        '1123456789ABCDEFGHJKMNPQRS',
        'TaskName',
        'UNDONE',
        0);

CREATE TABLE task_assignees
(
    task_id             VARCHAR(26) REFERENCES tasks (task_id) ON DELETE CASCADE,
    assigned_account_id VARCHAR(26) REFERENCES accounts (account_id),
    PRIMARY KEY (task_id, assigned_account_id)
);
INSERT INTO task_assignees(task_id,
                           assigned_account_id)
VALUES ('2123456789ABCDEFGHJKMNPQRS',
        '0123456789ABCDEFGHJKMNPQRS');

CREATE TABLE due_date_detail
(
    in_task_id         VARCHAR(26) REFERENCES tasks (task_id) ON DELETE CASCADE,
    due_date           TIMESTAMP NOT NULL,
    postpone_count     INTEGER   NOT NULL,
    max_postpone_count INTEGER   NOT NULL,
    PRIMARY KEY (in_task_id)
);
INSERT INTO due_date_detail(in_task_id,
                            due_date,
                            postpone_count,
                            max_postpone_count)
VALUES ('2123456789ABCDEFGHJKMNPQRS',
        '2000-01-01 00:00:00',
        0,
        10)
