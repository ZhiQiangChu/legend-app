package cn.com.dplus.legend.entity.mongo;

import lombok.Data;
import org.mongodb.morphia.annotations.Embedded;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午11:33 17-7-14
 * @Modified By:
 */
@Data
@Embedded
public class Size {

    Integer h;
    Integer w;
    String uom;

    // 必须有一个无参的默认构造方法。
    public Size() {
    }

    public Size(Integer h, Integer w, String uom) {
        this.h = h;
        this.w = w;
        this.uom = uom;
    }
}
