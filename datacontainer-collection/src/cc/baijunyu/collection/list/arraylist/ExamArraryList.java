package cc.baijunyu.collection.list.arraylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/**
 * ArrayList 是一个数组队列，相当于 动态数组。与Java中的数组相比，它的容量能动态增长。它继承于AbstractList，实现了List,
 * RandomAccess, Cloneable, java.io.Serializable这些接口。
 * ArrayList 继承了AbstractList，实现了List。它是一个数组队列，提供了相关的添加、删除、修改、遍历等功能。
 * ArrayList 实现了RandmoAccess接口，即提供了随机访问功能。RandmoAccess是java中用来被List实现，
 * 为List提供快速访问功能的。在ArrayList中，我们即可以通过元素的序号快速获取元素对象；
 * 这就是快速随机访问。“快速随机访问”和“通过Iterator迭代器访问”的效率比较。
 * ArrayList 实现了Cloneable接口，即覆盖了函数clone()，能被克隆。
 * ArrayList 实现java.io.Serializable接口，这意味着ArrayList支持序列化，能通过序列化去传输。
 * 和Vector不同，ArrayList中的操作不是线程安全的！所以，建议在单线程中才使用ArrayList，而在多线程中可以选择Vector或者CopyOnWriteArrayList。
 * ------------------------------数据结构---------------------------------
 * (01) ArrayList 实际上是通过一个数组去保存数据的。当我们构造ArrayList时；若使用默认构造函数，则ArrayList的默认容量大小是10。
 * (02) 当ArrayList容量不足以容纳全部元素时，ArrayList会重新设置容量：新的容量=“(原始容量x3)/2 + 1”。
 * (03) ArrayList的克隆函数，即是将全部元素克隆到一个数组中。
 * (04) ArrayList实现java.io.Serializable的方式。当写入到输出流时，先写入“容量”，再依次写入“每一个元素”；
 * 当读出输入流时，先读取“容量”，再依次读取“每一个元素”。
 * ArrayList包含了两个重要的对象：elementData 和 size。
 * (01) elementData 是"Object[]类型的数组"，它保存了添加到ArrayList中的元素。
 * 实际上，elementData是个动态数组，我们能通过构造函数 ArrayList(int initialCapacity)来执行它的初始容量为initialCapacity；
 * (02) size 则是动态数组的实际大小。
 * -----------------------------------应用场景----------------------------
 * iteratorThroughRandomAccess：3 ms
 * iteratorThroughIterator：8 ms
 * iteratorThroughFor2：5 ms
 * <p>
 * 由此可见，遍历ArrayList时，使用随机访问(即，通过索引序号访问)效率最高，而使用迭代器的效率最低！
 */
public class ExamArraryList {

    public static void main(String[] args) {

    }

    public static ArrayList getArraryList() {
        ArrayList<Integer> llist = new ArrayList<>();
        for (int i = 0; i < 100000; i++)
            llist.add(i);
        return llist;
    }

    /**
     * 随机访问，通过索引值去遍历
     *
     * @param list
     */
    public static void iteratorThroughRandomAccess(List list) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughRandomAccess：" + interval + " ms");
    }

    /**
     * RandmoAccess是java中用来被List实现，为List提供快速访问功能的。
     * @param list
     */
    private static void isRandomAccessSupported(List list) {
        if (list instanceof RandomAccess) {
            System.out.println("RandomAccess implemented!");
        } else {
            System.out.println("RandomAccess not implemented!");
        }

    }

    /**
     * 迭代器
     *
     * @param list
     */
    public static void iteratorThroughIterator(List list) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            iter.next();
        }
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughIterator：" + interval + " ms");
    }

    /**
     * for循环遍历
     *
     * @param list
     */
    public static void iteratorThroughFor2(List list) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (Object obj : list)
            ;
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughFor2：" + interval + " ms");
    }

    /**
     * 调用 toArray() 函数会抛出“java.lang.ClassCastException”异常，但是调用 toArray(T[] contents) 能正常返回 T[]。
     * toArray() 会抛出异常是因为 toArray() 返回的是 Object[] 数组，将 Object[] 转换为其它类型(如如，
     * 将Object[]转换为的Integer[])则会抛出“java.lang.ClassCastException”异常，因为Java不支持向下转型。
     * -------------------------数组转换---------------------------------------
     * 第一种方法和第三种方法都是利用了toArray(T[] contents)方法中的System.arraycopy语句进行数组的拷贝。由于System.arraycopy直接改变了
     * 目标数组引用的内容，因此可以像方法一一样，不需要定义toArray方法的返回值，再将返回值返回，而是可以直接返回目标数组。
     * 方法二则是使用了toArray(T[] contents)方法中Array.copyOf语句重新创建了一个目标类型的数组。
     * 由于方法二的语句更加简洁，因此方法二更为常用。
     */
    // toArray(T[] contents)调用方式一
    public static Integer[] vectorToArray1(ArrayList<Integer> v) {
        Integer[] newText = new Integer[v.size()];
        v.toArray(newText);
        return newText;
    }

    // toArray(T[] contents)调用方式二。最常用！
    public static Integer[] vectorToArray2(ArrayList<Integer> v) {
        Integer[] newText = (Integer[]) v.toArray(new Integer[0]);
        return newText;
    }

    // toArray(T[] contents)调用方式三
    public static Integer[] vectorToArray3(ArrayList<Integer> v) {
        Integer[] newText = new Integer[v.size()];
        Integer[] newStrings = (Integer[]) v.toArray(newText);
        return newStrings;
    }
}
