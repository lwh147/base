package com.lwh147.algorithm;

//import java.util.LinkedList;

import com.lwh147.algorithm.util.QNode;

import java.util.PriorityQueue;
import java.util.Queue;

public class BranchAndBound_01 {
    private double[] w;        //n个物品的重量
    private double[] v;        //n个物品的价值
    private double c;        //背包容量
    private int[] idMap;    //排序后的物品编号与排序前的物品编号对照数组
    private double cw;        //当前背包剩余容量
    private double cv;        //当前背包里的物品价值
    private double bestv;    //当前最大价值

    public BranchAndBound_01(double[] w, double[] v, double c) {
        this.w = w;
        this.v = v;
        this.c = c;
        idMap = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            idMap[i] = i;            //初始化编号对应情况，此时还未排序
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
        BranchAndBound_01 tb = new BranchAndBound_01(w, v, c);
        tb.start();
        double[] w1 = {3, 5, 2, 1};
        double[] v1 = {9, 10, 7, 4};
        double c1 = 7;
        System.out.println("\n测试用例2：-------------");
        BranchAndBound_01 tb1 = new BranchAndBound_01(w1, v1, c1);
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

    //基于队列的分支限界法
	/*
	private QNode Q_BB_01(){
		//队列
		Queue<QNode> que = new LinkedList<QNode>();
		QNode nowNode = null,		//当前访问节点
			  retNode = null;		//当前最优节点
		int i=0;					//层次
		while(true){
			//到达最后一层，寻找最优解节点
			if (i==w.length && nowNode.getCv()>=bestv){
				retNode = nowNode;
			}
			//到达最后一层表示所有情况已经生成，不必对物品再进行选择与否的判断
			if (i<w.length){
				//能装下则装
				if(w[i]<=cw){
					bestv = ( (cv+v[i])>bestv ) ? (cv+v[i]) : bestv;
					que.offer(new QNode(cw-w[i], cv+v[i], true, i+1, nowNode));
				}
				//装不下，判断上界，注意=的含义
				if(bound(i+1)>=bestv){
					que.offer(new QNode(cw, cv, false, i+1, nowNode));
				}
			}
			nowNode = que.poll();
			if (nowNode==null){
				break;
			}
			cw = nowNode.getCw();
			cv = nowNode.getCv();
			i = nowNode.getLevel();
		}
		return retNode;
	}
	*/
    //基于优先队列的分支限界法
    private QNode PQ_BB_01() {
        //优先队列
        Queue<QNode> que = new PriorityQueue<QNode>();
        QNode nowNode = null,        //当前访问节点
                retNode = null;        //当前最优节点
        int i = 0;                    //层次
        while (true) {
            //到达最后一层，寻找最优解节点
            if (i == w.length && nowNode.getCv() >= bestv) {
                retNode = nowNode;
                break;
            }
            //到达最后一层表示所有情况已经生成，不必对物品再进行选择与否的判断
            if (i < w.length) {
                //能装下则装
                if (w[i] <= cw) {
                    bestv = Math.max((cv + v[i]), bestv);
                    que.offer(new QNode(cw - w[i], cv + v[i], true, i + 1, nowNode));
                }
                //装不下，判断上界，注意=的含义
                if (bound(i + 1) >= bestv) {
                    que.offer(new QNode(cw, cv, false, i + 1, nowNode));
                }
            }
            nowNode = que.poll();
            if (nowNode == null) {
                break;
            }
            cw = nowNode.getCw();
            cv = nowNode.getCv();
            i = nowNode.getLevel();
        }
        return retNode;
    }

    //计算上界
    private double bound(int i) {
        double tempw = cw, tempv = cv;    //记录当前剩余背包容量和当前装入物品价值
        //首先装入所有可以装入的物品
        while (i < w.length && w[i] <= tempw) {
            tempw = tempw - w[i];
            tempv = tempv + v[i];
            i++;
        }
        //其次按不能装入的物品中单位价值最大的物品将背包剩余容量装满,此时tempv是该情况下的一个上界，虽然是不满足题目条件的，但是可以保证是上界
        if (i < w.length)
            tempv = tempv + v[i] / w[i] * tempw;
        return tempv;
    }

    //算法入口
    public void start() {
        selectSort();    //首先按照物品单位价值进行非升序排序
        System.out.println("排序后的物品信息如下");
        System.out.print("序号：");
        for (int i = 0; i < w.length; i++) {
            System.out.print((idMap[i] + 1) + " ");
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
        //QNode lastNode = Q_BB_01();
        QNode lastNode = PQ_BB_01();
        System.out.println("最大价值为：" + lastNode.getCv());
        StringBuilder result = new StringBuilder();
        while (lastNode != null) {
            if (lastNode.isLeft())
                result.append(lastNode.getLevel()).append(" ");
            lastNode = lastNode.getParent();
        }
        System.out.println("选择的物品为：" + result);
    }
}

