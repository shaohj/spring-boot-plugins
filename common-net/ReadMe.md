# common-net
## 描述
java 网络编程学习
* bio
    * 伪异步io,在传统的bio上添加了线程池
* nio  
实现了单个服务器与多个客户端进行通讯功能，未实现连接指定用户名，关闭连接，获取服务器所有当前连接信息功能，不过基本是复制粘贴发送消息代码即可
    * netty
* aio
##学习笔记
* 流的包装流被关闭时，会调用被包装流的关闭方法,[System.in关闭问题](https://bbs.csdn.net/topics/391841625)
* System.in、System.out、System.err的包装流不能被关闭，否则会影响其他程序
* socket的流建立连接后不能马上被关闭
## 参考
[Java 网络IO编程总结](https://blog.csdn.net/anxpp/article/details/51512200)
[Java IO编程模型概览](http://blog.decaywood.me/2016/02/24/Java-IO-programming-model/)
### nio参考
[NIO 入门](https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html)
[实现多人聊天室](https://www.jianshu.com/p/16104564f640)
### aio参考
[使用异步 I/O 大大提高应用程序的性能](https://www.ibm.com/developerworks/cn/linux/l-async/index.html)



