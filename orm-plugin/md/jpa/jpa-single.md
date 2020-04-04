## jpa-single
jpa-hibernate单数据源功能实现

### 使用说明
~~~
1.引入依赖
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>

2.示例代码
com.sprboot.ex.ormplugin.jpa.single
~~~

## 参考
* [jpa审计功能实现创建人，创建日期，修改人，修改时间更新](http://blog.csdn.net/solebogor/article/details/73381893)
* [JPA实体映射问题1](https://gxnotes.com/article/34956.html)。    
JPA 1.0不允许映射到non-entity类，只有在JPA 2.1中，已经添加了一个ConstructorResult来映射返回值一个java类。 
* [JPA实体映射问题2](https://stackoverflow.com/questions/13012584/jpa-how-to-convert-a-native-query-result-set-to-pojo-class-collection?answertab=votes)。    
jpa1.1 支持原生sql查询返回@Entity实体类的实体，jpa2.0 支持原生sql查询返回具体实体类

