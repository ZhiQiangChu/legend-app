package cn.com.dplus.legend.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午7:33 17-9-19
 * @Modified By:
 */
public class CacheTest extends TestCase {

    @Test
    public void testLoadingCache() throws ExecutionException {
        LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(10, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                String strProValue = "hello " + key;
                return strProValue;
            }
        });
        System.out.println("Jerry value:" + cacheBuilder.get("Jerry"));
        System.out.println("Jerry value:" + cacheBuilder.get("zhan"));
        try {
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("Jerry value:" + cacheBuilder.apply("Jerry"));
        System.out.println("Jerry value:" + cacheBuilder.get("Jerry"));
        System.out.println("Jerry value:" + cacheBuilder.get("zhan"));
    }

    @Test
    public void testCallableCache() throws ExecutionException {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(10, TimeUnit.MINUTES).build();
        String returnValue = cache.get("jerry", () -> {
            String strProValue = "hello " + "jerry!";
            return strProValue;
        });
        System.out.println(returnValue);
    }
}
