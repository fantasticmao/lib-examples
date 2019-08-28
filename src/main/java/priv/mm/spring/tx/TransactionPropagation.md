# Spring 事务传播行为

传播行为 | 说明 
--- | ---
Propagation.REQUIRED | 支持当前事务；如果当前不存在事务，则创建事务
Propagation.SUPPORTS | 支持当前事务；如果当前不存在事务，则以非事务方式执行
Propagation.MANDATORY | 支持当前事务；如果当前不存在事务，则抛出异常
Propagation.REQUIRES_NEW | 创建新的事务；如果当前存在事务，则挂起当前事务
Propagation.NOT_SUPPORTED | 以非事务方式执行；如果当前存在事务，则挂起当前事务
Propagation.NEVER | 以非事务方式执行；如果当前存在事务，则抛出异常
Propagation.NESTED | 以嵌套事务方式执行；如果当前不存在事务，则与 Propagation.REQUIRED 类似