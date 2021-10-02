import java.util.Objects;

/**
 * 员工
 *
 * @author bilib
 * @date 2021/09/06
 */

public class Employee {
    private int num;
    private String name;
    private int age;
    private double salay;

    public Employee(int num, String name, int age,double salay) {
        this.num = num;
        this.name = name;
        this.age = age;
        this.salay = salay;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalay() {
        return salay;
    }

    public void setSalay(double salay) {
        this.salay = salay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return num == employee.num && age == employee.age && Double.compare(employee.salay, salay) == 0 && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, name, age, salay);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salay=" + salay +
                '}';
    }
}
