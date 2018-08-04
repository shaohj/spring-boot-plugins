# common-freemarker
## 描述
* 根据模板生成具体数据
* 使用freemarker导出word
## freemarker转doc docx说明
* 转doc: 只需要将word另存为xml,然后用xml格式化后转为doc即可。
* 转docx: 需要用7z或winrar打开docx文件,提取word/document.xml,然后用freemarker动态替换document值,再根据docx模板新生成一个docx文件,依次复制文件,及document写入已替换值的文件即可。
