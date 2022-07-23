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
public class PostService {

    @Value("${post.file.address}")
    private String fileAddress;


    public void setAddressDataMap(Map<String, PostResponseBody> addressDataMap) {
        AddressDataMap = addressDataMap;
    }

    private Map<String, PostResponseBody> AddressDataMap;


    @PostConstruct
    private void init() throws IOException {
        // temp map to read file
        Map<String, PostResponseBody> addressDataMap
                = new HashMap<>();

        // read json file
        ObjectMapper mapper = new ObjectMapper();
        PostResponseBody[] personsList =  mapper.readValue(new File(fileAddress), PostResponseBody[].class);

        // store file's data in temp map
        for(PostResponseBody person : personsList){
            addressDataMap.put(person.getPostalCode(),person);
        }

        // transfer temp map to permanent map
        setAddressDataMap(addressDataMap);

    }

    public Response getPersonData(PostRequest request){

        // try to find person data from permanent map
        PostResponseBody person = AddressDataMap.get(request.getPostalCode());
        Response response = new Response();

        if (person == null) {
            String errorMessage = "Not Found";
            response.setMessage(errorMessage);
            response.setStatusCode(RequestStatus.Failed);

            PostResponseBody postResponseBody = new PostResponseBody();
            postResponseBody.setPostalCode(request.getPostalCode());

            response.setResponse(postResponseBody);

            return response;
        }

        response.setMessage("successful");
        response.setStatusCode(RequestStatus.Successful);
        response.setResponse(person);

        return response;
    }
}
