package com.lwh147.algorithm;

import java.util.*;

/**
 * @author lwh
 * @description 面试题题解测试类
 * @date 2020/9/12
 **/
public class Solution {
    // region --算法常用结构定义--

    /*
     * 因为栈只能每次pop栈顶的元素，在只有一个栈的情况下该值只能是最后入栈的元素，对应队列为最后入队的元素
     * 所以还需要一个stack2将stack1的元素倒序入栈，即stack2.push（stack1.pop），此时stack2栈顶元素为最先入栈即队首元素
     * 栈1即stack1始终是原始栈的存放方式，所以push操作直接在stack1上完成即可
     * 栈2即stack2始终是stack1的逆序，即栈顶存放的是最先入栈的元素，所以pop操作时必须将stack1倒序存入stack2再pop
     * 栈1和2都为空说明此时队列为空
     * 因为pop操作会删除原始栈中的值，所以队列有元素的情况下任何时刻栈1和栈2都是一个为空另一个不为空
     * 只有栈1为空说明上次操作为pop操作
     * 只有栈2为空说明上次操作为push操作
     * 只有两次操作不同时需要在stack1和stack2两种存放顺序之间进行相应转换
     * 一直push则一直对stack1操作，一直pop则一直对stack2操作
     * 注意:必须保证队列操作的合法性，方法不检查操作是否合法
     */
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    // endregion

    public static void main(String[] args) {
        Solution s = new Solution();
        Solution.combinationsOfSameSum(new int[]{1, 2, -1, 0, 3, 4}, 0, 3);
    }

    // region 寻找和为定值sum的n个数的组合
    public static void combinationsOfSameSum(int[] arr, int sum, int n) {
        int length = arr.length;
        if (length < n) {
            return;
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length - 2; i++) {
            if (arr[i] >= sum) {
                continue;
            }
            for (int j = i + 1; j < length - 1; j++) {
                if (arr[i] + arr[j] >= sum) {
                    continue;
                }
                for (int k = j + 1; k < length; k++) {
                    if (arr[i] + arr[j] + arr[k] == sum) {
                        List<Integer> solution = new ArrayList<>(n);
                        solution.add(arr[i]);
                        solution.add(arr[j]);
                        solution.add(arr[k]);
                        result.add(solution);
                    }
                }
            }
        }
        Iterator<List<Integer>> it1 = result.iterator();
        StringBuilder sb = new StringBuilder("[");
        while (it1.hasNext()) {
            sb.append(it1.next().toString());
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
    // endregion

    // region --约瑟夫问题--

    /**
     * 十个猴子围成一圈选大王，依次1-3 循环报数，报到3 的猴子被淘汰，最后哪只猴子能成为大王？
     *
     * @param n 人数
     * @param m 报的数
     * @return int 剩下的
     **/
    public static int monkeyKing(int n, int m) {
        boolean[] array = new boolean[n];
        for (int i = 0; i < n; i++) {
            array[i] = true;
        }
        int last = n;
        int index = 0;
        int count = 0;
        while (last > 1) {
            if (array[index]) {
                count++;
                if (count == m) {
                    array[index] = false;
                    last--;
                    count = 0;
                }
            }
            index++;
            if (index == n) {
                index = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            if (array[i]) {
                return i + 1;
            }
        }
        return -1;
    }
    // endregion

    // region --10进制数转换16进制--

    public static String converse2Hexadecimal(int num) {
        final int BASE = 16;
        final char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        Stack<Integer> remainderList = new Stack<>();
        StringBuilder hexString = new StringBuilder("Ox");
        int quotient = num;

        while (quotient / BASE != 0) {
            remainderList.push(quotient % BASE);
            quotient = quotient / BASE;
        }

        hexString.append(map[quotient]);
        while (!remainderList.empty()) {
            hexString.append(map[remainderList.pop()]);
        }

        return hexString.toString();
    }

    // endregion

    // region --递增子序列--

    /**
     * @param nums 数字数组
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @description 递增子序列
     * @author lwh
     * @datetime 2020/9/21 15:41
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        //数组长度为0或1则直接返回空
        if (nums.length == 0 || nums.length == 1) {
            return new ArrayList<>();
        }

        // int[] 转 List<Integer>
        List<Integer> numList = new ArrayList<>();
        for (int i : nums)
            numList.add(i);

        //保存结果
        List<List<Integer>> result = new ArrayList<>();

        //回溯寻找解
        backtrack(numList, -1, result, new ArrayList<>());

        return result;
    }

    /**
     * @param nums         原数组
     * @param index        当前作为子序列头的数字下标
     * @param Subsequences 所有递增子序列列表
     * @param Subsequence  当前递增子序列列表
     * @description 利用回溯思想寻找递增子序列
     * index为当前被选中的递增子序列的首位数字在数组中的下标
     * 因为第一次没有任何数字被选中，所以递归开始时index=-1
     * Subsequence为当前的递增子序列
     * @author lwh
     * @datetime 2020/9/21 15:42
     */
    public void backtrack(List<Integer> nums, int index, List<List<Integer>> Subsequences, List<Integer> Subsequence) {
        //递归开始，没有任何数字被选中，所以可以选择的数字为数组中的所有数
        if (index == -1) {
            for (int i = 0; i < nums.size(); i++) {
                //判断该数字是否在它之前已经出现过，如果出现过则不需要递归
                if (isRepeat(nums, i)) {
                    //新建递增子序列
                    List<Integer> start = new ArrayList<>();
                    //递增子序列的首位
                    start.add(nums.get(i));
                    //递归查找以当前数字为首的递增子序列
                    backtrack(nums, i, Subsequences, start);
                }
            }
        } else {
            //是否到达末尾
            //这里index只到达倒数第二位即可，因为递增子序列不能由一个数字组成
            if (index < nums.size() - 1) {
                //取得待选数字数组，即位于当前所选数字下标之后的所有数字
                List<Integer> selection = nums.subList(index + 1, nums.size());
                //当前递增子序列的最后一位
                Integer last = Subsequence.get(Subsequence.size() - 1);
                //遍历可选数字的数组
                for (int i = 0; i < selection.size(); i++) {
                    //当可选数字大于或等于递增子序列的最后一位时才能选择该数字
                    if (last <= selection.get(i)) {
                        //判断该数字是否在它之前已经出现过，如果出现过则不需要递归
                        if (isRepeat(selection, i)) {
                            //因为.add方法将参数的引用加入了目标容器中，所以每次产生新序列时都要new一个新的，
                            //要不然一直是对同一个序列操作，最终结果集里都是同一个序列
                            List<Integer> newSubsequence = new ArrayList<>(Subsequence);
                            newSubsequence.add(selection.get(i));
                            Subsequences.add(newSubsequence);
                            List<Integer> nextSubsequence = new ArrayList<>(newSubsequence);
                            backtrack(nums, index + i + 1, Subsequences, nextSubsequence);
                        }
                    }
                }
            }
        }

    }


    /**
     * @param selection 目标查询数组
     * @param index     指定的下标
     * @return boolean
     * @description 判断一个数组中指定下标的数字是否在它之前已经出现
     * 出现返回false
     * 未出现返回true
     * @author lwh
     * @datetime 2020/9/21 15:44
     */
    public boolean isRepeat(List<Integer> selection, Integer index) {
        Integer goal = selection.get(index);
        for (int j = 0; j < index; j++)
            if (selection.get(j).equals(goal))
                return false;
        return true;
    }

    // endregion

    // region --数字范围按位与--

    /**
     * @param m 数字1
     * @param n 数字2
     * @return int
     * @description 数字范围按位与
     * 对所有数字执行按位与运算的结果是所有对应二进制字符串的公共前缀再用零补上后面的剩余位，证明略
     * 给定两个整数，我们要找到它们对应的二进制字符串的公共前缀
     * 将两个整数不断按二进制运算右移直到两者值相等，记录右移次数
     * 然后将数字按二进制运算左移之前右移的次数(低位补0)就是与的结果
     * @author lwh
     * @datetime 2020/9/21 15:37
     */
    public int rangeBitwiseAnd(int m, int n) {
        if (m == n)
            return m;
        if (m == 0 || n == 0)
            return 0;
        // int temp = m;
        // for (int i=m+1; i<=n; i++)
        //     temp = temp & i;
        // return temp;
        int i = 0;
        while (m != n) {
            m /= 2;
            n /= 2;
            i++;
        }
        if (m == 0)
            return 0;
        for (; i > 0; i--)
            m *= 2;
        return m;
    }

    // endregion

    // region --树的最小深度--

    /**
     * @param root 树的根结点
     * @return int
     * @description 树的最小深度
     * @author lwh
     * @datetime 2020/9/21 15:34
     */
    public int minDepth(TreeNode root) {
        // 空树
        /*if(root == null)
            return 0;
        //叶子节点，最小深度为1
        if(root.left == null && root.right == null)
            return 1;
        else {
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            if(left == 0)
                return ++right;
            if(right == 0)
                return ++left;
            return left < right ? ++left : ++right;
        }*/
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    // endregion

    // region --字符串相乘--

    /**
     * @param num1 数字1字符串
     * @param num2 数字2字符串
     * @return java.lang.String
     * @description 利用乘法的基本运算方式实现字符串的相乘
     * @author lwh
     * @datetime 2020/9/21 15:32
     */
    public String multiply(String num1, String num2) {
        //任一数字为0结果为0
        if (num1.equals("0") || num2.equals("0"))
            return "0";

        //转换为整型数组
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();

        int[] left = new int[c1.length];
        int[] right = new int[c2.length];

        for (int i = 0; i < c1.length; i++)
            left[i] = c1[i] - 48;
        for (int i = 0; i < c2.length; i++)
            right[i] = c2[i] - 48;

        //两数相乘的结果长度只可能为i+j或者i+j-1，这里取最大，先将最高位置0
        int[] result = new int[left.length + right.length];

        //拆分为单个数字与字符串相乘
        for (int i = left.length - 1; i >= 0; i--) {
            //取得结果
            int[] temp = digitMultiply(right, left[i]);
            //单个数字与字符串相乘的结果相加时，第i个结果的初始位置要前移i位，即对result的操作的起始下标要减少i
            int j = left.length - i - 1;
            //第一次相加直接赋值即可
            if (j == 0)
                for (int k = temp.length - 1; k >= 0; k--) {
                    //赋值
                    result[result.length - 1 - j] = result[result.length - 1 - j] + temp[k];
                    //下一位
                    j++;
                }
                //不是第一次相加，要考虑进位
            else {
                //上一位相加的进位，初始为0
                int lastoverflow = 0;
                for (int k = temp.length - 1; k >= 0; k--) {
                    //考虑进位时先加上上一位的进位
                    int tempsum = result[result.length - 1 - j] + temp[k] + lastoverflow;
                    //加上上一位的进位之后是否产生新的进位
                    if (tempsum >= 10) {
                        //是，更新进位值
                        lastoverflow = tempsum / 10;
                        //实际值
                        result[result.length - 1 - j] = tempsum % 10;
                    } else {
                        //否，不产生新进位，置0
                        lastoverflow = 0;
                        result[result.length - 1 - j] = tempsum;
                    }
                    //下一位
                    j++;
                }
            }
        }

        StringBuilder resultStr = new StringBuilder();
        //最高位是否为0，如果是则去除，从次高位开始，否则从0开始
        int i = result[0] == 0 ? 1 : 0;
        for (; i < result.length; i++)
            resultStr.append(result[i]);

        return resultStr.toString();
    }

    /**
     * @param left  整型数组
     * @param right 单个数字
     * @return int[]
     * @description 单个数字与字符串相乘
     * @author lwh
     * @datetime 2020/9/21 15:33
     */
    public int[] digitMultiply(int[] left, int right) {

        //存放结果，长度取值原因同上
        int[] result = new int[left.length + 1];
        //中间变量，存储进位值
        int[] overflow = new int[left.length];

        //初步计算
        for (int i = left.length - 1; i >= 0; i--) {
            int temp = right * left[i];
            //进位保存
            overflow[i] = temp / 10;
            //未考虑进位时的结果
            result[i + 1] = temp % 10;
        }
        //因为不考虑进位，所以结果的最高位置0
        result[0] = 0;

        //根据进位计算实际相乘结果
        //最低位和最高位处理特殊，所以从循环中跳过，只对中间位计算
        for (int i = result.length - 2; i > 0; i--) {
            //考虑进位时先加上上一位的进位
            int temp = result[i] + overflow[i];
            //加上上一位进位之后是否产生进位
            if (temp >= 10) {
                //更新进位
                overflow[i - 1] = overflow[i - 1] + temp / 10;
                //该位的实际结果
                result[i] = temp % 10;
            } else
                result[i] = temp;

        }
        //最高位只能由进位产生，所以直接将进位值赋予最高位
        result[0] = overflow[0];

        return result;
    }

    // endregion

    // region --电话号码的字母组合--

    /**
     * @param digits 给定的字符串
     * @return java.util.List<java.lang.String>
     * @description 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射与电话按键相同。注意 1 不对应任何字母。
     * @author lwh
     * @datetime 2020/9/21 14:47
     */
    public List<String> letterCombinations(String digits) {
        if (digits.equals(""))
            return new ArrayList<>();

        //映射数组构建
        List<char[]> mapper = new ArrayList<>();

        mapper.add(new char[]{'a', 'b', 'c'});
        mapper.add(new char[]{'d', 'e', 'f'});
        mapper.add(new char[]{'g', 'h', 'i'});
        mapper.add(new char[]{'j', 'k', 'l'});
        mapper.add(new char[]{'m', 'n', 'o'});
        mapper.add(new char[]{'p', 'q', 'r', 's'});
        mapper.add(new char[]{'t', 'u', 'v'});
        mapper.add(new char[]{'w', 'x', 'y', 'z'});

        char[] charArray = digits.toCharArray();
        int[] digitsArray = new int[charArray.length];

        //将数字字符串分割为整型数组，减去2之后与mapper对应起来
        for (int i = 0; i < charArray.length; i++)
            digitsArray[i] = charArray[i] - 48 - 2;

        List<String> combinations = new ArrayList<>();

        backtrack(mapper, digitsArray, 0, combinations, new StringBuffer());

        return combinations;
    }

    /**
     * @param mapper       数字到字母的映射
     * @param digitsArray  输入数字的数组（数字即为其字母映射的下标）
     * @param index        当前数字的下标
     * @param combinations 所有的组合列表
     * @param combination  当前单个组合
     * @description 根据回溯算法思想查找电话号码的字母组合
     * @author lwh
     * @datetime 2020/9/21 15:27
     */
    public void backtrack(List<char[]> mapper, int[] digitsArray, int index, List<String> combinations, StringBuffer combination) {
        //如果当前电话号码的数字下标等于其长度，则代表找到一个组合并且需要回溯
        if (index == digitsArray.length) {
            combinations.add(combination.toString());
        } else {
            //获取该数字对应的字母表
            int digit = digitsArray[index];
            char[] clist = mapper.get(digit);
            //遍历字母表
            for (char c : clist) {
                //组合
                combination.append(c);
                //递归下一个数字的字母表
                backtrack(mapper, digitsArray, index + 1, combinations, combination);
                //回溯
                combination.delete(index, index + 1);
            }
        }
    }

    // endregion --电话号码的字母组合--

    // region --寻找字符串中的最长无重复子串--

    /**
     * @param s 要寻找的字符串
     * @return int
     * @description 寻找字符串中的最长无重复子串，注意不是递增序列
     * 滑动窗口，用set维护一个不重复的窗口
     * @author lwh
     * @datetime 2020/9/21 14:21
     */
    public int maxLength(String s) {
        // 利用一个辅助list(可以是List或者Set)记录当前的无重复子串，并且记录之前已知的最长无重复子串长度longest
        // 遍历字符串，将每一个元素加入辅助list，加入之前判断是否存在要加入的字符
        // 如果存在该字符则说明找到一个新的无重复子串，需要除掉列表中重复字符及之前的部分之后再加入新字符
        // 如果不存在则直接加入新字符
        // 比较更新当前最长字串长度

        int longest = 0;
        // List<Character> cList = new ArrayList<>();
        // 使用set速度更快
        Set<Character> cSet = new HashSet<>();

        for (int j = 0, i = 0; i < s.length(); i++) {
            char value = s.charAt(i);
            // 如果存在该字符则说明找到一个新的无重复子串，需要除掉列表中重复字符及之前的部分之后再加入新字符
            while (cSet.contains(value)) {
                cSet.remove(s.charAt(j++));
            }
            // 加入新字符
            cSet.add(value);
            // 更新最长长度
            longest = Math.max(longest, i - j + 1);
        }

        return longest;
    }

    // endregion

    // region --k个一组逆转链表--

    /**
     * @param head 要反转的链表头
     * @param k    k
     * @return com.algorithm.Solution.ListNode
     * @description k个一组反转链表
     * 首先使用两个指针指向分别最终形成的链表的头和尾，刚开始为null
     * k个k个遍历链表，每当遇到新的一组 记录头结点和尾结点，然后反转链表，将反转结果拼接到最终链表之后
     * 当遍历到当前组不够k个结点时直接将该组头结点接到最终链表之后即可
     * 注意需要判断是否第一组就不够k个结点，此时说明k值不合法，直接返回原链表
     * @author lwh
     * @datetime 2020/9/20 20:14
     */
    public ListNode reverseListByKGroup(ListNode head, int k) {
        if (head == null)
            return null;
        // 遍历原链表的指针
        ListNode traversal = head;
        // 最终链表的头尾指针
        ListNode resultHead = null;
        ListNode resultRear = null;
        while (traversal != null) {
            // 子链表的头尾指针
            ListNode subListHead = traversal;
            ListNode subListRear = null;
            // 前进k-1个结点，因为traversal进入循环之前已经指向该组第一个结点
            for (int i = 0; i < k - 1; i++) {
                // 只要不为空继续前进
                if (traversal == null)
                    break;
                traversal = traversal.next;
            }
            // 此时traversal指向该组结点的末尾结点
            // 如果traversal为null说明最后一组不够k个节点
            if (traversal != null) {
                // 记录子链表末尾结点
                subListRear = traversal;
                // 让traversal指向下一个子链表头结点
                traversal = traversal.next;
                // 将子链表从母链表中独立
                subListRear.next = null;
                // 记录反转后的子链表末尾结点
                subListRear = subListHead;
                // 进行反转，记录反转后的子链表头结点
                subListHead = reverseList(subListHead);
                if (subListRear == head) {
                    // 子链表的末尾结点如果是原链表的头结点，说明此时要对最终链表的头结点进行初始化
                    resultHead = subListHead;
                } else {
                    // 否则将新的子链表拼接到最终链表中
                    resultRear.next = subListHead;
                }
                // 更新最终链表末尾结点指针
                resultRear = subListRear;
            } else {
                // 说明该组结点数不足k个，判断是否是k不合法的情况
                if (resultRear == null)
                    return head;    // k不合法，返回原链表
                // k合法，直接将最后的子链表拼接到最终链表之后返回
                resultRear.next = subListHead;
                return resultHead;
            }
        }
        // 能够出循环说明链表结点数量刚好为k的整数倍，返回结果
        return resultHead;
    }

    // endregion

    // region --逆转链表--

    /**
     * @param pHead 要逆转的链表头
     * @return com.learn.LinkedList<T>
     * @description 逆转链表
     * @author lwh
     * @datetime 2020/9/12 9:09
     */
    public ListNode reverseList(ListNode pHead) {
        ListNode newHead, oldHead;
        newHead = pHead;
        oldHead = null;
        while (newHead != null) {
            ListNode temp = newHead.next;
            newHead.next = oldHead;
            oldHead = newHead;
            newHead = temp;
        }
        return oldHead;
    }

    // endregions

    // region --链表中环的入口结点--

    /**
     * @param head 要检测的链表头
     * @return com.algorithm.Solution.ListNode
     * @description 对于一个给定的链表，返回环的入口节点，如果没有环，返回null
     * 此方法在双指针法检测链表是否存在环的基础上返回环的入口节点
     * 如果有环，设链表头结点X到环入口结点Y的距离为a，Y到两指针相遇结点Z的距离为b，Z到Y的距离为c
     * 由快指针为慢指针的速度的两倍可知：2(a+b) = a+b+n(c+b)   n>=1
     * 由此可以推出X到Y的距离a = nc+(n-1)b
     * a = (n-1)c + (n-1)b + c
     * a = (n-1)(c+b) + c
     * c+b刚好是环的长度，那么如果设置两个指针以相同的速度分别从X和Z开始前进，当从X开始的指针走完距离a到达Y时，另一个从Z开始的指针
     * 恰好走完(n-1)(c+b)+c距离，其中走完(n-1)(c+b)刚好回到起点即Z，再走c距离刚好也到达Y，所以两指针必定会在环的入口处相遇
     * 换句话说，两指针相遇时指向的结点就是环的入口结点Y
     * @author lwh
     * @datetime 2020/9/20 15:25
     */
    public ListNode detectCycle(ListNode head) {
        // 特殊情况判断
        if (head == null)
            return null;

        // 检测是否存在环的快慢指针
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                // 此时说明存在环且fast和slow指向相遇结点Z
                // 复用快指针，让其指向头结点X，然后让fast与slow以相同的速度开始前进
                fast = head;
                while (fast != slow) {
                    slow = slow.next;
                    fast = fast.next;
                }
                // fast和slow相遇且都指向Y，返回其中一个即可
                return fast;
            }
        }
        // 能执行到此说明无环返回null
        return null;
    }

    // endregion

    // region --两个代表整数的链表相加--

    /**
     * @param head1 第一个链表
     * @param head2 第二个链表
     * @return com.algorithm.Solution.ListNode
     * @description 两个链表相加生成相加链表
     * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
     * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
     * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
     * @author lwh
     * @datetime 2020/9/19 15:25
     */
    public ListNode addInList(ListNode head1, ListNode head2) {
        // 特殊情况
        if (head1 == null && head2 == null)
            return null;
        else if (head1 == null)
            return head2;
        else if (head2 == null)
            return head1;
        else {
            // 保存链表中的数字到两个列表当中
            List<ListNode> num1 = new ArrayList<>();
            List<ListNode> num2 = new ArrayList<>();

            ListNode traversal = head1;
            while (traversal != null) {
                num1.add(traversal);
                traversal = traversal.next;
            }
            traversal = head2;
            while (traversal != null) {
                num2.add(traversal);
                traversal = traversal.next;
            }

            int num1Len = num1.size();
            int num2Len = num2.size();
            // 记录进位
            int overflow = 0;
            // 相加结果最长为两数中位数较长的一位+1
            int resultLen = Math.max(num1Len, num2Len) + 1;
            // 用来保存结果链表结点的列表
            List<ListNode> result = new ArrayList<>(resultLen);
            // 初始化
            for (int i = 0; i < resultLen; i++) {
                result.add(new ListNode(-1));
            }
            for (int i = 0; i < resultLen - 1; i++) {
                result.get(i).next = result.get(i + 1);
            }
            // 不论两数长度为多少，最终是否产生的新高位只根据最后进位值判断即可
            // 如果两数长度相等，则直接按照两数长度从末尾开始相加长度次
            if (num1Len == num2Len) {
                for (int i = 0; i < num1Len; i++) {
                    ListNode temp = result.get(resultLen - 1 - i);
                    int rtemp = num1.get(num1Len - 1 - i).val + num2.get(num2Len - 1 - i).val + overflow;
                    if (rtemp >= 10) {
                        overflow = rtemp / 10;
                        rtemp = rtemp % 10;
                    } else {
                        overflow = 0;
                    }
                    temp.val = rtemp;
                }
            } else {
                // 如果不等，则根据两数中位数较小的数相加较小长度次
                // 之后只需要将较长数剩下的位数与进位值进行相加即可得到最终结果对应位置的值
                int i = 0;
                if (num1Len < num2Len) {
                    // 首先根据较小位数的数相加其长度次
                    for (; i < num1Len; i++) {
                        ListNode temp = result.get(resultLen - 1 - i);
                        // 此时相加要考虑两数的对应位数值和进位值
                        int rtemp = num1.get(num1Len - 1 - i).val + num2.get(num2Len - 1 - i).val + overflow;
                        // 是否进位判断和进位值保存
                        if (rtemp >= 10) {
                            overflow = rtemp / 10;
                            rtemp = rtemp % 10;
                        } else {
                            overflow = 0;
                        }
                        // 确定该位的相加结果
                        temp.val = rtemp;
                    }
                    // 然后根据较大位数的数相加其剩余长度次
                    for (; i < num2Len; i++) {
                        ListNode temp = result.get(resultLen - 1 - i);
                        // 此时相加只需考虑较大数该位置的值与进位制
                        int rtemp = num2.get(num2Len - 1 - i).val + overflow;
                        if (rtemp >= 10) {
                            overflow = rtemp / 10;
                            rtemp = rtemp % 10;
                        } else {
                            overflow = 0;
                        }
                        temp.val = rtemp;
                    }
                } else {
                    for (; i < num2Len; i++) {
                        ListNode temp = result.get(resultLen - 1 - i);
                        int rtemp = num1.get(num1Len - 1 - i).val + num2.get(num2Len - 1 - i).val + overflow;
                        if (rtemp >= 10) {
                            overflow = rtemp / 10;
                            rtemp = rtemp % 10;
                        } else {
                            overflow = 0;
                        }
                        temp.val = rtemp;
                    }
                    for (; i < num1Len; i++) {
                        ListNode temp = result.get(resultLen - 1 - i);
                        int rtemp = num1.get(num1Len - 1 - i).val + overflow;
                        if (rtemp >= 10) {
                            overflow = rtemp / 10;
                            rtemp = rtemp % 10;
                        } else {
                            overflow = 0;
                        }
                        temp.val = rtemp;
                    }
                }
            }
            // 是否产生的新高位
            if (overflow == 0) {
                return result.get(1);
            } else {
                result.get(0).val = overflow;
                return result.get(0);
            }
        }
    }

    // endregion

    // region --用两个栈实现队列--

    /**
     * @param node 结点值
     * @description 用两个栈实现队列的push操作
     * @author lwh
     * @datetime 2020/9/19 13:17
     */
    public void push(int node) {
        // 判断上次是否是pop操作
        // 如果是则需要将stack2逆序存入stack1再执行push操作
        // 不是则直接push即可
        if (stack1.isEmpty()) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        stack1.push(node);
    }

    /**
     * @return int  队列头部值
     * @description 用两个栈实现队列的pop操作
     * @author lwh
     * @datetime 2020/9/19 13:18
     */
    public int pop() {
        // 判断上次是否是push操作
        // 如果是则需要将stack1逆序存入stack2再执行pop操作
        // 不是则直接pop即可
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     * @param root 要遍历的二叉树的根结点
     * @return java.util.ArrayList<java.util.ArrayList < java.lang.Integer>> 遍历结果
     * @description 返回二叉树的层序遍历结果，要求结果分层保存
     * @author lwh
     * @datetime 2020/9/19 12:39
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // 保存最终结果
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root != null) {
            // 队列，保存每层所有的结点
            Queue<TreeNode> q = new LinkedList<>();

            q.offer(root);
            while (!q.isEmpty()) {
                // 挨个遍历的思想是每次只输出一个结点，但是要求分层输出时，一次输出一层的结点
                // 每输出完一层，队列中剩下的结点就是下一层所有结点即q.size
                // 而刚开始根结点层的结点数为1也刚好为队列的大小即q.size，刚好形成递推关系

                // 获取每层的总共结点数
                int cLevelNodesCount = q.size();
                // 用来保存某一层的遍历结果
                ArrayList<Integer> levelResult = new ArrayList<>(cLevelNodesCount);
                for (int i = 0; i < cLevelNodesCount; i++) {
                    TreeNode temp = q.poll();
                    assert temp != null;
                    levelResult.add(temp.val);
                    if (temp.left != null)
                        q.offer(temp.left);
                    if (temp.right != null)
                        q.offer(temp.right);
                }
                // 将该层的遍历结果添加到最终结果
                result.add(levelResult);
            }
        }
        // 注意如果根节点为null，返回值为空的列表而不是null
        return result;
    }

    /**
     * @param head 链表头
     * @param n    移除倒数第n个结点
     * @return com.algorithm.Solution.ListNode
     * @description 移除链表的倒数第n个结点
     * 必须保证n是合法的
     * @author lwh
     * @datetime 2020/9/18 20:41
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 特殊情况判断
        if (head == null || head.next == null) {
            return null;
        } else {
            // 方法1
            // 遍历链表的同时按顺序存储链表结点到一个列表中
            // 之后只需要根据n与被删除的结点的下标之间的关系定位倒数第n+1个结点即可
            /*List<ListNode> list = new ArrayList<>();
            ListNode traversal = head;

            while (traversal != null) {
                list.add(traversal);
                traversal = traversal.next;
            }

            int length = list.size();
            // 判断删除的是不是头结点
            if (length - n == 0)
                return list.get(1);

            // 需要定位的是倒数第n+1个结点，length - n是倒数第n个
            list.get(length - n - 1).next = list.get(length - n).next;
            return list.get(0);*/


            // 方法2
            // 遍历链表取得链表长度
            // 根据n与被删除的结点位置的关系通过循环定位倒数第n+1个结点
            /*int length = getListLength(head);

            // 给链表添加一个头结点方便后续操作
            ListNode temp = new ListNode();
            temp.next = head;
            ListNode prev = temp;

            // 需要定位的是倒数第n+1个结点，length - n是倒数第n个
            for (int i = 0; i < length - n; i++)
                prev = prev.next;

            prev.next = prev.next.next;
            return temp.next;*/

            // 方法3 双指针
            // 首先为链表添加一个头结点将链表变为带有头结点的链表方便后续操作，然后让两个指针都指向新头结点
            // 让其中一个指针right(另一个相应为left)在链表上前进n+1次，这样left与right中间就隔了n个结点
            // 从right指向的结点开始每次前进一个结点遍历至链表结尾
            // 此时right指向null，指向链表最后一个结点之后的位置，而由于left与right之间隔了n个结点
            // 那么left指向的恰好就是倒数第n个结点前的结点也就是倒数第n+1个结点
            // 给链表添加一个头结点方便后续操作
            ListNode temp = new ListNode();
            temp.next = head;
            ListNode left = temp;
            ListNode right = temp;

            // 使left与right中间就隔n个结点
            for (int i = 0; i <= n; i++)
                right = right.next;

            // 从right指向的结点开始每次前进一个结点遍历至链表结尾
            while (right != null) {
                right = right.next;
                left = left.next;
            }

            left.next = left.next.next;
            return temp.next;
        }
    }

    // endregion

    // region --求二叉树的层序遍历--

    /**
     * @param head 链表头
     * @return int  链表长度
     * @description 得到链表的长度
     * @author lwh
     * @datetime 2020/9/18 20:10
     */
    public int getListLength(ListNode head) {
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }
        return i;
    }

    // endregion

    // region --移除链表的倒数第n个结点--

    /**
     * @param root 二叉树根结点
     * @return int[][]
     * @description 分别按照二叉树先序，中序和后序打印所有的结点。
     * @author lwh
     * @datetime 2020/9/21 15:11
     */
    public int[][] threeOrders(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>(3);
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());

        binaryTreeThreeOrdersTraversal(root, result);

        return new int[][]{
                result.get(0).stream().mapToInt(Integer::valueOf).toArray(),
                result.get(1).stream().mapToInt(Integer::valueOf).toArray(),
                result.get(2).stream().mapToInt(Integer::valueOf).toArray(),
        };
    }

    /**
     * @param root   要遍历的二叉树根结点
     * @param result 存储三种遍历结果的动态二维数组
     * @description 二叉树的先中后序遍历
     * 二维数组中，第一个数组存储先序遍历结果，第二个数组存储中序遍历结果，以此类推
     * @author lwh
     * @datetime 2020/9/16 11:13
     */
    public void binaryTreeThreeOrdersTraversal(TreeNode root, List<List<Integer>> result) {
        if (root != null) {
            result.get(0).add(root.val);
            binaryTreeThreeOrdersTraversal(root.left, result);
            result.get(1).add(root.val);
            binaryTreeThreeOrdersTraversal(root.right, result);
            result.get(2).add(root.val);
        }
    }

    // endregion

    // region --分别按照二叉树先序，中序和后序打印所有的结点--

    /**
     * @param a 目标数组
     * @param n 数组长度
     * @param K 目标第k大
     * @return int  所查找的值
     * @description 基于快排的思想找出数组中的第k大的数入口
     * 方法不检查k值的合法性，所以必须确保k值合法
     * @author lwh
     * @datetime 2020/9/15 20:24
     */
    public int findKth(int[] a, int n, int K) {
        return quickFind(a, 0, n - 1, K);
    }

    /**
     * @param a    目标数组
     * @param low  开始下标（包含）
     * @param high 结束下标（包含）
     * @param k    目标第k大
     * @return int  所查找的值
     * @description 基于快排的思想找出数组中的第k大的数
     * 方法不检查k值的合法性，所以必须确保k值合法
     * @author lwh
     * @datetime 2020/9/15 20:02
     */
    public int quickFind(int[] a, int low, int high, int k) {
        if (low < high) {
            // 分割方法 思路参考split
            int lastSwapPos = low, pivotVal = a[low];

            for (int i = low + 1; i <= high; i++) {
                if (a[i] > pivotVal) {
                    lastSwapPos++;
                    if (lastSwapPos != i) {
                        int temp = a[lastSwapPos];
                        a[lastSwapPos] = a[i];
                        a[i] = temp;
                    }
                }
            }
            if (lastSwapPos != low) {
                a[low] = a[lastSwapPos];
                a[lastSwapPos] = pivotVal;
            }

            // 由于数组是按从大到小的顺序排序的，所以针对当前数组，基准元素的位置确定之后，它的位置在之后的处理中不再发生变化
            // 所以每次基准元素确定位置之后，i=基准元素的下标加1 即为第i大元素，此时只需将所查找的k与i进行比较
            // 注意：因为第k大是从1开始的，而数组下标是从0开始的，所以这里的处理办法是将k-1(也可将i+1)
            // 如果i>k-1，说明要查找的元素处于当前基准元素的左侧，只在左侧进行查找即可
            // 如果i<k-1，说明要查找的元素处于当前基准元素的右侧，只在右侧进行查找即可
            // 如果i==k-1，说明要查找的元素即为当前基准元素，返回
            if (lastSwapPos < k - 1)
                return quickFind(a, lastSwapPos + 1, high, k);
            else if (lastSwapPos > k - 1)
                return quickFind(a, low, lastSwapPos - 1, k);
            else
                return pivotVal;
        }
        // 因为k必须合法，而此时数组被分割为只有一个元素，所以该元素必定为所找元素，直接返回即可
        return a[low];
    }

    // endregion

    // region --找到数组中的第k大数--

    /**
     * @param list 要排序的列表
     * @param low  要排序的起始下标（包含）
     * @param high 要排序的结束下标（包含）
     * @description 快速排序 从小到大
     * @author lwh
     * @datetime 2020/9/15 12:58
     */
    public void quickSort(List<Integer> list, int low, int high) {
        // 分割为一个元素的时候不必进行处理
        if (low < high) {
            // 获取用于分隔的基准元素位置
            int mid = split(list, low, high);
            // 调用自身对小于基准元素的数组和大于基准元素的数组进行排序
            quickSort(list, low, mid - 1);
            quickSort(list, mid + 1, high);
        }
    }

    /**
     * @param list 要排序的列表
     * @param low  要排序的起始下标（包含）
     * @param high 要排序的结束下标（包含）
     * @return int 分割后的基准元素下标
     * @description 快速排序的分割算法
     * 基准元素选取的是low位置的元素
     * 从基准元素下一个元素开始即low+1遍历到high指向的元素结束
     * 遍历过程中将小于基准元素的元素全部移至左边，大于或等于基准元素的自然到了右边
     * 最后将基准元素与最后一个小于基准元素的元素进行交换
     * 这样基准元素左侧全是小于它的，右侧全是大于它的
     * @author lwh
     * @datetime 2020/9/15 13:00
     */
    public int split(List<Integer> list, int low, int high) {
        // 记录最近一次元素交换操作后小于基准元素的元素位置，开始默认为基准元素位置low
        // 该位置始终指向已经遍历过的数组中最后一个小于基准元素的元素的位置
        int lastSwapPos = low;
        // 基准元素副本
        Integer pivotVal = list.get(low);

        // 从基准元素的下一个元素low+1开始遍历到high
        for (int i = low + 1; i <= high; i++) {
            // 碰到小于基准元素的元素，执行操作
            if (list.get(i) < pivotVal) {
                // 要进行交换，此时应该与最近交换后的小于基准元素的下一个元素进行交换
                // 如果要交换的目标位置与原始位置不相同则进行交换
                // 如果相同，只是不需要进行交换操作而已
                lastSwapPos++;
                if (lastSwapPos != i) {
                    Integer temp = list.get(i);
                    list.set(i, list.get(lastSwapPos));
                    list.set(lastSwapPos, temp);
                }
            }
        }
        // 最后将基准元素放到它应该放置的位置
        // 如果不等，说明要分割的数组中小于和大于基准元素的元素都有，将基准元素与最后一个小于基准元素的元素进行交换
        // 如果相等，说明要分割的数组中不存在小于基准元素的元素
        if (lastSwapPos != low) {
            list.set(low, list.get(lastSwapPos));
            list.set(lastSwapPos, pivotVal);
        }

        // 返回分割后的基准元素的位置
        return lastSwapPos;

        // region --第二种分割方法--

        // 采用双指针法，首先分别指向最两侧，然后根据与基准元素的比较结果向中间收缩，终止条件即为两个指针重合
        // 这里采取的基准元素为最左侧元素，排序方式为从大到小
        // 从指向最右侧的指针开始向左扫描（同时要检测两侧指针位置是否合法，即是否重合）直到遇到大于基准值的元素出现为止
        // 判断左右指针位置是否合法，合法则将大于基准元素的元素放到左侧指针处
        // 然后左侧指针开始向右扫描（同时要检测两侧指针位置是否合法，即是否重合）直到遇到小于基准值的元素出现为止
        // 判断左右指针位置是否合法，合法则将小于基准元素的元素放到右侧指针处
        // 如此进行下去，最后指针重合后指向的位置即为对数组分割后的基准元素所处的位置
        // 注意:指针的每一次移动以及元素的每一次调换之前都必须判断两侧指针的合法性

        // int pivotVal = a[low];
        // int l = low, r = high;
        // while (l < r) {
        //     while (l < r && a[r] < pivotVal) {
        //         r--;
        //     }
        //     if (l < r) {
        //         a[l] = a[r];
        //         l++;
        //     }
        //     while (l < r && a[l] > pivotVal) {
        //         l++;
        //     }
        //     if (l < r) {
        //         a[r] = a[l];
        //         r--;
        //     }
        // }
        // a[l] = pivotVal;
        //
        // int lastSwapPos = l;

        // endregion
    }

    // endregion

    // region --快排--

    /**
     * @param l1 有序链表1
     * @param l2 有序链表2
     * @return com.algorithm.Solution.ListNode
     * @description 合并两个有序链表
     * 采用双指针法，首先两个指针分别指向两个链表开头节点，将两个指针指向中较小的节点放入一个临时数组存放起来，然后指向较小的节点的
     * 指针后移继续进行比较，如此进行下去直到某一个指针为空，最后只需将数组中最后一个节点指向另一指针指向的节点即可
     * @author lwh
     * @datetime 2020/9/13 22:51
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 特殊情况判断
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        // 保存新的合并产生的有序链表节点
        List<ListNode> nodeList = new ArrayList<>();
        // 双指针
        ListNode p1 = l1, p2 = l2;

        // 开始循环，直到一个指针指空
        while (p1 != null && p2 != null) {
            // 始终放入较小的一方，然后被放入一方的指针后移
            if (p1.val <= p2.val) {
                nodeList.add(p1);
                p1 = p1.next;
            } else {
                nodeList.add(p2);
                p2 = p2.next;
            }
        }

        // 按照节点插入数组的顺序将节点连接起来
        int lastIndex = nodeList.size() - 1;
        for (int i = 0; i < lastIndex; i++) {
            nodeList.get(i).next = nodeList.get(i + 1);
        }
        // 数组最后一个节点指向不为空的指针指向的节点即可
        if (p1 != null)
            nodeList.get(lastIndex).next = p1;
        else
            nodeList.get(lastIndex).next = p2;

        // 链表头
        return nodeList.get(0);
    }

    /**
     * @param head 链表头
     * @return boolean
     * @description 判断链表是否有环
     * 将遍历过的节点都保存起来，每当遍历到新的节点判断是否已经遍历过该节点
     * 如果有，说明有环，返回false
     * 如果没有，说明无环，返回true
     * 空间复杂度为O(n)
     * @author lwh
     * @datetime 2020/9/12 17:26
     */
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;

        List<ListNode> list = new ArrayList<>();
        list.add(head);
        ListNode traversal = head.next;

        while (traversal != null) {
            if (list.contains(traversal))
                return true;
            list.add(traversal);
            traversal = traversal.next;
        }

        return false;
    }

    // endregion

    // region --合并两个有序链表--

    /**
     * @param head 链表头
     * @return boolean
     * @description 判断链表是否有环 改进版
     * 如果有环，设置一个快指针，设置一个慢指针，快指针一次走两步，慢指针一次走一步，快指针总能追上慢的
     * 空间复杂度为O(1)
     * @author lwh
     * @datetime 2020/9/12 17:31
     */
    public boolean hasCycle_opt(ListNode head) {
        if (head == null)
            return false;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }

        return false;
    }

    // endregion

    // region --判断链表是否有环--

    /**
     * @param n 数组长度
     * @param v 目标值
     * @param a 目标数组
     * @return int  第一个大于等于所查值的位置，不存在返回数组长度+1
     * @description 利用二分查找有重复数字的非递减数组中第一个大于等于查找值的位置
     * @author lwh
     * @datetime 2020/9/12 16:55
     */
    public int upper_bound_(int n, int v, int[] a) {
        // 针对所查数字不在数组范围中的情况进行判断返回
        if (v > a[n - 1])
            // 大于最大值，不存在，返回数组长度加1
            return n + 1;
        if (v < a[0])
            // 小于最小值，目标位置即为1
            return 1;

        // 排除特殊情况之后，利用二分进行查找
        return binarySearch(0, n - 1, a, v);
    }

    /**
     * @param start 开始下标
     * @param end   结束下标
     * @param a     目标数组
     * @param v     目标值
     * @return int  第一个大于等于所查值的位置，即下标+1
     * @description 二分查找有重复数字的非递减数组中第一个大于等于查找值的位置
     * 不考虑所查数字不在数组数字范围内的情况，即所查值小于数组中的最小值且大于数组中的最大值的情况
     * 上述情况直接判断输出即可，无需进行递归查找
     * @author lwh
     * @datetime 2020/9/12 16:42
     */
    public int binarySearch(int start, int end, int[] a, int v) {
        // 二分终止条件
        if (start + 1 == end) {
            // 始终返回大于等于目标值的第一个数字位置即可
            if (a[start] == v)
                return start + 1;
            else
                // 此时目标值必定等于右边界或者介于两者之间
                // 不管哪种情况返回值都是end + 1
                return end + 1;
        }

        // 二分向上取整
        int mid = (int) Math.ceil((start + end) / 2.0);

        // 因为要找的是第一个 大于等于 目标值的位置，所以只有最终分为两个数时才能确定
        if (a[mid] >= v) {
            return binarySearch(start, mid, a, v);
        } else {
            return binarySearch(mid, end, a, v);
        }
    }

    // endregion

    // region --寻找数组中第一个大于等于所查元素的位置--

    // 链表结点结构类定义
    static class ListNode {
        public int val;
        public ListNode next = null;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 二叉树结点结构类定义
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    // endregion
}
