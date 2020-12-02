package Java8Study.Annotation;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解 与 类型注解
 *
 * 重复注解使用时，注意使用容器，并用 Repeatable 注解告知是哪个容器
 * 通过反射拿到注解实例时，注意是注解数组
 *
 * 类型注解，在 注解的 @Target 里要加上 TYPE_PARAMETER，表示注解可以修饰 参数类型
 *
 * 修饰参数类型，可以很方便地对参数进行检查
 *
 *
 */
public class TestAnnotation {

    // 需要使用容器类 ，实现重复注解
    @MyAnnotation("我是重复注解1")
    @MyAnnotation("我是重复注解2")
    public void show(@MyAnnotation("我可以注解类型") String str){

    }

    @Test
    public void test1() throws NoSuchMethodException {
        //通过反射 拿到修饰方法的注解，因为是重复注解，可以拿到多个
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method method = clazz.getMethod("show");

        MyAnnotation[] myAnnotations = method.getAnnotationsByType(MyAnnotation.class);

        for(MyAnnotation myAnnotation : myAnnotations){
            System.out.println(myAnnotation.value());
        }




    }
}
