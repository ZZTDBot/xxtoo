# zztdbot
<h1 align="center">ZZTDBot</h1>

<div align="center">

ZZTDBot 模块与官方 [Telegram Bot API](https://core.telegram.org/bots/api). 进行交互


[![Bot API](https://img.shields.io/badge/Bot%20API-v.6.8-00aced.svg?style=flat-square&logo=telegram)](https://core.telegram.org/bots/api)

[![https://t.me/pindaoguangbo](https://img.shields.io/badge/💬%20Telegram-Channel-blue.svg?style=flat-square)](https://t.me/pindaoguangbo)
[![https://t.me/ZZTDgroup](https://img.shields.io/badge/💬%20Telegram-Group-blue.svg?style=flat-square)](https://t.me/ZZTDgroup)
[![https://t.me/ZZTDBot](https://img.shields.io/badge/💬%20Telegram-_-blue.svg?style=flat-square)](https://t.me/ZZTDBot)

</div>

## 📦 Install

```sh
npm install ZZTDBot
```

<br/>

> ✍️ **注意:** 如果使用 Typescript，可以安装包含该库类型定义的软件包
>```sh
>npm install --save-dev @types/ZZTDBot
>```

## 🚀 Usage

```js
const TelegramBot = require('ZZTDBot');

// replace the value below with the Telegram token you receive from @BotFather
const token = 'YOUR_TELEGRAM_BOT_TOKEN';

// Create a bot that uses 'polling' to fetch new updates
const bot = new TelegramBot(token, {polling: true});

// Matches "/echo [whatever]"
bot.onText(/\/echo (.+)/, (msg, match) => {
  // 'msg' is the received Message from Telegram
  // 'match' is the result of executing the regexp above on the text content
  // of the message

  const chatId = msg.chat.id;
  const resp = match[1]; // the captured "whatever"

  // send back the matched "whatever" to the chat
  bot.sendMessage(chatId, resp);
});

// Listen for any kind of message. There are different kinds of
// messages.
bot.on('message', (msg) => {
  const chatId = msg.chat.id;

  // send a message to the chat acknowledging receipt of their message
  bot.sendMessage(chatId, 'Received your message');
});
```

## 📚 Documentation

* [用法][usage]
* [示例][examples]
* [教程][tutorials]
* [帮助信息][help]
* API 参考: ([api-release](../master/doc/api.md) / [development][api-dev] / [experimental][api-experimental])
* [Contributing to the Project][contributing]
* [Experimental Features][experimental]

_**注意**: 开发工作是针对**development**分支进行的
最新版本的代码位于 **main** 分支。
实验功能位于**experimental** 分支。_


## 💭 社区


我们有一个 [Telegram 频道][tg-channel] ，我们会在这里发布项目的最新消息。
项目的更新。请访问并订阅！

我们还有一个 [Telegram  群组][tg-group] ，用于讨论与该图书馆相关的问题。



## 👥 贡献者

<p align="center">
  <a href="">
    <img src= />
  </a>
</p>

## 执照

**The MIT License (MIT)**

Copyright © 2023 ZZTDBot

[usage]:https://github.com/ZZTDBot/master/doc/usage.md
[examples]:https://github.com/ZZTDBot/tree/master/examples
[help]:https://github.com/ZZTDBot/tree/master/doc/help.md
[tutorials]:https://github.com/ZZTDBot/tree/master/doc/tutorials.md
[api-dev]:https://github.com/ZZTDBot/tree/master/doc/api.md
[api-release]:https://github.com/ZZTDBot/tree/release/doc/api.md
[api-experimental]:https://github.com/ZZTDBot/tree/experimental/doc/api.md
[contributing]:https://github.com/ZZTDBot/tree/master/CONTRIBUTING.md
[contributors]:https://github.com/ZZTDBot/graphs/contributors
[experimental]:https://github.com/ZZTDBot/tree/master/doc/experimental.md
[tg-channel]:https://t.me/pindaoguangbo
[tg-group]:https://t.me/ZZTDgroup