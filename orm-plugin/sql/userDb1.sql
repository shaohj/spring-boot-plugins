CREATE DATABASE IF NOT EXISTS userDb1;

USE `userDb1`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
    `code` varchar(20) NOT NULL COMMENT '逻辑逐渐',
    `name` varchar(50) DEFAULT NULL COMMENT '姓名',
    `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `last_modify_by` varchar(20) DEFAULT NULL COMMENT '最后修改人',
    `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
    `delete_flag` varchar(1) DEFAULT NULL COMMENT '是否删除标志。Y:已删除|N:未删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into `user`
(`code`,`name`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`,`delete_flag`)
values
('10000','张三','10086','2020-01-01 16:59:33','10086','2020-01-01 16:59:33','N');
