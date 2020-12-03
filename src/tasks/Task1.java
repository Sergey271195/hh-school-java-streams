package tasks;

import common.*;
import tests.TestCase1;
import tests.TestCase2;
import tests.TestCase3;

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
    List<Integer> ids = List.of(1, 2, 3);
    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }
}

