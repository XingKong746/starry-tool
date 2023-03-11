package cn.starrys.core.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
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
     * 运行时环境版本
     */
    public static final String JAVA_VERSION = "java.version";

    /**
     * Java运行时环境版本日期
     */
    public static final String JAVA_VERSION_DATE = "java.version.date";

    /**
     * 运行时环境供应商
     */
    public static final String JAVA_VENDOR = "java.vendor";

    /**
     * URL
     */
    public static final String JAVA_VENDOR_URL = "java.vendor.url";

    /**
     * url
     */
    public static final String JAVA_VENDOR_URL_BUG = "java.vendor.url.bug";

    /**
     * 运行时环境供应商版本
     */
    public static final String JAVA_VENDOR_VERSION = "java.vendor.version";

    /**
     * 运行时环境规范名称
     */
    public static final String JAVA_SPECIFICATION_NAME = "java.specification.name";

    /**
     * 运行时环境规范供应商
     */
    public static final String JAVA_SPECIFICATION_VENDOR = "java.specification.vendor";

    /**
     * 运行时环境规范版本
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
     * 虚拟机规范供应商
     */
    public static final String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";

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

    public static final String JDK_DEBUG = "jdk.debug";

    public static final String SUN_CPU_ISALIST = "sun.cpu.isalist";

    public static final String SUN_JNU_ENCODING = "sun.jnu.encoding";

    public static final String SUN_ARCH_DATA_MODEL = "sun.arch.data.model";

    public static final String SUN_JAVA_LAUNCHER = "sun.java.launcher";

    public static final String SUN_BOOT_LIBRARY_PATH = "sun.boot.library.path";

    public static final String SUN_JAVA_COMMAND = "sun.java.command";

    public static final String SUN_CPU_ENDIAN = "sun.cpu.endian";

    public static final String SUN_MANAGEMENT_COMPILER = "sun.management.compiler";

    public static final String SUN_OS_PATCH_LEVEL = "sun.os.patch.level";

    public static final String SUN_IO_UNICODE_ENCODING = "sun.io.unicode.encoding";

}
