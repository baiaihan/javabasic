package cc.baijunyu.map.weakhashmap;


/**
 * (01) 新建WeakHashMap，将“键值对”添加到WeakHashMap中。
 * 实际上，WeakHashMap是通过数组table保存Entry(键值对)；每一个Entry实际上是一个单向链表，即Entry是键值对链表。
 * (02) 当某“弱键”不再被其它对象引用，并被GC回收时。在GC回收该“弱键”时，这个“弱键”也同时会被添加到ReferenceQueue(queue)队列中。
 * (03) 当下一次我们需要操作WeakHashMap时，会先同步table和queue。table中保存了全部的键值对，而queue中保存被GC回收的键值对；
 * 同步它们，就是删除table中被GC回收的键值对。
 * 这就是“弱键”如何被自动从WeakHashMap中删除的步骤了。
 * 和HashMap一样，WeakHashMap是不同步的。可以使用 Collections.synchronizedMap 方法来构造同步的 WeakHashMap。
 */
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Date;
import java.lang.ref.WeakReference;

/**
 * @desc WeakHashMap测试程序
 *
 * @email kuiwu-wang@163.com
 */
public class WeakHashMapTest {

    public static void main(String[] args) throws Exception {
        testWeakHashMapAPIs();
    }

    private static void testWeakHashMapAPIs() {
        // 初始化3个“弱键”
        String w1 = new String("one");
        String w2 = new String("two");
        String w3 = new String("three");
        // 新建WeakHashMap
        Map wmap = new WeakHashMap();

        // 添加键值对
        wmap.put(w1, "w1");
        wmap.put(w2, "w2");
        wmap.put(w3, "w3");

        // 打印出wmap
        System.out.printf("\nwmap:%s\n",wmap );

        // containsKey(Object key) :是否包含键key
        System.out.printf("contains key two : %s\n",wmap.containsKey("two"));
        System.out.printf("contains key five : %s\n",wmap.containsKey("five"));

        // containsValue(Object value) :是否包含值value
        System.out.printf("contains value 0 : %s\n",wmap.containsValue(new Integer(0)));

        // remove(Object key) ： 删除键key对应的键值对
        wmap.remove("three");

        System.out.printf("wmap: %s\n",wmap );



        // ---- 测试 WeakHashMap 的自动回收特性 ----

        // 将w1设置null。
        // 这意味着“弱键”w1再没有被其它对象引用，调用gc时会回收WeakHashMap中与“w1”对应的键值对
        w1 = null;
        // 内存回收。这里，会回收WeakHashMap中与“w1”对应的键值对
        System.gc();

        // 遍历WeakHashMap
        Iterator iter = wmap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry en = (Map.Entry)iter.next();
            System.out.printf("next : %s - %s\n",en.getKey(),en.getValue());
        }
        // 打印WeakHashMap的实际大小
        System.out.printf(" after gc WeakHashMap size:%s\n", wmap.size());
    }
}
