package nl.viewsource.functional.option;

/**
 * Represents the absence of a value.
 *
 * @author M. Akkerman
 */
public final class None<T> extends Option<T> {

    public static class NoneHasNoValue extends RuntimeException {
    }

    public None() {
    }

    @Override
    public boolean hasValue() {
        return false;
    }

    @Override
    public T get() {
        throw new NoneHasNoValue();
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == None.class;
    }

    @Override
    public int hashCode() {
        return -1;
    }
}
