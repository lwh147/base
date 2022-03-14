package com.lwh147.algorithm;

import java.util.*;

/**
 * @author lwh
 * @description 实现LRU缓存结构算法的类
 * 用HashMap存储节点，此时节点是无序的，而节点带有前后节点指向
 * 此时通过节点的指向记录即可了解节点的顺序
 * 类似于静态链表，存储位置不固定，但是通过节点自身的 指向记录 属性 可知节点顺序
 * @date 2020/9/11
 */
public class LRU {
    // 记录数据顺序的双向链表的头和尾
    private final Node head = new Node(-1, -1);
    private final Node tail = new Node(-1, -1);
    // 缓存采用的数据结构
    private Map<Integer, Node> cache;
    // 缓存结构大小上限
    private int MAX_SIZE;

    // 测试入口
    public static void main(String[] args) {
        int[][] operators = {
                {1, 1, 1},
                {1, 2, 2},
                {1, 3, 2},
                {2, 1},
                {1, 4, 4},
                {2, 2}
        };
        int size = 3;

        // 使用LinkedHashMap完成
        int[] result1 = LRU.LRU_Built_In_LinkedHashMap(operators, size);
        for (int i : result1)
            System.out.println(i);

        // 使用自己写的链表结构完成
        LRU lru = new LRU();

        int[] result2 = lru.MyLRU(operators, size);
        for (int i : result2)
            System.out.println(i);
    }

    /**
     * @param operators 操作数组
     * @param k         缓存大小
     * @return int[]    get操作的输出列表
     * @description 利用java内置LinkedHashMap实现LRU缓存算法
     * @author lwh
     * @datetime 2020/9/11 18:14
     */
    public static int[] LRU_Built_In_LinkedHashMap(int[][] operators, int k) {
        // 缓存结构
        // accessOrder： 为true排序规则为最近最少使用，false默认按照插入顺序排序
        Map<Integer, Integer> Cache = new LinkedHashMap<Integer, Integer>(k, 0.75f, true) {
            // 重写缓存溢出时的移除策略
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                // 当当前的缓存数量大于容量时移除元素
                return size() > k;
            }
        };
        // 结果输出数组
        List<Integer> result = new ArrayList<>();

        for (int[] operator : operators) {
            if (operator[0] == 1) {
                // 插入操作
                Cache.put(operator[1], operator[2]);
            } else {
                // get操作
                Integer temp = Cache.get(operator[1]);
                // 返回为null的情况下结果数组要求为-1
                result.add(temp == null ? -1 : temp);
            }
        }
        // 返回整型数组，List转int[]
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * @param operators 操作数组
     * @param k         缓存大小
     * @return int[]
     * @description LRU算法测试入口
     * @author lwh
     * @datetime 2020/9/12 10:35
     */
    public int[] MyLRU(int[][] operators, int k) {
        // 初始化缓存大小
        this.MAX_SIZE = k;
        this.cache = new HashMap<>(MAX_SIZE);
        // 最开始缓存为空，头指向尾
        this.head.next = tail;
        this.tail.prev = head;

        // 记录结果
        List<Integer> result = new ArrayList<>();
        // 另一种写法
        // java8以上支持箭头函数，类似js中的箭头函数
        // int resultLen = (int)Arrays.stream(operators).filter(x -> x[0] == 2).count();
        // int[] result = new int[resultLen];

        for (int[] operator : operators) {
            if (operator[0] == 1) {
                // 插入操作
                set(operator[1], operator[2]);
            } else {
                // 取值操作
                result.add(get(operator[1]));
            }
        }

        // 返回整型数组，List转int[]
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * @param key   键
     * @param value 值
     * @description 向缓存结构中加入新数据或者更改指定缓存内容
     * @author lwh
     * @datetime 2020/9/12 10:24
     */
    public void set(int key, int value) {
        if (get(key) > -1) {
            // 说明set了已有key对应的value
            // 在执行get时已经将key的记录设置为最常用的记录
            // 直接更改key对应的value即可
            cache.get(key).value = value;
        } else {
            // 插入新记录
            // 溢出处理
            if (cache.size() == MAX_SIZE) {
                // 溢出 获取链尾的key，也就是最不常使用的记录的key
                int rk = tail.prev.key;
                // 孤立节点
                tail.prev = tail.prev.prev;
                tail.prev.prev.next = tail;
                // 移除节点
                cache.remove(rk);
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            // 将新记录设为最常用，即移至链首
            moveToHead(newNode);
        }
    }

    /**
     * @param key 键
     * @return int
     * @description 获取缓存结构中指定数据
     * @author lwh
     * @datetime 2020/9/12 10:24
     */
    public int get(int key) {
        // 是否存在
        if (cache.containsKey(key)) {
            // 存在，设为最常用记录，即移至链首
            Node node = cache.get(key);
            // 孤立节点
            node.prev.next = node.next;
            node.next.prev = node.prev;
            // 移至链首
            moveToHead(node);
            // 返回值
            return node.value;
        }
        // 不存在返回-1
        return -1;
    }

    /**
     * @param lastestVisitedNode 最近访问的节点
     * @description 将最近访问的节点移到链表头，该节点在调用方法之前必须是一个孤立的节点
     * @author lwh
     * @datetime 2020/9/12 10:22
     */
    public void moveToHead(Node lastestVisitedNode) {
        // 设置孤立节点在链中的先后指向
        lastestVisitedNode.prev = head;
        lastestVisitedNode.next = head.next;
        // 更改原链的先后指向
        head.next.prev = lastestVisitedNode;
        head.next = lastestVisitedNode;
    }

    /**
     * @author lwh
     * @description 描述节点顺序的双向链表
     * @datetime 2020/9/12 9:23
     */
    static class Node {
        public int key;
        public int value;
        public Node next;
        public Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
}
