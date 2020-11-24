package Java8Study.lambda3;

@FunctionalInterface
public interface MyFunctionWithGenerics<T,R> {

    R doSomething(T t1, T t2);
}
