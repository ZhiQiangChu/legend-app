package cn.com.dplus.legend.rxjava;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午5:19 17-8-17
 * @Modified By:
 */
@AllArgsConstructor
@Data
public class Item {
    private String name;
    private int qty;
    private BigDecimal price;

}
