CREATE DATABASE IF NOT EXISTS `forecaster_db`;
USE `forecaster_db`;

--
-- Table structure for table `Expense`
--

DROP TABLE IF EXISTS `Expense`;
CREATE TABLE `Expense` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `amount` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Table structure for table `PaymentSchedule`
--

DROP TABLE IF EXISTS `PaymentSchedule`;
CREATE TABLE `PaymentSchedule` (
  `expense_id` int(11) unsigned NOT NULL,
  `frequency` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`expense_id`),
  CONSTRAINT `fk_paymentSchedule_expenseid` FOREIGN KEY (`expense_id`) REFERENCES `Expense` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;