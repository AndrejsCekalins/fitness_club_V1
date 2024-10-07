package fitness_center.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DIComponentFilterTest {

    @Test
    public void filter() throws IOException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        DIComponentFilter filter = new DIComponentFilter();
        List<Class> classList = finder.findClassesInsidePackage("fitness_club");
        List<Class> diComponent = filter.filter(classList);
        diComponent.forEach(aClass -> {
            System.out.println(aClass.getName());
        });
    }
}