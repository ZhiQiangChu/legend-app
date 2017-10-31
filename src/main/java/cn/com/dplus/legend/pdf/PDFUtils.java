package cn.com.dplus.legend.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:35 17-10-16
 * @Modified By:
 */
public class PDFUtils {

    public Map readByPage() throws Exception {
        boolean sort = false;
        // pdf文件名
        String pdfFile = "";
        // 输入文本文件名称
        String textFile = null;
        // 编码方式
        String encoding = "UTF-8";
        // 开始提取页数
        int startPage = 1;
        // 结束提取页数
        int endPage = 5;
        // 文件输入流，生成文本文件
        Writer output = null;
        // 内存中存储的PDF Document
        PDDocument document = null;
        Map mpdf = new HashMap();
        try {
            try {
                // 首先当作一个URL来装载文件，如果得到异常再从本地文件系统//去装载文件
                URL url = new URL(pdfFile);
                //注意参数已不是以前版本中的URL.而是File。
                document = PDDocument.load(new File(pdfFile));
                // 获取PDF的文件名
                String fileName = url.getFile();
                // 以原来PDF的名称来命名新产生的txt文件
                if (fileName.length() > 4) {
                    File outputFile = new File(fileName.substring(0, fileName
                            .length() - 4)
                            + ".txt");
                    textFile = outputFile.getName();
                }
            } catch (MalformedURLException e) {
                // 如果作为URL装载得到异常则从文件系统装载
                //注意参数已不是以前版本中的URL.而是File。
                document = PDDocument.load(new File(pdfFile));
                if (pdfFile.length() > 4) {
                    textFile = pdfFile.substring(0, pdfFile.length() - 4)
                            + ".txt";
                }
            }
            // 文件输入流，写入文件倒textFile
            output = new OutputStreamWriter(new FileOutputStream(textFile),
                    encoding);
            // PDFTextStripper来提取文本
            PDFTextStripper stripper = null;
            stripper = new PDFTextStripper();
            // 设置是否排序
            stripper.setSortByPosition(sort);
            // 设置起始页
            stripper.setStartPage(startPage);
            // 设置结束页
            stripper.setEndPage(endPage);


            for (int i = startPage; i <= document.getNumberOfPages(); i++) {
                stripper = new PDFTextStripper();
                stripper.setSortByPosition(sort);
                // 设置起始页
                stripper.setStartPage(i);
                // 设置结束页
                stripper.setEndPage(i);
                String textT = stripper.getText(document);
                System.out.println("第" + i + "页");
                System.out.println("开始--------------------------------------------------------------------");
                System.out.println(textT);
                System.out.println("--------------------------------------------------------------------结束");
                mpdf.put(i, textT);
            }
            System.out.println(mpdf.size());
//             调用PDFTextStripper的writeText提取并输出文本
            stripper.writeText(document, output);
            System.out.println(stripper.getEndPage());
            System.out.println("*****=" + stripper.getText(document));
            System.out.println("*****22=" + stripper.getTextLineMatrix());
            System.out.println("*****33=" + stripper.getTextMatrix());
            System.out.println("*****44=" + stripper.getArticleStart());
            System.out.println("*****55=" + stripper.getArticleEnd());

        } finally {
            if (output != null) {
                // 关闭输出流
                output.close();
            }
            if (document != null) {
                // 关闭PDF Document
                document.close();
            }
        }
        return mpdf;
    }

    public static void getPdf() throws IOException {
        //Using PDFBox library available from http://pdfbox.apache.org/
        //Writes pdf document of specific pages as a new pdf file

        //Reads in pdf document
        File file = new File("/home/sondon/zhanjz/books/MongoDB权威指南第2版.pdf");
        PDDocument pdDoc = PDDocument.load(file);

        PDDocument document = null;

        try {
            document = new PDDocument();
            document.addPage((PDPage) pdDoc.getDocumentCatalog().getPages().get(0));
            document.save("/home/sondon/data/temp.pdf");
            document.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        try {
            getPdf();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
