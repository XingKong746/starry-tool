package cn.starrys.mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * creationTime: 2023/3/10 20:19 .
 *
 * @author XingKong
 */
class MailToolsTests {

    private static Properties props;

    @BeforeAll
    static void init() throws IOException {
        Properties properties = new Properties();
        // 从 classpath(Resource) 路径下读取配置文件 // 涉及机密，就不上传到 git
        InputStream inputStream = MailToolsTests.class.getClassLoader().getResourceAsStream("mail.properties");
        properties.load(inputStream);
        props = properties;
    }

    @Test
    void sendTest() {
        String from = props.getProperty("from");
        String password = props.getProperty("password");
        String addressee = props.getProperty("addressee");

        // 创建邮件工具
        MailTools mailTools = new MailTools("smtp.163.com", 465, from, password, "⭐");

        // 发送邮件
        boolean sendStatus = mailTools.send(addressee, "你好呀", "QwQ");

        // 断言发送成功
        Assertions.assertTrue(sendStatus);
    }

}
