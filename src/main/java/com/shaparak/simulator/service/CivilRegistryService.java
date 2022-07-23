package com.shaparak.simulator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaparak.simulator.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CivilRegistryService {

    private Map<String, CivilRegistryResponseBody> personsDataMap;

    @Value("${civilregistery.file.address}")
    private String fileAddress;


    @PostConstruct
    private void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        personsDataMap = new HashMap<>();
        CivilRegistryResponseBody[] personsList = mapper.readValue(new File(fileAddress), CivilRegistryResponseBody[].class);
        for (CivilRegistryResponseBody person : personsList) {
            personsDataMap.put(person.getIdNumber(), person);
        }

    }

    public Response getPersonData(CivilRegistryRequest request) {

        CivilRegistryResponseBody person = personsDataMap.get(request.getIdNumber());
        Response response = new Response();

        if (person == null) {
            String errorMessage = "Not Found";
            response.setMessage(errorMessage);
            response.setStatusCode(RequestStatus.Failed);

            CivilRegistryResponseBody civilRegistryResponseBody= new CivilRegistryResponseBody();
            civilRegistryResponseBody.setIdNumber(request.getIdNumber());

            response.setResponse(civilRegistryResponseBody);

            return response;
        }

        response.setMessage("successful");
        response.setStatusCode(RequestStatus.Successful);
        response.setResponse(person);
        return response;
    }


}
