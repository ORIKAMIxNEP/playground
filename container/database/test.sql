SELECT *
FROM projects
    INNER JOIN project_account_participations
        ON projects.project_id = project_account_participations.project_id
    LEFT JOIN tasks
        ON projects.project_id = tasks.project_id
    LEFT JOIN task_account_assignments
        ON tasks.task_id = task_account_assignments.task_id
    LEFT JOIN due_date_details
        ON tasks.task_id = due_date_details.task_id
WHERE projects.project_id = '0100ABCDEFGHJKMNPQRSTVWXYZ'
ORDER BY tasks.index;
