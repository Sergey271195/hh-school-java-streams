package tests;

import common.Person;
import common.TestCaseClass;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestCase23 extends TestCaseClass<List<Collection<Person>>> {

    @Override
    public void execute(List<Collection<Person>> persons) {
        Collection<Person> persons1 = persons.get(0);
        Collection<Person> persons2 = persons.get(1);
        Map<Integer, Person> personMap = persons1.stream()
                .collect(Collectors.toMap(Person::getId, Function.identity()));
        Optional<Person> result = persons2.stream()
                .filter(person -> personMap.get(person.getId()) == person).findFirst();
        boolean answer = !result.isEmpty();
    }
}

