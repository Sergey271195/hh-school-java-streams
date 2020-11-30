package tasks;

import common.Person;
import common.Task;
import common.TestCaseClass;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<Person> sort(Collection<Person> persons) {
    return persons.stream().sorted(
            Comparator.comparing(Person::getSecondName)
                    .thenComparing(Person::getFirstName)
                    .thenComparing(Person::getCreatedAt)
    ).collect(Collectors.toList());
  }

  @Override
  public boolean check() {
    System.out.println("-------- TASK 3 ----------");
    Instant time = Instant.now();
    List<Person> persons = List.of(
        new Person(1, "Oleg", "Ivanov", time),
        new Person(2, "Vasya", "Petrov", time),
        new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
        new Person(4, "Oleg", "Ivanov", time.plusSeconds(1))
    );
    List<Person> sortedPersons = List.of(
        new Person(1, "Oleg", "Ivanov", time),
        new Person(4, "Oleg", "Ivanov", time.plusSeconds(1)),
        new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
        new Person(2, "Vasya", "Petrov", time)
    );
    return sortedPersons.equals(sort(persons));
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
    System.out.println("Using concat");
    new TestCase4().test(5, List.of(persons1, persons2), "O(nlogn)");
    System.out.println("Using flatMap");
    new TestCase5().test(5, List.of(persons1, persons2), "O(nlogn)");
  }
}


