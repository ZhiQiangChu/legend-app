package cn.com.dplus.legend.rxjava;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午7:52 17-8-18
 * @Modified By:
 */
@Data
public class Student {
    private String name;
    private Set<String> book;

    public void addBook(String book) {
        if (this.book == null) {
            this.book = new HashSet<>();
        }
        this.book.add(book);
    }
}
