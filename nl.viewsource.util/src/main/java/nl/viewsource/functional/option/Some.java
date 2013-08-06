package nl.viewsource.functional.option;

/**
 * Represents existing values of type T.
 *
 * @author M. Akkerman
 */
public final class Some<T> extends Option<T> {
    private final T value;



    Some(T value) {
        this.value = value;
    }

    @Override
    public boolean hasValue() {
        return true;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public String toString() {
        return "Some(" + value + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Some some = (Some) o;

        return value.equals(some.value);
    }

    @Override
    public int hashCode() {
        return 37 * value.hashCode();
    }
}
