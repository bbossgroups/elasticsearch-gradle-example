#bboss elastic demo
# 使用参考文档
bboss elasticsearch开发库使用介绍

https://my.oschina.net/bboss/blog/1556866

# 构建部署
前提：安装和配置好最新的gradle版本，下载源码
## 利用gradle构建发布版本
gradle install

gradle releaseVersion

## 运行作业
gradle构建成功后，在build/distributions目录下会生成可以运行的zip包，解压后启动和运行quartz作业即可：


linux：

chmod +x startup.sh

./startup.sh

windows: startup.bat


