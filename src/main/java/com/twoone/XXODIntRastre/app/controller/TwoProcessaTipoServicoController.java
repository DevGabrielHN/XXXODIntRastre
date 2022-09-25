package com.twoone.XXODIntRastre.app.controller;


import com.twoone.XXODIntRastre.app.entities.TwoTipoServico;
import com.twoone.XXODIntRastre.app.entities.TwoTipoServicoTmp;
import com.twoone.XXODIntRastre.app.request.TwoLoteIdRequest;
import com.twoone.XXODIntRastre.app.response.TwoJsonResponse;
import com.twoone.XXODIntRastre.app.response.TwoLoteIdResponse;
import com.twoone.XXODIntRastre.app.service.TwoProcessaTipoServicoService;
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
@RequestMapping("/api/tipoServicoTmp")
public class TwoProcessaTipoServicoController {

    @Autowired
    private TwoProcessaTipoServicoService service;
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TwoLoteIdResponse postTipoServicoTmp(@RequestBody List<TwoTipoServicoTmp> bodyRequest){
        return service.postTipoServicoTmp(bodyRequest);
    }
//==============================================================================================\\
    @PostMapping("/processa")
    @ResponseStatus(HttpStatus.CREATED)
    public TwoJsonResponse postTipoServico(@RequestBody TwoLoteIdRequest bodyRequest){
        return service.postTipoServico(bodyRequest);
    }
//==============================================================================================\\
    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TwoTipoServico> getTipoServico(){
        return service.getTipoServico();
    }
//==============================================================================================\\
    @GetMapping("/buscar/{busca}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TwoTipoServico> getTipoServicoPorBusca(@PathVariable String busca){
        return service.getTipoServicoPorBusca(busca);
    }
//==============================================================================================\\
}
