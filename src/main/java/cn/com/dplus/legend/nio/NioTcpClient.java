package cn.com.dplus.legend.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:53 18-9-29
 * @Modified By:
 */
public class NioTcpClient {


    private InetSocketAddress inetSocketAddress;

    private Selector selector;

    public NioTcpClient(String hostname, int port) {
        inetSocketAddress = new InetSocketAddress(hostname, port);
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String requestData) {
        try {
            SocketChannel clientChannel = SocketChannel.open(inetSocketAddress);
            clientChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isConnectable()) {
                        clientChannel.write(ByteBuffer.wrap(requestData.getBytes()));
                        while (true) {
                            byteBuffer.clear();
                            int read = clientChannel.read(byteBuffer);
                            if (read > 0) {
                                byteBuffer.flip();
                                System.out.println("Client: readbytes = " + read);
                                System.out.println("Client: data = " + new String(byteBuffer.array(), 0, read));
                                clientChannel.write(byteBuffer);
                                byteBuffer.compact();
                                break;
                            }
                        }
                    }
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        NioTcpClient client = new NioTcpClient("localhost", 8687);
        String requestData = "hhhhhhhhhhhh";
        client.send(requestData);
    }
}
