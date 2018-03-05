package com.example.helloboot.test8;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

    public static void main(String[] args){
        /**
         * Test operations
         */
        /*justIntermediateOperations();
        hasTerminalOperations();
        streamRunResult();*/

        /**
         * Test the Effect of operations order
         */
        /*efficiencyOfOrder1();
        efficiencyOfOrder2();

        addSortOperations();*/

        /*streamRepeatly();*/

        baseCollectMethod();
        myOwnCollectOperators();
    }

    /**
     * if there just have the intermediate operations when you use the {@link Stream},
     * it cannot run
     */
    public static void justIntermediateOperations(){
        Stream.of("d2","a1","b1","b3","c")
                .filter(c -> {
                    System.out.println("filter:" + c);
                    return true;
                });

        System.out.println("===================justIntermediateOperations=====================");
    }

    /**
     * when you use the {@link Stream},
     * unless there have the terminal operations,it cannot run success.
     *
     */
    public static void hasTerminalOperations(){
        Stream.of("d2","a1","b1","b3","c")
                .filter(c -> {
                    System.out.println("filter:" + c);
                    return true;
                })
                .forEach(c -> System.out.println("forEach:" + c));

        System.out.println("===================hasTerminalOperations=====================");
    }

    /**
     *  You can found the result, there have some order about the print result.
     *  Every param must running all of this operations before skip to next param.
     *
     *  example: d2(filter-->forEach) --> a1(filter-->forEach)...
     *
     *  Because of this rules,this method will just print:
     *      map:      d2
     *      anyMatch: D2
     *      map:      a2
     *      anyMatch: A2
     */
    public static void streamRunResult(){
        Stream.of("d2","a1","b1","b3","c")
                .map(s -> {
                    System.out.println("map:" + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch:" + s);
                    return s.startsWith("A");
                });

        System.out.println("===================streamRunResult=====================");
    }

    //==================================================================================================

    /**
     *  Test the effect of operations order
     *
     *  order:map --> filter --> forEach(end)
     *
     *  result:
     *      map:d2
     *      filter:D2
     *      map:a1
     *      filter:A1
     *      forEach:A1
     *      map:b1
     *      filter:B1
     *      map:b3
     *      filter:B3
     *      map:c
     *      filter:C
     */
    public static void efficiencyOfOrder1(){
        Stream.of("d2","a1","b1","b3","c")
                .map(s -> {
                    System.out.println("map:" + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach:" + s));

        System.out.println("===================efficiencyOfOrder1=====================");
    }

    /**
     * Test the effect of operations order
     *
     * order: filter --> map --> forEach(end)
     *
     * You can find there have some difference about the result
     *  when you change the operations order
     * result:
     *      filter:d2
     *      filter:a1
     *      map:a1
     *      forEach:A1
     *      filter:b1
     *      filter:b3
     *      filter:c
     */
    public static void efficiencyOfOrder2(){
        Stream.of("d2","a1","b1","b3","c")
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map:" + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach:" + s));

        System.out.println("===================efficiencyOfOrder2=====================");
    }

    /**
     * if you add sort operation, it have another rule.
     *  it will finished the sort operation before run the other operations
     *
     *  notice:
     *      sorting is a stateful operation
     *      filter,map are stateless operations
     *
     *  result:
     *      sort:a1;d2
     *      sort:b1;a1
     *      sort:b1;d2
     *      sort:b1;a1
     *      sort:b3;b1
     *      sort:b3;d2
     *      sort:c;b3
     *      sort:c;d2
     *      filter:a1
     *      map:a1
     *      forEach:A1
     *      filter:b1
     *      filter:b3
     *      filter:c
     *      filter:d2
     */
    public static void addSortOperations(){
        Stream.of("d2","a1","b1","b3","c")
                .sorted((s1,s2) -> {
                    System.out.printf("sort:%s;%s\n",s1,s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map:" + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach:" + s));

        System.out.println("===================addSortOperations=====================");
    }

    /**
     *  Unfortunately,{@link Stream} cannot be repeatly,
     *  so the second operation {@link Stream#noneMatch} will throw a exception{@link IllegalStateException}.
     *
     *  if you really want to use stream again,you can use Supplier{@link Supplier},
     *      You can use the method{@link Supplier#get} to create a new stream
     *      when you run the terminal operation
     *      depend on the intermediate operations
     */
    public static void streamRepeatly(){
        /*Stream<String> stream =
                Stream.of("d2","a1","b1","b3","c")
                .filter(s->{
                    System.out.println("filter:" + s);
                    return s.startsWith("a");
                });

        stream.anyMatch(s -> true);  //ok
        stream.noneMatch(s -> true); //exception*/

        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2","a1","b1","b3","c")
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return s.startsWith("a");
                });

        streamSupplier.get().anyMatch(s -> true);  //ok
        streamSupplier.get().noneMatch(s -> true); //ok
    }

    //==============================================Collect====================================================
    class Person{
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static List<Person> persons = new ArrayList<>();
    static{
        TestStream ts=new TestStream();
        persons = Arrays.asList(
                ts.new Person("Max",18),
                ts.new Person("Peter",23),
                ts.new Person("Pamela",23),
                ts.new Person("David",12)
        );
    }

    public static void baseCollectMethod(){
        //exchange to list
        System.out.println("========================exchange to list===========================");
        List<Person> filtered =
                persons.stream()
                .filter(p -> p.name.startsWith("P"))
                .collect(Collectors.toList());
        System.out.println(filtered);

        //group the list
        System.out.println("=====================group by age============================");
        Map<Integer,List<Person>> personByAge =
                persons.stream()
                .collect(Collectors.groupingBy(p -> p.age));
        personByAge.forEach((age,p)-> System.out.printf("age %s: %s\n",age,p));

        //count averaging
        System.out.println("=====================averaging age============================");
        Double averagAge = persons.stream()
                .collect(Collectors.averagingDouble(p->p.age));
        System.out.println(averagAge);

        //summarizing collectors,this operation will give us more details info
        System.out.println("=====================summarizing collectors============================");
        IntSummaryStatistics ageSummary =
                persons.stream()
                .collect(Collectors.summarizingInt(p->p.age));
        System.out.println(ageSummary);

        //concat string,use join
        System.out.println("=====================join============================");
        String phrase =
                persons.stream()
                .filter(p->p.age>=18)
                .map(p->p.name)
                .collect(Collectors.joining(" and ","In Germany "," are of legal age."));
        System.out.println(phrase);

        /**
         * exchange a stream to map,we must pay more attention to the key,it must unique,
         * Otherwish it will throw {@link IllegalStateException}.
         *
         * If we cannot ensure this unique, we can use aggregations to skip this exception
         */
        System.out.println("=====================exchange to map============================");
        Map<Integer,String> map =
                persons.stream()
                .collect(Collectors.toMap(
                        p ->p.age,
                        p -> p.name,
                        (name1,name2) -> name1 + ";" + name2));
        System.out.println(map);
    }

    /**
     *  You can defined your own Collectors.
     *  We can use{@link Collector#of} to create our own collector, and we must supply
     *
     *  {@link Collector#supplier} create a StringJoiner{@link StringJoiner} with suit split sign,
     *  {@link Collector#accumulator} exchange name to upperCase{@link String#toUpperCase},then join in the stringJoiner object,
     *  {@link Collector#combiner} concat two stringJoiner object{@link StringJoiner#merge},
     *  {@link Collector#finisher} construct a string object which we hope to get.
     */
    public static void myOwnCollectOperators(){
        System.out.println("=====================defined own Collectors============================");
        Collector<Person,StringJoiner,String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),      //supplier
                        (j,p) -> j.add(p.name.toUpperCase()),       //accumulator
                        (j1,j2) -> j1.merge(j2),                    //combiner
                        StringJoiner::toString);                    //finisher

        String names = persons.stream()
                .collect(personNameCollector);
        System.out.println(names);
    }

    //==============================================FlatMap====================================================

}
