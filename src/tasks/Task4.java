package tasks;

import common.ApiPersonDto;
import common.Person;
import common.Task;
import org.w3c.dom.ls.LSOutput;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
Задача 4
Список персон класса Person необходимо сконвертировать в список ApiPersonDto
(предположим, что это некоторый внешний формат)
Конвертер для одной персоны уже предоставлен
FYI - DTO = Data Transfer Object - распространенный паттерн, можно погуглить
 */
public class Task4 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<ApiPersonDto> convert(List<Person> persons) {
    return persons.stream().map(person -> convert(person)).collect(Collectors.toList());
  }

  private static ApiPersonDto convert(Person person) {
    ApiPersonDto dto = new ApiPersonDto();
    dto.setCreated(person.getCreatedAt().toEpochMilli());
    dto.setId(person.getId().toString());
    dto.setName(person.getFirstName());
    return dto;
  }

  @Override
  public boolean check() {
    System.out.println("\n-------- TASK 4 ----------\n");
    System.out.println("Nothing exciting here...");
    System.out.println("Skipping to the next one");
    Person person1 = new Person(1, "Name", Instant.now());
    Person person2 = new Person(2, "Name", Instant.now());
    return List.of(convert(person1), convert(person2))
        .equals(convert(List.of(person1, person2)));
  }
}
