package cn.starrys.json;

import cn.starrys.json.pojo.*;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class JsonToolsTests {
    String json;

    /**
     * 初始化json数据大小
     */
    private final int n = 10;

    /**
     * for循环次数
     */
    private final int f = 100;

    @Test
    void getValue() {
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < f; i++) {
            int r = random.nextInt(n);
            User user = JsonTools.getValue(
                    json,
                    "data[%s].roles[%s].permissions[%s].other.mapList[%s].user%s".formatted(r, r, r, r, r),
                    User.class
            );
            System.out.println(user);
        }
        long stop = System.currentTimeMillis();
        System.out.println("转换Json节点到对象共耗时：" + (stop - start) + "毫秒");
    }

    /**
     * 创建 json 数据
     */
    @BeforeEach
    void before() {
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put("user" + i, new User("用户名" + i, "密码" + i, null));
        }
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            maps.add(map);
        }
        Other other = new Other();
        other.setMapList(maps);
        ArrayList<Permission> permissions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            permissions.add(new Permission("权限" + i, "权限描述" + i, other));
        }
        ArrayList<Role> roles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            roles.add(new Role("角色" + i, "角色描述" + i, permissions));
        }
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            users.add(new User("用户名" + i, "密码" + i, roles));
        }
        Result<ArrayList<User>> result = new Result<>();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(users);

        long start = System.currentTimeMillis();

        // gson：n=10，平均 160 毫秒
        // Gson gson = new GsonBuilder().serializeNulls().create();
        // json = gson.toJson(result);

        // fastjson2：n=10，平均 190 毫秒
        json = JSON.toJSONString(result);

        // jackson：n=10，平均 340 毫秒
        // ObjectMapper objectMapper = new ObjectMapper();
        // json = objectMapper.valueToTree(result).toString();

        long stop = System.currentTimeMillis();
        System.out.println("转换对象到Json字符串共耗时：" + (stop - start) + "毫秒");
    }

}
