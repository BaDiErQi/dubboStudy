# dubboStudy
dubbo框架的学习使用

# spring框架下

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

# springboot框架下

* 引包时注意dubbo-spring-boot-starter的版本和groupId，可以参考官网或github
* yml文件除暴露服务和引用接口外一样配置
* 启动类上使用@EableDubbo注解
* 引用的服务@AutoWire改为@Reference，暴露的服务改为dubbo的@Service，加上@Component注入容器

# 配置

> 如果没有服务提供方，启动消费方会报错，check属性，默认为true。设置为false后启动不会进行检查，调用服务时检查，不存在提供方则报错。

* 可以在消费方引用的标签或者注解上设置check属性
* 可以统一设置所有消费方，dubbo.consumer.check=false
* dubbo:registry check=fasle  没有注册中心启动不报错

> timeout属性，消费方引用提供方时，如果很长时间没有返回，可以设置时间，超过时间停止访问

* 在消费方引用标签或者注解上配置
* 方法配置优先，接口配置次之，全局配置最后。
* 同级别间，服务消费方配置优先，提供方配置次之

参考：http://dubbo.apache.org/zh-cn/docs/user/configuration/xml.html

> 重试次数，retries，设置3次，不包含第一次，那么一共会调用四次

* 有多个服务提供方，失败后重试次数会分到其他服务上，总次数不变
* 幂等接口(查询、删除、修改)可以设置retries，非幂等接口(新增)不设置重试次数
* 设置为0，0代表不重试

> 当一个接口实现，出现不兼容升级时，可以用版本号过渡，版本号不同的服务相互间不引用。

* 服务提供方暴露接口时可以指定版本号，消费方引用接口时指定版本号
* 版本号匹配的情况下服务才能调用

> 本地存根

* 在消费方创建存根类，实现暴露接口
* 固定有参构造，通过dubbo拿到远程代理对象
* 在实现的方法中做自己的实现，再调用代理对象的相应方法
* 在消费方引用标签配置stub存根属性

一般存根会放在接口模块进行实现

# springboot与dubbo整合的三种方式

1.导入dubbo-starter，在application.properties配置属性，使用@Service暴露服务使用@Reference引用服务，使用@EnableDubbo开启包扫描功能。  
2.保留dubbo xml配置文件，在启动类中加上@ImportResource(location="classpath:provider.xml")，dubbo的@Service和@Reference就可以不用使用了。   
3.书写配置类，注册实例ApplicationConfig/RegistryConfig/ProtocolConfig/ServiceConfig等API类，添加@EnableDubbo(scanBasePackages="xxx")。

# zk宕机和dubbo直连

* 方法引用时设置url，指向服务提供方接口

# 负载均衡

* 在一些引用和服务暴露上设置loadbalance属性，在控制台提供者设置其权重

# 服务降级

* 在消费者处选择屏蔽，则直接返回null
* 在消费者处选择容错，当超时和报错时返回null

# 整合Hystrix容错

* 导入Hystrix的starter包
* 启动类开启@EnableHystrix
* 消费方在调用方法上，提供方在可能出错的方法上，添加@HystrixCommand
* 消费方在@HystrixCommand中指定失败回调方法
