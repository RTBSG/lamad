import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * sd
 *java8内置了四个核心函数接口，就是为了节省事件，我们只需要要自己用lamda实现接口 自定义功能即可
 * 消费型接口Consumer<T>   void accept(T t);                            有参数，无返回值类型的接口。
 * Supplier<T>：供给型接口（T get（））                无参数，只有返回值，
 * Predicate<T>：断言型接口（boolean test（T t））     输入一个参数，输出一个boolean类型得返回值
 * Function<T, R>：函数型接口（R apply（T t））        输入一个类型得参数，输出一个类型得参数，当然两种类型可以一致。
 *
 * 除了上述得4种类型得接口外还有其他的一些子接口供我们使用：
 * 1）.BiFunction<T, U, R>
 * 参数类型有2个，为T，U，返回值为R，其中方法为R apply(T t, U u)
 * 2）.UnaryOperator<T>(Function子接口)
 *参数为T，对参数为T的对象进行一元操作，并返回T类型结果，其中方法为T apply(T t)
 * 3）.BinaryOperator<T>(BiFunction子接口)
 *参数为T，对参数为T得对象进行二元操作，并返回T类型得结果，其中方法为T apply（T t1， T t2）
 * 4）.BiConsumcr(T, U)
 *参数为T，U无返回值，其中方法为 void accept(T t, U u)
 * 5）.ToIntFunction<T>、ToLongFunction<T>、ToDoubleFunction<T>
 *参数类型为T，返回值分别为int，long，double，分别计算int，long，double得函数。
 * 6）.IntFunction<R>、LongFunction<R>、DoubleFunction<R>
 *参数分别为int，long，double，返回值为R。
 *
 */
public class lamda {
//    供给型
    @Test
    public void test() {
//        T get(); 方法无参数   调用getNum方法
        List<Integer> num = getNum(10, () ->
                (int) (Math.random() *100));
        for (Integer integer :  num) {
            System.out.println(integer);

        }
    }

    /**
 *     Supplier<Integer> supplier 就是一个接口，这个接口已经写好了 。接口中只有一个get方法
 *     相当于实现了这个接口， 调用这个方法穿的对应参数不是一个具体类型而是一个重写的方法，也就是个匿名函数。
     *     相当于自己重写了get方法 get()=(int) (Math.random() * 100)
 *     lamda表达式本身就是一个匿名函数
     *
     * @param num      全国矿工工会
     * @param supplier 供应商
     * @return {@link List<Integer>}
     */
    public List<Integer> getNum(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }

    /**
     *    R apply(T t);
     * test1
     *///函数式接口
    @Test
    public void test1(){
//        并不是说把这个字符串传给了lamda表达式 而是下面的方法传给的，这就是个简单的调用，只不过参数是个方法
        String str = str("      莎莎陈 晨  ", (s) -> s.trim());
        System.out.println(str);
    }

    public String str(String string, Function<String, String> function) {
//        把传过来的字符串执行自己用lamda表达式重写的接口中的方法(s) -> s.trim()
       return function.apply(string);
    }
@Test
    public void test2() {
    List<String> list = new ArrayList();
    list.add("shasha");
    list.add("chenchen");
//自己实现断言接口中的test方法为字符串长度大于三输出
    List<String> filter = filter(list, (t) -> t.length() > 3);
    for (String str : filter) {
        System.out.println(str);

    }

}
    //    断言型接口 用于判断
    public List<String> filter(List<String> list, Predicate<String> pre) {
        List <String>list1 = new LinkedList();
//        遍历传过来的集合
        for (String str : list) {
//            在这里自定义要进行的操作，实际上是调用的时候是实现接口 将满足条件的元素加入list中
            if (pre.test(str)) {
                list1.add(str);
            }
        }
        return list1;

    }


    /**
     * 构造器引用 用户返回一个对象
     * 构造器引用 相当于new了一个对象  至于是有参构造，还是无参构造 会与接口中的方法是否有参保持一致
     * Supplier里面的方法是无参的因此会调用无参构造  T get();
     * 如果是 Function 有一个参数就会调用只有一个参数的构造器，多个参数也是如此可以用子类中的方法
     *  Supplier<ri> sup1 = ri::new;
     *
     * 方法引用 如果lambda体中内容有方法已经实现了，可以采用方法引用
     * 方法引用有三种格式
     * 对象::实例方法名
     * 类::静态方法名
     * 类::实例方法名
     *
     *数组引用
     *
     *
     */
    @Test
    public void test03() {
//        单纯实现了生产型中的接口 但是要注意 实现的方法和四大内置函数的返回值类型要一致
//       Consumer 中内置的 void accept(T t); 而public void println(Object x)  两个都是void
        Consumer con = (x) -> System.out.println(x);
//        println是一个已经实现过的方法 因此可以用对象::方法
        Consumer con1 = System.out::println;
        con1.accept("超级莎莎");
    }
    @Test
    public void test04() {
//        lambda表达式写法
        Supplier<ri> sup = () -> new ri();
//       构造器引用 相当于new了一个对象  至于是有参构造，还是无参构造 会与接口中的方法是否有参保持一致
//      Supplier里面的方法是无参的因此会调用无参构造  T get();
//      如果是 Function 有一个参数就会调用只有一个参数的构造器，多个参数也是如此可以用子类中的方法
        Supplier<ri> sup1 = ri::new;
        ri ri = sup1.get();
//        ri{num=0, name='null'}
        System.out.println(ri);

    }
}
class ri{
    private int num;
    private String name;

    public ri(int num, String name) {
        this.num = num;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ri{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ri() {
    }
}