USE aspiredemo;

CREATE TABLE  `program` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT, /* id自增长 */
    `name` varchar(100) NOT NULL,
    `duration` int(5),
    `md5` varchar(100) NOT NULL,
    `extension` varchar(10) NOT NULL,
    `size` varchar(100) NOT NULL,
    `recordDateTime` datetime,
    PRIMARY KEY (`id`)                      /* id为主键 */
    ) ENGINE=MyISAM DEFAULT CHARSET=utf8;