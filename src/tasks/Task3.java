package tasks;

import common.Person;
import common.Task;
import tests.TestCase6;
import tests.TestCase7;

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
    System.out.println("\n-------- TASK 3 ----------\n");
    checkExecutionTime();
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
    String[] firstName =  new String[] { "Adam", "Alex", "Aaron", "Ben", "Carl", "Dan", "David", "Edward", "Fred", "Frank", "George", "Hal", "Hank", "Ike", "John", "Jack", "Joe", "Larry", "Monte", "Matthew", "Mark", "Nathan", "Otto", "Paul", "Peter", "Roger", "Roger", "Steve", "Thomas", "Tim", "Ty", "Victor", "Walter"};
    String[] lastName = new String[] { "Anderson", "Ashwoon", "Aikin", "Bateman", "Bongard", "Bowers", "Boyd", "Cannon", "Cast", "Deitz", "Dewalt", "Ebner", "Frick", "Hancock", "Haworth", "Hesch", "Hoffman", "Kassing", "Knutson", "Lawless", "Lawicki", "Mccord", "McCormack", "Miller", "Myers", "Nugent", "Ortiz", "Orwig", "Ory", "Paiser", "Pak", "Pettigrew", "Quinn", "Quizoz", "Ramachandran", "Resnick", "Sagar", "Schickowski", "Schiebel", "Sellon", "Severson", "Shaffer", "Solberg", "Soloman", "Sonderling", "Soukup", "Soulis", "Stahl", "Sweeney", "Tandy", "Trebil", "Trusela", "Trussel", "Turco", "Uddin", "Uflan", "Ulrich", "Upson", "Vader", "Vail", "Valente", "Van Zandt", "Vanderpoel", "Ventotla", "Vogal", "Wagle", "Wagner", "Wakefield", "Weinstein", "Weiss", "Woo", "Yang", "Yates", "Yocum", "Zeaser", "Zeller", "Ziegler", "Bauer", "Baxster", "Casal", "Cataldi", "Caswell", "Celedon", "Chambers", "Chapman", "Christensen", "Darnell", "Davidson", "Davis", "DeLorenzo", "Dinkins", "Doran", "Dugelman", "Dugan", "Duffman", "Eastman", "Ferro", "Ferry", "Fletcher", "Fietzer", "Hylan", "Hydinger", "Illingsworth", "Ingram", "Irwin", "Jagtap", "Jenson", "Johnson", "Johnsen", "Jones", "Jurgenson", "Kalleg", "Kaskel", "Keller", "Leisinger", "LePage", "Lewis", "Linde", "Lulloff", "Maki", "Martin", "McGinnis", "Mills", "Moody", "Moore", "Napier", "Nelson", "Norquist", "Nuttle", "Olson", "Ostrander", "Reamer", "Reardon", "Reyes", "Rice", "Ripka", "Roberts", "Rogers", "Root", "Sandstrom", "Sawyer", "Schlicht", "Schmitt", "Schwager", "Schutz", "Schuster", "Tapia", "Thompson", "Tiernan", "Tisler" };
    Collection<Person> persons = Stream.iterate(1, i -> i+1).limit(1_000_000)
            .map(id -> new Person(
                    id,
                    firstName[(int)Math.random()*(firstName.length - 1)],
                    lastName[(int)Math.random()*(lastName.length - 1)],
                    time.plusSeconds((int)Math.random()*id)))
            .collect(Collectors.toSet());
    System.out.println("Testing execution time for lists of size " + persons.size());
    new TestCase6().test(5, persons, "", "Using streams");
    new TestCase7().test(5, persons, "", "Using Collections.sort with custom Comparator");
  }
}


