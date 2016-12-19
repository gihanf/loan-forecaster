-- MySQL dump 10.13  Distrib 5.7.16, for Linux (x86_64)
--
-- Host: localhost    Database: forecaster_db
-- ------------------------------------------------------
-- Server version	5.7.13-0ubuntu0.16.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `forecaster_db`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `forecaster_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `forecaster_db`;

--
-- Table structure for table `Loan`
--

DROP TABLE IF EXISTS `Loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Loan` (
  `id` int(11) NOT NULL,
  `currentBalance` decimal(11,2) NOT NULL,
  `description` varchar(255) NOT NULL,
  `interestRate` decimal(5,2) NOT NULL,
  `principalAmount` decimal(11,2) NOT NULL,
  `term` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Loan`
--

LOCK TABLES `Loan` WRITE;
/*!40000 ALTER TABLE `Loan` DISABLE KEYS */;
INSERT INTO `Loan` VALUES (1,320150.00,'Investment property loan',4.12,500000.00,30);
/*!40000 ALTER TABLE `Loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Payment` (
  `id` int(11) NOT NULL,
  `amount` decimal(11,2) NOT NULL,
  `description` varchar(255) NOT NULL,
  `paymentDirection` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
INSERT INTO `Payment` VALUES (2,7000.00,'Salary',0),(3,1400.00,'Rental income',0),(4,59.99,'Internet charges',1),(5,30.00,'Gas bills',1),(6,50.00,'Electricity',1),(7,2600.00,'Rent',1),(8,65.00,'Groceries',1),(9,750.12,'Car insurance',1),(10,350.00,'Car registration',1),(11,112.95,'Health insurance',1),(12,42.00,'Public transport',1);
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PaymentSchedule`
--

DROP TABLE IF EXISTS `PaymentSchedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaymentSchedule` (
  `firstPaymentDate` tinyblob,
  `frequency` int(11) DEFAULT NULL,
  `payment_id` int(11) NOT NULL,
  PRIMARY KEY (`payment_id`),
  CONSTRAINT `FK247mh79wda1ar0tdlh0nsb0ps` FOREIGN KEY (`payment_id`) REFERENCES `Payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaymentSchedule`
--

LOCK TABLES `PaymentSchedule` WRITE;
/*!40000 ALTER TABLE `PaymentSchedule` DISABLE KEYS */;
INSERT INTO `PaymentSchedule` VALUES ('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0YG\àP\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',2,2),('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0X\Ç!T\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',2,3),('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0X¼Ôœ\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',2,4),('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0XÖ”h\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',2,5),('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0X\Ñn\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',2,6),('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0XÁúø\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',2,7),('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0X·®@\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',1,8),('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0V\è1\è\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',3,9),('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0W>l\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',3,10),('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0X·®@\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',2,11),('¬\í\0sr\0org.joda.time.LocalDateÿÿø\Ó\ä\ëµ\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0X·®@\0sr\0\'org.joda.time.chrono.ISOChronology$Stub©\Èfq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub¦/š|2\Z\ã\0\0xpw\0UTCxx',1,12);
/*!40000 ALTER TABLE `PaymentSchedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (13),(13);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-19 22:09:28
