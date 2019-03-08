# dubboStudy
dubbo框架的学习使用

## 下载zookeeper

* 镜像网上下载zk:https://mirrors.tuna.tsinghua.edu.cn/apache/zookeeper/
* 解压后进入conf目录，复制zoo_sample.cfg一份为zoo.cfg
* 修改dataDir目录，查看clientPort，为2181
* 启动zk服务，bin/zkServer.sh start
* 用客户端尝试连接zk，bin/zkCli.sh

## dubbo admin

* github查找dubbo，地址：https://github.com/apache/incubator-dubbo
* 查看Dubbo ecosystem下的Dubbo OPS
* 拉取对应github项目，老版本用master分支，新的用dev分支
* 进入dubbo-admin目录，查看配置文件注册中心地址是否正确
* mvn clean package 打包项目，jar包运行
* 根据server.port访问7001，账号root，密码root

## dubbo monitor

* 进入dubbo-monitor-simple目录，查看配置文件注册中心地址是否正确
* 查看监控中心端口dubbo.protocol.port，默认7070
* mvn clean package 打包项目，进入target，解压dubbo-monitor-simple-2.0.0-assembly.tar.gz
* 在解压后的目录dubbo-monitor-simple-2.0.0/assembly.bin/中，通过脚本assembly.bin/运行监控中心
* 访问UI页面端口8080
* 在消费方和提供方分别配置monitor，协议方式为registry

## 提供者

* 引入zookeeper客户端包，公共接口api包，dubbo包
* 在配置文件中配置应用名、注册中心地址
* 暴露服务的端口和协议、需要暴露的接口和接口对应的实现

## 消费者

* 引入zookeeper客户端包，公共接口api包，dubbo包
* 在配置文件中配置应用名、注册中心地址
* 配置引用的接口和其id
* 调用时，根据接口和id进行注入
