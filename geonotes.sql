-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 04, 2013 at 08:07 PM
-- Server version: 5.5.24-log
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `geonotes`
--

-- --------------------------------------------------------

--
-- Table structure for table `note`
--

CREATE TABLE IF NOT EXISTS `note` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `date` varchar(20) NOT NULL,
  `url_photo` varchar(256) DEFAULT NULL,
  `visible` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=97 ;

--
-- Dumping data for table `note`
--

INSERT INTO `note` (`id`, `nom`, `latitude`, `longitude`, `description`, `date`, `url_photo`, `visible`) VALUES
(91, 'Stade G-G', '45.46081757493149', '4.390094280388439', 'Stade Geoffroy Guichard', 'ven.01 févr. 2013', NULL, 0),
(93, 'Université Jean Monnet, Saint-Etienne', '45.42631', '4.390989999999988', 'Université Jean Monnet, Saint-Etienne', 'ven.01 févr. 2013', NULL, 1),
(94, 'Stade de France', '48.92551', '2.357240000000047', 'Stade de France', 'ven.01 févr. 2013', NULL, 1),
(95, 'Tour eiffel', '48.85784', '2.29525000000001', 'Tour eiffel', 'ven.01 févr. 2013', NULL, 1),
(96, 'Paris Bercy', '48.83643000000001', '2.383919999999989', 'Paris Bercy', 'ven.01 févr. 2013', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `note_parcours`
--

CREATE TABLE IF NOT EXISTS `note_parcours` (
  `refnote` int(10) NOT NULL,
  `refparcours` int(10) NOT NULL,
  `ordre` int(5) NOT NULL,
  PRIMARY KEY (`refnote`,`refparcours`),
  KEY `refparcours` (`refparcours`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `note_parcours`
--

INSERT INTO `note_parcours` (`refnote`, `refparcours`, `ordre`) VALUES
(94, 50, 1),
(95, 50, 2),
(96, 50, 3);

-- --------------------------------------------------------

--
-- Table structure for table `parcours`
--

CREATE TABLE IF NOT EXISTS `parcours` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(64) NOT NULL,
  `distance` double NOT NULL,
  `temps` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;

--
-- Dumping data for table `parcours`
--

INSERT INTO `parcours` (`id`, `nom`, `distance`, `temps`) VALUES
(50, 'Itinéraire avec étape :)', 14.012, 1259);

-- --------------------------------------------------------

--
-- Table structure for table `parcours_client`
--

CREATE TABLE IF NOT EXISTS `parcours_client` (
  `refparcours` int(10) NOT NULL,
  `refclient` int(10) NOT NULL,
  `tpsrealise` int(10) NOT NULL,
  PRIMARY KEY (`refparcours`,`refclient`),
  KEY `refclient` (`refclient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `mail` varchar(64) NOT NULL,
  `mdp` varchar(64) NOT NULL,
  `nom` varchar(64) NOT NULL,
  `prenom` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `mail`, `mdp`, `nom`, `prenom`) VALUES
(1, 'boussetta_islem@yahoo.fr', 'aaaaaa', 'BOUSSETTA', 'Islem'),
(2, 'philnet09@free.fr', 'bbbbbb', 'ZDZIOBECK', 'Philippe');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `note_parcours`
--
ALTER TABLE `note_parcours`
  ADD CONSTRAINT `note_parcours_ibfk_1` FOREIGN KEY (`refnote`) REFERENCES `note` (`id`),
  ADD CONSTRAINT `note_parcours_ibfk_2` FOREIGN KEY (`refparcours`) REFERENCES `parcours` (`id`);

--
-- Constraints for table `parcours_client`
--
ALTER TABLE `parcours_client`
  ADD CONSTRAINT `parcours_client_ibfk_1` FOREIGN KEY (`refparcours`) REFERENCES `parcours` (`id`),
  ADD CONSTRAINT `parcours_client_ibfk_2` FOREIGN KEY (`refclient`) REFERENCES `utilisateur` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
