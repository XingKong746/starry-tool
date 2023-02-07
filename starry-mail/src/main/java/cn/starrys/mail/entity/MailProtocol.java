package cn.starrys.mail.entity;

/**
 * creationTime: 2023/1/12 11:35 .
 *
 * @author XingKong
 */
public enum MailProtocol {

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

    private final String protocol;

    MailProtocol(String protocol) {
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
