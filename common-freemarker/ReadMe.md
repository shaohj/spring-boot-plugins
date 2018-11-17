# common-freemarker
## 功能描述
* 根据word模板，替换指定的模板变量为具体数据，导出word
* 支持导出doc和docx
* 支持word内有静态图片导出
* 支持word静态图片上有文字导出
* 支持中文名word文件导出
### 解决思路
本demo采用的解决思路为freemarker导出(freemarker的主要作用为将动态表达式替换为javabean的值)。对其他思路不予评价。  
word的doc和docx的底层存储结构不一样，因此对这两种情况分别进行处理。
#### freemarker导出doc及docx注意事项
请取消拼写检查的功能,否则doc或docx的表达式~${numNo}~，转为xml后会变成~<w:t>${</w:t>...numNo...<w:t>}~，将导致freemark无法正确解析表达式，也可手动删除，但没那必要是不。
#### freemarker导出doc
doc为单文件格式，我们将其转为可编辑的xml格式后，替换掉表达式值，再将其还原为doc文件即可。  
手动模拟步骤：  
* 创建doc，新建内容如“hello，${name}”，文件另存为word 2003 xml文档
* 用文本编辑器打开xml文件，找到${name}，改为张三，文件另存为doc文件
* 打开doc文件，内容显示为“hello，张三”  
程序实现无难点，请看具体代码  
![模板导出doc.gif](https://github.com/shaohj/shj_tools/blob/master/imgs/blog/%E6%A8%A1%E6%9D%BF%E5%AF%BC%E5%87%BAdoc.gif?raw=true)  
#### freemarker导出docx
docx是压缩文件格式，我们需要找到对应的文本模板文件，替换掉表达式的值后，将新的文本模板文件替换掉原docx的模板文件即可。
手动模拟步骤：
* 创建docx，新建内容如“hello，${name}”
* 用7z(使用winrar压缩软件也是一样的)打开docx文件，编辑word/document.xml，修改为“hello，张三”,系统为提示是否将已更改的内容更新到压缩包，点击确认即可。
* 打开docx文件，内容显示为“hello，张三”
程序实现时，涉及到了压缩包文件的操作，因此使用了apache的压缩包工具类，具体请看代码  
![模板导出docx.gif](https://github.com/shaohj/shj_tools/blob/master/imgs/blog/%E6%A8%A1%E6%9D%BF%E5%AF%BC%E5%87%BAdocx.gif?raw=true)  
## 缺陷
暂无记录，若发现了其他问题，可以提到issue里面。
