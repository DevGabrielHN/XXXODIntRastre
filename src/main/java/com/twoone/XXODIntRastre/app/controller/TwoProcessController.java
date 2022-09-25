package com.twoone.XXODIntRastre.app.controller;


import com.twoone.XXODIntRastre.app.request.TwoProcessRequest;
import com.twoone.XXODIntRastre.app.response.TwoJsonResponse;
import com.twoone.XXODIntRastre.app.service.TwoProcessService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@RestController
@RequestMapping("/api/processP")
public class TwoProcessController {

    @Autowired
    private TwoProcessService service;
//

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TwoJsonResponse postProcessP(@RequestBody TwoProcessRequest body ){
        return service.postProcessP(body);
    }
//


}
