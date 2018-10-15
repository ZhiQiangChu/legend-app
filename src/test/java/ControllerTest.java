import cn.com.dplus.legend.application.Application;
import cn.com.dplus.project.http.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:44 17-11-9
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ControllerTest {

    @Test
    public void testAccess() {
        String url = "http://127.0.0.1:6588/v1/access";
        try {
            HttpUtils.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
