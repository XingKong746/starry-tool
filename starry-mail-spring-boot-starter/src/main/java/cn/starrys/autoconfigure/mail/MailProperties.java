package cn.starrys.autoconfigure.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 邮件配置。
 * <p>
 * creationTime: 2023/1/13 18:01 .
 *
 * @author XingKong
 */
@Data
@ConfigurationProperties(prefix = MailProperties.PROPERTIES_PREFIX)
public class MailProperties {

    /**
     * 配置属性前缀。
     */
    public static final String PROPERTIES_PREFIX = "starry.mail";

    /**
     * 主机。
     */
    private String host;

    /**
     * 主机端口。
     */
    private Integer port;

    /**
     * 邮箱地址。
     */
    private String from;

    /**
     * 邮箱授权码。
     */
    private String password;

    /**
     * 昵称。
     */
    private String nickname;

    /**
     * 启用 ssl。
     */
    private boolean ssl = true;

    /**
     * 开启 debug 模式。
     */
    private boolean debug = false;

    /**
     * 邮件编码。
     */
    private Charset charset = StandardCharsets.UTF_8;

    /**
     * 开启鉴权。
     */
    private boolean auth = true;

}
