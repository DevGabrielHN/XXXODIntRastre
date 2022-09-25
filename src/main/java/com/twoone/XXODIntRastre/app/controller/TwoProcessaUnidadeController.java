package com.twoone.XXODIntRastre.app.controller;

import com.twoone.XXODIntRastre.app.entities.TwoUnidade;
import com.twoone.XXODIntRastre.app.entities.TwoUnidadeTmp;
import com.twoone.XXODIntRastre.app.request.TwoLoteIdRequest;
import com.twoone.XXODIntRastre.app.response.TwoJsonResponse;
import com.twoone.XXODIntRastre.app.response.TwoLoteIdResponse;
import com.twoone.XXODIntRastre.app.service.TwoProcessaUnidadeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@RestController
@CrossOrigin("*")
@RequestMapping("/api/processaUnidadesTmp")
public class TwoProcessaUnidadeController {

    @Autowired
    private TwoProcessaUnidadeService service;
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TwoLoteIdResponse postUnidadeTmp(@RequestBody List<TwoUnidadeTmp> bodyRequest){
        return service.postUnidadeTmp(bodyRequest);
    }
//==============================================================================================\\
    @PostMapping("/processa")
    @ResponseStatus(HttpStatus.CREATED)
    public TwoJsonResponse postUnidade(@RequestBody TwoLoteIdRequest bodyRequest) {
    return service.postUnidade(bodyRequest);
    }
//==============================================================================================\\
    @GetMapping("/busca")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TwoUnidade> getunidade(){
        return service.getUnidade();
    }
//==============================================================================================\\
    @GetMapping("busca/{busca}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TwoUnidade> getUnidadePorBusca(@PathVariable String busca){
        return service.getUnidadePorBusca(busca);
    }

}
