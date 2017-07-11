package org.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合
 * 1.junit启动时加载springICO容器
 * 2.告诉junit spring配置文件
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
