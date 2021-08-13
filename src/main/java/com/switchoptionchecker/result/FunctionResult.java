package com.switchoptionchecker.result;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Muhammed ALAGOZ
 */
@RequiredArgsConstructor(staticName = "of")
public class FunctionResult<T, R> implements ResultFunction<T, R>, Function<T, R> {
    private final Function<T, R> function;

    @Override
    public Optional<R> executeResult(T t) {
        return Optional.ofNullable(this.apply(t));
    }

    @Override
    public R apply(T t) {
        return function.apply(t);
    }
}
