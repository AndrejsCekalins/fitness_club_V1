DROP TABLE IF EXISTS member_card CASCADE;
DROP TABLE IF EXISTS fitness_centers CASCADE;
DROP TABLE IF EXISTS workouts CASCADE;
DROP TABLE IF EXISTS age_groups CASCADE;
DROP TABLE IF EXISTS clients CASCADE;

CREATE TABLE IF NOT EXISTS `clients` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50) NOT NULL,
`personal_code` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `age_groups` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`age_group` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `workouts` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`workout` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `fitness_centers` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`fitness_center` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `member_card` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`client_id` BIGINT NOT NULL,
`age_group_id` BIGINT NOT NULL,
`workout_id` BIGINT NOT NULL,
`fitness_center_id` BIGINT NOT NULL,
`term_of_contract` DATETIME NOT NULL,
PRIMARY KEY (`id`)
);


ALTER TABLE `member_card`
ADD FOREIGN KEY (`client_id`) REFERENCES `clients`(`id`);

ALTER TABLE `member_card`
ADD FOREIGN KEY (`age_group_id`) REFERENCES `age_groups`(`id`);

ALTER TABLE `member_card`
ADD FOREIGN KEY (`workout_id`) REFERENCES `workouts`(`id`);

ALTER TABLE `member_card`
ADD FOREIGN KEY (`fitness_center_id`) REFERENCES `fitness_centers`(`id`);


CREATE INDEX ix_member_card_client_id
ON member_card (client_id);

CREATE INDEX ix_member_card_age_group_id
ON member_card (age_group_id);

CREATE INDEX ix_member_card_fitness_center_id
ON member_card (fitness_center_id);

