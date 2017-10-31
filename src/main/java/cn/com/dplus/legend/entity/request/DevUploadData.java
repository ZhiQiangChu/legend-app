package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 设备上传数据
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午11:44 17-8-28
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DevUploadData extends BaseEntity {

    private static final long serialVersionUID = 4267420654901114491L;

    /** 设备SN号 */
    private String dsn;

    /** GPS信息 */
    private List<Double> gps;

    /** 设备MAC地址 */
    private String mac;

    /** 设备温度 */
    private Float devTemper;

    /** 设备湿度 */
    private Float devHumidity;

    /** 检测器温度  */
    private Float  detectorTemper;

    /** 检测器的湿度  */
    private Float detectorHumidity;

    /** 更新时间 */
    private Long updateTime;

    public static void main(String[] args) {
        DevUploadData devUploadData = new DevUploadData();
        devUploadData.setDsn("485602951174");
        devUploadData.setGps(Arrays.asList(116.4075260000, 39.9040300000));
        devUploadData.setMac("E8-13-24-FF-00-05");
        devUploadData.setDevTemper(40.1599998474121f);
        devUploadData.setDevHumidity(22.110000610351f);
        devUploadData.setDetectorTemper(40.1599998474121f);
        devUploadData.setDetectorHumidity(22.110000610351f);
        devUploadData.setUpdateTime(1500364677011l);
        System.out.println(devUploadData.toJson());
    }
}
