# bboss elastic demo，包含以下示例
拼音搜索示例

分页查询示例

地理位置检索示例

聚合统计查询示例

普通es 增删改、批量增删改、全文检索orm示例

父子关系检索示例

关键词高亮显示示例

关键词联想和补全示例

# 使用参考文档
bboss elasticsearch开发库使用介绍

https://my.oschina.net/bboss/blog/1556866

# 构建部署
前提：安装和配置好最新的gradle版本，下载源码
## 通过gradle构建发布版本

gradle clean releaseVersion

## 运行作业
gradle构建成功后，在build/distributions目录下会生成可以运行的zip包，解压后，参考《bboss elasticsearch开发库使用介绍》修改elasticsearch的相关配置，然后找到demo的运行指令，就可以启动和运行demo：

linux：

chmod +x startup.sh

./startup.sh

windows: startup.bat


