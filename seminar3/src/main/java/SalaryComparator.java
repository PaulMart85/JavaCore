import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee> {
    // здесь реализована сортировка по ЗП. Однако, компаратор позволяет задавать
    // произвольные правила сортировки и для любых полей сравниваемых объектов
    // здесь Comparator работает по аналогии с Comparable
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getSalary() - o2.getSalary();
    }
}
