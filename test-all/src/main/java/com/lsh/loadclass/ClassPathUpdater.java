package com.lsh.loadclass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 将指定的 URL路径添加到classpath中
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年3月24日
 *
 */
public class ClassPathUpdater {
    /** Used to find the method signature. */
    private static final Class[] PARAMETERS = new Class[] { URL.class };

    /** Class containing the private addURL method. */
    private static final Class CLASS_LOADER = MyClassLoader.class;
//    private static final Class CLASS_LOADER = URLClassLoader.class;

    /**
     * Adds a new path to the classloader. If the given string points to a file,
     * then that file's parent file (i.e., directory) is used as the directory
     * to add to the classpath. If the given string represents a directory, then
     * the directory is directly added to the classpath.
     *
     * @param s
     * The directory to add to the classpath (or a file, which will
     * relegate to its directory).
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    public static void add(String s) throws IOException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, IllegalArgumentException {
        add(new File(s));
    }

    /**
     * Adds a new path to the classloader. If the given file object is a file,
     * then its parent file (i.e., directory) is used as the directory to add to
     * the classpath. If the given string represents a directory, then the
     * directory it represents is added.
     *
     * @param f
     * The directory (or enclosing directory if a file) to add to the classpath.
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    public static void add(File f) throws IOException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, IllegalArgumentException {
        f = f.isDirectory() ? f : f.getParentFile();
        add(f.toURI().toURL());
    }

    /**
     * Adds a new path to the classloader. The class must point to a directory,
     * not a file.
     *
     * @param url
     * The path to include when searching the classpath.
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    public static void add(URL url) throws IOException, NoSuchMethodException,
             InvocationTargetException, IllegalAccessException, IllegalArgumentException {
        Method method = CLASS_LOADER.getDeclaredMethod("addURL", PARAMETERS);
        method.setAccessible(true);
        method.invoke(getClassLoader(), new Object[] { url });
    }

    private static URLClassLoader getClassLoader() {
        return (URLClassLoader) ClassLoader.getSystemClassLoader();
    }
}