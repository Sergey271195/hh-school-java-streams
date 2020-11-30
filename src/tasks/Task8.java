package tasks;

import common.Person;
import common.Task;

import java.sql.SQLOutput;
import java.time.Instant;
import java.util.*;
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

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    if (persons.size() == 0) {
      return Collections.emptyList();
    }
    persons.remove(0);
    return persons.stream().map(Person::getFirstName).collect(Collectors.toList());
  }

  public List<String> getNamesExtended(List<Person> persons) {
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream().distinct().collect(Collectors.toSet());
  }

  public Set<String> getDifferentNamesExtended(List<Person> persons) {
    System.out.println("Idea is swearing for redundant method calls...");
    System.out.println("No distinct with sets!");
    return getNames(persons).stream().collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {

    //Expecting middle name somewhere
    String result = "";
    if (person.getSecondName() != null) {
      result += person.getSecondName();
    }

    if (person.getFirstName() != null) {
      result += " " + person.getFirstName();
    }

    if (person.getSecondName() != null) {
      result += " " + person.getSecondName();
    }
    return result;
  }

  public String convertPersonToStringExtended(Person person) {
    return Stream.of(person.getFirstName(), person.getSecondName(), person.getMiddleName())
            .filter(Objects::nonNull).collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> map = new HashMap<>(1);
    for (Person person : persons) {
      if (!map.containsKey(person.getId())) {
        map.put(person.getId(), convertPersonToString(person));
      }
    }
    return map;
  }

  public Map<Integer, String> getPersonNamesExtended(Collection<Person> persons) {
    return persons.stream().collect(Collectors.toMap(
            Person::getId, person -> convertPersonToStringExtended(person), (a, b) -> a)
    );
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    boolean has = false;
    for (Person person1 : persons1) {
      for (Person person2 : persons2) {
        if (person1.equals(person2)) {
          has = true;
        }
      }
    }
    return has;
  }

  public boolean hasSamePersonsModified(Collection<Person> persons1, Collection<Person> persons2) {
    HashSet<Person> personsSet1 = new HashSet<>(persons1);
    HashSet<Person> personsSet2 = new HashSet<>(persons2);
    personsSet1.retainAll(personsSet2);
    return personsSet1.size() > 0;
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    count = 0;
    numbers.filter(num -> num % 2 == 0).forEach(num -> count++);
    return count;
  }

  public long countEvenModified(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    System.out.println(checkGetNames());
    boolean codeSmellsGood = false;
    boolean reviewerDrunk = false;

    return codeSmellsGood || reviewerDrunk;
  }

  public String checkGetNames() {
    System.out.println("getNames function...");
    List<Person> persons = List.of(
            new Person(1, "Oleg", Instant.now()),
            new Person(2, "Vasya", Instant.now()),
            new Person(3, "Kostja", Instant.now())
    );
    System.out.println("Checking list of " + persons.size() + " persons");
    persons.stream().forEach(person-> System.out.println("Creating person: " + person.getFirstName()));
    System.out.print("Should return: ");
    persons.subList(1, persons.size()).stream()
            .map(Person::getFirstName).forEach(name -> System.out.print(name + " "));
    System.out.println("");
    return getNamesExtended(persons)
            .equals(persons.subList(1, persons.size()).stream()
                    .map(Person::getFirstName).collect(Collectors.toList())
            ) ? "Right answer" : "Wrong answer";
  }

}
