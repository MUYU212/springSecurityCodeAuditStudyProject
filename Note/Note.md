# 0x00 新建项目

参考这个文章，将空项目构建成一个可以运行的strust2的maven项目

https://www.cnblogs.com/sherlson/articles/16220756.html

新建一个空项目之后开始`New Module`

![image-20230412144548212](/Users/red256/IdeaProjects/springSecurityCodeAuditStudyProject/Note/Note.assets/image-20230412144548212.png)

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

在`resourse`目录下新建`strust.xml`文件，内容如下

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

![image-20230412145437980](/Users/red256/IdeaProjects/springSecurityCodeAuditStudyProject/Note/Note.assets/image-20230412145437980.png)

