drop table if exists `candidate`;
drop table if exists `historical_data`;
drop table if exists `places_offered`;
drop table if exists `managed_user`;

create table `candidate`(
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

create table `historical_data` (
                                   academic_year int primary key,
                                   funded_places int,
                                   offers_made int,
                                   ratio double
);


create table `places_offered` (
                                  id int primary key,
                                  places_offered int
);

create table `managed_user` (
                        uid int AUTO_INCREMENT primary key,
                        username varchar(30) unique,
                        password varchar(255),
                        user_role varchar(10)
);

