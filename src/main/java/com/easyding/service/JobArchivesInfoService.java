package com.easyding.service;

import java.util.List;

import com.easyding.entity.query.JobArchivesInfoQuery;
import com.easyding.entity.po.indeedPo.JobArchivesInfo;
import com.easyding.entity.vo.PaginationResultVO;


/**
 *  业务接口
 */
public interface JobArchivesInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<JobArchivesInfo> findListByParam(JobArchivesInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(JobArchivesInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<JobArchivesInfo> findListByPage(JobArchivesInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(JobArchivesInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<JobArchivesInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<JobArchivesInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(JobArchivesInfo bean,JobArchivesInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(JobArchivesInfoQuery param);

	/**
	 * 根据JobUuidAndAge查询对象
	 */
	JobArchivesInfo getJobArchivesInfoByJobUuidAndAge(String jobUuid,String age);


	/**
	 * 根据JobUuidAndAge修改
	 */
	Integer updateJobArchivesInfoByJobUuidAndAge(JobArchivesInfo bean,String jobUuid,String age);


	/**
	 * 根据JobUuidAndAge删除
	 */
	Integer deleteJobArchivesInfoByJobUuidAndAge(String jobUuid,String age);

	/**
	 * 老年代升级到新生代
	 */
	void growJobArchivesAge();

}