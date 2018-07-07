# 项目Service原型功能说明：
  poi导入<br />
  导出,支持百万级数据模板导出<br />
  合并excel<br />

## 项目参考
  ExcelUtils http://excelutils.sourceforge.net
  备注：原来源不支持2007excel导出,还有些其他bug，因此在其上面再进行了开发
  
###支持标签
模板语法示例(具体可参考相关模板文件common-poi\src\main\resources\xlsx)
####if

####foreach
<span>
#foreach detail in ${list}  <br />
  ${detail.name}  <br />
#end  <br />
</span>

####如果值为null则设置默认值

####测试情况
测试数据对比
操作         数据量    时间
未缓存导出   1003      23000
             2003      124173

操作         数据量    时间
缓存10导出   1003      22540
             2003      132098