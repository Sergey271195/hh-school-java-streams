package tasks;

import common.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

  //Итерация по Set<Person> для создания Map - O(n)
  //Итерация по personsId, получение объекта по ключу, добавление объекта в список - O(n)
  //Итого: Асимптотика - O(n)

  private List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = PersonService.findPersons(personIds);
    Map<Integer, Person> map = persons.stream()
            .collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> a));
    return personIds.stream().map(id -> map.get(id)).collect(Collectors.toList());
  }

  @Override
  public boolean check() {
    System.out.println("-------- TASK 1 ----------");
    List<Integer> ids = List.of(1, 2, 3);
    checkExecutionTime();
    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

  public void checkExecutionTime() {
    List<Integer> ids = Stream.iterate(1, i -> i + 1).limit(100_000).collect(Collectors.toList());
    System.out.println("Testing execution time for " + ids.size() + " ids");
    System.out.println("Assuming that findPerson time complexity is O(k)");
    new TestCase1().test(5, ids, "O(n+k)");
    new TestCase2().test(5, ids, "O(n*k)");
    new TestCase3().test(1, ids, "O(n*n+k)");
  }

}

