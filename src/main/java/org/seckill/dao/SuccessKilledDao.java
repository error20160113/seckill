package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

    /**
     * ���빺����ϸ,�ɹ����ظ�
     * @param seckillId
     * @param userPhone
     * @return���������
     */
    int insertSuccessKilled(long seckillId,long userPhone);
    
    
    /**
     * ������ɱ��Ʒ��id��ѯ��ϸSuccessKilled����(�ö���Я����Seckill��ɱ��Ʒ����)
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId,long userPhone);
}
