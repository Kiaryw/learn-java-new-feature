package com.kangstant.learnjava17.jdk8.nlambda;

/**
 * @author spencer.huang@nio.com
 * @date 2023/4/19 14:53
 * @description
 */
public class LambdaClass extends LambdaClassSuper {
    public static void forEg() {
        lambdaInterfaceDemo(() -> System.out.println("自定义函数式接口 Lambda表达式"));
    }

    // 函数式接口参数
    static void lambdaInterfaceDemo(LambdaInterface lambdaInterface) {
        lambdaInterface.f();
    }

    public static LambdaInterface staticF() {
        return null;
    }

    public LambdaInterface f() {
        return null;
    }

    void show() {
        // 1.调用静态函数，返回类型必须是functional-interface
        LambdaInterface t = LambdaClass::staticF;

        // 2.实例方法调用
        LambdaClass lambdaClass = new LambdaClass();
        LambdaInterface lambdaInterface = lambdaClass::f;

        // 3.超类上的方法调用
        LambdaInterface superF = super::sf;

        //4. 构造方法调用
        LambdaInterface tt = LambdaClassSuper::new;
    }
}
