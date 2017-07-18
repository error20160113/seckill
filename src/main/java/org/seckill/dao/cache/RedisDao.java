package org.seckill.dao.cache;

import org.seckill.entity.Seckill;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * ����
 * @author error
 *
 */
public class RedisDao {

	private final JedisPool jedisPool;

	public RedisDao(String ip,int port) {
		jedisPool=new JedisPool(ip,port);
	}
	
	private RuntimeSchema<Seckill> schema =RuntimeSchema.createFrom(Seckill.class);
	
	/**
	 * �ӻ����ȡ��ɱ����
	 * @param seckillId
	 * @return
	 */
	public Seckill getSeckill(long seckillId){
		//redis�����߼�
		try {
			Jedis jedis = jedisPool.getResource();
			
			try {
				String key="seckill"+seckillId;
				//��û��ʵ���ڲ����л�����
				//get->byte[]->�����л�->object(Seckill)
				//�����Զ������л�
				byte[] bytes= jedis.get(key.getBytes());
				if(bytes!=null){
					//����һ���ն���
					Seckill seckill=schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
					//seckill��������
					return seckill;
				}
				
			}finally{
				jedis.close();
			}
							
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	/**
	 * �����������ɱ����
	 * @param seckill
	 * @return
	 */
	public String putSeckill(Seckill seckill){
		//put->object(Seckill)->���л�->byte[]
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key="seckill"+seckill.getSeckillId();
				byte[] bytes=ProtostuffIOUtil.toByteArray
						(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				
				//��ʱ����
				int timeOut=60*60;
				String result=jedis.setex(key.getBytes(), timeOut, bytes);
				return result;
				
			}finally{
				jedis.close();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
}
