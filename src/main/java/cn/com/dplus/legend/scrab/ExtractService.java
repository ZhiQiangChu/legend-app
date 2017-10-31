package cn.com.dplus.legend.scrab;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午8:51 17-8-16
 * @Modified By:
 */
public class ExtractService {


    public static void main(String[] args) {
        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/45.html";
        ExtractService service = new ExtractService();
        CopyOnWriteArrayList<Area> areas = service.grab(url, 1, AreaConstants.class_tags.get(1), "450000000000");
        areas.stream().forEach(System.out::println);
    }

    private CopyOnWriteArrayList<Area> grab(String url, Integer level, String classTag, String pcode) {
        Document doc = getDocument(url);
        CopyOnWriteArrayList<Area> areas = getArea(doc, level, classTag, pcode, url);
        areas.stream().forEach(System.out::println);
        for (Area area : areas) {
            areas.add(area);
            if (area.getHref() != null && area.getLevel() <= 2) {
                //递归
                CopyOnWriteArrayList<Area> tempAreas = grab(area.getHref(), area.getLevel() + 1, AreaConstants.class_tags.get(area.getLevel() + 1), area.getCode());
                if (!tempAreas.isEmpty()) {
                    areas.addAll(tempAreas);
                }
            }

        }
        return areas;
    }

    private Document getDocument(String url) {
        Document doc = null;
        try {
            Connection conn = Jsoup.connect(url);

            try {
                doc = conn.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(url);
            throw e;
        }
        return doc;
    }

    private CopyOnWriteArrayList<Area> getArea(Document doc, Integer level, String classTag, String pcode, String url) {
        Elements results = doc.getElementsByClass(classTag);
        CopyOnWriteArrayList<Area> areas = new CopyOnWriteArrayList<>();
        if (level == 4) { // 村
            for (Element e : results) {
                Area area = new Area();
                Elements links = e.getElementsByTag("td");
                if (links.size() == 3) {
                    String code = links.get(0).text();
                    String name = links.get(2).text();
                    area.setCode(code);
                    area.setName(name);
                    area.setLevel(level);
                    area.setPcode(pcode);
                    areas.add(area);
                }

            }
            return areas;
        }
        for (Element e : results) {
            Area area = new Area();
            Elements links = e.getElementsByTag("a");
            if (links.size() == 2) {
                String linkHref = links.get(0).attr("href");
                String code = links.get(0).text();
                String name = links.get(1).text();
                area.setCode(code);
                area.setName(name);
                area.setLevel(level);
                area.setPcode(pcode);
                area.setHref(url.substring(0, url.lastIndexOf("/")) + "/" + linkHref);
                areas.add(area);
            }
        }
        return areas;
    }
}
