# JDK8 新特性 Stream

新增了 java.util.stream 包，它和之前的流大同小异。

Stream依然不存储数据，不同的是它可以检索(Retrieve)和逻辑处理集合数据、包括筛选、排序、统计、计数等。可以想象成是 Sql 语句。

它的源数据可以是 Collection、Array 等。由于它的方法参数都是函数式接口类型，所以一般和 Lambda 配合使用。

## Stream 类型

1. stream串行流
2. parallelStream 并行流，可多线程

## 常用方法

```Java
class Demo {
    /**
     * 返回一个串行流
     */
    default Stream<E> stream();

    /**
     * 返回一个并行流
     */
    default Stream<E> parallelStream();

    /**
     * 返回 T 的流
     */
    public static <T> Stream<T> of(T t);

    /**
     * 返回其元素是指定值的顺序流。
     */
    public static <T> Stream<T> of(T... values) {
        return Arrays.stream(values);
    }


    /**
     * 过滤，返回由与给定predicate匹配的该流的元素组成的流
     */
    Stream<T> filter(Predicate<? super T> predicate);

    /**
     * 此流的所有元素是否与提供的predicate匹配。
     */
    boolean allMatch(Predicate<? super T> predicate);

    /**
     * 此流任意元素是否有与提供的predicate匹配。
     */
    boolean anyMatch(Predicate<? super T> predicate);

    /**
     * 返回一个 Stream的构建器。
     */
    public static <T> Builder<T> builder();

    /**
     * 使用 Collector对此流的元素进行归纳
     */
    <R, A> R collect(Collector<? super T, A, R> collector);

    /**
     * 返回此流中的元素数。
     */
    long count();

    /**
     * 返回由该流的不同元素（根据 Object.equals(Object) ）组成的流。
     */
    Stream<T> distinct();

    /**
     * 遍历
     */
    void forEach(Consumer<? super T> action);

    /**
     * 用于获取指定数量的流，截短长度不能超过 maxSize 。
     */
    Stream<T> limit(long maxSize);

    /**
     * 用于映射每个元素到对应的结果
     */
    <R> Stream<R> map(Function<? super T, ? extends R> mapper);

    /**
     * 根据提供的 Comparator进行排序。
     */
    Stream<T> sorted(Comparator<? super T> comparator);

    /**
     * 在丢弃流的第一个 n元素后，返回由该流的 n元素组成的流。
     */
    Stream<T> skip(long n);

    /**
     * 返回一个包含此流的元素的数组。
     */
    Object[] toArray();

    /**
     * 使用提供的 generator函数返回一个包含此流的元素的数组，以分配返回的数组，以及分区执行或调整大小可能需要的任何其他数组。
     */
    <A> A[] toArray(IntFunction<A[]> generator);

    /**
     * 合并流
     */
    public static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b);
}
```

## 延迟执行
在执行返回 Stream 的方法时，并不立刻执行，而是等返回一个非 Stream 的方法后才执行。


因为拿到 Stream 并不能直接用，而是需要处理成一个常规类型。这里的 Stream 可以想象成是二进制流，拿到也看不懂。



## Stream 特点小结
1. 通过简单的链式编程，使得它可以方便地对遍历处理后的数据进行再处理。
2. 方法参数都是函数式接口类型
3. 一个 Stream 只能操作一次，操作完就关闭了，继续使用这个 stream 会报错。
4. Stream 不保存数据，不改变数据源#

