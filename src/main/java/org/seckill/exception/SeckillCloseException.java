package org.seckill.exception;

/**
 * ��ɱ�ر��쳣
 * ����ɱ����ʱ�û���Ҫ������ɱ�ͻ��������쳣
 * @author error
 *
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
