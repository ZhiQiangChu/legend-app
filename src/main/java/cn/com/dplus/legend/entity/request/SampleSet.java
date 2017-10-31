package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 *  @类功能:	TODO	样品集对象	
 *	@文件名:	SampleSet.java
 * 	@所在包:	cn.com.dplus.sp.entity
 *	@开发者:	黄先国
 * 	@邮_件:     hsamgle@qq.com
 *  @时_间:		2016年9月27日下午1:49:17
 *	@公_司:		广州讯动网络科技有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
	public class SampleSet extends BaseEntity{

	
	private static final long serialVersionUID = 8463105272769028863L;

	/** 样品集的id  */
	private String _id;
	
	/** 样品集名称  */
	private String sampleSetName;
	
	/** 样品集描述  */
	private String sampleSetDesc;
	
	/** 品种的id  */
	private String breedId;
	
	/** 品种的名称  */
	private String breedName;
	
	/** 行业的id  */
	private String industryId;
	
	/** 行业的名称  */
	private String industryName;
	
	/** 用户的id  */
	private String userId;
	
	/** 设备的SN  */
	private String dSn;

	/** 设备的名称  */
	private String dName;
	
	/** 创建时间  */
	private Long createTime;
	
	/** 更新时间  */
	private Long updateTime;
	
	/** 是不是系统创建的样品集 */
	private Integer isSystemAutoCreated;
	

	/** 样品集中样品数统计  */
	private Integer sampleCount;
	
	/** 状态标识为  -1 表示删除了   默认1  状态正常 */
	private Integer state;

}