import java.lang.reflect.Array;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) {

        Employee[] employees = new Employee[] {
                new TimeWorker("Масленников Тимофей Петрович", 1),
                new TimeWorker("Бревнов Егор Михайлович", 4),
                new FixedSalaryWorker("Дробенко Михаил Павлович", 55),
                new FixedSalaryWorker("Григорьев Дмитрий Валентинович", 12)
        };

        employees[0].setSalary(250);
        employees[1].setSalary(200);
        employees[2].setSalary(50_500);
        employees[3].setSalary(35_800);

        System.out.println("До сортировки: " + Arrays.toString(employees));
        Arrays.sort(employees, new SalaryComparator());
        System.out.println("После сортировки: " + Arrays.toString(employees));

        EmployeesOutput.print(employees);

    }
}
