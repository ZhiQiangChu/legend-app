package cn.com.dplus.legend.entity.request;

import cn.com.dplus.mongodb.annatation.MFuture;
import cn.com.dplus.mongodb.annatation.MGroup;
import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.NotSaved;

import java.util.Set;


/**
 * @类功能: TODO    设备的即时状态
 * @文件名: DevImStatus.java
 * @所在包: net.sondon.dplus.accesslayer.entity
 * @开发者: 黄先国
 * @邮_件: hsamgle@qq.com
 * @时_间: 2016年7月26日下午3:10:11
 * @公_司: 广州讯动网络科技有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DevImStatus extends BaseEntity {

    private static final long serialVersionUID = -7302286940059938018L;

    private String _id;

    /** 设备的sn */
    private String dSn;

    /** 设备的类型id */
    private String dTypeId;

    /** 设备的类型名称 */
    private String dTypeName;

    /** 当前的运行态id */
    private String currentStateId;

    /** 连接服务器的ip 地址 */
    private String connectIp;

    /** 设备的ip */
    private String ip;

    /** 设备的状态 */
    private Integer status;

    /** 警告代码 */
    private Set<Integer> warningCode;

    /** 当前更新的时间 */
    private Long currentTime;

    /** 持有人的id */
    private String ownerId;

    /** 持有人的名称 */
    private String ownerName;

    /** 设备的备注名称 */
    private String deviceUserLabel;

    /** 用户自定义备注信息 */
    private String userRemark;

    /** 设备温度 */
    private Float devTemper;

    /** 设备的湿度 */
    private Float devHumidity;

    /** 检测器温度 */
    private Float detectorTemper;

    /** 检测器的湿度 */
    private Float detectorHumidity;

    /** 光源的温度 */
    private Float lampTemper;

    /** 程序的版本号 */
    private String devSoftVersion;

    /** 设备的sn参考值 */
    private String dSnRef;

    /** 开机时间 */
    private Long starupTime;

    /** 平台的id */
    private String platformId;

    /** GPS 的状态 */
    private String GPS;

    /** 生产日期 */
    private Long productionDate;

    /** 生产批次 */
    private String batch;

    /** 流转状态 */
    private Integer flowState;

    /** 设备添加的时间 */
    private Long devAddTime;

    /** 上一次的离线时间 */
    private Long lastOffLineTime;

    /** 设备的MAC地址 */
    private String MAC;

    /** 当前设备被何人锁定 */
    private String lockBy;

    /** 设备的预热状态  0 预热中   1 预热完成  2 用户关闭卤灯 */
    private Integer preheatState;

    public DevImStatus() {
    }

    public DevImStatus(String sn) {
        this.dSn = sn;
        this.status = -1;
    }

    @NotSaved
    private final static DevImStatus imStatus = new DevImStatus();

    public DevImStatus simpleIMStatus() {
        imStatus.setConnectIp(connectIp);
        imStatus.setCurrentTime(currentTime);
        imStatus.setDSn(dSn);
        imStatus.setDeviceUserLabel(deviceUserLabel);
        imStatus.setStatus(status);
        imStatus.setOwnerId(ownerId);
        imStatus.setDetectorTemper(detectorTemper);
        imStatus.setLampTemper(lampTemper);
        imStatus.setPreheatState(preheatState);
        return imStatus;
    }


    /**
     * @类功能: TODO    统计设备的在线状态时使用
     * @文件名: DevImStatus.java
     * @所在包: cn.com.dplus.d.entity.v2
     * @开发者: 黄先国
     * @邮_件: hsamgle@qq.com
     * @时_间: 2017年5月3日下午4:20:58
     * @公_司: 广州讯动网络科技有限公司
     */
    @Data
    public static class IMStatusStat {

        @MGroup(mId = true)
        /** 设备类型id  */
        private String dTypeId;

        /** 设备类型名称 */
        private String dTypeName;

        @MGroup(mId = true)
        /** 当前的在线状态  */
        private int status;

        @MGroup(func = MFuture.count)
        /** 当前的统计数  */
        private int count;
    }

}
