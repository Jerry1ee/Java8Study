package Java8Study.lambda1;

public class SalaryFilter implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary()>5000;
    }
}
