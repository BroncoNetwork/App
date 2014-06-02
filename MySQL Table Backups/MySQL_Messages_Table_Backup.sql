-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 02, 2014 at 05:19 PM
-- Server version: 5.1.57
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `a7453331_CS356`
--

-- --------------------------------------------------------

--
-- Table structure for table `Messages`
--

CREATE TABLE `Messages` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `target` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `timestamp` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `message` varchar(250) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=24 ;

--
-- Dumping data for table `Messages`
--

INSERT INTO `Messages` VALUES(3, 'aznafro91', 'aznafro', '2014/05/24 20:00:19', 'hi there!');
INSERT INTO `Messages` VALUES(4, 'user', 'user', '2014/06/02 07:30:52', 'hello user\n');
INSERT INTO `Messages` VALUES(7, 'bschon', 'user', '2014/05/27 17:04:10', 'freakin awesome');
INSERT INTO `Messages` VALUES(10, 'blahblah', 'snham', '2014/05/28 16:12:54', 'sup');
INSERT INTO `Messages` VALUES(11, 'bsc1015', 'bschon', '2014/05/28 16:41:57', 'heyyyy');
INSERT INTO `Messages` VALUES(12, 'bsc1015', 'bschon', '2014/05/28 16:41:58', 'heyyyy');
INSERT INTO `Messages` VALUES(13, 'bsc1015', 'bschon', '2014/05/28 16:42:12', 'heyyyy');
INSERT INTO `Messages` VALUES(14, 'snham', 'snham', '2014/05/28 18:19:15', 'dndjdkkdkd');
INSERT INTO `Messages` VALUES(15, 'heocon', 'user', '2014/05/28 20:35:49', 'hello this is me :)');
INSERT INTO `Messages` VALUES(17, 'tester', 'user', '2014/06/02 07:52:01', 'help me please');
INSERT INTO `Messages` VALUES(18, 'user', 'user', '2014/06/02 07:50:40', 'user is not very friendly');
INSERT INTO `Messages` VALUES(19, 'tester', 'user', '2014/06/02 08:04:49', 'hey want to meet up and study for finals?\n');
