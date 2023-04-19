# Functional DateTime
Date-Time API 是对 java.util.Date 强有力的补充，解决了 Date 类的大部分痛点：
1. 非线程安全
2. 时区处理麻烦
3. 各种格式化、和时间计算繁琐
4. 设计有缺陷，Date 类同时包含日期和时间；还有一个 java.sql.Date，容易混淆

## java.time 的主要类
java.util.Date 既包含日期又包含时间，而 java.time 把它们进行了分离
```java
class Demo {
    // LocalDateTime.class // 日期+时间 format: yyyy-MM-ddTHH:mm:ss.SSS
    // LocalDate.class // 日期 format: yyyy-MM-dd
    // LocalTime.class // 时间 format: HH:mm:ss
}
```

## 实践
DateTimeInPractice.java

## JDBC 和 Java8

现在 JDBC 时间类型和 Java8 时间类型对应关系是
1. Date ---> LocalDate
2. Time ---> LocalTime
3. Timestamp ---> LocalDateTime


而之前统统对应 Date，也只有 Date。

## 时区
java.util.Date 对象实质上存的是 1970 年 1 月 1 日 0 点（ GMT）至 Date 对象所表示时刻所经过的毫秒数。


也就是说不管在哪个时区 new Date，它记录的毫秒数都一样，和时区无关。但在使用上应该把它转换成当地时间，这就涉及到了时间的国际化。


java.util.Date 本身并不支持国际化，需要借助 TimeZone。
