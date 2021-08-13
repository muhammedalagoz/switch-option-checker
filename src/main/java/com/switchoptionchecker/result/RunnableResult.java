package com.switchoptionchecker.result;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * @author Muhammed ALAGOZ
 */
@RequiredArgsConstructor(staticName = "of")
public class RunnableResult<T, R> implements ResultFunction<T, R>, Runnable {
    private final Runnable runnable;

    @Override
    public Optional<R> executeResult(T t) {
        this.run();
        return Optional.empty();
    }

    @Override
    public void run() {
        runnable.run();
    }
}
