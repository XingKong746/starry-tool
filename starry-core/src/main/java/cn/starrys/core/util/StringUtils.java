package cn.starrys.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 字符串工具类。
 * <p>
 * creationTime: 2023/2/18 13:13 .
 *
 * @author XingKong
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class StringUtils {

    /**
     * 回车字符
     */
    public static final String CR = "\r";

    /**
     * 换行字符
     */
    public static final String LF = "\n";

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 空格
     */
    public static final String SPACE = " ";

    /**
     * 判断字符串是否为空
     *
     * @param str 进行判断的字符串
     * @return 当 str 为 null 或者 "" 时返回 true
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 进行判断的字符串
     * @return 当 str 不为 null 或者 "" 时返回 true
     */
    public static boolean notEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }

    /**
     * 拼接字符串
     *
     * @param strings 字符对象数组
     * @return 拼接后的字符串
     */
    public static @NotNull String splices(Object @NotNull ... strings) {
        // 可变字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (Object string : strings) {
            // 追加 str obj
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    /**
     * 计算字符串的MD5（32位长度）
     *
     * @param text 进行计算的文本
     * @return <code>text</code> 的MD5
     */
    public static @NotNull String md5(String text) {
        MessageDigest msgDigest;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("当前系统不支持 MD5 算法。");
        }

        msgDigest.update(text.getBytes(StandardCharsets.UTF_8));

        // msgDigest.digest()：完成计算后的 byte 数组
        BigInteger bigInteger = new BigInteger(1, msgDigest.digest());

        // bigInteger.toString(16)：转换为16进制字符串
        StringBuilder strBuilder = new StringBuilder(bigInteger.toString(16));

        while (strBuilder.length() < 32) {
            // 如果计算出的 MD5 不足32位则在前补"0"
            strBuilder.insert(0, "0");
        }
        return strBuilder.toString();
    }

    /**
     * 计算字符串的MD5（16位长度）
     *
     * @param text 要计算的文本（若为空则返回 ""）
     * @return <code>text</code> 的MD5
     */
    public static @NotNull String md5_16(String text) {
        return md5(text).substring(8, 24);
    }

}
