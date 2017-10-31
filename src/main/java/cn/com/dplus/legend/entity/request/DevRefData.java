package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 设备参比数据
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:50 17-8-28
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DevRefData extends BaseEntity {

    private static final long serialVersionUID = -3214692935941763628L;

    /** 设备SN号 */
    private String dsn;

    /** 白板数据 */
    private List<Integer> adValue;

    /** 采集时间 */
    private Long time;

    public static void main(String[] args) {
        DevRefData data = new DevRefData();
        data.setDsn("485602951174");
//        TreeMap<String, Integer> map = new TreeMap();
//        map.put("1001_61365", 27662);
//        map.put("1005_5104", 27640);
//        map.put("1009_4033", 27558);
//        map.put("1011_99646", 27491);
//        map.put("1015_883", 27391);
        List<Integer> adValue = Arrays.asList(27662, 27640, 27558, 27491, 27391);
        data.setAdValue(adValue);
        data.setTime(1500601657604l);
        System.out.println(data.toJson());
    }

}
