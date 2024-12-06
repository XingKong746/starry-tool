package cn.starrys.tool.mail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件收件人。
 * <p>
 * creationTime: 2022/12/12 16:28
 *
 * @author XingKong
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailAddressee {

    /**
     * 收件人。
     */
    private String addressee;

    /**
     * 收件者昵称。
     */
    private String nickname;

}
