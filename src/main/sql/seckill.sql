-- ��ɱִ�д洢����
--  console ; ת��Ϊ$$
DELIMITER $$
-- ����洢����
-- ������in ������� out �������
-- row_count()������һ���޸�����sql��Ӱ������
-- row_count() =0:δ�޸� >0:�޸����� <0:sql����/δִ��
CREATE PROCEDURE zy.`execute_seckill`
 (IN v_seckill_id BIGINT,IN v_phone BIGINT,IN v_kill_time TIMESTAMP,OUT r_result INT)
 BEGIN
  DECLARE insert_count INT DEFAULT 0; 
  START TRANSACTION;
  INSERT IGNORE INTO success_killed
  (seckill_id,user_phone,create_time)
  VALUES(v_seckill_id,v_phone,v_kill_time);
  SELECT ROW_COUNT() INTO insert_count;
  IF(insert_count=0) THEN
    ROLLBACK;
    SET r_result=-1;
  ELSEIF(insert_count<0)THEN
    ROLLBACK;
    SET r_result=-2;
  ELSE 
    UPDATE seckill 
    SET number=number-1
    WHERE seckill_id=v_seckill_id
      AND end_time>v_kill_time
      AND start_time<v_kill_time
      AND number>0;
    SELECT ROW_COUNT() INTO insert_count;
    IF(insert_count=0) THEN
      ROLLBACK;
      SET r_result=0;
    ELSEIF(insert_count<0)THEN
      ROLLBACK;
      SET r_result=-2;
    ELSE 
      COMMIT;
      SET r_result=1;
    END IF;
  END IF;
END ;
$$
-- �洢���̶������ 


DELIMITER ;

SET @r_result=-3;
-- ִ�д洢����
CALL execute_seckill(1003,13813813822,NOW(),@r_result);
-- ��ȡ���
SELECT @fadeResult;



-- �洢����:�Ż������м�������ʱ��