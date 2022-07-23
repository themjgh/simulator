package com.shaparak.simulator.rest;
import com.shaparak.simulator.dto.CivilRegistryRequest;
import com.shaparak.simulator.dto.CivilRegistryResponseBody;
import com.shaparak.simulator.dto.RequestStatus;
import com.shaparak.simulator.dto.Response;
import com.shaparak.simulator.service.CivilRegistryService;
import com.shaparak.simulator.service.validator.CivilRegistryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/civilregistry")
public class CivilRegistryController {

    @Autowired
    private CivilRegistryService civilRegistryService;

    @Autowired
    private CivilRegistryValidator civilRegistryValidator;

    @PostMapping("/")
    public Response getPersonalInfo(@RequestBody CivilRegistryRequest request){

        if (civilRegistryValidator.isValid(request)) {
            return civilRegistryService.getPersonData(request);
        } else {
            String errorMessage = "Bad Request";
            Response response = new Response();
            CivilRegistryResponseBody civilRegistryResponseBody = new CivilRegistryResponseBody();
            civilRegistryResponseBody.setIdNumber(request.getIdNumber());

            response.setMessage(errorMessage);
            response.setStatusCode(RequestStatus.BadRequest);
            response.setResponse(civilRegistryResponseBody);

            return response;
        }

    }

    }
