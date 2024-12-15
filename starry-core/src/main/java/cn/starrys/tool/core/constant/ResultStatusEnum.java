package cn.starrys.tool.core.constant;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created: 2024/12/7 13:16 .
 *
 * @author XingKong
 */
public enum ResultStatusEnum implements IResultStatus, Serializable {

    // ---------------------------------------- 信息 ----------------------------------------
    /**
     * 客户端应继续其请求
     */
    CONTINUE(true, 100, "继续", Series.INFORMATIONAL),
    /**
     * 服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到HTTP的新版本协议
     */
    SWITCHING_PROTOCOLS(true, 101, "切换协议", Series.INFORMATIONAL),


    // ---------------------------------------- 成功 ----------------------------------------
    /**
     * 一般用于GET与POST请求
     */
    OK(true, 200, "成功", Series.SUCCESSFUL),
    /**
     * 成功请求并创建了新的资源
     */
    CREATED(true, 201, "已创建", Series.SUCCESSFUL),
    /**
     * 已经接受请求，但未处理完成
     */
    ACCEPTED(true, 202, "已接受", Series.SUCCESSFUL),
    /**
     * 请求成功。但返回的meta信息不在原始的服务器，而是一个副本
     */
    NON_AUTHORITATIVE_INFORMATION(true, 203, "非授权信息", Series.SUCCESSFUL),
    /**
     * 服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档
     */
    NO_CONTENT(true, 204, "无内容", Series.SUCCESSFUL),
    /**
     * 服务器处理成功，用户终端（例如：浏览器）应重置文档视图。可通过此返回码清除浏览器的表单域
     */
    RESET_CONTENT(true, 205, "重置内容", Series.SUCCESSFUL),
    /**
     * 服务器成功处理了部分GET请求
     */
    PARTIAL_CONTENT(true, 206, "部分内容", Series.SUCCESSFUL),


    // ---------------------------------------- 重定向 ----------------------------------------
    /**
     * 请求的资源可包括多个位置，相应可返回一个资源特征与地址的列表用于用户终端（例如：浏览器）选择
     */
    MULTIPLE_CHOICES(true, 300, "多种选择", Series.REDIRECTION),
    /**
     * 请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替
     */
    MOVED_PERMANENTLY(true, 301, "永久移动", Series.REDIRECTION),
    /**
     * 与301类似。但资源只是临时被移动。客户端应继续使用原有URI
     */
    FOUND(true, 302, "临时移动", Series.REDIRECTION),
    /**
     * 与301类似。使用GET和POST请求查看
     */
    SEE_OTHER(true, 303, "查看其它地址", Series.REDIRECTION),
    /**
     * 所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源
     */
    NOT_MODIFIED(true, 304, "未修改", Series.REDIRECTION),
    /**
     * 与302类似。使用GET请求重定向
     */
    TEMPORARY_REDIRECT(true, 307, "临时重定向", Series.REDIRECTION),


    // ---------------------------------------- 客户端错误 ----------------------------------------
    /**
     * 客户端请求的语法错误，服务器无法理解
     */
    BAD_REQUEST(false, 400, "错误的请求", Series.CLIENT_ERROR),
    /**
     * 请求要求用户的身份认证
     */
    UNAUTHORIZED(false, 401, "未认证", Series.CLIENT_ERROR),
    /**
     * 保留，将来使用
     */
    PAYMENT_REQUIRED(false, 402, "付款要求", Series.CLIENT_ERROR),
    /**
     * 服务器理解请求客户端的请求，但是拒绝执行此请求
     */
    FORBIDDEN(false, 403, "被禁止的", Series.CLIENT_ERROR),
    /**
     * 服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置"您所请求的资源无法找到"的个性页面
     */
    NOT_FOUND(false, 404, "未找到", Series.CLIENT_ERROR),
    /**
     * 客户端请求中的方法被禁止
     */
    METHOD_NOT_ALLOWED(false, 405, "不允许的方法", Series.CLIENT_ERROR),
    /**
     * 服务器无法根据客户端请求的内容特性完成请求
     */
    NOT_ACCEPTABLE(false, 406, "不可接受", Series.CLIENT_ERROR),
    /**
     * 请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权
     */
    PROXY_AUTHENTICATION_REQUIRED(false, 407, "需要代理身份验证", Series.CLIENT_ERROR),
    /**
     * 服务器等待客户端发送的请求时间过长，超时
     */
    REQUEST_TIMEOUT(false, 408, "请求超时", Series.CLIENT_ERROR),
    /**
     * 服务器完成客户端的 PUT 请求时可能返回此代码，服务器处理请求时发生了冲突
     */
    CONFLICT(false, 409, "冲突", Series.CLIENT_ERROR),
    /**
     * 客户端请求的资源已经不存在。410不同于404，如果资源以前有现在被永久删除了可使用410代码，网站设计人员可通过301代码指定资源的新位置
     */
    GONE(false, 400, "消失的", Series.CLIENT_ERROR),
    /**
     * 服务器无法处理客户端发送的不带Content-Length的请求信息
     */
    LENGTH_REQUIRED(false, 401, "需要 Length", Series.CLIENT_ERROR),
    /**
     * 客户端请求信息的先决条件错误
     */
    PRECONDITION_FAILED(false, 402, "前提条件失败", Series.CLIENT_ERROR),
    /**
     * 由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息
     *
     * @since 4.1
     */
    PAYLOAD_TOO_LARGE(false, 403, "有效载荷过大", Series.CLIENT_ERROR),
    /**
     * 请求的URI过长（URI通常为网址），服务器无法处理
     */
    URI_TOO_LONG(false, 404, "URI太长", Series.CLIENT_ERROR),
    /**
     * 服务器无法处理请求附带的媒体格式
     */
    UNSUPPORTED_MEDIA_TYPE(false, 405, "不支持的媒体类型", Series.CLIENT_ERROR),
    /**
     * 客户端请求的范围无效
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(false, 406, "请求范围无法满足", Series.CLIENT_ERROR),
    /**
     * 服务器无法满足Expect的请求头信息
     */
    EXPECTATION_FAILED(false, 407, "预期结果失败", Series.CLIENT_ERROR),


    // ---------------------------------------- 服务器错误 ----------------------------------------
    /**
     * 服务器内部错误，无法完成请求
     */
    INTERNAL_SERVER_ERROR(false, 500, "内部服务器错误", Series.SERVER_ERROR),
    /**
     * 服务器不支持请求的功能，无法完成请求
     */
    NOT_IMPLEMENTED(false, 501, "未实现的", Series.SERVER_ERROR),
    /**
     * 作为网关或者代理工作的服务器尝试执行请求时，从远程服务器接收到了一个无效的响应
     */
    BAD_GATEWAY(false, 502, "网关错误", Series.SERVER_ERROR),
    /**
     * 由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中
     */
    SERVICE_UNAVAILABLE(false, 503, "服务不可用", Series.SERVER_ERROR),
    /**
     * 充当网关或代理的服务器，未及时从远端服务器获取请求
     */
    GATEWAY_TIMEOUT(false, 504, "网关超时", Series.SERVER_ERROR),
    /**
     * 服务器不支持请求的HTTP协议的版本，无法完成处理
     */
    HTTP_VERSION_NOT_SUPPORTED(false, 505, "不支持的HTTP版本", Series.SERVER_ERROR),
    ;


    @Serial
    private static final long serialVersionUID = 3327958315156039L;

    private final boolean status;
    private final Integer code;
    private final String message;
    private Series series;

    ResultStatusEnum(boolean status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    ResultStatusEnum(boolean status, Integer code, String message, Series series) {
        this(status, code, message);
        this.series = series;
    }

    @Override
    public Boolean getStatus() {
        return status;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Series getSeries() {
        if (series == null) {
            return IResultStatus.super.getSeries();
        }
        return series;
    }

    /**
     * 将给定的状态码解析为{@link ResultStatusEnum}。
     *
     * @param code 要解析的状态码
     * @return 匹配的的 {@link ResultStatusEnum}，如果没有找到，则为{@code null}
     */
    public static ResultStatusEnum resolve(Integer code) {
        for (ResultStatusEnum resultStatusEnum : values()) {
            if (Objects.equals(resultStatusEnum.code, code)) {
                return resultStatusEnum;
            }
        }
        return null;
    }
}
