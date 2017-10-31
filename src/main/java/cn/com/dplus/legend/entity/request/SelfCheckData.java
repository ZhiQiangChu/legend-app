package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 设备自检数据
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:31 17-8-28
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SelfCheckData extends BaseEntity {

    private static final long serialVersionUID = -3214692935941763628L;

    /** 设备SN号 */
    private String dsn;

    /** 白板数据 */
    private List<List<Integer>> adValues;

    /** 聚苯乙烯数据 */
    private List<List<Float>> absValues;

    /** 采集时间 */
    private Long time;

    public static void main(String[] args) {
        SelfCheckData data = new SelfCheckData();
        data.setDsn("485602951174");

        List<Integer> adValue1 = Arrays.asList(22428, 22992, 23765, 24238, 24907);
        List<Integer> adValue2 = Arrays.asList(22426, 22991, 23769, 24246, 24909);
        List<Integer> adValue3 = Arrays.asList(22431, 22988, 23775, 24241, 24896);

        data.setAdValues(Arrays.asList(adValue1, adValue2, adValue3));
        List<Float> absValue1 = Arrays.asList(0.15220545232296f,
                0.153848260641098f,
                0.154690384864807f,
                0.155211254954338f,
                0.156142711639404f);
        List<Float> absValue2 = Arrays.asList(0.152383804321289f,
                0.153646767139435f,
                0.154600381851196f,
                0.155211254954338f,
                0.156279027462006f);
        List<Float> absValue3 = Arrays.asList(0.152406111359596f,
                0.153646767139435f,
                0.154420450329781f,
                0.155030623078346f,
                0.15591561794281f);

        data.setAbsValues(Arrays.asList(absValue1, absValue2, absValue3));
        data.setTime(1500601796216l);
        System.out.println(data.toJson());
    }
}
