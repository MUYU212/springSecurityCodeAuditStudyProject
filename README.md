# SSH框架代码审计研究案例

​	本项目是在进行Java代码审计的时候由于遇到许多的SSH框架的项目，但是不够熟悉SSH框架以及springSecurity框架所以准备先写一个示例来学习一下，再继续进行之后的审计工作。关于构建项目的步骤我会放到Note文件夹下，感兴趣的小伙伴可以看看，以及和我交流。

## 目标

- [x] 实现strust2的路由接口。
- [x] 使用Hibernate实现基础的增加和查询操作，删除更新操作就不写了，没必要。
- [x] 编写存在SQL注入的方法以及编写其对应的修复代码。
- [x] 研究HQL注入。
- 实现spring security的拦截器和拦截方法。
  - 针对admin用户以及user用户的鉴权做到
- 实现FreeMarker构建项目，探究一下原理。
- [x] 集成一下ckfinder/ckeditor
