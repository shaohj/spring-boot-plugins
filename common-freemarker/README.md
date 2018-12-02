# common-freemarker

## 功能描述
* 只需配置好word模板，自动替换指定的模板变量为具体数据，自动导出word
* 支持导出doc和docx
* 支持word内有静态图片导出
* 支持word静态图片上有文字导出
* 支持中文名word文件导出

### 解决思路
本demo采用的解决思路为freemarker导出(freemarker的主要作用为将动态表达式替换为javabean的值)。对其他思路不予评价。  
word的doc和docx的底层存储结构不一样，因此对这两种情况分别进行处理。

#### freemarker导出doc及docx注意事项
请取消拼写检查的功能以及$和表达式字体设置为一致,否则doc或docx的表达式~${numNo}~，转为xml后会变成~<w:t>${</w:t>...numNo...<w:t>}~，将导致freemark无法正确解析表达式，也可手动删除，但没那必要是不。

#### freemarker导出doc
doc为单文件格式，我们将其转为可编辑的xml格式后，替换掉表达式值，再将其还原为doc文件即可。  
手动模拟步骤：  
* 创建doc，新建内容如“hello，${name}”，文件另存为word 2003 xml文档
* 用文本编辑器打开xml文件，找到${name}，改为张三，文件另存为doc文件
* 打开doc文件，内容显示为“hello，张三”  
步骤演示图如下  
<img src="https://github.com/shaohj/shj_tools/blob/master/imgs/blog/%E6%A8%A1%E6%9D%BF%E5%AF%BC%E5%87%BAdoc.gif?raw=true" style="zoom:50%" border="1px"/>  
代码步骤，对应如上步骤  
* 使用jacob将doc转为了xml,jacob使用注意事项见以下小节  
* freemarker读取模板字符串后，使用javabean值替换模板表达式值，再将xml另存为doc，删除xml临时文件，完成，具体请看代码  

##### jacob使用
我的环境为win10 64位系统，linux环境未测试。另一个不用jacob的方案如下，手动将doc转为xml，即直接跳过第一步，使用xml导出doc即可  
* jacob引入maven依赖
~~~
<dependency>  
    <groupId>com.hynnet</groupId>  
    <artifactId>jacob</artifactId>  
    <version>1.18</version>  
</dependency>
~~~
* 将common-freemarker/lib/jacob-1.18-x64.dll的文件复制到C:\Program Files\Java\jdk1.8.0_131\jre\bin\jacob-1.18-x64.dll
* 写代码将doc转为xml，具体测试请看JacobUtilsTest.docToXmlTest  
注意使用jacob转xml时，最好手动另存为xml看下表达式是否可以正确转，因为单词拼写检查去除后，去除前的单词拼写的样式还是会让我们的表达式不正确。

#### freemarker导出docx
docx是压缩文件格式，我们需要找到对应的文本模板文件，替换掉表达式的值后，将新的文本模板文件替换掉原docx的模板文件即可。
手动模拟步骤：
* 创建docx，新建内容如“hello，${name}”
* 用7z(使用winrar压缩软件也是一样的)打开docx文件，编辑word/document.xml，修改为“hello，张三”,系统为提示是否将已更改的内容更新到压缩包，点击确认即可。
* 打开docx文件，内容显示为“hello，张三”
程序实现时，涉及到了压缩包文件的操作，因此使用了apache的压缩包工具类，具体请看代码，步骤演示图如下  
<img src="https://github.com/shaohj/shj_tools/blob/master/imgs/blog/%E6%A8%A1%E6%9D%BF%E5%AF%BC%E5%87%BAdocx.gif?raw=true" style="zoom:50%" border="1px"/>  
代码步骤，对应如上步骤  
* 使用apache zip包打开压缩文件
* 编辑压缩文件，找到word/document.xml
* 因为要用freemarker,需要将ftl模板文件生成至模板文件目录中，我们将找到的word/document.xml文件复制过去改为ftl后缀即可(也可不改后缀)
* 将生成的ftl模板文件中的表达式翻译为具体数据，生成翻译后的ftl文件
* 将翻译后的ftl文件和其他文件一起写入新的doc文件中，删除2个ftl临时文件，完成，具体请看代码

## 注意
测试代码基本都在junit的FmRealPathManagerTest中写了，但是全是使用的真实路径测试的，带简单图片的我也测试成功了，测试时，需要将类路径下的资源拷贝到你本地真实路径测试即可。  
类路径的不建议使用，动态生成的ftl模板文件在类路径下会失效，需要重启才会生效，另外类路径还有些其他小问题，未测试成功，总而言之，类路径方式不太合理，放弃该方式吧。

## 缺陷
暂无记录，若发现了其他问题，可以提到issue里面。
