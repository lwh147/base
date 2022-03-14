package com.lwh147.algorithm;

public class GreedySelector {
    private int[] s;                    //存储活动开始时间的数组
    private int[] f;                        //存储活动结束事件的数组
    private int num;                    //活动总数
    private boolean[] result;    //结果数组

    public GreedySelector(int[] s, int[] f, int num) {
        this.s = s;
        this.f = f;
        this.num = num;
        this.result = new boolean[num];        //申请结果数组空间并进行初始化
        for (int i = 0; i < num; i++) {
            result[i] = false;
        }
    }

    public static void main(String[] args) {
        //总活动数
        int num = 11;
        //活动开始结束时间
        int[] s = {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] f = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        //创建解决问题的对象
        GreedySelector gs = new GreedySelector(s, f, num);
        //执行贪心算法并输出结果
        gs.getResult();
    }

    //执行贪心算法
    public void getResult() {
        nonDecreaseSort();    //对活动按结束时间进行非降序排序
        result[0] = true;            //第一个活动为最早结束的
        int last = 0;                    //记录当前子集合中最后一个活动
        for (int i = 1; i < num; i++) {        //遍历
            if (s[i] >= f[last]) {
                result[i] = true;        //若某活动开始时间大于等于当前子集合中最后一个活动的结束时间，则将其放入子集合
                last = i;                //更改当前子集合中的最后一个活动
            }
        }
        printResult();            //打印结果
    }

    //将会议按非降序排序
    private void nonDecreaseSort() {
        for (int i = 0; i < num; i++) {
            int mini = i, min = f[i];
            for (int j = i + 1; j < num; j++) {
                if (f[j] < min) {
                    mini = j;
                    min = f[j];
                }
            }
            if (mini != i) {
                f[mini] = f[i];
                f[i] = min;
            }
        }
        printSorted();
    }

    //打印排序后的活动顺序
    private void printSorted() {
        System.out.println("排序后的活动集合为：");
        for (int i = 0; i < num; i++) {
            System.out.println("活动" + (i + 1) + ":" + s[i] + "-" + f[i]);
        }
    }

    //打印结果
    private void printResult() {
        System.out.println("最大相容活动子集合为：");
        for (int i = 0; i < num; i++) {
            if (result[i] == true) {
                if (i != num - 1)
                    System.out.print("活动" + (i + 1) + "→");
                else
                    System.out.println("活动" + (i + 1) + "\n");
            }
        }
    }
}
