package mate.academy.ishop.lib;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Injector {
    private static List<Class> classList = new ArrayList<>();

    public static final String PROJECT_MAIN_PACKAGE = "mate.academy.ishop";

    static {
        try {
            classList.addAll(getClasses(PROJECT_MAIN_PACKAGE));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void injectDependencies() throws IllegalAccessException {
        for (Class certainClass : classList) {
            for (Field field : certainClass.getDeclaredFields()) {
                if (field.getDeclaredAnnotation(Inject.class) != null) {
                    Object implementation = AnnotatedClassMap
                            .getImplementation(field.getType());
                    if (implementation.getClass()
                            .getDeclaredAnnotation(Dao.class) != null
                            || implementation
                            .getClass().getDeclaredAnnotation(Service.class) != null) {
                        field.setAccessible(true);
                        field.set(null, implementation);
                    }
                }
            }
        }
    }

    private static List<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class> findClasses(File directory, String packageName)
            throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName
                        + "."
                        + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName
                                + '.'
                                + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
