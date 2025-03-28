DROP TABLE IF EXISTS `Buffer_Vip_List`;
-- DROP TABLE IF EXISTS `Buffer_Vip_Scheme_List`;
-- DROP TABLE IF EXISTS `Buffer_Vip_Scheme_Player`;

CREATE TABLE `Buffer_Vip_List` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buff_class` int(2) DEFAULT NULL,
  `buffType` varchar(10) DEFAULT NULL,
  `buffId` int(5) DEFAULT '0',
  `buffLevel` int(5) DEFAULT NULL,
  `forClass` tinyint(1) DEFAULT NULL,
  `canUse` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=136;

INSERT INTO `Buffer_Vip_List` VALUES ('1', '0', 'buff', '1036', '130', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('2', '0', 'buff', '1040', '130', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('3', '0', 'buff', '1043', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('4', '0', 'buff', '1044', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('5', '0', 'buff', '1045', '130', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('6', '0', 'buff', '1047', '230', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('7', '0', 'buff', '1048', '130', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('8', '0', 'buff', '1059', '130', '1', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('9', '0', 'buff', '1068', '130', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('10', '0', 'buff', '1077', '130', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('11', '0', 'buff', '1085', '130', '1', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('12', '0', 'buff', '1086', '130', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('13', '0', 'buff', '1087', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('14', '0', 'buff', '1204', '130', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('15', '0', 'buff', '1240', '130', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('16', '0', 'buff', '1242', '130', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('17', '0', 'buff', '1243', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('18', '0', 'buff', '1257', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('19', '0', 'buff', '1268', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('20', '0', 'buff', '1303', '130', '1', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('21', '0', 'buff', '1304', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('22', '0', 'buff', '1307', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('23', '0', 'buff', '1311', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('24', '0', 'buff', '1397', '430', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('25', '0', 'buff', '1460', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('26', '0', 'buff', '1232', '329', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('27', '0', 'buff', '1238', '330', '3', '0');
INSERT INTO `Buffer_Vip_List` VALUES ('28', '0', 'special', '1323', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('29', '0', 'special', '1388', '230', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('30', '0', 'special', '1389', '230', '1', '1');

INSERT INTO `Buffer_Vip_List` VALUES ('31', '1', 'song', '264', '130', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('32', '1', 'song', '265', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('33', '1', 'song', '266', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('34', '1', 'song', '267', '130', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('35', '1', 'song', '268', '130', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('36', '1', 'song', '269', '130', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('37', '1', 'song', '270', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('38', '1', 'song', '304', '130', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('39', '1', 'song', '305', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('40', '1', 'song', '306', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('41', '1', 'song', '308', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('42', '1', 'song', '349', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('43', '1', 'song', '363', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('44', '1', 'song', '364', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('45', '1', 'song', '529', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('46', '1', 'song', '764', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('47', '1', 'song', '914', '1', '3', '1');

INSERT INTO `Buffer_Vip_List` VALUES ('48', '2', 'dance', '271', '130', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('49', '2', 'dance', '272', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('50', '2', 'dance', '273', '130', '1', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('51', '2', 'dance', '274', '130', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('52', '2', 'dance', '275', '130', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('53', '2', 'dance', '276', '130', '1', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('54', '2', 'dance', '277', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('55', '2', 'dance', '307', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('56', '2', 'dance', '309', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('57', '2', 'dance', '310', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('58', '2', 'dance', '311', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('59', '2', 'dance', '365', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('60', '2', 'dance', '366', '115', '3', '0');
INSERT INTO `Buffer_Vip_List` VALUES ('61', '2', 'dance', '530', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('62', '2', 'dance', '765', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('63', '2', 'dance', '915', '1', '3', '1');

INSERT INTO `Buffer_Vip_List` VALUES ('64', '3', 'resist', '1461', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('65', '3', 'chant', '1002', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('66', '3', 'chant', '1006', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('67', '3', 'chant', '1007', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('68', '3', 'chant', '1009', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('69', '3', 'chant', '1251', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('70', '3', 'chant', '1252', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('71', '3', 'chant', '1253', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('72', '3', 'chant', '1284', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('73', '3', 'chant', '1308', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('74', '3', 'chant', '1309', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('75', '3', 'chant', '1310', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('76', '3', 'chant', '1362', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('77', '3', 'special', '1499', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('78', '3', 'special', '1500', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('79', '3', 'special', '1501', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('80', '3', 'special', '1502', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('81', '3', 'special', '1503', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('82', '3', 'special', '1504', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('83', '3', 'special', '1519', '1', '3', '1');

-- Dwarf
INSERT INTO `Buffer_Vip_List` VALUES ('84', '4', 'others', '825', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('85', '4', 'others', '826', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('86', '4', 'others', '827', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('87', '4', 'others', '828', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('88', '4', 'others', '829', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('89', '4', 'others', '830', '1', '3', '1');

-- Kamael
INSERT INTO `Buffer_Vip_List` VALUES ('90', '5', 'others', '834', '1', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('91', '5', 'others', '1442', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('92', '5', 'others', '1443', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('93', '5', 'others', '1444', '130', '3', '1');

INSERT INTO `Buffer_Vip_List` VALUES ('94', '6', 'cubic', '67', '7', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('95', '6', 'cubic', '10', '8', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('96', '6', 'cubic', '22', '7', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('97', '6', 'cubic', '33', '8', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('98', '6', 'cubic', '278', '6', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('99', '6', 'cubic', '449', '4', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('100', '6', 'cubic', '1279', '9', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('101', '6', 'cubic', '1280', '9', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('102', '6', 'cubic', '1281', '9', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('103', '6', 'cubic', '1328', '8', '3', '0');
INSERT INTO `Buffer_Vip_List` VALUES ('104', '6', 'cubic', '1329', '9', '3', '0');
INSERT INTO `Buffer_Vip_List` VALUES ('105', '6', 'cubic', '1330', '8', '3', '0');
INSERT INTO `Buffer_Vip_List` VALUES ('106', '6', 'cubic', '779', '1', '3', '1');

INSERT INTO `Buffer_Vip_List` VALUES ('107', '7', 'special', '1062', '2', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('108', '7', 'special', '1355', '315', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('109', '7', 'special', '1356', '315', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('110', '7', 'special', '1357', '315', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('111', '7', 'special', '1363', '315', '0', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('112', '7', 'special', '1413', '315', '1', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('114', '7', 'special', '1457', '315', '3', '0');
INSERT INTO `Buffer_Vip_List` VALUES ('115', '7', 'special', '4699', '13', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('116', '7', 'special', '4700', '13', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('117', '7', 'special', '4702', '13', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('118', '7', 'special', '4703', '13', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('119', '7', 'special', '1411', '315', '3', '1');

INSERT INTO `Buffer_Vip_List` VALUES ('146', '8', 'resist', '1548', '330', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('120', '8', 'resist', '1032', '330', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('121', '8', 'resist', '1033', '330', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('122', '8', 'resist', '1035', '330', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('123', '8', 'resist', '1078', '330', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('124', '8', 'resist', '1182', '330', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('125', '8', 'resist', '1189', '330', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('126', '8', 'resist', '1191', '330', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('127', '8', 'resist', '1259', '330', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('128', '8', 'resist', '1352', '315', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('129', '8', 'resist', '1353', '330', '2', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('130', '8', 'resist', '1354', '315', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('131', '8', 'resist', '1392', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('132', '8', 'resist', '1393', '130', '3', '1');

INSERT INTO `Buffer_Vip_List` VALUES ('133', '3', 'chant', '1003', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('134', '3', 'chant', '1004', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('135', '3', 'chant', '1005', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('136', '3', 'chant', '1008', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('137', '3', 'chant', '1249', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('138', '3', 'chant', '1250', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('139', '3', 'chant', '1260', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('140', '3', 'chant', '1261', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('141', '3', 'chant', '1282', '130', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('142', '3', 'chant', '1364', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('143', '3', 'chant', '1365', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('144', '3', 'chant', '1415', '115', '3', '1');
INSERT INTO `Buffer_Vip_List` VALUES ('145', '3', 'chant', '1416', '115', '3', '1');