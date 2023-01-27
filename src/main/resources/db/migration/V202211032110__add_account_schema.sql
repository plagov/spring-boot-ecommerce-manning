CREATE TABLE IF NOT EXISTS account_details
(
    email    TEXT NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS account_address
(
    email          TEXT NOT NULL,
    address_line_1 TEXT NOT NULL,
    address_line_2 TEXT NOT NULL,
    postcode       TEXT NOT NULL
)
