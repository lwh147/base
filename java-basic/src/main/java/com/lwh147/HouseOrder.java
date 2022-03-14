package com.lwh147;

import java.util.List;

/**
 * 模拟房源预定记录
 *
 * @author lwh
 * @date 2021/11/30 10:23
 **/
public class HouseOrder {
    private Long id;
    private List<RoomOrder> roomOrderList;

    public HouseOrder(Long id, List<RoomOrder> roomOrderList) {
        this.id = id;
        this.roomOrderList = roomOrderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RoomOrder> getRoomOrderList() {
        return roomOrderList;
    }

    public void setRoomOrderList(List<RoomOrder> roomOrderList) {
        this.roomOrderList = roomOrderList;
    }
}
