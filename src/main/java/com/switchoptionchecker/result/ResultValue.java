package com.switchoptionchecker.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Muhammed ALAGOZ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultValue<R> {
    private boolean result;
    private R returnValue;

    public ResultValue(R returnValue) {
        this.result = true;
        this.returnValue = returnValue;
    }

    public boolean isSuccess() {
        return result;
    }
}
