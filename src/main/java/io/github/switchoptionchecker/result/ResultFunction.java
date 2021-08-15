package io.github.switchoptionchecker.result;

import java.util.Optional;

/**
 * @author Muhammed ALAGOZ
 */
public interface ResultFunction<T, R> {
    Optional<R> executeResult(T t);
}
