这是一个不使用 JPA ，而是使用 mybatis 的 ddd 的实践尝试。 

biz-module作为后续可独立的微服务设计。

特点:

- bootstrap 依赖 ${biz-module}-interfaces;
- biz module 数据源独立；
- biz module 通过 interface-facade 相互调用；
- interface 可选包括 web package;