
CREATE DATABASE bank_app_test;

CREATE SCHEMA bank_schema;

CREATE TABLE bank_schema.banks (
    id BIGSERIAL PRIMARY KEY
);

CREATE TABLE bank_schema.clients (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    passport_number VARCHAR(255) NOT NULL,
    bank_id BIGINT,
    FOREIGN KEY (bank_id) REFERENCES bank_schema.banks(id)
);

CREATE TABLE bank_schema.credits (
    id BIGSERIAL PRIMARY KEY,
    credit_limit DECIMAL(19, 2) NOT NULL,
    interest_rate DECIMAL(5, 2) NOT NULL,
    bank_id BIGINT,
    FOREIGN KEY (bank_id) REFERENCES bank_schema.banks(id)
);

CREATE TABLE bank_schema.loan_offers (
    id BIGSERIAL PRIMARY KEY,
    client_id BIGINT,
    credit_id BIGINT,
    loan_amount DECIMAL(19, 2) NOT NULL,
	total_interest DECIMAL(19,2) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES bank_schema.clients(id),
    FOREIGN KEY (credit_id) REFERENCES bank_schema.credits(id)
);

CREATE TABLE bank_schema.payment_schedules (
    id BIGSERIAL PRIMARY KEY,
    payment_date DATE,
    payment_amount DECIMAL(19, 2),
    principal_amount DECIMAL(19, 2),
    interest_amount DECIMAL(19, 2),
    loan_offer_id BIGINT,
    FOREIGN KEY (loan_offer_id) REFERENCES bank_schema.loan_offers(id)
);

INSERT INTO bank_schema.banks (id) VALUES (1);
INSERT INTO bank_schema.banks (id) VALUES (2);

INSERT INTO bank_schema.clients (name, phone, email, passport_number, bank_id) VALUES
('John Doe', '123-456-7890', 'john.doe@example.com', '123456789', 1),
('Jane Smith', '098-765-4321', 'jane.smith@example.com', '987654321', 2);

INSERT INTO bank_schema.credits (credit_limit, interest_rate, bank_id) VALUES
(5000.00, 3.50, 1),
(10000.00, 4.00, 2);

INSERT INTO bank_schema.loan_offers (client_id, credit_id, loan_amount, total_interest) VALUES
(1, 1, 3000.00, 105.00),
(2, 2, 7000.00, 245.00);

INSERT INTO bank_schema.payment_schedules (payment_date, payment_amount, principal_amount, interest_amount, loan_offer_id) VALUES
('2024-08-01', 300.00, 250.00, 50.00, 1),
('2024-09-01', 300.00, 250.00, 50.00, 1),
('2024-08-01', 700.00, 650.00, 50.00, 2),
('2024-09-01', 700.00, 650.00, 50.00, 2);