-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2024 at 02:32 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 7.2.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ads_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `advertises`
--

CREATE TABLE `advertises` (
  `id` int(11) UNSIGNED NOT NULL,
  `licensing` tinyint(1) DEFAULT NULL,
  `height` float DEFAULT NULL,
  `width` float DEFAULT NULL,
  `images` text DEFAULT NULL,
  `pillar_quantity` int(11) DEFAULT NULL,
  `location_id` int(11) UNSIGNED NOT NULL,
  `ads_type_id` int(11) UNSIGNED NOT NULL,
  `status_edit` tinyint(1) DEFAULT NULL,
  `advertise_edit_id` int(11) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `advertises`
--

INSERT INTO `advertises` (`id`, `licensing`, `height`, `width`, `images`, `pillar_quantity`, `location_id`, `ads_type_id`, `status_edit`, `advertise_edit_id`, `created_at`, `updated_at`) VALUES
(1, 0, 15, 2.5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 1, 1, 1, NULL, NULL, '2023-12-19 08:05:18', '2023-12-19 08:05:18'),
(2, 0, 12, 2.8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 1, 2, NULL, NULL, '2023-12-19 08:05:18', '2023-12-19 08:05:18'),
(3, 0, 14, 2.2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 3, 2, 3, NULL, NULL, '2023-12-19 08:05:18', '2023-12-19 08:05:18'),
(4, 0, 10, 2.6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 3, 4, NULL, NULL, '2023-12-19 08:05:18', '2023-12-19 08:05:18'),
(5, 0, 13, 2.9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 1, 4, 5, NULL, NULL, '2023-12-19 08:05:18', '2023-12-19 08:05:18'),
(6, 0, 11, 2.4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 5, 6, NULL, NULL, '2023-12-19 08:05:18', '2023-12-19 08:05:18'),
(7, 0, 12, 2.7, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 2, 6, 7, NULL, NULL, '2023-12-19 08:05:29', '2023-12-19 08:05:29'),
(8, 0, 14, 2.2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 2, 7, 8, NULL, NULL, '2023-12-19 08:05:29', '2023-12-19 08:05:29'),
(9, 0, 10, 2.6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 8, 9, NULL, NULL, '2023-12-19 08:05:29', '2023-12-19 08:05:29'),
(10, 0, 13, 2.9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 1, 9, 10, NULL, NULL, '2023-12-19 08:05:29', '2023-12-19 08:05:29'),
(11, 0, 11, 2.4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 10, 1, NULL, NULL, '2023-12-19 08:05:29', '2023-12-19 08:05:29'),
(12, 0, 12, 2.7, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 3, 10, 2, NULL, NULL, '2023-12-19 08:05:29', '2023-12-19 08:05:29'),
(13, 0, 10, 2.8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 11, 3, NULL, NULL, '2023-12-19 08:05:29', '2023-12-19 08:05:29'),
(14, 0, 13, 2.9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 1, 12, 4, NULL, NULL, '2023-12-19 08:05:29', '2023-12-19 08:05:29'),
(15, 0, 14, 2.2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 2, 13, 5, NULL, NULL, '2023-12-19 08:05:29', '2023-12-19 08:05:29'),
(16, 0, 10, 2.6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 14, 6, NULL, NULL, '2023-12-19 08:05:29', '2023-12-19 08:05:29'),
(17, 0, 13, 2.9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 1, 15, 7, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(18, 0, 11, 2.4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 16, 8, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(19, 0, 12, 2.7, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 3, 17, 9, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(20, 0, 10, 2.8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 18, 10, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(21, 0, 13, 2.9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 1, 19, 1, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(23, 0, 14, 2.2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 2, 20, 2, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(24, 0, 10, 2.6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 21, 3, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(25, 0, 13, 2.9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 1, 22, 4, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(26, 0, 11, 2.4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 23, 5, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(27, 0, 12, 2.7, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 3, 24, 6, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(28, 0, 10, 2.8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', NULL, 25, 7, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35'),
(29, 0, 13, 2.9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmq1k95wJA5bP_jYuAeEomw-NanJEBmTULTA&usqp=CAU', 1, 27, 8, NULL, NULL, '2023-12-19 08:05:35', '2023-12-19 08:05:35');

-- --------------------------------------------------------

--
-- Table structure for table `advertises_edit`
--

CREATE TABLE `advertises_edit` (
  `id` int(11) UNSIGNED NOT NULL,
  `licensing` int(11) DEFAULT NULL,
  `height` float DEFAULT NULL,
  `width` float DEFAULT NULL,
  `pillar_quantity` int(11) DEFAULT NULL,
  `content` text NOT NULL,
  `location_id` int(11) UNSIGNED NOT NULL,
  `ads_type_id` int(11) UNSIGNED NOT NULL,
  `user_id` int(11) UNSIGNED NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `images` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `advertise_forms`
--

CREATE TABLE `advertise_forms` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `advertise_forms`
--

INSERT INTO `advertise_forms` (`id`, `name`, `description`, `created_at`) VALUES
(1, 'Cổ động chính trị', 'mô tả Cổ động chính trị', '2023-12-17 07:58:14'),
(2, 'Quảng cáo thương mại', 'mô tả Quảng cáo thương mại', '2023-12-17 07:58:14'),
(3, 'Xã hội hoá', 'mô tả Xã hội hoá', '2023-12-17 07:58:14');

-- --------------------------------------------------------

--
-- Table structure for table `advertise_types`
--

CREATE TABLE `advertise_types` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `advertise_types`
--

INSERT INTO `advertise_types` (`id`, `name`, `description`, `created_at`) VALUES
(1, 'Trụ bảng hiflex', 'Mô tả Trụ bảng hiflex', '2023-12-17 07:55:56'),
(2, 'Trụ màn hình điện tử LED', 'Mô tả Trụ màn hình điện tử LED', '2023-12-17 07:55:56'),
(3, 'Trụ hộp đèn', 'Mô tả Trụ hộp đèn', '2023-12-17 07:55:56'),
(4, 'Bảng hiflex ốp tường', 'Mô tả Bảng hiflex ốp tường', '2023-12-17 07:55:56'),
(5, 'Màn hình điện tử ốp tường', 'Mô tả Màn hình điện tử ốp tường', '2023-12-17 07:55:56'),
(6, 'Trụ treo băng rôn dọc', 'Mô tả Trụ treo băng rôn dọc', '2023-12-17 07:55:56'),
(7, 'Trụ treo băng rôn ngang', 'Mô tả Trụ treo băng rôn ngang', '2023-12-17 07:55:56'),
(8, 'Trụ/Cụm pano', 'Mô tả Trụ/Cụm pano', '2023-12-17 07:55:56'),
(9, 'Cổng chào', 'Mô tả Cổng chào', '2023-12-17 07:55:56'),
(10, 'Trung tâm thương mại', 'Mô tả Trung tâm thương mại', '2023-12-17 07:55:56');

-- --------------------------------------------------------

--
-- Table structure for table `contracts`
--

CREATE TABLE `contracts` (
  `id` int(10) UNSIGNED NOT NULL,
  `company_name` varchar(50) DEFAULT NULL,
  `company_email` varchar(50) DEFAULT NULL,
  `company_phone` varchar(50) DEFAULT NULL,
  `company_address` varchar(200) DEFAULT NULL,
  `start_at` datetime(6) DEFAULT NULL,
  `end_at` datetime(6) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '  licensed = 1,\r\n  notLicensed = 2,\r\n  expired = 3,\r\n  rejected = 4',
  `ads_id` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `images` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `contracts`
--

INSERT INTO `contracts` (`id`, `company_name`, `company_email`, `company_phone`, `company_address`, `start_at`, `end_at`, `status`, `ads_id`, `created_at`, `images`) VALUES
(1, 'Công ty TNHH ABC', 'Đường 1, Phường 1, Quận 1, TP.HCM', '0123456789', 'ctyst@gmail.com', '2023-12-25 00:00:00.000000', '2024-12-25 00:00:00.000000', 1, 2, '2023-12-21 04:04:05', 'https://res.cloudinary.com/dacvpgdfi/image/upload/v1702980254/gtl8wi5atyzx3sp49huk.jpg'),
(2, 'Công ty TNHH ABC', 'Đường 1, Phường 1, Quận 1, TP.HCM', '0123456789', 'ctyst@gmail.com', '2023-12-25 00:00:00.000000', '2024-12-25 00:00:00.000000', 2, 2, '2023-12-21 04:04:05', 'https://res.cloudinary.com/dacvpgdfi/image/upload/v1702980254/gtl8wi5atyzx3sp49huk.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE `locations` (
  `id` int(11) UNSIGNED NOT NULL,
  `planning` tinyint(1) DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `images` text DEFAULT NULL,
  `status_edit` bit(1) DEFAULT NULL,
  `ads_form_id` int(11) UNSIGNED NOT NULL,
  `location_type_id` int(11) UNSIGNED NOT NULL,
  `property_id` int(11) UNSIGNED NOT NULL,
  `location_edit_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`id`, `planning`, `longitude`, `latitude`, `address`, `images`, `status_edit`, `ads_form_id`, `location_type_id`, `property_id`, `location_edit_id`, `created_at`, `updated_at`) VALUES
(1, 1, 106.682, 10.7647, '239 Đ. Nguyễn Văn Cừ, Phường 4, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 2, 19, NULL, '2023-12-18 13:49:44', '2023-12-18 13:49:44'),
(2, 1, 106.682, 10.7652, '1 Đ. Phạm Viết Chánh, Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 3, 4, 3, NULL, '2023-12-18 13:49:44', '2023-12-18 13:49:44'),
(3, 1, 106.682, 10.7659, '64-59 Đ. Phạm Viết Chánh, Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 2, 6, 3, NULL, '2023-12-18 13:49:44', '2023-12-18 13:49:44'),
(4, 1, 106.683, 10.7665, '337-335 Đ. Phạm Viết Chánh, Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 3, 1, 3, NULL, '2023-12-18 13:49:44', '2023-12-18 13:49:44'),
(5, 1, 106.681, 10.7649, '2-6 Đ. Trần Phú, Phường 4, Quận 5, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 2, 4, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(6, 1, 106.681, 10.7649, '11-1 Đ. Hùng Vương, Phường 4, Quận 5, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 2, 3, 4, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(7, 1, 106.68, 10.7649, '20 Đ. Hùng Vương, Phường 1, Quận 10, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 3, 4, 6, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(8, 1, 106.681, 10.7657, '28 Đ. Lý Thái Tổ, Phường 2, Quận 3, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 5, 9, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(9, 1, 106.68, 10.7659, '58 Đ. Lý Thái Tổ, Phường 1, Quận 10, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 2, 6, 6, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(10, 1, 106.683, 10.7611, '280 Đ. An Dương Vương, Phường 3, Quận 5, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 3, 1, 5, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(11, 1, 106.682, 10.7605, '273 Đ. An Dương Vương, Phường 3, Quận 5, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 2, 5, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(12, 1, 106.683, 10.7608, '271 Đ. An Dương Vương, Phường 3, Quận 5, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 3, 4, 5, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(13, 1, 106.683, 10.7606, '213a Đ. Nguyễn Văn Cừ, Phường 3, Quận 5, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 5, 5, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(14, 1, 106.683, 10.7632, '235 Đ. Nguyễn Văn Cừ, Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 2, 6, 3, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(15, 1, 106.682, 10.7655, '63/4/52 Đ. Cống Quỳnh, Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 3, 1, 3, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(16, 1, 106.671, 10.7697, 'Đ. 3 Tháng 2, Phường 10, Quận 10, Thành phố Hồ Chí Minh 70040, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 2, 7, NULL, '2023-12-18 13:50:06', '2023-12-18 13:50:06'),
(17, 1, 106.672, 10.7704, '300 Đ. 3 Tháng 2, Phường 12, Quận 10, Thành phố Hồ Chí Minh 700000, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 2, 3, 11, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(18, 1, 106.674, 10.7694, '609 Đ. Lê Hồng Phong, Phường 10, Quận 10, Thành phố Hồ Chí Minh 700000, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 3, 4, 7, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(19, 1, 106.674, 10.7683, '535 Đ. Lê Hồng Phong, Phường 10, Quận 10, Thành phố Hồ Chí Minh 76000, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 5, 7, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(20, 1, 106.669, 10.7679, '407/6 Đ. Lý Thái Tổ, Phường 9, Quận 10, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 2, 6, 10, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(21, 1, 106.675, 10.7682, '665-667-669 Đ. Điện Biên Phủ, Phường 1, Quận 3, Thành phố Hồ Chí Minh 700000, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 3, 1, 8, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(22, 1, 106.676, 10.7688, '643 Đ. Điện Biên Phủ, Phường 1, Quận 3, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 2, 8, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(23, 1, 106.677, 10.7629, '78 Đ. Hùng Vương, Phường 1, Quận 10, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 2, 3, 6, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(24, 1, 106.678, 10.7636, '20 Đ. Hùng Vương, Phường 1, Quận 10, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 3, 4, 6, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(25, 1, 106.68, 10.7601, '196A, B, Đ. Trần Bình Trọng, Phường 4, Quận 5, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 5, 4, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(26, 1, 106.681, 10.7603, '292a Đ. An Dương Vương, Phường 4, Quận 5, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 2, 6, 4, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(27, 0, 106.681, 10.7601, '283 Đ. An Dương Vương, Phường 3, Quận 5, Thành phố Hồ Chí Minh 700000, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 3, 1, 5, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(28, 0, 106.675, 10.7666, '498 Đ. Lê Hồng Phong, Phường 1, Quận 10, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 2, 6, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(29, 0, 106.675, 10.767, '526 Tổ 53 Khu phố 4, Phường 1, Quận 10, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 1, 2, 6, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14'),
(30, 1, 106.675, 10.7677, '346 Đ. Lý Thái Tổ, Phường 1, Quận 3, Thành phố Hồ Chí Minh, Việt Nam', '[\"https://goldsungroup.com.vn/wp-content/uploads/2019/11/bien-quang-cao-mot-cot-tren-duong-quoc-lo.jpg\",\"https://lambanghieuhcm.com/wp-content/uploads/2020/01/lam-bang-hieu-trung-tam-thuong-mai-2.jpg\"]', b'1', 2, 3, 8, NULL, '2023-12-18 13:50:14', '2023-12-18 13:50:14');

-- --------------------------------------------------------

--
-- Table structure for table `locations_edit`
--

CREATE TABLE `locations_edit` (
  `id` int(11) UNSIGNED NOT NULL,
  `planning` tinyint(1) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `images` text DEFAULT NULL,
  `content` text DEFAULT NULL,
  `property_id` int(11) UNSIGNED NOT NULL,
  `ads_form_id` int(11) UNSIGNED NOT NULL,
  `location_type_id` int(11) UNSIGNED NOT NULL,
  `user_id` int(11) UNSIGNED NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `location_types`
--

CREATE TABLE `location_types` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `location_types`
--

INSERT INTO `location_types` (`id`, `name`, `description`, `created_at`) VALUES
(1, 'Đất công / Công viên / Hành lang an toàn giao thôn', 'Mô tả Đất công / Công viên / Hành lang an toàn giao thông', '2023-12-17 07:30:23'),
(2, 'Đất tư nhân/Nhà ở riêng lẻ', 'Mô tả Đất tư nhân/Nhà ở riêng lẻ', '2023-12-17 07:30:23'),
(3, 'Trung tâm thương mại', 'Mô tả trung tâm', '2023-12-17 07:44:09'),
(4, 'Chợ', 'Mô tả chợ', '2023-12-17 07:45:20'),
(5, 'Cây xăng', 'Mô tả cây xăng', '2023-12-17 07:45:20'),
(6, 'Nhà chờ xe buýt ', 'Mô tả nhà chờ xe buýt', '2023-12-17 07:45:20');

-- --------------------------------------------------------

--
-- Table structure for table `properties`
--

CREATE TABLE `properties` (
  `id` int(11) UNSIGNED NOT NULL,
  `property_parent_id` int(11) UNSIGNED DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` enum('DISTRICT','WARD','','') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `properties`
--

INSERT INTO `properties` (`id`, `property_parent_id`, `name`, `code`, `created_at`) VALUES
(1, NULL, 'Quận 5', 'DISTRICT', '2023-12-18 12:55:31'),
(2, NULL, 'Quận 10', 'DISTRICT', '2023-12-18 12:55:42'),
(3, 19, 'Phường Nguyễn Cư Trinh', 'WARD', '2023-12-18 12:56:35'),
(4, 1, 'Phường 4', 'WARD', '2023-12-18 12:57:10'),
(5, 1, 'Phường 3', 'WARD', '2023-12-18 12:57:38'),
(6, 2, 'Phường 1', 'WARD', '2023-12-18 12:59:37'),
(7, 2, 'Phường 10', 'WARD', '2023-12-18 13:00:13'),
(8, 13, 'Phường 1', 'WARD', '2023-12-18 13:00:45'),
(9, 13, 'Phường 2', 'WARD', '2023-12-18 13:01:26'),
(10, 2, 'Phường 9', 'WARD', '2023-12-18 13:02:03'),
(11, 2, 'Phường 12', 'WARD', '2023-12-18 13:02:03'),
(13, NULL, 'Quận 3', 'DISTRICT', '2023-12-18 12:59:45'),
(14, 19, 'Phường 4', 'WARD', '2024-01-10 11:54:13'),
(19, NULL, 'Quận 1', 'DISTRICT', '2024-01-10 11:33:55');

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `id` int(11) UNSIGNED NOT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `guest_email` varchar(50) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT 1 COMMENT '  NEW = 1,\r\n  PROCESSING = 2,\r\n  DONE = 3',
  `reply` varchar(255) DEFAULT NULL,
  `images` text DEFAULT NULL,
  `report_type_name` enum('LOCATION','ADVERTISE') DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `report_form_id` int(11) UNSIGNED NOT NULL,
  `ads_id` int(11) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`id`, `full_name`, `email`, `guest_email`, `phone`, `content`, `status`, `reply`, `images`, `report_type_name`, `address`, `longitude`, `latitude`, `report_form_id`, `ads_id`, `created_at`) VALUES
(1, 'Nguyễn Văn A', 'nguyenvana@gmail.com', 'nguyenquoctuan385@gmail.com', '123456789', 'Báo cáo sai phạm', 1, NULL, NULL, 'ADVERTISE', NULL, NULL, NULL, 1, 16, '2023-12-21 07:04:51'),
(2, 'Nguyễn Văn B', 'nguyenvanb@gmail.com', 'nguyenquoctuan385@gmail.com', '123456789', 'Báo cáo sai phạm', 2, NULL, NULL, 'ADVERTISE', NULL, NULL, NULL, 1, 16, '2023-12-21 07:04:51'),
(3, 'Nguyễn Văn A', 'nguyenvana@gmail.com', 'nguyenquoctuan385@gmail.com', '123456789', 'Báo cáo sai phạm', 3, '<p>Đã xử lý xong, vui lòng kiểm tra</p>', NULL, 'ADVERTISE', NULL, NULL, NULL, 1, 2, '2023-12-21 07:04:51'),
(4, 'Nguyễn Văn A', 'nguyenvana@gmail.com', 'nguyenquoctuan385@gmail.com', '123456789', 'Báo cáo sai phạm', 2, NULL, NULL, 'LOCATION', '603 Đ. Trần Hưng Đạo, Cầu Kho, Quận 1, Thành phố Hồ Chí Minh 700000, Việt Nam', 106.686, 10.7565, 1, NULL, '2023-12-21 07:04:51'),
(5, 'Nguyễn Quốc Tuấn', 'nguyenvana@gmail.com', 'nguyenquoctuan385@gmail.com', '0937690534', '<p>aaaaaaaaaaaa</p><p><strong>bbbbbbbbbbbb</strong></p>', 1, NULL, '[\"https://res.cloudinary.com/dacvpgdfi/image/upload/v1704261398/dldckqkxpa7ij81bvgt9.jpg\"]', 'ADVERTISE', NULL, NULL, NULL, 1, 8, '2024-01-03 05:56:36'),
(6, 'Nguyễn Quốc Tuấn', 'nguyenvana@gmail.com', 'nguyenquoctuan385@gmail.com', '0937690534', '<p>aaaaaaaaaaaaaaaa</p><p>		<strong>dddddddddđ</strong></p><p>			<strong><s>			eeeeeeeeeeee</s></strong></p>', 1, NULL, '[\"https://res.cloudinary.com/dacvpgdfi/image/upload/v1704261771/wme79phlpo0tdxd0sawl.png\",\"https://res.cloudinary.com/dacvpgdfi/image/upload/v1704261772/vgeqoson0nn168jk7zm9.png\"]', 'ADVERTISE', NULL, NULL, NULL, 3, 11, '2024-01-03 06:02:50');

-- --------------------------------------------------------

--
-- Table structure for table `report_forms`
--

CREATE TABLE `report_forms` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `report_forms`
--

INSERT INTO `report_forms` (`id`, `name`, `description`, `created_at`) VALUES
(1, 'Tố giác sai phạm', 'Mô tả Tố giác sai phạm', '2023-12-17 07:59:07'),
(2, 'Đăng ký nội dung', 'Mô tả Đăng ký nội dung', '2023-12-17 07:59:07'),
(3, 'Đóng góp ý kiến', 'Mô tả Đóng góp ý kiến', '2023-12-17 07:59:07'),
(4, 'Giải đáp thắc mắc', 'Mô tả Giải đáp thắc mắc', '2023-12-17 07:59:07');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) UNSIGNED NOT NULL,
  `code` varchar(10) NOT NULL,
  `description` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `code`, `description`) VALUES
(2, 'WARD', 'Cán bộ phường/xã'),
(3, 'DISTRICT', 'Cán bộ quận/huyện'),
(4, 'DEPARTMENT', 'Cán bộ Sở văn hóa - TT');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(256) NOT NULL,
  `birthday` date DEFAULT NULL,
  `avatar` text DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `token` varchar(256) DEFAULT NULL,
  `token_exp_time` datetime DEFAULT NULL,
  `otp` varchar(6) DEFAULT NULL,
  `otp_exp_time` datetime DEFAULT NULL,
  `role_id` int(11) UNSIGNED DEFAULT NULL,
  `property_id` int(11) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `birthday`, `avatar`, `phone`, `token`, `token_exp_time`, `otp`, `otp_exp_time`, `role_id`, `property_id`, `created_at`) VALUES
(1, 'Nguyễn Văn Dũng', 'nguyenvandung@example.com', 'fda750c7638bc78996f2f02e185125ac', '1990-01-01', 'https://i.pravatar.cc/1000', '123456789', NULL, NULL, NULL, NULL, 2, 3, '2023-12-23 09:54:10'),
(2, 'Lê Thị Hoài Anh', 'lethihoaianh@example.com', '2adfb08f3f42ff58e047e69e887be782', '1991-02-02', 'https://i.pravatar.cc/1000', '987654321', NULL, NULL, NULL, NULL, 2, 4, '2023-12-23 09:54:10'),
(3, 'Phạm Văn Thanh', 'phamvanthanh@example.com', '64fd9733c78983e938b1018dcb65f038', '1992-03-03', 'https://i.pravatar.cc/1000', '555555555', NULL, NULL, NULL, NULL, 2, 5, '2023-12-23 09:54:10'),
(4, 'Nguyễn Thị Hồng', 'nguyenthihong@example.com', '96a3ce71a3523b18d6294556ae63be8a', '1993-04-04', 'https://i.pravatar.cc/1000', '111111111', NULL, NULL, NULL, NULL, 2, 6, '2023-12-23 09:54:10'),
(5, 'Lê Văn Hùng', 'levanhung@example.com', '0cc9dbe1b38f956cbc1ab3aaf993415b', '1994-05-05', 'https://i.pravatar.cc/1000', '999999999', NULL, NULL, NULL, NULL, 2, 7, '2023-12-23 09:54:10'),
(6, 'Trần Thị Hạnh', 'tranthihanh@example.com', '55c4e63e983a47b2e6b2130006e677d9', '1995-06-06', 'https://i.pravatar.cc/1000', '444444444', NULL, NULL, NULL, NULL, 2, 8, '2023-12-23 09:54:10'),
(7, 'Nguyễn Thanh Tâm', 'nguyenthanhtam@example.com', '5536ad18d17669b03f1ef01ae0157eee', '1996-07-07', 'https://i.pravatar.cc/1000', '333333333', NULL, NULL, NULL, NULL, 2, 9, '2023-12-23 09:54:10'),
(8, 'Lê Minh Tuấn', 'leminhtuan@example.com', 'abb00bcb8f96f371df43353b2a2a2320', '1997-08-08', 'https://i.pravatar.cc/1000', '777777777', NULL, NULL, NULL, NULL, 2, 10, '2023-12-23 09:54:10'),
(9, 'Phan Thị Bích Ngọc', 'phanthibichngoc@example.com', '387bb8f826eead194dac2e3f34168760', '1998-09-09', 'https://i.pravatar.cc/1000', '222222222', NULL, NULL, NULL, NULL, 2, 11, '2023-12-23 09:54:10'),
(10, 'Vũ Quang Huy', 'vuquanghuy@example.com', '05cae72a37cf74340d399e266276bac1', '1999-10-10', 'https://i.pravatar.cc/1000', '666666666', NULL, NULL, NULL, NULL, 3, 1, '2023-12-23 09:54:10'),
(11, 'Nguyễn Thị Thanh Hương', 'nguyenthanhhuong@example.com', '1b8f5b8ee98a8d95d59ff2c025dbbc38', '2000-11-11', 'https://i.pravatar.cc/1000', '555555555', NULL, NULL, NULL, NULL, 3, 2, '2023-12-23 09:54:10'),
(12, 'Trần Đình Thiên Long', 'trandinhthienlong@example.com', '38eaea022f00cbca98d87dd3df245e70', '2001-12-12', 'https://i.pravatar.cc/1000', '888888888', NULL, NULL, NULL, NULL, 3, 13, '2023-12-23 09:54:10'),
(13, 'Nguyễn Thị Lan Anh', 'nguyenthilananh@example.com', '762de3d52ec142d731063e3dfa669f5e', '2002-01-01', 'https://i.pravatar.cc/1000', '333333333', NULL, NULL, NULL, NULL, 3, 1, '2023-12-23 09:54:10'),
(14, 'Phan Văn Hải', 'phanvanhai@example.com', '672e2f76926555c1125fc70c3a1f9423', '2003-02-02', 'https://i.pravatar.cc/1000', '999999999', NULL, NULL, NULL, NULL, 3, 2, '2023-12-23 09:54:10'),
(15, 'Nguyễn Thị Diệu Linh', 'nguyenthidieulinh@example.com', '301df57dc5e58bd98c4947b564cae46b', '2004-03-03', 'https://i.pravatar.cc/1000', '111111111', NULL, NULL, NULL, NULL, 3, 13, '2023-12-23 09:54:10'),
(16, 'Lê Quang Minh', 'lequangminh@example.com', 'ef8eaa65bc27704608e4c81a6086e29c', '2005-04-04', 'https://i.pravatar.cc/1000', '777777777', NULL, NULL, NULL, NULL, 3, 1, '2023-12-23 09:54:10'),
(17, 'Trần Văn Hoàng', 'tranvanhoang@example.com', 'ad1f2d588544580995487bb76f665763', '2006-05-05', 'https://i.pravatar.cc/1000', '222222222', NULL, NULL, NULL, NULL, 3, 2, '2023-12-23 09:54:10'),
(18, 'Nguyễn Thị Thảo Vy', 'nguyenthithaovy@example.com', 'a257151af6813c9eddce0821c33f9573', '2007-06-06', 'https://i.pravatar.cc/1000', '444444444', NULL, NULL, NULL, NULL, 3, 13, '2023-12-23 09:54:10'),
(19, 'Nguyễn Văn Khánh', 'nguyenvankhanh@example.com', 'a65fdc4f6521b1b91562fb585efd0d0a', '2008-07-07', 'https://i.pravatar.cc/1000', '666666666', NULL, NULL, NULL, NULL, 3, 1, '2023-12-23 09:54:10'),
(20, 'Nguyễn Thị Ngọc Ánh', 'nguyenthingocanh@example.com', '6d9a2eb35e3fb0b80777988ca941e4e9', '2009-08-08', 'https://i.pravatar.cc/1000', '888888888', NULL, NULL, NULL, NULL, 3, 2, '2023-12-23 09:54:10'),
(22, 'Nguyễn Thị Ngọc Ánh', 'nguyenthingocanh2@example.com', '6d9a2eb35e3fb0b80777988ca941e4e9', '2009-08-08', 'https://i.pravatar.cc/1000', '888888888', NULL, NULL, NULL, NULL, 3, 2, '2023-12-23 09:54:10'),
(23, 'Nguyễn Quốc Tuấn', 'nguyenquoctuan385@gmail.com', '$2a$10$IRBRzn6MYoX2WI/a/j3d7u/lz7ZUijzUy.H6qmQV7itV81oToGFJ6', '2002-04-29', 'https://res.cloudinary.com/dacvpgdfi/image/upload/v1704859357/wt7p7dnuoru279n3lqle.jpg', '+84937690534', '0670781f0430e81aab587a02f04b1fdd4d4c501d4c03d13f975e26d9af6a0f85', '2024-01-19 11:45:43', NULL, NULL, 3, 13, '2024-01-10 04:02:39'),
(24, 'Nguyễn Quốc Tuấn', 'nguyenquoctuan852@gmail.com', '$2a$10$IRBRzn6MYoX2WI/a/j3d7u/lz7ZUijzUy.H6qmQV7itV81oToGFJ6', '2002-04-29', 'https://res.cloudinary.com/dacvpgdfi/image/upload/v1704859357/wt7p7dnuoru279n3lqle.jpg', '+84937690534', '1fd831eedb5d6c30d8bfd12681b9c4ac3c8c651f1047897944dbee8abeeb7da7', '2024-01-18 21:38:55', NULL, NULL, 2, 3, '2024-01-10 04:02:39'),
(25, 'Nguyễn Quốc Tuấn', 'nguyenquoctuan147@gmail.com', '$2a$10$IRBRzn6MYoX2WI/a/j3d7u/lz7ZUijzUy.H6qmQV7itV81oToGFJ6', '2002-04-29', 'https://res.cloudinary.com/dacvpgdfi/image/upload/v1704859357/wt7p7dnuoru279n3lqle.jpg', '+84937690534', '9dce67b46876ac64ba7fc8e3227eccfcb91eecf949912cdd3d85cd97d030d6ec', '2024-01-19 09:40:39', NULL, NULL, 4, NULL, '2024-01-10 04:02:39');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `advertises`
--
ALTER TABLE `advertises`
  ADD PRIMARY KEY (`id`),
  ADD KEY `location_id` (`location_id`),
  ADD KEY `ads_type_id` (`ads_type_id`),
  ADD KEY `advertises_ibfk_3` (`advertise_edit_id`);

--
-- Indexes for table `advertises_edit`
--
ALTER TABLE `advertises_edit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `location_id` (`location_id`),
  ADD KEY `ads_type_id` (`ads_type_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `advertise_forms`
--
ALTER TABLE `advertise_forms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `advertise_types`
--
ALTER TABLE `advertise_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contracts`
--
ALTER TABLE `contracts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `contract_ibfk_1` (`ads_id`);

--
-- Indexes for table `locations`
--
ALTER TABLE `locations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `property_id` (`property_id`),
  ADD KEY `ads_form_id` (`ads_form_id`),
  ADD KEY `location_type_id` (`location_type_id`),
  ADD KEY `locations_ibfk_4` (`location_edit_id`);

--
-- Indexes for table `locations_edit`
--
ALTER TABLE `locations_edit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `property_id` (`property_id`),
  ADD KEY `ads_form_id` (`ads_form_id`),
  ADD KEY `location_type_id` (`location_type_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `location_types`
--
ALTER TABLE `location_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `properties`
--
ALTER TABLE `properties`
  ADD PRIMARY KEY (`id`),
  ADD KEY `property_parent_id` (`property_parent_id`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `advertise_id` (`ads_id`),
  ADD KEY `report_form_id` (`report_form_id`);

--
-- Indexes for table `report_forms`
--
ALTER TABLE `report_forms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_role_id` (`role_id`),
  ADD KEY `fk_property_id` (`property_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `advertises`
--
ALTER TABLE `advertises`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `advertises_edit`
--
ALTER TABLE `advertises_edit`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `advertise_forms`
--
ALTER TABLE `advertise_forms`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `advertise_types`
--
ALTER TABLE `advertise_types`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `contracts`
--
ALTER TABLE `contracts`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `locations`
--
ALTER TABLE `locations`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `locations_edit`
--
ALTER TABLE `locations_edit`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `location_types`
--
ALTER TABLE `location_types`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `properties`
--
ALTER TABLE `properties`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `report_forms`
--
ALTER TABLE `report_forms`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `advertises`
--
ALTER TABLE `advertises`
  ADD CONSTRAINT `advertises_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`),
  ADD CONSTRAINT `advertises_ibfk_2` FOREIGN KEY (`ads_type_id`) REFERENCES `advertise_types` (`id`),
  ADD CONSTRAINT `advertises_ibfk_3` FOREIGN KEY (`advertise_edit_id`) REFERENCES `advertises_edit` (`id`);

--
-- Constraints for table `advertises_edit`
--
ALTER TABLE `advertises_edit`
  ADD CONSTRAINT `advertises_edit_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`),
  ADD CONSTRAINT `advertises_edit_ibfk_2` FOREIGN KEY (`ads_type_id`) REFERENCES `advertise_types` (`id`),
  ADD CONSTRAINT `advertises_edit_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `contracts`
--
ALTER TABLE `contracts`
  ADD CONSTRAINT `contract_ibfk_1` FOREIGN KEY (`ads_id`) REFERENCES `advertises` (`id`);

--
-- Constraints for table `locations`
--
ALTER TABLE `locations`
  ADD CONSTRAINT `locations_ibfk_1` FOREIGN KEY (`property_id`) REFERENCES `properties` (`id`),
  ADD CONSTRAINT `locations_ibfk_2` FOREIGN KEY (`ads_form_id`) REFERENCES `advertise_forms` (`id`),
  ADD CONSTRAINT `locations_ibfk_3` FOREIGN KEY (`location_type_id`) REFERENCES `location_types` (`id`),
  ADD CONSTRAINT `locations_ibfk_4` FOREIGN KEY (`location_edit_id`) REFERENCES `locations_edit` (`id`);

--
-- Constraints for table `locations_edit`
--
ALTER TABLE `locations_edit`
  ADD CONSTRAINT `locations_edit_ibfk_1` FOREIGN KEY (`property_id`) REFERENCES `properties` (`id`),
  ADD CONSTRAINT `locations_edit_ibfk_2` FOREIGN KEY (`ads_form_id`) REFERENCES `advertise_forms` (`id`),
  ADD CONSTRAINT `locations_edit_ibfk_3` FOREIGN KEY (`location_type_id`) REFERENCES `location_types` (`id`),
  ADD CONSTRAINT `locations_edit_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `properties`
--
ALTER TABLE `properties`
  ADD CONSTRAINT `properties_ibfk_1` FOREIGN KEY (`property_parent_id`) REFERENCES `properties` (`id`);

--
-- Constraints for table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`ads_id`) REFERENCES `advertises` (`id`),
  ADD CONSTRAINT `reports_ibfk_2` FOREIGN KEY (`report_form_id`) REFERENCES `report_forms` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_property_id` FOREIGN KEY (`property_id`) REFERENCES `properties` (`id`),
  ADD CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
