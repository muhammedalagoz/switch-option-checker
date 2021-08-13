package com.switchoptionchecker;

import com.switchoptionchecker.function.CheckFunction;
import com.switchoptionchecker.result.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@RequiredArgsConstructor
public class SwitchOptionFactory<T, R> {
    private final Map<CheckFunction<T>, ResultFunction<T, R>> caseMap;
    private final CheckFunction<T> check;

    public SwitchOption<T, R> then(Supplier<R> sup) {
        caseMap.put(check, SupplierResult.of(sup));
        return SwitchOption.of(caseMap);
    }

    public SwitchOption<T, R> then(Runnable runnable) {
        caseMap.put(check, RunnableResult.of(runnable));
        return SwitchOption.of(caseMap);
    }

    public SwitchOption<T, R> then(R retVal) {
        caseMap.put(check, ReferenceResult.of(retVal));
        return SwitchOption.of(caseMap);
    }

    public SwitchOption<T, R> then(Function<T, R> retVal) {
        caseMap.put(check, FunctionResult.of(retVal));
        return SwitchOption.of(caseMap);
    }
}
