package com.tw.conference.entity;

import java.util.List;

/**
 * @Authir : skevin
 * @Description:
 * @Date : create in 13:49 2018/12/6
 * @Mail :shenkai_cd@keruyun.com
 */
public class Track {
    private String trackName;

    /**
     * 会议明细
     */
    private List<TrackDetail> trackDetails;

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public List<TrackDetail> getTrackDetails() {
        return trackDetails;
    }

    public void setTrackDetails(List<TrackDetail> trackDetails) {
        this.trackDetails = trackDetails;
    }




}
