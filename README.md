## SpringMVC+Spring+MyBatis实现秒杀系统案例  
* [Java高并发秒杀API之业务分析与DAO层](http://www.imooc.com/learn/587)  
* [Java高并发秒杀API之Service层](http://www.imooc.com/learn/631) 
* [Java高并发秒杀API之web层](http://www.imooc.com/learn/630)   
* [Java高并发秒杀API之高并发优化](http://www.imooc.com/learn/632)   

  
高并发优化  

* 前端控制：暴露接口，按钮防重复  
* 动静态数据分离：CDN缓存，后端缓存
* 事物竞争优化：减少事物锁时间



Restful风格  
GET->查询操作  
POST->添加/修改操作  
PUT->修改操作  
DELETE->删除操作  

* [Restful架构详解](http://kb.cnblogs.com/page/512047/)   

@RequestMapping注解  
1. 支持标准的URL 
2. Ant风格URL(?,*,**等字符) 
3. 带{XXX}占位符的URL  

  

#### GET  
* 安全且幂等  
* 获取表示  
* 变更时获取表示（缓存）  
* 200（OK） - 表示已在响应中发出  
* 204（无内容） - 资源有空表示  
* 301（Moved Permanently） - 资源的URI已被更新  
* 303（See Other） - 其他（如，负载均衡）  
* 304（not modified）- 资源未更改（缓存）  
* 400 （bad request）- 指代坏请求（如，参数错误）  
* 404 （not found）- 资源不存在  
* 406 （not acceptable）- 服务端不支持所需表示  
* 500 （internal server error）- 通用错误响应  
* 503 （Service Unavailable）- 服务端当前无法处理请求  

#### POST
* 不安全且不幂等  
* 使用服务端管理的（自动产生）的实例号创建资源  
* 创建子资源  
* 部分更新资源  
* 如果没有被修改，则不过更新资源（乐观锁）  
* 200（OK）- 如果现有资源已被更改  
* 201（created）- 如果新资源被创建  
* 202（accepted）- 已接受处理请求但尚未完成（异步处理）  
* 301（Moved Permanently）- 资源的URI被更新  
* 303（See Other）- 其他（如，负载均衡）  
* 400（bad request）- 指代坏请求  
* 404 （not found）- 资源不存在  
* 406 （not acceptable）- 服务端不支持所需表示  
* 409 （conflict）- 通用冲突  
* 412 （Precondition Failed）- 前置条件失败（如执行条件更新时的冲突）  
* 415 （unsupported media type）- 接受到的表示不受支持  
* 500 （internal server error）- 通用错误响应  
* 503 （Service Unavailable）- 服务当前无法处理请求  

#### PUT
* 不安全但幂等  
* 用客户端管理的实例号创建一个资源  
* 通过替换的方式更新资源  
* 如果未被修改，则更新资源（乐观锁）
* 200 （OK）- 如果已存在资源被更改
* 201 （created）- 如果新资源被创建
* 301（Moved Permanently）- 资源的URI已更改
* 303 （See Other）- 其他（如，负载均衡）
* 400 （bad request）- 指代坏请求
* 404 （not found）- 资源不存在
* 406 （not acceptable）- 服务端不支持所需表示
* 409 （conflict）- 通用冲突
* 412 （Precondition Failed）- 前置条件失败（如执行条件更新时的冲突）
* 415 （unsupported media type）- 接受到的表示不受支持
* 500 （internal server error）- 通用错误响应
* 503 （Service Unavailable）- 服务当前无法处理请求  

#### DELETE  
* 不安全但幂等
* 删除资源
* 200 （OK）- 资源已被删除
* 301 （Moved Permanently）- 资源的URI已更改
* 303 （See Other）- 其他，如负载均衡
* 400 （bad request）- 指代坏请求
* 404 （not found）- 资源不存在
* 409 （conflict）- 通用冲突
* 500 （internal server error）- 通用错误响应
* 503 （Service Unavailable）- 服务端当前无法处理请求

