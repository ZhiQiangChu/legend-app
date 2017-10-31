import cn.com.dplus.legend.application.Application;
import cn.com.dplus.project.utils.JsonUtil;
import cn.com.dplus.redis.SentinelJedisCluster;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.io.File;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:48 17-7-5
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AppTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void testGetPdf() {
        File file = new File("/home/sondon/zhanjz/books/MongoDB权威指南第2版.pdf");
        Jedis jedis = null;
        try {
            jedis = SentinelJedisCluster.getJedis();
            System.out.println("step 1");
            PDDocument pdDoc = PDDocument.load(file);
//            jedis.set("pdf", JsonUtil.toJson(pdDoc));
            PDDocument document = new PDDocument();
            PDPageTree pages = pdDoc.getDocumentCatalog().getPages();
            System.out.println("step 2:pages->"+pages.getCount());
            document.addPage(pages.get(4));
            document.save("/home/sondon/data/temp.pdf");
            System.out.println("step 3");
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                SentinelJedisCluster.returnToPool(jedis);
            }
        }
    }
}
