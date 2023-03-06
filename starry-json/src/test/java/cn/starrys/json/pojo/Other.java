package cn.starrys.json.pojo;

import java.util.List;
import java.util.Map;

/**
 * creationTime: 2023/3/6 18:43 .
 *
 * @author XingKong
 */
public class Other {
    private List<Map<String, Object>> mapList;

    public Other() {
    }

    public Other(List<Map<String, Object>> mapList) {
        this.mapList = mapList;
    }

    public List<Map<String, Object>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, Object>> mapList) {
        this.mapList = mapList;
    }

    @Override
    public String toString() {
        return "Other{" +
                "mapList=" + mapList +
                '}';
    }
}
