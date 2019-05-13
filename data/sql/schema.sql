DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_no` varchar(100) DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `pass_word` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `full_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `cred_type` varchar(20) DEFAULT NULL COMMENT '证件类型',
  `cred_num` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `profession` varchar(50) DEFAULT NULL COMMENT '职业',
  `id_card_front` varchar(100) DEFAULT NULL COMMENT '身份证正面照',
  `id_card_back` varchar(100) DEFAULT NULL COMMENT '身份证反面照',
  `id_card_hand` varchar(100) DEFAULT NULL COMMENT '手持身份证照',
  `other_data` varchar(100) DEFAULT NULL COMMENT '其他资料',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_admin` char(1) NOT NULL DEFAULT 'N' COMMENT '是否为管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
