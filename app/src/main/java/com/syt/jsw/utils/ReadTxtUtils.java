package com.syt.jsw.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadTxtUtils {

    /*文件路径*/
    public static String readTxtFile(String strFilePath) {
        File file = new File(strFilePath);
        BufferedReader reader;
        StringBuilder content = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedInputStream.mark(4);
            byte[] thirdBytes = new byte[3];
            //找到文档的前三个字节并自动判断文档类型。
            bufferedInputStream.read(thirdBytes);
            bufferedInputStream.reset();
            // utf-8
            if (thirdBytes[0] == (byte) 0xEF && thirdBytes[1] == (byte) 0xBB && thirdBytes[2] == (byte) 0xBF) {
                reader = new BufferedReader(new InputStreamReader(bufferedInputStream, "utf-8"));
            } else if (thirdBytes[0] == (byte) 0xFF && thirdBytes[1] == (byte) 0xFE) {
                reader = new BufferedReader(new InputStreamReader(bufferedInputStream, "unicode"));
            } else if (thirdBytes[0] == (byte) 0xFE && thirdBytes[1] == (byte) 0xFF) {
                reader = new BufferedReader(new InputStreamReader(bufferedInputStream, "utf-16be"));
            } else if (thirdBytes[0] == (byte) 0xFF && thirdBytes[1] == (byte) 0xFF) {
                reader = new BufferedReader(new InputStreamReader(bufferedInputStream, "utf-16le"));
            } else {
                reader = new BufferedReader(new InputStreamReader(bufferedInputStream, "GBK"));
            }
            String str = reader.readLine();
            while (str != null) {
                content.append(str).append("\n");
                str = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
