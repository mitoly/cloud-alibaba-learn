### Nacos
**Nacos打包编译**
* mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U

**Nacos运行**

* cd distribution/target/nacos-server-$version/nacos/bin
> // change the $version to your actual path

**Linux/Unix/Mac**
启动命令(standalone代表着单机模式运行，非集群模式):
sh startup.sh -m standalone // 如果是单机的话无需添加-m standalone

**如果您使用的是ubuntu系统，或者运行脚本报错提示[[符号找不到，可尝试如下运行：**
bash startup.sh -m standalone

**Windows**
启动命令(standalone代表着单机模式运行，非集群模式):
startup.cmd -m standalone


### Sentinel
**Sentinel打包编译**
* mvn clean package
**使用如下命令启动控制台**
* java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
**用于指定控制台的登录用户名为 sentinel**
* -Dsentinel.dashboard.auth.username=sentinel
**用于指定控制台的登录密码为 123456；如果省略这两个参数，默认用户和密码均为 sentinel**
* -Dsentinel.dashboard.auth.password=123456
**用于指定 Spring Boot 服务端 session 的过期时间，如 7200 表示 7200 秒；60m 表示 60 分钟，默认为 30 分钟**
* -Dserver.servlet.session.timeout=7200
**更多配置见**
* https://github.com/alibaba/Sentinel/wiki/%E6%8E%A7%E5%88%B6%E5%8F%B0#%E9%89%B4%E6%9D%83