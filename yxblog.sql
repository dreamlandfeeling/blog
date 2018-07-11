
DROP TABLE IF EXISTS `yx_user`;
CREATE TABLE `yx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0：禁用，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of yx_user
-- ----------------------------
INSERT INTO `yx_user` VALUES ('1', '青枫', 'admin', 'c3284d0f94606de1fd2af172aba15bf3', '1');
INSERT INTO `yx_user` VALUES ('2', 'test', 'test', 'fb469d7ef430b0baf0cab6c436e70375', '1');

DROP TABLE IF EXISTS `yx_comment`;
CREATE TABLE `yx_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) DEFAULT NULL COMMENT '被评论文章id',
  `cid` int(11) DEFAULT NULL COMMENT '被评论id',
  `author` varchar(20) DEFAULT NULL COMMENT '该评论作者',
  `email` varchar(20) DEFAULT NULL COMMENT '评论者邮箱',
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `status` int(11) DEFAULT NULL COMMENT '状态:0删除1正常2审批',
  `is_admin` int(11) DEFAULT NULL COMMENT '是否是博主  1为是',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='评论表';

DROP TABLE IF EXISTS `yx_article`;
CREATE TABLE `yx_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL COMMENT '作者名',
  `uid` int(11) DEFAULT NULL COMMENT '创建人id',
  `content` text COMMENT '内容',
  `status` int(11) DEFAULT NULL COMMENT '0为草稿1为发布',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='文章表';
