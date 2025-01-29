CREATE TABLE project_account_participations
(
    project_id               VARCHAR(26) REFERENCES projects (project_id) ON DELETE CASCADE,
    participating_account_id VARCHAR(26) REFERENCES accounts (account_id) ON DELETE CASCADE,
    PRIMARY KEY (project_id, participating_account_id)
);

INSERT INTO project_account_participations(project_id, participating_account_id)
VALUES ('0100ABCDEFGHJKMNPQRSTVWXYZ',
        '0000ABCDEFGHJKMNPQRSTVWXYZ'),
       ('0100ABCDEFGHJKMNPQRSTVWXYZ',
        '0001ABCDEFGHJKMNPQRSTVWXYZ'),
       ('0101ABCDEFGHJKMNPQRSTVWXYZ',
        '0000ABCDEFGHJKMNPQRSTVWXYZ');
