/*
Navicat MySQL Data Transfer

Source Server         : hmb
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : hotel

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-07-03 19:18:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `description` varchar(255) DEFAULT NULL,
                        `hotelId` int(11) DEFAULT '-1',
                        `couponType` int(11) NOT NULL,
                        `couponName` varchar(255) NOT NULL,
                        `target_money` int(11) DEFAULT NULL,
                        `discount` double DEFAULT NULL,
                        `status` int(11) DEFAULT NULL,
                        `start_time` datetime DEFAULT NULL,
                        `end_time` datetime DEFAULT NULL,
                        `discount_money` int(11) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES ('2', '满500-100优惠', '2', '3', '满减优惠券', '500', '0', '1', null, null, '100');

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `hotelName` varchar(255) NOT NULL,
                       `hotelDescription` varchar(255) DEFAULT NULL,
                       `address` varchar(255) DEFAULT NULL,
                       `bizRegion` varchar(255) DEFAULT NULL,
                       `hotelStar` varchar(255) DEFAULT NULL,
                       `phoneNum` varchar(67) DEFAULT NULL,
                       `rate` double DEFAULT '0',
                       `hotelState` int(11) DEFAULT '-1',
                       `manager_id` int(11) DEFAULT NULL,
                       `commentNumber` int(11) DEFAULT '0',
                       `totalCommentScore` int(11) DEFAULT '0',
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('1', '汉庭酒店', '欢迎您入住', null, 'XiDan', 'Four', '1829373819', '4.8', '-1', '6', '5', '24');
INSERT INTO `hotel` VALUES ('2', '儒家酒店', '欢迎您入住', '南京市鼓楼区珠江路268号', 'XiDan', 'Four', '1829373819', '4.8', '-1', '6', '5', '24');
INSERT INTO `hotel` VALUES ('3', '桂圆酒店', '欢迎您入住', '南京市栖霞区珠江路268号', 'XiDan', 'Four', '1829553719', '4.8', '-1', '6', '5', '24');

-- ----------------------------
-- Table structure for orderlist
-- ----------------------------
DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `userId` int(11) DEFAULT NULL,
                           `hotelId` int(11) DEFAULT NULL,
                           `hotelName` varchar(255) DEFAULT NULL,
                           `checkInDate` varchar(255) DEFAULT NULL,
                           `checkOutDate` varchar(255) DEFAULT NULL,
                           `roomType` varchar(255) DEFAULT NULL,
                           `roomNum` int(255) DEFAULT NULL,
                           `peopleNum` int(255) DEFAULT NULL,
                           `haveChild` tinytext,
                           `createDate` varchar(255) DEFAULT NULL,
                           `price` decimal(65,0) DEFAULT NULL,
                           `tenantName` varchar(255) DEFAULT NULL,
                           `phoneNumber` varchar(255) DEFAULT NULL,
                           `orderState` varchar(255) DEFAULT NULL,
                           `comment` mediumtext,
                           `commentScore` int(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderlist
-- ----------------------------

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
                      `id` int(11) NOT NULL AUTO_INCREMENT,
                      `price` double DEFAULT NULL,
                      `curNum` int(11) DEFAULT NULL,
                      `total` int(11) DEFAULT NULL,
                      `hotel_id` int(11) DEFAULT NULL,
                      `roomType` varchar(50) DEFAULT NULL,
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('2', '199', '20', '20', '1', 'BigBed');
INSERT INTO `room` VALUES ('3', '299', '30', '30', '1', 'DoubleBed');
INSERT INTO `room` VALUES ('4', '399', '10', '10', '1', 'Family');
INSERT INTO `room` VALUES ('6', '399', '10', '10', '2', 'Family');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                      `id` int(11) NOT NULL AUTO_INCREMENT,
                      `email` varchar(255) DEFAULT NULL,
                      `password` varchar(11) NOT NULL,
                      `username` varchar(255) DEFAULT NULL,
                      `phonenumber` varchar(255) DEFAULT NULL,
                      `credit` double(255,0) DEFAULT NULL,
                      `usertype` varchar(255) DEFAULT NULL,
                      `birthday` varchar(255) DEFAULT NULL,
                      PRIMARY KEY (`id`),
                      UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '2636221659@qq.com', '67is666', '网站管理员', '+86 188...', '100', 'Manager', '1999/05/20');
INSERT INTO `user` VALUES ('4', '1012681@qq.com', '123456', '用户1', '12345678919', '100', 'Client', '1993/05/20');
INSERT INTO `user` VALUES ('5', '123@qq.com', '123456', '用户2', '12345678911', '100', 'Client', '1999/09/20');
INSERT INTO `user` VALUES ('6', '333@qq.com', '123456', 'jd工作人员', null, null, 'HotelManager', '1967/06/07');
INSERT INTO `user` VALUES ('7', '67666@qq.com', '123456', '网站营销人员', '10086', '1000', 'WebMarketer', '1967/06/07');

-- ----------------------------
-- Procedure structure for e_test
-- ----------------------------
DROP PROCEDURE IF EXISTS `e_test`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `e_test`()
BEGIN
  update OrderList o,User u set orderState='异常',credit=credit-o.price  where o.userId=u.id and to_days(now())-TO_DAYS(checkInDate)>0 and orderState='已预订';
END
;;
DELIMITER ;

-- ----------------------------
-- Event structure for event_test
-- ----------------------------
DROP EVENT IF EXISTS `event_test`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `event_test` ON SCHEDULE EVERY 20 SECOND STARTS '2020-06-30 00:21:12' ON COMPLETION PRESERVE ENABLE DO CALL e_test()
;;
DELIMITER ;
