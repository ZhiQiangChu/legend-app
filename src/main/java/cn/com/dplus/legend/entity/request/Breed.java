package cn.com.dplus.legend.entity.request;


import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;

/**
 * 
 *  @作用:	TODO 品种持久类
 *	@文件名:	Breed.java
 * 	@所在包:	cn.com.dplus.sample.entity
 *	@开发者: 	余浪
 * 	@邮件: 	365617581@qq.com
 *  @时间:	2016年9月27日
 *	@公司:	广州讯动网络科技有限公司
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity(value="BREED",noClassnameStored=true)
public class Breed extends BaseEntity {
	
	private static final long serialVersionUID = -5805331522374218854L;

	/** 品种ID	*/
	private String _id;
	
	/** 行业ID	*/
	private String industryId;
	
	/** 行业名称	*/
	private String industryName;
	
	/** 品种名称 */
	private String breedName;
	
	/** 用户ID	*/
	private String userId;
	
	/** 图片	*/
	private String image;

	/** 品种图片的背景颜色  */
	private String backgroundColor;

	/** 英文名称	*/
	private String enName;
	
	/** 创建日期	*/
	private Integer createdAt;
	
	/** 更新日期	*/
	private Integer updatedAt;

	/** 删除标记 */
	private Integer flag;
	
	/** 属性集 */
	private String attriList;

	/** 单个样品最大样本 */
	private Integer maxSpecimen;
	

}
