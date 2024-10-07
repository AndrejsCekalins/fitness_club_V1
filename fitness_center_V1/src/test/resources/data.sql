INSERT INTO clients(first_name, last_name, personal_code)
VALUES ('Vasja', 'Pupkin', 11111);

INSERT INTO clients(first_name, last_name, personal_code)
VALUES ('Andrey', 'Petrov', 22222);

INSERT INTO clients(first_name, last_name, personal_code)
VALUES ('Dmitrij', 'Ivanov', 33333);

INSERT INTO clients(first_name, last_name, personal_code)
VALUES ('Viktor', 'Sidorov', 33222);


INSERT INTO age_groups
VALUES (1, 'CHILD');

INSERT INTO age_groups
VALUES (2, 'ADULT');

INSERT INTO age_groups
VALUES (3, 'SENIOR');


INSERT INTO workouts
VALUES (1, 'GYM');

INSERT INTO workouts
VALUES (2, 'SWIMMING_POOL');

INSERT INTO workouts
VALUES (3, 'GROUP_CLASSES');


INSERT INTO fitness_centers
VALUES (1, 'IMANTA');

INSERT INTO fitness_centers
VALUES (2,'AKROPOLE');

INSERT INTO fitness_centers
VALUES (3, 'SAGA');

INSERT INTO fitness_centers
VALUES (4, 'RIGA_PLAZA');

INSERT INTO fitness_centers
VALUES (5, 'ZOLITUDE');


INSERT INTO member_card(client_id, age_group_id, workout_id, fitness_center_id, term_of_contract)
VALUES (1, 2, 2, 1, '2024-03-31 23:59:59');

INSERT INTO member_card(client_id, age_group_id, workout_id, fitness_center_id, term_of_contract)
VALUES (2, 3, 3, 4, '2024-04-30 23:59:59');

INSERT INTO member_card(client_id, age_group_id, workout_id, fitness_center_id, term_of_contract)
VALUES (3, 1, 1, 5, '2024-03-31 23:59:59');

INSERT INTO member_card(client_id, age_group_id, workout_id, fitness_center_id, term_of_contract)
VALUES (4, 2, 1, 3, '2024-05-31 23:59:59');