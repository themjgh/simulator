package com.shaparak.simulator.rest;

import com.shaparak.simulator.dto.PostRequest;
import com.shaparak.simulator.dto.PostResponseBody;
import com.shaparak.simulator.dto.RequestStatus;
import com.shaparak.simulator.dto.Response;
import com.shaparak.simulator.service.PostService;
import com.shaparak.simulator.service.validator.PostRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostRequestValidator postRequestValidator;

    @PostMapping("/")
    public Response getAddress(@RequestBody PostRequest request) {
        if (postRequestValidator.isValid(request)) {
            return postService.getPersonData(request);
        } else {
            Response response = new Response();
            String errorMessage = "Bad Request";
            PostResponseBody postResponseBody = new PostResponseBody();
            postResponseBody.setPostalCode(request.getPostalCode());

            response.setMessage(errorMessage);
            response.setStatusCode(RequestStatus.BadRequest);
            response.setResponse(postResponseBody);

            return response;
        }

    }


}
