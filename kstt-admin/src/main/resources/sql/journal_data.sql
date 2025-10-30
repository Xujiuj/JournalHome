-- ============================================
-- 期刊管理系统模拟数据
-- 文件名: journal_data.sql
-- 创建时间: 2025-01-27
-- 版本: 4.0
-- 用途: 插入期刊管理系统的模拟数据
-- ============================================

USE journal;

-- ============================================
-- 1. 插入基础数据
-- ============================================

-- 1.1 Article Status
INSERT INTO article_status (status_id, status_name, status_desc, sort_order) VALUES
(0, 'Draft', 'Draft status', 1),
(1, 'Submitted', 'Submitted', 2),
(2, 'Under Review', 'Under review', 3),
(3, 'Revision Requested', 'Revision in progress', 4),
(4, 'Accepted', 'Accepted', 5),
(5, 'Published', 'Published', 6),
(6, 'Rejected', 'Rejected', 7),
(7, 'Withdrawn', 'Withdrawn', 8);

-- 1.2 Manuscript Type
INSERT INTO article_manuscript_type (type_id, type_name, type_desc, sort_order) VALUES
(1, 'Original Research', 'Original research article', 1),
(2, 'Review', 'Review article', 2),
(3, 'Letter', 'Short communication', 3),
(4, 'Case Study', 'Case study', 4),
(5, 'Editorial', 'Editorial', 5),
(6, 'Perspective', 'Perspective article', 6),
(7, 'Comment', 'Comment', 7);

-- 1.3 Submission Type
INSERT INTO article_submission_type (type_id, type_name, type_desc, sort_order) VALUES
(1, 'Initial Submission', 'Initial submission', 1),
(2, 'Revised Submission', 'Revised submission', 2),
(3, 'Resubmission', 'Resubmission', 3);

-- 1.4 File Type
INSERT INTO article_file_type (type_id, type_name, type_desc, sort_order) VALUES
(1, 'Main Document', 'Main document', 1),
(2, 'Supplementary Material', 'Supplementary material', 2),
(3, 'Cover Letter', 'Cover letter', 3),
(4, 'Data File', 'Data file', 4),
(5, 'Figure', 'Figure file', 5),
(6, 'Table', 'Table file', 6);

-- 1.5 Academic Degree
INSERT INTO sys_user_academic_degree (degree_id, degree_name, degree_level, sort_order) VALUES
(1, 'Bachelor', 'Bachelor', 1),
(2, 'Master', 'Master', 2),
(3, 'Doctor', 'Doctor', 3),
(4, 'None', 'None', 4);

-- ============================================
-- 2. 插入基础数据
-- ============================================

-- 2.1 Role Data
INSERT INTO sys_role (role_id, role_name, role_sort, role_scope, role_status, create_by, create_time, remark) VALUES
(1, 'Guest', 1, 0, 1, 'admin', NOW(), 'Guest user'),
(2, 'Author', 2, 1, 1, 'admin', NOW(), 'Author who can submit manuscripts'),
(3, 'Reviewer', 3, 2, 1, 'admin', NOW(), 'Reviewer who can review manuscripts'),
(4, 'Editor', 4, 3, 1, 'admin', NOW(), 'Editor who can process manuscripts'),
(5, 'Editor-in-Chief', 5, 3, 1, 'admin', NOW(), 'Editor-in-Chief who can make final decisions'),
(6, 'Administrator', 6, 0, 1, 'admin', NOW(), 'System administrator');

-- 2.2 学科数据
INSERT INTO sys_subject (subject_id, subject_name, create_by, create_time, update_by, update_time) VALUES
(10, 'Multidisciplinary', null, NOW(), null, null),
(11, 'Agricultural and Biological Sciences', null, NOW(), null, null),
(12, 'Arts and Humanities', null, NOW(), null, null),
(13, 'Biochemistry', null, NOW(), null, null),
(14, 'Business', null, NOW(), null, null),
(15, 'Chemical Engineering', null, NOW(), null, null),
(16, 'Chemistry', null, NOW(), null, null),
(17, 'Computer Science', null, NOW(), null, null),
(18, 'Decision Sciences', null, NOW(), null, null),
(19, 'Earth and Planetary Sciences', null, NOW(), null, null),
(20, 'Economics', null, NOW(), null, null),
(21, 'Energy', null, NOW(), null, null),
(22, 'Engineering', null, NOW(), null, null),
(23, 'Environmental Science', null, NOW(), null, null),
(24, 'Immunology and Microbiology', null, NOW(), null, null),
(25, 'Materials Science', null, NOW(), null, null),
(26, 'Mathematics', null, NOW(), null, null),
(27, 'Medicine', null, NOW(), null, null),
(28, 'Neuroscience', null, NOW(), null, null),
(29, 'Nursing', null, NOW(), null, null),
(30, 'Pharmacology', null, NOW(), null, null),
(31, 'Physics and Astronomy', null, NOW(), null, null),
(32, 'Psychology', null, NOW(), null, null),
(33, 'Social Sciences', null, NOW(), null, null),
(34, 'Veterinary', null, NOW(), null, null),
(35, 'Dentistry', null, NOW(), null, null),
(36, 'Health Professions', null, NOW(), null, null);

-- 2.4 Journal Information
INSERT INTO sys_info (info_id, info_issn, info_name, info_simple_name, info_comment, info_mission, info_impact_factor, info_founded_year, info_publisher, info_website, info_email, info_phone, info_address, info_logo, create_by, create_time, remark) VALUES
(1, '1234-5678', 'Journal of Computer Science and Technology', 'JCST', 'International journal focusing on computer science and technology', 'Promoting academic exchange and development in computer science and technology', 2.5, 2020, 'Science Publishing House', 'https://jcst.example.com', 'editor@jcst.example.com', '+1-234-567-8900', '123 Tech Boulevard, Innovation City, USA 12345', '/images/journal-logo.png', 'admin', NOW(), 'Journal basic information');

-- 2.5 Journal Issues
INSERT INTO journal (journal_id, journal_volume, journal_issue, journal_publish_date, journal_status, journal_theme, journal_description, journal_cover, journal_pdf, create_by, create_time, remark) VALUES
(1, 1, 1, '2024-01-15', 1, 'Artificial Intelligence and Machine Learning', 'This issue focuses on the latest research achievements in artificial intelligence and machine learning', '/images/volume1-1-cover.jpg', '/pdfs/volume1-1.pdf', 'admin', NOW(), 'Volume 1, Issue 1'),
(2, 1, 2, '2024-04-15', 1, 'Big Data and Cloud Computing', 'This issue explores the latest advances in big data processing and cloud computing technologies', '/images/volume1-2-cover.jpg', '/pdfs/volume1-2.pdf', 'admin', NOW(), 'Volume 1, Issue 2'),
(3, 2, 1, '2024-07-15', 0, 'Cybersecurity and Privacy Protection', 'This issue discusses cutting-edge technologies in cybersecurity and privacy protection', '/images/volume2-1-cover.jpg', '/pdfs/volume2-1.pdf', 'admin', NOW(), 'Volume 2, Issue 1');

-- 2.6 Menu Data
INSERT INTO sys_menu (menu_id, menu_name, menu_parent_id, menu_order_num, menu_path, menu_component, menu_type, menu_visible, menu_status, menu_perms, menu_icon, create_by, create_time, remark) VALUES
(1, 'Home', 0, 1, '/', 'Home', 'C', 1, 1, 'home:view', 'home', 'admin', NOW(), 'Home menu'),
(2, 'About', 0, 2, '/about', 'About', 'C', 1, 1, 'about:view', 'info', 'admin', NOW(), 'About menu'),
(3, 'Articles', 0, 3, '/articles', 'Articles', 'C', 1, 1, 'articles:view', 'file-text', 'admin', NOW(), 'Articles menu'),
(4, 'Notices', 0, 4, '/notices', 'Notices', 'C', 1, 1, 'notices:view', 'bell', 'admin', NOW(), 'Notices menu'),
(5, 'Contact', 0, 5, '/contact', 'Contact', 'C', 1, 1, 'contact:view', 'mail', 'admin', NOW(), 'Contact menu'),
(6, 'Submit', 0, 6, '/submit', 'Submit', 'C', 1, 1, 'submit:view', 'edit', 'admin', NOW(), 'Submit menu'),
(7, 'My Submissions', 0, 7, '/my-submissions', 'MySubmissions', 'C', 1, 1, 'my-submissions:view', 'file-text', 'admin', NOW(), 'My Submissions menu'),
(8, 'User Menu', 0, 8, '', '', 'M', 1, 1, '', 'user', 'admin', NOW(), 'User menu group'),
(9, 'Profile', 8, 1, '/profile', 'Profile', 'C', 1, 1, 'profile:view', 'user', 'admin', NOW(), 'User profile menu'),
(10, 'Change Password', 8, 2, '/change-password', 'ChangePassword', 'C', 1, 1, 'password:change', 'lock', 'admin', NOW(), 'Change password menu'),
(11, 'My Submissions', 8, 3, '/my-submissions', 'MySubmissions', 'C', 1, 1, 'my-submissions:view', 'file-text', 'admin', NOW(), 'My submissions menu'),
(12, 'Logout', 8, 4, '', '', 'F', 1, 1, 'logout', 'log-out', 'admin', NOW(), 'Logout function');

-- 2.7 Notice Data
INSERT INTO notice (notice_id, notice_title, notice_content, notice_type, notice_status, notice_top, notice_order, create_by, create_time, remark) VALUES
(1, 'Journal Submission System Launch', 'Welcome to the Journal of Computer Science and Technology submission system. Please read the submission guidelines carefully.', 'announcement', 1, 1, 1, 'admin', NOW(), 'System launch notice'),
(2, 'Call for Papers - 2025 Issue 1', 'We are now accepting submissions for Issue 1, 2025. Theme: Artificial Intelligence and Machine Learning.', 'call_for_papers', 1, 0, 2, 'admin', NOW(), 'Call for papers'),
(3, 'Review Process Guidelines', 'Authors and reviewers are requested to read the review process guidelines carefully to ensure smooth submission and review processes.', 'guidelines', 1, 0, 3, 'admin', NOW(), 'Review process guidelines');

-- 2.8 FAQ Data
INSERT INTO contact_faq (faq_id, faq_question, faq_answer, faq_order, faq_status, create_by, create_time, remark) VALUES
(1, 'How to submit a manuscript?', 'Please register an account first, then log in to the system, click the "Submit" menu, and follow the instructions to fill in manuscript information and upload files.', 1, 1, 'admin', NOW(), 'Submission process question'),
(2, 'What is the review period?', 'Generally, the review period is 30 days, which may be extended under special circumstances.', 2, 1, 'admin', NOW(), 'Review period question'),
(3, 'What are the manuscript format requirements?', 'Please refer to the manuscript template on the journal website and strictly follow the format requirements when writing the manuscript.', 3, 1, 'admin', NOW(), 'Format requirements question'),
(4, 'How to check submission status?', 'After logging in, you can view the status and progress of all submissions on the "My Submissions" page.', 4, 1, 'admin', NOW(), 'Status query question');

-- 2.9 Contact Message Data
INSERT INTO contact_message (message_id, message_name, message_email, message_subject, message_content, message_status, message_reply, create_by, create_time, remark) VALUES
(1, 'John Smith', 'john.smith@example.com', 'Submission Inquiry', 'What are the submission requirements for your journal?', 1, 'Please refer to our submission guidelines for specific requirements. For more details, please visit our website.', 'admin', NOW(), 'Responded inquiry'),
(2, 'Mary Johnson', 'mary.johnson@example.com', 'Review Period', 'How long is the review period approximately?', 1, 'Generally, the review period is 30 days, which may be extended under special circumstances.', 'admin', NOW(), 'Responded inquiry'),
(3, 'David Brown', 'david.brown@example.com', 'Journal Scope', 'What research areas does your journal focus on?', 0, NULL, 'admin', NOW(), 'Pending inquiry');

-- ============================================
-- 3. 用户数据（依赖 sys_role）
-- ============================================

INSERT INTO sys_user (user_id, user_name, user_email, user_password, user_real_name, user_affiliation, user_department, user_title, user_position, user_phone, user_address, user_country, user_state, user_city, user_postal_code, user_role_id, user_avatar, user_status, user_email_verified, user_last_login, user_orcid, user_biography, user_classification, user_keywords, create_by, create_time, remark) VALUES
(1, 'admin', 'admin@jcst.example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyVhUy0nD2nZJx8Qz8Qz8Qz8Qz8', 'System Administrator', 'Editorial Office of JCST', 'Editorial Office', 'Editor-in-Chief', 'System Administrator', '+1-234-567-8900', '123 Tech Boulevard, Innovation City, USA 12345', 'USA', 'California', 'Innovation City', '12345', 6, '/images/admin-avatar.jpg', 1, 1, NOW(), '0000-0000-0000-0001', 'Focusing on journal management system development and maintenance', 'Journal Management', 'journal, system, management', 'system', NOW(), 'System administrator account'),
(2, 'john.researcher', 'john.researcher@university.edu', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyVhUy0nD2nZJx8Qz8Qz8Qz8Qz8', 'John Researcher', 'Stanford University', 'Computer Science Department', 'Professor', 'Distinguished Professor', '+1-650-123-4567', '450 Serra Mall, Stanford, CA 94305', 'USA', 'California', 'Stanford', '94305', 2, '/images/default-avatar.png', 1, 1, NOW(), '0000-0000-0000-0002', 'Focusing on artificial intelligence and machine learning research, with over 100 publications in top-tier journals and conferences.', 'Artificial Intelligence', 'AI, machine learning, neural networks', 'admin', NOW(), 'Author account'),
(3, 'mary.reviewer', 'mary.reviewer@university.edu', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyVhUy0nD2nZJx8Qz8Qz8Qz8Qz8', 'Mary Reviewer', 'MIT', 'Electrical Engineering and Computer Science', 'Associate Professor', 'Associate Professor', '+1-617-258-0000', '77 Massachusetts Ave, Cambridge, MA 02139', 'USA', 'Massachusetts', 'Cambridge', '02139', 3, '/images/default-avatar.png', 1, 1, NOW(), '0000-0000-0000-0003', 'Main research focus on computer vision and image processing, with multiple publications in top conferences such as CVPR and ICCV.', 'Computer Vision', 'computer vision, image processing, deep learning', 'admin', NOW(), 'Reviewer account'),
(4, 'robert.editor', 'robert.editor@university.edu', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyVhUy0nD2nZJx8Qz8Qz8Qz8Qz8', 'Robert Editor', 'Carnegie Mellon University', 'School of Computer Science', 'Research Professor', 'Senior Research Professor', '+1-412-268-0000', '5000 Forbes Ave, Pittsburgh, PA 15213', 'USA', 'Pennsylvania', 'Pittsburgh', '15213', 4, '/images/default-avatar.png', 1, 1, NOW(), '0000-0000-0000-0004', 'Long-term research in natural language processing and knowledge graphs, with over 50 SCI publications and serving as editor for multiple international journals.', 'Natural Language Processing', 'NLP, knowledge graph, deep learning', 'admin', NOW(), 'Editor account');

-- ============================================
-- 4. 论文数据（依赖 journal, article_status_enum 等）
-- ============================================

INSERT INTO article (article_id, article_manuscript_id, article_title, article_abstract, article_keywords, article_manuscript_type_id, article_journal_id, article_pages, article_word_count, article_figure_count, article_table_count, article_submit_time, article_accept_time, article_publish_time, article_online_time, article_doi, article_pdf, article_status_id, article_submission_type_id, article_cover_letter, article_type_id, article_volume, article_is_open_access, article_subject_areas, create_by, create_time, update_by, update_time, remark) VALUES
(1, 'MAN-2024-001', 'Deep Learning Applications in Natural Language Processing', 'This paper presents a comprehensive survey of deep learning techniques applied to natural language processing tasks, including recent advances in transformer architectures and their applications.', 'deep learning, NLP, transformers, machine learning', 1, 1, '15-28', 8500, 8, 3, '2024-01-10 10:00:00', '2024-03-15 14:30:00', '2024-04-01 09:00:00', '2024-03-25 10:00:00', '10.1000/journal.2024.001', '/pdfs/article-001.pdf', 5, 1, 'This paper represents our latest research in NLP.', 1, '1', 1, '1702,1703', 'author', '2024-01-10 10:00:00', 'editor', '2024-03-15 14:30:00', 'Published article'),
(2, 'MAN-2024-002', 'Machine Learning Approaches for Climate Change Prediction', 'We propose a novel machine learning framework for predicting climate patterns using satellite data and historical records.', 'machine learning, climate change, prediction, satellite data', 1, 1, '45-62', 12000, 12, 5, '2024-02-15 11:00:00', '2024-04-20 16:00:00', '2024-05-01 09:00:00', '2024-04-25 10:00:00', '10.1000/journal.2024.002', '/pdfs/article-002.pdf', 5, 1, 'This work addresses critical challenges in climate science.', 1, '1', 1, '1706,1902', 'author', '2024-02-15 11:00:00', 'editor', '2024-04-20 16:00:00', 'Published article'),
(3, 'MAN-2024-003', 'Quantum Computing Applications in Cryptography', 'This paper explores the potential of quantum computing in cryptographic systems and proposes new quantum-resistant algorithms.', 'quantum computing, cryptography, quantum algorithms, security', 2, 1, '80-95', 9800, 10, 4, '2024-03-20 14:00:00', NULL, NULL, NULL, NULL, '/pdfs/article-003.pdf', 2, 1, 'Our findings have significant implications for post-quantum cryptography.', 2, '1', 0, '1707,1705', 'author', '2024-03-20 14:00:00', NULL, NULL, 'Under review');

-- ============================================
-- 5. 作者数据（依赖 article, sys_user）
-- ============================================

INSERT INTO article_author (author_id, author_article_id, author_user_id, author_order, author_is_corresponding, author_contribution, create_by, create_time, update_by, update_time, remark) VALUES
(1, 1, 2, 1, 1, 'Conceptualization, Methodology, Writing - Original Draft', 'author', '2024-01-10 10:00:00', 'author', '2024-01-10 10:00:00', 'First author and corresponding author'),
(2, 1, 3, 2, 0, 'Data Curation, Formal Analysis, Validation', 'author', '2024-01-10 10:00:00', 'author', '2024-01-10 10:00:00', 'Second author'),
(3, 2, 4, 1, 1, 'Conceptualization, Methodology, Investigation, Writing - Original Draft, Supervision', 'author', '2024-02-15 11:00:00', 'author', '2024-02-15 11:00:00', 'First and corresponding author'),
(4, 2, 2, 2, 0, 'Data Curation, Visualization', 'author', '2024-02-15 11:00:00', 'author', '2024-02-15 11:00:00', 'Second author'),
(5, 3, 2, 1, 1, 'Conceptualization, Methodology, Software, Writing - Original Draft, Funding Acquisition', 'author', '2024-03-20 14:00:00', 'author', '2024-03-20 14:00:00', 'First and corresponding author');

INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1000, 'Multidisciplinary', 10, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1100, 'General Agricultural and Biolog', 11, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1101, 'Agricultural and Biological Sci', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1102, 'Agronomy and Crop Science', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1103, 'Animal Science and Zoology', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1104, 'Aquatic Science', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1105, 'Ecology, Evolution, Behavior and Systematics', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1106, 'Food Science', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1107, 'Forestry', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1108, 'Horticulture', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1109, 'Insect Science', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1110, 'Plant Science', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1111, 'Soil Science', 11, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1200, 'General Arts and Humanities', 12, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1201, 'Arts and Humanities (miscellane', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1202, 'History', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1203, 'Language and Linguistics', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1204, 'Archeology (arts and humanities', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1205, 'Classics', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1206, 'Conservation', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1207, 'History and Philosophy of Scien', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1208, 'Literature and Literary Theory', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1209, 'Museology', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1210, 'Music', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1211, 'Philosophy', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1212, 'Religious Studies', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1213, 'Visual Arts and Performing Arts', 12, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1300, 'General Biochemistry, Genetics and Molecular Biology', 13, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1301, 'Biochemistry, Genetics and Molecular Biology (miscellaneous)', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1302, 'Aging', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1303, 'Biochemistry', 13, '2025-10-27 15:25:35', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1304, 'Biophysics', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1305, 'Biotechnology', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1306, 'Cancer Research', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1307, 'Cell Biology', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1308, 'Clinical Biochemistry', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1309, 'Developmental Biology', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1310, 'Endocrinology', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1311, 'Genetics', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1312, 'Molecular Biology', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1313, 'Molecular Medicine', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1314, 'Physiology', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1315, 'Structural Biology', 13, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1400, 'General Business, Management and Accounting', 14, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1401, 'Business, Management and Accounting (miscellaneous)', 14, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1402, 'Accounting', 14, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1403, 'Business and International Mana', 14, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1404, 'Management Information Systems', 14, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1405, 'Management of Technology and In', 14, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1406, 'Marketing', 14, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1407, 'Organizational Behavior and Hum', 14, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1408, 'Strategy and Management', 14, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1409, 'Tourism, Leisure and Hospitality Management', 14, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1410, 'Industrial Relations', 14, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1500, 'General Chemical Engineering', 15, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1501, 'Chemical Engineering (miscellan', 15, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1502, 'Bioengineering', 15, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1503, 'Catalysis', 15, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1504, 'Chemical Health and Safety', 15, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1505, 'Colloid and Surface Chemistry', 15, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1506, 'Filtration and Separation', 15, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1507, 'Fluid Flow and Transfer Process', 15, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1508, 'Process Chemistry and Technolog', 15, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1600, 'General Chemistry', 16, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1601, 'Chemistry (miscellaneous)', 16, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1602, 'Analytical Chemistry', 16, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1603, 'Electrochemistry', 16, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1604, 'Inorganic Chemistry', 16, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1605, 'Organic Chemistry', 16, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1606, 'Physical and Theoretical Chemis', 16, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1607, 'Spectroscopy', 16, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1700, 'General Computer Science', 17, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1701, 'Computer Science (miscellaneous', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1702, 'Artificial Intelligence', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1703, 'Computational Theory and Mathem', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1704, 'Computer Graphics and Computer-', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1705, 'Computer Networks and Communica', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1706, 'Computer Science Applications', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1707, 'Computer Vision and Pattern Rec', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1708, 'Hardware and Architecture', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1709, 'Human-Computer Interaction', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1710, 'Information Systems', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1711, 'Signal Processing', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1712, 'Software', 17, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1800, 'General Decision Sciences', 18, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1801, 'Decision Sciences (miscellaneou', 18, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1802, 'Information Systems and Managem', 18, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1803, 'Management Science and Operatio', 18, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1804, '
Statistics, Probability and Uncertainty', 18, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1900, 'General Earth and Planetary Sci', 19, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1901, 'Earth and Planetary Sciences (m', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1902, 'Atmospheric Science', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1903, 'Computers in Earth Sciences', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1904, 'Earth-Surface Processes', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1905, 'Economic Geology', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1906, 'Geochemistry and Petrology', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1907, 'Geology', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1908, 'Geophysics', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1909, 'Geotechnical Engineering and En', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1910, 'Oceanography', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1911, 'Paleontology', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1912, 'Space and Planetary Science', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (1913, 'Stratigraphy', 19, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2000, 'General Economics, Econometrics and Finance', 20, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2001, 'Economics, Econometrics and Finance (miscellaneous)', 20, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2002, 'Economics and Econometrics', 20, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2003, 'Finance', 20, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2100, 'General Energy', 21, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2101, 'Energy (miscellaneous)', 21, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2102, 'Energy Engineering and Power Te', 21, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2103, 'Fuel Technology', 21, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2104, 'Nuclear Energy and Engineering', 21, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2105, 'Renewable Energy, Sustainability and the Environment', 21, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2200, 'General Engineering', 22, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2201, 'Engineering (miscellaneous)', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2202, 'Aerospace Engineering', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2203, 'Automotive Engineering', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2204, 'Biomedical Engineering', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2205, 'Civil and Structural Engineerin', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2206, 'Computational Mechanics', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2207, 'Control and Systems Engineering', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2208, 'Electrical and Electronic Engin', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2209, 'Industrial and Manufacturing En', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2210, 'Mechanical Engineering', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2211, 'Mechanics of Materials', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2212, 'Ocean Engineering', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2213, 'Safety, Risk, Reliability and Quality', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2214, 'Media Technology', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2215, 'Building and Construction', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2216, 'Architecture', 22, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2300, 'General Environmental Science', 23, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2301, 'Environmental Science (miscella', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2302, 'Ecological Modeling', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2303, 'Ecology', 23, '2025-10-27 15:25:35', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2304, 'Environmental Chemistry', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2305, 'Environmental Engineering', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2306, 'Global and Planetary Change', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2307, 'Health, Toxicology and Mutagenesis', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2308, 'Management, Monitoring, Policy and Law', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2309, 'Nature and Landscape Conservati', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2310, 'Pollution', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2311, 'Waste Management and Disposal', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2312, 'Water Science and Technology', 23, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2400, 'General Immunology and Microbio', 24, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2401, 'Immunology and Microbiology (mi', 24, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2402, 'Applied Microbiology and Biotec', 24, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2403, 'Immunology', 24, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2404, 'Microbiology', 24, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2405, 'Parasitology', 24, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2406, 'Virology', 24, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2500, 'General Materials Science', 25, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2501, 'Materials Science (miscellaneou', 25, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2502, 'Biomaterials', 25, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2503, 'Ceramics and Composites', 25, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2504, 'Electronic, Optical and Magnetic Materials', 25, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2505, 'Materials Chemistry', 25, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2506, 'Metals and Alloys', 25, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2507, 'Polymers and Plastics', 25, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2508, 'Surfaces, Coatings and Films', 25, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2600, 'General Mathematics', 26, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2601, 'Mathematics (miscellaneous)', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2602, 'Algebra and Number Theory', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2603, 'Analysis', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2604, 'Applied Mathematics', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2605, 'Computational Mathematics', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2606, 'Control and Optimization', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2607, 'Discrete Mathematics and Combin', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2608, 'Geometry and Topology', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2609, 'Logic', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2610, 'Mathematical Physics', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2611, 'Modeling and Simulation', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2612, 'Numerical Analysis', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2613, 'Statistics and Probability', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2614, 'Theoretical Computer Science', 26, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2700, 'General Medicine', 27, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2701, 'Medicine (miscellaneous)', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2702, 'Anatomy', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2703, 'Anesthesiology and Pain Medicin', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2704, 'Biochemistry (medical)', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2705, 'Cardiology and Cardiovascular M', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2706, 'Critical Care and Intensive Car', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2707, 'Complementary and Alternative M', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2708, 'Dermatology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2709, 'Drug Guides', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2710, 'Embryology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2711, 'Emergency Medicine', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2712, 'Endocrinology, Diabetes and Metabolism', 27, '2025-10-27 15:25:35', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2713, 'Epidemiology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2714, 'Family Practice', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2715, 'Gastroenterology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2716, 'Genetics (clinical)', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2717, 'Geriatrics and Gerontology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2718, 'Health Informatics', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2719, 'Health Policy', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2720, 'Hematology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2721, 'Hepatology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2722, 'Histology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2723, 'Immunology and Allergy', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2724, 'Internal Medicine', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2725, 'Infectious Diseases', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2726, 'Microbiology (medical)', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2727, 'Nephrology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2728, 'Neurology (clinical)', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2729, 'Obstetrics and Gynecology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2730, 'Oncology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2731, 'Ophthalmology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2732, 'Orthopedics and Sports Medicine', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2733, 'Otorhinolaryngology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2734, 'Pathology and Forensic Medicine', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2735, 'Pediatrics, Perinatology and Child Health', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2736, 'Pharmacology (medical)', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2737, 'Physiology (medical)', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2738, 'Psychiatry and Mental Health', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2739, 'Public Health, Environmental and Occupational Health', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2740, 'Pulmonary and Respiratory Medic', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2741, 'Radiology, Nuclear Medicine and Imaging', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2742, 'Rehabilitation', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2743, 'Reproductive Medicine', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2744, 'Reviews and References (medical', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2745, 'Rheumatology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2746, 'Surgery', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2747, 'Transplantation', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2748, 'Urology', 27, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2800, 'General Neuroscience', 28, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2801, 'Neuroscience (miscellaneous)', 28, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2802, 'Behavioral Neuroscience', 28, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2803, 'Biological Psychiatry', 28, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2804, 'Cellular and Molecular Neurosci', 28, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2805, 'Cognitive Neuroscience', 28, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2806, 'Developmental Neuroscience', 28, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2807, 'Endocrine and Autonomic Systems', 28, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2808, 'Neurology', 28, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2809, 'Sensory Systems', 28, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2900, 'General Nursing', 29, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2901, 'Nursing (miscellaneous)', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2902, 'Advanced and Specialized Nursin', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2903, 'Assessment and Diagnosis', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2904, 'Care Planning', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2905, 'Community and Home Care', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2906, 'Critical Care Nursing', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2907, 'Emergency Nursing', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2908, 'Fundamentals and Skills', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2909, 'Gerontology', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2910, 'Issues, Ethics and Legal Aspects', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2911, 'Leadership and Management', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2912, 'LPN and LVN', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2913, 'Maternity and Midwifery', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2914, 'Medical and Surgical Nursing', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2915, 'Nurse Assisting', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2916, 'Nutrition and Dietetics', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2917, 'Oncology (nursing)', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2918, 'Pathophysiology', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2919, 'Pediatrics', 29, '2025-10-27 15:25:35', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2920, 'Pharmacology (nursing)', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2921, 'Psychiatric Mental Health', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2922, 'Research and Theory', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (2923, 'Review and Exam Preparation', 29, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3000, 'General Pharmacology, Toxicology and Pharmaceutics', 30, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3001, 'Pharmacology, Toxicology and Pharmaceutics (miscellaneous)', 30, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3002, 'Drug Discovery', 30, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3003, 'Pharmaceutical Science', 30, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3004, 'Pharmacology', 30, '2025-10-27 15:25:35', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3005, 'Toxicology', 30, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3100, 'General Physics and Astronomy', 31, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3101, 'Physics and Astronomy (miscella', 31, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3102, 'Acoustics and Ultrasonics', 31, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3103, 'Astronomy and Astrophysics', 31, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3104, 'Condensed Matter Physics', 31, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3105, 'Instrumentation', 31, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3106, 'Nuclear and High Energy Physics', 31, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3107, 'Atomic and Molecular Physics, and Optics', 31, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3108, 'Radiation', 31, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3109, 'Statistical and Nonlinear Physi', 31, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3110, 'Surfaces and Interfaces', 31, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3200, 'General Psychology', 32, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3201, 'Psychology (miscellaneous)', 32, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3202, 'Applied Psychology', 32, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3203, 'Clinical Psychology', 32, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3204, 'Developmental and Educational P', 32, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3205, 'Experimental and Cognitive Psyc', 32, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3206, 'Neuropsychology and Physiologic', 32, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3207, 'Social Psychology', 32, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3300, 'General Social Sciences', 33, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3301, 'Social Sciences (miscellaneous)', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3302, 'Archeology', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3303, 'Development', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3304, 'Education', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3305, 'Geography, Planning and Development', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3306, 'Health (social science)', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3307, 'Human Factors and Ergonomics', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3308, 'Law', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3309, 'Library and Information Science', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3310, 'Linguistics and Language', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3311, 'Safety Research', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3312, 'Sociology and Political Science', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3313, 'Transportation', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3314, 'Anthropology', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3315, 'Communication', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3316, 'Cultural Studies', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3317, 'Demography', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3318, 'Gender Studies', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3319, 'Life-span and Life-course Studi', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3320, 'Political Science and Internati', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3321, 'Public Administration', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3322, 'Urban Studies', 33, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3400, 'General Veterinary', 34, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3401, 'Veterinary (miscellaneous)', 34, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3402, 'Equine', 34, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3403, 'Food Animals', 34, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3404, 'Small Animals', 34, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3500, 'General Dentistry', 35, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3501, 'Dentistry (miscellaneous)', 35, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3502, 'Dental Assisting', 35, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3503, 'Dental Hygiene', 35, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3504, 'Oral Surgery', 35, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3505, 'Orthodontics', 35, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3506, 'Periodontics', 35, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3600, 'General Health Professions', 36, '2025-10-27 15:22:12', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3601, 'Health Professions (miscellaneo', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3602, 'Chiropractics', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3603, 'Complementary and Manual Therap', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3604, 'Emergency Medical Services', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3605, 'Health Information Management', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3606, 'Medical Assisting and Transcrip', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3607, 'Medical Laboratory Technology', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3608, 'Medical Terminology', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3609, 'Occupational Therapy', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3610, 'Optometry', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3611, 'Pharmacy', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3612, 'Physical Therapy, Sports Therapy and Rehabilitation', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3613, 'Podiatry', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3614, 'Radiological and Ultrasound Tec', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3615, 'Respiratory Care', 36, '2025-10-27 15:24:25', null, null, null);
INSERT INTO journal.sys_area (area_id, area_name, area_subject_id, create_time, create_by, update_time, update_by) VALUES (3616, 'Speech and Hearing', 36, '2025-10-27 15:24:25', null, null, null);

