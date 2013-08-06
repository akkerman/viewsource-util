package nl.viewsource.functional.option;

/**
 * Represents optional values. Instances of Option are either an instance of Some or None.
 *
 * @author M. Akkerman
 */
public abstract class Option<T> {
    public abstract boolean hasValue();

    /**
     * Returns the Option's value.
     * @return the value
     */
    public abstract T get();

    /**
     * Returns the option's value if the option is nonempty, otherwise return the alternative.
     * @param alternative the alternative value
     * @return a value of type T
     */
    public T getOrElse(T alternative) {
        return hasValue() ? get() : alternative;
    }

    public static <T> Some<T> Some(T value) {
        return new Some<T>(value);
    }

    public static <T> None<T> None() {
        return new None<T>();
    }

    public static <T> Option<T> wrap(T value) {
        return value == null ? new None<T>() : Some(value);
    }
}
