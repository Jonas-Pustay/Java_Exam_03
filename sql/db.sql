CREATE DATABASE IF NOT EXISTS `auto_dealer`;
USE `auto_dealer`;
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
`registration_number` varchar(20) NOT NULL,
`brand` varchar(100) DEFAULT NULL,
`model` varchar(100) DEFAULT NULL,
`date_of_first_registration` datetime
DEFAULT NULL,
`price` int DEFAULT NULL,
PRIMARY KEY (`registration_number`)
);