# spring-boot-common
## 描述
将功能原型集成到该github库中，如
* spring boot 项目原型，处理一些基本配置和日志等配置
* aop,如记录日志
* email，如发送邮件，分析邮件
* poi，如导出excel，这里做了2种处理，分为缓存模板导出(SXSSFWorkbook，不支持xls等导出)、及支持xls的导出(不支持缓存导出)
* common-poi-no-cache
* freemarker模板导出word
* resttemplate调用封装
* redis，如5种数据类型处理
* jpa-hb，jpa-hibernate常用功能支持,如审计功能，返回自定义实体，多对多等关系配置
* muldb-hb，jpa-hibernate多数据源功能
* muldb-mb，mybatis多数据源功能
* util，封装一些常用的工具类，如时间、文件、hibernate验证器等工具类
## 参考
[利用内存分析工具（Memory Analyzer Tool，MAT）分析java项目内存泄露](https://blog.csdn.net/wanghuiqi2008/article/details/50724676)
