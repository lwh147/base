package com.lwh147;

import java.util.Date;

/**
 * 模拟房间预定记录
 *
 * @author lwh
 * @date 2021/11/30 10:23
 **/
public class RoomOrder {
    private Long id;
    private Date start;
    private Date end;
    private int day;

    public RoomOrder(Long id, Date start, Date end, int day) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
