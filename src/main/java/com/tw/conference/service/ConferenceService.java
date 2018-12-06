package com.tw.conference.service;

import com.tw.conference.config.BasicSetting;
import com.tw.conference.entity.Conference;
import com.tw.conference.entity.Track;

import java.util.List;

/**
 * @Authir : skevin
 * @Description:
 * @Date : create in 13:29 2018/12/6
 * @Mail :shenkai_cd@keruyun.com
 */
public interface ConferenceService {

     List<Conference> initializeConference();

    List<Track> scheduleConference(List<Conference> conferences,BasicSetting parameter);
}
