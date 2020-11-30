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

  private long count;
  private List<Person> persons = Stream.iterate(0, i -> i +1).limit(5_000_000)
          .map(id -> new Person(id, "Name " + id, Instant.now()))
          .collect(Collectors.toList());


  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream().collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут

  public String convertPersonToString(Person person) {
    return Stream.of(person.getFirstName(), person.getSecondName(), person.getMiddleName())
            .filter(Objects::nonNull).collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream().collect(Collectors.toMap(
            Person::getId, person -> convertPersonToString(person), (a, b) -> a)
    );
  }

  // есть ли совпадающие в двух коллекциях персоны?

  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    HashSet<Person> personsSet1 = new HashSet<>(persons1);
    HashSet<Person> personsSet2 = new HashSet<>(persons2);
    personsSet1.retainAll(personsSet2);
    return personsSet1.size() > 0;
  }

  public boolean hasSamePersonsModified(Collection<Person> persons1, Collection<Person> persons2) {
    //Если бы я был уверен, что каждому id соответствует уникальная персона
    Map<Integer, Person> personMap = persons1.stream()
            .collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> a));
    Optional<Person> result = persons2.stream()
            .filter(person -> personMap.get(person.getId()) == person).findFirst();
    return !result.isEmpty();
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    System.out.println("\n-------- TASK 8 ----------");
    System.out.println("\t  Final round\n");
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    checkGetNames();
    checkDifferentNames();
    checkConvertPersonToString();
    checkGetPersonsNames();
    checkHasSamePerson();
    checkCountEven();
    boolean codeSmellsGood = false;
    boolean reviewerDrunk = false;

    return codeSmellsGood || reviewerDrunk;
  }

  public void checkGetNames() {
    System.out.println("\nChecking getNames function...");
    System.out.println("Checking list of " + persons.size() + " persons");
    new TestCase13().test(5, persons, "O(n)", "Using skip");
    new TestCase14().test(5, persons, "O(n)", "Удаление элемента из начала массива - не лучшая идея");
  }

  public void checkDifferentNames() {
    System.out.println("\nChecking getDifferentNames function...");
    System.out.println("Checking list of " + persons.size() + " persons");
    new TestCase15().test(5, persons, "O(n)", "Без distinct. Во множестве и так будут содержаться только уникальные элементы");
    new TestCase16().test(5, persons, "O(n)", "С distinct. Посмотрим, как скажется на времени выполнения... ");
    System.out.println("\nПохоже, что лишний раз фильтровать не стоит");
  }

  public void checkConvertPersonToString() {
    System.out.println("\nChecking convertPersonToString function...");
    System.out.println("Checking list of " + persons.size() + " persons");
    new TestCase17().test(5, persons, "", "Старый варинат");
    new TestCase18().test(5, persons, "", "Вариант с применением стримов");
    System.out.println("\nБез применения стримов - быстрее. Но читабельность кода оставляет желать лучшего");
  }

  public void checkGetPersonsNames() {
    System.out.println("\nChecking getPersonsNames function...");
    System.out.println("Checking list of " + persons.size() + " persons");
    new TestCase19().test(5, persons, "O(n)", "Вариант с циклом for");
    new TestCase20().test(5, persons, "O(n)", "Вариант с применением стримов");
    System.out.println("\nПохоже, что со стримами и быстрее и лаконичнее");
  }

  public void checkHasSamePerson() {
    System.out.println("\nChecking hasSamePerson function...");

    new TestCase21().test(1, List.of(persons.subList(1_000_000, 2_000_000), persons.subList(0, 1_100_000)),
            "",
            "Двойной цикл for");
    new TestCase22().test(5, List.of(persons.subList(1_000_000, 2_000_000), persons.subList(0, 990_000)),
            "",
            "Пересечение множеств");
    new TestCase23().test(5, List.of(persons.subList(1_000_000, 2_000_000), persons.subList(0, 990_000)),
            "",
            "Перевод одной коллекции в Map (при услови, что каждому id соответствует уникальная персона)\nИспользование стримов и фильтров");
    System.out.println("\nИ снова стримы быстрее и лаконичнее");
  }

  public void checkCountEven() {
    System.out.println("\nChecking countEven function...");
    new TestCase24().test(5, 1, "O(n)", "Первоначальный вариант");
    new TestCase25().test(5, 1, "O(n)", "Streams.count()");
    System.out.println("\nСтримы лаконичнее");
  }

}


