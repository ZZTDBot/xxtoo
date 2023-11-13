const TronWeb = require('tronweb')
const botconfig = require('../config/bot/botconfig');

const HttpProvider = TronWeb.providers.HttpProvider;
const fullNode = new HttpProvider("https://api.trongrid.io");
const solidityNode = new HttpProvider("https://api.trongrid.io");
const eventServer = new HttpProvider("https://api.trongrid.io");

// 操作波场账户的对象
const tronWeb = new TronWeb(fullNode,solidityNode,eventServer,botconfig.privateKey);
// 私钥转的地址
const fromPrivateKeyAddress = tronWeb.address.fromPrivateKey(botconfig.privateKey);


/**
 * trx 转账
 * @param toTrxAddress   目标地址
 * @param trxCount       trx数量
 * @param privateKey     私钥
 * @returns {Promise<unknown>}
 */
function sendTrxTransaction(toTrxAddress,trxCount,privateKey) {
    return new Promise((resolve, reject) => {
        tronWeb.trx.sendTransaction(toTrxAddress, trxCount * 1000000,privateKey)
            .then((res,err) => {
                if(err){
                    console.log( "trx转账失败:" + err.message)
                    reject(err)
                    return;
                }
                console.log( "trx转账成功:" + JSON.stringify(res))
                resolve(res)

            })
    })
    //     .catch(error => {
    //     console.error(`trx转账失败......error:`,error)
    // })
}
// sendTrxTransaction('TWFfiQtG8JgWeLA7DDPQuBGBPuHjpkXhF4',1,'FF107E4371FA0F378EC4F2F57CEE9E8A1D79DB3A50726AC56519FFE7D294EBF4')
//     .then((res,err) => {
//         console.log(res)
// })
// const fromPrivateKey = tronWeb.address.fromPrivateKey('FF107E4371FA0F378EC4F2F57CEE9E8A1D79DB3A50726AC56519FFE7D294EBF4');
// console.log(fromPrivateKey)

// {
//     "result":true,
//     "txid":"ce1aaa0ca68f32c40b176ea277a09207fc2606fcb2bd39ec379dea348720b0b6",
//     "transaction":{
//     "visible":false,
//         "txID":"ce1aaa0ca68f32c40b176ea277a09207fc2606fcb2bd39ec379dea348720b0b6",
//         "raw_data":{
//         "contract":[
//             {
//                 "parameter":{
//                     "value":{
//                         "amount":1,
//                         "owner_address":"411dd7ebda445a2636e0b7862e08aa5aa88abc21f7",
//                         "to_address":"41de7f784e827252fdb49b0c92844f79c026c2af9a"
//                     },
//                     "type_url":"type.googleapis.com/protocol.TransferContract"
//                 },
//                 "type":"TransferContract"
//             }
//         ],
//             "ref_block_bytes":"762e",
//             "ref_block_hash":"c3089c748dcec29e",
//             "expiration":1683272418000,
//             "timestamp":1683272359610
//     },
//     "raw_data_hex":"0a02762e2208c3089c748dcec29e40d085ebd6fe305a65080112610a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412300a15411dd7ebda445a2636e0b7862e08aa5aa88abc21f7121541de7f784e827252fdb49b0c92844f79c026c2af9a180170babde7d6fe30",
//         "signature":[
//         "839b8c8dcf5daaf10b4a0ac53c9c85bb3e9e76e63ca66da820243f79efa7f0ab8ba9f32cabe6055d3dcd82f96ffa77f9103526d471efda383fb7dd4910b0109501"
//     ]
// }
// }



/**
 * trc20转账方法
 * @param trc20ContractAddress    合约地址
 * @param toAddress               目标账户地址
 * @param amount                  金额
 * @returns {Promise<*>}
 */
async function triggerSmartContractTransfer(trc20ContractAddress,toAddress,amount) {
    try {
        let contract = await tronWeb.contract().at(trc20ContractAddress);
        //Use send to execute a non-pure or modify smart contract method on a given smart contract that modify or change values on the blockchain.
        // These methods consume resources(bandwidth and energy) to perform as the changes need to be broadcasted out to the network.
        let result = await contract.transfer(
            toAddress, //address _to
            amount * 1000000   //amount
        ).send({
            feeLimit: 15000000
        })
        //     .then(output => {
        //     console.log('- Output:', output, '\n');
        // });
        console.log('result: ', result);
        return result
    } catch(error) {
        console.error("trigger smart contract error",error)
    }
}
// triggerSmartContractTransfer('TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t','TWFfiQtG8JgWeLA7DDPQuBGBPuHjpkXhF4',0.05).then((result,error) => {
//     if (error){
//         console.error("trigger smart contract error",error)
//     }
//     console.log('result: ', result);
// })

/**
 * 创建账户的方法
 */
function createAccount(){
    tronWeb.createAccount().then((res,err) => {
        if(err){
            console.log(err.message)
        }
        console.log(JSON.stringify(res))
    });
}
// createAccount()


module.exports = {
    sendTrxTransaction,
    triggerSmartContractTransfer,
    fromPrivateKeyAddress
}