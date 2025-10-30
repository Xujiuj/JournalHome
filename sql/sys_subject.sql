create table sys_subject
(
    subject_id   int auto_increment
        primary key,
    subject_name varchar(63)                        not null,
    create_by    varchar(31)                        null,
    create_time  datetime default CURRENT_TIMESTAMP null,
    update_by    varchar(31)                        null,
    update_time  datetime                           null,
    constraint sys_subject_pk
        unique (subject_id),
    constraint sys_subject_pk_3
        unique (subject_name)
);

INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (10, 'Multidisciplinary', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (11, 'Agricultural and Biological Sciences', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (12, 'Arts and Humanities', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (13, 'Biochemistry', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (14, 'Business', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (15, 'Chemical Engineering', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (16, 'Chemistry', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (17, 'Computer Science', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (18, 'Decision Sciences', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (19, 'Earth and Planetary Sciences', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (20, 'Economics', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (21, 'Energy', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (22, 'Engineering', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (23, 'Environmental Science', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (24, 'Immunology and Microbiology', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (25, 'Materials Science', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (26, 'Mathematics', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (27, 'Medicine', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (28, 'Neuroscience', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (29, 'Nursing', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (30, 'Pharmacology', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (31, 'Physics and Astronomy', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (32, 'Psychology', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (33, 'Social Sciences', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (34, 'Veterinary', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (35, 'Dentistry', null, '2025-10-27 15:20:57', null, null);
INSERT INTO journal.sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES (36, 'Health Professions', null, '2025-10-27 15:20:57', null, null);
