package tasks;

import common.Person;
import common.Task;
import common.TestCaseClass;
import tests.*;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
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
    //distinct здесь излишен
    return getNames(persons).stream().collect(Collectors.toSet());
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
  //O(n*k) слишком долго
  //Добавление break не поможет, если совпадающих персон нет
  //Да и рассматриваем мы всегда worst case

  //В приведенном ниже случае асимптотика - O(n)
  //поскольку HashSet позволяет проверять наличие в нем объекта за константное время
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    HashSet<Person> personsSet1 = new HashSet<>(persons1);
    HashSet<Person> personsSet2 = new HashSet<>(persons2);
    personsSet1.retainAll(personsSet2);
    return personsSet1.size() > 0;
  }

  //Если бы я был уверен, что каждому id соответствует уникальная персона
  public boolean hasSamePersonsModified(Collection<Person> persons1, Collection<Person> persons2) {

    Map<Integer, Person> personMap = persons1.stream()
            .collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> a));
    Optional<Person> result = persons2.stream()
            .filter(person -> personMap.get(person.getId()) == person).findFirst();
    return !result.isEmpty();
  }

  //В данном случае нет необходимости вводить лишнюю перепенную
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


