package com.lwh147.algorithm;

public class TraceBack_01 {
    private double[] w;        //n个物品的重量
    private double[] v;        //n个物品的价值
    private double c;        //背包容量
    private int[] idMap;        //排序后的物品编号与排序前的物品编号对照数组
    private double cw;        //当前背包剩余容量
    private double cv;        //当前背包里的物品价值
    private double bestv;    //当前最大价值
    private boolean[] choice;    //记录回溯过程做出的选择

    public TraceBack_01(double[] w, double[] v, double c) {
        this.w = w;
        this.v = v;
        this.c = c;
        idMap = new int[w.length];
        choice = new boolean[w.length];
        for (int i = 0; i < w.length; i++) {
            idMap[i] = i;            //初始化编号对应情况，此时还未排序
            choice[i] = false;        //初始化选择情况
        }
        cw = this.c;
        cv = 0;
        bestv = 0;
    }

    public static void main(String[] args) {
        System.out.println("测试用例1：------------");
        double[] w = {2, 3, 4, 5};
        double[] v = {3, 4, 5, 6};
        double c = 8;
        TraceBack_01 tb = new TraceBack_01(w, v, c);
        tb.start();
        double[] w1 = {3, 5, 2, 1};
        double[] v1 = {9, 10, 7, 4};
        double c1 = 7;
        System.out.println("\n测试用例2：-------------");
        TraceBack_01 tb1 = new TraceBack_01(w1, v1, c1);
        tb1.start();
    }

    //将物品按其单位价值非升序排序
    private void selectSort() {
        double[] unitValue = new double[w.length];    //先计算每个物品对应的单位价值
        for (int i = 0; i < w.length; i++) {
            unitValue[i] = v[i] / w[i];
        }
        for (int i = 0; i < unitValue.length; i++) {
            double bestUV = unitValue[i];    //记录当前最大单位价值
            int index = i;                    //记录当前最大单位价值对应物品下标
            for (int j = i + 1; j < unitValue.length; j++) {
                if (unitValue[j] > bestUV) {    //说明出现比当前最大单位价值大的物品
                    index = j;                //更新最大单位价值物品的下表
                    bestUV = unitValue[j];    //更新最大单位价值
                }
            }
            if (index != i) {                    //判断，当前子序列首个元素不是最大时，进行交换
                swap(index, i);
                double temp = unitValue[index];
                unitValue[index] = unitValue[i];
                unitValue[i] = temp;
            }
            //首个元素最大时不用交换
        }
    }

    //交换方法
    private void swap(int i, int j) {
        double tempd = w[i];
        w[i] = w[j];    //对物品i，j的重量进行交换
        w[j] = tempd;
        tempd = v[i];
        v[i] = v[j];    //对物品i，j的价值进行交换
        v[j] = tempd;
        int tempi = idMap[i];    //更新物品编号对应情况
        idMap[i] = idMap[j];
        idMap[j] = tempi;
    }

    //回溯方法
    private void traceBack(int level) {
        if (level >= w.length) {    //超出物品个数，停止回溯
            bestv = cv;            //记录该回溯路径下的最大价值
            return;
        }
        if (w[level] <= cw) {        //如果背包容量足够，就放入该物品，进入左子树
            cw = cw - w[level];    //更新当前背包剩余容量
            cv = cv + v[level];    //更新当前背包价值
            choice[level] = true;    //更新选择
            traceBack(level + 1);        //深入搜索下一层
            //此时准备进入右子树，初始化相关记录数据
            cw = cw + w[level];    //恢复当前背包剩余容量
            cv = cv - v[level];    //恢复当前背包价值
        }
        //判断不放当前物品时的价值上界
        if (bound(level + 1) > bestv) {    //如果大于当前最大价值，则进行深入搜索
            choice[level] = false;    //不选择当前物品
            traceBack(level + 1);
        }
    }

    //计算上界
    private double bound(int level) {
        double tempw = cw, tempv = cv;    //记录当前剩余背包容量和当前装入物品价值
        //首先装入所有可以装入的物品
        while (level < w.length && w[level] <= tempw) {
            tempw = tempw - w[level];
            tempv = tempv + v[level];
            level++;
        }
        //其次按不能装入的物品中单位价值最大的物品将背包剩余容量装满,此时tempv是该情况下的一个上界，虽然是不满足题目条件的，但是可以保证是上界
        if (level < w.length)
            tempv = tempv + v[level] / w[level] * tempw;
        return tempv;
    }

    //算法入口
    public void start() {
        selectSort();    //首先按照物品单位价值进行非升序排序
        System.out.println("排序后的物品信息如下");
        System.out.print("序号：");
        for (int i = 0; i < w.length; i++) {
            System.out.print(idMap[i] + " ");
        }
        System.out.print("\n重量：");
        for (double value : w) {
            System.out.print(value + " ");
        }
        System.out.print("\n价值：");
        for (int i = 0; i < w.length; i++) {
            System.out.print(v[i] + " ");
        }
        System.out.println();
        traceBack(0);
        System.out.println("最大价值为：" + bestv);
        System.out.println("选择商品序号为：");
        for (int i = 0; i < w.length; i++) {
            if (choice[i])
                System.out.print(idMap[i] + " ");
        }
    }
}
