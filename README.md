## SpringMVC+Spring+MyBatis实现秒杀系统案例  
* [Java高并发秒杀API之业务分析与DAO层](http://www.imooc.com/learn/587)  
* [Java高并发秒杀API之Service层](http://www.imooc.com/learn/631) 
* [Java高并发秒杀API之web层](http://www.imooc.com/learn/630)   
* [Java高并发秒杀API之高并发优化](http://www.imooc.com/learn/632)   

Redis配置在spring-dao.xml   
MySQL配置在jdbc.properties  
MyEclipse+tomcat8+jdk1.8  
http://localhost:8080/seckill/seckill/list  



* [Restful架构详解](http://kb.cnblogs.com/page/512047/)   

@RequestMapping注解  

1. 支持标准的URL 
2. Ant风格URL(?,*,**等字符) 
3. 带{XXX}占位符的URL  


Restful风格  

* GET->查询操作  
* POST->添加/修改操作  
* PUT->修改操作  
* DELETE->删除操作    


  
高并发优化  

* 前端控制：暴露接口，按钮防重复  
* 动静态数据分离：CDN缓存，后端缓存
* 事物竞争优化：减少事物锁时间

