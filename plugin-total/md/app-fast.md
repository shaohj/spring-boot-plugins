## app-fast
快速配置应用

### demo
无代码，详细请看下面步骤

~~~
1.创建空的应用
https://start.spring.io/

2.配置application.yml
server:
  port: 8080
  address: 0.0.0.0
  servlet:
    context-path: /appfast
logging:
  level:
    root: INFO
    com:
      sprboot:
        plugin: DEBUG
    org:
      springframework:
        web:
          servlet:
            DispatcherServlet: TRACE
            mvc:
              method:
                annotation:
                  ServletInvocableHandleMethod: TRACE
  file: log/appfast.log

3.若引用数据库依赖，且不想启动数据库，其他类似
@SpringBootApplication(
        //启动时排除了数据源
        exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class} 
)

4.junit测试基础类配置
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles
public class BaseApplicationTest {
    @Test
    public void contextLoads() {
        
    }
}

~~~

