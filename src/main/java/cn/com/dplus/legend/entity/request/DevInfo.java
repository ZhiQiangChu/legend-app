package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 
 *  @类功能:	TODO		设备的基本静态信息
 *	@文件名:	DevInfo.java
 * 	@所在包:	cn.com.dplus.d.entity.v2
 *	@开发者:	黄先国
 * 	@邮_件:     hsamgle@qq.com
 *  @时_间:		2017年4月17日下午1:31:04
 *	@公_司:		广州讯动网络科技有限公司
 */
@Table(name="d_info")
@Data
@EqualsAndHashCode(callSuper=false)
public class DevInfo extends BaseEntity{

	private static final long serialVersionUID = -1218085020994407152L;

	/** 对应的设备的id  */
	private String id;
	
	/** 设备类型的id  */
	private String typeId;
	
	/** 设备的类型名称  */
	private String typeName;
	
	/** 设备的sn  */
	private String dSn;
	
	/** 设备的生产批次  */
	private String batch;
	
	/** 设备的生产日期  */
	private Date productionDate;
	
	/** 设备的添加日期  */
	private Timestamp createDate;
	
	/** 设备的mac地址  */
	private String MAC;
	
	/** 激活状态  */
	private Integer isActive;
	
	/** 激活日期  */
	private Timestamp activeDate;
	
	/** 流转状态  */
	private Integer flowState;
	

}
