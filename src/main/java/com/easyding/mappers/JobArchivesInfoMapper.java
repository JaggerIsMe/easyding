package com.easyding.mappers;

import org.apache.ibatis.annotations.Param;

/**
 *  数据库操作接口
 */
public interface JobArchivesInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据JobUuidAndAge更新
	 */
	 Integer updateByJobUuidAndAge(@Param("bean") T t,@Param("jobUuid") String jobUuid,@Param("age") String age);


	/**
	 * 根据JobUuidAndAge删除
	 */
	 Integer deleteByJobUuidAndAge(@Param("jobUuid") String jobUuid,@Param("age") String age);


	/**
	 * 根据JobUuidAndAge获取对象
	 */
	 T selectByJobUuidAndAge(@Param("jobUuid") String jobUuid,@Param("age") String age);


}
