package com.chengxuunion.util.file;

import com.chengxuunion.util.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @Author youpanpan
 * @Description:    zip压缩工具
 * @Date:创建时间: 2018-12-17 15:42
 * @Modified By:
 */
public class ZipUtils {

    private static Logger logger = LoggerFactory.getLogger(ZipUtils.class);

    private static final int BUFFER_SIZE = 1024 * 4;

    private ZipUtils() {

    }

    /**
     * 压缩多个目录下的内容到指定的输出流中
     *
     * @param srcDirList
     * @param out
     * @throws Exception
     */
    public static void compress(List<String> srcDirList, OutputStream out) throws Exception{
        compress(srcDirList, out, false);
    }

    /**
     * 压缩多个目录下的内容到指定的输出流中
     *
     * @param srcDirList
     * @param out
     * @param includeRoot
     * @throws Exception
     */
    public static void compress(List<String> srcDirList, OutputStream out, boolean includeRoot) throws Exception {
        ZipOutputStream zos = null ;
        try {
            long start = System.currentTimeMillis();
            zos = new ZipOutputStream(out);

            for (String srcDir : srcDirList) {
                File sourceFile = new File(srcDir);
                String rootName = "";
                if (includeRoot) {
                    rootName = sourceFile.getName();
                }
                compress(sourceFile, zos, rootName);
            }

            long end = System.currentTimeMillis();
            logger.debug("压缩完成，耗时：" + (end - start) +" ms");
        } finally {
            if(zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 压缩指定目录下的文件到指定输出流的压缩文件中
     *
     * @param srcDir
     * @param out
     * @throws Exception
     */
    public static void compress(String srcDir, OutputStream out) throws Exception {
        compress(Arrays.asList(srcDir), out, false);
    }

    /**
     * 压缩指定目录下的文件到指定输出流的压缩文件中
     *
     * @param srcDir    要压缩的目录
     * @param out       压缩文件输出流
     * @param includeRoot   是否包含压缩目录的根目录名，如果为false则只压缩目录下的目录/文件
     * @throws Exception
     */
    public static void compress(String srcDir, OutputStream out, boolean includeRoot) throws Exception {
        compress(Arrays.asList(srcDir), out, includeRoot);
    }

    /**
     * 压缩指定文件或目录到压缩输出流中
     *
     * @param sourceFile    目录/文件
     * @param zos           压缩文件输出流
     * @param name          目录/文件名称
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name) throws Exception {
        if (sourceFile.isFile()) {
            byte[] buf = new byte[BUFFER_SIZE];

            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));

            // copy文件到zip输出流中
            int len;
            FileInputStream in = null;
            try {
                in = new FileInputStream(sourceFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                // Complete the entry
                zos.closeEntry();
            } catch (IOException e) {
                throw e;
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0) {
                if (StringUtils.isNotEmpty(name)) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    if (StringUtils.isNotEmpty(name)) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName());
                    } else {
                        compress(file, zos, file.getName());
                    }
                }
            }
        }
    }

    /**
     * 将原压缩文件解压到指定的目录中,并替换目录和文件中的变量，变量格式：{{变量名}}
     * @param zipPath   zip压缩文件目录
     * @param destDir   要解压的目标目录
     * @param dataMap   数据集合，用于替换目录中的变量
     */
    @SuppressWarnings({ "rawtypes", "resource" })
	public static void decompress(String zipPath, String destDir, Map<String, Object> dataMap) throws Exception {
        File file = new File(zipPath);
        if (!file.exists()) {
            throw new RuntimeException(zipPath + "所指文件不存在");
        }

        ZipFile zf = new ZipFile(file);
        Enumeration entries = zf.entries();
        ZipEntry entry = null;
        while (entries.hasMoreElements()) {
            entry = (ZipEntry) entries.nextElement();
            if (entry.isDirectory()) {
                String dirPath = destDir + File.separator + replaceVariable(entry.getName(), dataMap);
                File dir = new File(dirPath);
                dir.mkdirs();
            } else {
                // 表示文件
                File f = new File(destDir + File.separator + replaceVariable(entry.getName(), dataMap));
                if (!f.exists()) {
                    String dirs = f.getParent();
                    File parentDir = new File(dirs);
                    parentDir.mkdirs();
                }

                f.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = null;
                FileOutputStream fos = null;
                try {
                    is = zf.getInputStream(entry);
                    fos = new FileOutputStream(f);
                    int count;
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((count = is.read(buf)) != -1) {
                        fos.write(buf, 0, count);
                    }
                } finally {
                    is.close();
                    fos.close();
                }
            }
        }
    }


    /**
     * 将原压缩文件解压到指定的目录中
     * @param zipPath   zip压缩文件目录
     * @param destDir   要解压的目标目录
     */
    @SuppressWarnings("unchecked")
	public static void decompress(String zipPath, String destDir) throws Exception {
        decompress(zipPath, destDir, Collections.EMPTY_MAP);
    }

    /**
     * 将多个文件打包成zip，并输出
     *
     * @param out   输出流
     * @param fileList  要打包的文件列表（本地地址）
     */
    public static boolean packageZip(OutputStream out, List<String> fileList) {
        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;

        try {
            zipStream = new ZipOutputStream(out);
            for (String filePath : fileList) {

                File file = new File(filePath);
                zipSource = new FileInputStream(file);

                byte[] bufferArea = new byte[1024 * 10];// 读写缓冲区

                // 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipStream.putNextEntry(zipEntry);// 定位到该压缩条目位置，开始写入文件到压缩包中

                bufferStream = new BufferedInputStream(zipSource, 1024 * 10);// 输入缓冲流
                int read = 0;

                // 在任何情况下，b[0] 到 b[off] 的元素以及 b[off+len] 到 b[b.length-1]
                // 的元素都不会受到影响。这个是官方API给出的read方法说明，经典！
                while ((read = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
                    zipStream.write(bufferArea, 0, read);
                }
            }

            return true;
        } catch (Exception e) {
            logger.error("压缩多个文件【" + fileList +"】为zip文件出现异常", e);
        } finally {
            // 关闭流
            try {
                if (null != bufferStream) {
                    bufferStream.close();
                }
                if (null != zipStream) {
                    zipStream.close();
                }
                if (null != zipSource) {
                    zipSource.close();
                }
            } catch (IOException e) {
                logger.error("关闭流出现异常", e);
            }
        }

        return false;
    }

    /**
     * 替换字符串中的变量（{{变量名}}）,并使用变量值进行替换
     *
     * @param name
     * @param dataMap
     * @return
     */
    private static String replaceVariable(String name, Map<String, Object> dataMap) {
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            if (name.contains(entry.getKey())) {
                name = name.replaceAll("\\{\\{"+ entry.getKey() +"\\}\\}", entry.getValue().toString());
            }
        }
        return name;
    }

}
