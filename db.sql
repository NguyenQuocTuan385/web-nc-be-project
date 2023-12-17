-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2023 at 04:58 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 7.2.21

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


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

CREATE TABLE `advertises`
(
    `id`                int(11) UNSIGNED NOT NULL,
    `licensing`         tinyint(1) DEFAULT NULL,
    `height`            float              DEFAULT NULL,
    `width`             float              DEFAULT NULL,
    `location_id`       int(11) UNSIGNED NOT NULL,
    `ads_type_id`       int(11) UNSIGNED NOT NULL,
    `status_edit`       tinyint(1) DEFAULT NULL,
    `advertise_edit_id` int(11) UNSIGNED NOT NULL,
    `created_at`        timestamp NOT NULL DEFAULT current_timestamp(),
    `updated_at`        timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `advertises_edit`
--

CREATE TABLE `advertises_edit`
(
    `id`          int(11) UNSIGNED NOT NULL,
    `licensing`   tinyint(1) DEFAULT NULL,
    `height`      float              DEFAULT NULL,
    `width`       float              DEFAULT NULL,
    `content`     text      NOT NULL,
    `location_id` int(11) UNSIGNED NOT NULL,
    `ads_type_id` int(11) UNSIGNED NOT NULL,
    `user_id`     int(11) UNSIGNED NOT NULL,
    `created_at`  timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `advertise_forms`
--

CREATE TABLE `advertise_forms`
(
    `id`          int(11) UNSIGNED NOT NULL,
    `name`        varchar(50)        DEFAULT NULL,
    `description` varchar(255)       DEFAULT NULL,
    `created_at`  timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `advertise_types`
--

CREATE TABLE `advertise_types`
(
    `id`          int(11) UNSIGNED NOT NULL,
    `name`        varchar(50)        DEFAULT NULL,
    `description` varchar(255)       DEFAULT NULL,
    `created_at`  timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `contracts`
--

CREATE TABLE `contracts`
(
    `id`              int(10) UNSIGNED NOT NULL,
    `company_name`    varchar(50)        DEFAULT NULL,
    `company_email`   varchar(50)        DEFAULT NULL,
    `company_phone`   varchar(50)        DEFAULT NULL,
    `company_address` varchar(200)       DEFAULT NULL,
    `start_at`        datetime(6) DEFAULT NULL,
    `end_at`          datetime(6) DEFAULT NULL,
    `status`          int(11) DEFAULT NULL,
    `img_url`         varchar(100)       DEFAULT NULL,
    `advertise_id`    int(10) UNSIGNED NOT NULL,
    `created_at`      timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images`
(
    `id`               int(11) UNSIGNED NOT NULL,
    `img_url`          text DEFAULT NULL,
    `location_id`      int(11) UNSIGNED NOT NULL,
    `report_id`        int(11) UNSIGNED NOT NULL,
    `location_edit_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE `locations`
(
    `id`               int(11) UNSIGNED NOT NULL,
    `planning`         tinyint(1) DEFAULT NULL,
    `latitude`         float              DEFAULT NULL,
    `longitude`        float              DEFAULT NULL,
    `address`          varchar(200)       DEFAULT NULL,
    `status_edit`      int(1) DEFAULT NULL,
    `ads_form_id`      int(11) UNSIGNED NOT NULL,
    `location_type_id` int(11) UNSIGNED NOT NULL,
    `property_id`      int(11) UNSIGNED NOT NULL,
    `location_edit_id` int(10) UNSIGNED DEFAULT NULL,
    `created_at`       timestamp NOT NULL DEFAULT current_timestamp(),
    `updated_at`       timestamp NULL DEFAULT current_timestamp ()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `locations_edit`
--

CREATE TABLE `locations_edit`
(
    `id`               int(11) UNSIGNED NOT NULL,
    `planning`         tinyint(1) DEFAULT NULL,
    `latitude`         float              DEFAULT NULL,
    `longitude`        float              DEFAULT NULL,
    `address`          varchar(200)       DEFAULT NULL,
    `content`          text               DEFAULT NULL,
    `property_id`      int(11) UNSIGNED NOT NULL,
    `ads_form_id`      int(11) UNSIGNED NOT NULL,
    `location_type_id` int(11) UNSIGNED NOT NULL,
    `user_id`          int(11) UNSIGNED NOT NULL,
    `created_at`       timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `location_types`
--

CREATE TABLE `location_types`
(
    `id`          int(11) UNSIGNED NOT NULL,
    `name`        varchar(50)        DEFAULT NULL,
    `description` varchar(255)       DEFAULT NULL,
    `created_at`  timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `properties`
--

CREATE TABLE `properties`
(
    `id`                 int(11) UNSIGNED NOT NULL,
    `property_parent_id` int(11) UNSIGNED DEFAULT NULL,
    `name`               varchar(255)       DEFAULT NULL,
    `code`               varchar(255)       DEFAULT NULL,
    `created_at`         timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports`
(
    `id`               int(11) UNSIGNED NOT NULL,
    `full_name`        varchar(50)          DEFAULT NULL,
    `email`            varchar(50)          DEFAULT NULL,
    `phone`            varchar(10)          DEFAULT NULL,
    `content`          varchar(255)         DEFAULT NULL,
    `status`           tinyint(1) DEFAULT NULL,
    `reply`            varchar(255)         DEFAULT NULL,
    `report_type_name` varchar(20) NOT NULL,
    `report_form_id`   int(11) UNSIGNED NOT NULL,
    `advertise_id`     int(11) UNSIGNED DEFAULT NULL,
    `location_id`      int(11) UNSIGNED DEFAULT NULL,
    `created_at`       timestamp   NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `report_forms`
--

CREATE TABLE `report_forms`
(
    `id`          int(11) UNSIGNED NOT NULL,
    `name`        varchar(50) NOT NULL,
    `description` varchar(255) DEFAULT NULL,
    `created_at`  timestamp NULL DEFAULT current_timestamp ()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles`
(
    `id`          int(11) UNSIGNED NOT NULL,
    `code`        varchar(10) NOT NULL,
    `description` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users`
(
    `id`          int(11) UNSIGNED NOT NULL,
    `name`        varchar(100)          DEFAULT NULL,
    `email`       varchar(100) NOT NULL,
    `password`    varchar(50)  NOT NULL,
    `birthday`    date                  DEFAULT NULL,
    `avatar`      varchar(150)          DEFAULT NULL,
    `phone`       varchar(20)           DEFAULT NULL,
    `role_id`     int(11) UNSIGNED NOT NULL,
    `property_id` int(11) UNSIGNED NOT NULL,
    `created_at`  timestamp    NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  ADD KEY `contract_FK` (`advertise_id`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
    ADD PRIMARY KEY (`id`),
  ADD KEY `location_id` (`location_id`),
  ADD KEY `report_id` (`report_id`),
  ADD KEY `images_ibfk_3` (`location_edit_id`);

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
  ADD KEY `advertise_id` (`advertise_id`),
  ADD KEY `report_form_id` (`report_form_id`),
  ADD KEY `location_id` (`location_id`);

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
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `advertises_edit`
--
ALTER TABLE `advertises_edit`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `advertise_forms`
--
ALTER TABLE `advertise_forms`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `advertise_types`
--
ALTER TABLE `advertise_types`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contracts`
--
ALTER TABLE `contracts`
    MODIFY `id` int (10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `locations`
--
ALTER TABLE `locations`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `locations_edit`
--
ALTER TABLE `locations_edit`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `location_types`
--
ALTER TABLE `location_types`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `properties`
--
ALTER TABLE `properties`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `report_forms`
--
ALTER TABLE `report_forms`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `id` int (11) UNSIGNED NOT NULL AUTO_INCREMENT;

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
    ADD CONSTRAINT `contract_FK` FOREIGN KEY (`advertise_id`) REFERENCES `advertises` (`id`);

--
-- Constraints for table `images`
--
ALTER TABLE `images`
    ADD CONSTRAINT `images_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`),
  ADD CONSTRAINT `images_ibfk_2` FOREIGN KEY (`report_id`) REFERENCES `reports` (`id`),
  ADD CONSTRAINT `images_ibfk_3` FOREIGN KEY (`location_edit_id`) REFERENCES `locations_edit` (`id`);

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
    ADD CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`advertise_id`) REFERENCES `advertises` (`id`),
  ADD CONSTRAINT `reports_ibfk_2` FOREIGN KEY (`report_form_id`) REFERENCES `report_forms` (`id`),
  ADD CONSTRAINT `reports_ibfk_4` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`);

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
