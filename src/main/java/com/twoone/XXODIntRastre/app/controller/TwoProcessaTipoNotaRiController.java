package com.twoone.XXODIntRastre.app.controller;

import com.twoone.XXODIntRastre.app.entities.TwoTipoNotaRi;
import com.twoone.XXODIntRastre.app.entities.TwoTipoNotaRiTmp;
import com.twoone.XXODIntRastre.app.request.TwoLoteIdRequest;
import com.twoone.XXODIntRastre.app.response.TwoJsonResponse;
import com.twoone.XXODIntRastre.app.response.TwoLoteIdResponse;
import com.twoone.XXODIntRastre.app.service.TwoProcessaTipoNotaRiService;
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
@RequestMapping("/api/processaTipoNotaRi")
@CrossOrigin("*")
public class TwoProcessaTipoNotaRiController {

    @Autowired
    TwoProcessaTipoNotaRiService service;
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TwoLoteIdResponse postTipoNotaRiTmp(@RequestBody List<TwoTipoNotaRiTmp> bodyRequest){
        return service.postTipoNotaRiTmp(bodyRequest);
    }
//==============================================================================================\\
    @PostMapping("/processa")
    @ResponseStatus(HttpStatus.CREATED)
    public TwoJsonResponse postTipoNotaRi(@RequestBody TwoLoteIdRequest bodyRequest){
        return service.postTipoNotaRi(bodyRequest);
    }
//==============================================================================================\\
    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TwoTipoNotaRi> getTipoNotaRi(){
        return service.getTipoNotaRi();
    }
//==============================================================================================\\
    @GetMapping("/buscar/{busca}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TwoTipoNotaRi> getTipoNotaRiPorNome(@PathVariable String busca){
        return service.getTipoNotaRiPorNome(busca);
    }
//==============================================================================================\\

}
