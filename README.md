# Java 22 Leaning
* dev: Use jshell & emacs eval buffer
* docs: https://dev.java/playground/

## jshell
`$ jshell --enable-preview`

```java
jshell> var name = "Duke"; System.out.println("👋 Hello, " + name);
   ...>
name ==> "Duke"
👋 Hello, Duke
jshell>
```
## lambda 
```java
jshell> // Consumer with Unnamed Pattern
   ...> List<String> strings = List.of("one", "two", "three");
   ...> Consumer<String> notInterested = _ -> System.out.println("I'm not interested in this argument");
   ...> strings.forEach(notInterested);
strings ==> [one, two, three]
notInterested ==> $Lambda/0x0000000129024a00@46fbb2c1
I'm not interested in this argument
I'm not interested in this argument
I'm not interested in this argument
jshell>
// ======
jshell> // Function with Unnamed Pattern
   ...> List<String> strings = List.of("1", "11", "111");
   ...> Function<String, Integer> constantLength = _ -> 3;
   ...> var result = strings.stream()
   ...>         .map(constantLength)
   ...>         .toList();
   ...> System.out.println("result = " + result);
   ...>
strings ==> [1, 11, 111]
constantLength ==> $Lambda/0x0000000129025028@443b7951
result ==> [3, 3, 3]
result = [3, 3, 3]
// =====
jshell> // BiFunction with Unnamed Pattern
   ...> var strings = List.of("one", "two", "three", "four");
   ...> BiFunction<String, Integer, Integer> indexer = (_, i) -> i;
   ...> var result = IntStream.range(0, strings.size())
   ...>         .mapToObj(index -> indexer.apply(strings.get(index), index))
   ...>         .toList();
   ...> System.out.println("result = " + result);
   ...>
strings ==> [one, two, three, four]
indexer ==> $Lambda/0x0000000129025c60@2328c243
result ==> [0, 1, 2, 3]
result = [0, 1, 2, 3]
// =====

```
## map reduce (stream)
```java
jshell> List<String> cities =
   ...>             Arrays.asList("Shenzhen", "Brussels", "Taipei", "Buenos Aires", "Sydney", "Bristol");
   ...>
cities ==> [Shenzhen, Brussels, Taipei, Buenos Aires, Sydney, Bristol]
jshell> cities.stream()
   ...>       .filter(s -> s.startsWith("B"))
   ...>       .map(String::toUpperCase)
   ...>       .sorted()
   ...>       .forEach(System.out::println);
   ...>
BRISTOL
BRUSSELS
BUENOS AIRES
jshell>
```
## sorted, and stream to list
```
jshell> Stream<String> stream = Stream.of("Shenzhen", "Brussels", "Taipei", "Buenos Aires", "Sydney", "Bristol");
   ...>
   ...> List<String> cities = stream.sorted().collect(Collectors.toList());
   ...>
   ...> System.out.println(cities);
stream ==> java.util.stream.ReferencePipeline$Head@1e80bfe8
cities ==> [Bristol, Brussels, Buenos Aires, Shenzhen, Sydney, Taipei]
[Bristol, Brussels, Buenos Aires, Shenzhen, Sydney, Taipei]
jshell>
```
## stream & list convert
```java
jshell> var cities = """
   ...>       San Francisco
   ...>       Casablanca
   ...>       Antwerp
   ...>       New Delhi
   ...>       Osaka
   ...> """;
   ...>
   ...> Stream<String> lines = cities.lines();
   ...>
   ...> System.out.println(lines.toList());
cities ==> "      San Francisco\n      Casablanca\n      Ant ...  New Delhi\n      Osaka\n"
lines ==> java.util.stream.ReferencePipeline$Head@2dda6444
[      San Francisco,       Casablanca,       Antwerp,       New Delhi,       Osaka]
jshell>
```
## Class & Object
```java

import java.io.*;
 
public class Employee{
   String name;
   int age;
   String designation;
   double salary;
   // Employee 类的构造器
   public Employee(String name){
      this.name = name;
   }
   // 设置age的值
   public void empAge(int empAge){
      age =  empAge;
   }
   /* 设置designation的值*/
   public void empDesignation(String empDesig){
      designation = empDesig;
   }
   /* 设置salary的值*/
   public void empSalary(double empSalary){
      salary = empSalary;
   }
   /* 打印信息 */
   public void printEmployee(){
      System.out.println("名字:"+ name );
      System.out.println("年龄:" + age );
      System.out.println("职位:" + designation );
      System.out.println("薪水:" + salary);
   }
}
// => Test
import java.io.*;
public class EmployeeTest{
 
   public static void main(String[] args){
      /* 使用构造器创建两个对象 */
      Employee empOne = new Employee("RUNOOB1");
      Employee empTwo = new Employee("RUNOOB2");
 
      // 调用这两个对象的成员方法
      empOne.empAge(26);
      empOne.empDesignation("高级程序员");
      empOne.empSalary(1000);
      empOne.printEmployee();
 
      empTwo.empAge(21);
      empTwo.empDesignation("菜鸟程序员");
      empTwo.empSalary(500);
      empTwo.printEmployee();
   }
}
```
## super (extends class)
```java
jshell> // Statement before super
   ...> static class Shape {
   ...>     private String name;
   ...>     public Shape(String name) {
   ...>         if (name == null) {
   ...>             throw new IllegalArgumentException("User should not be null");
   ...>         }
   ...>         super();
   ...>         this.name = name;
   ...>     }
   ...>     public String name() {
   ...>         return name;
   ...>     }
   ...> }
|  已创建 类 Shape

jshell> static class Square extends Shape {
   ...>     private int edge;
   ...>     public Square(int edge) {
   ...>         if (edge <= 0) {
   ...>             throw new IllegalArgumentException("Edge should be greater than 0");
   ...>         }
   ...>         super("Square");
   ...>         this.edge = edge;
   ...>     }
   ...>     public int edge() {
   ...>         return edge;
   ...>     }
   ...>     public String toString() {
   ...>         return "Square[" + edge + "]";
   ...>     }
   ...> }
   ...>
|  已创建 类 Square

jshell> Square square = new Square(100);
   ...> System.out.println("square = " + square);
square ==> Square[100]
square = Square[100]

jshell> square.name()
$5 ==> "Square"
jshell> square.edge()
$6 ==> 100
jshell>
```
## record
```java
jshell> // Simple record
   ...> record Player(String last, String first, int level) {}
   ...> var jane = new Player("Doe", "Jane", 42);
   ...> System.out.println(jane);
|  已创建 记录 Player
jane ==> Player[last=Doe, first=Jane, level=42]
Player[last=Doe, first=Jane, level=42]
jshell>
// ====
jshell> // Composing
   ...> record Population(int population) {}
   ...> record City(String name, Population population) {
   ...>     // static methods are allowed in records
   ...>     public static City of(String name, int p) {
   ...>         var population = new Population(p);
   ...>         return new City(name, population);
   ...>     }
   ...> }
|  已创建 记录 Population
|  已创建 记录 City

jshell> var paris = City.of("Paris", 2_161);
   ...> System.out.println(paris);
paris ==> City[name=Paris, population=Population[population=2161]]
City[name=Paris, population=Population[population=2161]]

jshell> paris
paris ==> City[name=Paris, population=Population[population=2161]]

jshell> paris.name()
$15 ==> "Paris"

jshell> paris.population()
$16 ==> Population[population=2161]

jshell> paris.population().
clone()        equals(        finalize()     getClass()     hashCode()     notify()       notifyAll()    population()
toString()     wait(
jshell> paris.population().population()
$17 ==> 2161
jshell>
```
## Pattern Matching
```java
jshell> sealed interface Operation permits Add, Mult, Sub, Div {}
   ...>
   ...> record Add(int left, int right) implements Operation  {}
   ...> record Mult(int left, int right) implements Operation  {}
   ...> record Sub(int left, int right) implements Operation  {}
   ...> record Div(int left, int right) implements Operation  {}
   ...>
|  已创建 接口 Operation, 不过, 它无法引用, 直至 class Add, class Mult, class Sub, and class Div 已声明
|  已创建 记录 Add, 不过, 它无法引用, 直至 class Operation 已声明
|  已创建 记录 Mult, 不过, 它无法引用, 直至 class Operation 已声明
|  已创建 记录 Sub, 不过, 它无法引用, 直至 class Operation 已声明
|  已创建 记录 Div

jshell> public static boolean naturalNumber(Operation operation) {
   ...>     return switch (operation) {
   ...>         case Add _ -> true;
   ...>         case Mult _ -> true;
   ...>         case Sub(int left, int right) -> left > right;
   ...>         case Div(int left, int right) -> left % right == 0;
   ...>     };
   ...> }
   ...> public static int resolve(Operation operation) {
   ...>     return switch(operation) {
   ...>         case Add(int left, int right) -> left + right;
   ...>         case Mult(int left, int right) -> left * right;
   ...>         case Sub(int left, int right) -> left - right;
   ...>         case Div(int left, int right) -> left / right;
   ...>     };
   ...> }
   ...>
|  已创建 方法 naturalNumber(Operation)
|  已创建 方法 resolve(Operation)

jshell> var operations = List.of(
   ...>         new Add(1, 2),
   ...>         new Mult(4, 3),
   ...>         new Sub(0, 3), new Sub(4, 3),
   ...>         new Div(9, 4), new Div(1, 3), new Div(12, 4));
operations ==> [Add[left=1, right=2], Mult[left=4, right=3], Sub ... 3], Div[left=12, right=4]]

jshell> operations.forEach(operation -> {
   ...>     System.out.println(operation + (
   ...>             naturalNumber(operation) ? " = " + resolve(operation): " -> result is not a natural integer"));
   ...> });
Add[left=1, right=2] = 3
Mult[left=4, right=3] = 12
Sub[left=0, right=3] -> result is not a natural integer
Sub[left=4, right=3] = 1
Div[left=9, right=4] -> result is not a natural integer
Div[left=1, right=3] -> result is not a natural integer
Div[left=12, right=4] = 3

jshell>
```
