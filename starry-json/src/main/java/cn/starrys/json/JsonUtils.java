// package cn.starrys.json;
//
// import com.alibaba.fastjson2.JSON;
// import com.alibaba.fastjson2.JSONException;
// import com.alibaba.fastjson2.JSONObject;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.google.gson.Gson;
// import com.google.gson.GsonBuilder;
// import com.google.gson.JsonObject;
// import com.google.gson.JsonParser;
// import lombok.AccessLevel;
// import lombok.NoArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// /**
//  * Json 工具。<br>
//  * 同时实现了目前主流的Json解析库的使用。
//  * <p>
//  * creationTime: 2023/3/6 10:20 .
//  *
//  * @author XingKong
//  */
// @Slf4j
// @NoArgsConstructor(access = AccessLevel.NONE)
// public class JsonUtils {
//
//     private static final ObjectMapper objectMapper = new ObjectMapper();
//     private static final Gson gson = new Gson();
//
//     /**
//      * 拆分keyPath
//      *
//      * @param keyPath 节点路径
//      * @return 节点数组
//      */
//     private static String[] split(String keyPath) {
//         // 根据”.“进行拆分
//         return keyPath.split("\\.");
//     }
//
//     /**
//      * 获取Json串中指定节点的值。<br>
//      * FastJson2 实现
//      *
//      * @param jsonStr Json 字符串
//      * @param keyPath 节点路径
//      * @param type    转换类型
//      * @param <T>     返回类型
//      * @return 指定类型的值
//      */
//     public static <T> T getValue(String jsonStr, String keyPath, Class<T> type) {
//         // 节点数组
//         String[] keys = split(keyPath);
//
//         // 遍历节点数组获得节点
//         for (String key : keys) {
//             // Json 对象
//             JSONObject jsonObject = JSON.parseObject(jsonStr);
//
//             int begin = key.indexOf('[');
//             int end = key.lastIndexOf(']');
//             // 如果key中包含[]，则表示是数组
//             if (begin != -1 && end != -1) {
//                 // key
//                 String k = key.substring(0, begin);
//                 // index
//                 int i = Integer.parseInt(key.substring(begin + 1, end));
//
//                 try {
//                     // 获取数组中的值
//                     jsonStr = jsonObject.getJSONArray(k).getString(i);
//                 } catch (IndexOutOfBoundsException e) {
//                     log.error("数组越界", e);
//                 }
//             } else {
//                 // 获取对象中的值
//                 jsonStr = jsonObject.getString(key);
//             }
//         }
//         try {
//             return JSON.to(type, jsonStr);
//         } catch (JSONException e) {
//             log.error("类型转换异常", e);
//         }
//         return null;
//     }
//
//     /**
//      * 获取Json串中指定节点的值。<br>
//      * jackson 实现
//      *
//      * @param jsonStr Json 字符串
//      * @param keyPath 节点路径
//      * @param type    转换类型
//      * @param <T>     返回类型
//      * @return 指定类型的值
//      */
//     public static <T> T getValue2(String jsonStr, String keyPath, Class<T> type) {
//         // 节点数组
//         String[] keys = split(keyPath);
//         try {
//             // 转换为JsonNode
//             JsonNode jsonNode = objectMapper.readTree(jsonStr);
//             for (String key : keys) {
//                 int begin = key.indexOf('[');
//                 int end = key.lastIndexOf(']');
//                 // 如果key中包含[]，则表示是数组
//                 if (begin != -1 && end != -1) {
//                     // key
//                     String k = key.substring(0, begin);
//                     // index
//                     int i = Integer.parseInt(key.substring(begin + 1, end));
//
//                     // 获取数组中的值
//                     jsonNode = jsonNode.get(k).get(i);
//                 } else {
//                     // 获取对象中的值
//                     jsonNode = jsonNode.get(key);
//                 }
//             }
//             return objectMapper.treeToValue(jsonNode, type);
//         } catch (JsonProcessingException e) {
//             log.error("Json解析异常", e);
//         } catch (NullPointerException e) {
//             log.error("发生了空指针：也许是下标越界造成的", e);
//         }
//         return null;
//     }
//
//     /**
//      * 获取Json串中指定节点的值。<br>
//      * gson 实现
//      *
//      * @param jsonStr Json 字符串
//      * @param keyPath 节点路径
//      * @param type    转换类型
//      * @param <T>     返回类型
//      * @return 指定类型的值
//      */
//     public static <T> T getValue3(String jsonStr, String keyPath, Class<T> type) {
//         // 节点数组
//         String[] keys = split(keyPath);
//         for (String key : keys) {
//             // Json 对象
//             JsonObject jsonObject = JsonParser.parseString(jsonStr).getAsJsonObject();
//
//             int begin = key.indexOf('[');
//             int end = key.lastIndexOf(']');
//             // 如果key中包含[]，则表示是数组
//             if (begin != -1 && end != -1) {
//                 // key
//                 String k = key.substring(0, begin);
//                 // index
//                 int i = Integer.parseInt(key.substring(begin + 1, end));
//                 try {
//                     // 获取数组中的值
//                     jsonStr = jsonObject.get(k).getAsJsonArray().get(i).toString();
//                 } catch (IllegalStateException e) {
//                     log.error("{} 并非数组", k, e);
//                 } catch (IndexOutOfBoundsException e) {
//                     log.error("数组越界", e);
//                 }
//             } else {
//                 // 获取对象中的值
//                 jsonStr = jsonObject.get(key).toString();
//             }
//         }
//         return gson.fromJson(jsonStr, type);
//     }
//
// }
