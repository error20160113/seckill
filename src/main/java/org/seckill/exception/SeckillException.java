package org.seckill.exception;

/**
 * ��ɱ���ҵ�������쳣
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
