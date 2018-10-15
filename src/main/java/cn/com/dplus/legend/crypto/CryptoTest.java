package cn.com.dplus.legend.crypto;

import org.junit.Test;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:37 18-5-29
 * @Modified By:
 */

public class CryptoTest {

    @Test
    public void testToHexString() {
        final String hexChar = "0123456789ABCDEF";
        byte[] strBytes = "10".getBytes();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strBytes.length; i++) {
            sb.append(hexChar.charAt((strBytes[i] >> 4) & 0x0f));
            sb.append(hexChar.charAt(strBytes[i] & 0x0f));
        }
        System.out.println(sb.toString());
    }

}
