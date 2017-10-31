package cn.com.dplus.legend.entity.request;

import cn.com.dplus.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 样品理化值
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:36 17-8-28
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SampleAttrValue extends BaseEntity {

    private static final long serialVersionUID = 1253589409740406959L;

    /** 样品ID */
    private String sampleId;

    /** 行业ID */
    private String industryId;

    /** 行业名称 */
    private String industryName;

    /** 品种ID */
    private String breedId;

    /** 品种名称 */
    private String breedName;

    /** 指标ID */
    private String indicatorId;

    /** 指标名称 */
    private String indicatorName;

    /** 属性ID */
    private String attrId;

    /** 属性类型  0:指标类型 1:基础类型 */
    private Integer attrType;

    /** 属性名称 */
    private String attrName;

    /** 属性值 */
    private String attrValue;

    public static void main(String[] args) {
        SampleAttrValue sampleAttrValue = new SampleAttrValue();
        sampleAttrValue.setSampleId("3bce48c1c16a47ecafd8da3b9efd3160");
        sampleAttrValue.setIndustryId("faa573520a414f3d928260670984ba4f");
        sampleAttrValue.setIndustryName("饲料");
        sampleAttrValue.setBreedId("986f9537326a486899c2556834bf5b08");
        sampleAttrValue.setBreedName("玉米");
        sampleAttrValue.setIndicatorId("4904ee858da941ccb913a971a4558338");
        sampleAttrValue.setIndicatorName("水分");
        sampleAttrValue.setAttrId("c77fddf132de46859cf35e484161aefb");
        sampleAttrValue.setAttrType(0);
        sampleAttrValue.setAttrName("水分");
        sampleAttrValue.setAttrValue("9.21");
        System.out.println(sampleAttrValue.toJson());
    }

}
