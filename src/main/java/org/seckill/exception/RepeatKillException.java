package org.seckill.exception;

/**
 * �ظ���ɱ�쳣
 * ��һ���������쳣
 * ����Ҫ�����ֶ�try catch
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
