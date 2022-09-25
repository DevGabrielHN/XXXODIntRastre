package com.twoone.XXODIntRastre.app.controller;

import com.twoone.XXODIntRastre.app.entities.TwoUnidadeMedida;
import com.twoone.XXODIntRastre.app.entities.TwoUnidadeMedidaTmp;
import com.twoone.XXODIntRastre.app.request.TwoLoteIdRequest;
import com.twoone.XXODIntRastre.app.response.TwoJsonResponse;
import com.twoone.XXODIntRastre.app.response.TwoLoteIdResponse;
import com.twoone.XXODIntRastre.app.service.TwoProcessaUnidadeMedidaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@RestController
@RequestMapping("/api/unidadeMedidaTmp")
@CrossOrigin("*")
public class TwoProcessaUnidadeMedidaController {

    @Autowired
    private TwoProcessaUnidadeMedidaService service;
///
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TwoLoteIdResponse postUnidadeMedidaTmp(@RequestBody List<TwoUnidadeMedidaTmp> bodyRequest){
        return service.postUnidadeMedidaTmp(bodyRequest);
    }
//==============================================================================================\\
    @PostMapping("/processa")
    @ResponseStatus(HttpStatus.CREATED)
    public TwoJsonResponse postUnidadeMedida(@RequestBody TwoLoteIdRequest bodyRequest){
        return service.postUnidadeMedida(bodyRequest);
    }
//==============================================================================================\\
    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TwoUnidadeMedida> getunidadeMedida(){
        return service.getunidadeMedida();
    }
//==============================================================================================\\
    @GetMapping("/buscar/{busca}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TwoUnidadeMedida> getunidadeMedidaPorDescr(@PathVariable String busca){
        return service.getUnidadeMedidaPorDescr(busca);
    }
}
