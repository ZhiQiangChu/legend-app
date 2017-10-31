package cn.com.dplus.legend.entity.request;

import cn.com.dplus.mongodb.annatation.MGroup;
import cn.com.dplus.mongodb.annatation.SortType;
import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mongodb.morphia.annotations.*;

/**
 * 
 *  @类功能:	TODO	设备性能指标检测记录
 *	@文件名:	DevSelfCheckingRecord.java
 * 	@所在包:	cn.com.dplus.d.entity.v2
 *	@开发者:	黄先国
 * 	@邮_件:     hsamgle@qq.com
 *  @时_间:		2017年4月18日下午1:43:30
 *	@公_司:		广州讯动网络科技有限公司
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DevPerformanceRecord extends BaseEntity{
	
	private static final long serialVersionUID = -9102342361788384048L;

	private String _id;
	
	/** 对应的设备的sn  */
	private String dSn;
	
	/** 当前记录的类型  */
	private Integer type;
	
	/** 当前记录状态   -5 时代表的是出厂时的参数    0 未生效    1 记录已经生效  -1 删除 */
	private Integer state;
	
	/** 检测异常码   0  时代表成功  其他代表异常 */
	private Integer errCode;
	
	/** 开始的时间 */
	private Long startTime;
	
	/** 开启时的设备状态id  */
	private String startStateId;
	
	/** 结束时间  */
	private Long endTime;
	
	/** 结束时的状态id  */
	private String finishStateId;

	/** 设置对外的返回结果   不做保存*/
	private Object data;
	
	/** 备注  通常 在设备自检上生效*/
	private Object remark;


    /**
     * 封装记录给前端
     */
    @Data
	public static class SimpleOpRecord{

	    @MGroup(mId = true)
        private Integer type;

        private Integer state;

        @MGroup(sort = SortType.DESC)
        private Long endTime;

    }

}
