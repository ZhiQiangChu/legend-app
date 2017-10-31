package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @类功能: TODO    样品归集实体，这里保存了样品与样品集的对应关系
 * @文件名: SampleInPool.java
 * @所在包: cn.com.dplus.sp.entity
 * @开发者: 黄先国
 * @邮_件: hsamgle@qq.com
 * @时_间: 2016年10月10日上午9:23:51
 * @公_司: 广州讯动网络科技有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SampleInPool extends BaseEntity {

    private static final long serialVersionUID = -1006735767314745695L;

    /** 记录的主键 */
    private String _id;

    /** 样品的id */
    private String sampleId;

    /** 样品集的id */
    private String sampleSetId;

    /** 状态 */
    private Integer state;

}
