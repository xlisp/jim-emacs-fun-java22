# Java 22 Leaning
Use jshell & emacs eval buffer
## jshell
`$ jshell --enable-preview`

```java
jshell> var name = "Duke"; System.out.println("👋 Hello, " + name);
   ...>
name ==> "Duke"
👋 Hello, Duke
jshell>
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
