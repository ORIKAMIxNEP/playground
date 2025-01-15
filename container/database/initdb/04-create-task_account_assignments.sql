CREATE TABLE task_account_assignments
(
    task_id             VARCHAR(26) REFERENCES tasks (task_id) ON DELETE CASCADE,
    account_id VARCHAR(26) REFERENCES accounts (account_id),
    PRIMARY KEY (task_id, account_id)
);
INSERT INTO task_account_assignments(task_id, account_id)
VALUES ('2123456789ABCDEFGHJKMNPQRS',
        '0123456789ABCDEFGHJKMNPQRS');
