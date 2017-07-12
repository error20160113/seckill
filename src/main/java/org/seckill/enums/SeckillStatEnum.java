package org.seckill.enums;

/**
 * ʹ��ö�ٱ��������ֶ�
 * @author error
 *
 */
public enum SeckillStatEnum {

    SUCCESS(1,"��ɱ�ɹ�"),
    END(0,"��ɱ����"),
    REPEAT_KILL(-1,"�ظ���ɱ"),
    INNER_ERROR(-2,"ϵͳ�쳣"),
    DATE_REWRITE(-3,"���ݴ۸�");
	
	private int state;
	private String stateInfo;
	
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	private SeckillStatEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static SeckillStatEnum stateOf(int index){
		for(SeckillStatEnum state : values()){
			if(state.getState()==index){
				return state;
			}
		}
		
		return null;
	}

}
