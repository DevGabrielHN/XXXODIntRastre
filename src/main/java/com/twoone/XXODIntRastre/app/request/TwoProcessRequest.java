package com.twoone.XXODIntRastre.app.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TwoProcessRequest {

    private String codCidade;
    private String cidade;
    private String codEstado;
    private String estado;
    private String codPais;
    private String pais;
    private String user;

}
