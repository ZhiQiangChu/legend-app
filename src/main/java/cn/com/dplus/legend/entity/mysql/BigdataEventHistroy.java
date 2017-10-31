package cn.com.dplus.legend.entity.mysql;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Description: 大数据
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:54 17-8-28
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "dp_bigdata_event_histroy")
public class BigdataEventHistroy extends BaseEntity {

    private static final long serialVersionUID = -8392913970482999588L;

    /** 事件ID */
    @Id
    private Long id;

    /** 事件ID */
    @Column(name = "event_id")
    private Long eventId;

    /** 事件名称 */
    @Column(name = "event_name")
    private String eventName;

    /** 数据 */
    @Column(name = "event_data")
    private String eventData;

    /** 发布次数 */
    @Column(name = "publish")
    private int publish;

    /** 事件创建时间 */
    @Column(name = "create_at")
    private Timestamp createAt;

    /** 事件更新时间 */
    @Column(name = "upadte_at")
    private Timestamp updateAt;

    /** 记录时间 */
    @Column(name = "record_at")
    private Timestamp recordAt;

}
