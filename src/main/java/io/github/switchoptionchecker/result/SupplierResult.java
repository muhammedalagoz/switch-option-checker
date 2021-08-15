package io.github.switchoptionchecker.result;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Muhammed ALAGOZ
 */
@RequiredArgsConstructor(staticName = "of")
public class SupplierResult<T, R> implements ResultFunction<T, R>, Supplier<R> {
    private final Supplier<R> sup;

    @Override
    public R get() {
        return this.sup.get();
    }

    @Override
    public Optional<R> executeResult(T t) {
        return Optional.ofNullable(this.get());
    }
}
