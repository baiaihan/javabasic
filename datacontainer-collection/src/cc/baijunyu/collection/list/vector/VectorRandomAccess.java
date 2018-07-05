package cc.baijunyu.collection.list.vector;

import java.util.*;

/**
 * iteratorThroughRandomAccess：6 ms
 * iteratorThroughIterator：9 ms
 * iteratorThroughFor2：8 ms
 * iteratorThroughEnumeration：7 ms
 * 总结：遍历Vector，使用索引的随机访问方式最快，使用迭代器最慢。
 */
public class VectorRandomAccess {

    public static void main(String[] args) {
        Vector vec = new Vector();
        for (int i = 0; i < 100000; i++)
            vec.add(i);
        iteratorThroughRandomAccess(vec);
        iteratorThroughIterator(vec);
        iteratorThroughFor2(vec);
        iteratorThroughEnumeration(vec);

    }

    /**
     * RandmoAccess是java中用来被List实现，为List提供快速访问功能的。
     *
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
     * 快速随机访问
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
     * 迭代器遍历
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
     * for循环
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
     * Enumeration遍历
     * Enumeration 接口是Iterator迭代器的“古老版本”，从JDK 1.0开始，Enumeration接口就已经存在了（Iterator从JDK 1.2才出现）。
     * Enumeration接口只有两个方法。
     * boolean hasMoreElements()
     * 如果此迭代器还有剩下的元素，则返回true
     * Object nextElement()
     * --------------------------------------tip--------------------------------------------------------
     * Enumeration迭代器只能遍历Vector、Hashtable这种古老的集合，因此通常不要使用它，除非在某些极端情况下，
     * 不得不使用Enumeration，否则都应该选择Iterator迭代器。
     * @param vec
     */
    public static void iteratorThroughEnumeration(Vector vec) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (Enumeration enu = vec.elements(); enu.hasMoreElements(); ) {
            enu.nextElement();
        }
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughEnumeration：" + interval + " ms");
    }
}
