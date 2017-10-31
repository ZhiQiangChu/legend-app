package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.annotation.ParamsValid;
import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 
 *  @类功能:	TODO	记录设备的配置信息	
 *	@文件名:	DevConfig.java
 * 	@所在包:	cn.com.dplus.d.entity.v2
 *	@开发者:	黄先国
 * 	@邮_件:     hsamgle@qq.com
 *  @时_间:		2017年4月17日下午1:40:52
 *	@公_司:		广州讯动网络科技有限公司
 */
@Table(name="d_param_ids")
@Data
@EqualsAndHashCode(callSuper=false)
public class DevParamIds extends BaseEntity implements Comparable<DevParamIds>{

	private static final long serialVersionUID = 2540083762314223602L;
	
	private Integer id;

	/** 16进制的id索引 */
	private String hexId;
	
	/** 匹配名称  */
	@Column(name="param_name")
	private String paramName;
	
	/** 是否是只读属性  */
	private Integer readOnly;
	
	/** 是否是全局参数  */
	private Integer isGlobal;
	
	/** 值的单位 */
	private String unit;
	
	/** 参数类型  */
	private Integer type;
	
	/** 参数对应的数据长度  */
	private Integer len;

	
	@Override
	public int compareTo(DevParamIds o) {
		return this.id.compareTo(o.getId());
	}
}
