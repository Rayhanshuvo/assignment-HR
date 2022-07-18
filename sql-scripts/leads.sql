CREATE DATABASE  IF NOT EXISTS `employee`;
USE `employee`;


CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
   `emp_id` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
   `title` varchar(45) DEFAULT NULL,
  `division` varchar(45) DEFAULT NULL,
  `building` varchar(45) DEFAULT NULL,
  `room` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

