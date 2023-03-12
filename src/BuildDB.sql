
CREATE DATABASE c22106964_recruitment_admission_db /*!40100 DEFAULT CHARACTER SET latin1 */;

USE c22106964_recruitment_admission_db;

DROP TABLE IF EXISTS candidate;
DROP TABLE IF EXISTS historical_data;
DROP TABLE IF EXISTS places_offered;
DROP TABLE IF EXISTS managed_user;

CREATE TABLE candidate(
                            student_no varchar(255) primary key,
                            dbscert_number varchar(255),
                            fastatus varchar(255),
                            ftpchecked int,
                            ucascardiff_course_code varchar(255),
                            application_status_code int,
                            application_status_comments varchar(255),
                            application_statushcare varchar(255),
                            cardiff_course_code varchar(255),
                            confirmation_comments varchar(255),
                            contextual_flag int,
                            correspondence_lang_welsh int,
                            country_of_domicile varchar(255),
                            date_of_birth date,
                            date_received varchar(255),
                            enrolment_criteria_comments varchar(255),
                            entry_year varchar(255),
                            essential_to_dos varchar(255),
                            fee_status int,
                            fee_status_comments varchar(255),
                            first_name varchar(255),
                            gender int,
                            grades_achieved varchar(255),
                            grades_achieved_after varchar(255),
                            highest_level_qualification varchar(255),
                            home_email varchar(255),
                            interview_date date,
                            interview_invite_comments varchar(255),
                            invite_interview int,
                            issue_date varchar(255),
                            keeping_warm_email_sent int,
                            latest_decision_code varchar(255),
                            nationality varchar(255),
                            non_standard_qualifications_chaser_email int,
                            offer_conditions varchar(255),
                            offer_email_sent int,
                            personalid varchar(255),
                            record_first_created date,
                            surname varchar(255),
                            total_interview_score int,
                            total_personal_statement_score int,
                            update_service varchar(255),
                            welsh_speaker int
);

CREATE TABLE historical_data (
                                   academic_year int primary key,
                                   funded_places int,
                                   offers_made int,
                                   ratio double
);


CREATE TABLE places_offered (
                                  id int primary key,
                                  places_offered int
);

CREATE TABLE managed_user (
                                uid int auto_increment primary key,
                                username varchar(30) unique,
                                password varchar(255),
                                user_role varchar(10)
);

INSERT INTO candidate
(student_no,
 dbscert_number,
 fastatus,
 ftpchecked,
 ucascardiff_course_code,
 application_status_code,
 application_status_comments,
 application_statushcare,
 cardiff_course_code,
 confirmation_comments,
 contextual_flag,
 correspondence_lang_welsh,
 country_of_domicile,
 date_of_birth,
 date_received,
 enrolment_criteria_comments,
 entry_year,
 essential_to_dos,
 fee_status,
 fee_status_comments,
 first_name,
 gender,
 grades_achieved,
 grades_achieved_after,
 highest_level_qualification,
 home_email,
 interview_date,
 interview_invite_comments,
 invite_interview,
 issue_date,
 keeping_warm_email_sent,
 latest_decision_code,
 nationality,
 non_standard_qualifications_chaser_email,
 offer_conditions,
 offer_email_sent,
 personalid,
 record_first_created,
 surname,
 total_interview_score,
 total_personal_statement_score,
 update_service,
 welsh_speaker)
VALUES
    ('01932678',
     '',
     '',
     0,
     'B720',
     1,
     '',
     'CFUF',
     'UFBWMIDA',
     '',
     1,
     0,
     'England',
     DATE '1983-03-03',
     '23.11.17',
     '',
     '2018/9',
     '',
     0,
     '',
     'Richard',
     0,
     '',
     '',
     '',
     'N/A',
     DATE '2018-01-09',
     '',
     0,
     '',
     0,
     'CODE',
     'UNITED KINGDOM',
     null,
     '',
     1,
     '1167890123',
     DATE '2007-11-23',
     'EKONG',
     50,
     60,
     '',
     0);


INSERT INTO candidate
(student_no,
 dbscert_number,
 fastatus,
 ftpchecked,
 ucascardiff_course_code,
 application_status_code,
 application_status_comments,
 application_statushcare,
 cardiff_course_code,
 confirmation_comments,
 contextual_flag,
 correspondence_lang_welsh,
 country_of_domicile,
 date_of_birth,
 date_received,
 enrolment_criteria_comments,
 entry_year,
 essential_to_dos,
 fee_status,
 fee_status_comments,
 first_name,
 gender,
 grades_achieved,
 grades_achieved_after,
 highest_level_qualification,
 home_email,
 interview_date,
 interview_invite_comments,
 invite_interview,
 issue_date,
 keeping_warm_email_sent,
 latest_decision_code,
 nationality,
 non_standard_qualifications_chaser_email,
 offer_conditions,
 offer_email_sent,
 personalid,
 record_first_created,
 surname,
 total_interview_score,
 total_personal_statement_score,
 update_service,
 welsh_speaker)
VALUES
    ('01932679',
     '',
     '',
     1,
     'B720',
     0,
     '',
     'CFUF',
     'UFBWMIDA',
     '',
     0,
     1,
     'Germany',
     DATE '1989-01-03',
     '28.11.17',
     '',
     '2018/9',
     '',
     1,
     '',
     'Adele',
     1,
     '',
     '',
     '',
     'N/A',
     DATE '2018-01-09',
     '',
     1,
     '',
     1,
     'CODE',
     'UNITED KINGDOM',
     null,
     '',
     1,
     '1167890124',
     DATE '2007-11-24',
     'Barry',
     50,
     45,
     '',
     0);

INSERT INTO managed_user(username, password, user_role) values ('Richard','password','USER');

INSERT INTO managed_user(username, password, user_role) values ('Faisal','password','USER');

INSERT INTO managed_user(username, password, user_role) values ('Bhatt','password','USER');

INSERT INTO managed_user(username, password, user_role) values ('Yinzou','password','USER');

INSERT INTO managed_user(username, password, user_role) values ('Yibo','password','USER');

INSERT INTO managed_user(username, password, user_role) values ('Admin','password','ADMIN');




