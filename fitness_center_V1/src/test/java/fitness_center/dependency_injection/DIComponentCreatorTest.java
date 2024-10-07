package fitness_center.dependency_injection;

import org.junit.Test;

import java.util.List;

public class DIComponentCreatorTest {

    @Test
    public void create() {
        ApplicationContext applicationContext = new ApplicationContext();
        ClassFinder finder = new ClassFinder();
        DIComponentFilter filter = new DIComponentFilter();
        List<Class> classList = finder.findClassesInsidePackage("fitness_club");
        List<Class> diComponents = filter.filter(classList);
        DIComponentCreator creator = new DIComponentCreator();
        creator.create(applicationContext, diComponents);
        if(diComponents.size() > 0)
            for (Class diComponent : diComponents) {
                System.out.println(applicationContext.getBean(diComponent).getClass().getName());
            }
    }
}
