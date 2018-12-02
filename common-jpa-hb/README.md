# common-jpa-hb
## 描述
jpa-hibernate功能支持
## 参考
* [jpa审计功能实现创建人，创建日期，修改人，修改时间更新](http://blog.csdn.net/solebogor/article/details/73381893)
* [JPA实体映射问题1](https://gxnotes.com/article/34956.html)。    
JPA 1.0不允许映射到non-entity类，只有在JPA 2.1中，已经添加了一个ConstructorResult来映射返回值一个java类。 
* [JPA实体映射问题2](https://stackoverflow.com/questions/13012584/jpa-how-to-convert-a-native-query-result-set-to-pojo-class-collection?answertab=votes)。    
jpa1.1 支持原生sql查询返回@Entity实体类的实体，jpa2.0 支持原生sql查询返回具体实体类
