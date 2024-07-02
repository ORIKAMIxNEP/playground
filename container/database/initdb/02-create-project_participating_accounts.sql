CREATE TABLE project_participating_accounts
(
    project_id               VARCHAR(26) REFERENCES projects (project_id) ON DELETE CASCADE,
    participating_account_id VARCHAR(26) REFERENCES accounts (account_id),
    PRIMARY KEY (project_id, participating_account_id)
);
INSERT INTO project_participating_accounts(project_id,
                                           participating_account_id)
VALUES ('1123456789ABCDEFGHJKMNPQRS',
        '0123456789ABCDEFGHJKMNPQRS');
