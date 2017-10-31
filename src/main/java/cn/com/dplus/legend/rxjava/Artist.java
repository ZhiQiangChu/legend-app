package cn.com.dplus.legend.rxjava;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:11 17-8-18
 * @Modified By:
 */
@Data
@AllArgsConstructor
public class Artist {
    private String name;
    private String city;
    private Integer age;

    public boolean isFrom(String city) {
        return this.city.equals(city);
    }
}
