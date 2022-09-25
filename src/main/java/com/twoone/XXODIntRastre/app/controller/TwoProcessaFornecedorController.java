package com.twoone.XXODIntRastre.app.controller;

import com.twoone.XXODIntRastre.app.email.TwoEnviarEmail;
import com.twoone.XXODIntRastre.app.entities.TwoFornecedor;
import com.twoone.XXODIntRastre.app.entities.TwoFornecedorTmp;
import com.twoone.XXODIntRastre.app.request.TwoLoteIdRequest;
import com.twoone.XXODIntRastre.app.response.TwoJsonResponse;
import com.twoone.XXODIntRastre.app.response.TwoLoteIdResponse;
import com.twoone.XXODIntRastre.app.service.TwoProcessaFornecedorService;
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
@RequestMapping("/api/fornecedorTmp")
@CrossOrigin("*")
public class TwoProcessaFornecedorController {

    @Autowired
    TwoProcessaFornecedorService service;
//
    @Autowired
    private TwoEnviarEmail enviarEmail;
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TwoLoteIdResponse postFornecedorTmp(@RequestBody List<TwoFornecedorTmp> bodyRequest){
        return service.postFornecedorTmp(bodyRequest);
    }
//==============================================================================================\\
    @PostMapping("/processa")
    @ResponseStatus(HttpStatus.CREATED)
    public TwoJsonResponse postFornecedor(@RequestBody TwoLoteIdRequest bodyRequest){
        return service.postFornecedor(bodyRequest);
    }
//==============================================================================================\\
    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TwoFornecedor> getFornecedor(){
        return service.getFornecedor();
    }
//==============================================================================================\\
    @GetMapping("/buscar/{nome}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TwoFornecedor> getFornecedorPorNome(@PathVariable String nome){
        return service.getFornecedorPorNome(nome);
    }


}
