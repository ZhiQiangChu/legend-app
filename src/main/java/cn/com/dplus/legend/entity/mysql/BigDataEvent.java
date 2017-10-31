package cn.com.dplus.legend.entity.mysql;

import cn.com.dplus.legend.entity.request.DevUploadData;
import cn.com.dplus.project.entity.BaseEntity;
import cn.com.dplus.project.utils.DateTimeUtils;
import cn.com.dplus.project.utils.JsonUtil;
import com.google.common.reflect.TypeToken;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:48 17-8-28
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"eventData"})
@Table(name = "dp_bigdata_event")
public class BigDataEvent extends BaseEntity {

    private static final long serialVersionUID = 3152821273556651014L;

    /** 事件ID */
    @Id
    private Long id;

    /** 事件名称 */
    @Column(name = "event_name")
    private String eventName;

    /** 数据 */
    @Column(name = "event_data")
    private String eventData;

    private Object data;

    /** 发布次数 */
    @Column(name = "publish")
    private Integer publish;

    /** 事件创建时间 */
    @Column(name = "create_at")
    private Timestamp createAt;

    /** 事件更新时间 */
    @Column(name = "upadte_at")
    private Timestamp updateAt;

    public static void main(String[] args) {
        BigDataEvent event = new BigDataEvent();
        event.setId(1804698734123025413l);
        event.setEventName("EVENT_DEV_UPLOAD_DATA");
        event.setCreateAt(DateTimeUtils.getTimestamp());
        DevUploadData devUploadData = new DevUploadData();
        devUploadData.setDsn("485602951174");
        devUploadData.setGps(Arrays.asList(116.4075260000, 39.9040300000));
        devUploadData.setMac("E8-13-24-FF-00-05");
        devUploadData.setDevTemper(40.1599998474121f);
        devUploadData.setDevHumidity(22.110000610351f);
        devUploadData.setDetectorTemper(40.1599998474121f);
        devUploadData.setDetectorHumidity(22.110000610351f);
        devUploadData.setUpdateTime(1500364677011l);

        event.setEventData(devUploadData.toJson());

        String data = event.getEventData();

        System.out.println(data);

        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();


        Map<String, Object> eData = JsonUtil.toObject(data, type);
        System.out.println(eData);
        event.setData(eData);
        System.out.println(event.toJson());
    }

}
