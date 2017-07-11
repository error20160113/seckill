package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

    /**
     * ���빺����ϸ,�ɹ����ظ�
     * @param seckillId
     * @param userPhone
     * @return���������
     */
    int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
    
    
    /**
     * ������ɱ��Ʒ��id��ѯ��ϸSuccessKilled����(�ö���Я����Seckill��ɱ��Ʒ����)
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
