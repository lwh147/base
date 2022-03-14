package com.lwh147;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间区段封装类
 *
 * @author lwh
 * @date 2021/11/30 10:24
 **/
public class Duration {
    public static final int orderNum = 5;
    public static final int dayNum = 4;
    public static final int monthNum = 6;
    public static final int resultNum = (int) (monthNum * 31 / 0.75);
    public static final int ableNum = 5;
    private Date start;
    private Date end;
    private int day;

    public Duration(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Duration(Date start, Date end, int day) {
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public static void main(String[] args) {
        RoomOrder r1 = new RoomOrder(1L, new Date(1638288000000L), new Date(1638460800000L), 3);
        RoomOrder r2 = new RoomOrder(2L, new Date(1638374400000L), new Date(1638633600000L), 4);
        RoomOrder r3 = new RoomOrder(3L, new Date(1638547200000L), new Date(1638979200000L), 6);
        RoomOrder r4 = new RoomOrder(2L, new Date(1638806400000L), new Date(1639152000000L), 5);
        RoomOrder r5 = new RoomOrder(1L, new Date(1638892800000L), new Date(1639065600000L), 3);

        List<RoomOrder> roomOrderList1 = new ArrayList<>();
        roomOrderList1.add(r1);
        roomOrderList1.add(r2);
        // for (int i = 0; i < orderNum; i++) {
        //     Calendar date1 = Calendar.getInstance();
        //     date1.add(Calendar.DAY_OF_MONTH, i * dayNum);
        //     clearTime(date1);
        //     Calendar date2 = Calendar.getInstance();
        //     date2.add(Calendar.DAY_OF_MONTH, i * dayNum + dayNum - 1);
        //     clearTime(date2);
        //     roomOrderList1.add(new RoomOrder(1L, date1.getTime(), date2.getTime(), dayNum));
        // }
        List<RoomOrder> roomOrderList2 = new ArrayList<>();
        roomOrderList2.add(r3);
        roomOrderList2.add(r4);
        // for (int i = 0; i < orderNum; i++) {
        //     Calendar date1 = Calendar.getInstance();
        //     date1.add(Calendar.DAY_OF_MONTH, i * dayNum);
        //     clearTime(date1);
        //     Calendar date2 = Calendar.getInstance();
        //     date2.add(Calendar.DAY_OF_MONTH, i * dayNum + dayNum - 1);
        //     clearTime(date2);
        //     roomOrderList1.add(new RoomOrder(2L, date1.getTime(), date2.getTime(), dayNum));
        // }
        List<RoomOrder> roomOrderList3 = new ArrayList<>();
        roomOrderList2.add(r5);
        // for (int i = 0; i < orderNum; i++) {
        //     Calendar date1 = Calendar.getInstance();
        //     date1.add(Calendar.DAY_OF_MONTH, i * dayNum);
        //     clearTime(date1);
        //     Calendar date2 = Calendar.getInstance();
        //     date2.add(Calendar.DAY_OF_MONTH, i * dayNum + dayNum - 1);
        //     clearTime(date2);
        //     roomOrderList1.add(new RoomOrder(3L, date1.getTime(), date2.getTime(), dayNum));
        // }

        HouseOrder houseOrder1 = new HouseOrder(11L, roomOrderList1);
        HouseOrder houseOrder2 = new HouseOrder(22L, roomOrderList2);
        HouseOrder houseOrder3 = new HouseOrder(33L, roomOrderList3);

        // 三个订单，分别对应2、2、1条房间的预约记录
        List<HouseOrder> houseOrderList = new ArrayList<>();
        houseOrderList.add(houseOrder1);
        houseOrderList.add(houseOrder2);
        houseOrderList.add(houseOrder3);

        Map<Long, List<Duration>> roomDuration = new HashMap<>();
        for (HouseOrder ho : houseOrderList) {
            for (RoomOrder ro : ho.getRoomOrderList()) {
                List<Duration> durationList = roomDuration.get(ro.getId());
                if (Objects.nonNull(durationList)) {
                    durationList.add(new Duration(ro.getStart(), ro.getEnd(), ro.getDay()));
                } else {
                    durationList = new ArrayList<>();
                    durationList.add(new Duration(ro.getStart(), ro.getEnd(), ro.getDay()));
                    roomDuration.put(ro.getId(), durationList);
                }
            }
        }

        long time1;
        long time2;

        time1 = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar end = Calendar.getInstance();
        end.add(Calendar.MONTH, monthNum);
        end.set(Calendar.DAY_OF_MONTH, 1);
        end.add(Calendar.DAY_OF_MONTH, -1);
        clearTime(end);
        Calendar point = Calendar.getInstance();
        point.set(Calendar.DAY_OF_MONTH, 1);
        clearTime(point);
        Map<String, Integer> result = new LinkedHashMap<>(resultNum);
        while (point.getTimeInMillis() - end.getTimeInMillis() <= 0) {
            result.put(sdf.format(point.getTime()), roomCount(roomDuration, point.getTime(), ableNum));
            point.add(Calendar.DAY_OF_MONTH, 1);
        }
        time2 = System.currentTimeMillis();
        System.out.println("方法1耗时：" + (time2 - time1));
        System.out.println(result);

        // time1 = System.currentTimeMillis();
        // Map<String, Integer> result1 = solution2(roomDuration);
        // time2 = System.currentTimeMillis();
        // System.out.println("方法2耗时：" + (time2 - time1));
        // System.out.println(result1);
    }

    public static int roomCount(Map<Long, List<Duration>> map, Date date, int n) {
        for (Map.Entry<Long, List<Duration>> e : map.entrySet()) {
            List<Duration> durationList = e.getValue();
            for (Duration d : durationList) {
                if (d.include(date)) {
                    n--;
                    break;
                }
            }
        }
        return n;
    }

    public static void clearTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static Map<String, Integer> solution2(Map<Long, List<Duration>> roomDuration) {
        Calendar end = Calendar.getInstance();
        end.add(Calendar.MONTH, monthNum);
        end.set(Calendar.DAY_OF_MONTH, 1);
        end.add(Calendar.DAY_OF_MONTH, -1);
        clearTime(end);
        Calendar start = Calendar.getInstance();
        start.set(Calendar.DAY_OF_MONTH, 1);
        clearTime(start);
        Map<String, Integer> result = new LinkedHashMap<>(resultNum);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (start.getTimeInMillis() - end.getTimeInMillis() <= 0) {
            result.put(sdf.format(start.getTime()), ableNum);
            start.add(Calendar.DAY_OF_MONTH, 1);
        }
        for (Map.Entry<Long, List<Duration>> e : roomDuration.entrySet()) {
            for (Duration duration : e.getValue()) {
                Calendar date = Calendar.getInstance();
                date.setTime(duration.getStart());
                clearTime(date);
                int day = duration.getDay();
                while (day > 0) {
                    String date1 = sdf.format(date.getTime());
                    result.put(date1, result.get(date1) - 1);
                    date.add(Calendar.DAY_OF_MONTH, 1);
                    day--;
                }
            }
        }
        return result;
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

    @Override
    public String toString() {
        return "Duration{" +
                "start=" + start +
                ", end=" + end +
                ", day=" + day +
                '}';
    }

    /**
     * 判断给定日期是否包含在当前入离时间段内，date ∈ [start, end]?
     * <p>
     * 边界相等也视为包含
     *
     * @param date 一个日期
     * @return 是否包含
     **/
    public boolean include(Date date) {
        if (Objects.isNull(date)) {
            return false;
        }
        return date.getTime() - this.start.getTime() >= 0 &&
                date.getTime() - this.end.getTime() <= 0;
    }
}
