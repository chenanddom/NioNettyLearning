package com.itdom.codec;

import com.itdom.struct.Customer;

import java.util.*;

public class A {
    protected int a = 10;
    public static Comparator<Customer> idComparator = new Comparator<Customer>() {

        @Override
        public int compare(Customer o1, Customer o2) {
            return (int) (o1.getId() - o2.getId());
        }
    };

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("B");
        set.add("C");
        set.add("A");
        set.add("A");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String element = iterator.next();
            System.out.println(element);
        }

        System.out.println("--------------------------------------------------");
        /**
         * 保证元素的顺序
         */
       Set<String> set2 = new LinkedHashSet<String>();
        set2.add("B");
        set2.add("C");
        set2.add("A");
        Iterator<String> iterator2 = set2.iterator();
        while (iterator2.hasNext()){
            String element = iterator2.next();
            System.out.println(element);
        }
        System.out.println("--------------------------------------------------");

        Set<String> set3 = new TreeSet<String>();

        set3.add("B");
        set3.add("C");
        set3.add("A");

        Iterator<String> iterator3 = set3.iterator();
        while (iterator3.hasNext()){
            String element = iterator3.next();
            System.out.println(element);
        }







      /*  Map<String,String> map = new HashMap<>();
        map.put("a","1");
        map.put("a","2");
        map.put("a","3");
        map.put("a","4");
        System.out.println(map.size());

        Map<String,String> map2 = new HashMap<>();
        map2.put(new String("a"),"1");
        map2.put(new String("a"),"2");
        map2.put(new String("a"),"3");
        map2.put(new String("a"),"4");
        System.out.println(map2.size());


        Map<Customer,String> map3 = new HashMap<>();
        map3.put(new Customer(1,"a"),"1");
        map3.put(new Customer(1,"a"),"2");
        map3.put(new Customer(1,"a"),"3");
        map3.put(new Customer(1,"a"),"4");
        System.out.println(map3.size());

        Map<String,String> map4 = new IdentityHashMap<>();
        map4.put("a","1");
        map4.put("a","2");
        map4.put("a","3");
        map4.put("a","4");
        System.out.println(map4.size());


        Map<String,String> map5 = new HashMap<>();
        map5.put(new String("a"),"1");
        map5.put(new String("a"),"2");
        map5.put(new String("a"),"3");
        map5.put(new String("a"),"4");
        System.out.println(map5.size());*/


        /*PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(7);

        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            priorityQueue.add(new Integer(random.nextInt(100)));
        }
        while (priorityQueue.size() > 0) {
            Integer element = priorityQueue.poll();
            System.out.println(element);
        }
        System.out.println("------------------------------------------------------------------------");


        Queue<Customer> customerQueue = new PriorityQueue<>(7, idComparator);

        addDataToQueue(customerQueue);
        pollDataFromQueue(customerQueue);*/



        /*
        Queue<String> queue = new LinkedList();
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        queue.offer("D");
        queue.offer("E");

        while (queue.size() > 0) {
            String poll = queue.poll();
            System.out.println(poll);
        }
*/

    }

    /**
     * 向队列增数据的通用的方法
     *
     * @param customerQueue
     */
    private static void addDataToQueue(Queue<Customer> customerQueue) {
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            int id = random.nextInt(100);
            System.out.println("ID:" + id);
            customerQueue.add(new Customer(id, "Pankaj" + id));
        }
    }

    /**
     * 用于从对了获取通用的数据的方法
     * @param customerQueue
     */
    private static void pollDataFromQueue(Queue<Customer> customerQueue) {
        while (true) {
            Customer poll = customerQueue.poll();
            if (poll == null) {
                break;
            }
            System.out.println("Processing Customer with ID=" + poll.getId());
        }
    }
}
