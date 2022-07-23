package com.shaparak.simulator.service.validator;

import com.shaparak.simulator.dto.Request;

public interface IValidator<T extends Request> {
    public boolean isValid(T request);
}
