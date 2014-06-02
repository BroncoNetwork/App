-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 02, 2014 at 05:17 PM
-- Server version: 5.1.57
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `a7453331_CS356`
--

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `username` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `password` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `Email` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `Course1` varchar(40) COLLATE latin1_general_ci DEFAULT NULL,
  `Course2` varchar(40) COLLATE latin1_general_ci DEFAULT NULL,
  `Course3` varchar(40) COLLATE latin1_general_ci DEFAULT NULL,
  `Course4` varchar(40) COLLATE latin1_general_ci DEFAULT NULL,
  `Course5` varchar(40) COLLATE latin1_general_ci DEFAULT NULL,
  `Course6` varchar(40) COLLATE latin1_general_ci DEFAULT NULL,
  `Course7` varchar(40) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `Course1` (`Course1`,`Course2`,`Course3`,`Course4`,`Course5`,`Course6`,`Course7`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `User`
--

INSERT INTO `User` VALUES('user', 'pass', 'user@yahoo.com', 'CS 356', 'CS 408', 'CS 450', 'CS 370', 'CS 245', 'CS 256', 'CS 301');
INSERT INTO `User` VALUES('tester', 'checking', 'heo6611@gmail.com', 'CS 128', 'CS 408', 'CS 301', NULL, NULL, NULL, NULL);
INSERT INTO `User` VALUES('aznafro', 'starburst', 'joekwon123@yahoo.com', 'CS 356', 'CS 408', 'CS 450', NULL, NULL, NULL, NULL);
INSERT INTO `User` VALUES('bschon', 'nogard', 'bsc1015@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `User` VALUES('ruud', 'heo89', 'kim@yahoo.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `User` VALUES('testing2', 'test', 'testing2@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `User` VALUES('testing', 'changed', 'test@yahoo.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `User` VALUES('heocon', 'conheo', 'heo@con.com', 'CS 200', 'CS 356', 'CS 128', 'CS 370', 'CS 130', NULL, NULL);
INSERT INTO `User` VALUES('aznafro91', 'starburst', 'aznafro91@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `User` VALUES('testuser', 'asdfqwer', 'test@test.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `User` VALUES('snham', 'pass', 'snham@csupomona.edu', 'CS 445', 'CS 140', 'CS 200', 'CS 408', 'CS 245', 'CS 130', NULL);
INSERT INTO `User` VALUES('bsc1015', 'nogard', 'blahh@blahhh.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `User` VALUES('hellothere', 'pass', 'snham92@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `User` VALUES('vivan', 'checking', 'vtn_247@yahoo.com', 'CS 128', NULL, NULL, NULL, NULL, NULL, NULL);
