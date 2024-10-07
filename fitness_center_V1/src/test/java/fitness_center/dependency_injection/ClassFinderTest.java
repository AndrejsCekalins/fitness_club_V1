package fitness_center.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class ClassFinderTest {

    @Test
    public void findClassesInsidePackage() throws IOException, ClassNotFoundException {
        ClassFinder classFinder = new ClassFinder();
        List<Class> classes = classFinder.findClassesInsidePackage("fitness_club");
        classes.forEach(aClass -> {
            System.out.println(aClass.getName());
        });
    }
}