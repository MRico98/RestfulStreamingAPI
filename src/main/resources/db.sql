CREATE DATABASE  IF NOT EXISTS `streaming_api`;
USE `streaming_api`;

CREATE TABLE `users` (
  `id_user` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) UNIQUE,
  `email` varchar(255),
  `password` varchar(255),
  `role` int,
  `created_at` timestamp,
  `updated_at` timestamp
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `roles` (
  `id_role` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `videos` (
  `id_video` varchar(255),
  `titulo` varchar(255),
  `autor` int,
  `average_rating` float default 0,
  `created_at` timestamp,
  `video_location` text,
  PRIMARY KEY (`id_video`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_ratings` (
  `id_user` int,
  `id_video` varchar(255),
  `rating` int,
  `created_at` timestamp,
  `updated_at` timestamp,
  PRIMARY KEY (`id_user`, `id_video`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `videos_clasifications` (
    `id_videos_clasifications` int PRIMARY KEY AUTO_INCREMENT,
  `id_video` varchar(255),
  `tag` varchar(50)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_recommendations` (
  `id_user` int,
  `id_video` varchar(255),
  PRIMARY KEY (`id_user`, `id_video`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_preferences_tags` (
  `id_user` int,
  `tag` varchar(50),
  PRIMARY KEY (`id_user`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `users` ADD FOREIGN KEY (`role`) REFERENCES `roles` (`id_role`);

ALTER TABLE `videos` ADD FOREIGN KEY (`autor`) REFERENCES `users` (`id_user`);

ALTER TABLE `user_ratings` ADD FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

ALTER TABLE `user_ratings` ADD FOREIGN KEY (`id_video`) REFERENCES `videos` (`id_video`);

ALTER TABLE `videos_clasifications` ADD FOREIGN KEY (`id_video`) REFERENCES `videos` (`id_video`);

ALTER TABLE `user_recommendations` ADD FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

ALTER TABLE `user_recommendations` ADD FOREIGN KEY (`id_video`) REFERENCES `videos` (`id_video`);

ALTER TABLE `user_preferences_tags` ADD FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

LOCK TABLES `roles` WRITE;
LOCK TABLES `users` WRITE;

INSERT INTO `roles` VALUES (1,'USER'),(2,'ADMIN');
INSERT INTO `users` (name,email,password,role,created_at,updated_at) VALUES ("rico","ManuelRico98@outlook.es","root",1,"2021-01-01 00:00:00","2021-01-01 00:00:00");

UNLOCK TABLES;