package cn.com.dplus.legend.entity.mongo;

import cn.com.dplus.project.entity.BaseEntity;
import cn.com.dplus.project.entity.ResponseEntity;
import cn.com.dplus.project.utils.DateTimeUtils;
import cn.com.dplus.project.utils.RandomUtil;
import cn.com.dplus.project.utils.UUIDUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:46 17-7-26
 * @Modified By:
 */
@Entity("MODEL_TASK")
@Data
@EqualsAndHashCode(callSuper = false)
public class ModelTask extends BaseEntity {

    private static final long serialVersionUID = -2459538655273588471L;
    @Property("_id")
    @Id
    private String id;

    /** 任务编号 */
    private String taskNo;

    /** 用户ID */
    private String userId;

    /** 品种ID */
    private String breedId;

    /** 品种名称 */
    private String breedName;

    /** 样品集ID */
    private String sampleSetId;

    /** 样品集名称 */
    private String sampleSetName;

    /** 指标ID */
    private String indicatorIds;

    /** 建模开始时间 */
    private Long startTime;

    /** 建模结束时间 */
    private Long endTime;

    /** 预计消耗时间 */
    private Long estimatedTime;

    /** 建模状态 0:正在建模 1:建模完成 */
    private int state;

    /** 更新时间 */
    private Long updateTime;

    public static void main(String[] args) {
        ModelTask task = new ModelTask();
        task.setId(UUIDUtils.getUUID());
        task.setTaskNo("Modeling-Task_" + RandomUtil.getNumAndAZ(15));
        task.setUserId("8e4aefa9beaa426c9b54d6103a53d71e");
        task.setBreedId("d1a999eaa43b4e8fb3777b46a89af63b");
        task.setSampleSetId("00f67411dafe40968af2e841996743a6");
        task.setSampleSetName("发酵豆粕");
        task.setBreedId("d1a999eaa43b4e8fb3777b46a89af63b");
        task.setBreedName("豆粕");
        task.setIndicatorIds("7d3650912a534aa99ccbb14faf46b58e,7b45ad416c194e1b8d19dbbc40b2f107");
        task.setStartTime(DateTimeUtils.getNowInMillis());
        task.setEndTime(DateTimeUtils.getNowInMillis() + 20000);
        task.setEstimatedTime(20000L);
        task.setState(1);
        System.out.println(new ResponseEntity(task).toJson());

    }

}
