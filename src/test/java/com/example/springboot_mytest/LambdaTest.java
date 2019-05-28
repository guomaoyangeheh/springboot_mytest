package com.example.springboot_mytest;

import com.example.springboot_mytest.api.MyFun;
import com.example.springboot_mytest.api.MyFun2;
import com.example.springboot_mytest.bean.ResponseResult;
import org.junit.Test;
import org.mockito.internal.matchers.CompareTo;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.PrintStream;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Guo Mao Yang
 * @date 2019/5/23 17:15
 */
@SpringBootTest
public class LambdaTest {

    @Test
    public void test1(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };
        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r.run();
        r1.run();
    }
    @Test
    public void test2(){
        Consumer<String> con = (e) -> System.out.println(e);
        con.accept("Lambda 好牛逼！");

        Runnable runnable = () -> System.out.println("123");

    }

    @Test
    public void test3(){
        Comparator<Integer> compare = (x, y) -> {
            System.out.println("进入了CompareTo 方法中");
            return Integer.compare(x,y);
        };
        compare.compare(1,2);
        Comparator<Integer> compare2 = (z, e) ->  Integer.compare(z,e);
    }

    @Test
    public void test4(){
        MyFun mf = (x, y) -> {
            System.out.println("进行相加运算");
            return x + y;
        };
        Integer result = operation(5, 6, mf);
        System.out.println("结果为：" + result);

        MyFun mf2 = (x, y) -> x - y;

        Integer operation = operation(5, 6, mf2);

        System.out.println("进行相减运算，结果为：" + operation);

        Integer operation1 = operation(5, 6, (x, y) -> x * y);
        System.out.println("进行相乘运算，结果为：" + operation1);


    }

    @Test
    public void test5(){
        MyFun myFun = new MyFun() {
            @Override
            public Integer operation(Integer x, Integer y) {
                return x + y;
            }
        };
        Integer operation = myFun.operation(5, 6);
        System.out.println( operation);
        MyFun myFun1 = (x, y) -> x + y;
        System.out.println(myFun1.operation(6,5));

        Runnable runnable = () -> System.out.println("123");
    }

    @Test
    public void test6(){
        List<Integer> list = Arrays.asList(4, 3, 8, 2, 10);
        /*Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });*/
        Collections.sort(list,(x,y) -> Integer.compare(x,y));
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    public void test7(){
        //将字符串全部转为大写
        String str = convert("wsdfSEDsdsEWscDWsWEAxg123", (x) -> x.toUpperCase());
        System.out.println("输出结果：" + str);
    }

    @Test
    public void test8(){
        String str = "123sdf,";
        System.out.println(str.substring(0,str.length()-1));

    }

    //构造器引用
    @Test
    public void test9(){
        //lambda 形式
        Supplier<ResponseResult> sub = () -> new ResponseResult();
        ResponseResult res1 = sub.get();

        Supplier<ResponseResult> sub2 = ResponseResult::new;
        ResponseResult res2 = sub2.get();

        Function<Integer,ResponseResult> fun1 = (x) -> new ResponseResult(x);
        ResponseResult res3 = fun1.apply(1);
        System.out.println(res3);

        Function<Integer,ResponseResult>  fun2 = ResponseResult::new;
        ResponseResult res4 = fun2.apply(15);
        System.out.println(res4);
    }

    //方法引用，注意：抽象方法的参数列表要与方法引用的保持一致！
    @Test
    public void test10(){
        //对象::实例方法
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("去你妈的！");

        PrintStream out = System.out;
        Consumer<String> con2 = out::println;
        con2.accept("日了狗！");

        BinaryOperator<Double>  bi = (x,y) -> Math.pow(x,y);
        Double apply = bi.apply(1.0, 1.0);
        System.out.println(apply);

        //类::静态方法
        BinaryOperator<Double>  bi2 = Math::pow;




    }

    @Test
    public void test11(){
        UUID uuid = UUID.randomUUID();

        System.out.println(uuid.toString());
        System.out.println(uuid.toString().length());

    }

    public String convert(String str, Function<String,String> fun){
        return fun.apply(str);

    }
    

    public Integer operation(Integer x, Integer y, MyFun mf){
        return mf.operation(x,y);
    }




}
