package org.seckill.dao.cache;

import org.seckill.entity.Seckill;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 缓存
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
	 * 从缓存获取秒杀对象
	 * @param seckillId
	 * @return
	 */
	public Seckill getSeckill(long seckillId){
		//redis操作逻辑
		try {
			Jedis jedis = jedisPool.getResource();
			
			try {
				String key="seckill"+seckillId;
				//并没有实现内部序列化操作
				//get->byte[]->反序列化->object(Seckill)
				//采用自定义序列化
				byte[] bytes= jedis.get(key.getBytes());
				if(bytes!=null){
					//创建一个空对象
					Seckill seckill=schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
					//seckill被反序列
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
	 * 给缓存添加秒杀对象
	 * @param seckill
	 * @return
	 */
	public String putSeckill(Seckill seckill){
		//put->object(Seckill)->序列化->byte[]
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key="seckill"+seckill.getSeckillId();
				byte[] bytes=ProtostuffIOUtil.toByteArray
						(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				
				//超时缓存
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
