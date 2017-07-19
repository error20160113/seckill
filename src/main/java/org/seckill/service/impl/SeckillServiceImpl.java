package org.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService{

	private Logger logger =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private  RedisDao redisDao;
	
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	//md5盐值字符串，用于混淆加密
	private final String slat="!@#$%^&*()";
	
	
	@Override
	public List<Seckill> getSeckillList() {
		
		return seckillDao.queryAll(0, 10);
	}

	@Override
	public Seckill getById(long seckillId) {

		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		//优化点 缓存优化
		//1.访问redis
		Seckill seckill=redisDao.getSeckill(seckillId);
		if(seckill==null){
			//2.访问数据库
			seckill= seckillDao.queryById(seckillId);
			if(seckill==null){
				return new Exposer(false, seckillId);
			}else{//3.放入ridis
				redisDao.putSeckill(seckill);
			}
		}
		
		//Seckill seckill= seckillDao.queryById(seckillId);
		
		
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if(nowTime.getTime()<startTime.getTime()
				||nowTime.getTime()>endTime.getTime()){
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		
		String md5 = getMD5( seckillId);
		
		return new Exposer(true, md5, seckillId);
	}

	@Override
	@Transactional
    /**
     * 使用注解控制事务方法的优点:
     * 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部
     * 3.不是所有的方法都需要事务，如只有一条修改操作、只读操作不要事务控制
     */
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		if(md5==null || !md5.equals(getMD5(seckillId))){
			throw new SeckillException("seckill data rewrite");
		}
		//执行秒杀 减库存+记录购买行为
		Date nowTime = new Date();
		
		try{
			/**
			 * 优化逻辑
			 * 先插入明细再减库存，减少减库存时候行级锁的等待时间
			 * 
			 */
			//记录购买行为
			int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			if(insertCount<=0){
				//重复秒杀
				throw new RepeatKillException("seckill repeated");
			}			
			//减库存
			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
			if(updateCount<=0){
				//没有更新记录，秒杀结束
				throw new SeckillCloseException("seckill is closed");
			}else{
				//秒杀成功
				SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
				return new SeckillExecution(seckillId,SeckillStatEnum.SUCCESS, successKilled);
			}
		}catch(SeckillCloseException e1){
			throw e1;
		}catch(RepeatKillException e2){
			throw e2;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			//所有编译器异常转化为运行期异常
			throw new SeckillException("seckill error : "+ e.getMessage());
		}

		
	}

	
	private String getMD5(long seckillId) {
		String base = seckillId+"/"+slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	/* (non-Javadoc)
	 * @see org.seckill.service.SeckillService#executeSeckillProcedure(long, long, java.lang.String)
	 */
	@Override
	public SeckillExecution executeSeckillProcedure(long seckillId,long userPhone, String md5){
		if(md5==null || !md5.equals(getMD5(seckillId))){
			return new SeckillExecution(seckillId,SeckillStatEnum.DATE_REWRITE);
			
		}
		Date killTime = new Date();
		Map<String, Object> map=  new HashMap<String, Object>();
		map.put("seckillId", seckillId);
		map.put("phone", userPhone);
		map.put("killTime", killTime);
		map.put("result", null);
		
		//执行完存过后 result被赋值
		try {
			seckillDao.killByProcedure(map);
			//获取result
			int result=MapUtils.getInteger(map,"result",-2);
			if(result==1){
				SuccessKilled sk= successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
				return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS,sk);				
			}else{
				return new SeckillExecution(seckillId, SeckillStatEnum.stateOf(result));	
			}
		} catch (Exception e) {
			return new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
		}	

	}
























}