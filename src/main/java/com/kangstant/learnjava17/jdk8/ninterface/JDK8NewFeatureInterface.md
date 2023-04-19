# JDK8 新特性 Interface

## default 和 static 方法
JDK 8 中的新 interface 的方法可以用 default 或 static 修饰，这样就可以有方法体，实现类也不必重写此方法。
一个 interface 中可以有多个方法被它们修饰，这 2 个修饰符的区别主要也是普通方法和静态方法的区别。
1. default 修饰的方法，是普通实例方法，可以用this调用，可以被子类继承、重写。
2. static 修饰的方法，使用上和一般类静态方法一样。但它不能被子类继承，只能用Interface调用。

## Java 8 中，接口和抽象类的区别
1. interface 和 class 的区别
   - 接口多实现，类单继承
   - 接口的方法是 public abstract 修饰的，变量是 public static final 修饰的。abstract class 可以用其他修饰符
2. interface 的方法更像是一个扩展插件，而 abstract class 的方法是要继承的
   interface 新增default和static修饰的方法，为了解决接口的修改与现有的实现不兼容的问题，并不是为了要替代abstract class。在使用上，该用 abstract class 的地方还是要用 abstract class，不要因为 interface 的新特性而将之替换。

