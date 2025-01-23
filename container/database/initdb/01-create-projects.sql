CREATE TABLE projects
(
    project_id   VARCHAR(26),
    project_name TEXT NOT NULL,
    PRIMARY KEY (project_id)
);

CREATE INDEX project_name_index ON projects (project_name);

INSERT INTO projects(project_id,
                     project_name)
VALUES ('0100ABCDEFGHJKMNPQRSTVWXYZ',
        'ProjectName0'),
       ('0101ABCDEFGHJKMNPQRSTVWXYZ',
        'ProjectName1');
