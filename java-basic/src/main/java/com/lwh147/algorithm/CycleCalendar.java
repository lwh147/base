package com.lwh147.algorithm;

public class CycleCalendar {
    private int n;                        //比赛人数
    private int[][] result;            //比赛日程结果数组

    //构造函数
    public CycleCalendar(int n) {
        this.n = n;
        result = new int[n][n];
        for (int i = 0; i < n; i++)            //对结果数组进行初始化
            result[i][0] = i + 1;
    }

    public static void main(String[] args) {
        //比赛选手数
        int n = 16;//8
        //创建解决问题的对象
        CycleCalendar tb = new CycleCalendar(n);
        //执行生成循环赛程表的方法并输出结果
        tb.generateTable();
    }

    //生成日程表入口
    public void generateTable() {
        divide(n);            //执行算法
        showTable();    //输出结果
    }

    //显示循环日程表
    private void showTable() {
        System.out.println("循环比赛日程表：");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result[i][j] + " ");            //输出行
            }
            System.out.println("");        //换行
        }
    }

    //分割算法
    private void divide(int n) {
        if (n == 1)
            return;            //选手只有一人，停止分割，不进行复制
        else
            divide(n / 2);        //选手不止一人，继续分割
        copy(n);    //分割完成，进行复制
    }

    //复制操作
    private void copy(int n) {
        int m = this.n / n;                    //得到复制次数
        for (int i = 0; i < m; i++) {            //控制某一规模下需要进行区域复制的次数
            for (int j = 0; j < n / 2; j++) {                //控制复制的行数
                for (int k = 0; k < n / 2; k++) {            //控制复制的列数
                    result[i * n + j + n / 2][k + n / 2] = result[i * n + j][k];        //左上复制到右下
                    result[i * n + j][k + n / 2] = result[i * n + j + n / 2][k];        //右下复制到左上
                }
            }
        }
    }
}
