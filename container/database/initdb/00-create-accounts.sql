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
        'AccountName1',
        'Password1');
