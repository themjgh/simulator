package com.shaparak.simulator.service.validator;
import com.shaparak.simulator.dto.PostRequest;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Component
public class PostRequestValidator extends Validator<PostRequest> {

    @Override
    public boolean isValid(PostRequest request) {
        return isNumeric(request.getPostalCode());
    }
}
