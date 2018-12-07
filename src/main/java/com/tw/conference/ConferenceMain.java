package com.tw.conference;

import com.tw.conference.config.BasicSetting;
import com.tw.conference.entity.Conference;
import com.tw.conference.entity.Track;
import com.tw.conference.service.impl.ConferenceServiceImpl;

import java.util.List;

/**
 * @Authir : skevin
 * @Description:
 * @Date : create in 16:03 2018/12/5
 * @Mail :shenkai_cd@keruyun.com
 */
public class ConferenceMain {

    public static void main(String[] args) {
        //参数信息
        BasicSetting parameter =new BasicSetting();
        /*System.out.print("是否修改参数(Y/N)：");
        Scanner sc = new Scanner(System.in);
        String flag=sc.nextLine();
        if("Y".equals(flag)){
            parameter.setting();
        }*/
        System.out.println("当前参数信息");
        parameter.printInfo();
        System.out.println("=====================================");

        //获取会议信息
        System.out.println("当前会议信息");
        ConferenceServiceImpl conferenceService =new ConferenceServiceImpl();
        List<Conference> conferences =conferenceService.initializeConference();
        for(Conference conference:conferences){
            if(conference.getDuration()==0){
                conference.setDuration(parameter.getLightning());
            }
            System.out.println("会议名称："+conference.getConferenceName()+" ,会议时长："+ conference.getDuration());
        }
        System.out.println("=====================================");

        //构建会议安排
       List<Track> tracks= conferenceService.scheduleConference(conferences,parameter);
       for(Track track:tracks){
            System.out.println(track.getTrackName());
            track.getTrackDetails().forEach(detail->{
                if(detail.getConference().getDuration()!=0){
                    System.out.println(detail.getTime()+" "+detail.getConference().getConferenceName()+" "+detail.getConference().getDuration()+"min");
                }else{
                    System.out.println(detail.getTime()+" "+detail.getConference().getConferenceName());
                }
            });
       }
    }

}
