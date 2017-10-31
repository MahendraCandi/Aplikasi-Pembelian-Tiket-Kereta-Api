-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 31, 2017 at 12:56 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bokingkereta`
--

-- --------------------------------------------------------

--
-- Table structure for table `detailtransaksi`
--

CREATE TABLE IF NOT EXISTS `detailtransaksi` (
  `Id` int(11) NOT NULL,
  `NoTransaksi` varchar(6) NOT NULL,
  `KodeKereta` varchar(10) NOT NULL,
  `NamaKereta` varchar(40) DEFAULT NULL,
  `KotaAsal` varchar(30) DEFAULT NULL,
  `StasiunAsal` varchar(30) DEFAULT NULL,
  `KotaTujuan` varchar(30) DEFAULT NULL,
  `StasiunTujuan` varchar(30) DEFAULT NULL,
  `Kelas` varchar(10) DEFAULT NULL,
  `Subkelas` varchar(5) DEFAULT NULL,
  `TanggalBerangkat` date DEFAULT NULL,
  `Berangkat` time DEFAULT NULL,
  `Harga` double DEFAULT NULL,
  `JumlahPenumpang` int(11) DEFAULT NULL,
  `Status` varchar(15) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detailtransaksi`
--

INSERT INTO `detailtransaksi` (`Id`, `NoTransaksi`, `KodeKereta`, `NamaKereta`, `KotaAsal`, `StasiunAsal`, `KotaTujuan`, `StasiunTujuan`, `Kelas`, `Subkelas`, `TanggalBerangkat`, `Berangkat`, `Harga`, `JumlahPenumpang`, `Status`) VALUES
(18, 'TR-001', 'KRT-006', 'MELAJU CEPAT', 'Jakarta', 'Gambir', 'Yogyakarta', 'Brambanan', 'Eksekutif', 'A', '2017-06-01', '10:00:00', 120000, 1, 'Sudah bayar'),
(19, 'TR-002', 'KRT-002', 'CICING ULA GANDENG', 'Jakarta', 'Gambir', 'Bandung', 'Cimahi', 'Ekonomi', 'C', '2017-05-30', '11:00:00', 90000, 3, 'Belum bayar'),
(20, 'TR-003', 'KRT-003', 'ODONG EKSPRESS', 'Jakarta', 'Gambir', 'Bandung', 'Cikadongdong', 'Ekonomi', 'C', '2017-05-28', '09:09:00', 90000, 2, 'Belum bayar'),
(21, 'TR-004', 'KRT-003', 'ODONG EKSPRESS', 'Jakarta', 'Gambir', 'Bandung', 'Cikadongdong', 'Ekonomi', 'Q', '2017-05-28', '09:09:00', 50000, 2, 'Sudah bayar'),
(22, 'TR-005', 'KRT-002', 'CICING ULA GANDENG', 'Jakarta', 'Gambir', 'Bandung', 'Cimahi', 'Eksekutif', 'A', '2017-06-04', '11:00:00', 120000, 2, 'Sudah bayar'),
(23, 'TR-006', 'KRT-001', 'NGEBUT EKSPRESS', 'Jakarta', 'Gambir', 'Yogyakarta', 'Yogyakarta', 'Eksekutif', 'J', '2017-05-29', '10:00:00', 120000, 2, 'Sudah bayar'),
(24, 'TR-007', 'KRT-007', 'BERANGKAT', 'Jakarta', 'Manggari', 'Surabaya', 'Surabaya Pasar Turi', 'Eksekutif', 'A', '2017-06-22', '10:00:00', 120000, 2, 'Belum bayar'),
(25, 'TR-008', 'KRT-002', 'CICING ULA GANDENG', 'Jakarta', 'Gambir', 'Bandung', 'Cimahi', 'Eksekutif', 'A', '2017-05-31', '11:00:00', 120000, 3, 'Belum bayar'),
(26, 'TR-009', 'KRT-001', 'NGEBUT EKSPRESS', 'Jakarta', 'Gambir', 'Yogyakarta', 'Yogyakarta', 'Eksekutif', 'H', '2017-06-07', '10:00:00', 120000, 1, 'Sudah bayar');

-- --------------------------------------------------------

--
-- Table structure for table `kereta`
--

CREATE TABLE IF NOT EXISTS `kereta` (
  `Id` int(11) NOT NULL,
  `KodeKereta` varchar(10) DEFAULT NULL,
  `KotaAsal` varchar(30) DEFAULT NULL,
  `StasiunAsal` varchar(30) DEFAULT NULL,
  `KotaTujuan` varchar(30) DEFAULT NULL,
  `StasiunTujuan` varchar(30) DEFAULT NULL,
  `NamaKereta` varchar(40) DEFAULT NULL,
  `Berangkat` time DEFAULT NULL,
  `Tiba` time DEFAULT NULL,
  `Kelas` varchar(10) DEFAULT NULL,
  `SubKelas` varchar(5) DEFAULT NULL,
  `Harga` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kereta`
--

INSERT INTO `kereta` (`Id`, `KodeKereta`, `KotaAsal`, `StasiunAsal`, `KotaTujuan`, `StasiunTujuan`, `NamaKereta`, `Berangkat`, `Tiba`, `Kelas`, `SubKelas`, `Harga`) VALUES
(71, 'KRT-001', 'Jakarta', 'Gambir', 'Yogyakarta', 'Yogyakarta', 'NGEBUT EKSPRESS', '10:00:00', '18:00:00', 'Eksekutif', 'A', 140000),
(72, 'KRT-001', 'Jakarta', 'Gambir', 'Yogyakarta', 'Yogyakarta', 'NGEBUT EKSPRESS', '10:00:00', '18:00:00', 'Eksekutif', 'H', 120000),
(73, 'KRT-001', 'Jakarta', 'Gambir', 'Yogyakarta', 'Yogyakarta', 'NGEBUT EKSPRESS', '10:00:00', '18:00:00', 'Eksekutif', 'I', 120000),
(74, 'KRT-001', 'Jakarta', 'Gambir', 'Yogyakarta', 'Yogyakarta', 'NGEBUT EKSPRESS', '10:00:00', '18:00:00', 'Eksekutif', 'J', 120000),
(75, 'KRT-002', 'Jakarta', 'Gambir', 'Bandung', 'Cimahi', 'CICING ULA GANDENG', '11:00:00', '17:00:00', 'Eksekutif', 'A', 120000),
(76, 'KRT-002', 'Jakarta', 'Gambir', 'Bandung', 'Cimahi', 'CICING ULA GANDENG', '11:00:00', '17:00:00', 'Eksekutif', 'H', 120000),
(77, 'KRT-002', 'Jakarta', 'Gambir', 'Bandung', 'Cimahi', 'CICING ULA GANDENG', '11:00:00', '17:00:00', 'Eksekutif', 'I', 120000),
(78, 'KRT-002', 'Jakarta', 'Gambir', 'Bandung', 'Cimahi', 'CICING ULA GANDENG', '11:00:00', '17:00:00', 'Ekonomi', 'C', 90000),
(79, 'KRT-003', 'Jakarta', 'Gambir', 'Bandung', 'Cikadongdong', 'ODONG EKSPRESS', '09:09:00', '10:00:00', 'Ekonomi', 'C', 90000),
(80, 'KRT-003', 'Jakarta', 'Gambir', 'Bandung', 'Cikadongdong', 'ODONG EKSPRESS', '09:09:00', '10:00:00', 'Ekonomi', 'P', 70000),
(81, 'KRT-003', 'Jakarta', 'Gambir', 'Bandung', 'Cikadongdong', 'ODONG EKSPRESS', '09:09:00', '10:00:00', 'Ekonomi', 'Q', 50000),
(82, 'KRT-004', 'Jakarta', 'Tanah Abang', 'Jakarta', 'Gambir', 'DEKAT EKSPRESS', '09:00:00', '09:45:00', 'Ekonomi', 'C', 12000),
(83, 'KRT-004', 'Jakarta', 'Tanah Abang', 'Jakarta', 'Gambir', 'DEKAT EKSPRESS', '09:00:00', '09:45:00', 'Ekonomi', 'P', 12000),
(84, 'KRT-004', 'Jakarta', 'Tanah Abang', 'Jakarta', 'Gambir', 'DEKAT EKSPRESS', '09:00:00', '09:45:00', 'Ekonomi', 'Q', 12000),
(85, 'KRT-004', 'Jakarta', 'Tanah Abang', 'Jakarta', 'Gambir', 'DEKAT EKSPRESS', '09:00:00', '09:45:00', 'Ekonomi', 'S', 12000),
(87, 'KRT-004', 'Jakarta', 'Tanah Abang', 'Jakarta', 'Gambir', 'DEKAT EKSPRESS', '10:00:00', '10:45:00', 'Ekonomi', 'C', 12000),
(88, 'KRT-004', 'Jakarta', 'Tanah Abang', 'Jakarta', 'Gambir', 'DEKAT EKSPRESS', '10:00:00', '10:45:00', 'Ekonomi', 'P', 12000),
(89, 'KRT-004', 'Jakarta', 'Tanah Abang', 'Jakarta', 'Gambir', 'DEKAT EKSPRESS', '10:00:00', '10:45:00', 'Ekonomi', 'Q', 12000),
(90, 'KRT-004', 'Jakarta', 'Tanah Abang', 'Jakarta', 'Gambir', 'DEKAT EKSPRESS', '10:00:00', '10:45:00', 'Ekonomi', 'S', 12000),
(91, 'KRT-005', 'Jakarta', 'Gambir', 'Bandung', 'Bandung', 'GERILYA KENCANA', '08:00:00', '10:00:00', 'Eksekutif', 'A', 80000),
(92, 'KRT-005', 'Jakarta', 'Gambir', 'Bandung', 'Bandung', 'GERILYA KENCANA', '08:00:00', '10:00:00', 'Eksekutif', 'H', 80000),
(93, 'KRT-005', 'Jakarta', 'Gambir', 'Bandung', 'Bandung', 'GERILYA KENCANA', '08:00:00', '10:00:00', 'Eksekutif', 'I', 80000),
(97, 'KRT-006', 'Jakarta', 'Gambir', 'Yogyakarta', 'Brambanan', 'MELAJU CEPAT', '10:00:00', '15:00:00', 'Eksekutif', 'A', 120000),
(98, 'KRT-006', 'Jakarta', 'Gambir', 'Yogyakarta', 'Brambanan', 'MELAJU CEPAT', '10:00:00', '15:00:00', 'Eksekutif', 'H', 120000),
(99, 'KRT-006', 'Jakarta', 'Gambir', 'Yogyakarta', 'Brambanan', 'MELAJU CEPAT', '10:00:00', '15:00:00', 'Eksekutif', 'I', 120000),
(100, 'KRT-006', 'Jakarta', 'Gambir', 'Yogyakarta', 'Brambanan', 'MELAJU CEPAT', '10:00:00', '15:00:00', 'Eksekutif', 'J', 120000),
(101, 'KRT-007', 'Jakarta', 'Manggari', 'Surabaya', 'Surabaya Pasar Turi', 'BERANGKAT', '10:00:00', '17:00:00', 'Eksekutif', 'A', 120000),
(102, 'KRT-007', 'Jakarta', 'Manggari', 'Surabaya', 'Surabaya Pasar Turi', 'BERANGKAT', '10:00:00', '17:00:00', 'Eksekutif', 'H', 110000),
(103, 'KRT-007', 'Jakarta', 'Manggari', 'Surabaya', 'Surabaya Pasar Turi', 'BERANGKAT rrrr', '10:00:00', '17:00:00', 'Eksekutif', 'I', 100000),
(104, 'KRT-008', 'Jakarta', 'Gambir', 'Yogyakarta', 'Lempuyangan', 'ekpress', '09:00:00', '13:00:00', 'Eksekutif', 'A', 100000),
(105, 'KRT-008', 'Jakarta', 'Gambir', 'Yogyakarta', 'Lempuyangan', 'ekpress', '09:00:00', '13:00:00', 'Eksekutif', 'H', 90000),
(106, 'KRT-008', 'Jakarta', 'Gambir', 'Yogyakarta', 'Lempuyangan', 'ekpress oooo', '09:00:00', '13:00:00', 'Eksekutif', 'I', 80000);

-- --------------------------------------------------------

--
-- Table structure for table `penumpang`
--

CREATE TABLE IF NOT EXISTS `penumpang` (
  `Id` int(11) NOT NULL,
  `NoTransaksi` varchar(6) NOT NULL,
  `NamaPenumpang` varchar(30) DEFAULT NULL,
  `NoKtp` varchar(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penumpang`
--

INSERT INTO `penumpang` (`Id`, `NoTransaksi`, `NamaPenumpang`, `NoKtp`) VALUES
(42, 'TR-001', '12144477', 'Mamad'),
(43, 'TR-002', '12144489', 'Boboboy'),
(44, 'TR-002', '12145690', 'Upin'),
(45, 'TR-002', '12143221', 'Ipin'),
(46, 'TR-003', '12140897', 'Doraemon'),
(47, 'TR-003', '121464532', 'Nobita'),
(48, 'TR-004', '121433321', 'Saprol'),
(49, 'TR-004', '121432231', 'Kibli'),
(50, 'TR-005', '123456', 'asdfgh'),
(51, 'TR-005', '234567', 'sdfghjk'),
(52, 'TR-006', '12144455', 'Ahmad'),
(53, 'TR-006', '1233417', 'ade'),
(54, 'TR-007', '12145101', 'ahmad sutiar'),
(55, 'TR-007', '121451111', 'Abduhu'),
(56, 'TR-008', 'qwer', 'citra'),
(57, 'TR-008', '1234', 'indo'),
(58, 'TR-008', '1234', 'citra 2'),
(59, 'TR-009', '34567890', 'wertyui');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `NoTransaksi` varchar(6) NOT NULL,
  `Tanggal` date DEFAULT NULL,
  `Total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`NoTransaksi`, `Tanggal`, `Total`) VALUES
('TR-001', '2017-05-28', 120000),
('TR-002', '2017-05-28', 270000),
('TR-003', '2017-05-28', 180000),
('TR-004', '2017-05-28', 100000),
('TR-005', '2017-05-29', 240000),
('TR-006', '2017-05-29', 240000),
('TR-007', '2017-05-29', 240000),
('TR-008', '2017-05-31', 360000),
('TR-009', '2017-06-07', 120000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `KodeUser` varchar(10) NOT NULL,
  `NamaUser` varchar(30) NOT NULL,
  `Password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`KodeUser`, `NamaUser`, `Password`) VALUES
('USR-001', 'Abdu', '12144455'),
('USR-002', 'Dewi', '12144454'),
('USR-003', 'Tiar', '12144453'),
('USR-004', 'Azwar', '12144452'),
('USR-005', 'Ade', '12144451'),
('usr002', 'ahmad', '12345');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detailtransaksi`
--
ALTER TABLE `detailtransaksi`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `kereta`
--
ALTER TABLE `kereta`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `penumpang`
--
ALTER TABLE `penumpang`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`NoTransaksi`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`KodeUser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detailtransaksi`
--
ALTER TABLE `detailtransaksi`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `kereta`
--
ALTER TABLE `kereta`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=107;
--
-- AUTO_INCREMENT for table `penumpang`
--
ALTER TABLE `penumpang`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=60;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
