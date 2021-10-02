/**
 * 接口
 *Java8中新增了很多新的特性，其中就包括可以在接口中添加方法和变量。
 * 也就是说接口中可以存在非抽象方法，变量 接口更像抽象类
 * 在接口中，也可以直接添加静态方法。
 * Java接口中只能包含public,static,final类型的成员变量和public,abstract类型的成员方法
 *
 * 接口默认方法的”类优先”原则 优先使用父类中的方法
 * 若一个接口中定义了一个默认方法，而另外一个父类或接口中又定义了一个同名的方法时
 * 选择父类中的方法。如果一个父类提供了具体的实现,
 * 那么接口中具有相同名称和参数的默认方法会被忽略.
 * 接口冲突.如果一个父接口提供一个默认方法，而另一个接口也提供了一个具有相同名称和参数列表的方法(不管方法是否是默认方法)，
 * 那么必须覆盖该方法来解决冲突使用super关键字指定调用那个接口的方法
 * @author bilib
 * @date 2021/09/06
 */
public class Interface {
}
