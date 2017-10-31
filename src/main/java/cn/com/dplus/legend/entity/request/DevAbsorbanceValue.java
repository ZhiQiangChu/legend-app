package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @类功能: TODO    设备的吸光度数据
 * @文件名: DevAbsorbanceValue.java
 * @所在包: cn.com.dplus.d.entity.v2
 * @开发者: 黄先国
 * @邮_件: hsamgle@qq.com
 * @时_间: 2017年4月18日下午2:31:22
 * @公_司: 广州讯动网络科技有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DevAbsorbanceValue extends BaseEntity {

    private static final long serialVersionUID = -3299921447455892142L;

    private String _id;

    /** 对应的记录id */
    private String recordId;

    /** 对应的abs值 */
    private List<Float> absValue;

    /** 采集的时间 */
    private Long time;

    /** 当前记录的状态 */
    private Integer state;
}
