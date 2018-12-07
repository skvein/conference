package com.tw.conference.service.impl;

import com.tw.conference.config.BasicSetting;
import com.tw.conference.entity.Conference;
import com.tw.conference.entity.Track;
import com.tw.conference.entity.TrackDetails;
import com.tw.conference.service.ConferenceService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * @Authir : skevin
 * @Description: 会议Service
 * @Date : create in 13:29 2018/12/6
 * @Mail :shenkai_cd@keruyun.com
 */
public class ConferenceServiceImpl implements ConferenceService {
    private final static String LIGHTNING="lightning";
    private final static int MINLENGTH=3;
    private final static int MINUTE=60;
    private final static String AM="AM";
    private final static String PM="PM";
    private final static String LUNCH="Lunch";
    private final static String TRACK="Track";
    private final static String NETWORK="Networking Event";
    /**
     * 初始化会议信息
     * @return
     */
    @Override
    public List<Conference> initializeConference() {
        List<Conference> result =new ArrayList<>();
        File file = new File("src/main/resources/talks.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                Conference conference=new Conference();
                int numTime = 0;
                int lastBlank = line.lastIndexOf(" ");
                String title = line.substring(0, lastBlank);
                String time = line.substring(lastBlank + 1);
                if (!LIGHTNING.equals(time)) {
                    numTime = Integer.parseInt(time.substring(0, time.length() - MINLENGTH));
                }
                conference.setConferenceName(title);
                conference.setDuration(numTime);
                result.add(conference);
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
        return result;
    }

    /**
     * 安排会议
     * @param conferences
     * @param parameter
     * @return
     */
    @Override
    public List<Track> scheduleConference(List<Conference> conferences,BasicSetting parameter) {
        List<Track> result =new ArrayList<>();
        //计算会议总时长
        int allMinute=0;
        for(Conference conference:conferences){
            allMinute=allMinute+conference.getDuration();
        }
        //获取早上允许的会议时间
        int morningMinute=(parameter.getMorningEndHours()-parameter.getMorningStartHours())*MINUTE;
        //获取下午允许的会议时间
        int afernoonMinute=(parameter.getAfternoonEndHours()-parameter.getAfternoonStartHours())*MINUTE;

        //计算总天数
        int totalDay=(int)Math.ceil( (double) allMinute/(morningMinute+afernoonMinute));
        //构建会议安排
        for(int i=1;i<=totalDay;i++){
            Track track=buildTrack(conferences,morningMinute,afernoonMinute,parameter);
            track.setTrackName(TRACK+" "+i);
            result.add(track);
        }
        return result;
    }

    /**
     * 构建会议安排
     * @param conferences
     * @param morningMinute
     * @param afernoonMinute
     * @param parameter
     * @return
     */
    private Track buildTrack(List<Conference> conferences,int morningMinute,int afernoonMinute,BasicSetting parameter){
        Track track=new Track();
        List<TrackDetails> trackDetails=new ArrayList<>();
        //上午会议
        buildTrack(trackDetails,conferences,morningMinute,parameter.getMorningStartHours(),AM);
        //中午lunch
        TrackDetails lunchdetail=new TrackDetails();
        lunchdetail.setTime(String.valueOf(parameter.getMorningEndHours())+AM);
        Conference lunch=new Conference();
        lunch.setConferenceName(LUNCH);
        lunchdetail.setConference(lunch);
        trackDetails.add(lunchdetail);
        //下午
        buildTrack(trackDetails,conferences,afernoonMinute,parameter.getAfternoonStartHours(),PM);
        //Networking Event
        TrackDetails netWrokdetail=new TrackDetails();
        netWrokdetail.setTime(String.valueOf(parameter.getAfternoonEndHours())+PM);
        Conference networking=new Conference();
        networking.setConferenceName(NETWORK);
        netWrokdetail.setConference(networking);
        trackDetails.add(netWrokdetail);
        track.setTrackDetails(trackDetails);
        return track;
    }

    /**
     * 构建会议
     * @param conferences
     * @param leisureMinute
     * @param startHour
     * @return
     */
    private List<TrackDetails> buildTrack(List<TrackDetails> trackDetails,List<Conference> conferences,int leisureMinute,int startHour,String str){
        Calendar morningCalendar =Calendar.getInstance();
        morningCalendar.set(0,0,0,startHour,0);
        List<Conference> datas=getConference(conferences,new ArrayList<>(),leisureMinute,0).get(0);
        conferences.remove(datas);
        for(Conference conference:datas){
            TrackDetails details=new TrackDetails();
            details.setTime(String.valueOf(morningCalendar.get(Calendar.HOUR))+":"+String.valueOf(morningCalendar.get(Calendar.MINUTE))+str);
            details.setConference(conference);
            trackDetails.add(details);
            morningCalendar.add(Calendar.MINUTE,conference.getDuration());
            conferences.remove(conference);
        }
        return trackDetails;
    }


    /**
     * 更具时间片安排会议
     * @param conferences
     * @param list
     * @return
     */
    public static List<List<Conference>> getConference(List<Conference> conferences,List<Conference> list , int m, int i) {
        List<List<Conference>> resList = new ArrayList<>();
        while (i < conferences.size()) {
            list.add(conferences.get(i));
            if (getsum(list) == m) {
                resList.add(list);
                return resList;
            }
            i++;
            getConference(conferences,list, m, i);
            list.remove(list.size() - 1);
        }
        return resList;
    }

    /**
     * 求和
     * @param list
     * @return
     */
    private static int getsum(List<Conference> list) {
        int sum = 0;
        Iterator<Conference> iterator = list.iterator();
        while(iterator.hasNext()){
            sum += iterator.next().getDuration();
        }
        return sum;
    }
}
