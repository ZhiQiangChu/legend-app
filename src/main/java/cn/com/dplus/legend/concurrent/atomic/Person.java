package cn.com.dplus.legend.concurrent.atomic;

import lombok.Data;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:07 18-10-23
 * @Modified By:
 */
@Data
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}


