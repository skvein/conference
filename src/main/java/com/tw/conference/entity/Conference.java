package com.tw.conference.entity;

/**
 * @Authir : skevin
 * @Description: 会议对象
 * @Date : create in 16:27 2018/12/5
 * @Mail :shenkai_cd@keruyun.com
 */
public class Conference {
    /**
     * 会议名称
     */
    private String conferenceName;
    /**
     * 会议时长
     */
    private int duration;


    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
