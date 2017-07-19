-- 秒杀执行存储过程
--  console ; 转换为$$
DELIMITER $$
-- 定义存储过程
-- 参数：in 输入参数 out 输出参数
-- row_count()返回上一条修改类型sql的影响行数
-- row_count() =0:未修改 >0:修改行数 <0:sql错误/未执行
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
-- 存储过程定义结束 


DELIMITER ;

SET @r_result=-3;
-- 执行存储过程
CALL execute_seckill(1003,13813813822,NOW(),@r_result);
-- 获取结果
SELECT @fadeResult;



-- 存储过程:优化事物行级锁持有时间