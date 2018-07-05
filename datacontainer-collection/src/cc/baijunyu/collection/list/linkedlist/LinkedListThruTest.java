package cc.baijunyu.collection.list.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * LinkedList支持多种遍历方式。建议不要采用随机访问的方式去遍历LinkedList，而采用逐个遍历的方式。
 * -----------------------------Iterator和ListIterator------------------------------
 * Iterator和ListIterator的联系和区别主要有：
 * 一、ListIterator有add()方法，可以向List中添加对象，而Iterator不能。
 * 二、ListIterator和Iterator都有hasNext()和next()方法，可以实现顺序向后遍历。
 * 但是ListIterator有hasPrevious()和previous()方法，可以实现逆向（顺序向前）遍历。Iterator就不可以。
 * 三、ListIterator可以定位当前的索引位置，nextIndex()和previousIndex()可以实现。Iterator 没有此功能。
 * 四、都可实现删除对象，但是ListIterator可以实现对象的修改，set()方法可以实现。Iterator仅能遍历，不能修改。
 * 因为ListIterator的这些功能，可以实现对LinkedList等List数据结构的操作。
 * -------------------------------Iterator设计模式------------------------------
 * Iterator模式也叫迭代模式，是行为模式之一，它把对容器中包含的内部对象的访问委让给外部类，使用Iterator按顺序进行遍历访问的设计模式。
 * -------------------------------性能对比--------------------------------------
 * iteratorLinkedListThruIterator：8 ms
 * iteratorLinkedListThruForeach：3724 ms
 * iteratorThroughFor2：5 ms
 * iteratorThroughPollFirst：8 ms
 * iteratorThroughPollLast：6 ms
 * iteratorThroughRemoveFirst：2 ms
 * iteratorThroughRemoveLast：2 ms
 * <p>
 * 由此可见，遍历LinkedList时，使用removeFist()或removeLast()效率最高。但用它们遍历时，会删除原始数据；
 * 若单纯只读取，而不删除，应该使用第3种遍历方式。
 * 无论如何，千万不要通过随机访问去遍历LinkedList！
 */
public class LinkedListThruTest {
    public static void main(String[] args) {

    }

    private static LinkedList getLinkedList() {
        LinkedList<Integer> llist = new LinkedList<>();
        for (int i = 0; i < 100000; i++)
            llist.addLast(i);
        return llist;
    }

    /**
     * 通过快迭代器遍历LinkedList
     */
    private static void iteratorLinkedListThruIterator(LinkedList<Integer> list) {
        if (list == null)
            return;

        // 记录开始时间
        long start = System.currentTimeMillis();
        //ListIterator和Iterator的区别(返回类型object、真实类型<E>、强转)
        //ListIterator<Integer> integerListIterator = list.listIterator();
        //Iterator<Integer> iterator = list.iterator();
        for (Iterator iter = list.iterator(); iter.hasNext(); )
            iter.next();

        // 记录结束时间
        long end = System.currentTimeMillis();
        long interval = end - start;
        System.out.println("iteratorLinkedListThruIterator：" + interval + " ms");
    }

    /**
     * 通过快速随机访问遍历LinkedList
     */
    private static void iteratorLinkedListThruForeach(LinkedList<Integer> list) {
        if (list == null)
            return;

        // 记录开始时间
        long start = System.currentTimeMillis();

        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i);
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        long interval = end - start;
        System.out.println("iteratorLinkedListThruForeach：" + interval + " ms");
    }

    /**
     * 通过另外一种for循环来遍历LinkedList
     */
    private static void iteratorThroughFor2(LinkedList<Integer> list) {
        if (list == null)
            return;

        // 记录开始时间
        long start = System.currentTimeMillis();

        for (Integer integ : list)

            ;

        // 记录结束时间
        long end = System.currentTimeMillis();
        long interval = end - start;
        System.out.println("iteratorThroughFor2：" + interval + " ms");
    }

    /**
     * 通过pollFirst()来遍历LinkedList
     */
    private static void iteratorThroughPollFirst(LinkedList<Integer> list) {
        if (list == null)
            return;

        // 记录开始时间
        long start = System.currentTimeMillis();
        while (list.pollFirst() != null)
            ;

        // 记录结束时间
        long end = System.currentTimeMillis();
        long interval = end - start;
        System.out.println("iteratorThroughPollFirst：" + interval + " ms");
    }

    /**
     * 通过pollLast()来遍历LinkedList
     */
    private static void iteratorThroughPollLast(LinkedList<Integer> list) {
        if (list == null)
            return;

        // 记录开始时间
        long start = System.currentTimeMillis();
        while (list.pollLast() != null)
            ;

        // 记录结束时间
        long end = System.currentTimeMillis();
        long interval = end - start;
        System.out.println("iteratorThroughPollLast：" + interval + " ms");
    }

    /**
     * 通过removeFirst()来遍历LinkedList
     */
    private static void iteratorThroughRemoveFirst(LinkedList<Integer> list) {
        if (list == null)
            return;

        // 记录开始时间
        long start = System.currentTimeMillis();
        try {
            while (list.removeFirst() != null)
                ;
        } catch (NoSuchElementException e) {
        }

        // 记录结束时间
        long end = System.currentTimeMillis();
        long interval = end - start;
        System.out.println("iteratorThroughRemoveFirst：" + interval + " ms");
    }

    /**
     * 通过removeLast()来遍历LinkedList
     */
    private static void iteratorThroughRemoveLast(LinkedList<Integer> list) {
        if (list == null)
            return;

        // 记录开始时间
        long start = System.currentTimeMillis();
        try {
            while (list.removeLast() != null)
                ;
        } catch (NoSuchElementException e) {
        }

        // 记录结束时间
        long end = System.currentTimeMillis();
        long interval = end - start;
        System.out.println("iteratorThroughRemoveLast：" + interval + " ms");
    }
}
