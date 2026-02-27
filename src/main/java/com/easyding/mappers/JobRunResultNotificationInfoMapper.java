package com.easyding.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 任务执行结果通知表 数据库操作接口
 */
public interface JobRunResultNotificationInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据JobUuidAndWorkUuid更新
	 */
	 Integer updateByJobUuidAndWorkUuid(@Param("bean") T t,@Param("jobUuid") String jobUuid,@Param("workUuid") String workUuid);


	/**
	 * 根据JobUuidAndWorkUuid删除
	 */
	 Integer deleteByJobUuidAndWorkUuid(@Param("jobUuid") String jobUuid,@Param("workUuid") String workUuid);


	/**
	 * 根据JobUuidAndWorkUuid获取对象
	 */
	 T selectByJobUuidAndWorkUuid(@Param("jobUuid") String jobUuid,@Param("workUuid") String workUuid);


}
