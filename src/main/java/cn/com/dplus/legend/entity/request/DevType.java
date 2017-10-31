package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 
 *  @类功能:	TODO	设备类型实体
 *	@文件名:	DevType.java
 * 	@所在包:	cn.com.dplus.d.entity.v2
 *	@开发者:	黄先国
 * 	@邮_件:     hsamgle@qq.com
 *  @时_间:		2017年4月11日下午2:53:07
 *	@公_司:		广州讯动网络科技有限公司
 */
@Table(name="d_type")
@Data
@EqualsAndHashCode(callSuper=false)
public class DevType extends BaseEntity{

	private static final long serialVersionUID = -3364050468562701422L;

	/** 对应记录的id  */
	private String id;
	
	/** 对应的类型名称  */
	private String typeName;
	
	/** 设备类型的对应的sn索引值  */
	private String snRef;

	/** 该设备类型对应的图片地址  */
	private String image;

	/** 更新时间 */
	private Timestamp updateTime;

	
}
