# starry-tool

## 星空工具
starry-tool

## 使用说明

### starry-mail

1. 引入依赖

   - **需要 jdk17 ，若非 jdk17 修改依赖版本 为 2.7.7**
   - Maven

     ```xml
     <dependency>
         <groupId>cn.starrys</groupId>
         <artifactId>starry-mail</artifactId>
         <version>3.1.0</version>
     </dependency>
     ```

   - Gradle

     ```groovy
     implementation("cn.starrys:starry-mail:3.1.0")
     ```

2. 演示

   ```java
   public class Demo {
       public static void main(String[] args) {
           // 创建邮件工具
           MailTools mailTools = new MailTools("smtp.163.com", 465, 发件邮箱, 发件邮箱授权码, "⭐");
           // 发送邮件
           boolean sendStatus = mailTools.send(收件邮箱, "你好呀", "QwQ");
       }
   }
   ```

### starry-mail-spring-boot-starter

1. 引入依赖

   - **需要 jdk17，spring-boot3.x，若非 spring-boot3.x 修改依赖版本 为 2.7.7**
   - Maven

     ```xml
     <dependency>
         <groupId>cn.starrys</groupId>
         <artifactId>starry-mail-spring-boot-starter</artifactId>
         <version>3.1.0</version>
     </dependency>
     ```

   - Gradle

     ```groovy
     implementation("cn.starrys:starry-mail-spring-boot-starter:3.1.0")
     ```

2. 演示

   - application.yml

     ```yaml
     starry:
       mail:
         host: 邮件主机
         port: 主机端口
         from: 发件邮箱
         password: 发件邮箱授权码
         nickname: "昵称"
     ```

   - SpringBootApplicationTests.java

     ```java
     @SpringBootTest
     class SpringBootApplicationTests {
         @Autowired
         private MailTools mailTools;
         @Test
         void sendMailTest() {
             mailTools.send("收件邮箱", "收件者昵称", "标题", "内容");
         }
     }
     ```

### starry-json

1. 引入依赖

   - Maven

     ```xml
     <dependency>
         <groupId>cn.starrys</groupId>
         <artifactId>starry-json</artifactId>
         <version>1.1.0</version>
     </dependency>
     ```

   - Gradle

     ```groovy
     implementation("cn.starrys:starry-json:1.1.0")
     ```

2. 演示

   ```java
   public class Demo {
       /**
        * <pre>
        * {
        *   "code": 200,
        *   "message": "成功",
        *   "data": {
        *     "username": "用户名0",
        *     "password": "密码0",
        *     "roles": [
        *       {
        *         "roleDescription": "角色描述0",
        *         "roleName": "角色0",
        *         "permissions": [
        *           {
        *             "permissionName": "权限0",
        *             "permissionDescription": "权限描述0"
        *           },
        *           {
        *             "permissionName": "权限1",
        *             "permissionDescription": "权限描述1"
        *           }
        *         ]
        *       }
        *     ]
        *   }
        * }
        * </pre>
        */
       static String json = "{\"code\":200,\"message\":\"成功\",\"data\":{\"username\":\"用户名0\",\"password\":\"密码0\",\"roles\":[{\"roleDescription\":\"角色描述0\",\"roleName\":\"角色0\",\"permissions\":[{\"permissionName\":\"权限0\",\"permissionDescription\":\"权限描述0\"},{\"permissionName\":\"权限1\",\"permissionDescription\":\"权限描述1\"}]}]}}";
       public static void main(String[] args) {
           Map value = JsonTools.getValue(json, "data.roles[0].permissions[1]", Map.class);
           System.out.println(value.get("permissionName"));
       }
   }
   ```

