public class FixedSalaryWorker extends Employee {

    public FixedSalaryWorker() {}

    public FixedSalaryWorker(String name) {
        super(name);
    }

    public FixedSalaryWorker(String name, int id) {
        super(name, id);
    }

    @Override
    protected void setSalary(int reward) {
        salary = reward;
    }

}
