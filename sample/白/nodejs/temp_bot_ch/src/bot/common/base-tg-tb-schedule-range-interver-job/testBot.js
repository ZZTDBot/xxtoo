//引入数据库组件
const mysql  = require('mysql');

//数据库连接池
const dbPool = mysql.createPool({
    host:"localhost",//如果是操作本地数据库，填写127.0.0.1；如果是远程服务器上的数据库填服务公网ip
    port:3306,//如果是操作本地数据库，填写127.0.0.1；如果是远程服务器上的数据库填服务公网ip
    user:"root",//通常为root
    password:"root",//通常为root
    database:"tg_schedule001",//你所要操作的数据库的名称（是你在mysql中建立的数据库）
    charset:'utf8mb4_general_ci'
})

//引入飞机相关包组价
const TelegramBot = require('node-telegram-bot-api');

// 测试token
const token = '5545477878:AAH6I_aeNFdIIftp2ZByx3vomrHws6ZlCiY';

// 创建一个机器人实例
const bot = new TelegramBot(token, {polling: true});

//引入飞机相关包组价
const tbScheduleRangeInterverJobTgBot = require('./tg/TbScheduleRangeInterverJobTgBot');

const tbScheduleRangeInterverJobTgBotImpl = new tbScheduleRangeInterverJobTgBot.TbScheduleRangeInterverJobTgBot(dbPool,bot);