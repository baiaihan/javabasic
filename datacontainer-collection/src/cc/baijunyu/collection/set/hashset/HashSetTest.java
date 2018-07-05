package cc.baijunyu.collection.set.hashset;

import java.util.Iterator;
import java.util.HashSet;

/**
 * HashSet 是一个没有重复元素的集合。
 * 它是由HashMap实现的，不保证元素的顺序，而且HashSet允许使用 null 元素。
 * HashSet是非同步的。如果多个线程同时访问一个哈希 set，而其中至少一个线程修改了该 set，
 * 那么它必须 保持外部同步。这通常是通过对自然封装该 set 的对象执行同步操作来完成的。
 * 如果不存在这样的对象，则应该使用 Collections.synchronizedSet 方法来“包装” set。
 * 最好在创建时完成这一操作，以防止对该 set 进行意外的不同步访问：
 * Set s = Collections.synchronizedSet(new HashSet(...));
 * HashSet通过iterator()返回的迭代器是fail-fast的。
 * ---------------------------------数据结构------------------------------------
 * (01) HashSet继承于AbstractSet，并且实现了Set接口。
 * (02) HashSet的本质是一个"没有重复元素"的集合，它是通过HashMap实现的。
 * HashSet中含有一个"HashMap类型的成员变量"map，HashSet的操作函数，实际上都是通过map实现的。
 * ---------------------------------api--------------------------------------
 *
 * @desc HashSet常用API的使用。
 */
public class HashSetTest {

    public static void main(String[] args) {
        // HashSet常用API
        testHashSetAPIs();
    }

    /*
     * HashSet除了iterator()和add()之外的其它常用API
     */
    private static void testHashSetAPIs() {
        // 新建HashSet
        HashSet set = new HashSet();

        // 将元素添加到Set中
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");

        // 打印HashSet的实际大小
        System.out.printf("size : %d\n", set.size());

        // 判断HashSet是否包含某个值
        System.out.printf("HashSet contains a :%s\n", set.contains("a"));
        System.out.printf("HashSet contains g :%s\n", set.contains("g"));

        // 删除HashSet中的“e”
        set.remove("e");

        // 将Set转换为数组
        String[] arr = (String[]) set.toArray(new String[0]);
        for (String str : arr)
            System.out.printf("for each : %s\n", str);

        // 新建一个包含b、c、f的HashSet
        HashSet otherset = new HashSet();
        otherset.add("b");
        otherset.add("c");
        otherset.add("f");

        // 克隆一个removeset，内容和set一模一样
        HashSet removeset = (HashSet) set.clone();
        // 删除“removeset中，属于otherSet的元素”
        removeset.removeAll(otherset);
        // 打印removeset
        System.out.printf("removeset : %s\n", removeset);

        // 克隆一个retainset，内容和set一模一样
        HashSet retainset = (HashSet) set.clone();
        // 保留“retainset中，属于otherSet的元素”
        retainset.retainAll(otherset);
        // 打印retainset
        System.out.printf("retainset : %s\n", retainset);


        // 遍历HashSet
        for (Iterator iterator = set.iterator();
             iterator.hasNext(); )
            System.out.printf("iterator : %s\n", iterator.next());

        // 清空HashSet
        set.clear();

        // 输出HashSet是否为空
        System.out.printf("%s\n", set.isEmpty() ? "set is empty" : "set is not empty");
    }

}
