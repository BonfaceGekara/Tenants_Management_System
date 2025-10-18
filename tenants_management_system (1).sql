-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2024 at 07:52 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tenants_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `apartments`
--

CREATE TABLE `apartments` (
  `apartment_no` varchar(50) NOT NULL,
  `apartment_name` varchar(50) NOT NULL,
  `landlord_id` int(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `number_of_rooms` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `apartments`
--

INSERT INTO `apartments` (`apartment_no`, `apartment_name`, `landlord_id`, `address`, `number_of_rooms`) VALUES
('A/1/1', 'Taraja', 1, 'Gash', 10),
('A/1/2', 'Dam View', 1, 'Gash', 20),
('A/2/1', 'benson', 2, 'Thika road juja', 12),
('A/4/1', 'Dam View', 4, 'JR-10 , Jamal Road, Juja ,Kenya', 12);

-- --------------------------------------------------------

--
-- Table structure for table `landlords`
--

CREATE TABLE `landlords` (
  `landlord_id` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `landlords`
--

INSERT INTO `landlords` (`landlord_id`, `full_name`, `phone_number`, `email`, `password`) VALUES
(1, 'LandLord One', '0723456789', 'landlord.one@gmail.com', '.one'),
(2, 'Landlord Two', '0712345679', 'landlord.two@gmail.com', '.two'),
(3, 'Landlord Three', '0112323232', 'landlord.three@gmail.com', '.three'),
(4, 'Bonface Morara', '0113390198', 'bonface@gmail.com', '.one');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `room_no` varchar(50) NOT NULL,
  `apartment_no` varchar(50) NOT NULL,
  `landlord_id` int(11) NOT NULL,
  `room_type` varchar(50) NOT NULL,
  `room_status` varchar(50) NOT NULL,
  `room_price` varchar(20) NOT NULL,
  `paid` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `room_no`, `apartment_no`, `landlord_id`, `room_type`, `room_status`, `room_price`, `paid`) VALUES
(63, 'R/4/1', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(64, 'R/4/2', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(65, 'R/4/3', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(66, 'R/4/4', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(67, 'R/4/5', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(68, 'R/4/6', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(69, 'R/4/7', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(70, 'R/4/8', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(71, 'R/4/9', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(72, 'R/4/10', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(73, 'R/4/11', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(74, 'R/4/12', 'A/4/1', 4, 'Two Bedroom', 'available', '14500', NULL),
(75, 'R/2/1', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(76, 'R/2/2', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(77, 'R/2/3', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(78, 'R/2/4', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(79, 'R/2/5', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(80, 'R/2/6', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(81, 'R/2/7', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(82, 'R/2/8', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(83, 'R/2/9', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(84, 'R/2/10', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(85, 'R/2/11', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(86, 'R/2/12', 'A/2/1', 2, 'Single', 'available', '3500', NULL),
(1087, 'R/1/1', 'A/1/1', 1, 'Single', 'occupied', '5400', NULL),
(1088, 'R/1/2', 'A/1/1', 1, 'Single', 'occupied', '5400', NULL),
(1089, 'R/1/3', 'A/1/1', 1, 'Single', 'available', '5400', NULL),
(1090, 'R/1/4', 'A/1/1', 1, 'Single', 'available', '5400', NULL),
(1091, 'R/1/5', 'A/1/1', 1, 'Single', 'available', '5400', NULL),
(1092, 'R/1/6', 'A/1/1', 1, 'Single', 'available', '5400', NULL),
(1093, 'R/1/7', 'A/1/1', 1, 'Single', 'available', '5400', NULL),
(1094, 'R/1/8', 'A/1/1', 1, 'Single', 'available', '5400', NULL),
(1095, 'R/1/9', 'A/1/1', 1, 'Single', 'available', '5400', NULL),
(1096, 'R/1/10', 'A/1/1', 1, 'Single', 'available', '5400', NULL),
(1097, 'R/1/1', 'A/1/2', 1, 'Single', 'occupied', '3700', NULL),
(1098, 'R/1/2', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1099, 'R/1/3', 'A/1/2', 1, 'Single', 'occupied', '3700', 'Yes'),
(1100, 'R/1/4', 'A/1/2', 1, 'Single', 'occupied', '3700', 'Yes'),
(1101, 'R/1/5', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1102, 'R/1/6', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1103, 'R/1/7', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1104, 'R/1/8', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1105, 'R/1/9', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1106, 'R/1/10', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1107, 'R/1/11', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1108, 'R/1/12', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1109, 'R/1/13', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1110, 'R/1/14', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1111, 'R/1/15', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1112, 'R/1/16', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1113, 'R/1/17', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1114, 'R/1/18', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1115, 'R/1/19', 'A/1/2', 1, 'Single', 'available', '3700', NULL),
(1116, 'R/1/20', 'A/1/2', 1, 'Single', 'available', '3700', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tenants`
--

CREATE TABLE `tenants` (
  `tenant_no` varchar(50) NOT NULL,
  `landlord_id` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `phone_number` varchar(21) NOT NULL,
  `email` varchar(50) NOT NULL,
  `id_card_number` varchar(11) NOT NULL,
  `rent_due_date` varchar(30) DEFAULT NULL,
  `room_no` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tenants`
--

INSERT INTO `tenants` (`tenant_no`, `landlord_id`, `full_name`, `phone_number`, `email`, `id_card_number`, `rent_due_date`, `room_no`) VALUES
('T/1/1', 1, 'Bonface Morara', '0701234567', 'bonface.morara@gmail.com', '', '0000-00-00', 'R/1/1'),
('T/1/2', 1, 'Tenant Two', '0701234568', 'tenant.two@gmail.com', '', 'Mon Dec 23 21:19:57 EAT 2024', 'R/1/1'),
('T/1/3', 1, 'Justine Morara', '0701234569', 'justo.morara@gmail.com', '23456780', 'Tue Dec 03 21:34:36 EAT 2024', 'R/1/1'),
('T/1/4', 1, 'Joseph Muiri', '0723456287', 'joseph.muiri@gmail.com', '23456781', 'Wed Jan 01 21:41:51 EAT 2025', 'R/1/3'),
('T/1/5', 1, 'Titus Ken', '0746873728', 'titus.ken123@gmail.com', '23456782', 'Tue Dec 03 21:34:36 EAT 2024', 'R/1/2'),
('T/1/6', 1, 'Bonface Morara', '0113390198', 'bon.g@gmail.com', '21343444', 'Tue Dec 31 21:45:50 EAT 2024', 'R/1/4'),
('T/2/1', 2, 'Stanley Omondi', '0743317264', 'stan.ui@yahoo.com', '424351671', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apartments`
--
ALTER TABLE `apartments`
  ADD PRIMARY KEY (`apartment_no`),
  ADD KEY `landlord_id` (`landlord_id`);

--
-- Indexes for table `landlords`
--
ALTER TABLE `landlords`
  ADD PRIMARY KEY (`landlord_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`),
  ADD KEY `landlord_id` (`landlord_id`),
  ADD KEY `apartment_no` (`apartment_no`);

--
-- Indexes for table `tenants`
--
ALTER TABLE `tenants`
  ADD PRIMARY KEY (`tenant_no`),
  ADD KEY `landlord_id` (`landlord_id`),
  ADD KEY `room_no` (`room_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `landlords`
--
ALTER TABLE `landlords`
  MODIFY `landlord_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1117;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `apartments`
--
ALTER TABLE `apartments`
  ADD CONSTRAINT `apartments_ibfk_1` FOREIGN KEY (`landlord_id`) REFERENCES `landlords` (`landlord_id`);

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`landlord_id`) REFERENCES `landlords` (`landlord_id`),
  ADD CONSTRAINT `rooms_ibfk_2` FOREIGN KEY (`apartment_no`) REFERENCES `apartments` (`apartment_no`);

--
-- Constraints for table `tenants`
--
ALTER TABLE `tenants`
  ADD CONSTRAINT `tenants_ibfk_1` FOREIGN KEY (`landlord_id`) REFERENCES `landlords` (`landlord_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
