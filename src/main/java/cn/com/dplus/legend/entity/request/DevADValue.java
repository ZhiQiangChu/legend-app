package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.List;

/**
 * @类功能: TODO    设备的白板标定时的产生的
 * @文件名: DevADValue.java
 * @所在包: cn.com.dplus.d.entity.v2
 * @开发者: 黄先国
 * @邮_件: hsamgle@qq.com
 * @时_间: 2017年4月18日下午2:26:50
 * @公_司: 广州讯动网络科技有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DevADValue extends BaseEntity {

    private static final long serialVersionUID = -6886443646755832302L;

    private String _id;

    /** 对应的记录id */
    private String recordId;

    /** 对应的ad值 */
    private List<Integer> adValue;

    /** 采集的时间 */
    private Long time;

    /** 当前记录的状态 */
    private Integer state;


    /**
     *
     * @方法功能： TODO    实现 TreeMap<Float, Integer>   到    TreeMap<String, Integer> 的转换
     * @方法名称： convert
     * @编写时间： 2017年5月8日下午5:07:47
     * @开发者  ：	  黄先国
     * @方法参数：    @param source
     * @方法参数：    @return
     * @返回值  :	TreeMap<String,Integer>
     */
    /*public static TreeMap<String, Integer> convert(TreeMap<Float, Integer> source){
		TreeMap<String, Integer> target = new TreeMap<String,Integer>();
		for (Entry<Float, Integer> s : source.entrySet()) {
			target.put((s.getKey()+"").replace(".", "_"),s.getValue());
		}
		return target;
	}*/


    /**
     *
     * @方法功能： TODO    实现  TreeMap<String, Integer>  到  TreeMap<Float, Integer> 的转换
     * @方法名称： reConvert
     * @编写时间： 2017年5月8日下午5:37:00
     * @开发者  ：	  黄先国
     * @方法参数：    @param source
     * @方法参数：    @return
     * @返回值  :	TreeMap<Float,Integer>
     */
	/*public static TreeMap<Float, Integer> reConvert(LinkedHashMap<String, Integer> source){
		TreeMap<Float, Integer> target = new TreeMap<>(Float::compareTo);
		for (Entry<String, Integer> ad : source.entrySet()) {
			target.put(Float.valueOf(ad.getKey().replace("_", ".")),ad.getValue());
		}
		return target;
	}*/
}
