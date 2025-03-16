CREATE TABLE IF NOT EXISTS vacancy
(
    id BIGSERIAL PRIMARY KEY,
    keyword VARCHAR(255),
    name VARCHAR(255) not null,
    url VARCHAR(2048),
    experience VARCHAR(255) not null
--     salary BIGSERIAL,
--     city_name VARCHAR(255),
--     street VARCHAR(255),
--     work_format VARCHAR(255),
--     professional_role VARCHAR(255)
);
-- CREATE SEQUENCE IF NOT EXISTS vacancy_id_seq START WITH 1 INCREMENT BY 1;