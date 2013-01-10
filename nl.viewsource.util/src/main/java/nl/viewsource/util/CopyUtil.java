package nl.viewsource.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CopyUtil {

    public static void copy(Object src, Object dst) {
        try {
            for (Method getter : src.getClass().getDeclaredMethods()) {
                final String getterName = getter.getName();
                if (!getterName.startsWith("get") || getter.getReturnType() == void.class)
                    continue;
                final String setterName = getterName.replaceFirst("get", "set");
                final Method setter = dst.getClass().getMethod(setterName, getter.getReturnType());
                setter.invoke(dst, getter.invoke(src)); // dst.setStuff(src.getStuff());
            }
        } catch (NoSuchMethodException e) {
            // ignore
        } catch (InvocationTargetException e) {
            // ignore
        } catch (IllegalAccessException e) {
            // ignore
        }
    }
}
