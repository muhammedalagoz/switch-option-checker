package io.github.switchoptionchecker.result;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * @author Muhammed ALAGOZ
 */

@RequiredArgsConstructor(staticName = "of")
public class ReferenceResult<T, R> implements ResultFunction<T, R> {
    private final R refVal;

    @Override
    public Optional<R> executeResult(T t) {
        return Optional.ofNullable(this.refVal);
    }
}
