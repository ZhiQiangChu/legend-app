import java.io.*;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author 詹军政|zhanjz@sondon.net
 * @ClassName: Test
 * @Description: TODO
 * @date Jun 8, 2017 2:12:20 PM
 */
public class GenerateMd {
    public static void main(String[] args) {

        String packageName = "cn.com.dplus.legend.entity.request";
        String packageDir = "/home/sondon/IdeaProjects/dplus-service/legend-app/src/main/java/cn/com/dplus/legend/entity/request/";
        Set<String> clSet = getClassName(packageName, true);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("/home/sondon/IdeaProjects/dplus-service/legend-app/doc/design/API/api.md"));
            Class cls = null;
            for (String clsName : clSet) {
                cls = Class.forName(clsName);
                out.write("\n");
                Field[] fields = cls.getDeclaredFields();
                out.write("|参数名称         |类型      |描述       |\n|:-------------|:-------------|:-------------|\n");
                for (Field f : fields) {
                    if (f.getName().equals("serialVersionUID")) {
                        continue;
                    }
                    String name = f.getName();
                    String typeName = f.getGenericType().getTypeName();
                    String comment = parseFieldComment(packageDir + cls.getSimpleName() + ".java", name);
                    out.write("|" + name + "\t|" + typeName.substring(typeName.lastIndexOf(".") + 1) + "\t|" + comment + "\t|\n");

                }
                out.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    public static String parseFieldComment(String fileName, String fieldName) throws Exception {
        FileReader reader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        // int lineNum = 0;
        String comment = "";
        String preLine = "";
        while ((str = br.readLine()) != null) {
            if (str.contains(fieldName)) {
                Pattern leftpattern = Pattern.compile("\\//");
                Matcher leftmatcher = leftpattern.matcher(str);
                if (leftmatcher.find()) {
                    comment = str.substring(leftmatcher.start() + 2).trim();
                    break;
                } else {
                    Pattern temPattern = Pattern.compile("\\/\\*\\*");
                    Matcher temMatcher = temPattern.matcher(preLine);
                    if (temMatcher.find()) {
                        comment = preLine.substring(temMatcher.start()).replaceAll("\\/", "").replaceAll("\\*", "").trim();
                        break;
                    }
                }
            }
            preLine = str;
        }
        br.close();
        reader.close();
        return comment;
    }

    public static Set<String> getClassName(String packageName, boolean isRecursion) {
        Set<String> classNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");

        URL url = loader.getResource(packagePath);
        if (url != null) {
            String protocol = url.getProtocol();
            if (protocol.equals("file")) {
                classNames = getClassNameFromDir(url.getPath(), packageName, isRecursion);
            } else if (protocol.equals("jar")) {
                JarFile jarFile = null;
                try {
                    jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (jarFile != null) {
                    getClassNameFromJar(jarFile.entries(), packageName, isRecursion);
                }
            }
        } else {
        /*从所有的jar包中查找包名*/
            classNames = getClassNameFromJars(((URLClassLoader) loader).getURLs(), packageName, isRecursion);
        }

        return classNames;
    }

    private static Set<String> getClassNameFromDir(String filePath, String packageName, boolean isRecursion) {
        Set<String> className = new HashSet<String>();
        File file = new File(filePath);
        File[] files = file.listFiles();
        for (File childFile : files) {
            if (childFile.isDirectory()) {
                if (isRecursion) {
                    className.addAll(getClassNameFromDir(childFile.getPath(), packageName + "." + childFile.getName(), isRecursion));
                }
            } else {
                String fileName = childFile.getName();
                if (fileName.endsWith(".class") && !fileName.contains("$")) {
                    className.add(packageName + "." + fileName.replace(".class", ""));
                }
            }
        }

        return className;
    }

    /**
     * @param jarEntries
     * @param packageName
     * @param isRecursion
     * @return
     */
    private static Set<String> getClassNameFromJar(Enumeration<JarEntry> jarEntries, String packageName, boolean isRecursion) {
        Set<String> classNames = new HashSet<String>();

        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            if (!jarEntry.isDirectory()) {
        /*
        * 这里是为了方便，先把"/" 转成 "." 再判断 ".class" 的做法可能会有bug
		* (FIXME: 先把"/" 转成 "." 再判断 ".class" 的做法可能会有bug)
		*/
                String entryName = jarEntry.getName().replace("/", ".");
                if (entryName.endsWith(".class") && !entryName.contains("$") && entryName.startsWith(packageName)) {
                    entryName = entryName.replace(".class", "");
                    if (isRecursion) {
                        classNames.add(entryName);
                    } else if (!entryName.replace(packageName + ".", "").contains(".")) {
                        classNames.add(entryName);
                    }
                }
            }
        }

        return classNames;
    }

    /**
     * 从所有jar中搜索该包，并获取该包下所有类
     *
     * @param urls        URL集合
     * @param packageName 包路径
     * @param isRecursion 是否遍历子包
     * @return 类的完整名称
     */
    private static Set<String> getClassNameFromJars(URL[] urls, String packageName, boolean isRecursion) {
        Set<String> classNames = new HashSet<String>();

        for (int i = 0; i < urls.length; i++) {
            String classPath = urls[i].getPath();

            // 不必搜索classes文件夹
            if (classPath.endsWith("classes/")) {
                continue;
            }

            JarFile jarFile = null;
            try {
                jarFile = new JarFile(classPath.substring(classPath.indexOf("/")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (jarFile != null) {
                classNames.addAll(getClassNameFromJar(jarFile.entries(), packageName, isRecursion));
            }
        }

        return classNames;
    }
}
