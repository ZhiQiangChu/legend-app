package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * @类功能: TODO        样本的实体
 * @文件名: Specimen.java
 * @所在包: cn.com.dplus.sp.entity
 * @开发者: 黄先国
 * @邮_件: hsamgle@qq.com
 * @时_间: 2017年2月6日下午3:30:13
 * @公_司: 广州讯动网络科技有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Specimen extends BaseEntity implements Comparable<Specimen> {

    private static final long serialVersionUID = 7587757003211138713L;

    /** 样本的id */
    private String id;

    /** 所属样品的id */
    private String sampleId;

    /** 采集的时间 */
    private Timestamp collectTime;

    /** 样品集id */
    private String sampleSetId;

    /** 设备的sn */
    private String dSn;

    /** 光谱数据 */
    private Object spectrum;


    /** 存放在Hbase 中的光谱数据 */
    private transient String spectrumJson;


    /** 存放在和base中的设备环境变量json数据 */
    private transient String devEnvFactorsJson;

    /** 用户ID */
    private String userId;


    @Override
    public int compareTo(Specimen o) {
        return o.getCollectTime().compareTo(this.getCollectTime());
    }

}
