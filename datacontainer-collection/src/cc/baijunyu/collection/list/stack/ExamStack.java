package cc.baijunyu.collection.list.stack;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Stack是栈。它的特性是：先进后出(FILO, First In Last Out)。
 * java工具包中的Stack是继承于Vector(矢量队列)的，由于Vector是通过数组实现的，这就意味着，Stack也是通过数组实现的，而非链表。
 * ----------------------------------api-----------------------------------
 * (01) Stack实际上也是通过数组去实现的。
 * 执行push时(即，将元素推入栈中)，是通过将元素追加的数组的末尾中。
 * 执行peek时(即，取出栈顶元素，不执行删除)，是返回数组末尾的元素。
 * 执行pop时(即，取出栈顶元素，并将该元素从栈中删除)，是取出数组末尾的元素，然后将该元素从数组中删除。
 * (02) Stack继承于Vector，意味着Vector拥有的属性和功能，Stack都拥有。
 */
public class ExamStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        // 将1,2,3,4,5添加到栈中
        for (int i = 1; i < 6; i++) {
            stack.push(String.valueOf(i));
        }

        // 遍历并打印出该栈
        iteratorThroughRandomAccess(stack);

        // 查找“2”在栈中的位置，并输出
        int pos = stack.search("2");
        System.out.println("the postion of 2 is:" + pos);

        // pop栈顶元素之后，遍历栈
        stack.pop();
        iteratorThroughRandomAccess(stack);

        // peek栈顶元素之后，遍历栈
        String val = (String) stack.peek();
        System.out.println("peek:" + val);
        iteratorThroughRandomAccess(stack);

        // 通过Iterator去遍历Stack
        iteratorThroughIterator(stack);
    }

    /**
     * 通过快速访问遍历Stack
     */
    public static void iteratorThroughRandomAccess(List list) {
        String val = null;
        for (int i = 0; i < list.size(); i++) {
            val = (String) list.get(i);
            System.out.print(val + " ");
        }
        System.out.println();
    }

    /**
     * 通过迭代器遍历Stack
     */
    public static void iteratorThroughIterator(List list) {

        String val = null;
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            val = (String) iter.next();
            System.out.print(val + " ");
        }
        System.out.println();
    }

}
