package org.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ����spring��junit����
 * 1.junit����ʱ����springICO����
 * 2.����junit spring�����ļ�
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

	@Resource
	private SuccessKilledDao successKilledDao;
	
	@Test
	public void testInsertSuccessKilled() {
		int insertCount=successKilledDao.insertSuccessKilled(1001, 13227711853l);
		System.out.println(insertCount);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		SuccessKilled successKilled =successKilledDao.queryByIdWithSeckill(1000, 13227711853l);
		System.out.println(successKilled.toString());
	}

}
