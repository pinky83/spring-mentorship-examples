package org.pinky83.custom.scanner;

import org.pinky83.custom.CustomComponent;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class CustomBeanScanner {
    private static final String COMPONENT_SCAN_PATH = "org.pinky83.custom.component";

    private final Class[] beanAnnotations = new Class []{CustomComponent.class};

    public void scanComponents() {
        List<Class<?>> classes = getClassesInPackage(COMPONENT_SCAN_PATH);

        classes.forEach(clazz -> {
            if (isComponent(clazz)) {
                try {
                    CustomBeanFactory.addBean(clazz);
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isComponent(Class<?> clazz) {
        return Arrays.stream(beanAnnotations)
                .anyMatch(clazz::isAnnotationPresent);
    }

    private static List<Class<?>> getClassesInPackage(String packageName) {
        String path = packageName.replaceAll("\\.", File.separator);
        List<Class<?>> classes = new ArrayList<>();
        String[] classPathEntries = System.getProperty("java.class.path").split(
                System.getProperty("path.separator")
        );

        String name;
        for (String classpathEntry : classPathEntries) {
            if (classpathEntry.endsWith(".jar")) {
                File jar = new File(classpathEntry);
                try {
                    JarInputStream is = new JarInputStream(new FileInputStream(jar));
                    JarEntry entry;
                    while((entry = is.getNextJarEntry()) != null) {
                        name = entry.getName();
                        if (name.endsWith(".class")) {
                            if (name.contains(path) && name.endsWith(".class")) {
                                String classPath = name.substring(0, entry.getName().length() - 6);
                                classPath = classPath.replaceAll("[|/]", ".");
                                classes.add(Class.forName(classPath));
                            }
                        }
                    }
                } catch (Exception ex) {
                    // Silence is gold
                }
            } else {
                try {
                    File base = new File(classpathEntry + File.separatorChar + path);
                    for (File file : Objects.requireNonNull(base.listFiles())) {
                        name = file.getName();
                        if (name.endsWith(".class")) {
                            name = name.substring(0, name.length() - 6);
                            classes.add(Class.forName(packageName + "." + name));
                        }
                    }
                } catch (Exception ex) {
                    // Silence is gold
                }
            }
        }

        return classes;
    }
}
