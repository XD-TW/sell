/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : sell

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-09-27 22:35:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL COMMENT '订单id',
  `product_id` varchar(32) NOT NULL COMMENT '商品id',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品单价',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(512) NOT NULL COMMENT '商品小图',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态,默认0新下单',
  `pay_status` tinyint(3) DEFAULT '0' COMMENT '支付状态,默认0未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单主表';

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='类目表\r\n';

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) NOT NULL COMMENT '小图',
  `product_status` tinyint(11) NOT NULL DEFAULT '0' COMMENT '商品状态,0正常1下架,',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';
