-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 02, 2014 at 05:18 PM
-- Server version: 5.1.57
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `a7453331_CS356`
--

-- --------------------------------------------------------

--
-- Table structure for table `Post`
--

CREATE TABLE `Post` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `target` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `message` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `timeStamp` varchar(20) COLLATE latin1_general_ci NOT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=155 ;

--
-- Dumping data for table `Post`
--

INSERT INTO `Post` VALUES(130, 'snham', 'CS 408', 'Hello there', '2014/06/02 05:54:29');
INSERT INTO `Post` VALUES(97, 'user', 'CS 356', 'blahh', '2014/05/28 16:06:19');
INSERT INTO `Post` VALUES(98, 'user', 'CS 356', 'jshdsa', '2014/05/28 16:06:25');
INSERT INTO `Post` VALUES(95, 'user', 'CS 356', 'hdjakh', '2014/05/28 16:05:34');
INSERT INTO `Post` VALUES(96, 'user', 'CS 356', 'hskxbvh', '2014/05/28 16:05:43');
INSERT INTO `Post` VALUES(131, 'tester', 'CS 408', 'Hi! Welcome to CS 408!', '2014/06/02 05:55:41');
INSERT INTO `Post` VALUES(94, 'user', 'CS 356', 'cooool', '2014/05/28 16:05:22');
INSERT INTO `Post` VALUES(92, 'aznafro', 'CS 356', 'testing', '2014/05/28 15:59:38');
INSERT INTO `Post` VALUES(90, 'user', 'CS 256', 'ghdjfhf', '2014/05/28 15:54:31');
INSERT INTO `Post` VALUES(91, 'heocon', 'CS 408', 'najshdhfufbf', '2014/05/28 15:58:22');
INSERT INTO `Post` VALUES(89, 'snham', 'CS 200', 'fdffggghg', '2014/05/28 15:21:41');
INSERT INTO `Post` VALUES(87, 'user', 'CS 499', 'gakdixhw', '2014/05/28 13:32:54');
INSERT INTO `Post` VALUES(88, 'user', 'CS 499', 'jwisgsiqojb', '2014/05/28 13:33:00');
INSERT INTO `Post` VALUES(86, 'user', 'CS 356', 'coool', '2014/05/28 13:28:59');
INSERT INTO `Post` VALUES(85, 'user', 'CS 356', 'blahhh', '2014/05/28 13:23:36');
INSERT INTO `Post` VALUES(74, 'heocon', 'CS 408', 'i dont understand', '2014/05/28 07:50:54');
INSERT INTO `Post` VALUES(73, 'heocon', 'CS 356', 'no why', '2014/05/28 07:50:30');
INSERT INTO `Post` VALUES(72, 'user', 'CS 356', 'help me', '2014/05/28 07:49:44');
INSERT INTO `Post` VALUES(71, 'user', 'CS 408', 'yeah yeah wrong', '2014/05/28 07:49:28');
INSERT INTO `Post` VALUES(70, 'user', 'CS 408', 'come on', '2014/05/28 07:49:11');
INSERT INTO `Post` VALUES(69, 'user', 'CS 408', ' gru gru', '2014/05/28 07:44:44');
INSERT INTO `Post` VALUES(84, 'user', 'CS 356', 'i.imgur.com/kTSskl8.gif', '2014/05/28 13:05:00');
INSERT INTO `Post` VALUES(83, 'user', 'CS 356', 'i.imgur.com/jauHsXE.png', '2014/05/28 12:54:51');
INSERT INTO `Post` VALUES(81, 'user', 'CS 356', 'i.imgur.com/jauHsXE.png', '2014/05/28 12:38:57');
INSERT INTO `Post` VALUES(82, 'user', 'CS 408', 'ajworbf', '2014/05/28 12:41:26');
INSERT INTO `Post` VALUES(78, 'user', 'CS 260', 'nalllh', '2014/05/28 10:55:56');
INSERT INTO `Post` VALUES(80, 'bschon', 'CS 140', 'does this workkkk?', '2014/05/28 10:56:50');
INSERT INTO `Post` VALUES(79, 'user', 'CS 450', 'cooool', '2014/05/28 10:56:06');
INSERT INTO `Post` VALUES(77, 'user', 'CS 130', 'kdihab', '2014/05/28 10:54:35');
INSERT INTO `Post` VALUES(76, 'user', 'CS 130', 'blahhh', '2014/05/28 10:54:31');
INSERT INTO `Post` VALUES(75, 'user', 'CS 408', 'la la la...', '2014/05/28 08:15:24');
INSERT INTO `Post` VALUES(60, 'heocon', 'CS 408', 'test test in the morning', '2014/05/28 07:23:43');
INSERT INTO `Post` VALUES(100, 'user', 'CS 356', 'coool', '2014/05/28 16:06:32');
INSERT INTO `Post` VALUES(101, 'user', 'CS 356', 'jsjfhwb', '2014/05/28 16:06:40');
INSERT INTO `Post` VALUES(102, 'user', 'CS 356', 'blahhh', '2014/05/28 16:07:07');
INSERT INTO `Post` VALUES(103, 'user', 'CS 356', 'hello now 356', '2014/05/28 16:08:18');
INSERT INTO `Post` VALUES(104, 'user', 'CS 408', 'hello 408 ', '2014/05/28 16:08:31');
INSERT INTO `Post` VALUES(105, 'user', 'CS 408', 'hflfjfnfnf', '2014/05/28 16:08:38');
INSERT INTO `Post` VALUES(106, 'user', 'CS 356', 'is this working', '2014/05/28 16:09:18');
INSERT INTO `Post` VALUES(107, 'snham', 'CS 408', 'hello ddfg', '2014/05/28 16:10:11');
INSERT INTO `Post` VALUES(132, 'user', 'CS 356', 'dfdsgdsgdsasdfdfasffsf', '2014/06/02 09:44:05');
INSERT INTO `Post` VALUES(109, 'user', 'CS 408', 'hello 408 1', '2014/05/28 16:15:35');
INSERT INTO `Post` VALUES(110, 'user', 'CS 408', 'hello 408 2', '2014/05/28 16:15:40');
INSERT INTO `Post` VALUES(129, 'heo', 'CS 128', 'hello ', '2014/05/31 01:04:06');
INSERT INTO `Post` VALUES(112, 'user', 'CS 408', 'hello 408 3', '2014/05/28 16:17:37');
INSERT INTO `Post` VALUES(113, 'user', 'CS 408', 'hello 408 4', '2014/05/28 16:17:47');
INSERT INTO `Post` VALUES(114, 'user', 'CS 408', 'blahhh', '2014/05/28 16:29:17');
INSERT INTO `Post` VALUES(115, 'user', 'CS 408', 'hrhfbfb', '2014/05/28 16:29:54');
INSERT INTO `Post` VALUES(116, 'user', 'CS 408', 'hdhdjdh', '2014/05/28 16:29:57');
INSERT INTO `Post` VALUES(117, 'user', 'CS 408', 'gftgg', '2014/05/28 16:30:29');
INSERT INTO `Post` VALUES(118, 'user', 'CS 408', 'fhdhdbf', '2014/05/28 16:30:39');
INSERT INTO `Post` VALUES(119, 'user', 'CS 408', 'bdbfhfjf', '2014/05/28 16:30:42');
INSERT INTO `Post` VALUES(120, 'user', 'CS 356', 'this is me handsome boy :)', '2014/05/28 16:49:24');
INSERT INTO `Post` VALUES(121, 'user', 'CS 356', 'and this is meee', '2014/05/28 16:49:30');
INSERT INTO `Post` VALUES(122, 'aznafro', 'CS 356', 'refresh test', '2014/05/28 16:49:38');
INSERT INTO `Post` VALUES(123, 'heocon', 'CS 128', 'hahahah', '2014/05/28 16:53:45');
INSERT INTO `Post` VALUES(124, 'heocon', 'CS 370', 'hello boys :)', '2014/05/28 17:03:32');
INSERT INTO `Post` VALUES(125, 'hallo', 'CS 370', 'hello :)', '2014/05/28 17:27:29');
INSERT INTO `Post` VALUES(126, 'heocon', 'CS 431', 'hello 431 ', '2014/05/28 17:55:34');
INSERT INTO `Post` VALUES(127, 'conheo', 'CS 480', 'hello i am the first user of 480', '2014/05/30 23:53:11');
INSERT INTO `Post` VALUES(128, 'conheo', 'CS 128', 'im the second ', '2014/05/30 23:59:23');
INSERT INTO `Post` VALUES(152, 'tester', 'CS 301', 'yay cs 301', '2014/06/02 08:04:05');
INSERT INTO `Post` VALUES(149, 'user', 'CS 301', 'I added cs301!', '2014/06/02 07:49:34');
INSERT INTO `Post` VALUES(148, 'user', 'CS 356', 'hi there cs 356', '2014/06/02 07:49:02');
INSERT INTO `Post` VALUES(153, 'vivan', 'CS 128', 'hello.im testing this', '2014/06/02 13:17:11');
INSERT INTO `Post` VALUES(154, 'hellothere', 'CS 356', 'postt', '2014/06/02 14:10:27');
