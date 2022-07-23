package com.shaparak.simulator.service.validator;

import com.shaparak.simulator.dto.CivilRegistryRequest;
import com.shaparak.simulator.dto.Request;
import org.springframework.stereotype.Component;
import static org.apache.commons.lang3.StringUtils.isNumeric;


@Component
public class CivilRegistryValidator extends Validator<CivilRegistryRequest>{

    @Override
    public boolean isValid(CivilRegistryRequest request) {
        boolean isNumber = isNumeric(request.getIdNumber());
        int idLength = request.getIdNumber().length();

        if (isNumber){
            return idLength == 10;
        }
        return false;
    }
}
