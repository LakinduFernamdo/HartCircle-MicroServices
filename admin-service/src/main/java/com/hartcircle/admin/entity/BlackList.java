package com.hartcircle.admin.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
public class BlackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") // Match column name
    private Integer BlockId;

    @Column(name="userNic")
    private String userNic;

    @Column(name="Blocked_BY")
    private String adminNic;

    @Column(name="Date")
    private Date date;

    @Column(name="Time")
    private Time time;

    public Integer getBlockId() {
        return BlockId;
    }

    public void setBlockId(Integer blockId) {
        BlockId = blockId;
    }

    public String getUserNic() {
        return userNic;
    }

    public void setUserNic(String userNic) {
        this.userNic = userNic;
    }

    public String getAdminNic() {
        return adminNic;
    }

    public void setAdminNic(String adminNic) {
        this.adminNic = adminNic;
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
