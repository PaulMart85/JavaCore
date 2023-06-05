public class TimeWorker extends Employee {

    public TimeWorker() {}

    public TimeWorker(String name) {
        super(name);
    }

    public TimeWorker(String name, int id) {
        super(name, id);
    }

    @Override
    protected void setSalary(int reward) {
        salary = (int) (20.8 * 8 * reward); // средняя ЗП округляется до целых
    }


}
