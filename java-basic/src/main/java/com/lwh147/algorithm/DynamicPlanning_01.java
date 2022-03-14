package com.lwh147.algorithm;

public class DynamicPlanning_01 {
    private int[] w;            //存放c个物品的重量
    private int[] v;            //存放c个物品的价值
    private int c;                //物品总个数
    private int[][] resultTB;    //表

    public DynamicPlanning_01(int[] w, int[] v, int c) {
        this.w = w;
        this.v = v;
        this.c = c;
        resultTB = new int[w.length + 1][c + 1];
        for (int i = 0; i <= w.length; i++) {
            resultTB[i][0] = 0;
        }
        for (int i = 0; i <= c; i++) {
            resultTB[0][i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] w = {2, 3, 4, 5};
        int[] v = {3, 4, 5, 6};
        int c = 8;
        DynamicPlanning_01 dp_01 = new DynamicPlanning_01(w, v, c);
        System.out.println("被选择的物品如下：");
        dp_01.start();
    }

    //入口方法
    public void start() {
        knapsack();
        traceBack(w.length, c);
    }

    //填表
    private void knapsack() {
        for (int i = 1; i <= w.length; i++) {
            for (int j = 1; j <= c; j++) {
                if (j < w[i - 1]) {                //说明此时背包容量装不下第i个物品
                    resultTB[i][j] = resultTB[i - 1][j];        //当前最大价值不变
                } else if (resultTB[i - 1][j] >= (resultTB[i - 1][j - w[i - 1]] + v[i - 1])) {        //说明此时背包容量可以装下第i个物品，但是装入之后价值小于不装的价值
                    resultTB[i][j] = resultTB[i - 1][j];        //当前最大价值不变
                } else {
                    resultTB[i][j] = resultTB[i - 1][j - w[i - 1]] + v[i - 1];        //说明此时装入第i个物品后当前最大价值大于之前的最大价值，修改背包容量和当前最大价值
                }
            }
        }
    }

    //回溯求解
    private void traceBack(int i, int j) {
        if (i == 0 || j == 0)        //背包容量为0或者物品个数为0时达到递归终止条件
            return;
        if (resultTB[i][j] == resultTB[i - 1][j])    //说明没有选择第i个物品
            traceBack(i - 1, j);            //修改物品数量继续回溯
        else {
            System.out.print(i + " ");    //说明选择了第i个物品，输出
            traceBack(i - 1, j - w[i - 1]);    //修改物品数量和背包容量继续回溯
        }
    }
}
