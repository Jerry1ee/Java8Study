package Java8Study.lambda1;

public class AgeFilter implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee t) {
        return t.getAge()>=35;
    }
}
