CREATE TABLE task_account_assignments
(
    task_id    VARCHAR(26) REFERENCES tasks (task_id) ON DELETE CASCADE,
    assigned_account_id VARCHAR(26) REFERENCES accounts (account_id) ON DELETE CASCADE,
    PRIMARY KEY (task_id, assigned_account_id)
);

INSERT INTO task_account_assignments(task_id, assigned_account_id)
VALUES ('0200ABCDEFGHJKMNPQRSTVWXYZ',
        '0000ABCDEFGHJKMNPQRSTVWXYZ'),
       ('0200ABCDEFGHJKMNPQRSTVWXYZ',
        '0001ABCDEFGHJKMNPQRSTVWXYZ');
