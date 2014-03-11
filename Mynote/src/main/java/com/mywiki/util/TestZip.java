package com.mywiki.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 测试文件压缩与解压缩
 *
 */
public class TestZip {

    public static void main(String[] args) {
        //-->压缩单个文件
        zipSingleFileOrFolder("D:\\BIZFLOW\\src\\tst\\新建文本文档.zip", "D:\\BIZFLOW\\src\\tst\\新建文本文档.txt");
        //-->压缩文件夹
        //zipSingleFileOrFolder("D:\\BIZFLOW\\src\\tst.zip", "D:\\BIZFLOW\\src\\tst");
        //-->压缩多个文件(不指定压缩文件保存路径)
        //zipMultiFiles("","D:\\BIZFLOW\\src\\tst\\新建文本文档.txt","D:\\BIZFLOW\\src\\tst\\新建文本文档 - 副本.txt");
        //-->压缩多个文件(指定压缩文件保存路径)
        //zipMultiFiles("D:\\BIZFLOW\\src\\tst\\新建.zip","D:\\BIZFLOW\\src\\tst\\新建文本文档.txt","D:\\BIZFLOW\\src\\tst\\新建文本文档 - 副本.txt");
        //-->解压缩文件
        //unzipFile("D:\\BIZFLOW\\src\\tst\\新建", "D:\\BIZFLOW\\src\\tst\\新建文本文档.txt等.zip");
    }

    /**
     * 压缩单个文件/文件夹
     * @param destPath 压缩文件保存路径(为空串/null时默认压缩路径:待压缩文件所在目录，压缩文件名为待压缩文件/文件夹名+“等.zip”)
     * @param srcPath 待压缩的文件路径
     */
    public static void zipSingleFileOrFolder(String destPath, String srcPath) {
        if(StringUtils.isBlank(srcPath)) {
            throw new RuntimeException("待压缩文件夹不可为空！");
        }
        if(StringUtils.isNotBlank(destPath) && !destPath.endsWith(".zip")) {
            throw new RuntimeException("保存文件名应以.zip结尾！");
        }
        File file = new File(srcPath);
        if(!file.exists()) {
            throw new RuntimeException("路径'"+srcPath+"'下未找到文件");
        }
        if(file.isDirectory()) {
            //压缩文件夹
            zipFolder(destPath, srcPath);
        } else {
            //压缩单个文件
            zipFile(destPath, srcPath); 
        }
    }

    /**
     * 压缩多个文件
     * @param destPath 压缩文件保存路径(为空串/null时默认压缩路径:待压缩文件所在目录，压缩文件名为第一个待压缩文件名+“等.zip”)
     * @param srcPaths 待压缩文件路径集合
     */
    public static void zipMultiFiles(String destPath, String...srcPaths) {
        if(srcPaths==null || srcPaths.length==0) {
            throw new RuntimeException("待压缩文件夹不可为空！");
        }
        if(StringUtils.isNotBlank(destPath) && !destPath.endsWith(".zip")) {
            throw new RuntimeException("保存文件名应以.zip结尾！");
        }
        zipFiles(destPath, srcPaths);
    }

    /**
     * 解压缩文件
     * @param destPath 解压缩路径(为空串/null时默认解压缩路径:压缩文件所在目录)
     * @param zipPath 压缩文件路径
     */
    public static void unzipFile(String destPath, String zipPath) {
        if(StringUtils.isBlank(zipPath)) {
            throw new RuntimeException("压缩文件路径不可为空！");
        }
        if(!zipPath.endsWith(".zip")) {
            throw new RuntimeException("保存文件名应以.zip结尾！");
        }
        File file = new File(zipPath);
        if(StringUtils.isBlank(destPath)) {
            destPath = getUnzipBasePath(file);
        }
        if(!file.exists()) {
            throw new RuntimeException("路径'"+zipPath+"'下未找到文件");
        }
        ZipFile zipFile;
        int len;
        byte[] buff = new byte[1024];
        try {
            zipFile = new ZipFile(zipPath);
            //获取压缩文件中所有条目
            Enumeration<ZipEntry> entries = zipFile.getEntries();
            if(entries!=null) {
                while(entries.hasMoreElements()) {
                    //压缩文件条目转为文件或文件夹
                    ZipEntry zipEntry = entries.nextElement();
                    //获取输入流
                    InputStream ins = zipFile.getInputStream(zipEntry);
                    BufferedInputStream bis = new BufferedInputStream(ins);
                    File unzipFile = new File(destPath+File.separator+zipEntry.getName());
                    if(zipEntry.isDirectory()) {
                        unzipFile.mkdirs();
                        continue;
                    } 

                    File pf = unzipFile.getParentFile();
                    if(pf!=null && !pf.exists()) {
                        pf.mkdirs();
                    }
                    unzipFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(unzipFile);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    //输出流写文件
                    while((len=bis.read(buff, 0, 1024))!=-1) {
                        bos.write(buff, 0, len);
                    }
                    bos.flush();
                    fos.close();
                    bos.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * 获取解压缩文件基路径
     * @param zipPath
     * @return 
     */
    private static String getUnzipBasePath(File zipPath) {
        return zipPath.getParentFile().getAbsolutePath();
    }

    /**
     * 压缩单个文件
     * @param destPath 压缩文件保存路径
     * @param srcPath 待压缩文件路径
     */
    private static void zipFile(String destPath, String srcPath) {
        zipFiles(destPath, srcPath);
    }
    /**
     * 压缩多个文件
     * @param srcPath
     * @param zipPath
     */
    private static void zipFiles(String destPath, String...srcPaths) {
        if(StringUtils.isBlank(destPath)) {
            File srcFile0 = new File(srcPaths[0]);
            destPath = srcFile0.getParentFile().getAbsolutePath()+File.separator+srcFile0.getName()+"等.zip";
        }
        File zipFile = new File(destPath);
        FileOutputStream fos = null;
        try {
            zipFile.createNewFile();
            zipFile.mkdirs();
            fos = new FileOutputStream(zipFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        //获取输出流
        ZipOutputStream zipOs = new ZipOutputStream(fos);
        //循环将多个文件转为压缩文件条目
        for(String srcPath : srcPaths) {
            if(StringUtils.isBlank(srcPath)) {
                throw new RuntimeException("待压缩文件路径不可为空！");
            }
            File srcFile = new File(srcPath);
            try {
                ZipEntry zipEntry = new ZipEntry(srcFile.getName());
                zipOs.putNextEntry(zipEntry);
                //获取输入流
                FileInputStream fis = new FileInputStream(srcFile);
                int len;
                byte[] buff = new byte[1024];
                //输入/输出流对拷
                while((len=fis.read(buff))!=-1) {
                    zipOs.write(buff, 0, len);
                }
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        try {
            zipOs.flush();
            zipOs.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 压缩文件夹
     * @param destPath 压缩文件保存路径
     * @param srcPath 待压缩文件夹路径
     */
    private static void zipFolder(String destPath, String srcPath) {
        File srcFile = new File(srcPath);
        //如压缩文件保存路径为空，则默认保存路径
        if(StringUtils.isBlank(destPath)) {
            destPath = srcFile.getParent()+File.separator+srcFile.getName()+".zip";
        }
        FileOutputStream fos = null;
        ZipOutputStream zipOs = null;
        try {
            fos = new FileOutputStream(destPath);
            zipOs = new ZipOutputStream(fos);
            //递归往压缩文件中添加文件夹条目
            zipFolder(srcFile, srcFile, zipOs);
            zipOs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 递归往压缩文件中添加文件夹条目
     * @param srcFile 待压缩文件夹路径
     * @param baseFile 
     * @param zipOs
     */
    private static void zipFolder(File srcFile, File baseFile, ZipOutputStream zipOs) {
        int len;
        byte[] buff = new byte[1024];
        if(srcFile.isDirectory()) {
            //针对空文件夹需要特殊处理，需要往Entry中放入"/"+文件夹名+"/"项
            try {
                zipOs.putNextEntry(new ZipEntry(srcFile.getAbsolutePath().replaceFirst(baseFile.getParentFile().getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"), "")+"/"));
                zipOs.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
            //获取当前文件夹下文件列表
            File[] fileLists = srcFile.listFiles();
            if(fileLists!=null && fileLists.length>0) {
                for (int i = 0; i < fileLists.length; i++) {
                    //递归往压缩文件中添加文件夹条目
                    zipFolder(fileLists[i], baseFile, zipOs);
                }
            }
        } else {
            //文件处理比较简单，直接放入Entry
            try {
                FileInputStream fis = new FileInputStream(srcFile);
                zipOs.putNextEntry(new ZipEntry(getRelativePath(baseFile.getAbsolutePath(), srcFile)));
                while((len=fis.read(buff))!=-1) {
                    zipOs.write(buff, 0, len);
                }
                zipOs.closeEntry();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    /**
     * 获取相对基路径的相对路径
     * @param basePath
     * @param file
     * @return
     */
    private static String getRelativePath(String basePath, File file) {
        if(!basePath.equalsIgnoreCase(file.getAbsolutePath())) {
            return getRelativePath(basePath, file.getParentFile())+File.separator+file.getName();
        } else {
            return file.getName();
        }
    }

}