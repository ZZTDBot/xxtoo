const fs = require("fs");
const fsExtra = require("fs-extra");
const path = require("path");

const writeFile = (filepath, content) => {
    fs.writeFile(filepath, content, err => {
        if(err) {
            console.error(err);
            return;
        }
    });
};

const appendFile = (filepath, content) => {
    fs.appendFile(filepath, content, err => {
        if(err) {
            console.error(err);
            return;
        }
    });
};

const readFile = async (filepath) =>{
    const content = await fs.promises.readFile(filepath, "utf8")
    .catch (err => console.error("failed to read file", err));
    return content;
};

const getFileInfo = async (filepath) => {
    const stat = await fs.promises.stat(filepath)
    .catch(err => console.error("failed to read file", err));
    return stat;
};

const listFiles = async (folderpath) =>{
    const fileList = await fs.promises.readdir(folderpath)
    .catch(err => console.error("failed to read dir", err));
    return fileList;
};

const copyFolder = async (folderpathSrc, folderpathDest) =>{
    await fsExtra.copy(folderpathSrc, folderpathDest)
    .catch(err => console.error("failed to copy dir", err));
};

const removeFolder = async (folderpath) =>{
    await fsExtra.remove(folderpath)
    .catch(err => console.error("failed to remove dir", err));
}

const parsePath = (filepath) => {
    return {
        dirname: path.dirname(filepath),
        basename: path.basename(filepath),
        extname: path.extname(filepath)
    };
};

//writeFile("./assets/test.txt", "codewithlarry helps you improve full stack skills");
//writeFile("./assets/test.txt", "小林方程式，你的全栈加速器");
//appendFile("./assets/test.txt", "\nyou will become a senior developer soon.\n你会很快成为大牛。");
/*
readFile("./assets/test.txt").then(content =>{
    console.log("content="+content);
});

getFileInfo("./assets/test.txt").then(stat=>{
    console.log("file size = "+ stat.size);
    console.log("Is a file = "+ stat.isFile());
});

listFiles("./assets").then(fileList =>{
    console.log(JSON.stringify(fileList, null, '  '));
});

copyFolder("./assets","./assets-bak");
*/
//removeFolder("./assets-bak");

const pathobj = parsePath("/user/larry/test.txt");
console.log(JSON.stringify(pathobj, null, '  '));
