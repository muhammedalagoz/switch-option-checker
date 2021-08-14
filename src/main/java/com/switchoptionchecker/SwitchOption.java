package com.switchoptionchecker;

import com.switchoptionchecker.function.CheckFunction;
import com.switchoptionchecker.function.PredicateCheck;
import com.switchoptionchecker.result.ResultFunction;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Muhammed ALAGOZ
 */
public class SwitchOption<T, R> {
    private static final SwitchOption<?, ?> EMPTY = new SwitchOption<>();

    private T value;
    private final Map<CheckFunction<T>, ResultFunction<T, R>> caseMap;

    private SwitchOption() {
        this.value = null;
        this.caseMap = new LinkedHashMap<>();
    }

    public static <T, R> SwitchOption<T, R> empty() {
        @SuppressWarnings("unchecked")
        SwitchOption<T, R> t = (SwitchOption<T, R>) EMPTY;
        return t;
    }

    private SwitchOption(T value, Map<CheckFunction<T>, ResultFunction<T, R>> caseMap) {
        this.value = value;
        this.caseMap = caseMap;
    }

    public static <T, R> SwitchOption<T, R> of() {
        return new SwitchOption<>(null, new LinkedHashMap<>());
    }

    public static <T, R> SwitchOption<T, R> of(Map<CheckFunction<T>, ResultFunction<T, R>> cases) {
        return new SwitchOption<>(null, cases);
    }

    public static <T, R> SwitchOption<T, R> of(T value) {
        return value == null ? empty() : of();
    }

    public SwitchOptionFactory<T, R> match(Predicate<T> predicate) {
        return new SwitchOptionFactory<>(caseMap, PredicateCheck.of(predicate));
    }

    public SwitchOptionFactory<T, R> match(boolean check) {
        return new SwitchOptionFactory<>(caseMap, x -> check);
    }

    public boolean isPresent() {
        return value == null;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public Optional<R> get(T value) {
        this.value = Objects.requireNonNull(value);

        for (Map.Entry<CheckFunction<T>, ResultFunction<T, R>> entry : this.caseMap.entrySet()) {
            final CheckFunction<T> key = entry.getKey();
            if (key.apply(this.value)) {
                final ResultFunction<T, R> resultFunction = this.caseMap.get(key);
                return resultFunction.executeResult(this.value);
            }
        }
        return Optional.empty();
    }
}
