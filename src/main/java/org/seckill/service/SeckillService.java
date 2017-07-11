package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

/**
 * ҵ��ӿ�:վ��ʹ���߽Ƕ���ƽӿ�
 * ������������,����,��������
 * @author error
 *
 */
public interface SeckillService {

	/**
	 * ��ѯ������ɱ
	 * @return
	 */
	List<Seckill> getSeckillList(); 
	
	/**
	 * ��ѯ������ɱ
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId); 
	
	/**
	 * ��ɱ����ʱ�����ɱ��ַ
	 * �������ϵͳʱ�����ɱʱ��
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	

	/**
	 * ִ����ɱ�������п���ʧ�ܣ��п��ܳɹ�������Ҫ�׳�����������쳣
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @return
	 * @throws SeckillException ��ɱ��������ȥ����������������쳣
	 * @throws RepeatKillException �ظ���ɱ�쳣
	 * @throws SeckillCloseException ��ɱ�ر��쳣
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
			throws SeckillException,RepeatKillException,SeckillCloseException;
}
