import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 流
 * 操作stream流的三个步骤
 * 1.创建一个数据流 比如，集合流，数组流，无限流
 * Stream<String> stream1 = list.stream();
 * <p>
 * 2.进行中间操作，对数据源的数据进行处理
 * 筛选 / 切片
 * 中间操作：
 *          filter：接收 Lambda ，从流中排除某些元素
 *          limit：截断流，使其元素不超过给定数量
 *          skip(n)：跳过元素，返回一个舍弃了前n个元素的流；若流中元素不足n个，则返回一个空流；与 limit(n) 互补
 *          distinct：筛选，通过流所生成的 hashCode() 与 equals() 取除重复元素
 * <p>
 * 映射
 *          map：接收 Lambda ，将元素转换为其他形式或提取信息；接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
 *          flatMap：接收一个函数(这个函数本身就是一个流)作为参数，将流中每一个值都换成另一个流，然后把所有流重新连接成一个流
 * 排序
 *          sorted()：自然排序
 *          sorted(Comparator c)：定制排序
 * 3.中止操作 ，负责执行中间操作并产生结果 一次性处理全部，惰性求值
 * 查找 / 匹配
 *          allMatch：检查是否匹配所有元素
 *          anyMatch：检查是否至少匹配一个元素
 *          noneMatch：检查是否没有匹配所有元素
 *          findFirst：返回第一个元素
 *          findAny：返回当前流中的任意元素
 *          count：返回流中元素的总个数
 *          max：返回流中最大值  可以指定规则
 *          min：返回流中最小值
 * 归约 / 收集
 *          归约：reduce(T identity, BinaryOperator) / reduce(BinaryOperator) 可以将流中的数据反复结合起来，得到一个值 典型的求和 1，2,3,4,5
 *          收集：collect 将流转换成其他形式；接收一个 Collector 接口的实现，用于给流中元素做汇总的方法 里面有很多操作 ，分区，分组，多级之类的
 *
 * stringStream.forEach(System.out::println);
 *
 *
 *  串行流(单线程)：切换为并行流 parallel()
    并行流：切换为串行流 sequential()


 Optional
 **定义：**Optional 类 (java.util.Optional) 是一个容器类，代表一个值存在或不存在，原来用 null 表示一个值不存在，现在用 Optional 可以更好的表达这个概念；并且可以避免空指针异常

 常用方法：

 Optional.of(T t)：创建一个 Optional 实例
 Optional.empty(T t)：创建一个空的 Optional 实例
 Optional.ofNullable(T t)：若 t 不为 null，创建 Optional 实例，否则空实例
 isPresent()：判断是否包含某值
 orElse(T t)：如果调用对象包含值，返回该值，否则返回 t
 orElseGet(Supplier s)：如果调用对象包含值，返回该值，否则返回 s 获取的值
 map(Function f)：如果有值对其处理，并返回处理后的 Optional，否则返回 Optional.empty()
 flatmap(Function mapper)：与 map 相似，要求返回值必须是 Optional

 */
public class Stream {
    List<Employee> emps = Arrays.asList(
            new Employee(101, "Z3", 19, 9999.99),
            new Employee(102, "L3", 40, 7777.77),
            new Employee(103, "W5", 35, 6666.66),
            new Employee(104, "Tom", 44, 1111.11),
            new Employee(105, "Jerry", 60, 4444.44)
    );


    List<String> list = Arrays.asList("chenchen", "shasha", "maomao", "zhangsan");

    /**
     * 排除加截断
     */
    @Test
    public void test() {
//        这种形式创建的集合不能以成员变量形式存在
        List<String> list = new ArrayList();
        list.add("shasha");
        list.add("chenchen");
        list.add("zhangsan");
        list.add("wangwu");
//        中间操作
        java.util.stream.Stream<String> stringStream = list.stream()
//       接收 Lambda  从流中排除某些元素
                .filter(e -> e.length() > 6)
//        截断流 只要找到满足条件，后面的就不会执行
                .limit(1);
//        中止操作
        stringStream.forEach(System.out::println);
    }

    /**
     * 排除加跳过
     */
    @Test
    public void test1() {
//        这里不采用返回值，可以直接用增强for
        list.stream()
                //       接收 Lambda  从流中排除某些元素
                .filter(e -> e.length() > 5)
//                跳过符合条件的值
                .skip(1)
//                中止操作
                .forEach(System.out::println);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stream stream = (Stream) o;
        return Objects.equals(list, stream.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    /**
     * 排除加筛选
     */
    @Test
    public void test2() {
//        这里不采用返回值，可以直接用增强for
        list.stream()
                //       接收 Lambda  从流中排除某些元素
                .filter(e -> e.length() > 5)
//                筛选 通过流所生成的 hashCode() 与 equals() 取除重复元素 因此要重写这两个方法
                .distinct()
//                中止操作
                .forEach(System.out::println);

    }

    /**
     * 映射
     */
    @Test
    public void test3() {
//        这里不采用返回值，可以直接用增强for
        list.stream()
                //       接收 Lambda  从流中排除某些元素
                .filter(e -> e.length() > 5)
//               map 映射 需要的是function接口  R apply(T t);  这里是自定义的方法将字符串转成大写返回 这里面除了lambda 还可以用引用
//               将元素转换为其他形式或提取信息；接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素 shasha 执行这个方法 SHASHA
                .map(str->str.toUpperCase())
//                中止操作
                .forEach(System.out::println);

    }

    /**
     *映射
     */
/*    @Test
    public void test4() {
//        这里不采用返回值，可以直接用增强for
        java.util.stream.Stream<String> rStream = list.stream()
                //       接收 Lambda  从流中排除某些元素
                .filter(e -> e.length() > 5)
//                引用其他方法，其他方法本身就是个流，流中又调用流
                .flatMap(Stream::test2);

//                中止操作
        rStream.forEach(System.out::println);

    }*/

    /**
     * 自然排序
     */
    @Test
    public void test5() {
//        这里不采用返回值，可以直接用增强for
        list.stream()
//                自然排序
                .sorted()
//                中止操作
                .forEach(System.out::println);

    }

    /**
     * 定制排序
     */
    @Test
    public void test6() {
        List<String>list=new ArrayList<>();
        list.add("shasha");
        list.add("chenchen");
        list.add("zhangsan");
        list.add("wangwu");
//        这里不采用返回值，可以直接用增强for
        list.stream()
//                定制排序
                .sorted((e, e1) -> {
                    if (e.length()== e1.length()) {
                        return e.compareTo(e1);
                    }
                    return 0;

                })
//                中止操作
                .forEach(System.out::println);

    }

    /**
     * 归约
     */
    @Test
    public void test7() {
      Object[] array;
      List<Integer> list= Arrays.asList(1,2,3,4,5,6);
//      初始值为0
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

    }
    /**
     * 收集
     */
    @Test
    public void test8() {
        List<String>list=new ArrayList<>();
        list.add("shasha");
        list.add("chenchen");
        list.add("zhangsan");
        list.add("wangwu");
//      初始值为0
        list.stream().filter(e -> e.contains("s"))
//                收集将上面符合条件的添加到一个list集合中
//                .collect(Collectors.toList())
//              收集将上面符合条件的添加到一个set集合中 通过引用创建一个set对象
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);



    }
    /**
     *分组
     */
    @Test
    public void test9() {
        List<String>list=new ArrayList<>();
        list.add("shasha");
        list.add("chenchen");
        list.add("zhangsan");
        list.add("wangwu");
//      初始值为0
        Map<Boolean, List<String>> collect = list.stream().filter(e -> e.contains("s"))
//                收集将上面符合条件的添加到一个list集合中
//                .collect(Collectors.toList())
//              收集将上面符合条件的添加到一个set集合中 通过引用创建一个set对象
                .collect(Collectors.groupingBy(e -> e.contains("e")));
        System.out.println(collect);

    }

    /**
     * 多级分组
     */
    @Test
    public void test10() {

//      初始值为0
        Map<String, Map<String, List<Employee>>> collect =
//                多级分组 先按照名字分组，名字相同按照年龄段分组
                emps.stream().collect(Collectors.groupingBy(Employee::getName,
                Collectors.groupingBy(e -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(collect);
    }

    /**
     * 分区 满足条件的在一个区，不满足的在一个区
     */
    @Test
    public void test11() {


        Map<Boolean, List<Employee>> collect = emps.stream().collect(Collectors.partitioningBy(e -> e.getSalay() > 5000));
        System.out.println(collect);
    }




    @Test
    public void test12() {
        long a = -1;
        System.out.println(-1 == a);


    }


}

