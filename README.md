# starry-tool

## 星空工具
starry-tool

## 使用说明

### starry-mail

1. 引入依赖

   - Maven

     ```xml
     <dependency>
         <groupId>cn.starrys</groupId>
         <artifactId>starry-mail</artifactId>
         <version>3.0.2</version>
     </dependency>
     ```

   - Gradle

     ```groovy
     implementation("cn.starrys:starry-mail:3.0.2")
     ```

2. 简单演示

   ```java
   public class Demo {
       public static void main(String[] args) {
           // 邮件配置
           MailProps mailProps = new MailProps();
           // 设置邮件主机
           mailProps.setHost("smtp.163.com");
           // 设置主机端口
           mailProps.setPort(465);
           // 发件人邮箱
           mailProps.setFrom("xxx@163.com");
           // 授权码
           mailProps.setPassword("YUJOLACXLxxxxxxx");
           // 发件者昵称
           mailProps.setNickname("星空");
   
           // 一封邮件
           Mail mail = new Mail();
           ArrayList<MailAddressee> to = new ArrayList<>();
           // 创建收件人
           to.add(new MailAddressee("330139xxxx@qq.com", "收件人昵称"));
           to.add(new MailAddressee("184160xxxx@qq.com", "收件人昵称"));
           // 设置收件人
           mail.setTo(to);
           // 邮件标题
           mail.setSubject("测试邮件");
           // 邮件内容
           mail.setBody("你好");
   
           MailTools mailTools = new MailTools(mailProps);
           // 发送邮件
           boolean sendStatus = mailTools.send(mail);
           System.out.println("发送状态：" + sendStatus);
   
           // 简单方法
           // mailTools.send("330139xxxx@qq.com", "收件者昵称", "标题", "内容");
       }
   }
   ```

### starry-mail-spring-boot-starter

1. 引入依赖

   - Maven

     ```xml
     <dependency>
         <groupId>cn.starrys</groupId>
         <artifactId>starry-mail-spring-boot-starter</artifactId>
         <version>3.0.2</version>
     </dependency>
     ```

   - Gradle

     ```groovy
     implementation("cn.starrys:starry-mail-spring-boot-starter:3.0.2")
     ```

2. 简单演示

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
             mailTools.send("330139xxxx@qq.com", "收件者昵称", "标题", "内容");
         }
     }
     ```

