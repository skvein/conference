package com.tw.conference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Authir : skevin
 * @Description:
 * @Date : create in 16:27 2018/12/5
 * @Mail :shenkai_cd@keruyun.com
 */
public class Conference {

    public static Map<String,Integer> getConferences(){
        Map<String,Integer> talkMap=new HashMap<>();
        File file = new File("src/main/resources/talks.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            int numTime = 0;
            while ((line = reader.readLine()) != null) {
                int lastBlank = line.lastIndexOf(" ");
                String title = line.substring(0, lastBlank);
                String time = line.substring(lastBlank + 1);
                if (time.equals("lightning")) {
                    numTime = 5;
                } else {
                    numTime = Integer.parseInt(time.substring(0, time.length() - 3));
                }
                talkMap.put(title, numTime);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return talkMap;
    }


}
