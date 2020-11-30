package tests;

import common.Person;
import common.TestCaseClass;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCase19 extends TestCaseClass<Collection<Person>> {

    public String convertPersonToString(Person person) {

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

    @Override
    public void execute(Collection<Person> persons) {
        Map<Integer, String> map = new HashMap<>(1);
        for (Person person : persons) {
            if (!map.containsKey(person.getId())) {
                map.put(person.getId(), convertPersonToString(person));
            }
        }
    }
}

