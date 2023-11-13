const baseDb = require('../../../common/base-db');

/**
 * 转换秒分时的方法
 * @param scheduleType
 * @returns {number}
 */
function getScheduleType(scheduleType){
    if(scheduleType == '秒'){
        return 1
    }else if (scheduleType == '分'){
        return 2
    }else if (scheduleType == '时'){
        return 3
    }
}

/**
 * 指定时间范围 设置定时任务
 */
class TbScheduleRangeInterverJobDao{

    constructor(dbPool) {
        this.pool = dbPool;
    }

    /**
     * 插入一个调度任务信息
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
        // 会话ID:xxxx
        // 开始时间:2023-09-12 12:00:00
        // 结束时间:2023-09-12 13:00:00
        // 周期类型:秒/分/时
        // 周期数值:1
        // 调度内容:测试定时+周期消息

        const sql = 'INSERT INTO tb_schedule_range_interver_job(tg_target_chat_id,tg_user_id,job_name,start_time,endt_time,schedule_type,schedule_value,CONTEXT) \n' +
            'VALUE(?,?,?,?,?,?,?,?)'
        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[targetChatId,tgUserId,jobName,startTime,endTime,getScheduleType(scheduleType),scheduleValue,context])
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

        const sql = `UPDATE tg_schedule001.tb_schedule_range_interver_job
        SET tg_target_chat_id = ?,
            tg_user_id = ?,
            job_name = ?,
            start_time = ?,
            endt_time = ?,
            schedule_type = ?,
            schedule_value = ?,
            context = ?
        WHERE id = ?;`
        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[targetChatId,tgUserId,jobName,startTime,endTime,scheduleType,scheduleValue,context,jobId])
    }

    /**
     * 通过ID查询
     * @param jobId
     * @returns {Promise<*>}
     */
    selectById(jobId){
        // 会话ID:xxxx

        const sql = `select
                         tg_target_chat_id ,
                         tg_user_id ,
                         job_name ,
                         start_time,
                         endt_time,
                         schedule_type,
                         schedule_value,
                         context,
                         is_enable,
                         create_time,
                         update_time

                     from tg_schedule001.tb_schedule_range_interver_job
                     WHERE id = ?;`

        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[jobId])
    }

    /**
     * 查询所有任务
     * @returns {Promise<*>}
     */
    selectList(){
        // 会话ID:xxxx

        const sql = `select
                         id,
                         tg_target_chat_id ,
                         tg_user_id ,
                         job_name ,
                         start_time,
                         endt_time,
                         schedule_type,
                         schedule_value,
                         context,
                         is_enable,
                         create_time,
                         update_time

                     from tg_schedule001.tb_schedule_range_interver_job;`

        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[])
    }

    /**
     * 通过ID删除任务
     * @param jobId
     * @returns {Promise<*>}
     */
    deleteById(jobId){
        // 会话ID:xxxx

        const sql = `delete from tg_schedule001.tb_schedule_range_interver_job
                     WHERE id = ?;`

        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[jobId])
    }

    /**
     * 删除所有任务
     * @returns {Promise<*>}
     */
    deleteAll(){
        // 会话ID:xxxx

        const sql = `delete from tg_schedule001.tb_schedule_range_interver_job;`

        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[])
    }

    /**
     * 更新单个任务启用状态
     * @param jobId
     * @param isEnable
     * @returns {Promise<*>}
     */
    updateIsEnableById(jobId,isEnable){
        // 会话ID:xxxx

        const sql = `UPDATE tg_schedule001.tb_schedule_range_interver_job
                     SET
                         is_enable = ?
                     WHERE id = ?;`
        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[isEnable,jobId])

    }

    /**
     * 启动单个任务
     * @returns {Promise<*>}
     */
    startJobById(jobId){
        // 会话ID:xxxx
        return this.updateIsEnableById(jobId,1)
    }

    /**
     * 停止单个任务
     * @returns {Promise<*>}
     */
    stopJobById(jobId){
        // 会话ID:xxxx
        return this.updateIsEnableById(jobId,0)
    }

    /**
     * 更新所有任务启用状态
     * @param isEnable
     * @returns {Promise<*>}
     */
    updateAllIsEnable(isEnable){
        // 会话ID:xxxx

        const sql = `UPDATE tg_schedule001.tb_schedule_range_interver_job
                     SET
                         is_enable = ?;`
        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[isEnable])

    }

    /**
     * 启动所有任务
     * @returns {Promise<*>}
     */
    startAllJob(){
        // 会话ID:xxxx
        return this.updateAllIsEnable(1)
    }

    /**
     * 停止所有任务
     * @returns {Promise<*>}
     */
    stopAllJob(){
        // 会话ID:xxxx
        return this.updateAllIsEnable(0)
    }




}

module.exports = {
    TbScheduleRangeInterverJobDao
}