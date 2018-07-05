package cc.baijunyu.map.treemap;

/**
 * TreeMap 是一个有序的key-value集合，它是通过红黑树实现的，TreeMap的排序是基于对key的排序。
 * TreeMap 继承于AbstractMap，所以它是一个Map，即一个key-value集合。
 * TreeMap 实现了NavigableMap接口，意味着它支持一系列的导航方法。比如返回有序的key集合。
 * TreeMap 实现了Cloneable接口，意味着它能被克隆。
 * TreeMap 实现了java.io.Serializable接口，意味着它支持序列化。
 * <p>
 * TreeMap基于红黑树（Red-Black tree）实现。该映射根据其键的自然顺序进行排序，或者根据创建映射时提供的 Comparator 进行排序，
 * 具体取决于使用的构造方法。
 * TreeMap的基本操作 containsKey、get、put 和 remove 的时间复杂度是 log(n) 。
 * 另外，TreeMap是非同步的。 它的iterator 方法返回的迭代器是fail-fastl的。
 * -------------------------------------------------数据结构---------------------------------------------------
 * (01) TreeMap实现继承于AbstractMap，并且实现了NavigableMap接口。
 * (02) TreeMap的本质是R-B Tree(红黑树)，它包含几个重要的成员变量： root, size, comparator。
 * 　　root 是红黑数的根节点。它是Entry类型，Entry是红黑数的节点，
 * 它包含了红黑数的6个基本组成成分：key(键)、value(值)、left(左孩子)、right(右孩子)、parent(父节点)、color(颜色)。
 * Entry节点根据key进行排序，Entry节点包含的内容为value。
 * 　　红黑数排序时，根据Entry中的key进行排序；Entry中的key比较大小是根据比较器comparator来进行判断的。
 * 　　size是红黑数中节点的个数。
 * -------------------------------------------------红黑树------------------------------------------------------
 * 红黑树（Red Black Tree） 是一种自平衡二叉查找树，是在计算机科学中用到的一种数据结构，典型的用途是实现关联数组。
 * 红黑树和AVL树类似，都是在进行插入和删除操作时通过特定操作保持二叉查找树的平衡，从而获得较高的查找性能。
 */
import java.util.*;

/**
 * @desc TreeMap测试程序
 *
 */
public class TreeMapTest  {

    public static void main(String[] args) {
        // 测试常用的API
        testTreeMapOridinaryAPIs();

        // 测试TreeMap的导航函数
        //testNavigableMapAPIs();

        // 测试TreeMap的子Map函数
        //testSubMapAPIs();
    }

    /**
     * 测试常用的API
     */
    private static void testTreeMapOridinaryAPIs() {
        // 初始化随机种子
        Random r = new Random();
        // 新建TreeMap
        TreeMap tmap = new TreeMap();
        // 添加操作
        tmap.put("one", r.nextInt(10));
        tmap.put("two", r.nextInt(10));
        tmap.put("three", r.nextInt(10));

        System.out.printf("\n ---- testTreeMapOridinaryAPIs ----\n");
        // 打印出TreeMap
        System.out.printf("%s\n",tmap );

        // 通过Iterator遍历key-value
        Iterator iter = tmap.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            System.out.printf("next : %s - %s\n", entry.getKey(), entry.getValue());
        }

        // TreeMap的键值对个数
        System.out.printf("size: %s\n", tmap.size());

        // containsKey(Object key) :是否包含键key
        System.out.printf("contains key two : %s\n",tmap.containsKey("two"));
        System.out.printf("contains key five : %s\n",tmap.containsKey("five"));

        // containsValue(Object value) :是否包含值value
        System.out.printf("contains value 0 : %s\n",tmap.containsValue(new Integer(0)));

        // remove(Object key) ： 删除键key对应的键值对
        tmap.remove("three");

        System.out.printf("tmap:%s\n",tmap );

        // clear() ： 清空TreeMap
        tmap.clear();

        // isEmpty() : TreeMap是否为空
        System.out.printf("%s\n", (tmap.isEmpty()?"tmap is empty":"tmap is not empty") );
    }


    /**
     * 测试TreeMap的子Map函数
     */
    public static void testSubMapAPIs() {
        // 新建TreeMap
        TreeMap tmap = new TreeMap();
        // 添加“键值对”
        tmap.put("a", 101);
        tmap.put("b", 102);
        tmap.put("c", 103);
        tmap.put("d", 104);
        tmap.put("e", 105);

        System.out.printf("\n ---- testSubMapAPIs ----\n");
        // 打印出TreeMap
        System.out.printf("tmap:\n\t%s\n", tmap);

        // 测试 headMap(K toKey)
        System.out.printf("tmap.headMap(\"c\"):\n\t%s\n", tmap.headMap("c"));
        // 测试 headMap(K toKey, boolean inclusive)
        System.out.printf("tmap.headMap(\"c\", true):\n\t%s\n", tmap.headMap("c", true));
        System.out.printf("tmap.headMap(\"c\", false):\n\t%s\n", tmap.headMap("c", false));

        // 测试 tailMap(K fromKey)
        System.out.printf("tmap.tailMap(\"c\"):\n\t%s\n", tmap.tailMap("c"));
        // 测试 tailMap(K fromKey, boolean inclusive)
        System.out.printf("tmap.tailMap(\"c\", true):\n\t%s\n", tmap.tailMap("c", true));
        System.out.printf("tmap.tailMap(\"c\", false):\n\t%s\n", tmap.tailMap("c", false));

        // 测试 subMap(K fromKey, K toKey)
        System.out.printf("tmap.subMap(\"a\", \"c\"):\n\t%s\n", tmap.subMap("a", "c"));
        // 测试
        System.out.printf("tmap.subMap(\"a\", true, \"c\", true):\n\t%s\n",
                tmap.subMap("a", true, "c", true));
        System.out.printf("tmap.subMap(\"a\", true, \"c\", false):\n\t%s\n",
                tmap.subMap("a", true, "c", false));
        System.out.printf("tmap.subMap(\"a\", false, \"c\", true):\n\t%s\n",
                tmap.subMap("a", false, "c", true));
        System.out.printf("tmap.subMap(\"a\", false, \"c\", false):\n\t%s\n",
                tmap.subMap("a", false, "c", false));

        // 测试 navigableKeySet()
        System.out.printf("tmap.navigableKeySet():\n\t%s\n", tmap.navigableKeySet());
        // 测试 descendingKeySet()
        System.out.printf("tmap.descendingKeySet():\n\t%s\n", tmap.descendingKeySet());
    }

    /**
     * 测试TreeMap的导航函数
     */
    public static void testNavigableMapAPIs() {
        // 新建TreeMap
        NavigableMap nav = new TreeMap();
        // 添加“键值对”
        nav.put("aaa", 111);
        nav.put("bbb", 222);
        nav.put("eee", 333);
        nav.put("ccc", 555);
        nav.put("ddd", 444);

        System.out.printf("\n ---- testNavigableMapAPIs ----\n");
        // 打印出TreeMap
        System.out.printf("Whole list:%s%n", nav);

        // 获取第一个key、第一个Entry
        System.out.printf("First key: %s\tFirst entry: %s%n",nav.firstKey(), nav.firstEntry());

        // 获取最后一个key、最后一个Entry
        System.out.printf("Last key: %s\tLast entry: %s%n",nav.lastKey(), nav.lastEntry());

        // 获取“小于/等于bbb”的最大键值对
        System.out.printf("Key floor before bbb: %s%n",nav.floorKey("bbb"));

        // 获取“小于bbb”的最大键值对
        System.out.printf("Key lower before bbb: %s%n", nav.lowerKey("bbb"));

        // 获取“大于/等于bbb”的最小键值对
        System.out.printf("Key ceiling after ccc: %s%n",nav.ceilingKey("ccc"));

        // 获取“大于bbb”的最小键值对
        System.out.printf("Key higher after ccc: %s%n\n",nav.higherKey("ccc"));
    }

}