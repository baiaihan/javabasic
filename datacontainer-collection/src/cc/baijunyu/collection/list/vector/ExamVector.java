package cc.baijunyu.collection.list.vector;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Vector 是矢量队列，它是JDK1.0版本添加的类。继承于AbstractList，实现了List, RandomAccess, Cloneable这些接口。
 * Vector 继承了AbstractList，实现了List；所以，它是一个队列，支持相关的添加、删除、修改、遍历等功能。
 * Vector 实现了RandmoAccess接口，即提供了随机访问功能。RandmoAccess是java中用来被List实现，为List提供快速访问功能的。
 * 在Vector中，我们即可以通过元素的序号快速获取元素对象；这就是快速随机访问。
 * Vector 实现了Cloneable接口，即实现clone()函数。它能被克隆。
 * 和ArrayList不同，Vector中的操作是线程安全的。
 * -------------------------------------数据结构-------------------------------------------
 * Vector的数据结构和ArrayList差不多，它包含了3个成员变量：elementData , elementCount， capacityIncrement。
 * (01) elementData 是"Object[]类型的数组"，它保存了添加到Vector中的元素。elementData是个动态数组，如果初始化Vector时，
 * 没指定动态数组的>大小，则使用默认大小10。随着Vector中元素的增加，Vector的容量也会动态增长，capacityIncrement是与容量增长相关的增长系数，
 * 具体的增长方式，请参考源码分析中的ensureCapacity()函数。
 * (02) elementCount 是动态数组的实际大小。
 * (03) capacityIncrement 是动态数组的增长系数。如果在创建Vector时，
 * 指定了capacityIncrement的大小；则，每次当Vector中动态数组容量增加时>，增加的大小都是capacityIncrement。
 */
public class ExamVector {
    public static void main(String[] args) {
        // 新建Vector
        Vector vec = new Vector();

        // 添加元素
        vec.add("1");
        vec.add("2");
        vec.add("3");
        vec.add("4");
        vec.add("5");

        // 设置第一个元素为100
        vec.set(0, "100");
        // 将“500”插入到第3个位置
        vec.add(2, "300");
        System.out.println("vec:" + vec);

        // (顺序查找)获取100的索引
        System.out.println("vec.indexOf(100):" + vec.indexOf("100"));
        // (倒序查找)获取100的索引
        System.out.println("vec.lastIndexOf(100):" + vec.lastIndexOf("100"));
        // 获取第一个元素
        System.out.println("vec.firstElement():" + vec.firstElement());
        // 获取第3个元素
        System.out.println("vec.elementAt(2):" + vec.elementAt(2));
        // 获取最后一个元素
        System.out.println("vec.lastElement():" + vec.lastElement());

        // 获取Vector的大小
        System.out.println("size:" + vec.size());
        // 获取Vector的总的容量
        System.out.println("capacity:" + vec.capacity());

        // 获取vector的“第2”到“第4”个元素
        System.out.println("vec 2 to 4:" + vec.subList(1, 4));

        // 通过Enumeration遍历Vector
        Enumeration enu = vec.elements();
        while (enu.hasMoreElements())
            System.out.println("nextElement():" + enu.nextElement());

        Vector retainVec = new Vector();
        retainVec.add("100");
        retainVec.add("300");
        // 获取“vec”中包含在“retainVec中的元素”的集合
        System.out.println("vec.retain():" + vec.retainAll(retainVec));
        System.out.println("vec:" + vec);

        // 获取vec对应的String数组
        String[] arr = (String[]) vec.toArray(new String[0]);
        for (String str : arr)
            System.out.println("str:" + str);

        // 清空Vector。clear()和removeAllElements()一样！
        vec.clear();
        //        vec.removeAllElements();

        // 判断Vector是否为空
        System.out.println("vec.isEmpty():" + vec.isEmpty());
    }
}
