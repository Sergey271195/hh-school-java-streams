package tests;

import common.Person;
import common.TestCaseClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class TestCase7 extends TestCaseClass<Collection<Person>> {

  @Override
  public void execute(Collection<Person> persons) {

    Comparator<Person> myComparator = (o1, o2) -> {

      int bySecondName = o1.getSecondName().compareTo(o2.getSecondName());
      if (bySecondName != 0) {
        return bySecondName;
      }

      int byFirstName = o1.getFirstName().compareTo(o2.getFirstName());
      if (byFirstName != 0) {
        return byFirstName;
      }

      return o1.getCreatedAt().compareTo(o2.getCreatedAt());
    };
    Collections.sort(new ArrayList<>(persons), myComparator);
  }
}
