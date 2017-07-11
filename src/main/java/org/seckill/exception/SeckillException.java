package org.seckill.exception;

/**
 * 秒杀相关业务所有异常
 * @author error
 *
 */
public class SeckillException extends RuntimeException{

	public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
