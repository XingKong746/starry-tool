package cn.starrys.tool.mail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

/**
 * 邮件内容。
 * <p>
 * creationTime: 2022/12/5 9:43
 *
 * @author XingKong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    /**
     * 收件人(主要收件人)。
     */
    private @NotNull List<MailAddressee> to;

    /**
     * 邮件主题（标题）。
     */
    private @NotNull String subject;

    /**
     * 邮件内容（主体）。
     */
    private @NotNull String body;

    /**
     * 附件。
     */
    private List<File> attachments;

    /**
     * 邮件类型（HTML,TEXT）
     */
    private Type type = Type.HTML;

    /**
     * 抄送(carbon copy)收件人。
     */
    private List<MailAddressee> cc;

    /**
     * 密送(blind carbon copy)收件人。
     */
    private List<MailAddressee> bcc;

    /**
     * 邮件类型。
     */
    public enum Type {
        /**
         * HTML类型
         */
        HTML,
        /**
         * 文本类型
         */
        TEXT
    }

}
