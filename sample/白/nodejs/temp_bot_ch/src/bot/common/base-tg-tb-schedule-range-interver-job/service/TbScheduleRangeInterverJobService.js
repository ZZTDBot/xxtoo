const tbScheduleRangeInterverJobDao = require('../dao/TbScheduleRangeInterverJobDao');

let selfDao;

/**
 * 时间范围-周期调度任务
 */
class TbScheduleRangeInterverJobService {

    constructor(dbPool) {
        this.tbScheduleRangeInterverJobDaoImpl = new tbScheduleRangeInterverJobDao.TbScheduleRangeInterverJobDao(dbPool)
        selfDao = this.tbScheduleRangeInterverJobDaoImpl;
    }

    /**
     * 创建一个调度任务信息
     * @param targetChatId   目标群ID
     * @param tgUserId       TG用户ID
     * @param jobName        任务名称
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @param scheduleType   调度类型
     * @param scheduleValue  调度周期
     * @param context        调度内容
     */
    insertJob(targetChatId,tgUserId,jobName,startTime,endTime,scheduleType,scheduleValue,context){
        return this.tbScheduleRangeInterverJobDaoImpl.insertJob(targetChatId,tgUserId,jobName,startTime,endTime,scheduleType,scheduleValue,context)
    }

    /**
     * 修改一个调度任务信息
     * @param jobId          任务ID
     * @param targetChatId   目标群ID
     * @param tgUserId       TG用户ID
     * @param jobName        任务名称
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @param scheduleType   调度类型
     * @param scheduleValue  调度周期
     * @param context        调度内容
     */
    updateJob(jobId,targetChatId,tgUserId,jobName,startTime,endTime,scheduleType,scheduleValue,context){
        // 会话ID:xxxx
        // 开始时间:2023-09-12 12:00:00
        // 结束时间:2023-09-12 13:00:00
        // 周期类型:秒/分/时
        // 周期数值:1
        // 调度内容:测试定时+周期消息
        return this.tbScheduleRangeInterverJobDaoImpl.updateJob(jobId,targetChatId,tgUserId,jobName,startTime,endTime,scheduleType,scheduleValue,context)
    }

    /**
     * 通过ID查询
     * @param jobId
     * @returns {Promise<*>}
     */
    selectById(jobId){
        // 会话ID:xxxx
        return this.tbScheduleRangeInterverJobDaoImpl.selectById(jobId)
    }

    /**
     * 查询所有任务
     * @returns {Promise<*>}
     */
    selectList(){
        // 会话ID:xxxx
        return this.tbScheduleRangeInterverJobDaoImpl.selectList()
    }

    /**
     * 查询所有任务
     * @returns {Promise<*>}
     */
    selectListReplyContext(){
        return new Promise((resolve, reject) => {

            let jobList = '--定时任务列表--\n'
            // 会话ID:xxxx

            this.selectList().then((selectListRes,selectListErr) => {
                if(selectListErr){
                    console.error('数据库异常!!!')
                    return
                }
                if(selectListRes && selectListRes.length > 0){
                    selectListRes.forEach(x => {
                        const jobContext =
                            `任务ID:${x.id}
会话ID:${x.tg_target_chat_id}
任务名称:${x.job_name}
开始时间:${x.start_time}
结束时间:${x.endt_time}
周期类型:${x.schedule_type}
周期数值:${x.schedule_value}
调度内容:${x.context}\n\n`;
                        jobList = jobList + jobContext
                    })

                    resolve(jobList)
                }

            })
        })


    }

    /**
     * 通过ID删除任务
     * @param jobId
     * @returns {Promise<*>}
     */
    deleteById(jobId){
        // 会话ID:xxxx
        return this.tbScheduleRangeInterverJobDaoImpl.deleteById(jobId)
    }

    /**
     * 删除所有任务
     * @returns {Promise<*>}
     */
    deleteAll(){
        return this.tbScheduleRangeInterverJobDaoImpl.deleteAll()
    }

    /**
     * 更新单个任务启用状态
     * @param jobId
     * @param isEnable
     * @returns {Promise<*>}
     */
    updateIsEnableById(jobId,isEnable){
        // 会话ID:xxxx
        return this.tbScheduleRangeInterverJobDaoImpl.updateIsEnableById(jobId,isEnable)
    }


    /**
     * 启动单个任务
     * @returns {Promise<*>}
     */
    startJobById(jobId){
        // 会话ID:xxxx
        return this.tbScheduleRangeInterverJobDaoImpl.startJobById(jobId)
    }

    /**
     * 停止单个任务
     * @returns {Promise<*>}
     */
    stopJobById(jobId){
        // 会话ID:xxxx
        return this.tbScheduleRangeInterverJobDaoImpl.updateIsEnableById(jobId,0)
    }

    /**
     * 更新所有任务启用状态
     * @param isEnable
     * @returns {Promise<*>}
     */
    updateAllIsEnable(isEnable){
        // 会话ID:xxxx
        return this.tbScheduleRangeInterverJobDaoImpl.updateAllIsEnable(isEnable)
    }

    /**
     * 启动所有任务
     * @returns {Promise<*>}
     */
    startAllJob(){
        // 会话ID:xxxx
        return this.tbScheduleRangeInterverJobDaoImpl.updateAllIsEnable(1)
    }

    /**
     * 停止所有任务
     * @returns {Promise<*>}
     */
    stopAllJob(){
        // 会话ID:xxxx
        return this.tbScheduleRangeInterverJobDaoImpl.updateAllIsEnable(0)
    }

}

module .exports = {
    TbScheduleRangeInterverJobService
}