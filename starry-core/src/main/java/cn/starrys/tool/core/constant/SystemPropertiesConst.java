package cn.starrys.tool.core.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 系统属性常量（部分属性）
 * <p>
 * creationTime: 2023/3/10 10:34 .
 *
 * @author XingKong
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SystemPropertiesConst {

    /**
     * 操作系统的名称
     */
    public static final String OS_NAME = "os.name";

    /**
     * 操作系统的架构
     */
    public static final String OS_ARCH = "os.arch";

    /**
     * 操作系统的版本
     */
    public static final String OS_VERSION = "os.version";

    /**
     * 当前用户的账户名称
     */
    public static final String USER_NAME = "user.name";

    /**
     * 当前用户的主目录
     */
    public static final String USER_HOME = "user.home";

    /**
     * 当前用户的当前工作目录
     */
    public static final String USER_DIR = "user.dir";

    /**
     * 当前用户的设置国家区域
     */
    public static final String USER_COUNTRY = "user.country";

    /**
     * 当前用户的设置语言
     */
    public static final String USER_LANGUAGE = "user.language";

    public static final String USER_SCRIPT = "user.script";

    public static final String USER_VARIANT = "user.variant";

    /**
     * Java 版本
     */
    public static final String JAVA_VERSION = "java.version";

    /**
     * Java 版本的构建日期
     */
    public static final String JAVA_VERSION_DATE = "java.version.date";

    /**
     * Java 供应商
     */
    public static final String JAVA_VENDOR = "java.vendor";

    /**
     * Java 供应商 Url
     */
    public static final String JAVA_VENDOR_URL = "java.vendor.url";

    /**
     * Java 供应商 问题反馈 Url
     */
    public static final String JAVA_VENDOR_URL_BUG = "java.vendor.url.bug";

    /**
     * Java 供应商版本
     */
    public static final String JAVA_VENDOR_VERSION = "java.vendor.version";

    /**
     * Java 规范名称
     */
    public static final String JAVA_SPECIFICATION_NAME = "java.specification.name";

    /**
     * Java 规范供应商
     */
    public static final String JAVA_SPECIFICATION_VENDOR = "java.specification.vendor";

    /**
     * Java 规范版本
     */
    public static final String JAVA_SPECIFICATION_VERSION = "java.specification.version";

    /**
     * 编译器的名称
     */
    public static final String JAVA_COMPILER = "java.compiler";

    /**
     * 类路径
     */
    public static final String JAVA_CLASS_PATH = "java.class.path";

    /**
     * 类格式版本号
     */
    public static final String JAVA_CLASS_VERSION = "java.class.version";

    /**
     * 加载库时搜索的路径列表
     */
    public static final String JAVA_LIBRARY_PATH = "java.library.path";

    /**
     * Java运行时版本名称
     */
    public static final String JAVA_RUNTIME_NAME = "java.runtime.name";

    /**
     * Java运行时详细版本
     */
    public static final String JAVA_RUNTIME_VERSION = "java.runtime.version";

    /**
     * 虚拟机实现名称
     */
    public static final String JAVA_VM_NAME = "java.vm.name";

    /**
     * 虚拟机实现版本
     */
    public static final String JAVA_VM_VERSION = "java.vm.version";

    /**
     * 虚拟机实现信息
     */
    public static final String JAVA_VM_INFO = "java.vm.info";

    /**
     * 虚拟机实现供应商
     */
    public static final String JAVA_VM_VENDOR = "java.vm.vendor";

    /**
     * 虚拟机规范名称
     */
    public static final String JAVA_VM_SPECIFICATION_NAME = "java.vm.specification.name";

    /**
     * 虚拟机规范版本
     */
    public static final String JAVA_VM_SPECIFICATION_VERSION = "java.vm.specification.version";

    /**
     * Java 虚拟机规范供应商
     */
    public static final String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";

    /**
     * Java虚拟机压缩指针模式
     */
    public static final String JAVA_VM_COMPRESSED_OOPS_MODE = "java.vm.compressedOopsMode";

    /**
     * JavaHome目录
     */
    public static final String JAVA_HOME = "java.home";

    /**
     * 默认的临时文件路径
     */
    public static final String JAVA_IO_TMPDIR = "java.io.tmpdir";

    /**
     * 一个或多个扩展目录的路径
     */
    public static final String JAVA_EXT_DIRS = "java.ext.dirs";

    /**
     * 文件分隔符（在 UNIX 系统中是“/”）
     */
    public static final String FILE_SEPARATOR = "file.separator";

    /**
     * 路径分隔符（在 UNIX 系统中是“:”）
     */
    public static final String PATH_SEPARATOR = "path.separator";

    /**
     * 行分隔符（在 UNIX 系统中是“/n”）
     */
    public static final String LINE_SEPARATOR = "line.separator";

    /**
     * 从主机环境和或用户设置派生的字符编码名称。设置此系统属性没有效果。
     */
    public static final String NATIVE_ENCODING = "native.encoding";

    /**
     * 文件编码
     */
    public static final String FILE_ENCODING = "file.encoding";

    /**
     * JDK调试模式的系统属性键
     * 用于获取JDK调试模式的相关信息
     */
    public static final String JDK_DEBUG = "jdk.debug";

    /**
     * CPU指令集列表的系统属性键
     * 用于获取系统支持的CPU指令集列表
     */
    public static final String SUN_CPU_ISALIST = "sun.cpu.isalist";

    /**
     * 文件系统编码的系统属性键
     * 用于获取文件系统使用的编码方式
     */
    public static final String SUN_JNU_ENCODING = "sun.jnu.encoding";

    /**
     * 系统架构数据模型的系统属性键
     * 用于获取系统架构的数据模型，如32位或64位
     */
    public static final String SUN_ARCH_DATA_MODEL = "sun.arch.data.model";

    /**
     * Java启动器的系统属性键
     * 用于获取启动Java应用程序的启动器信息
     */
    public static final String SUN_JAVA_LAUNCHER = "sun.java.launcher";

    /**
     * 根依赖路径，用于获取 Java 根引导类库的路径信息
     */
    public static final String SUN_BOOT_LIBRARY_PATH = "sun.boot.library.path";

    /**
     * Java命令的系统属性键
     * 用于获取启动Java应用程序的命令行指令
     */
    public static final String SUN_JAVA_COMMAND = "sun.java.command";

    /**
     * CPU字节序的系统属性键
     * 用于获取CPU使用的字节序，如小端字节序或大端字节序
     */
    public static final String SUN_CPU_ENDIAN = "sun.cpu.endian";

    /**
     * Java管理编译器的系统属性键
     * 用于获取Java管理编译器的相关信息
     */
    public static final String SUN_MANAGEMENT_COMPILER = "sun.management.compiler";

    /**
     * 操作系统补丁级别的系统属性键
     * 用于获取操作系统的补丁级别信息
     */
    public static final String SUN_OS_PATCH_LEVEL = "sun.os.patch.level";

    /**
     * Unicode编码的系统属性键
     * 用于获取系统使用的Unicode编码方式
     */
    public static final String SUN_IO_UNICODE_ENCODING = "sun.io.unicode.encoding";
}
