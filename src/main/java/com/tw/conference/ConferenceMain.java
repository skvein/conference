package com.tw.conference;

import java.util.Map;
import java.util.Scanner;

/**
 * @Authir : skevin
 * @Description:
 * @Date : create in 16:03 2018/12/5
 * @Mail :shenkai_cd@keruyun.com
 */
public class ConferenceMain {

    public static void main(String[] args) {
        //获取会议信息
        System.out.print("当前会议信息");
        Conference conference=new Conference();
        Map<String,Integer> conferenceMap= conference.getConferences();
        conferenceMap.forEach((k,v)->{
            System.out.println("会议题目："+k+"，会议时间："+v);
        });
        System.out.println("=====================================");

        //参数信息
        System.out.print("当前参数信息");
        BasicSetting  parameter =new BasicSetting();
        parameter.printInfo();
        Scanner sc = new Scanner(System.in);
        System.out.println("是否修改参数(Y/N)：");
        String str=sc.nextLine();
        if("Y".equals(str)){
            parameter.setting();
        }

        //构建会议安排

    }
}
