package com.tw.conference.config;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Authir : skevin
 * @Description:
 * @Date : create in 17:22 2018/12/5
 * @Mail :shenkai_cd@keruyun.com
 */
public class BasicSetting {
    private final static Pattern pattern = Pattern.compile("[0-9]*");
    private final static int MORNING_MAX=12;
    private final static int AFTERNOON_MAX=5;

    private int conferenceDay;

    private int lightning=5;

    private int morningStartHours=9;

    private int morningEndHours=12;

    private int afternoonStartHours=1;

    private int afternoonEndHours=5;

    public int getConferenceDay() {
        return conferenceDay;
    }

    public void setConferenceDay(int conferenceDay) {
        this.conferenceDay = conferenceDay;
    }

    public int getLightning() {
        return lightning;
    }

    public void setLightning(int lightning) {
        this.lightning = lightning;
    }

    public int getMorningStartHours() {
        return morningStartHours;
    }

    public void setMorningStartHours(int morningStartHours) {
        this.morningStartHours = morningStartHours;
    }

    public int getMorningEndHours() {
        return morningEndHours;
    }

    public void setMorningEndHours(int morningEndHours) {
        this.morningEndHours = morningEndHours;
    }

    public int getAfternoonStartHours() {
        return afternoonStartHours;
    }

    public void setAfternoonStartHours(int afternoonStartHours) {
        this.afternoonStartHours = afternoonStartHours;
    }

    public int getAfternoonEndHours() {
        return afternoonEndHours;
    }

    public void setAfternoonEndHours(int afternoonEndHours) {
        this.afternoonEndHours = afternoonEndHours;
    }

    /**
     * 参数设置
     */
    public void setting(){
        Scanner sc = new Scanner(System.in);
        //设置会议天数
        System.out.println("请输入会议安排天数：");
        conferenceDay=settingInt(sc,"会议天数需是正整数");

        System.out.println("请输入时间单位：");
        lightning = settingInt(sc,"时间单位是正整数");

        System.out.println("早上开始时间：");
        morningStartHours = settingMorningStartHours(sc);
        System.out.println("早上结束时间：");
        morningEndHours = settingMorningEndHours(sc);

        System.out.println("下午开始时间：");
        afternoonStartHours = settingAfternoonStartHours(sc);
        System.out.println("下午结束时间：");
        afternoonEndHours = settingAfternoonEndHours(sc);

    }

    /**
     * 设置
     * @param sc
     * @return
     */
    private int settingInt(Scanner sc,String errStr){
        String day =sc.nextLine();
        if(vlidateIsNumber(day)){
            return Integer.valueOf(day);
        }else{
            System.out.println(errStr+"，请重新输入：");
            return settingInt(sc,errStr);
        }
    }

    /**
     * 设置早上开始时间
     * @param sc
     * @return
     */
    private int settingMorningStartHours(Scanner sc){
        String str =sc.nextLine();
        if(!vlidateIsNumber(str)){
            System.out.println("时间必须是正整数，请重新输入：");
            return settingMorningStartHours(sc);
        }
        int hour=Integer.valueOf(str);
        if(MORNING_MAX < hour){
            System.out.println("早上开始需小于12点，请重新输入：");
            return settingMorningStartHours(sc);
        }
        return hour;
    }

    /**
     * 设置早上结束时间
     * @param sc
     * @return
     */
    private int settingMorningEndHours(Scanner sc){
        String str =sc.nextLine();
        if(!vlidateIsNumber(str)){
            System.out.println("时间必须是正整数，请重新输入：");
            return settingMorningEndHours(sc);
        }
        int hour=Integer.valueOf(str);
        if(MORNING_MAX < hour || hour<morningStartHours){
            System.out.println("早上结束需小于12点并大于早上开始时间，请重新输入：");
            return settingMorningEndHours(sc);
        }
        return hour;
    }

    /**
     * 设置下午开始时间
     * @param sc
     * @return
     */
    private int settingAfternoonStartHours(Scanner sc){
        String str =sc.nextLine();
        if(!vlidateIsNumber(str)){
            System.out.println("时间必须是正整数，请重新输入：");
            return settingAfternoonStartHours(sc);
        }
        int hour=Integer.valueOf(str);
        if(AFTERNOON_MAX < hour){
            System.out.println("下午开始需小于5点，请重新输入：");
            return settingAfternoonStartHours(sc);
        }
        return hour;
    }

    /**
     * 设置下午结束时间
     * @param sc
     * @return
     */
    private int settingAfternoonEndHours(Scanner sc){
        String str =sc.nextLine();
        if(!vlidateIsNumber(str)){
            System.out.println("时间必须是正整数，请重新输入：");
            return settingAfternoonEndHours(sc);
        }
        int hour=Integer.valueOf(str);
        if(AFTERNOON_MAX < hour || hour<afternoonStartHours){
            System.out.println("下午结束需小于5点并大于下午开始时间，请重新输入：");
            return settingAfternoonEndHours(sc);
        }
        return hour;
    }

    /**
     * 使用正则表达式判断是否是正数
     * @param str
     * @return
     */
    private Boolean vlidateIsNumber(String str){
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public void printInfo(){
        System.out.println("会议安排天数："+conferenceDay);
        System.out.println("最小会议时间："+lightning);
        System.out.println("早上开始时间："+morningStartHours);
        System.out.println("早上结束时间："+morningEndHours);
        System.out.println("下午开始时间："+afternoonStartHours);
        System.out.println("下午结束时间："+afternoonEndHours);
        System.out.println("=====================================");
    }
}
