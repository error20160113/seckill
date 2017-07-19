package org.seckill.service;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
	
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList() {
		
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}",list);
	}

	@Test
	public void testGetById() {
		long id = 1000;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}",seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		long id =1003;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		logger.info("exposer={}",exposer);
	}

	@Test
	public void testExecuteSeckill() {
		long id =1003;
		long userPhone=13227711883l;
		String md5="1c921046f7ff633a1f9172776496fdd2";
		SeckillExecution execution=seckillService.executeSeckill(id, userPhone, md5);
		logger.info("SeckillExecution={}",execution);
	}

	@Test
	public void executeSeckillProcedure(){
		long seckillId =1003;
		long userPhone=88888888888l;
		String md5="1c921046f7ff633a1f9172776496fdd2";
		SeckillExecution execution=seckillService.executeSeckillProcedure(seckillId, userPhone, md5);
		System.out.println(execution);
	}
}
