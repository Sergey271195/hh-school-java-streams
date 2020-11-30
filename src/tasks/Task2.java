package tasks;

import common.*;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Задача 2
На вход принимаются две коллекции объектов Person и величина limit
Необходимо объеденить обе коллекции
отсортировать персоны по дате создания и выдать первые limit штук.
 */
public class Task2 implements Task {

  // !!! Редактируйте этот метод !!!
  private static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                      Collection<Person> persons2,
                                                      int limit) {

    return Stream.concat(persons1.stream(), persons2.stream())
            .sorted(Comparator.comparing(Person::getCreatedAt))
            .limit(limit).collect(Collectors.toList());
  }

  @Override
  public boolean check() {
    System.out.println("\n-------- TASK 2 ----------\n");
    checkExecutionTime();
    Instant time = Instant.now();
    Collection<Person> persons1 = Set.of(
        new Person(1, "Person 1", time),
        new Person(2, "Person 2", time.plusSeconds(1))
    );
    Collection<Person> persons2 = Set.of(
        new Person(3, "Person 3", time.minusSeconds(1)),
        new Person(4, "Person 4", time.plusSeconds(2))
    );
    return combineAndSortWithLimit(persons1, persons2, 3).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(List.of(3, 1, 2))
        && combineAndSortWithLimit(persons1, persons2, 5).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(List.of(3, 1, 2, 4));
  }

  public void checkExecutionTime() {
    Instant time = Instant.now();
    Collection<Person> persons1 = Stream.iterate(1, i -> i+1).limit(100_000)
            .map(id -> new Person(id, "Person " + id, time.plusSeconds(id)))
            .collect(Collectors.toSet());
    Collection<Person> persons2 = Stream.iterate(100_000, i -> i+1).limit(100_000)
            .map(id -> new Person(id, "Person " + id, time.minusSeconds(200_000 - id)))
            .collect(Collectors.toSet());
    System.out.println("Testing execution time for lists of size " + persons1.size());
    System.out.println("Assuming that sorting time complexity is O(nlogn)");
    new TestCase4().test(5, List.of(persons1, persons2), "O(nlogn)", "Using concat");
    new TestCase5().test(5, List.of(persons1, persons2), "O(nlogn)", "Using flatMap");
  }
}

