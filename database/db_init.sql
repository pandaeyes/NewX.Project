-- --------------------------------------------------------------------
-- 系统建表脚本 不可重复执行
-- --------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `sys_right` (
    `id` varchar(50) NOT NULL COMMENT '权限ID',
    `rightname` varchar(100) NOT NULL COMMENT '权限名称',
    `url` varchar(100) NOT NULL COMMENT '链接地址',
    `rightsortno` varchar(20) NOT NULL COMMENT '排序号',
    `parentid` varchar(50) NOT NULL COMMENT '上级权限主键',
    `memo` varchar(255) NOT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

CREATE TABLE if NOT EXISTS sys_user (
    id int(11) NOT NULL COMMENT '用户ID',
    account varchar(60) NOT NULL COMMENT '账号',
    `name` varchar(60) NOT NULL COMMENT '名称',
    passwd varchar(32) NOT NULL COMMENT '密码(MD5)',
    orgcode varchar(14) not NULL COMMENT '所属机构',
    status varchar(5) not NULL COMMENT '状态',
    last_visit int(10) unsigned NOT NULL COMMENT '最后登录时间',
    last_ip varchar(15) NOT NULL COMMENT '最后登录点IP',
    `desc` varchar(100) NOT NULL COMMENT '描述',
    PRIMARY KEY (id),
    KEY account (account)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表'

CREATE TABLE if NOT EXISTS sys_demo (
    id int(11) NOT NULL COMMENT '用户ID',
    account varchar(60) COMMENT '账号',
    `name` varchar(60) COMMENT '名称',
    passwd varchar(32) COMMENT '密码(MD5)',
    orgcode varchar(14) COMMENT '所属机构',
    status varchar(5) COMMENT '状态',
    last_visit int(10) COMMENT '最后登录时间',
    last_ip varchar(15) COMMENT '最后登录点IP',
    `desc` varchar(100) COMMENT '描述',
    PRIMARY KEY (id),
    KEY account (account)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='demo用户表'