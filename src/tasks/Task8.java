package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {


  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    //Удалять элемент из начала массива - не лучшая затея
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    //Идея только про distinct мне подсказывала))
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    //Намного лаконичнее и легче читается
    return Stream.of(person.getFirstName(), person.getSecondName(), person.getMiddleName())
            .filter(Objects::nonNull).collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    //Намного лаконичнее и легче читается
    return persons.stream().collect(Collectors.toMap(
            Person::getId, person -> convertPersonToString(person), (a, b) -> a)
    );
  }

  // есть ли совпадающие в двух коллекциях персоны?
//Совсем крастота
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
      return persons1.stream().anyMatch(person -> new HashSet<>(persons2).contains(person));
  }

  //В голову приходит только возможность изменения значения переменной в другом месте программы
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    boolean codeSmellsGood = false;
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}


