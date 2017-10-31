package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.annotation.ParamsValid;
import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * 
 *  @作用:	TODO 行业持久类
 *	@文件名:	Industrys.java
 * 	@所在包:	cn.com.dplus.sample.entity
 *	@开发者: 	余浪
 * 	@邮件: 	365617581@qq.com
 *  @时间:	2016年9月27日
 *	@公司:	广州讯动网络科技有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(value="INDUSTRY",noClassnameStored=true)
public class Industry extends BaseEntity {


	private static final long serialVersionUID = -7254274476668792714L;

	/** 行业id */
	private String _id;
	
	/** 行业名称	*/
	private String industryName;

	/** 记录创建时间 */
	private Long createTime;

	/** 记录更新时间  */
	private Long updateTime;

	/** 状态 */
	private Integer state;
	
}
