package com.yfax.task.htt.alipay.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Util {
    public static String read(String name) {
        StringBuffer buff = new StringBuffer();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(name);
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("//")) {
                    continue;
                }
                buff.append(line.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return buff.toString();
    }

}
