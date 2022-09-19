package com.intern.ambassador.data.entity;

import java.util.Arrays;
import java.util.List;

public enum EduGroup {
    KNOWLEDGE,
    CONVERSATION,
    WRITING,
    QUALITY,
    TARGETING,
    SNS,
    CHANNEL;

    private String title;
    private List<String> eduList;

//    EduGroup(String title, List<String> eduList) {
//        this.title = title;
//        this.eduList = eduList;
//    }
}
