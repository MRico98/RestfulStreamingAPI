CREATE DATABASE  IF NOT EXISTS `streaming_api`;
USE `streaming_api`;

CREATE TABLE `users` (
  `id_user` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
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
  `id_video` int AUTO_INCREMENT,
  `titulo` varchar(255),
  `autor` int,
  `average_rating` float,
  `created_at` timestamp,
  `video_location` text,
  PRIMARY KEY (`id_video`, `autor`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tags` (
  `id_tag` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_ratings` (
  `id_user` int,
  `id_video` int,
  `rating` int,
  `created_at` timestamp,
  `updated_at` timestamp,
  PRIMARY KEY (`id_user`, `id_video`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `videos_clasifications` (
  `id_tag` int,
  `id_video` int,
  PRIMARY KEY (`id_tag`, `id_video`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_recommendations` (
  `id_user` int,
  `id_video` int,
  PRIMARY KEY (`id_user`, `id_video`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_preferences_tags` (
  `id_user` int,
  `id_tag` int,
  PRIMARY KEY (`id_user`, `id_tag`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `users` ADD FOREIGN KEY (`role`) REFERENCES `roles` (`id_role`);

ALTER TABLE `videos` ADD FOREIGN KEY (`autor`) REFERENCES `users` (`id_user`);

ALTER TABLE `user_ratings` ADD FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

ALTER TABLE `user_ratings` ADD FOREIGN KEY (`id_video`) REFERENCES `videos` (`id_video`);

ALTER TABLE `videos_clasifications` ADD FOREIGN KEY (`id_tag`) REFERENCES `tags` (`id_tag`);

ALTER TABLE `videos_clasifications` ADD FOREIGN KEY (`id_video`) REFERENCES `videos` (`id_video`);

ALTER TABLE `user_recommendations` ADD FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

ALTER TABLE `user_recommendations` ADD FOREIGN KEY (`id_video`) REFERENCES `videos` (`id_video`);

ALTER TABLE `user_preferences_tags` ADD FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

ALTER TABLE `user_preferences_tags` ADD FOREIGN KEY (`id_tag`) REFERENCES `tags` (`id_tag`);

LOCK TABLES `roles` WRITE;

INSERT INTO `roles` VALUES (1,'USER'),(2,'ADMIN');

UNLOCK TABLES;