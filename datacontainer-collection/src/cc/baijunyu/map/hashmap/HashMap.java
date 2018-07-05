package cc.baijunyu.map.hashmap;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * HashMap 是一个散列表，它存储的内容是键值对(key-value)映射。
 * HashMap 继承于AbstractMap，实现了Map、Cloneable、java.io.Serializable接口。
 * HashMap 的实现不是同步的，这意味着它不是线程安全的。它的key、value都可以为null。此外，HashMap中的映射不是有序的。
 * ------------------------------------------------------------------------------------------------------------------------------
 * HashMap 的实例有两个参数影响其性能：“初始容量” 和 “加载因子”。容量 是哈希表中桶的数量，初始容量 只是哈希表在创建时的容量。
 * 加载因子 是哈希表在其容量自动增加之前可以达到多满的一种尺度。当哈希表中的条目数超出了加载因子与当前容量的乘积时，
 * 则要对该哈希表进行 rehash 操作（即重建内部数据结构），从而哈希表将具有大约两倍的桶数。
 * 通常，默认加载因子是 0.75, 这是在时间和空间成本上寻求一种折衷。加载因子过高虽然减少了空间开销，
 * 但同时也增加了查询成本（在大多数 HashMap 类的操作中，包括 get 和 put 操作，都反映了这一点）。
 * 在设置初始容量时应该考虑到映射中所需的条目数及其加载因子，以便最大限度地减少 rehash 操作次数。
 * 如果初始容量大于最大条目数除以加载因子，则不会发生 rehash 操作。
 */
public class HashMap {
    public static void main(String[] args) {
        testHashMapAPIs();
    }

    private static void testHashMapAPIs() {
        // 初始化随机种子
        Random r = new Random();
        // 新建HashMap
        java.util.HashMap<String, Integer> map = new java.util.HashMap<>();

        // HashMap<String,Integer> map = new HashMap<>();
        // 添加操作
        map.put("one", r.nextInt(10));
        map.put("two", r.nextInt(10));
        map.put("three", r.nextInt(10));

        // 打印出map
        System.out.println("map:" + map);

        // 通过Iterator遍历key-value
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println("next : " + entry.getKey() + " - " + entry.getValue());
        }

        // HashMap的键值对个数
        System.out.println("size:" + map.size());

        // containsKey(Object key) :是否包含键key
        System.out.println("contains key two : " + map.containsKey("two"));
        System.out.println("contains key five : " + map.containsKey("five"));

        // containsValue(Object value) :是否包含值value
        System.out.println("contains value 0 : " + map.containsValue(new Integer(0)));

        // remove(Object key) ： 删除键key对应的键值对
        // map.remove("three");

        System.out.println("map:" + map);

        // clear() ： 清空HashMap
        map.clear();

        // isEmpty() : HashMap是否为空
        System.out.println((map.isEmpty() ? "map is empty" : "map is not empty"));
    }

}
