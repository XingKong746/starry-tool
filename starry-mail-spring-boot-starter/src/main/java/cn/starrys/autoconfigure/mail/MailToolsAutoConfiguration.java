package cn.starrys.autoconfigure.mail;

import cn.starrys.mail.MailTools;
import cn.starrys.mail.entity.MailProps;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * MailTools 自动配置类。
 * <p>
 * creationTime: 2023/1/12 12:06 .
 *
 * @author XingKong
 */
@AutoConfiguration
@ConditionalOnMissingBean({MailTools.class})
@EnableConfigurationProperties({MailProperties.class})
public class MailToolsAutoConfiguration {

    /**
     * 创建邮件工具加入ioc容器
     *
     * @param properties 邮件配置
     * @return 邮件工具
     */
    @Bean
    @ConditionalOnProperty(
            prefix = MailProperties.PROPERTIES_PREFIX,
            name = {"host", "port", "from", "password"}
    )
    public MailTools createMailTools(@NotNull MailProperties properties) {
        return new MailTools(new MailProps(
                properties.getHost(),
                properties.getPort(),
                properties.getFrom(),
                properties.getPassword(),
                properties.getNickname(),
                properties.isSsl(),
                properties.isDebug(),
                properties.getCharset(),
                properties.isAuth()
        ));
    }

}
