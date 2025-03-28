REPLACE INTO `npc` SET `id`=555, `idTemplate`=18544
 , `name`='Buffer', `serverSideName`=1, `title`='Rin4a''s Custom', `serverSideTitle`=1
 , `class`='LineageNpcEV.trap_ironcastle'
 , `collision_radius`=8.00, `collision_height`=24.00
 , `level`=85, `sex`='male', `type`='L2Npc', `attackrange`=40
 , `hp`=20000, `mp`=164, `hpreg`=1.15, `mpreg`=1.21
 , `str`=40, `con`=1, `dex`=30, `int`=21, `wit`=20, `men`=25, `exp`=0, `sp`=0
 , `patk`=40000, `pdef`=50000, `matk`=50000, `mdef`=50000
 , `atkspd`=999, `aggro`=0, `matkspd`=333
 , `rhand`=0, `lhand`=0
 , `walkspd`=60, `runspd`=125;

DROP TABLE IF EXISTS `Buffer_List`;
-- DROP TABLE IF EXISTS `Buffer_Scheme_List`;
-- DROP TABLE IF EXISTS `Buffer_Scheme_Player`;

CREATE TABLE `Buffer_List` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buff_class` int(2) DEFAULT NULL,
  `buffType` varchar(10) DEFAULT NULL,
  `buffId` int(5) DEFAULT '0',
  `buffLevel` int(5) DEFAULT NULL,
  `forClass` tinyint(1) DEFAULT NULL,
  `canUse` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=136;
CREATE TABLE IF NOT EXISTS `Buffer_Scheme_List` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scheme_id` int(11) DEFAULT NULL,
  `skill_id` int(8) DEFAULT NULL,
  `skill_level` int(4) DEFAULT NULL,
  `buff_class` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=30;
CREATE TABLE IF NOT EXISTS `Buffer_Scheme_Player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` varchar(40) DEFAULT NULL,
  `scheme_name` varchar(36) DEFAULT NULL,
  `mod_accepted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=7;

INSERT INTO `Buffer_List` VALUES ('1', '0', 'buff', '1036', '2', '2', '1');
INSERT INTO `Buffer_List` VALUES ('2', '0', 'buff', '1040', '3', '2', '1');
INSERT INTO `Buffer_List` VALUES ('3', '0', 'buff', '1043', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('4', '0', 'buff', '1044', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('5', '0', 'buff', '1045', '6', '2', '1');
INSERT INTO `Buffer_List` VALUES ('6', '0', 'buff', '1047', '4', '3', '1');
INSERT INTO `Buffer_List` VALUES ('7', '0', 'buff', '1048', '6', '2', '1');
INSERT INTO `Buffer_List` VALUES ('8', '0', 'buff', '1059', '3', '1', '1');
INSERT INTO `Buffer_List` VALUES ('9', '0', 'buff', '1068', '3', '0', '1');
INSERT INTO `Buffer_List` VALUES ('10', '0', 'buff', '1077', '3', '0', '1');
INSERT INTO `Buffer_List` VALUES ('11', '0', 'buff', '1085', '3', '1', '1');
INSERT INTO `Buffer_List` VALUES ('12', '0', 'buff', '1086', '2', '0', '1');
INSERT INTO `Buffer_List` VALUES ('13', '0', 'buff', '1087', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('14', '0', 'buff', '1204', '2', '2', '1');
INSERT INTO `Buffer_List` VALUES ('15', '0', 'buff', '1240', '3', '0', '1');
INSERT INTO `Buffer_List` VALUES ('16', '0', 'buff', '1242', '3', '0', '1');
INSERT INTO `Buffer_List` VALUES ('17', '0', 'buff', '1243', '6', '3', '1');
INSERT INTO `Buffer_List` VALUES ('18', '0', 'buff', '1257', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('19', '0', 'buff', '1268', '4', '3', '1');
INSERT INTO `Buffer_List` VALUES ('20', '0', 'buff', '1303', '2', '1', '1');
INSERT INTO `Buffer_List` VALUES ('21', '0', 'buff', '1304', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('22', '0', 'buff', '1307', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('23', '0', 'buff', '1311', '6', '3', '1');
INSERT INTO `Buffer_List` VALUES ('24', '0', 'buff', '1397', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('25', '0', 'buff', '1460', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('26', '0', 'buff', '1232', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('27', '0', 'buff', '1238', '3', '3', '0');
INSERT INTO `Buffer_List` VALUES ('28', '0', 'special', '1323', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('29', '0', 'special', '1388', '3', '0', '1');
INSERT INTO `Buffer_List` VALUES ('30', '0', 'special', '1389', '3', '1', '1');

INSERT INTO `Buffer_List` VALUES ('31', '1', 'song', '264', '1', '2', '1');
INSERT INTO `Buffer_List` VALUES ('32', '1', 'song', '265', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('33', '1', 'song', '266', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('34', '1', 'song', '267', '1', '2', '1');
INSERT INTO `Buffer_List` VALUES ('35', '1', 'song', '268', '1', '2', '1');
INSERT INTO `Buffer_List` VALUES ('36', '1', 'song', '269', '1', '0', '1');
INSERT INTO `Buffer_List` VALUES ('37', '1', 'song', '270', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('38', '1', 'song', '304', '1', '2', '1');
INSERT INTO `Buffer_List` VALUES ('39', '1', 'song', '305', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('40', '1', 'song', '306', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('41', '1', 'song', '308', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('42', '1', 'song', '349', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('43', '1', 'song', '363', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('44', '1', 'song', '364', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('45', '1', 'song', '529', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('46', '1', 'song', '764', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('47', '1', 'song', '914', '1', '3', '1');

INSERT INTO `Buffer_List` VALUES ('48', '2', 'dance', '271', '1', '0', '1');
INSERT INTO `Buffer_List` VALUES ('49', '2', 'dance', '272', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('50', '2', 'dance', '273', '1', '1', '1');
INSERT INTO `Buffer_List` VALUES ('51', '2', 'dance', '274', '1', '0', '1');
INSERT INTO `Buffer_List` VALUES ('52', '2', 'dance', '275', '1', '0', '1');
INSERT INTO `Buffer_List` VALUES ('53', '2', 'dance', '276', '1', '1', '1');
INSERT INTO `Buffer_List` VALUES ('54', '2', 'dance', '277', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('55', '2', 'dance', '307', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('56', '2', 'dance', '309', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('57', '2', 'dance', '310', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('58', '2', 'dance', '311', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('59', '2', 'dance', '365', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('60', '2', 'dance', '366', '1', '3', '0');
INSERT INTO `Buffer_List` VALUES ('61', '2', 'dance', '530', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('62', '2', 'dance', '765', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('63', '2', 'dance', '915', '1', '3', '1');

INSERT INTO `Buffer_List` VALUES ('64', '3', 'resist', '1461', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('65', '3', 'chant', '1002', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('66', '3', 'chant', '1006', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('67', '3', 'chant', '1007', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('68', '3', 'chant', '1009', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('69', '3', 'chant', '1251', '2', '3', '1');
INSERT INTO `Buffer_List` VALUES ('70', '3', 'chant', '1252', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('71', '3', 'chant', '1253', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('72', '3', 'chant', '1284', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('73', '3', 'chant', '1308', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('74', '3', 'chant', '1309', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('75', '3', 'chant', '1310', '4', '3', '1');
INSERT INTO `Buffer_List` VALUES ('76', '3', 'chant', '1362', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('77', '3', 'special', '1499', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('78', '3', 'special', '1500', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('79', '3', 'special', '1501', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('80', '3', 'special', '1502', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('81', '3', 'special', '1503', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('82', '3', 'special', '1504', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('83', '3', 'special', '1519', '1', '3', '1');

INSERT INTO `Buffer_List` VALUES ('84', '4', 'others', '825', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('85', '4', 'others', '826', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('86', '4', 'others', '827', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('87', '4', 'others', '828', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('88', '4', 'others', '829', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('89', '4', 'others', '830', '1', '3', '1');

INSERT INTO `Buffer_List` VALUES ('90', '5', 'others', '834', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('91', '5', 'others', '1442', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('92', '5', 'others', '1443', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('93', '5', 'others', '1444', '1', '3', '1');

INSERT INTO `Buffer_List` VALUES ('94', '6', 'cubic', '67', '7', '3', '1');
INSERT INTO `Buffer_List` VALUES ('95', '6', 'cubic', '10', '8', '3', '1');
INSERT INTO `Buffer_List` VALUES ('96', '6', 'cubic', '22', '7', '3', '1');
INSERT INTO `Buffer_List` VALUES ('97', '6', 'cubic', '33', '8', '3', '1');
INSERT INTO `Buffer_List` VALUES ('98', '6', 'cubic', '278', '6', '3', '1');
INSERT INTO `Buffer_List` VALUES ('99', '6', 'cubic', '449', '4', '3', '1');
INSERT INTO `Buffer_List` VALUES ('100', '6', 'cubic', '1279', '9', '3', '1');
INSERT INTO `Buffer_List` VALUES ('101', '6', 'cubic', '1280', '9', '3', '1');
INSERT INTO `Buffer_List` VALUES ('102', '6', 'cubic', '1281', '9', '3', '1');
INSERT INTO `Buffer_List` VALUES ('103', '6', 'cubic', '1328', '8', '3', '0');
INSERT INTO `Buffer_List` VALUES ('104', '6', 'cubic', '1329', '9', '3', '0');
INSERT INTO `Buffer_List` VALUES ('105', '6', 'cubic', '1330', '8', '3', '0');
INSERT INTO `Buffer_List` VALUES ('106', '6', 'cubic', '779', '1', '3', '1');

INSERT INTO `Buffer_List` VALUES ('107', '7', 'special', '1062', '2', '2', '1');
INSERT INTO `Buffer_List` VALUES ('108', '7', 'special', '1355', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('109', '7', 'special', '1356', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('110', '7', 'special', '1357', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('111', '7', 'special', '1363', '1', '0', '1');
INSERT INTO `Buffer_List` VALUES ('112', '7', 'special', '1413', '1', '1', '1');
INSERT INTO `Buffer_List` VALUES ('114', '7', 'special', '1457', '1', '3', '0');
INSERT INTO `Buffer_List` VALUES ('115', '7', 'special', '4699', '13', '3', '1');
INSERT INTO `Buffer_List` VALUES ('116', '7', 'special', '4700', '13', '3', '1');
INSERT INTO `Buffer_List` VALUES ('117', '7', 'special', '4702', '13', '3', '1');
INSERT INTO `Buffer_List` VALUES ('118', '7', 'special', '4703', '13', '3', '1');

INSERT INTO `Buffer_List` VALUES ('119', '8', 'resist', '1032', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('120', '8', 'resist', '1033', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('121', '8', 'resist', '1035', '4', '2', '1');
INSERT INTO `Buffer_List` VALUES ('122', '8', 'resist', '1078', '6', '3', '1');
INSERT INTO `Buffer_List` VALUES ('123', '8', 'resist', '1182', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('124', '8', 'resist', '1189', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('125', '8', 'resist', '1191', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('126', '8', 'resist', '1259', '4', '3', '1');
INSERT INTO `Buffer_List` VALUES ('127', '8', 'resist', '1352', '1', '2', '1');
INSERT INTO `Buffer_List` VALUES ('128', '8', 'resist', '1353', '1', '2', '1');
INSERT INTO `Buffer_List` VALUES ('129', '8', 'resist', '1354', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('130', '8', 'resist', '1392', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('131', '8', 'resist', '1393', '3', '3', '1');

INSERT INTO `Buffer_List` VALUES ('132', '8', 'overlord', '1003', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('133', '8', 'overlord', '1004', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('134', '8', 'overlord', '1005', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('135', '8', 'overlord', '1008', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('136', '8', 'overlord', '1249', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('137', '8', 'overlord', '1250', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('138', '8', 'overlord', '1260', '3', '3', '1');
INSERT INTO `Buffer_List` VALUES ('139', '8', 'overlord', '1261', '2', '3', '1');
INSERT INTO `Buffer_List` VALUES ('140', '8', 'overlord', '1282', '2', '3', '1');
INSERT INTO `Buffer_List` VALUES ('141', '8', 'overlord', '1364', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('142', '8', 'overlord', '1365', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('143', '8', 'overlord', '1415', '1', '3', '1');
INSERT INTO `Buffer_List` VALUES ('144', '8', 'overlord', '1416', '1', '3', '1');