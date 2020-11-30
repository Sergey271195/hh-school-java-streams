package tests;

import common.Company;
import common.TestCaseClass;
import common.Vacancy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TestCase12 extends TestCaseClass<Collection<Company>> {

  @Override
  public void execute(Collection<Company> companies) {
    Set<String> result = new HashSet<>();
    for (Company company: companies) {
      for (Vacancy vacancy: company.getVacancies()) {
        result.add(vacancy.getTitle());
      }
    }
  }
}
