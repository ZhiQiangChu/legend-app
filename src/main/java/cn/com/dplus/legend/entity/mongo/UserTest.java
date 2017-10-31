package cn.com.dplus.legend.entity.mongo;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.List;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:45 17-7-14
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(value = "user_test", noClassnameStored = true)
public class UserTest extends BaseEntity {
    @Property("_id")
    @Id
    private String id;
    private String title;
    private String description;
    private String by;
    private String url;
    @Embedded
    private List<String> tags;
    private Integer likes;
    @Embedded
    private Size size;

}