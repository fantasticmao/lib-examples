package priv.mm.java8;

import java.util.Objects;

/**
 * 模拟java8 Optional
 * Created by maomao on 16-11-20.
 */
public final class AnalogOptional<T> {
    private static final AnalogOptional<?> EMPTY = new AnalogOptional<>();

    private final T value;

    private AnalogOptional() {
        this.value = null;
    }

    private AnalogOptional(T value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static <T> AnalogOptional<T> empty() {
        @SuppressWarnings("unchecked")
        AnalogOptional<T> t = (AnalogOptional<T>) EMPTY;
        return t;
    }

    public static <T> AnalogOptional<T> of(T value) {
        return new AnalogOptional<>(value);
    }

    public static <T> AnalogOptional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public T get() {
        Objects.requireNonNull(value, "No value present");
        return value;
    }

    public boolean isPresent() {
        return value == null;
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AnalogOptional)) {
            return false;
        }

        AnalogOptional<?> other = (AnalogOptional<?>) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value != null
                ? String.format("AnalogOptional[%s]", value)
                : "AnalogOptional.empty";
    }
}
