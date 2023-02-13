package cn.starrys.mail;

import cn.starrys.mail.entity.*;
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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
     * 主机。
     */
    private final @NotNull String host;

    /**
     * 主机端口。
     */
    private final @NotNull Integer port;

    /**
     * 发件邮箱。
     */
    private final @NotNull String from;

    /**
     * 发件邮箱授权码。
     */
    private final @NotNull String password;

    /**
     * 昵称。
     */
    private final String nickname;

    /**
     * 启用 ssl。
     */
    private final boolean ssl;

    /**
     * 开启debug模式。
     */
    private final boolean debug;

    /**
     * 邮件编码。
     */
    private final Charset charset;

    /**
     * 开启鉴权。
     */
    private final boolean auth;

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
        this(host, port, from, password, nickname, ssl, false, StandardCharsets.UTF_8);
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
     * @param charset  邮件编码。
     */
    public MailTools(@NotNull String host, @NotNull Integer port, @NotNull String from, @NotNull String password, String nickname, boolean ssl, boolean debug, Charset charset) {
        this(host, port, from, password, nickname, ssl, debug, charset, true);
    }

    /**
     * 全参构造。
     *
     * @param host     主机。
     * @param port     主机端口。
     * @param from     发件邮箱。
     * @param password 发件邮箱授权码。
     * @param nickname 昵称。
     * @param ssl      启用 ssl。
     * @param debug    开启debug模式。
     * @param charset  邮件编码。
     * @param auth     开启鉴权。
     */
    public MailTools(@NotNull String host, @NotNull Integer port, @NotNull String from, @NotNull String password, String nickname, boolean ssl, boolean debug, Charset charset, boolean auth) {
        this(new MailProps(host, port, from, password, nickname, ssl, debug, charset, auth));
    }

    /**
     * 最终构造方法
     *
     * @param props {@link MailProps} 邮件配置。
     */
    public MailTools(@NotNull MailProps props) {
        this.host = props.getHost();
        this.port = props.getPort();
        this.from = props.getFrom();
        this.password = props.getPassword();
        this.nickname = props.getNickname();
        this.ssl = props.isSsl();
        this.debug = props.isDebug();
        this.charset = props.getCharset();
        this.auth = props.isAuth();
    }

    /**
     * 获取 {@link Session} 对象.
     *
     * @param protocol 指定协议
     * @return {@link Session Session 对象}。
     */
    public Session getSession(@NotNull MailProtocol protocol) {
        Properties properties = new Properties();

        switch (protocol) {
            case IMAP:
            case POP3:
                // 接收邮件时分配给协议的名称
                properties.setProperty("mail.store.protocol", protocol.get());
                break;
            case SMTP:
            default:
                // 发送邮件时分配给协议的名称
                properties.setProperty("mail.transport.protocol", protocol.get());
                break;
        }

        // 邮箱服务器地址
        properties.setProperty("mail.host", host);

        // 端口
        properties.setProperty(String.format("mail.%s.port", protocol), port.toString());

        // 发件邮箱
        properties.setProperty("mail.from", from);

        // 发件邮箱授权码
        properties.setProperty("mail.password", password);

        if (nickname != null) {
            // 发信昵称
            properties.setProperty("mail.user", nickname);
        }

        if (ssl) {
            // 开启 ssl
            properties.setProperty(String.format("mail.%s.ssl.enable", protocol), "true");
            properties.setProperty(String.format("mail.%s.ssl.socketFactory", protocol), "javax.net.ssl.SSLSocketFactory");

            // 第二种开启 SSL 方法
            /*
            MailSSLSocketFactory 。 mailSSLSocketFactory = new MailSSLSocketFactory()
            mailSSLSocketFactory.setTrustedHosts(host)
            props.setProperty("mail." + protocol + ".ssl.enable", "true")
            props.setProperty("mail." + protocol + ".ssl.socketFactory", mailSSLSocketFactory.toString())
            */
        }

        if (auth) {
            // 开启鉴权
            properties.setProperty(String.format("mail.%s.auth", protocol), "true");
        }

        if (debug) {
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
        if (auth) {
            return Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
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
            mimeMessage.setFrom(new InternetAddress(from, nickname, charset.name()));

            List<MailAddressee> mailTo = mail.getTo();
            InternetAddress[] to = new InternetAddress[mailTo.size()];
            for (int i = 0; i < to.length; i++) {
                MailAddressee addressee = mailTo.get(i);
                to[i] = new InternetAddress(addressee.getAddressee(), addressee.getNickname(), charset.name());
            }
            // 收件人
            mimeMessage.setRecipients(Message.RecipientType.TO, to);

            List<MailAddressee> mailCc = mail.getCc();
            // 抄送人可以没有
            if (mailCc != null) {
                InternetAddress[] cc = new InternetAddress[mailCc.size()];
                for (int i = 0; i < cc.length; i++) {
                    MailAddressee addressee = mailCc.get(i);
                    cc[i] = new InternetAddress(addressee.getAddressee(), addressee.getNickname(), charset.name());
                }
                // 抄送人
                mimeMessage.setRecipients(Message.RecipientType.CC, cc);
            }

            List<MailAddressee> mailBcc = mail.getBcc();
            // 密送人可以没有
            if (mailBcc != null) {
                InternetAddress[] bcc = new InternetAddress[mailBcc.size()];
                for (int i = 0; i < bcc.length; i++) {
                    MailAddressee addressee = mailBcc.get(i);
                    bcc[i] = new InternetAddress(addressee.getAddressee(), addressee.getNickname(), charset.name());
                }
                // 密送人
                mimeMessage.setRecipients(Message.RecipientType.BCC, bcc);
            }

            // 邮件主题
            mimeMessage.setSubject(mail.getSubject(), charset.name());

            // 邮件内容
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            switch (mail.getMailType()) {
                case TEXT:
                    // text格式
                    mimeBodyPart.setText(mail.getBody(), charset.name());
                    break;
                case HTML:
                default:
                    // html格式
                    mimeBodyPart.setContent(mail.getBody(), "text/html;charset=" + charset.name());
                    break;
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
            log.error("不支持 “{}” 此编码", charset, e);
        } catch (MessagingException e) {
            log.error("创建邮件信息异常！", e);
        } catch (IOException e) {
            log.error("添加附件异常！", e);
        }
        return null;
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
        return send(to, subject, body, attachments, MailType.HTML);
    }

    /**
     * 发送邮件。
     *
     * @param to          收件人(主要收件人)。
     * @param subject     邮件主题（标题）。
     * @param body        邮件内容（主体）。
     * @param attachments 附件。
     * @param mailType    邮件类型（HTML,TEXT）
     * @return 发送结果。
     */
    public boolean send(List<MailAddressee> to, String subject, String body, List<File> attachments, MailType mailType) {
        return send(to, subject, body, attachments, mailType, null, null);
    }

    /**
     * 发送邮件。
     *
     * @param to          收件人(主要收件人)。
     * @param subject     邮件主题（标题）。
     * @param body        邮件内容（主体）。
     * @param attachments 附件。
     * @param mailType    邮件类型（HTML,TEXT）
     * @param cc          抄送(carbon copy)收件人。
     * @param bcc         密送(blind carbon copy)收件人。
     * @return 发送结果。
     */
    public boolean send(List<MailAddressee> to, String subject, String body, List<File> attachments, MailType mailType, List<MailAddressee> cc, List<MailAddressee> bcc) {
        return send(new Mail(to, subject, body, attachments, mailType, cc, bcc));
    }

    /**
     * 发送邮件。
     *
     * @param mail {@link Mail} 邮件内容。
     * @return 发送结果。
     */
    public boolean send(Mail mail) {
        return send(createMimeMessage(getSession(MailProtocol.SMTP), mail));
    }

    /**
     * 最终执行发送方法。
     *
     * @param msg 一封邮件。
     * @return 发送结果。
     */
    public boolean send(MimeMessage msg) {
        if (auth) {
            try {
                Transport.send(msg);
            } catch (MessagingException e) {
                log.error("邮件发送失败！", e);
                return false;
            }
        } else {
            Session session = msg.getSession();
            try (Transport transport = session.getTransport()) {
                transport.connect(host, port, from, password);
                transport.sendMessage(msg, msg.getAllRecipients());
            } catch (MessagingException e) {
                log.error("邮件发送失败！", e);
                return false;
            }
        }
        return true;
    }

}
