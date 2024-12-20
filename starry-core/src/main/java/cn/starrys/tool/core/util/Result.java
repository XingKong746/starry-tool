package cn.starrys.tool.core.util;

import cn.starrys.tool.core.constant.IResultStatus;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.StringJoiner;

/**
 * 一个通用类，用于封装操作结果。
 * 包含时间戳、操作状态、状态码、消息和特定数据等信息。
 * <br>
 * Created: 2024/12/6 21:23 .
 *
 * @param <T> 结果中携带的数据类型
 * @author XingKong
 */
@Slf4j
@Getter
public class Result<T> implements IResultStatus, Serializable {
    @Serial
    private static final long serialVersionUID = 4327958315156039L;


    /**
     * 结果创建的时间戳
     */
    private final Instant timestamp;
    /**
     * 操作状态，true 表示成功，false 表示失败,
     * 用于快速获取执行操作的状态。
     */
    private Boolean status;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    @SuppressWarnings("all")
    private T data;


    /**
     * 默认构造函数，初始化时间戳为当前时间
     */
    private Result() {
        timestamp = Instant.now();
    }

    /**
     * 构造函数，初始化时间戳、状态、状态码、消息和数据
     *
     * @param status  操作状态
     * @param code    状态码
     * @param message 消息
     * @param data    数据
     */
    public Result(Boolean status, Integer code, String message, T data) {
        this();
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造函数，初始化时间戳、状态、状态码、消息和数据
     *
     * @param resultStatus 结果状态
     * @param data         数据
     */
    public Result(IResultStatus resultStatus, T data) {
        this(resultStatus.getStatus(), resultStatus.getCode(), resultStatus.getMessage(), data);
    }


    /**
     * 创建一个带有状态、消息和数据的成功结果
     *
     * @param resultStatus 结果状态
     * @param data         数据
     * @param <T>          数据类型
     * @return 带有状态、消息和数据的成功结果对象
     */
    public static <T> Result<T> success(IResultStatus resultStatus, T data) {
        if (resultStatus.isError()) log.error("结果状态异常：使用的 IResultStatus 似乎并不是成功状态");
        return result(resultStatus, data);
    }

    /**
     * 创建一个带有消息和数据的成功结果
     *
     * @param message 成功消息
     * @param data    成功数据
     * @param <T>     数据类型
     * @return 带有消息和数据的成功结果对象
     */
    public static <T> Result<T> success(String message, T data) {
        return success(null, message, data);
    }

    /**
     * 创建一个带有状态、消息和数据的成功结果
     *
     * @param code    状态码
     * @param message 成功消息
     * @param data    成功数据
     * @param <T>     数据类型
     * @return 带有状态、消息和数据的成功结果对象
     */
    @Contract("_, _, _ -> new")
    public static <T> @NotNull Result<T> success(Integer code, String message, T data) {
        return result(true, code, message, data);
    }


    /**
     * 创建一个指定失败状态的结果
     *
     * @param resultStatus 结果状态
     * @return 指定状态的结果
     */
    public static Result<Void> failure(IResultStatus resultStatus) {
        if (!resultStatus.isError()) log.error("结果状态异常：使用的 IResultStatus 似乎并不是失败状态");
        return result(resultStatus, null);
    }

    /**
     * 创建一个带有消息的失败结果
     *
     * @param message 失败消息
     * @return 带有消息的失败结果对象
     */
    public static Result<Void> failure(String message) {
        return failure(null, message);
    }

    /**
     * 创建一个带有状态码和消息的失败结果
     *
     * @param code    状态码
     * @param message 失败消息
     * @return 带有状态码和消息的失败结果对象
     */
    @Contract("_, _ -> new")
    public static @NotNull Result<Void> failure(Integer code, String message) {
        return result(false, code, message, null);
    }


    /**
     * 创建一个带有状态、状态码、消息和数据的结果
     *
     * @param status  结果状态
     * @param code    状态码
     * @param message 结果消息
     * @param data    结果数据
     * @param <T>     数据类型
     * @return 带有状态、状态码、消息和数据的结果对象
     */
    @Contract("_, _, _, _ -> new")
    public static <T> @NotNull Result<T> result(Boolean status, Integer code, String message, T data) {
        return new Result<>(status, code, message, data);
    }

    /**
     * 创建一个带有结果状态和数据的结果
     *
     * @param resultStatus 结果状态
     * @param data         结果数据
     * @param <T>          数据类型
     * @return 带有结果状态和数据的结果对象
     */
    public static <T> Result<T> result(IResultStatus resultStatus, T data) {
        return new Result<>(resultStatus, data);
    }


    /**
     * 构建器，用于构建 Result 对象
     *
     * @param <T> 数据类型
     */
    public static class ResultBuilder<T> implements Serializable {
        @Serial
        private static final long serialVersionUID = 4327958315156040L;
        private Boolean status;
        private Integer code;
        private String message;
        @SuppressWarnings("all")
        private T data;

        /**
         * 构建器构造函数，初始化数据
         *
         * @param data 初始数据
         */
        private ResultBuilder(T data) {
            data(data);
        }

        /**
         * 设置操作状态
         *
         * @param status 操作状态
         * @return 当前构建器对象
         */
        public ResultBuilder<T> status(boolean status) {
            this.status = status;
            return this;
        }

        /**
         * 设置状态码
         *
         * @param code 状态码
         * @return 当前构建器对象
         */
        public ResultBuilder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        /**
         * 设置消息
         *
         * @param message 消息
         * @return 当前构建器对象
         */
        public ResultBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置数据
         *
         * @param data 数据
         * @return 当前构建器对象
         */
        public ResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        /**
         * 设置状态
         *
         * @param status 状态
         * @return 当前构建器对象
         */
        public ResultBuilder<T> status(@NotNull IResultStatus status) {
            return this.status(status.getStatus()).code(status.getCode()).message(status.getMessage());
        }

        /**
         * 设置状态和数据
         *
         * @param status 状态
         * @param data   数据
         * @return 当前构建器对象
         */
        public ResultBuilder<T> status(IResultStatus status, T data) {
            return this.status(status).data(data);
        }

        /**
         * 构建并返回 Result 对象
         *
         * @return 构建的 Result 对象
         */
        public Result<T> build() {
            return new Result<>(status, code, message, data);
        }
    }

    /**
     * 用于创建不包含数据的Result建造者实例。<br>
     * 若需要使用包含数据的构建者，请使用 {@link Result#builder(Object)} 方法。
     *
     * @return ResultBuilder<Void>，不包含 data 属性的 ResultBuilder
     */
    @Contract(" -> new")
    public static @NotNull ResultBuilder<Void> builder() {
        return new ResultBuilder<>(null);
    }

    /**
     * 用于创建包含数据的Result建造者实例。
     *
     * @param <T>  泛型，表示结果中包含的数据的数据类型。
     * @param data Result 中包含的数据。
     * @return ResultBuilder<T>，包含指定 data 属性的 ResultBuilder
     */
    @Contract("_ -> new")
    public static <T> @NotNull ResultBuilder<T> builder(T data) {
        return new ResultBuilder<>(data);
    }

    /**
     * 返回结果对象的字符串表示形式
     *
     * @return 结果对象的字符串表示形式
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Result.class.getSimpleName() + "{", "}")
                .add("timestamp=" + timestamp)
                .add("status=" + status)
                .add("code=" + code)
                .add("message='" + message + "'")
                .add("data=" + data)
                .toString();
    }
}
