package cn.starrys.tool.mail;

import cn.starrys.tool.mail.entity.Mail;
import cn.starrys.tool.mail.entity.MailAddressee;
import cn.starrys.tool.mail.entity.MailProps;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 邮件工具。
 * <p>
 * creationTime: 2023/1/12 13:31 .
 *
 * @author XingKong
 */
@Slf4j
public class MailTools {

    /**
     * 邮件配置。
     */
    private final @NotNull MailProps mailProps;

    /**
     * 最简构造。
     *
     * @param host     主机。
     * @param port     主机端口。
     * @param from     发件邮箱。
     * @param password 发件邮箱授权码。
     */
    public MailTools(@NotNull String host, @NotNull Integer port, @NotNull String from, @NotNull String password) {
        this(host, port, from, password, null);
    }

    /**
     * 重载构造。
     *
     * @param host     主机。
     * @param port     主机端口。
     * @param from     发件邮箱。
     * @param password 发件邮箱授权码。
     * @param nickname 昵称。
     */
    public MailTools(@NotNull String host, @NotNull Integer port, @NotNull String from, @NotNull String password, String nickname) {
        this(host, port, from, password, nickname, true);
    }

    /**
     * 重载构造。
     *
     * @param host     主机。
     * @param port     主机端口。
     * @param from     发件邮箱。
     * @param password 发件邮箱授权码。
     * @param nickname 昵称。
     * @param ssl      启用 ssl。
     */
    public MailTools(@NotNull String host, @NotNull Integer port, @NotNull String from, @NotNull String password, String nickname, boolean ssl) {
        this(host, port, from, password, nickname, ssl, false);
    }

    /**
     * 重载构造。
     *
     * @param host     主机。
     * @param port     主机端口。
     * @param from     发件邮箱。
     * @param password 发件邮箱授权码。
     * @param nickname 昵称。
     * @param ssl      启用 ssl。
     * @param debug    开启debug模式。
     */
    public MailTools(@NotNull String host, @NotNull Integer port, @NotNull String from, @NotNull String password, String nickname, boolean ssl, boolean debug) {
        this(new MailProps(host, port, from, password, nickname, ssl, debug, StandardCharsets.UTF_8, true));
    }

    /**
     * 最终构造方法
     *
     * @param mailProps {@link MailProps} 邮件配置。
     */
    public MailTools(@NotNull MailProps mailProps) {
        this.mailProps = mailProps;
    }

    /**
     * 获取 {@link Session} 对象.
     *
     * @param protocol 指定协议
     * @return {@link Session Session 对象}。
     */
    public Session getSession(@NotNull MailProps.Protocols protocol) {
        Properties properties = new Properties();

        if (MailProps.Protocols.SMTP == protocol) {
            // 发送邮件时分配给协议的名称
            properties.setProperty("mail.transport.protocol", protocol.get());
        } else {
            // 接收邮件时分配给协议的名称
            properties.setProperty("mail.store.protocol", protocol.get());
        }

        // 邮箱服务器地址
        properties.setProperty("mail.host", mailProps.getHost());

        // 端口
        properties.setProperty(String.format("mail.%s.port", protocol), mailProps.getPort().toString());

        // 发件邮箱
        properties.setProperty("mail.from", mailProps.getFrom());

        // 发件邮箱授权码
        properties.setProperty("mail.password", mailProps.getPassword());

        Optional.ofNullable(mailProps.getNickname()).ifPresent(name ->
                // 发信昵称
                properties.setProperty("mail.user", name)
        );

        if (mailProps.isSsl()) {
            // 开启 ssl
            properties.setProperty(String.format("mail.%s.ssl.enable", protocol), "true");
            properties.setProperty(String.format("mail.%s.ssl.socketFactory", protocol), "javax.net.ssl.SSLSocketFactory");
        }

        if (mailProps.isAuth()) {
            // 开启鉴权
            properties.setProperty(String.format("mail.%s.auth", protocol), "true");
        }

        if (mailProps.isDebug()) {
            // Debug
            properties.setProperty("mail.debug", "true");
        }

        return getSession(properties);
    }

    /**
     * 最终获取 {@link Session} 对象方法。
     *
     * @param props properties
     * @return {@link Session Session 对象}。
     */
    private Session getSession(Properties props) {
        if (mailProps.isAuth()) {
            return Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailProps.getFrom(), mailProps.getPassword());
                }
            });
        }
        return Session.getDefaultInstance(props);
    }

    /**
     * 创建邮件。
     *
     * @param session {@link Session Session}对象。
     * @param mail    {@link Mail} 邮件内容。
     * @return 一封邮件。
     */
    public MimeMessage createMimeMessage(Session session, @NotNull Mail mail) {
        try {
            MimeMessage mimeMessage = new MimeMessage(session);

            // 发件人
            mimeMessage.setFrom(new InternetAddress(mailProps.getFrom(), mailProps.getNickname(), mailProps.getCharset().name()));

            // 收件人
            mimeMessage.setRecipients(Message.RecipientType.TO, createInternetAddresses(mail.getTo()));

            List<MailAddressee> cc = mail.getCc();
            if (cc != null) {
                // 抄送人
                mimeMessage.setRecipients(Message.RecipientType.CC, createInternetAddresses(cc));
            }

            List<MailAddressee> bcc = mail.getBcc();
            if (bcc != null) {
                // 密送人
                mimeMessage.setRecipients(Message.RecipientType.BCC, createInternetAddresses(bcc));
            }

            // 邮件主题
            mimeMessage.setSubject(mail.getSubject(), mailProps.getCharset().name());

            // 邮件内容
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            if (mail.getType() == Mail.Type.TEXT) {
                // text格式
                mimeBodyPart.setText(mail.getBody(), mailProps.getCharset().name());
            } else {
                // html格式
                mimeBodyPart.setContent(mail.getBody(), "text/html;charset=" + mailProps.getCharset().name());
            }

            // 邮件体
            Multipart mimeMultipart = new MimeMultipart("mixed");

            // 添加邮件内容
            mimeMultipart.addBodyPart(mimeBodyPart);

            // 附件
            List<File> attachments = mail.getAttachments();
            if (attachments != null) {
                for (File attachment : attachments) {
                    MimeBodyPart fileBodyPart = new MimeBodyPart();
                    fileBodyPart.attachFile(attachment);
                    mimeMultipart.addBodyPart(fileBodyPart);
                }
            }

            // 设置邮件体
            mimeMessage.setContent(mimeMultipart);

            // 发送时间
            mimeMessage.setSentDate(new Date());

            // 保存上面设置的邮件
            mimeMessage.saveChanges();

            return mimeMessage;
        } catch (UnsupportedEncodingException e) {
            log.error("不支持 “{}” 此编码", mailProps.getCharset(), e);
        } catch (MessagingException e) {
            log.error("创建邮件信息异常！", e);
        } catch (IOException e) {
            log.error("添加附件异常！", e);
        }
        return null;
    }

    /**
     * 创建 {@link InternetAddress} 对象。
     *
     * @param addresseeList {@link MailAddressee} 邮件收件人列表。
     * @return {@link InternetAddress} 对象数组。
     */
    private InternetAddress @NotNull [] createInternetAddresses(@NotNull List<MailAddressee> addresseeList) throws UnsupportedEncodingException {
        InternetAddress[] internetAddresses = new InternetAddress[addresseeList.size()];
        for (int i = 0; i < internetAddresses.length; i++) {
            MailAddressee mailAddressee = addresseeList.get(i);
            internetAddresses[i] = new InternetAddress(mailAddressee.getAddressee(), mailAddressee.getNickname(), mailProps.getCharset().name());
        }
        return internetAddresses;
    }

    /**
     * 发送邮件。
     *
     * @param addressee 收件人。
     * @param subject   邮件主题（标题）。
     * @param body      邮件内容（主体）。
     * @return 发送结果。
     */
    public boolean send(String addressee, String subject, String body) {
        return send(addressee, null, subject, body);
    }

    /**
     * 发送邮件。
     *
     * @param addressee 收件人。
     * @param nickname  收件者昵称。
     * @param subject   邮件主题（标题）。
     * @param body      邮件内容（主体）。
     * @return 发送结果。
     */
    public boolean send(String addressee, String nickname, String subject, String body) {
        return send(addressee, nickname, subject, body, null);
    }

    /**
     * 发送邮件。
     *
     * @param addressee   收件人。
     * @param nickname    收件者昵称。
     * @param subject     邮件主题（标题）。
     * @param body        邮件内容（主体）。
     * @param attachments 附件。
     * @return 发送结果。
     */
    public boolean send(String addressee, String nickname, String subject, String body, List<File> attachments) {
        ArrayList<MailAddressee> to = new ArrayList<>();
        to.add(new MailAddressee(addressee, nickname));
        return send(to, subject, body, attachments);
    }

    /**
     * 发送邮件。
     *
     * @param to          收件人(主要收件人)。
     * @param subject     邮件主题（标题）。
     * @param body        邮件内容（主体）。
     * @param attachments 附件。
     * @return 发送结果。
     */
    public boolean send(List<MailAddressee> to, String subject, String body, List<File> attachments) {
        return send(new Mail(to, subject, body, attachments, Mail.Type.HTML, null, null));
    }

    /**
     * 发送邮件。
     *
     * @param mail {@link Mail} 邮件内容。
     * @return 发送结果。
     */
    public boolean send(Mail mail) {
        return send(createMimeMessage(getSession(MailProps.Protocols.SMTP), mail));
    }

    /**
     * 最终执行发送方法。
     *
     * @param msg 一封邮件。
     * @return 发送结果。
     */
    public boolean send(MimeMessage msg) {
        if (mailProps.isAuth()) {
            try {
                Transport.send(msg);
            } catch (MessagingException e) {
                log.error("邮件发送失败！", e);
                return false;
            }
        } else {
            Session session = msg.getSession();
            try (Transport transport = session.getTransport()) {
                transport.connect(mailProps.getHost(), mailProps.getPort(), mailProps.getFrom(), mailProps.getPassword());
                transport.sendMessage(msg, msg.getAllRecipients());
            } catch (MessagingException e) {
                log.error("邮件发送失败！", e);
                return false;
            }
        }
        return true;
    }

}
