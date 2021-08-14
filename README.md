#set oracle jdk environment
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java  ## 这里要注意目录要换成自己解压的jdk 目录
export JRE_HOME=${JAVA_HOME}/jre  
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib  
export PATH=${JAVA_HOME}/bin:$PATH  


指定maven 配置目录
<localRepository>/root/dev/maven/repo</localRepository>  
  
  
 export M2_HOME=/root/dev/maven/apache-maven-3.6.3
 export PATH=.:$M2_HOME/bin:$PATH
 
 
 sudo npm install cnpm -g --registry=https://registry.npm.taobao.org
 
 
1、先查看一下防火墙已经开放了哪些端口
firewall-cmd --zone=public --list-ports

2、如果没有自己想打开的端口，就把端口开启，比如下面就是开放8099这个端口
firewall-cmd --zone=public --add-port=8099/tcp --permanent

3、使得上面配置立即生效
firewall-cmd --reload

4、如果想关闭当前这个端口
firewall-cmd --zone=public --remove-port=8099/tcp --permanent


然后重启下Nginx，在Xshell中依次敲以下CMD：

sudo systemctl enable nginx（停止nginx运行）

sudo systemctl start nginx（开启nginx运行）

sudo systemctl status nginx（查看nginx运行状态）


1. 下载 emqx-ubuntu18.04-4.2.3-x86_64.deb SHA256
wget https://www.emqx.io/cn/downloads/broker/v4.2.3/emqx-ubuntu18.04-4.2.3-x86_64.deb
2. 安装
sudo dpkg -i emqx-ubuntu18.04-4.2.3-x86_64.deb
3. 运行
sudo emqx start



5. 添加系统自启
/etc/rc.local
#!/bin/bash

#export PATH=/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/bin:$PATH
#export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre

nohup java -jar /root/onlinePro/springboot/manager-0.0.1-SNAPSHOT.jar &

6.导入数据库
二、导入数据库
1、首先建空数据库
mysql>create database abc;

2、导入数据库
方法一：
（1）选择数据库
mysql>use abc;
（2）设置数据库编码
mysql>set names utf8;
（3）导入数据（注意sql文件的路径）
mysql>source /home/abc/abc.sql;
方法二：
mysql -u用户名 -p密码 数据库名 < 数据库名.sql
#mysql -uabc_f -p abc < abc.sql

阿里云添加安全组

7. cd my-project-first/
   cnpm run dev
   
   
   
   
   
   
   数据库连接问题
   mysql -uroot -h 127.0.0.1 -p 
