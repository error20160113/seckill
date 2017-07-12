package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
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
import org.springframework.util.DigestUtils;

public class SeckillServiceImpl implements SeckillService{

	private Logger logger =LoggerFactory.getLogger(this.getClass());
	
	private SeckillDao seckillDao;
	
	private SuccessKilledDao successKilledDao;
	
	//md5��ֵ�ַ��������ڻ�������
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
		
		Seckill seckill= seckillDao.queryById(seckillId);
		if(seckill==null){
			return new Exposer(false, seckillId);
		}
		
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
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		if(md5==null || !md5.equals(getMD5(seckillId))){
			throw new SeckillException("seckill data rewrite");
		}
		//ִ����ɱ �����+��¼������Ϊ
		Date nowTime = new Date();
		
		try{
			//�����
			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
			if(updateCount<=0){
				//û�и��¼�¼����ɱ����
				throw new SeckillCloseException("seckill is closed");
			}else{
				//��¼������Ϊ
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				if(insertCount<=0){
					//�ظ���ɱ
					throw new RepeatKillException("seckill repeated");
				}else{
					//��ɱ�ɹ�
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId,SeckillStatEnum.SUCCESS, successKilled);
				}
			}
		}catch(SeckillCloseException e1){
			throw e1;
		}catch(RepeatKillException e2){
			throw e2;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			//���б������쳣ת��Ϊ�������쳣
			throw new SeckillException("seckill error : "+ e.getMessage());
		}

		
	}

	
	private String getMD5(long seckillId) {
		String base = seckillId+"/"+slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
}
