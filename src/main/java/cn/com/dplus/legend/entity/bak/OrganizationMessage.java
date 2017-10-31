package cn.com.dplus.legend.entity.bak;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Description: 组织消息实体
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午8:19 17-9-21
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false, of = {"msgId", "orgId"})
@Table(name = "organization_message")
public class OrganizationMessage extends BaseEntity {

    private static final long serialVersionUID = -2577269474154690437L;
    /**
     * 消息ID
     */
    @Id
    @Column(name = "msg_id")
    private String msgId;

    /**
     * 组织ID
     */
    @Id
    @Column(name = "org_id")
    private String orgId;


    /**
     * 是否已删除
     */
    @Column(name = "is_removed")
    private Boolean isRemoved;

    /**
     * 删除时间
     */
    @Column(name = "remove_at", nullable = true)
    private Timestamp removeAt;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Timestamp createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Timestamp updateAt;
}
