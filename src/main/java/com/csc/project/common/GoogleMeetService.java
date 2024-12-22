package com.csc.project.common;

import org.springframework.stereotype.Service;

@Service
public class GoogleMeetService {

    public String createMeetLink() {
        String meetLink = "https://meet.google.com/new";
        return meetLink;
    }
}
