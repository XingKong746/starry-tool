package cn.starrys.tool.core.constant;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * 一个接口，用于表示请求结果的状态。<br>
 * 该接口内包含了一个默认方法来判断请求结果的不同系列，根据具体需要使用。<br>
 * 需要创建一个枚举实现<br>
 * Created: 2024/12/7 12:23 .
 *
 * @author XingKong
 */
public interface IResultStatus extends Serializable {
    @Serial
    long serialVersionUID = 5327958315156039L;

    /**
     * 获取请求是否成功的状态。
     *
     * @return 如果请求成功则返回 {@code true}，否则返回 {@code false}。
     */
    Boolean getStatus();

    /**
     * 获取状态码。
     *
     * @return 状态码。
     */
    Integer getCode();

    /**
     * 获取状态信息。
     *
     * @return 状态信息。
     */
    String getMessage();


    /**
     * 根据需求扩展使用。
     * 若要使用请必须重写该方法。
     *
     * @return 状态系列
     */
    default Series getSeries() {
        return Series.valueOf(getCode());
    }

    /**
     * 判断状态系列是否为信息性系列。
     *
     * @return 如果状态系列为信息性系列，则返回true；否则返回false。
     */
    default boolean isInformational() {
        return (getSeries() == Series.INFORMATIONAL);
    }

    /**
     * 判断状态系列是否为成功系列。
     *
     * @return 如果状态系列为成功系列，则返回true；否则返回false。
     */
    default boolean isSuccessful() {
        return (getSeries() == Series.SUCCESSFUL);
    }

    /**
     * 判断状态系列是否为重定向系列。
     *
     * @return 如果状态系列为重定向系列，则返回true；否则返回false。
     */
    default boolean isRedirection() {
        return (getSeries() == Series.REDIRECTION);
    }

    /**
     * 判断状态系列是否为客户端错误系列。
     *
     * @return 如果状态系列为客户端错误系列，则返回true；否则返回false。
     */
    default boolean isClientError() {
        return (getSeries() == Series.CLIENT_ERROR);
    }

    /**
     * 判断状态系列是否为服务器错误系列。
     *
     * @return 如果状态系列为服务器错误系列，则返回true；否则返回false。
     */
    default boolean isServerError() {
        return (getSeries() == Series.SERVER_ERROR);
    }

    /**
     * 判断状态系列是否为错误系列（客户端错误或服务器错误）。
     *
     * @return 如果状态系列为错误系列，则返回true；否则返回false。
     */
    default boolean isError() {
        return (isClientError() || isServerError());
    }

    /**
     * 状态系列的枚举。
     */
    @Getter
    enum Series {
        /**
         * 信息，服务器收到请求，需要请求者继续执行操作
         */
        INFORMATIONAL(1),
        /**
         * 成功，操作被成功接收并处理
         */
        SUCCESSFUL(2),
        /**
         * 重定向，需要进一步的操作以完成请求
         */
        REDIRECTION(3),
        /**
         * 客户端错误，请求包含语法错误或无法完成请求
         */
        CLIENT_ERROR(4),
        /**
         * 服务器错误，服务器在处理请求的过程中发生了错误
         */
        SERVER_ERROR(5),
        /**
         * 保留
         */
        OTHER(6),
        ;
        /**
         * -- GETTER --
         * 获取此状态系列的整数值。
         */
        private final int value;

        Series(int value) {
            this.value = value;
        }

        /**
         * 将给定的状态码解析为{@link Series}序列状态。
         *
         * @param code 要解析的状态码
         * @return 匹配的的 {@link Series}，如果没有找到，则为{@code null}
         */
        public static Series resolve(Integer code) {
            if (code != null) {
                while (code >= 10) {
                    code /= 10;
                }
                for (Series series : values()) {
                    if (series.value == code) {
                        return series;
                    }
                }
            }
            return null;
        }

        /**
         * 返回与给定状态码对应的Series枚举实例，
         * 如果没有找到匹配的枚举实例，则返回 {@code OTHER}。
         *
         * @param code 状态码
         * @return 对应的Series枚举实例，如果没有匹配的则返回{@code Series.OTHER}。
         */
        public static Series valueOf(int code) {
            Optional<Series> seriesOptional = Optional.ofNullable(resolve(code));
            return seriesOptional.orElse(OTHER);
        }
    }
}
