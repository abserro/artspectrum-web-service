--liquibase formatted sql

--changeset yurieva:001-filling-trole-table
--createdOn: May 9, 2024
--comment: filling in the "trole" table.
--rollback DROP TABLE IF EXISTS public.tuser;
INSERT INTO trole (id, name) VALUES
                                 (1, 'ROLE_ADMIN'),
                                 (2, 'ROLE_USER'),
                                 (3, 'ROLE_ARTIST'),;
