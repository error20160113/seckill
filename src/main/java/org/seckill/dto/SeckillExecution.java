package org.seckill.dto;

import org.seckill.entity.SuccessKilled;

/**
 * ��ɱ�󷵻ؽ��
 * @author error
 *
 */
public class SeckillExecution {

    private long seckillId;

    //��ɱִ�н����״̬
    private int state;

    //״̬�����ı�ʶ
    private String stateInfo;

    //����ɱ�ɹ�ʱ����Ҫ������ɱ�ɹ��Ķ����ȥ
    private SuccessKilled successKilled;

    //��ɱ�ɹ���������
	public SeckillExecution(long seckillId, int state, String stateInfo,
			SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = state;
		this.stateInfo = stateInfo;
		this.successKilled = successKilled;
	}

	//��ɱʧ�ܵĹ��췽��
	public SeckillExecution(long seckillId, int state, String stateInfo) {
		super();
		this.seckillId = seckillId;
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state
				+ ", stateInfo=" + stateInfo + ", successKilled="
				+ successKilled + "]";
	}
    
	
	
    
}
