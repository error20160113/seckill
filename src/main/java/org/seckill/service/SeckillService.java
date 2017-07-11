package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

/**
 * 业务接口:站在使用者角度设计接口
 * 方法定义粒度,参数,返回类型
 * @author error
 *
 */
public interface SeckillService {

	/**
	 * 查询所有秒杀
	 * @return
	 */
	List<Seckill> getSeckillList(); 
	
	/**
	 * 查询单个秒杀
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId); 
	
	/**
	 * 秒杀开启时输出秒杀地址
	 * 否则输出系统时间和秒杀时间
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	

	/**
	 * 执行秒杀操作，有可能失败，有可能成功，所以要抛出我们允许的异常
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @return
	 * @throws SeckillException 秒杀出错，区别去后面两个更具体的异常
	 * @throws RepeatKillException 重复秒杀异常
	 * @throws SeckillCloseException 秒杀关闭异常
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
			throws SeckillException,RepeatKillException,SeckillCloseException;
}
