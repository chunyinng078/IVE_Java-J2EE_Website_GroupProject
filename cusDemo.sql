-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2022 年 04 月 28 日 18:24
-- 伺服器版本： 10.4.19-MariaDB
-- PHP 版本： 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `4511ass`
--

-- --------------------------------------------------------

--
-- 資料表結構 `booking`
--

DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `id` int(20) NOT NULL,
  `date` date NOT NULL,
  `type` varchar(20) NOT NULL,
  `typeid` varchar(20) NOT NULL,
  `custid` int(20) NOT NULL,
  `time` time NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 傾印資料表的資料 `booking`
--

INSERT INTO `booking` (`id`, `date`, `type`, `typeid`, `custid`, `time`, `status`) VALUES
(1, '2022-05-01', 'trainer', '1', 9, '12:00:00', 'confirm'),
(2, '2022-03-22', 'trainer', '1', 13, '12:00:00', 'confirm'),
(3, '2022-03-23', 'center', '1', 14, '11:00:00', 'confirm'),
(4, '2022-04-01', 'center', '1', 15, '10:00:00', 'confirm'),
(5, '2022-04-02', 'trainer', '2', 16, '12:00:00', 'confirm'),
(6, '2022-04-10', 'trainer', '3', 17, '12:00:00', 'confirm'),
(7, '2022-04-20', 'center', '1', 18, '11:00:00', 'confirm'),
(8, '2022-04-25', 'center', '2', 20, '10:00:00', 'confirm'),
(9, '2022-03-01', 'trainer', '3', 1, '12:00:00', 'confirm'),
(10, '2022-03-22', 'center', '4', 3, '12:00:00', 'confirm'),
(11, '2022-03-23', 'center', '5', 1, '11:00:00', 'confirm'),
(12, '2022-04-01', 'center', '1', 12, '10:00:00', 'confirm'),
(13, '2022-04-02', 'center', '2', 22, '12:00:00', 'confirm'),
(14, '2022-04-10', 'center', '4', 5, '12:00:00', 'confirm'),
(15, '2022-04-20', 'center', '5', 1, '11:00:00', 'confirm'),
(16, '2022-04-25', 'center', '5', 2, '10:00:00', 'confirm'),
(17, '2022-04-01', 'trainer', '1', 10, '10:00:00', 'confirm'),
(18, '2022-04-02', 'trainer', '1', 19, '12:00:00', 'confirm'),
(19, '2022-04-10', 'trainer', '4', 5, '12:00:00', 'confirm'),
(20, '2022-04-20', 'trainer', '4', 1, '11:00:00', 'confirm'),
(21, '2022-04-25', 'trainer', '5', 2, '10:00:00', 'confirm'),
(22, '2022-04-01', 'center', '1', 11, '10:00:00', 'confirm'),
(23, '2022-04-02', 'center', '3', 21, '12:00:00', 'confirm'),
(24, '2022-04-10', 'trainer', '5', 10, '12:00:00', 'confirm'),
(25, '2022-04-20', 'trainer', '4', 1, '11:00:00', 'confirm'),
(26, '2022-04-22', 'center', '1', 12, '12:00:00', 'confirm'),
(27, '2022-04-22', 'center', '1', 13, '12:00:00', 'confirm'),
(28, '2022-04-22', 'center', '1', 14, '12:00:00', 'confirm'),
(29, '2022-04-22', 'center', '1', 15, '12:00:00', 'confirm'),
(30, '2022-04-22', 'center', '1', 16, '12:00:00', 'confirm'),
(31, '2022-04-22', 'center', '1', 17, '12:00:00', 'confirm'),
(32, '2022-04-22', 'center', '1', 18, '12:00:00', 'confirm'),
(33, '2022-04-22', 'center', '1', 19, '12:00:00', 'confirm'),
(34, '2022-04-22', 'center', '1', 20, '12:00:00', 'confirm'),
(35, '2022-04-22', 'center', '1', 21, '12:00:00', 'confirm');

-- --------------------------------------------------------

--
-- 資料表結構 `gymcenter`
--

DROP TABLE IF EXISTS `gymcenter`;
CREATE TABLE `gymcenter` (
  `id` int(20) NOT NULL,
  `centerName` varchar(20) NOT NULL,
  `description` varchar(20) NOT NULL,
  `states` varchar(20) NOT NULL,
  `hrRate` int(20) NOT NULL,
  `imageID` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 傾印資料表的資料 `gymcenter`
--

INSERT INTO `gymcenter` (`id`, `centerName`, `description`, `states`, `hrRate`, `imageID`) VALUES
(1, 'Tuen Mun', 'Biggest center', 'enable', 200, 1),
(2, 'Sha Tin', 'Most popular', 'enable', 200, 2),
(3, 'Tsing Yi', 'Many training tools', 'enable', 200, 3),
(4, 'Lee Wai Lee', 'Smallest center', 'enable', 200, 4),
(5, 'Chai Wan', 'Many private rooms', 'enable', 200, 5);

-- --------------------------------------------------------

--
-- 資料表結構 `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(11) NOT NULL,
  `description` text NOT NULL,
  `type` varchar(20) NOT NULL,
  `enable` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 傾印資料表的資料 `role`
--

INSERT INTO `role` (`id`, `name`, `description`, `type`, `enable`) VALUES
(1, 'trainer', 'trainer of gym center', 'trainer', 'true'),
(2, 'staff', 'staff of gym center center', 'staff', 'true'),
(3, 'sm', 'senior management of gym center', 'management', 'true'),
(4, 'customer', 'customer of gym center', 'customer', 'true');

-- --------------------------------------------------------

--
-- 資料表結構 `roletype`
--

DROP TABLE IF EXISTS `roletype`;
CREATE TABLE `roletype` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 傾印資料表的資料 `roletype`
--

INSERT INTO `roletype` (`id`, `name`) VALUES
(1, 'customer'),
(2, 'trainer'),
(3, 'staff'),
(4, 'management');

-- --------------------------------------------------------

--
-- 資料表結構 `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `id` int(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gymCenter` varchar(20) NOT NULL,
  `description` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 資料表結構 `trainer`
--

DROP TABLE IF EXISTS `trainer`;
CREATE TABLE `trainer` (
  `id` int(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `states` varchar(20) NOT NULL,
  `hrRate` int(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `sportType` varchar(100) NOT NULL,
  `imageID` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 傾印資料表的資料 `trainer`
--

INSERT INTO `trainer` (`id`, `name`, `states`, `hrRate`, `description`, `sportType`, `imageID`) VALUES
(1, 'sam', 'enable', 200, '10 year exp', 'cardio', 1),
(2, 'tom', 'enable', 200, 'Study sport', 'cardio,weight training', 2),
(3, 'zan', 'enable', 200, 'Over 100 customer', 'weight training', 3),
(4, 'alex', 'enable', 200, 'Earn over $999999', 'boxing', 4),
(5, 'zoey', 'enable', 200, 'Professional', 'cardio,weight training,boxing', 5);

-- --------------------------------------------------------

--
-- 資料表結構 `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  `tel` int(8) NOT NULL,
  `enable` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 傾印資料表的資料 `user`
--

INSERT INTO `user` (`userid`, `username`, `password`, `name`, `role`, `tel`, `enable`) VALUES
(1, 'peter123', '123', 'peter', 'staff', 12345678, 'true'),
(2, 'zoe123', '123', 'zoe', 'staff', 87654321, 'true'),
(4, 'sam123', '123', 'sam', 'trainer', 21321321, 'true'),
(5, 'tom123', '123', 'tom', 'trainer', 21546541, 'true'),
(6, 'zan123', '123', 'zan', 'trainer', 21378321, 'true'),
(7, 'alex123', '123', 'alex', 'trainer', 21999321, 'true'),
(8, 'zoey123', '123', 'zoey', 'trainer', 31238811, 'true'),
(9, 'user1', '123', 'user', 'customer', 14141414, 'true'),
(10, 'user2', '123', 'tony', 'customer', 13131313, 'true'),
(11, 'user3', '123', 'jasper', 'customer', 12121212, 'true'),
(12, 'user4', '123', 'edwin', 'customer', 99999999, 'true'),
(13, 'user5', '123', 'alexson', 'customer', 88888888, 'true'),
(14, 'user6', '123', 'tommy', 'customer', 77777777, 'true'),
(15, 'user7', '123', 'supertom', 'customer', 66666666, 'true'),
(16, 'user8', '123', 'supersam', 'customer', 55555555, 'true'),
(17, 'user9', '123', 'superzoe', 'customer', 44444444, 'true'),
(18, 'user10', '123', 'superman', 'customer', 33333333, 'true'),
(19, 'user11', '123', 'badman', 'customer', 22222222, 'true'),
(20, 'user12', '123', 'chun', 'customer', 11111111, 'true'),
(21, 'user13', '123', 'yikcsam', 'customer', 95959595, 'true'),
(22, 'user14', '123', 'yin', 'sm', 96969696, 'true'),
(23, 'user15', '123', 'homan', 'sm', 97979797, 'true'),
(24, 'user16', '123', 'manho', 'sm', 98989898, 'true');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `gymcenter`
--
ALTER TABLE `gymcenter`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `roletype`
--
ALTER TABLE `roletype`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `trainer`
--
ALTER TABLE `trainer`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `gymcenter`
--
ALTER TABLE `gymcenter`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `roletype`
--
ALTER TABLE `roletype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `status`
--
ALTER TABLE `status`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `trainer`
--
ALTER TABLE `trainer`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
