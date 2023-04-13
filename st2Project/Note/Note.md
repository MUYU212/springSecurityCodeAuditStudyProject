# 0x00 新建项目

参考这个文章，将空项目构建成一个可以运行的strust2的maven项目

https://www.cnblogs.com/sherlson/articles/16220756.html

新建一个空项目之后开始`New Module`

![image-20230412144548212](https://raw.githubusercontent.com/MUYU212/springSecurityCodeAuditStudyProject/main/st2Project/Note/Note.assets/image-20230412144548212.png)

在新生成的pom.xml中添加以下依赖

```xml
<dependencies>
  <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>3.8.1</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.apache.struts</groupId>
    <artifactId>struts2-core</artifactId>
    <version>2.3.37</version>
  </dependency>
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.0.1</version>
  </dependency>
</dependencies>
```

`web.xml`中的内容修改为

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <filter>
        <filter-name>struts2</filter-name>
        <filter-class>
            org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter
        </filter-class>
    </filter>
 
    <filter-mapping>
        <filter-name>struts2</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
 
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
 
</web-app>
```

在`resourse`目录下新建`struts.xml`文件（这个名字很重要一开始我就是因为写错了一直访问不到接口的），内容如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts PUBLIC
        "-//Apache Software Foundation//DTD Struts Configuration 2.3//EN"
        "http://struts.apache.org/dtds/struts-2.3.dtd">
<struts>
    <package name="basicstruts" extends="struts-default">
        <action name="index">
            <result>index.jsp</result>
        </action>
    </package>
</struts>
```

配置好tomcat之后应该就可以运行，访问到Hello world就成功执行

![image-20230412145437980](https://raw.githubusercontent.com/MUYU212/springSecurityCodeAuditStudyProject/main/st2Project/Note/Note.assets/image-20230412145437980.png)

# 0x01 测试接口

在main目录下新建一个java目录作为源代码路径，在java目录下新建com.red.action目录存放web接口代码，新建`HelloAction`

```java
package com.red.action;

public class HelloAction {
    public String sayHi(){
        System.out.println("Hello Action");
        return "success";
    }
}
```

然后修改`struts.xml`文件中的内容，添加以下的内容，这里class就对应了我们上边编写好的类，然后method参数对应要调用的方法，而name属性就是等下要访问的路径，里边的标签，代表结果返回success的话就跳转到hello.jsp，反之则到error.jsp中。

```xml
<package name="test" extends="struts-default">
    <action name="sayHi" class="com.red.action.HelloAction" method="sayHi">
        <result name="success">hello.jsp</result>
        <result name="error">error.jsp</result>
    </action>
</package>
```

接下来在webapp中就新建`hello.jsp`和`error.jsp`了，内容师傅们自行定义吧，只要能区分出来两者的区别就好。

编写完之后项目结构变成了这样

![image-20230412174049602](https://raw.githubusercontent.com/MUYU212/springSecurityCodeAuditStudyProject/main/st2Project/Note/Note.assets/image-20230412174049602.png)

运行访问index接口

![image-20230412174116872](https://raw.githubusercontent.com/MUYU212/springSecurityCodeAuditStudyProject/main/st2Project/Note/Note.assets/image-20230412174116872.png)

访问sayHi接口，成功跳转到`hello.jsp`中

![image-20230412174156731](https://raw.githubusercontent.com/MUYU212/springSecurityCodeAuditStudyProject/main/st2Project/Note/Note.assets/image-20230412174156731.png)

而且确实也成功的执行了方法中的print方法

![image-20230412174232285](https://raw.githubusercontent.com/MUYU212/springSecurityCodeAuditStudyProject/main/st2Project/Note/Note.assets/image-20230412174232285.png)

接下来要尝试接收请求中的参数，然后将参数进行对应的处理，突然想好了本项目的目标，就是实现一个使用Hibernate进行简易的CURD操作，然后写一个Hibernate存在SQL注入的情况以及对应的修复方案，然后实现一个使用spring security进行鉴权的系统，该项目就算是告一段落了。
