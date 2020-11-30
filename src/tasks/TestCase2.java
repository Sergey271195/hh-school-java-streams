package tasks;

import common.PersonService;
import common.TestCaseClass;

import java.util.List;
import java.util.stream.Collectors;

class TestCase2 extends TestCaseClass<List<Integer>> {

  @Override
  public void execute(List<Integer> personIds) {
    personIds.stream()
            .map(id -> PersonService.findPersons(List.of(id)).stream().findAny().get())
            .collect(Collectors.toList());
  }
}
