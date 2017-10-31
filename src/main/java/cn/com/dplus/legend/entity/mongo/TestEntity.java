package cn.com.dplus.legend.entity.mongo;

import cn.com.dplus.project.entity.BaseEntity;
import cn.com.dplus.project.utils.CustomJsonUtil;
import cn.com.dplus.project.utils.JsonUtil;
import com.google.common.reflect.TypeToken;
import com.google.gson.annotations.Expose;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:29 17-9-21
 * @Modified By:
 */
@Data
public class TestEntity extends BaseEntity {
//    @Expose(serialize = false)
    private Long a;
    private Float b;
    private Integer c;
    private Boolean d;
    private List<List<Float>> list;

    public static void main(String[] args) {
        TestEntity entity = new TestEntity();
        entity.setA(1821525711654547240L);
        entity.setB(0.23f);
        entity.setC(1);
        entity.setD(false);
        String json = entity.toJson();
        System.out.println(json);
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map map = JsonUtil.toObject(json, type);
        System.out.println(CustomJsonUtil.toJson(map));

        TestEntity entity1 = JsonUtil.toObject(JsonUtil.toJson(map), TestEntity.class);
        System.out.println(entity1.toString());

//        Gson gson = new GsonBuilder().serializeNulls().create();
//        String json = gson.toJson(entity);
//        System.out.println(json);
//        TestEntity entity1 = gson.fromJson(json, TestEntity.class);
//        System.out.println(entity.toString());
    }
}
