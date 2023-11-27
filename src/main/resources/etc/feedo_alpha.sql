
CREATE TABLE `user_details` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `resetPwd` tinyint DEFAULT NULL,
  `pwdExpired` tinyint DEFAULT NULL,
  `pwdResetDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user_survey_details` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `survey_id` int DEFAULT NULL,
  `completed` tinyint DEFAULT NULL,
  `completedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id_idx` (`user_id`),
  KEY `fk_survey_id_idx` (`survey_id`),
  CONSTRAINT `fk_survey_id` FOREIGN KEY (`survey_id`) REFERENCES `survey_details` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `survey_details` (
  `id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `active` tinyint DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `qts_details` (
  `id` int NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `survey_qts_details` (
  `id` int NOT NULL,
  `qtn_id` int DEFAULT NULL,
  `survery_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_qtn_id_idx` (`qtn_id`),
  KEY `fk_survey_id_idx` (`survery_id`),
  CONSTRAINT `fk_qtn_id` FOREIGN KEY (`qtn_id`) REFERENCES `qts_details` (`id`),
  CONSTRAINT `fk_qtn_survey_id` FOREIGN KEY (`survery_id`) REFERENCES `survey_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `ans_details` (
  `id` int NOT NULL,
  `answer` varchar(150) DEFAULT NULL,
  `qtn_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ans_qtn_id_idx` (`qtn_id`),
  CONSTRAINT `fk_ans_qtn_id` FOREIGN KEY (`qtn_id`) REFERENCES `qts_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


select * from hibernate_sequence;
insert into hibernate_sequence(next_val) values(1);