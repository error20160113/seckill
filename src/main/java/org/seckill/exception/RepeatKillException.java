package org.seckill.exception;

/**
 * 重复秒杀异常
 * 是一个运行期异常
 * 不需要我们手动try catch
 * @author error
 *
 */
public class RepeatKillException extends SeckillException{

	public RepeatKillException(String message) {
		super(message);
	}
	
	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}


}
