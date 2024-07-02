CREATE TABLE task_assigned_accounts
(
    task_id             VARCHAR(26) REFERENCES tasks (task_id) ON DELETE CASCADE,
    assigned_account_id VARCHAR(26) REFERENCES accounts (account_id),
    PRIMARY KEY (task_id, assigned_account_id)
);
INSERT INTO task_assigned_accounts(task_id,
                                   assigned_account_id)
VALUES ('2123456789ABCDEFGHJKMNPQRS',
        '0123456789ABCDEFGHJKMNPQRS');
