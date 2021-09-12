package com.wxzy.aws.dynamodb.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wxzy.aws.dynamodb.model.output.CityTopList;

public class TestFileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    public static String getPath() {
        return TestFileUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }

    public static void main(String[] args) throws FileNotFoundException {
        // D:\idea-workspace\wxzy-project-dynamodb\target\classes\city.list.json
        File file = TestFileUtil.readFile("static/city.list.json");
        InputStream fileInputStream = getResourcesFileInputStream("static/city.list.json");
        // InputStream inputStream = new FileInputStream(file);
        String cityJson = StreamUtil.getString(fileInputStream);
        List<CityTopList> cityTopLists = JSON.parseArray(cityJson, CityTopList.class);
        System.err.println(cityTopLists + "vvvvvv");
    }
}