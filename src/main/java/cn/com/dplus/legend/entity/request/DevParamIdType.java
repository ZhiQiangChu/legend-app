package cn.com.dplus.legend.entity.request;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 *  @类功能:	TODO	设备参数分类实体
 *	@文件名:	DevParamIdType.java
 * 	@所在包:	cn.com.dplus.d.entity.v2
 *	@开发者:	黄先国
 * 	@邮_件:     hsamgle@qq.com
 *  @时_间:		2017年4月28日上午11:46:49
 *	@公_司:		广州讯动网络科技有限公司
 */
@Table(name="d_param_type")
@Data
public class DevParamIdType {

	@Id
	private Integer id;
	
	/** 设备参数分类名称  */
	private String typeName;
	
}
