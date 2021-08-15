package io.github.switchoptionchecker.function;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 * @author Muhammed ALAGOZ
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class PredicateCheck<T> implements CheckFunction<T> {
    private final Predicate<T> predicate;

    @Override
    public boolean apply(T t) {
        return this.getPredicate().test(t);
    }
}
