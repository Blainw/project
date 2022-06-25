/*
 Navicat Premium Data Transfer

 Source Server         : wdm
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : excel

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 28/04/2022 15:34:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `SID` int(0) NOT NULL AUTO_INCREMENT,
  `SName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `RName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Pwd` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SID`) USING BTREE,
  UNIQUE INDEX `SName`(`SName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'root', '王德明', '123456');
INSERT INTO `admin` VALUES (2, 'admin', '张三', '123456');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `CID` int(0) NOT NULL AUTO_INCREMENT,
  `CNO` int(0) NOT NULL,
  `CName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CPhase` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CIntroduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CTime` date NOT NULL,
  `LNum` int(0) NOT NULL,
  PRIMARY KEY (`CID`) USING BTREE,
  UNIQUE INDEX `CNO`(`CNO`) USING BTREE,
  INDEX `LNum`(`LNum`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`LNum`) REFERENCES `lecturer` (`LNum`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10045 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (10001, 22001, 'AND函数', 'excel中级', '主要功能：返回逻辑值：如果所有参数值均为逻辑“真(TRUE)”，则返回逻辑“真(TRUE)”，反之返回逻辑“假(FALSE)”。', '2022-03-19', 1001);
INSERT INTO `course` VALUES (10003, 22002, 'COLUMN函数', 'excel中级', '\n主要功能：显⽰所引⽤单元格的列标号值。使⽤格式：COLUMN(reference)参数说明：reference为引⽤的单元格。', '2022-03-15', 1004);
INSERT INTO `course` VALUES (10004, 22003, 'CONCATENATE函数', 'excel高级', '主要功能：将多个字符⽂本或单元格中的数据连接在⼀起，显⽰在⼀个单元格中。使⽤格式：CONCATENATE(Text1，Text……)', '2022-04-05', 1003);
INSERT INTO `course` VALUES (10005, 22004, 'COUNTIF函数', 'excel中级', '主要功能：统计某个单元格区域中符合指定条件的单元格数⽬。使⽤格式：COUNTIF(Range,Criteria)', '2022-04-05', 1002);
INSERT INTO `course` VALUES (10006, 22005, 'AVERAGE函数', 'excel初级', '主要功能：求出所有参数的算术平均值。使⽤格式：AVERAGE(number1,number2,……)', '2022-05-27', 1001);
INSERT INTO `course` VALUES (10007, 22006, 'DATE函数', 'excel高级', '\n主要功能：给出指定数值的⽇期。使⽤格式：DATE(year,month,day)', '2022-04-05', 1001);
INSERT INTO `course` VALUES (10009, 22008, 'DAY函数', 'excel高级', '\n主要功能：求出指定⽇期或引⽤单元格中的⽇期的天数。使⽤格式：DAY(serial_number)', '2022-04-07', 1001);
INSERT INTO `course` VALUES (10010, 22009, 'DCOUNT函数', 'excel高级', '主要功能：返回数据库或列表的列中满⾜指定条件并且包含数字的单元格数⽬。', '2022-04-06', 1001);
INSERT INTO `course` VALUES (10023, 22011, 'FREQUENCY函数', 'excel高级', '主要功能：以⼀列垂直数组返回某个区域中数据的频率分布。使⽤格式：FREQUENCY(data_array,bins_array)', '2022-04-13', 1001);
INSERT INTO `course` VALUES (10024, 22022, 'ABS函数', 'excel中级', '主要功能：求出相应数字的绝对值。使⽤格式：ABS(number)', '2022-04-08', 1001);
INSERT INTO `course` VALUES (10025, 22010, 'IF函数', 'excel初级', '主要功能：根据对指定条件的逻辑判断的真假结果，返回相对应的内容。使⽤格式：=IF(Logical,Value_if_true,Value_i', '2022-04-06', 1002);
INSERT INTO `course` VALUES (10026, 22012, 'INDEX函数', 'excel初级', '主要功能：返回列表或数组中的元素值，此元素由⾏序号和列序号的索引值进⾏确定。使⽤格式：INDEX(array,row_num,column_', '2022-04-15', 1002);
INSERT INTO `course` VALUES (10027, 22013, 'INT函数', 'excel初级', '主要功能：将数值向下取整为最接近的整数。使⽤格式：INT(number)', '2022-03-31', 1002);
INSERT INTO `course` VALUES (10028, 22014, 'ISERROR函数', 'excel高级', '主要功能：⽤于测试函数式返回的数值是否有错。如果有错，该函数返回TRUE，反之返回FALSE。使⽤格式：ISERROR(value)', '2022-04-15', 1002);
INSERT INTO `course` VALUES (10029, 22015, 'LEFT函数', 'excel高级', '主要功能：从⼀个⽂本字符串的第⼀个字符开始，截取指定数⽬的字符。使⽤格式：LEFT(text,num_chars)', '2022-04-22', 1002);
INSERT INTO `course` VALUES (10030, 22016, 'LEN函数', 'excel中级', '主要功能：统计⽂本字符串中字符数⽬。使⽤格式：LEN(text)', '2022-04-27', 1002);
INSERT INTO `course` VALUES (10031, 22017, 'MATCH', 'excel高级', '主要功能：返回在指定⽅式下与指定数值匹配的数组中元素的相应位置。', '2022-04-07', 1002);
INSERT INTO `course` VALUES (10032, 22018, 'MAX函数', 'excel初级', '主要功能：求出⼀组数中的最⼤值。', '2022-05-13', 1002);
INSERT INTO `course` VALUES (10033, 22019, 'MID函数', 'excel中级', '主要功能：从⼀个⽂本字符串的指定位置开始，截取指定数⽬的字符。使⽤格式：MID(text,start_num,num_chars)', '2022-05-02', 1002);
INSERT INTO `course` VALUES (10034, 22020, 'MIN函数', 'excel中级', '主要功能：求出⼀组数中的最⼩值。使⽤格式：MIN(number1,number2……)', '2022-04-15', 1003);
INSERT INTO `course` VALUES (10035, 22021, 'MOD函数', 'excel中级', '主要功能：求出两数相除的余数。使⽤格式：MOD(number,divisor)', '2022-04-14', 1003);
INSERT INTO `course` VALUES (10036, 22023, 'MONTH函数', 'excel高级', '主要功能：求出指定⽇期或引⽤单元格中的⽇期的⽉份。使⽤格式：MONTH(serial_number)', '2022-04-14', 1003);
INSERT INTO `course` VALUES (10037, 22024, 'NOW函数', 'excel初级', '主要功能：给出当前系统⽇期和时间。使⽤格式：NOW()', '2022-04-08', 1003);
INSERT INTO `course` VALUES (10038, 22025, 'OR函数', 'excel高级', '主要功能：返回逻辑值，仅当所有参数值均为逻辑“假(FALSE)”时返回函数结果逻辑“假(FALSE)”，否则都返回逻辑“真(TRUE)”。', '2022-04-20', 1003);
INSERT INTO `course` VALUES (10039, 22026, 'RANK函数', 'excel中级', '主要功能：返回某⼀数值在⼀列数值中的相对于其他数值的排位。使⽤格式：RANK(Number,ref,order)', '2022-04-17', 1003);
INSERT INTO `course` VALUES (10040, 22027, 'RIGHT函数', 'excel高级', '主要功能：从⼀个⽂本字符串的最后⼀个字符开始，截取指定数⽬的字符。使⽤格式：RIGHT(text,num_chars)', '2022-04-23', 1003);
INSERT INTO `course` VALUES (10041, 22028, 'SUBTOTAL函数', 'excel高级', '主要功能：返回列表或数据库中的分类汇总。使⽤格式：SUBTOTAL(function_num, ref1, ref2, ...)', '2022-04-23', 1003);
INSERT INTO `course` VALUES (10045, 22029, 'OR函数', 'excel高级', '主要功能：返回逻辑值，仅当所有参数值均为逻辑“假(FALSE)”时返回函数结果逻辑“假(FALSE)”，否则都返回逻辑“真(TRUE)”。', '2022-04-15', 1001);

-- ----------------------------
-- Table structure for lecturer
-- ----------------------------
DROP TABLE IF EXISTS `lecturer`;
CREATE TABLE `lecturer`  (
  `LID` int(0) NOT NULL AUTO_INCREMENT,
  `LNum` int(0) NOT NULL,
  `LName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Pwd` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LEmail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`LID`) USING BTREE,
  UNIQUE INDEX `LNum`(`LNum`) USING BTREE,
  UNIQUE INDEX `LEmail`(`LEmail`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lecturer
-- ----------------------------
INSERT INTO `lecturer` VALUES (1, 1001, '王德明', '123456', '1831486177@163.com');
INSERT INTO `lecturer` VALUES (2, 1002, '张三', '123456', '5555@163.com');
INSERT INTO `lecturer` VALUES (3, 1003, '赵六', '123456', 'shihao@163.com');
INSERT INTO `lecturer` VALUES (4, 1004, '王五', '123456', '111@163.com');
INSERT INTO `lecturer` VALUES (10, 1005, '法外狂徒', '123456', 'fwkt@qq.com');
INSERT INTO `lecturer` VALUES (11, 1006, '路明非', '123456', 'lmf@QQ.com');
INSERT INTO `lecturer` VALUES (12, 1007, '张飞', '123456', 'zf@163.com');
INSERT INTO `lecturer` VALUES (15, 1008, '赵七', '123456', '1831486177@cc.com');

SET FOREIGN_KEY_CHECKS = 1;
