package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.annotation.ParamsValid;
import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * 
 *  @作用:	TODO 品种属性
 *	@文件名:	BreedAttribute.java
 * 	@所在包:	cn.com.dplus.sample.entity
 *	@开发者: 	余浪
 * 	@邮件: 	365617581@qq.com
 *  @时间:	2016年9月27日
 *	@公司:	广州讯动网络科技有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(value="BREED_ATTRIBUTE",noClassnameStored=true)
public class BreedAttribute extends BaseEntity{
	

	private static final long serialVersionUID = -6653169971607711058L;

	/** 属性ID	*/
	private String _id;
	
	/** 属性名称	*/
	private String attrName;
	
	/** 属性类型	*/
	private String attrType;
	
	/** 品种ID	*/
	private String breedId;

	/** 品种的名称  */
	private String breedName;

	/** 行业的id  */
	private String industryId;

	/** 行业的名称  */
	private String industryName;

	/** 必要属性 	*/
	private Integer required;
	
	/** 属性值	*/
	private String attrValues;
	
	/** 默认值	*/
	private String defaultValue;
	
	/** 用户ID	*/
	private String userId;
	
	/** 创建时间	*/
	private Integer createdAt;
	
	/** 更新时间	*/
	private Integer updatedAt;

	/** 删除标记 */
	private Integer flag;
	
}
