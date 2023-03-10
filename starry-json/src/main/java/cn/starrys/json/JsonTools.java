package cn.starrys.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Json 工具。<br>
 * 同时实现了目前主流的Json解析库的使用。
 * <p>
 * creationTime: 2023/3/6 10:20 .
 *
 * @author XingKong
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.NONE)
public class JsonTools {

    /**
     * 拆分keyPath
     *
     * @param keyPath 节点路径
     * @return 节点数组
     */
    @Contract(pure = true)
    private static String @NotNull [] split(@NotNull String keyPath) {
        // 根据”.“进行拆分
        return keyPath.split("\\.");
    }

    /**
     * 获取Json串中指定节点的值。<br>
     * FastJson2 实现
     *
     * @param jsonStr Json 字符串
     * @param keyPath 节点路径
     * @param type    转换类型
     * @param <T>     返回类型
     * @return 指定类型的值
     */
    public static <T> @Nullable T getValue(String jsonStr, String keyPath, Class<T> type) {
        // 节点数组
        String[] keys = split(keyPath);

        // 遍历节点数组获得节点
        for (String key : keys) {
            // Json 对象
            JSONObject jsonObject = JSON.parseObject(jsonStr);

            int begin = key.indexOf('[');
            int end = key.lastIndexOf(']');
            // 如果key中包含[]，则表示是数组
            if (begin != -1 && end != -1) {
                // key
                String k = key.substring(0, begin);
                // index
                int i = Integer.parseInt(key.substring(begin + 1, end));

                try {
                    // 获取数组中的值
                    jsonStr = jsonObject.getJSONArray(k).getString(i);
                } catch (IndexOutOfBoundsException e) {
                    log.error("数组越界", e);
                }
            } else {
                // 获取对象中的值
                jsonStr = jsonObject.getString(key);
            }
        }
        try {
            return JSON.to(type, jsonStr);
        } catch (JSONException e) {
            log.error("类型转换异常", e);
        }
        return null;
    }

}
