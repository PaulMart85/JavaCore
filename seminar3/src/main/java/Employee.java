import java.util.Comparator;

public abstract class Employee {

    protected String name; // пусть будет ФИО (3 в 1)
    protected int id; // некий доп идентификатор, например, номер паспорта
    protected int salary; // ЗП

    { // дефолтный инициализатор
        name = "noname";
        id = 0;
        salary = 0;
    }

    public Employee() {}
    public Employee(String name) {
        setName(name);
    }
    public Employee(String name, int id) {
        this(name);
        setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null || name.isEmpty()) ? "noname" : name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id < 0 ? 0 : id;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", salary=" + salary +
                "}\n";
    }

    protected abstract void setSalary(int reward);

}
