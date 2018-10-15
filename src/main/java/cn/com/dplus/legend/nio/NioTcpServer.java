package cn.com.dplus.legend.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:52 18-9-29
 * @Modified By:
 */
public class NioTcpServer extends Thread {


    private Handler handler = new ServerHandler();

    private InetSocketAddress inetSocketAddress;

    public NioTcpServer(String hostname, int port) {
        inetSocketAddress = new InetSocketAddress(hostname, port);
    }

    @Override
    public void run() {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(inetSocketAddress);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server: socket server started.");
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        System.out.println("Server: SelectonKey is acceptable.");
                        handler.handleAccept(key);
                    } else if (key.isReadable()) {
                        System.out.println("Server: SelectonKey is readable.");
                        handler.handleRead(key);
                    } else if (key.isWritable()) {
                        System.out.println("Server: SelectonKey is writable.");
                        handler.handleWrite(key);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    interface Handler {
        void handleAccept(SelectionKey key) throws IOException;

        void handleRead(SelectionKey key) throws IOException;

        void handleWrite(SelectionKey key) throws IOException;
    }


    class ServerHandler implements Handler {

        @Override
        public void handleAccept(SelectionKey key) throws IOException {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("Server: accept client socket " + serverSocketChannel);
            socketChannel.configureBlocking(false);
            socketChannel.register(key.selector(), SelectionKey.OP_READ);
        }

        @Override
        public void handleRead(SelectionKey key) throws IOException {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = socketChannel.read(byteBuffer);
            while (read > 0) {
                System.out.println("Server: readbytes = " + read);
                System.out.println("Server: data = " + new String(byteBuffer.array(), 0, read));
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
                read = socketChannel.read(byteBuffer);
            }
            if (read <= 0) {
                key.interestOps(SelectionKey.OP_WRITE).attach(new String("hello"));
//                socketChannel.register(key.selector(), SelectionKey.OP_WRITE).attach(new String("hello"));
            }
//            socketChannel.close();
        }

        @Override
        public void handleWrite(SelectionKey key) throws IOException {
            String attachment = (String) key.attachment();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put((attachment + " world").getBytes("UTF-8"));
            SocketChannel socketChannel = (SocketChannel) key.channel();
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            key.interestOps(SelectionKey.OP_READ);
            byteBuffer.compact();
        }
    }


    public static void main(String[] args) {
        NioTcpServer tcpServer = new NioTcpServer("localhost", 8687);
        tcpServer.start();
    }
}
