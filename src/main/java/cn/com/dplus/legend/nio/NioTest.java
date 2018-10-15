package cn.com.dplus.legend.nio;

import lombok.Cleanup;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午11:06 18-5-29
 * @Modified By:
 */
public class NioTest {

    @Test
    public void readNIO() throws Exception {
        String pathName = "/home/sondon/textsecure.yml";

        try {
            @Cleanup FileInputStream fin = new FileInputStream(new File(pathName));
            @Cleanup FileChannel channel = fin.getChannel();
            int capacity = 100;
            ByteBuffer bf = ByteBuffer.allocate(capacity);
            System.out.println("限制是：" + bf.limit() + ",容量是:" + bf.capacity() + ",位置是：" + bf.position());
            int length = -1;
            while ((length = channel.read(bf)) != -1) {
                bf.clear();
                byte[] bytes = bf.array();
                System.out.write(bytes, 0, length);
                System.out.println();
                System.out.println("限制是：" + bf.limit() + ",容量是:" + bf.capacity() + ",位置是:" + bf.position());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeNIO() throws Exception {
        String fileName = "out.txt";
        @Cleanup FileOutputStream fos = new FileOutputStream(new File(fileName));
        @Cleanup FileChannel channel = fos.getChannel();
        ByteBuffer src = Charset.forName("utf-8").encode("hhhhhhhh");
        System.out.println("初始化容量和limit:" + src.capacity() + "," + src.limit());
        int length = 0;
        while ((length = channel.write(src)) != 0) {
            System.out.println("写入长度:" + length);
        }
    }

    @Test
    public void testFile() throws Exception {
        RandomAccessFile accessFile = new RandomAccessFile("/home/sondon/textsecure.yml", "rw");
        FileChannel inChannel = accessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int byteRead = inChannel.read(buf);
        while (byteRead != -1) {
            System.out.println("Read " + byteRead);
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.println((char) buf.get());
            }

            buf.clear();
            inChannel.read(buf);
        }
        accessFile.close();
    }
}
