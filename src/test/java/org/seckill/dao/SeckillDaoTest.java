package org.seckill.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
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
public class SeckillDaoTest {
	
	//注入dao实现依赖
	@Resource
	private SeckillDao seckillDao;
	
	
	@Test
	public void testReduceNumber() {
        long seckillId=1000;
        Date date=new Date();
        int updateCount=seckillDao.reduceNumber(seckillId,date);
        System.out.println("updateCount:  "+updateCount);
	}

	@Test
	public void testQueryById() {
		long id =1000;
		Seckill seckill=seckillDao.queryById(id);
		System.out.println(seckill.toString());
	}

	@Test
	public void testQueryAll() {
		List<Seckill> seckills=new ArrayList<Seckill>();
		seckills =seckillDao.queryAll(0, 50);
		for(Seckill seckill : seckills){
			System.out.println(seckill);
		}
	}

}
