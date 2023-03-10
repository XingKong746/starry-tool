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

    /**
     * 邮件协议。
     */
    public enum Protocols {
        /**
         * smtp 协议。
         */
        SMTP("smtp"),
        /**
         * imap 协议。
         */
        IMAP("imap"),
        /**
         * pop3 协议。
         */
        POP3("pop3");
        /**
         * 协议字符串。
         */
        private final String protocol;

        Protocols(String protocol) {
            this.protocol = protocol;
        }

        /**
         * 获取该协议的字符串名称。
         *
         * @return 该协议的字符串名称。
         */
        public String get() {
            return protocol;
        }

        @Override
        public String toString() {
            return get();
        }
    }
}
