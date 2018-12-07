package com.tw.conference.entity;

/**
 * @Authir : skevin
 * @Description:
 * @Date : create in 15:02 2018/12/6
 * @Mail :shenkai_cd@keruyun.com
 */
public class TrackDetails{
    /**
     * 时间
     */
    private String time;

    /**
     * 会议信息
     */
    private Conference conference;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
}