# JDK9 New Feature

## 1. JShell

## 2. Multi-Release JAR

## 3. G1 成为默认垃圾回收器

在 Java 8 的时候，默认垃圾回收器是 Parallel Scavenge（新生代）+ Parallel Old（老年代）。

到了 Java 9, CMS 垃圾回收器被废弃了，G1（Garbage-First Garbage Collector） 成为了默认垃圾回收器。

G1 还是在 Java 7 中被引入的，经过两个版本优异的表现成为默认垃圾回收器。

## 4. 快速创建不可变集合

增加了List.of()、Set.of()、Map.of() 和 Map.ofEntries()等工厂方法来创建不可变集合

```java
List.of("Java","C++");
        Set.of("Java","C++");
        Map.of("Java",1,"C++",2);
```

使用 of() 创建的集合为不可变集合，不能进行添加、删除、替换、 排序等操作，不然会报 java.lang.UnsupportedOperationException 异常。

## 5. String 存储结构优化

ava 8 及之前的版本，String 一直是用 char[] 存储。在 Java 9 之后，String 的实现改用 byte[] 数组存储字符串，节省了空间。

```java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
    // @Stable 注解表示变量最多被修改一次，称为“稳定的”。
    @Stable
    private final byte[] value;
}
```

## 6. 接口私有方法

Java 9 允许在接口中使用私有方法。这样的话，接口的使用就更加灵活了，有点像是一个简化版的抽象类。

```java
public interface MyInterface {
    private void methodPrivate() {
    }
}
```

## 7. try-with-resources 优化

在 Java 9 之前，我们只能在 try-with-resources 块中声明变量：

```java
try(Scanner scanner=new Scanner(new File("testRead.txt"));
        PrintWriter writer=new PrintWriter(new File("testWrite.txt"))){
        // omitted
        }
```

在 Java 9 之后，在 try-with-resources 语句中可以使用 effectively-final 变量。

```java
final Scanner scanner=new Scanner(new File("testRead.txt"));
        PrintWriter writer=new PrintWriter(new File("testWrite.txt"))
        try(scanner;writer){
        // omitted
        }
```

什么是 effectively-final 变量？ 简单来说就是没有被 final 修饰但是值在初始化后从未更改的变量。

正如上面的代码所演示的那样，即使 writer 变量没有被显示声明为 final，但它在第一次被复制后就不会改变了，因此，它就是
effectively-final 变量。

## 8. Stream & Optional 增强

Stream 中增加了新的方法 ofNullable()、dropWhile()、takeWhile() 以及 iterate() 方法的重载方法。

Java 9 中的 ofNullable() 方 法允许我们创建一个单元素的 Stream，可以包含一个非空元素，也可以创建一个空 Stream。 而在 Java 8
中则不可以创建空的 Stream 。

```java
Stream<String> stringStream = Stream.ofNullable("Java");
System.out.println(stringStream.count());// 1
Stream<String> nullStream = Stream.ofNullable(null);
System.out.println(nullStream.count());//0
```

takeWhile() 方法可以从 Stream 中依次获取满足条件的元素，直到不满足条件为止结束获取。
```java
List<Integer> integerList = List.of(11, 33, 66, 8, 9, 13);
integerList.stream().takeWhile(x -> x < 50).forEach(System.out::println);// 11 33
```
dropWhile() 方法的效果和 takeWhile() 相反。
```java
List<Integer> integerList2 = List.of(11, 33, 66, 8, 9, 13);
integerList2.stream().dropWhile(x -> x < 50).forEach(System.out::println);// 66 8 9 13
```
iterate() 方法的新重载方法提供了一个 Predicate 参数 (判断条件)来决定什么时候结束迭代
```java
public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f) {
}
// 新增加的重载方法
public static<T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next) {
}
```
Optional 类中新增了 ifPresentOrElse()、or() 和 stream() 等方法


ifPresentOrElse() 方法接受两个参数 Consumer 和 Runnable ，如果 Optional 不为空调用 Consumer 参数，为空则调用 Runnable 参数。
```java
public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)
Optional<Object> objectOptional = Optional.empty();
objectOptional.ifPresentOrElse(System.out::println, () -> System.out.println("Empty!!!"));// Empty!!!
```
or() 方法接受一个 Supplier 参数 ，如果 Optional 为空则返回 Supplier 参数指定的 Optional 值。
```java
public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier)

Optional<Object> objectOptional = Optional.empty();
objectOptional.or(() -> Optional.of("java")).ifPresent(System.out::println);//java
```

## 9. 进程 API
Java 9 增加了 java.lang.ProcessHandle 接口来实现对原生进程进行管理，尤其适合于管理长时间运行的进程。

```java
// 获取当前正在运行的 JVM 的进程
ProcessHandle currentProcess = ProcessHandle.current();
// 输出进程的 id
System.out.println(currentProcess.pid());
// 输出进程的信息
System.out.println(currentProcess.info());
```

## 10. 响应式流 （ Reactive Streams ）
在 Java 9 中的 java.util.concurrent.Flow 类中新增了反应式流规范的核心接口。
Flow 中包含了 Flow.Publisher、Flow.Subscriber、Flow.Subscription 和 Flow.Processor 等 4 个核心接口。
Java 9 还提供了SubmissionPublisher 作为Flow.Publisher 的一个实现。

## 11. 变量句柄
变量句柄是一个变量或一组变量的引用，包括静态域，非静态域，数组元素和堆外数据结构中的组成部分等。


变量句柄的含义类似于已有的方法句柄 MethodHandle，由 Java 类 java.lang.invoke.VarHandle 来表示，可以使用类 java.lang.invoke.MethodHandles.Lookup 中的静态工厂方法来创建 VarHandle 对象
VarHandle 的出现替代了 java.util.concurrent.atomic 和 sun.misc.Unsafe 的部分操作。并且提供了一系列标准的内存屏障操作，用于更加细粒度的控制内存排序。


在安全性、可用性、性能上都要优于现有的 API。








