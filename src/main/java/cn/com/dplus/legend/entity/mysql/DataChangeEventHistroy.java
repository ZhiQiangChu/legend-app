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
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午5:45 17-8-25
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "dp_data_change_event_histroy")
public class DataChangeEventHistroy extends BaseEntity {


    private static final long serialVersionUID = 1993733227648139000L;

    /** 历史记录ID */
    @Id
    private Integer id;

    /** 事件ID */
    @Column(name = "event_id")
    private Long eventId;

    /** 事件发生时的服务名称 */
    @Column(name = "service")
    private String service;

    /** 事件对应的资源名称 */
    @Column(name = "resource")
    private String resource;

    /** 对资源进行何种操作引发的事件（insert/update/delete） */
    @Column(name = "action")
    private Integer action;

    /** 事件名称，为空时event_name=service_resouce_action */
    @Column(name = "event_name")
    private String eventName;

    /** 数据 */
    @Column(name = "event_data")
    private String eventData;

    /** 发送次数 */
    @Column(name = "send_count")
    private Integer sendCount;

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
