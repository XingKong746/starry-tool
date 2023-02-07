package cn.starrys.mail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 邮件配置。
 * <p>
 * creationTime: 2022/12/12 15:27
 *
 * @author XingKong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailProps {

    /**
     * 主机。
     */
    private @NotNull String host;

    /**
     * 主机端口。
     */
    private @NotNull Integer port;

    /**
     * 发件邮箱。
     */
    private @NotNull String from;

    /**
     * 发件邮箱授权码。
     */
    private @NotNull String password;

    /**
     * 昵称。
     */
    private String nickname;

    /**
     * 启用 ssl。
     */
    private boolean ssl = true;

    /**
     * 开启debug模式。
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
