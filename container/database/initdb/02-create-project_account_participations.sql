CREATE TABLE project_account_participations
(
    project_id               VARCHAR(26) REFERENCES projects (project_id) ON DELETE CASCADE,
    account_id VARCHAR(26) REFERENCES accounts (account_id),
    PRIMARY KEY (project_id, account_id)
);
INSERT INTO project_account_participations(project_id, account_id)
VALUES ('1123456789ABCDEFGHJKMNPQRS',
        '0123456789ABCDEFGHJKMNPQRS');
