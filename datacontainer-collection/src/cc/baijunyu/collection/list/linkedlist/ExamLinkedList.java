package cc.baijunyu.collection.list.linkedlist;


import java.util.LinkedList;

/**
 * LinkedList既是List，也是Queue(Deque)，其原因是它是双向的，内部的元素(Entry)同时保留了上一个和下一个元素的引用。
 * 继承于AbstractSequentialList的双向链表。它也可以被当作堆栈、队列或双端队列进行操作。
 * 实现 List 接口，能对它进行队列操作。
 * 实现 Deque 接口，即能将LinkedList当作双端队列使用。
 * 实现了Cloneable接口，即覆盖了函数clone()，能克隆。
 * 实现java.io.Serializable接口，这意味着LinkedList支持序列化，能通过序列化去传输。
 * 是非同步的。
 * ------------------------------数据结构---------------------------------
 * LinkedList的本质是双向链表。
 * (01) LinkedList继承于AbstractSequentialList，并且实现了Dequeue接口。
 * (02) LinkedList包含两个重要的成员：header 和 size。
 * 　　header是双向链表的表头，它是双向链表节点所对应的类Entry的实例。Entry中包含成员变量：
 * previous, next, element。其中，previous是该节点的上一个节点，next是该节点的下一个节点，element是该节点所包含的值。
 * 　　size是双向链表中节点的个数。
 * LinkedList实际上是通过双向链表去实现的。既然是双向链表，那么它的顺序访问会非常高效，而随机访问效率比较低。
 * 既然LinkedList是通过双向链表的，但是它也实现了List接口{也就是说，它实现了get(int location)、remove(int location)等
 * “根据索引值来获取、删除节点的函数”}。LinkedList是如何实现List的这些接口的，如何将“双向链表和索引值联系起来的”？
 * 实际原理非常简单，它就是通过一个计数索引值来实现的。例如，当我们调用get(int location)时，首先会比较“location”和
 * “双向链表长度的1/2”；若前者大，则从链表头开始往后查找，直到location位置；否则，从链表末尾开始先前查找，直到location位置。
 * 这就是“双线链表和索引值联系起来”的方法。
 * -----------------------------------应用场景----------------------------
 * 和数组不同，根据下标查找特定元素时，只能遍历地获取了，因而在随机访问时效率不如ArrayList。
 * ----------------------------------等价操作-------------------------------
 * LinkedList可以作为FIFO(先进先出)的队列，作为FIFO的队列时，下表的方法等价：
 * <p>
 * 队列方法       等效方法
 * add(e)        addLast(e)
 * offer(e)      offerLast(e)
 * remove()      removeFirst()
 * poll()        pollFirst()
 * element()     getFirst()
 * peek()        peekFirst()
 * <p>
 * LinkedList可以作为LIFO(后进先出)的栈，作为LIFO的栈时，下表的方法等价：
 * <p>
 * 栈方法        等效方法
 * push(e)      addFirst(e)
 * pop()        removeFirst()
 * peek()       peekFirst()
 */

public class ExamLinkedList {

    public static void main(String[] args) {
        // 测试LinkedList的API
        testLinkedListAPIs();

        // 将LinkedList当作 LIFO(后进先出)的堆栈
        useLinkedListAsLIFO();

        // 将LinkedList当作 FIFO(先进先出)的队列
        useLinkedListAsFIFO();
    }


    /*
     * 测试LinkedList中部分API
     */
    private static void testLinkedListAPIs() {

        //linkedlist llist;
        //llist.offer("10");
        // 新建一个LinkedList
        LinkedList<String> llist = new LinkedList<>();
        //---- 添加操作 ----
        // 依次添加1,2,3
        llist.add("1");
        llist.add("2");
        llist.add("3");

        // 将“4”添加到第一个位置
        llist.add(1, "4");


        System.out.println("\nTest \"addFirst(), removeFirst(), getFirst()\"");
        // (01) 将“10”添加到第一个位置。  失败的话，抛出异常！
        llist.addFirst("10");
        System.out.println("llist:" + llist);
        // (02) 将第一个元素删除。        失败的话，抛出异常！
        System.out.println("llist.removeFirst():" + llist.removeFirst());
        System.out.println("llist:" + llist);
        // (03) 获取第一个元素。          失败的话，抛出异常！
        System.out.println("llist.getFirst():" + llist.getFirst());


        System.out.println("\nTest \"offerFirst(), pollFirst(), peekFirst()\"");
        // (01) 将“10”添加到第一个位置。  返回true。
        llist.offerFirst("10");
        System.out.println("llist:" + llist);
        // (02) 将第一个元素删除。        失败的话，返回null。
        System.out.println("llist.pollFirst():" + llist.pollFirst());
        System.out.println("llist:" + llist);
        // (03) 获取第一个元素。          失败的话，返回null。
        System.out.println("llist.peekFirst():" + llist.peekFirst());


        System.out.println("\nTest \"addLast(), removeLast(), getLast()\"");
        // (01) 将“20”添加到最后一个位置。  失败的话，抛出异常！
        llist.addLast("20");
        System.out.println("llist:" + llist);
        // (02) 将最后一个元素删除。        失败的话，抛出异常！
        System.out.println("llist.removeLast():" + llist.removeLast());
        System.out.println("llist:" + llist);
        // (03) 获取最后一个元素。          失败的话，抛出异常！
        System.out.println("llist.getLast():" + llist.getLast());


        System.out.println("\nTest \"offerLast(), pollLast(), peekLast()\"");
        // (01) 将“20”添加到第一个位置。  返回true。
        llist.offerLast("20");
        System.out.println("llist:" + llist);
        // (02) 将第一个元素删除。        失败的话，返回null。
        System.out.println("llist.pollLast():" + llist.pollLast());
        System.out.println("llist:" + llist);
        // (03) 获取第一个元素。          失败的话，返回null。
        System.out.println("llist.peekLast():" + llist.peekLast());


        // 将第3个元素设置300。不建议在LinkedList中使用此操作，因为效率低！
        llist.set(2, "300");
        // 获取第3个元素。不建议在LinkedList中使用此操作，因为效率低！
        System.out.println("\nget(3):" + llist.get(2));


        // ---- toArray(T[] a) ----
        // 将LinkedList转行为数组
        String[] arr = (String[]) llist.toArray(new String[0]);
        for (String str : arr)
            System.out.println("str:" + str);

        // 输出大小
        System.out.println("size:" + llist.size());
        // 清空LinkedList
        llist.clear();
        // 判断LinkedList是否为空
        System.out.println("isEmpty():" + llist.isEmpty() + "\n");

    }

    /**
     * 将LinkedList当作 LIFO(后进先出)的堆栈
     */
    private static void useLinkedListAsLIFO() {
        System.out.println("\nuseLinkedListAsLIFO");
        // 新建一个LinkedList
        LinkedList<String> stack = new LinkedList<>();

        // 将1,2,3,4添加到堆栈中
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        // 打印“栈”
        System.out.println("stack:" + stack);

        // 删除“栈顶元素”
        System.out.println("stack.pop():" + stack.pop());

        // 取出“栈顶元素”
        System.out.println("stack.peek():" + stack.peek());

        // 打印“栈”
        System.out.println("stack:" + stack);
    }

    /**
     * 将LinkedList当作 FIFO(先进先出)的队列
     */
    private static void useLinkedListAsFIFO() {
        System.out.println("\nuseLinkedListAsFIFO");
        // 新建一个LinkedList
        LinkedList queue = new LinkedList();

        // 将10,20,30,40添加到队列。每次都是插入到末尾
        queue.add("10");
        queue.add("20");
        queue.add("30");
        queue.add("40");
        // 打印“队列”
        System.out.println("queue:" + queue);

        // 删除(队列的第一个元素)
        System.out.println("queue.remove():" + queue.remove());

        // 读取(队列的第一个元素)
        System.out.println("queue.element():" + queue.element());

        // 打印“队列”
        System.out.println("queue:" + queue);
    }
}

