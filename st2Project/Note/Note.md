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

在`struts.xml`文件中存在一个`namespace`的属性，这个属性也会帮助我们寻找接口路径，我们修改文件,这里我做了两处修改，一处是加上了namespace属性，一处是将`<result>`标签中的jsp文件指定了根路径下的jsp文件，如果不指定的话，访问接口会找到/test/hello.jsp文件去,所以这里要指定上文件，重新运行代码

```xml
<package name="test" extends="struts-default" namespace="/test">
    <action name="sayHi" class="com.red.action.HelloAction" method="sayHi">
        <result name="success">/hello.jsp</result>
        <result name="error">/error.jsp</result>
    </action>
</package>
```

原本访问http://localhost:8081/sayHi.action就可以找到现在会提示404

![image-20230413181914713](https://github.com/MUYU212/springSecurityCodeAuditStudyProject/blob/main/st2Project/Note/Note.assets/image-20230413181914713.png?raw=true)

需要访问http://localhost:8081/test/sayHi.action

![image-20230413181936868](https://github.com/MUYU212/springSecurityCodeAuditStudyProject/blob/main/st2Project/Note/Note.assets/image-20230413181936868.png?raw=true)

# 0x02 接收请求参数

因为多数项目都是采用MVC架构的我们就选择使用实体类来接受前端请求的参数的方式，这里用到了`lombok`插件（非必要），自行学习其使用方法

在源代码文件夹中的`action`目录下新建一个`pojo`目录用来存放实体类，新建一个`User.java`文件并且属性配置好。

```java
package com.red.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private int age;
}
```

在`action`文件夹下新建一个`LoginAction.java`文件，这里记得继承`ActionSupport`类，以及实现`ModelDriven<User>`接口，这里尖括号里是填写我们新定义的类（操作是这么操作，但是原理没仔细研究），然后一定要写一个`getModel()`方法，返回一个user对象才可以不报错。

```java
package com.red.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.red.pojo.User;

public class LoginAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();
    public String login(){

        return "login";
    }
    public String logon(){
        System.out.println(user.getUsername()+"====="+user.getPassword());
        if (user.getPassword().equals("123456")){
            return "success";
        }
        return "error";
    }
    public User getModel() {
        return user;
    }
}
```

之后在`webapps`目录下新建`login.jsp`  `success.jsp`两个文件，成功页面的内容自定义就好，`login.jsp`的内容如下，其中存在一个登录框，然后发送请求到/user/logon.action中。

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login JSP</title>
</head>
<body>
<h2>Login JSP</h2>
<form action="/user/logon.action" method="post">
  <input type="text" name="username"></br>
  <input type="password" name="password"></br>
  <button>登录</button>
</form>
</body>
</html>
```

最后就是修改`struts.xml`文件，这里`namespace`我更换成了`/user`，然后声明了两个接口一个`login`接口是跳转到`login.jsp`的作用，另一个是接收提交数据的。（我看有一些代码里是这么实现登录的，但是觉得有点奇怪，感觉这么写很麻烦）

```xml
<package name="user" extends="struts-default" namespace="/user">
    <action name="sayHi" class="com.red.action.HelloAction" method="sayHi">
        <result name="success">/hello.jsp</result>
        <result name="error">/error.jsp</result>
    </action>
    <action name="login" class="com.red.action.LoginAction" method="login">
        <result name="login">/login.jsp</result>
    </action>
    <action name="logon" class="com.red.action.LoginAction" method="logon">
        <result name="success">/success.jsp</result>
        <result name="error">/error.jsp</result>
    </action>
</package>
```

那么项目的目录结构就变成了以下这样。

![image-20230414112931990](https://github.com/MUYU212/springSecurityCodeAuditStudyProject/blob/main/st2Project/Note/Note.assets/image-20230414112931990.png?raw=true)

运行系统，访问http://localhost:8081/user/login，成功访问到，根据我们上边的代码逻辑，密码输入123456就可以成功跳转到成功页面

![image-20230414122023698](https://github.com/MUYU212/springSecurityCodeAuditStudyProject/blob/main/st2Project/Note/Note.assets/image-20230414122023698.png?raw=true)

成功跳转并且，控制台也打印到对应的信息了。

![image-20230414122116172](https://github.com/MUYU212/springSecurityCodeAuditStudyProject/blob/main/st2Project/Note/Note.assets/image-20230414122116172.png?raw=true)

![image-20230414122150324](https://github.com/MUYU212/springSecurityCodeAuditStudyProject/blob/main/st2Project/Note/Note.assets/image-20230414122150324.png?raw=true)

接下来要尝试接收请求中的参数，然后将参数进行对应的处理，突然想好了本项目的目标，就是实现一个使用Hibernate进行简易的CURD操作，然后写一个Hibernate存在SQL注入的情况以及对应的修复方案，然后实现一个使用spring security进行鉴权的系统，该项目就算是告一段落了。
