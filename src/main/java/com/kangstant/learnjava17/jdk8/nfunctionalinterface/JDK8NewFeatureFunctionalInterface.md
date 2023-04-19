# Functional Interface 函数式接口

定义：也称 SAM 接口，即 Single Abstract Method interfaces，有且只有一个抽象方法，但可以有多个非抽象方法的接口。

在 Java 8 中专门有一个包放函数式接口java.util.function，该包下的所有接口都有 @FunctionalInterface 注解，提供函数式编程。

在其他包中也有函数式接口，其中一些没有@FunctionalInterface 注解，但是只要符合函数式接口的定义就是函数式接口，与是否有@FunctionalInterface注解无关，注解只是在编译时起到强制规范定义的作用。 其在 Lambda 表达式中有广泛的应用。
