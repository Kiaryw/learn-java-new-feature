# JDK8 新特性 Optional

## NPE 产生的场景

防止 NPE，是程序员的基本修养，注意 NPE 产生的场景：

1. 返回类型为基本数据类型，return 包装数据类型的对象时，自动拆箱有可能产生 NPE。反例：public int f() { return Integer 对象}，
   如果为 null，自动解箱抛 NPE。
2. 数据库的查询结果可能为 null。
3. 集合里的元素即使 isNotEmpty，取出的数据元素也可能为 null。
4. 远程调用返回对象时，一律要求进行空指针判断，防止 NPE。
5. 对于 Session 中获取的数据，建议进行 NPE 检查，避免空指针。
6. 级联调用 obj.getA().getB().getC()；一连串调用，易产生 NPE。

正例：使用 JDK8 的 Optional 类来防止 NPE 问题。

## 如何创建一个 Optional

Demo: OptionalInPractice.java

```java
class Demo {
    /**
     * Common instance for {@code empty()}. 全局EMPTY对象
     */
    private static final Optional<?> EMPTY = new Optional<>();

    /**
     * Optional维护的值
     */
    private final T value;

    /**
     * 如果value是null就返回EMPTY，否则就返回of(T)
     */
    public static <T> Optional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * 返回 EMPTY 对象
     */
    public static <T> Optional<T> empty() {
        Optional<T> t = (Optional<T>) EMPTY;
        return t;
    }

    /**
     * 返回Optional对象
     */
    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    /**
     * 私有构造方法，给value赋值
     */
    private Optional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * 所以如果of(T value) 的value是null，会抛出NullPointerException异常，这样貌似就没处理NPE问题
     */
    public static <T> T requireNonNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }
}
```

ofNullable 方法和of方法唯一区别就是当 value 为 null 时，ofNullable 返回的是EMPTY，of 会抛出 NullPointerException 异常。

如果需要把 NullPointerException 暴漏出来就用 of，否则就用 ofNullable。

## map() 相关方法

```java
class Demo {
    /**
     * 如果value为null，返回EMPTY，否则返回Optional封装的参数值
     */
    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Optional.ofNullable(mapper.apply(value));
        }
    }

    /**
     * 如果value为null，返回EMPTY，否则返回Optional封装的参数值，如果参数值返回null会抛 NullPointerException
     */
    public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }
}
```

### map() 和 flatMap() 的区别？

1. 参数不一样
2. flatMap() 参数返回值如果是 null 会抛 NullPointerException，而 map() 返回EMPTY

## 判断 value 是否为 null
```java
class Demo {
    /**
     * 如果value为null，返回true，否则返回false
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * 如果value为null，执行consumer.accept
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }
}
```

## 获取 value

```java
class Demo {
   /**
    * Return the value if present, otherwise invoke {@code other} and return
    * the result of that invocation.
    * 如果value != null 返回value，否则返回other的执行结果
    */
   public T orElseGet(Supplier<? extends T> other) {
      return value != null ? value : other.get();
   }

   /**
    * 如果value != null 返回value，否则返回T
    */
   public T orElse(T other) {
      return value != null ? value : other;
   }

   /**
    * 如果value != null 返回value，否则抛出参数返回的异常
    */
   public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
      if (value != null) {
         return value;
      } else {
         throw exceptionSupplier.get();
      }
   }
   /**
    * value为null抛出NoSuchElementException，不为空返回value。
    */
   public T get() {
      if (value == null) {
         throw new NoSuchElementException("No value present");
      }
      return value;
   }
}
```
## 过滤值
```java
/**
* 1. 如果是empty返回empty
* 2. predicate.test(value)==true 返回this，否则返回empty
*/
class Demo {
   public Optional<T> filter(Predicate<? super T> predicate) {
      Objects.requireNonNull(predicate);
      if (!isPresent())
         return this;
      else
         return predicate.test(value) ? this : empty();
   }
}

```

## 小结
如果坚决不想看见 NPE，就不要用 of() 、 get() 、flatMap(..)。