package com.lwh147.algorithm;

public class DynamicPlanning_LCS {
    private char[] x;    //第一个序列
    private char[] y;    //第二个序列
    private int[][] b;    //存放x[i]和y[j]的最长公共子序列来自哪两个子序列
    private int[][] c;    //存放x[i]和y[j]的最长公共子序列长度

    public DynamicPlanning_LCS(char[] x, char[] y) {
        this.x = x;
        this.y = y;
        b = new int[x.length + 1][y.length + 1];    //对一行一列
        c = new int[x.length + 1][y.length + 1];
        for (int i = 0; i < x.length + 1; i++) {        //对第一行和第一列进行清零
            c[i][0] = 0;
            b[i][0] = 0;
        }
        for (int i = 0; i < y.length + 1; i++) {
            c[0][i] = 0;
            b[0][i] = 0;
        }
    }

    public static void main(String[] args) {
        char[] x = {'A', 'B', 'D', 'C', 'D', 'B', 'D'};
        char[] y = {'B', 'D', 'C', 'A', 'B', 'A'};
        DynamicPlanning_LCS dp_lcs = new DynamicPlanning_LCS(x, y);
        System.out.println("最长公共子序列为：");
        dp_lcs.start();
    }

    public void start() {    //程序入口
        lcsLength();
        lcs(x.length, y.length);
    }

    private void lcsLength() {
        int m = x.length;
        int n = y.length;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i - 1] == y[j - 1]) {                        //如果两元素相等，说明当前最长公共子序列长度是该元素之前的最长公共子序列长度加1
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;                    //表示x[i]和y[j]的最长公共子序列是x[i-1]和y[j-1]的最长公共子序列加上x[i]
                } else if (c[i][j - 1] >= c[i - 1][j]) {    //说明第x[i]和y[j-1]的最长公共子序列长度大于等于第x[i-1]和y[j]的最长公共子序列
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 2;                    //表示x[i]和y[j]的最长公共子序列等于x[i]和y[j-1]的最长公共子序列
                } else {                                //说明第x[i]和y[j-1]的最长公共子序列长度小于第x[i-1]和y[j]的最长公共子序列
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 3;                    //表示x[i]和y[j]的最长公共子序列是x[i-1]和y[j]的最长公共子序列
                }
            }
        }
    }

    private void lcs(int i, int j) {
        if (i == 0 || j == 0) {            //递归终止条件：已经比较结束
            return;
        } else if (b[i][j] == 1) {        //说明最长公共子序列必然包含x[i];
            lcs(i - 1, j - 1);            //继续查找
            System.out.print(x[i - 1] + " ");        //输出
        } else if (b[i][j] == 2) {        //说明最长公共子序列在x[i]和y[j-1]中
            lcs(i, j - 1);
        } else {                        //说明最长公共子序列在x[i-1]和y[j]中
            lcs(i - 1, j);
        }
    }
}
