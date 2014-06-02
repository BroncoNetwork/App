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
-- Table structure for table `Comments`
--

CREATE TABLE `Comments` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `target` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `message` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `timestamp` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `root` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=26 ;

--
-- Dumping data for table `Comments`
--

INSERT INTO `Comments` VALUES(1, 'aznafro', 'aznafro', 'first post', '2014/05/31 19:24:59', 122);
INSERT INTO `Comments` VALUES(2, 'aznafro', 'aznafro', 'how''s the testing?', '2014/05/31 19:26:32', 122);
INSERT INTO `Comments` VALUES(3, 'aznafro', 'aznafro', 'kewl comments are wo', '2014/05/31 19:26:46', 122);
INSERT INTO `Comments` VALUES(4, 'aznafro', 'aznafro', 'list view. start scr', '2014/05/31 19:27:20', 122);
INSERT INTO `Comments` VALUES(5, 'aznafro', 'aznafro', 'alright cool', '2014/05/31 19:27:33', 122);
INSERT INTO `Comments` VALUES(6, 'aznafro', 'user', 'handsome boy teehee ', '2014/05/31 22:47:56', 120);
INSERT INTO `Comments` VALUES(7, 'heocon', 'user', 'wat''s up :)', '2014/06/01 01:24:27', 121);
INSERT INTO `Comments` VALUES(8, 'heocon', 'user', 'yeah working', '2014/06/01 01:24:46', 121);
INSERT INTO `Comments` VALUES(9, 'user', 'hallo', 'whoaaa', '2014/06/01 01:36:47', 125);
INSERT INTO `Comments` VALUES(10, 'user', 'hallo', 'wooow', '2014/06/01 01:36:59', 125);
INSERT INTO `Comments` VALUES(11, 'user', 'heocon', 'this is very nicceee', '2014/06/01 01:37:16', 124);
INSERT INTO `Comments` VALUES(12, 'user', 'heocon', 'and this is a cobtin', '2014/06/01 01:37:40', 124);
INSERT INTO `Comments` VALUES(13, 'aznafro', 'user', 'shweet', '2014/06/01 12:14:54', 121);
INSERT INTO `Comments` VALUES(14, 'user', 'hallo', 'cooool', '2014/06/02 01:11:48', 125);
INSERT INTO `Comments` VALUES(15, 'user', 'hallo', 'testing', '2014/06/02 02:11:37', 125);
INSERT INTO `Comments` VALUES(16, 'user', 'hallo', 'tesst', '2014/06/02 03:46:58', 125);
INSERT INTO `Comments` VALUES(21, 'user', 'user', 'hiiii', '2014/06/02 11:36:17', 148);
INSERT INTO `Comments` VALUES(20, 'tester', 'user', 'let''s form a study g', '2014/06/02 08:04:15', 149);
INSERT INTO `Comments` VALUES(22, 'user', 'user', 'i.imgur.com/jauHsXE.', '2014/06/02 12:45:56', 83);
INSERT INTO `Comments` VALUES(23, 'user', 'user', 'i.imgur.com/jauHsXE.', '2014/06/02 12:46:52', 83);
INSERT INTO `Comments` VALUES(24, 'user', 'user', 'i.imgur.com/jauHsXE.', '2014/06/02 12:55:43', 83);
INSERT INTO `Comments` VALUES(25, 'user', 'user', 'blahh', '2014/06/02 12:57:50', 83);
