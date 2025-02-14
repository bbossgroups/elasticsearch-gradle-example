Bboss is a good elasticsearch Java rest client. It operates and accesses elasticsearch in a way similar to mybatis.

# BBoss Environmental requirements

JDK requirement: JDK 1.8+

Elasticsearch version requirements: 1.X,2.X,5.X,6.X,+

Spring booter 1.x,2.x,+
# bboss elastic demo，包含以下示例
拼音搜索示例

分页查询示例

地理位置检索示例

聚合统计查询示例

普通es 增删改、批量增删改、全文检索orm示例

父子关系检索示例

关键词高亮显示示例

关键词联想和补全示例（term suggest，phrase suggest，complete suggest）


# 构建部署
## 源码下载
https://github.com/bbossgroups/elasticsearchdemo

## 通过gradle构建发布版本
前提：[安装和配置gradle](https://esdoc.bbossgroups.com/#/bboss-build)
### 修改配置：
一、设置mainclass，设置为要运行的带Main方法的运行类

打开配置文件application.properties，修改mainclass配置：

mainclass=org.frameworkset.elasticsearch.TestKerberos

二、设置Elasticsearch地址和认证信息

参考[《bboss elasticsearch开发库使用介绍》](https://esdoc.bbossgroups.com/#/development)修改elasticsearch的相关配置，

打开配置文件application.properties，修改es地址，es账号和口令：

elasticsearch.rest.hostNames=127.0.1.1:9200

如果启动了elasticsearch认证，修改es账号和口令：

elasticUser=elastic

elasticPassword=changeme

三、打包

运行指令，打包发布版本

release.bat

四、 运行

打包成功后，在build/distributions目录下会生成可以运行的zip包，解压后，找到demo的运行指令，就可以启动和运行demo。

修改JVM参数：打开jvm.options文件，可以设置jvm相关参数

调整内存：

```properties
-Xms1g
-Xmx1g

-XX:NewSize=512m
-XX:MaxNewSize=512m
-Xss256k
```


运行demo

linux：

chmod +x startup.sh

./startup.sh

windows: startup.bat


# 使用参考文档
## 快速集成和应用
非spring boot项目：
https://esdoc.bbossgroups.com/#/common-project-with-bboss

spring boot项目：
https://esdoc.bbossgroups.com/#/spring-booter-with-bboss

详细配置说明参考文档：
https://esdoc.bbossgroups.com/#/development

## 技术交流群:166471282 
     
## 微信公众号:bbossgroup   
![GitHub Logo](https://static.oschina.net/uploads/space/2017/0617/094201_QhWs_94045.jpg)


