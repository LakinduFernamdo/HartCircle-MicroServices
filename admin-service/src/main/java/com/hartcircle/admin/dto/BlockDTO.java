package com.hartcircle.admin.dto;

import java.sql.Date;
import java.sql.Time;

public class BlockDTO {

    private String userNic;

    private Date date;
    private Time time;

    public String getUserNic() {
        return userNic;
    }

    public void setUserNic(String userNic) {
        this.userNic = userNic;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
