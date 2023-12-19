import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long streamAge = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();

        List<String> streamArm = persons.stream()
                .filter(w -> w.getAge() > 17 && w.getAge() < 28)
                .filter(w -> w.getSex(Sex.MAN).equals(Sex.MAN))
                .map(Person::toString)
                .collect(Collectors.toList());

        List<Person> streamWork = persons.stream()
                .filter(w -> w.getAge() > 17 && w.getEducation(Education.HIGHER).equals(Education.HIGHER))
                .filter(w -> w.getSex(Sex.MAN).equals(Sex.MAN) ? w.getAge() < 66 : w.getAge() < 61)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
