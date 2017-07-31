
use AspireDemo;

CREATE TABLE  `tempDevice` (
    `deviceId` varchar(50) not null,
    `deviceName` varchar(100) not null,
    `firmVersion` varchar(50) not null,
    `recordDateTime` datetime,
    PRIMARY KEY (`deviceId`)
    )ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `device` (
    `deviceId` varchar(50) not null,
    `deviceName` varchar(100) not null,
    `firmVersion` varchar(50) not null,
    `recordDateTime` datetime,
    `deviceStatus` int(2) not null,
    PRIMARY KEY (`deviceId`)
    )ENGINE=MyISAM DEFAULT CHARSET=utf8;