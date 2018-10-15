import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:08 18-6-26
 * @Modified By:
 */
public class RegxTest {


    public static void main(String[] args) {
        String fileName = "blk_1073767291";
        Pattern pattern = Pattern.compile("^blk_(-??\\d+)$");
        Matcher m = pattern.matcher(fileName);
        if (m.matches()) {
            System.out.println(m.group(1));
        }

    }
}
