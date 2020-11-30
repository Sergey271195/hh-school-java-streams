package tests;

import common.Person;
import common.TestCaseClass;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCase4 extends TestCaseClass<List<Collection<Person>>> {

  @Override
  public void execute(List<Collection<Person>> persons) {
    int limit = 1000;
    var persons1 = persons.get(0);
    var persons2 = persons.get(1);
    Stream.concat(persons1.stream(), persons2.stream())
        .sorted(Comparator.comparing(Person::getCreatedAt))
        .limit(limit).collect(Collectors.toList());
  }
}
