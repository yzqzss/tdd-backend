package com.bunnyxt.tdd.model.video.record.rank;

import com.bunnyxt.tdd.model.fragment.VideoMemberFragment;

public class WeeklyArchiveEx extends WeeklyArchive {

    private VideoMemberFragment video;

    public VideoMemberFragment getVideo() {
        return video;
    }

    public void setVideo(VideoMemberFragment video) {
        this.video = video;
    }
}
