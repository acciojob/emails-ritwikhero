package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.*;
import java.util.Collections;

public class Workspace extends Gmail{

    private List<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId);
        inboxCapacity = Integer.MAX_VALUE;
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int maxMeetings = 0;
        for (Meeting meeting1 : calendar) {
            int count = 1;
            for (Meeting meeting2 : calendar) {
                if (meeting1 != meeting2 && !overlap(meeting1, meeting2)) {
                    count++;
                }
            }
            maxMeetings = Math.max(maxMeetings, count);
        }
        return maxMeetings;

    }

    private boolean overlap(Meeting meeting1, Meeting meeting2) {
        LocalTime start1 = meeting1.getStartTime();
        LocalTime end1 = meeting1.getEndTime();
        LocalTime start2 = meeting2.getStartTime();
        LocalTime end2 = meeting2.getEndTime();
        return (start1.isBefore(end2) && end1.isAfter(start2));
    }
}
