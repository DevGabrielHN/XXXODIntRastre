package com.twoone.XXODIntRastre.app.service;


import com.twoone.XXODIntRastre.app.email.TwoEnviarEmail;
import com.twoone.XXODIntRastre.app.entities.*;
import com.twoone.XXODIntRastre.app.repository.*;
import com.twoone.XXODIntRastre.app.request.TwoLoteIdRequest;
import com.twoone.XXODIntRastre.app.request.TwoProcessRequest;
import com.twoone.XXODIntRastre.app.response.TwoJsonResponse;
import com.twoone.XXODIntRastre.app.response.TwoLoteIdResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TwoProcessaFornecedorService {

    @Autowired
    private TwoFornecedorRepository repositoryFornecedor;
    //
    @Autowired
    private TwoFornecedorTmpRepository repositoryTmp;
    //
    @Autowired
    private TwoLotesFornecedorRepository repositoryLote;
    //
    @Autowired
    private TwoBaseDadoRepository repositoryBaseDado;
    //
    @Autowired
    private XxodDeParaDetailRepository repositoryDeParaDetail;
    //
    @Autowired
    private TwoCidadeRepository repositoryCid;
    //
    @Autowired
    private TwoUnidadeRepository repositoryUnidade;
    //
    @Autowired
    private TwoFornecedorSiteRepository repositoryFornecedorSite;
    //
    @Autowired
    private TwoFornecedorContaBancariaRepository repositoryContaBancaria;
    //
    @Autowired
    private TwoSistemaOrigemRepository repositorySistemaOrigem;
    //
    // intancia para usar a funcao de processar pais/uf/cidade.
    @Autowired
    private TwoProcessService process;
    //
    @Autowired
    private TwoEnviarEmail enviaEmail;
    //
    public TwoLoteIdResponse postFornecedorTmp(List<TwoFornecedorTmp> bodyRequest){

        // Variavel de seguranca.
        boolean encerra = false;

        // Instancia da response.
        TwoLoteIdResponse response = new TwoLoteIdResponse();

        // Buscando data da execucao
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());
        //

        // Obj. da tabela de lote (instancia)
        TwoLotesFornecedor lote = new TwoLotesFornecedor();

        lote.setId(null); // AUTO

        // NUMERO_DO_LOTE = Total de lihas (unidades) + YYYYMMDDHHMMSS
        String numerosDaData = String.valueOf(sysdate)
                .replaceAll("-", "")
                .replaceAll(" ","")
                .replaceAll(":","")
                .replaceAll("\\.","")
                .substring(0,14);

        String numeroDoLote = String.valueOf(bodyRequest.size());

        numeroDoLote += numerosDaData;

        lote.setNumeroDoLote(Long.valueOf(numeroDoLote));
        //

        lote.setCreatedDate(sysdate);
        lote.setCreatedBy(bodyRequest.get(0).getUsuario()); // PEGANDO O USUARIO DO PRIMEIRO FORNECEDOR DO REQUEST
        lote.setLastUpdadeBy(bodyRequest.get(0).getUsuario()); // PEGANDO O USUARIO DO PRIMEIRO FORNECEDOR DO REQUEST
        lote.setLastUpdateDate(sysdate);


        // Lista de tmp para uso do save() no final do script.
        List<TwoFornecedorTmp> listaInsert = new ArrayList<>();
        //
        try {
            for(int i = 0; i < bodyRequest.size(); i++){


                // "Instancia da tabela tmp"
                TwoFornecedorTmp fornecedorTmp = new TwoFornecedorTmp();
                //

                fornecedorTmp.setFornTmpId(null); // AUTO
                fornecedorTmp.setInstanceId(null);
                fornecedorTmp.setFornTmpId(bodyRequest.get(i).getFornTmpId());
                fornecedorTmp.setNome(bodyRequest.get(i).getNome());
                fornecedorTmp.setTpIdentId(bodyRequest.get(i).getTpIdentId());
                fornecedorTmp.setIdentificador(bodyRequest.get(i).getIdentificador());
                fornecedorTmp.setRaiz(bodyRequest.get(i).getRaiz());
                fornecedorTmp.setFilial(bodyRequest.get(i).getFilial());
                fornecedorTmp.setDigitoVerificador(bodyRequest.get(i).getDigitoVerificador());
                fornecedorTmp.setSiteCod(bodyRequest.get(i).getSiteCod());
                fornecedorTmp.setEndereco(bodyRequest.get(i).getEndereco());
                fornecedorTmp.setNumero(bodyRequest.get(i).getNumero());
                fornecedorTmp.setComplemento(bodyRequest.get(i).getComplemento());
                fornecedorTmp.setBairro(bodyRequest.get(i).getBairro());
                fornecedorTmp.setCodEstado(bodyRequest.get(i).getCodEstado());
                fornecedorTmp.setEstado(bodyRequest.get(i).getEstado());
                fornecedorTmp.setCidId(bodyRequest.get(i).getCidId());
                fornecedorTmp.setCidade(bodyRequest.get(i).getCidade());
                fornecedorTmp.setCodCidade(bodyRequest.get(i).getCodCidade());
                fornecedorTmp.setPais(bodyRequest.get(i).getPais());
                fornecedorTmp.setCodPais(bodyRequest.get(i).getCodPais());
                fornecedorTmp.setEmail(bodyRequest.get(i).getEmail());
                fornecedorTmp.setIndDesoneracao(bodyRequest.get(i).getIndDesoneracao());
                fornecedorTmp.setIndConta(bodyRequest.get(i).getIndConta());
                fornecedorTmp.setCodBanco(bodyRequest.get(i).getCodBanco());
                fornecedorTmp.setNomeBanco(bodyRequest.get(i).getNomeBanco());
                fornecedorTmp.setCodAgencia(bodyRequest.get(i).getCodAgencia());
                fornecedorTmp.setNomeAgencia(bodyRequest.get(i).getNomeAgencia());
                fornecedorTmp.setCodConta(bodyRequest.get(i).getCodConta());
                fornecedorTmp.setDigVerifConta(bodyRequest.get(i).getDigVerifConta());
                fornecedorTmp.setDtFinal(bodyRequest.get(i).getDtFinal());
                fornecedorTmp.setUnidade(bodyRequest.get(i).getUnidade());
                fornecedorTmp.setUnidadeId(bodyRequest.get(i).getUnidadeId());
                fornecedorTmp.setUsuario(bodyRequest.get(i).getUsuario());
                fornecedorTmp.setIntegrationDate(bodyRequest.get(i).getIntegrationDate());
                fornecedorTmp.setMsg(null); // DEIXA ASSIM?
                fornecedorTmp.setStatus("NEW"); // DEIXA ASSIM?
                fornecedorTmp.setBase(bodyRequest.get(i).getBase());
                fornecedorTmp.setBaseId(bodyRequest.get(i).getBaseId());
                fornecedorTmp.setSistemaId(bodyRequest.get(i).getSistemaId());
                fornecedorTmp.setNivelConta(bodyRequest.get(i).getNivelConta());
                fornecedorTmp.setMsgConta(bodyRequest.get(i).getMsgConta());
                fornecedorTmp.setStatusConta(bodyRequest.get(i).getStatusConta());
                fornecedorTmp.setLoteId(lote);

                // Salvando na lista para usar no metodo save().
                listaInsert.add(fornecedorTmp);

            } // FOR


            repositoryLote.save(lote);

            for(int j = 0;j < listaInsert.size();j++){
                repositoryTmp.save(listaInsert.get(j));
            }

            response.setIdLote(lote.getId());

        }catch (Exception e){
            System.out.print(sysdate);
            System.out.println(" - ### ERRO! Algo deu errado ao savar fornecedorTmp  Erro: " + e);
        }

        return response;
    }
//==============================================================================================\\
    public TwoJsonResponse validaFornecedor(Long pLoteId){

        // Buscando data da execucao
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());

        // Variaveis.
        TwoBaseDado lBaseId;
        TwoSistemaOrigem lSistemaId;
        String lEstado;
        Long lCidId;
        Long lUnidadeId;
        String auxIdentificador = null;

        // Obj de retorno.
        TwoJsonResponse response = new TwoJsonResponse();

        // Obj de resposta para atualizar coluna.
        TwoJsonResponse responseColumn = new TwoJsonResponse();


        // TwoJsonResponse do cursor (cursorTmp3) da conta.
        TwoJsonResponse responseConta = new TwoJsonResponse();


        try{

            response.setStatus("SUCCESS");
            response.setMsg("Fornecedor validado com sucesso.");


            List<TwoFornecedorTmp> cursorTmp1 =
                    repositoryTmp.cursorValidaTmp1(pLoteId);


            cursorTmp1:
            for (int i = 0;i < cursorTmp1.size();i++){

                    try{
                        lBaseId = repositoryBaseDado.lBaseId(cursorTmp1.get(i).getBase());
                        lSistemaId = null;
                        if(lBaseId != null){
                            lSistemaId = repositoryBaseDado.lBaseId(cursorTmp1.get(i).getBase()).getSistemaId();
                        }

                        if(lBaseId == null || lSistemaId == null){
                                responseColumn.setStatus("ERROR");
                                responseColumn.setMsg("Base " + cursorTmp1.get(i).getBase() + " nao encontrada no Rastreabilidade");
                                //
                                System.out.print(sysdate);
                                System.out.println(" - ### ERRO! FORN_TMP_ID: "+ cursorTmp1.get(i).getFornTmpId() + " => " + "Base " + cursorTmp1.get(i).getBase() + " nao encontrada no Rastreabilidade");
                                //
                                repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                        , responseColumn.getMsg()
                                        , cursorTmp1.get(i).getIdentificador()
                                        , pLoteId);
                                // Logica para dar "response" na primeira linha de erro.
                                if(!response.getStatus().equals("ERROR")){
                                    response.setStatus(responseColumn.getStatus());
                                    response.setMsg(responseColumn.getMsg());
                                }
                                continue;
                        }
                    }catch (Exception e){
                        responseColumn.setStatus("ERROR");
                        responseColumn.setMsg("Erro ao verificar se base " +  cursorTmp1.get(i).getBase() +  " existe no Rastreabilidade. Erro: " + e);
                        //
                        System.out.print(sysdate);
                        System.out.println(" - ### ERRO! FORN_TMP_ID: "+ cursorTmp1.get(i).getFornTmpId() + " => Erro ao verificar se base " +  cursorTmp1.get(i).getBase() +  " existe no Rastreabilidade. Erro: " + e);
                        //
                        repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                , responseColumn.getMsg()
                                , cursorTmp1.get(i).getIdentificador()
                                , pLoteId);
                        // Logica para dar "response" na primeira linha de erro.
                        if(!response.getStatus().equals("ERROR")){
                            response.setStatus(responseColumn.getStatus());
                            response.setMsg(responseColumn.getMsg());
                        }
                        continue;
                    }
                    //
                    if(cursorTmp1.get(i).getNome() == null){
                        responseColumn.setStatus("ERROR");
                        responseColumn.setMsg("Campo \"Nome\" obrigatorio.");
                        //
                        System.out.print(sysdate);
                        System.out.println(" - ### ERRO! FORN_TMP_ID: "+ cursorTmp1.get(i).getFornTmpId() + " => Campo \"Nome\" obrigatorio.");
                        //
                        repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                , responseColumn.getMsg()
                                , cursorTmp1.get(i).getIdentificador()
                                , pLoteId);
                        // Logica para dar "response" na primeira linha de erro.
                        if(!response.getStatus().equals("ERROR")){
                            response.setStatus(responseColumn.getStatus());
                            response.setMsg(responseColumn.getMsg());
                        }
                        continue;
                    }
                    //
                if(cursorTmp1.get(i).getIndDesoneracao() == null){
                    responseColumn.setStatus("ERROR");
                    responseColumn.setMsg("Campo \"IND_DESONERACAO\" obrigatorio.");
                    //
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! FORN_TMP_ID: "+ cursorTmp1.get(i).getFornTmpId() + " => Campo \"IND_DESONERACAO\" obrigatorio.");
                    //
                    repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                            , responseColumn.getMsg()
                            , cursorTmp1.get(i).getIdentificador()
                            , pLoteId);
                    // Logica para dar "response" na primeira linha de erro.
                    if(!response.getStatus().equals("ERROR")){
                        response.setStatus(responseColumn.getStatus());
                        response.setMsg(responseColumn.getMsg());
                    }
                    continue;
                }
                    //
                    if(cursorTmp1.get(i).getTpIdentId() == null){
                        responseColumn.setStatus("ERROR");
                        responseColumn.setMsg("Campo \"Tp Ident Id\" obrigatorio.");
                        //
                        System.out.print(sysdate);
                        System.out.println(" - ### ERRO! FORN_TMP_ID: "+ cursorTmp1.get(i).getTpIdentId() + " => Campo \"Tp Ident Id\" obrigatorio.");
                        //
                        repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                , responseColumn.getMsg()
                                , cursorTmp1.get(i).getIdentificador()
                                , pLoteId);
                        // Logica para dar "response" na primeira linha de erro.
                        if(!response.getStatus().equals("ERROR")){
                            response.setStatus(responseColumn.getStatus());
                            response.setMsg(responseColumn.getMsg());
                        }
                        continue;
                    }
                    //
                    if(cursorTmp1.get(i).getIdentificador() == null){
                        responseColumn.setStatus("ERROR");
                        responseColumn.setMsg("Campo \"Identificador\" obrigatorio.");
                        //
                        System.out.print(sysdate);
                        System.out.println(" - ### ERRO! FORN_TMP_ID: "+ cursorTmp1.get(i).getFornTmpId() + " => Campo \"Identificador\" obrigatorio.");
                        //
                        repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                , responseColumn.getMsg()
                                , cursorTmp1.get(i).getIdentificador()
                                , pLoteId);
                        // Logica para dar "response" na primeira linha de erro.
                        if(!response.getStatus().equals("ERROR")){
                            response.setStatus(responseColumn.getStatus());
                            response.setMsg(responseColumn.getMsg());
                        }
                        continue;
                    }
                    //
                    List<TwoFornecedorTmp> cursorTmp2 =
                            repositoryTmp.cursorValidaTmp2(pLoteId,cursorTmp1.get(i).getIdentificador());
                    for(int j = 0;j <cursorTmp2.size();j++) {

                            if(cursorTmp2.get(j).getTpIdentId().equals("CPF")){

                                if(cursorTmp2.get(j).getRaiz() == null){
                                    responseColumn.setStatus("ERROR");
                                    responseColumn.setMsg("Raiz do CPF obrigatorio");
                                    //
                                    System.out.print(sysdate);
                                    System.out.println(" - ### ERRO! FORN_TMP_ID: "+ cursorTmp1.get(i).getFornTmpId() + " => Raiz do CPF obrigatorio");
                                    //
                                    repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                            , responseColumn.getMsg()
                                            , cursorTmp1.get(i).getIdentificador()
                                            , pLoteId);
                                    // Logica para dar "response" na primeira linha de erro.
                                    if(!response.getStatus().equals("ERROR")){
                                        response.setStatus(responseColumn.getStatus());
                                        response.setMsg(responseColumn.getMsg());
                                    }
                                    continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                                } // END IF
                            }else if(cursorTmp2.get(j).getTpIdentId().equals("CNPJ")) {

                                if (cursorTmp2.get(j).getRaiz() == null) {
                                    responseColumn.setStatus("ERROR");
                                    responseColumn.setMsg("Raiz do CNPJ obrigatorio");
                                    //
                                    System.out.print(sysdate);
                                    System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Raiz do CNPJ obrigatorio");
                                    //
                                    repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                            , responseColumn.getMsg()
                                            , cursorTmp1.get(i).getIdentificador()
                                            , pLoteId);
                                    // Logica para dar "response" na primeira linha de erro.
                                    if(!response.getStatus().equals("ERROR")) {
                                        response.setStatus(responseColumn.getStatus());
                                        response.setMsg(responseColumn.getMsg());
                                    }
                                    continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                                }
                                //
                                if (cursorTmp2.get(j).getFilial() == null) {
                                    responseColumn.setStatus("ERROR");
                                    responseColumn.setMsg("Filial do CNPJ obrigatorio");
                                    //
                                    System.out.print(sysdate);
                                    System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Filial do CNPJ obrigatorio");
                                    //
                                    repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                            , responseColumn.getMsg()
                                            , cursorTmp1.get(i).getIdentificador()
                                            , pLoteId);
                                    // Logica para dar "response" na primeira linha de erro.
                                    if(!response.getStatus().equals("ERROR")){
                                        response.setStatus(responseColumn.getStatus());
                                        response.setMsg(responseColumn.getMsg());
                                    }
                                    continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                                }
                                //
                                if (cursorTmp2.get(j).getDigitoVerificador() == null) {
                                    responseColumn.setStatus("ERROR");
                                    responseColumn.setMsg("Digito Verificador do CNPJ obrigatorio");
                                    //
                                    System.out.print(sysdate);
                                    System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Digito Verificador do CNPJ obrigatorio");
                                    //
                                    repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                            , responseColumn.getMsg()
                                            , cursorTmp1.get(i).getIdentificador()
                                            , pLoteId);
                                    // Logica para dar "response" na primeira linha de erro.
                                    if(!response.getStatus().equals("ERROR")){
                                        response.setStatus(responseColumn.getStatus());
                                        response.setMsg(responseColumn.getMsg());
                                    }
                                    continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                                }
                            } // ELSIF;
                            //
                            if(cursorTmp2.get(j).getCidade() == null){
                                responseColumn.setStatus("ERROR");
                                responseColumn.setMsg("Cidade obrigatoria");
                                //
                                System.out.print(sysdate);
                                System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Cidade obrigatoria");
                                //
                                repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                        , responseColumn.getMsg()
                                        , cursorTmp1.get(i).getIdentificador()
                                        , pLoteId);
                                // Logica para dar "response" na primeira linha de erro.
                                if(!response.getStatus().equals("ERROR")){
                                    response.setStatus(responseColumn.getStatus());
                                    response.setMsg(responseColumn.getMsg());
                                }
                                continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                            }
                            //
                            if(cursorTmp2.get(j).getEstado() == null){
                                responseColumn.setStatus("ERROR");
                                responseColumn.setMsg("Unidade Federativa obrigatoria");
                                //
                                System.out.print(sysdate);
                                System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId()+ " => Unidade Federativa obrigatoria");
                                //
                                repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                        , responseColumn.getMsg()
                                        , cursorTmp1.get(i).getIdentificador()
                                        , pLoteId);
                                // Logica para dar "response" na primeira linha de erro.
                                if(!response.getStatus().equals("ERROR")){
                                    response.setStatus(responseColumn.getStatus());
                                    response.setMsg(responseColumn.getMsg());
                                }
                                continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                            }
                            //
                            if(cursorTmp2.get(j).getCodEstado() == null){
                                responseColumn.setStatus("ERROR");
                                responseColumn.setMsg("Codigo da Unidade Federativa obrigatorio");
                                //
                                System.out.print(sysdate);
                                System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Codigo da Unidade Federativa obrigatorio");
                                //
                                repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                        , responseColumn.getMsg()
                                        , cursorTmp1.get(i).getIdentificador()
                                        , pLoteId);
                                // Logica para dar "response" na primeira linha de erro.
                                if(!response.getStatus().equals("ERROR")){
                                    response.setStatus(responseColumn.getStatus());
                                    response.setMsg(responseColumn.getMsg());
                                }
                                continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                            }
                            //
                            if(cursorTmp2.get(j).getPais() == null){
                                responseColumn.setStatus("ERROR");
                                responseColumn.setMsg("Pais obrigatorio");
                                //
                                System.out.print(sysdate);
                                System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Pais obrigatorio");
                                //
                                repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                        , responseColumn.getMsg()
                                        , cursorTmp1.get(i).getIdentificador()
                                        , pLoteId);
                                // Logica para dar "response" na primeira linha de erro.
                                if(!response.getStatus().equals("ERROR")){
                                    response.setStatus(responseColumn.getStatus());
                                    response.setMsg(responseColumn.getMsg());
                                }
                                continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                            }
                            //
                            if(cursorTmp2.get(j).getCodPais() == null){
                                responseColumn.setStatus("ERROR");
                                responseColumn.setMsg("Codigo do Pais obrigatorio");
                                //
                                System.out.print(sysdate);
                                System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Codigo do Pais obrigatorio");
                                //
                                repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                        , responseColumn.getMsg()
                                        , cursorTmp1.get(i).getIdentificador()
                                        , pLoteId);
                                // Logica para dar "response" na primeira linha de erro.
                                if(!response.getStatus().equals("ERROR")){
                                    response.setStatus(responseColumn.getStatus());
                                    response.setMsg(responseColumn.getMsg());
                                }
                                continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                            }
                            //
                            lEstado = cursorTmp2.get(j).getEstado();
                            //
                            if(cursorTmp2.get(j).getCodPais().equals("BR")){

                                try{
                                    lEstado =
                                            repositoryDeParaDetail.getLEstado2(cursorTmp2.get(j).getCodPais(),
                                                    cursorTmp2.get(j).getCodEstado());
                                    //
                                    if(lEstado == null){
                                        lEstado = cursorTmp2.get(j).getEstado();
                                    }
                                    //
                                }catch (Exception e){
                                    lEstado = cursorTmp2.get(j).getEstado();
                                }
                            }// IF
                            //
                            try{
                                lCidId =
                                        repositoryCid.getLCidId(cursorTmp2.get(j).getCidade()
                                                ,lEstado
                                                ,cursorTmp2.get(j).getPais());
                            }catch (Exception e){
                                try{
                                    lCidId = repositoryCid.getLCidId2(cursorTmp2.get(j).getCidade()
                                            ,lEstado
                                            ,cursorTmp2.get(j).getCodEstado()
                                            ,cursorTmp2.get(j).getPais());
                                }catch (Exception e2){
                                    responseColumn.setStatus("ERROR");
                                    responseColumn.setMsg("Ocorreu um erro ao recuperar Cidade " + cursorTmp2.get(i).getCidade() +" para o codigo do estado " + cursorTmp2.get(j).getCodEstado() + " Erro: " + e2);
                                    //
                                    System.out.print(sysdate);
                                    System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Ocorreu um erro ao recuperar Cidade " + cursorTmp2.get(j).getCidade() + " para o codigo do estado " + cursorTmp2.get(j).getCodEstado() + " Erro: " + e2);
                                    //
                                    repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                            , responseColumn.getMsg()
                                            , cursorTmp1.get(i).getIdentificador()
                                            , pLoteId);
                                    // Logica para dar "response" na primeira linha de erro.
                                    if(!response.getStatus().equals("ERROR")){
                                        response.setStatus(responseColumn.getStatus());
                                        response.setMsg(responseColumn.getMsg());
                                    }
                                    continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                                }

                            }
                            //
                            if(lCidId == null){ // WHEN no_data_found THEN

                                try{ // begin

                                    ///
                                    // obj de parametro para a funcao de procassar pais/uf/cidade.
                                    TwoProcessRequest objParmProcess = new TwoProcessRequest();

                                    objParmProcess.setCodCidade(cursorTmp2.get(j).getCodCidade());
                                    objParmProcess.setCidade(cursorTmp2.get(j).getCidade());
                                    objParmProcess.setCodEstado(cursorTmp2.get(j).getCodEstado());
                                    objParmProcess.setEstado(lEstado);
                                    objParmProcess.setCodPais(cursorTmp2.get(j).getCodPais());
                                    objParmProcess.setPais(cursorTmp2.get(j).getPais());
                                    objParmProcess.setUser(cursorTmp2.get(j).getUsuario());

                                    // Resposta da funcao de inserir pais/uf/cidade.
                                    TwoJsonResponse repostaProcess = process.postProcessP(objParmProcess);


                                    if(repostaProcess.getStatus().equals("ERROR")){
                                        responseColumn.setStatus(repostaProcess.getStatus());
                                        responseColumn.setMsg(repostaProcess.getMsg());
                                        //
                                        System.out.print(sysdate);
                                        System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => " +  repostaProcess.getMsg());
                                        //
                                        repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                                , responseColumn.getMsg()
                                                , cursorTmp1.get(i).getIdentificador()
                                                , pLoteId);
                                        // Logica para dar "response" na primeira linha de erro.
                                        if(!response.getStatus().equals("ERROR")){
                                            response.setStatus(responseColumn.getStatus());
                                            response.setMsg(responseColumn.getMsg());
                                        }
                                        continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                                    } // IF

                                    lCidId = repositoryCid.getLCidId(cursorTmp2.get(j).getCidade()
                                            ,lEstado
                                            ,cursorTmp2.get(j).getPais());
                                    //
                                    if(lCidId == null){
                                        responseColumn.setStatus("ERROR");
                                        responseColumn.setMsg("Cidade " + cursorTmp2.get(j).getCidade() + " nao encontrada para o estado  " + lEstado + "-" + cursorTmp2.get(j).getCodEstado() );
                                        //
                                        System.out.print(sysdate);
                                        System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Cidade " + cursorTmp2.get(j).getCidade() + " nao encontrada para o estado  "  + lEstado + "-" + cursorTmp2.get(j).getCodEstado());
                                        //
                                        repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                                , responseColumn.getMsg()
                                                , cursorTmp1.get(i).getIdentificador()
                                                , pLoteId);
                                        // Logica para dar "response" na primeira linha de erro.
                                        if(!response.getStatus().equals("ERROR")){
                                            response.setStatus(responseColumn.getStatus());
                                            response.setMsg(responseColumn.getMsg());
                                        }
                                        continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                                    }
                                }catch (Exception e){

                                    responseColumn.setStatus("ERROR");
                                    responseColumn.setMsg("[2] Ocorreu um erro ao recuperar Cidade " + cursorTmp2.get(j).getCidade() + " para o codigo do estado " + cursorTmp2.get(j).getCodEstado() + ". Erro: " + e);
                                    //
                                    System.out.print(sysdate);
                                    System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => [2] Ocorreu um erro ao recuperar Cidade " + cursorTmp2.get(j).getCidade() + " para o codigo do estado " + cursorTmp2.get(j).getCodEstado() + ". Erro: " + e);
                                    //
                                    repositoryTmp.atuFornecedorTmp2(responseColumn.getStatus()
                                            , responseColumn.getMsg()
                                            , cursorTmp1.get(i).getIdentificador()
                                            , pLoteId);
                                    // Logica para dar "response" na primeira linha de erro.
                                    if(!response.getStatus().equals("ERROR")){
                                        response.setStatus(responseColumn.getStatus());
                                        response.setMsg(responseColumn.getMsg());
                                    }
                                    continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                                }
                            }

                                // Contas de fornecedor a nivel de Site
                                // CursorTmp3.
                                List<TwoFornecedorTmp> cursorTmp3 =
                                        repositoryTmp.cursorValidaTmp3(pLoteId
                                                ,cursorTmp1.get(i).getIdentificador()
                                                ,cursorTmp2.get(j).getSiteCod());

                                responseConta.setStatus("SUCCESS");
                                for(int k = 0;k <cursorTmp3.size();k++) {

                                    try{

                                        if(cursorTmp3.get(k).getCodConta() == null){
                                            responseConta.setStatus("ERROR");
                                            responseConta.setMsg("Campo \"Cod Conta\" Obrigatorio");
                                            //
                                            System.out.print(sysdate);
                                            System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Campo \"Cod Conta\" Obrigatorio");
                                          //  continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                                        }

                                        //
                                        if(cursorTmp3.get(k).getCodAgencia() == null){
                                            // IF para possibilitar pegar o primeiro erro.
                                            if(!responseConta.getStatus().equals("ERROR")){
                                                responseConta.setStatus("ERROR");
                                                responseConta.setMsg("Campo \"Cod Agencia\" Obrigatoria");
                                                //
                                                System.out.print(sysdate);
                                                System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Campo \"Cod Agencia\" Obrigatoria");
                                                // continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".

                                            }
                                        }

                                        //
                                        if(cursorTmp3.get(k).getCodBanco() == null) {
                                            // IF para possibilitar pegar o primeiro erro.
                                            if (!responseConta.getStatus().equals("ERROR")) {
                                                responseConta.setStatus("ERROR");
                                                responseConta.setMsg("Campo \"Cod Banco\" Obrigatorio");
                                                //
                                                System.out.print(sysdate);
                                                System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Campo \"Cod Banco\" Obrigatorio");
                                                //  continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".
                                            }
                                        }
                                        //

                                        lUnidadeId = null;

                                        ///// Logica para funcionar o equals() mesmo se o "getNivelConta" retornar null.
                                        String auxNivelConta = cursorTmp3.get(k).getNivelConta();
                                        if(auxNivelConta == null){
                                            auxNivelConta = "NAO";
                                        }
                                        /////

                                        if(auxNivelConta.equals("SITE")/*cursorTmp3.get(k).getNivelConta().equals("SITE")*/){

                                            if(cursorTmp3.get(k).getUnidade() != null) {

                                                try {
                                                    lUnidadeId = repositoryUnidade.lUnidadeIdUo(cursorTmp3.get(k).getUnidade()
                                                            , lBaseId.getBaseId());
                                                    if (lUnidadeId == null) {
                                                        // IF para possibilitar pegar o primeiro erro.
                                                        if (!responseConta.getStatus().equals("ERROR")) {
                                                            responseConta.setStatus("ERROR");
                                                            responseConta.setMsg("Unidade \"" +   cursorTmp3.get(k).getUnidade() + "\" nao encontrada no Rastreabilidade.");
                                                            //
                                                            System.out.print(sysdate);
                                                            System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Unidade " + cursorTmp3.get(k).getUnidade() + " nao encontrada no Rastreabilidade.");
                                                            //  continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".

                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    // IF para possibilitar pegar o primeiro erro.
                                                    if (!responseConta.getStatus().equals("ERROR")) {
                                                        responseConta.setStatus("ERROR");
                                                        responseConta.setMsg("Ocorreu um erro ao validar unidade " + cursorTmp1.get(i).getFornTmpId() + ". Erro: " + e);
                                                        //
                                                        System.out.print(sysdate);
                                                        System.out.println(" - ### ERRO! FORN_TMP_ID: " + cursorTmp1.get(i).getFornTmpId() + " => Unidade Ocorreu um erro ao validader unidade " + cursorTmp3.get(k).getUnidade() + ". Erro: " + e);
                                                        //  continue cursorTmp1; // goto para dar um "continue" no "cursorTmp1".

                                                    }
                                                }
                                            }
                                        }
                                        // Atualizando unidade para a conta correspondente
                                        repositoryTmp.atuFornecedorSuccess(lUnidadeId
                                                ,cursorTmp1.get(i).getIdentificador()
                                                ,cursorTmp2.get(j).getSiteCod()
                                                ,cursorTmp2.get(j).getFornTmpId()
                                                /*,cursorTmp3.get(k).getCodBanco()
                                                ,cursorTmp3.get(k).getCodAgencia()
                                                ,cursorTmp3.get(k).getCodConta()
                                                ,cursorTmp3.get(k).getUnidade()
                                                ,cursorTmp3.get(k).getDigVerifConta() */
                                                ,pLoteId
                                                ,cursorTmp3.get(k).getNivelConta());


                                        // Atualizar caso dê erro ao processar conta.
                                        if(responseConta.getStatus().equals("ERROR")){

                                            repositoryTmp.atuFornecedorN(responseConta.getMsg()
                                                                        ,responseConta.getStatus()
                                                                        ,cursorTmp1.get(i).getIdentificador()
                                                                        ,cursorTmp2.get(j).getSiteCod()
                                                                        ,cursorTmp2.get(j).getFornTmpId()
                                                                       /* ,cursorTmp3.get(k).getCodBanco()
                                                                        ,cursorTmp3.get(k).getCodAgencia()
                                                                        ,cursorTmp3.get(k).getCodConta()
                                                                        ,cursorTmp3.get(k).getUnidade()
                                                                        ,cursorTmp3.get(k).getDigVerifConta()*/
                                                                        ,pLoteId
                                                                        ,cursorTmp3.get(k).getNivelConta());

                                        }

                                    }catch (Exception e){

                                        responseConta.setStatus("ERROR");
                                        responseConta.setMsg("Ocorreu um erro ao validar conta. Erro: " + e);


                                        repositoryTmp.atuFornecedorN(responseConta.getMsg()
                                                ,responseConta.getStatus()
                                                ,cursorTmp1.get(i).getIdentificador()
                                                ,cursorTmp2.get(j).getSiteCod()
                                                ,cursorTmp2.get(j).getFornTmpId()
                                               /* ,cursorTmp3.get(k).getCodBanco()
                                                ,cursorTmp3.get(k).getCodAgencia()
                                                ,cursorTmp3.get(k).getCodConta()
                                                ,cursorTmp3.get(k).getUnidade()
                                                ,cursorTmp3.get(k).getDigVerifConta()*/
                                                ,pLoteId
                                                ,cursorTmp3.get(k).getNivelConta());
                                    } // END;
                                } // FOR (cursorTmp3)


                                // Atualizando cidade para o site correspondente
                                repositoryTmp.atuCidIdFornecedor(lCidId
                                                                ,cursorTmp1.get(i).getIdentificador()
                                                                ,cursorTmp2.get(j).getSiteCod()
                                                                ,pLoteId);
                    }// FOR (cursorTmp2)
                        // Atualizando status e msg caso o fornecedor tenha sido validado com sucesso
                        repositoryTmp.atuFornecedorTmp(lBaseId.getBaseId()
                                                      ,lSistemaId.getSistemaId()
                                                      ,cursorTmp1.get(i).getIdentificador()
                                                      ,pLoteId);
            } // FOR (cursorTmp1).


        }catch (Exception e){

            response.setStatus("ERROR");
            response.setMsg("Ocorreu um erro no processo de criacao/atualizacao de fonrecedor. Erro: " + e);
            //
            System.out.print(sysdate);
            System.out.println(" - ### ERRO! LOTE_ID: "+ pLoteId + " => Ocorreu um erro no processo de criacao/atualizacao de fonrecedor. Erro: " + e);

        } // END;
        return response;
    }
//==============================================================================================\\
    public TwoJsonResponse postFornecedor(TwoLoteIdRequest bodyRequest){

        // Buscando data da execucao.
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());

        // Variaveis globais.
        Long indexFornId;
        String lChaveCompleta;
        Long indexFornecedorSite;
        Long indexFornecedorConta;
        Long indexFornecedorConta2;

        // Obj de retorno.
        TwoJsonResponse response = new TwoJsonResponse();
        response.setStatus("SUCCESS");
        response.setMsg("Tipo Servico criado/atualizado com sucesso.");

             try{

            // Chamando funcao para validar.
            TwoJsonResponse respostaValidaFornecedor = validaFornecedor(bodyRequest.getLoteId());
            //
            if(respostaValidaFornecedor.getStatus().equals("ERROR")){
                response.setStatus(respostaValidaFornecedor.getStatus());
                response.setMsg(respostaValidaFornecedor.getMsg());
            }
            //

            // Cursor r_frne.
            List<TwoFornecedorTmp> r_frne
                    = repositoryTmp.cursor1(bodyRequest.getLoteId());

            r_frne: // Essa label será usada para break ou continue
            for(int i = 0; i < r_frne.size();i++){

                // Obj. Fornecedor para inserir na tabela TWO_FORNECEDOR.
                TwoFornecedor fornecedor = new TwoFornecedor();

                indexFornId =
                        repositoryFornecedor.getFornIdIndex(r_frne.get(i).getIdentificador());


                if(indexFornId == null){
                    // Insert
                    fornecedor.setFornId(null); //AUTO
                    fornecedor.setNome(r_frne.get(i).getNome());
                    fornecedor.setTpIdentId(r_frne.get(i).getTpIdentId());
                    fornecedor.setIdentificador(r_frne.get(i).getIdentificador());
                    fornecedor.setBaseId(r_frne.get(i).getBaseId());
                    fornecedor.setUsuarioIdCriacao(r_frne.get(i).getUsuario());
                    fornecedor.setDtHrCriacao(sysdate);
                    fornecedor.setUsuarioIdAlter(r_frne.get(i).getUsuario());
                    fornecedor.setDtHrAlter(sysdate);

                }else{
                    // Update
                    fornecedor.setFornId(indexFornId);
                    fornecedor.setNome(r_frne.get(i).getNome());
                    fornecedor.setTpIdentId(r_frne.get(i).getTpIdentId());
                    fornecedor.setIdentificador(repositoryFornecedor.getFornecedor(indexFornId).getIdentificador());
                    fornecedor.setBaseId(r_frne.get(i).getBaseId());
                    fornecedor.setUsuarioIdCriacao(repositoryFornecedor.getFornecedor(indexFornId).getUsuarioIdCriacao());
                    fornecedor.setDtHrCriacao(repositoryFornecedor.getFornecedor(indexFornId).getDtHrCriacao());
                    fornecedor.setUsuarioIdAlter(r_frne.get(i).getUsuario());
                    fornecedor.setDtHrAlter(sysdate);
                }

                // Inserindo ou atualizando fornecedor.
                repositoryFornecedor.save(fornecedor);

                // Sites.

                // Cursor r_frne_site.
                List<TwoFornecedorTmp> r_frne_site
                        = repositoryTmp.cursor2(bodyRequest.getLoteId()
                                                ,r_frne.get(i).getIdentificador());


                for (int j = 0;j < r_frne_site.size(); j++){

                    if(r_frne.get(i).getTpIdentId().equals("CPF")){

                        lChaveCompleta = r_frne.get(i).getIdentificador() + r_frne_site.get(j).getDigitoVerificador();

                    }else if(r_frne.get(i).getTpIdentId().equals("CNPJ")){

                        lChaveCompleta = r_frne.get(i).getIdentificador() + r_frne_site.get(j).getFilial() + r_frne_site.get(j).getDigitoVerificador();

                    }else{

                        lChaveCompleta = r_frne.get(i).getIdentificador();

                    } // END IF;

                    // Verificando ID do index
                    try{
                        /// VER SE A QUERY ESTA CERTA.
                        indexFornecedorSite =
                                repositoryFornecedorSite.fornSiteIdIndex(fornecedor.getFornId()
                                                                        ,r_frne_site.get(j).getSiteCod());
                        ///
                    }catch (Exception e){
                        response.setStatus("ERROR");
                        response.setMsg("FORN_TMP_ID: " + r_frne_site.get(j).getFornTmpId() + ", Erro ao buscar FORN_SITE_ID");
                        //
                        System.out.print(sysdate);
                        System.out.println(" - ### ERRO! FORN_TMP_ID: "+ r_frne_site.get(j).getFornTmpId() + " => FORN_TMP_ID: " + r_frne_site.get(j).getFornTmpId() + ", Erro ao buscar FORN_SITE_ID");
                        continue r_frne; // goto para parar cursor principal (r_frne)
                    }

                    // Inserindo / Atualizando dados do site do fornecedor
                    TwoFornecedorSite fornecedorSite = new TwoFornecedorSite();

                   if(indexFornecedorSite == null){
                       // INSERT
                       fornecedorSite.setFornSiteId(null); //AUTO
                       fornecedorSite.setFornId(fornecedor);
                       fornecedorSite.setSiteCod(r_frne_site.get(j).getSiteCod());
                       fornecedorSite.setRaiz(r_frne_site.get(j).getRaiz());
                       fornecedorSite.setFilial(r_frne_site.get(j).getFilial());
                       fornecedorSite.setDigitoVerificador(r_frne_site.get(j).getDigitoVerificador());
                       fornecedorSite.setEndereco(r_frne_site.get(j).getEndereco());
                       fornecedorSite.setNumero(r_frne_site.get(j).getNumero());
                       fornecedorSite.setComplemeto(r_frne_site.get(j).getComplemento());
                       fornecedorSite.setBairro(r_frne_site.get(j).getBairro());
                       fornecedorSite.setCidId(repositoryCid.getCidade(r_frne_site.get(j).getCidId()));
                       fornecedorSite.setEmail(r_frne_site.get(j).getEmail());
                       fornecedorSite.setIndDesoneracao(r_frne_site.get(j).getIndDesoneracao());
                       fornecedorSite.setSistemaId(repositorySistemaOrigem.getSistemaOrigem(r_frne_site.get(j).getSistemaId()));
                       fornecedorSite.setUsuarioIdCriacao(r_frne_site.get(j).getUsuario());
                       fornecedorSite.setDtHrCriacao(sysdate);
                       fornecedorSite.setUsuarioIdAlter(r_frne_site.get(j).getUsuario());
                       fornecedorSite.setDtHrAlter(sysdate);
                       fornecedorSite.setChaveCompleta(lChaveCompleta);
                       fornecedorSite.setTpIdentId(r_frne_site.get(j).getTpIdentId());
                   }else {
                       // UPDADE
                       fornecedorSite.setFornSiteId(indexFornecedorSite);
                       fornecedorSite.setFornId(repositoryFornecedorSite.getFornecedorSite(indexFornecedorSite).getFornId());
                       fornecedorSite.setSiteCod(r_frne_site.get(j).getSiteCod());
                       fornecedorSite.setRaiz(r_frne_site.get(j).getRaiz());
                       fornecedorSite.setFilial(r_frne_site.get(j).getFilial());
                       fornecedorSite.setDigitoVerificador(r_frne_site.get(j).getDigitoVerificador());
                       fornecedorSite.setEndereco(r_frne_site.get(j).getEndereco());
                       fornecedorSite.setNumero(r_frne_site.get(j).getNumero());
                       fornecedorSite.setComplemeto(r_frne_site.get(j).getComplemento());
                       fornecedorSite.setBairro(r_frne_site.get(j).getBairro());
                       fornecedorSite.setCidId(repositoryCid.getCidade(r_frne_site.get(j).getCidId()));
                       fornecedorSite.setEmail(r_frne_site.get(j).getEmail());
                       fornecedorSite.setIndDesoneracao(r_frne_site.get(j).getIndDesoneracao());
                       fornecedorSite.setSistemaId(repositorySistemaOrigem.getSistemaOrigem(r_frne_site.get(j).getSistemaId()));
                       fornecedorSite.setUsuarioIdCriacao(repositoryFornecedorSite.getFornecedorSite(indexFornecedorSite).getUsuarioIdCriacao());
                       fornecedorSite.setDtHrCriacao(repositoryFornecedorSite.getFornecedorSite(indexFornecedorSite).getDtHrCriacao());
                       fornecedorSite.setUsuarioIdAlter(r_frne_site.get(j).getUsuario());
                       fornecedorSite.setDtHrAlter(sysdate);
                       fornecedorSite.setChaveCompleta(lChaveCompleta);
                       fornecedorSite.setTpIdentId(r_frne_site.get(j).getTpIdentId());
                   }

                   // inserindo ou atualizando TWO_FORNECEDOR_SITE
                   repositoryFornecedorSite.save(fornecedorSite);

                   // Contas de fornecedor a nivel de SITE
                   // Curdor r_frne_conta.
                   List<TwoFornecedorTmp> r_frne_conta =
                           repositoryTmp.cursor3(bodyRequest.getLoteId()
                                                ,r_frne.get(i).getIdentificador()
                                                ,r_frne_site.get(j).getSiteCod());

                   for(int k = 0; k < r_frne_conta.size();k++){


                       // Verificando ID do index
                       try{
                           /// VER SE A QUERY ESTA CERTA.
                           indexFornecedorConta =
                                   repositoryContaBancaria.fornCtaBcoIdIndex(r_frne_conta.get(k).getCodBanco()
                                   ,r_frne_conta.get(k).getCodAgencia()
                                   ,r_frne_conta.get(k).getCodConta()
                                   ,r_frne_conta.get(k).getDigVerifConta()
                                   ,r_frne_conta.get(k).getFornTmpId()
                                   ,fornecedor.getFornId()
                                   ,r_frne_conta.get(k).getUnidadeId()
                                   ,r_frne_conta.get(k).getBaseId());
                           ///
                       }catch (Exception e){
                           response.setStatus("ERROR");
                           response.setMsg("FORN_TMP_ID: " + r_frne_conta.get(k).getFornTmpId() + ", Erro ao buscar FORN_CTA_BCO_ID");
                           //
                           System.out.print(sysdate);
                           System.out.println(" - ### ERRO! FORN_TMP_ID: "+ r_frne_conta.get(k).getFornTmpId() + " => FORN_TMP_ID: " + r_frne_conta.get(k).getFornTmpId() + ", Erro ao buscar FORN_CTA_BCO_ID. ERRO: " + e);
                           continue r_frne; // goto para parar cursor principal (r_frne)
                       }

                       // Inserindo / Atualizando dados da conta bancaria
                       TwoFornecedorContaBancaria fornecedorContaBancaria = new TwoFornecedorContaBancaria();

                       if(indexFornecedorConta == null){
                           // Insert
                           fornecedorContaBancaria.setFornCtaBcoId(null); //AUTO
                           fornecedorContaBancaria.setCodAgencia(r_frne_conta.get(k).getCodAgencia());
                           fornecedorContaBancaria.setNomeAgencia(r_frne_conta.get(k).getNomeAgencia());
                           fornecedorContaBancaria.setCodBanco(r_frne_conta.get(k).getCodBanco());
                           fornecedorContaBancaria.setNomeBanco(r_frne_conta.get(k).getNomeBanco());
                           fornecedorContaBancaria.setCodConta(r_frne_conta.get(k).getCodConta());
                           fornecedorContaBancaria.setDigVerifConta(r_frne_conta.get(k).getDigVerifConta());
                           fornecedorContaBancaria.setFornId(fornecedor);
                           fornecedorContaBancaria.setFornSiteId(fornecedorSite);
                           fornecedorContaBancaria.setSistemaId(repositorySistemaOrigem.getSistemaOrigem(r_frne_conta.get(k).getSistemaId()));
                           fornecedorContaBancaria.setBaseId(r_frne_conta.get(k).getBaseId());
                           fornecedorContaBancaria.setUnidadeId(repositoryUnidade.getUnidade(r_frne_conta.get(k).getUnidadeId()));
                           fornecedorContaBancaria.setDtFinal(r_frne_conta.get(k).getDtFinal());
                           fornecedorContaBancaria.setUsuarioIdCriacao(r_frne_conta.get(k).getUsuario());
                           fornecedorContaBancaria.setDtHrCriacao(sysdate);
                           fornecedorContaBancaria.setUsuarioIdAlter(r_frne_conta.get(k).getUsuario());
                           fornecedorContaBancaria.setDtHrAlter(sysdate);
                       }else{
                            // Update
                           fornecedorContaBancaria.setFornCtaBcoId(indexFornecedorConta);
                           fornecedorContaBancaria.setCodAgencia(repositoryContaBancaria.getFornecedorContaBancaria(indexFornecedorConta).getCodAgencia());
                           fornecedorContaBancaria.setNomeAgencia(r_frne_conta.get(k).getNomeAgencia());
                           fornecedorContaBancaria.setCodBanco(repositoryContaBancaria.getFornecedorContaBancaria(indexFornecedorConta).getCodBanco());
                           fornecedorContaBancaria.setNomeBanco(r_frne_conta.get(k).getNomeBanco());
                           fornecedorContaBancaria.setCodConta(repositoryContaBancaria.getFornecedorContaBancaria(indexFornecedorConta).getCodConta());
                           fornecedorContaBancaria.setDigVerifConta(repositoryContaBancaria.getFornecedorContaBancaria(indexFornecedorConta).getDigVerifConta());
                           fornecedorContaBancaria.setFornId(repositoryContaBancaria.getFornecedorContaBancaria(indexFornecedorConta).getFornId());
                           fornecedorContaBancaria.setFornSiteId(repositoryContaBancaria.getFornecedorContaBancaria(indexFornecedorConta).getFornSiteId());
                           fornecedorContaBancaria.setSistemaId(repositorySistemaOrigem.getSistemaOrigem(r_frne_conta.get(k).getSistemaId()));
                           fornecedorContaBancaria.setBaseId(repositoryContaBancaria.getFornecedorContaBancaria(indexFornecedorConta).getBaseId());
                           fornecedorContaBancaria.setUnidadeId(repositoryUnidade.getUnidade(r_frne_conta.get(k).getUnidadeId()));
                           fornecedorContaBancaria.setDtFinal(r_frne_conta.get(k).getDtFinal());
                           fornecedorContaBancaria.setUsuarioIdCriacao(repositoryContaBancaria.getFornecedorContaBancaria(indexFornecedorConta).getUsuarioIdCriacao());
                           fornecedorContaBancaria.setDtHrCriacao(repositoryContaBancaria.getFornecedorContaBancaria(indexFornecedorConta).getDtHrCriacao());
                           fornecedorContaBancaria.setUsuarioIdAlter(r_frne_conta.get(k).getUsuario());
                           fornecedorContaBancaria.setDtHrAlter(sysdate);
                       }

                       // inserindo ou atualizando TWO_FORNECEDOR_CONTA_BANCARIA
                       repositoryContaBancaria.save(fornecedorContaBancaria);

                   } // for (r_frne_conta) "k"

                } // for (r_frne_site) "j"

               // Curdor r_frne_conta.  [2]
               List<TwoFornecedorTmp> r_frne_conta2
                        = repositoryTmp.cursor4(bodyRequest.getLoteId(),r_frne.get(i).getIdentificador());

               for(int q = 0;q < r_frne_conta2.size();q++){

                   // Verificando ID do index
                   try{
                       /// VER SE A QUERY ESTA CERTA.
                       indexFornecedorConta2 =
                               repositoryContaBancaria.fornCtaBcoIdIndex2(r_frne_conta2.get(q).getCodBanco()
                                       ,r_frne_conta2.get(q).getCodAgencia()
                                       ,r_frne_conta2.get(q).getCodConta()
                                       ,r_frne_conta2.get(q).getDigVerifConta()
                                       ,fornecedor.getFornId()
                                       ,r_frne_conta2.get(q).getBaseId());
                       ///
                   }catch (Exception e){
                       response.setStatus("ERROR");
                       response.setMsg("FORN_TMP_ID: " + r_frne_conta2.get(q).getFornTmpId() + ", Erro ao buscar FORN_CTA_BCO_ID");
                       //
                       System.out.print(sysdate);
                       System.out.println(" - ### ERRO! FORN_TMP_ID: "+ r_frne_conta2.get(q).getFornTmpId() + " => FORN_TMP_ID: " + r_frne_conta2.get(q).getFornTmpId() + ", Erro ao buscar FORN_CTA_BCO_ID");
                       continue r_frne; // goto para parar cursor principal (r_frne)
                   }
                   //
                   // Inserindo / Atualizando dados na tabela TWO_FORNECEDOR_CONTA_BANCARIA.
                   TwoFornecedorContaBancaria fornecedorContaBancaria2 = new TwoFornecedorContaBancaria();
                   if(indexFornecedorConta2 == null){
                       // Insert
                       fornecedorContaBancaria2.setFornCtaBcoId(null); // AUTO
                       fornecedorContaBancaria2.setCodAgencia(r_frne_conta2.get(q).getCodAgencia());
                       fornecedorContaBancaria2.setNomeAgencia(r_frne_conta2.get(q).getNomeAgencia());
                       fornecedorContaBancaria2.setCodBanco(r_frne_conta2.get(q).getCodBanco());
                       fornecedorContaBancaria2.setNomeBanco(r_frne_conta2.get(q).getNomeBanco());
                       fornecedorContaBancaria2.setCodConta(r_frne_conta2.get(q).getCodConta());
                       fornecedorContaBancaria2.setDigVerifConta(r_frne_conta2.get(q).getDigVerifConta());
                       fornecedorContaBancaria2.setFornId(fornecedor);
                       fornecedorContaBancaria2.setFornSiteId(null);
                       fornecedorContaBancaria2.setSistemaId(repositorySistemaOrigem.getSistemaOrigem(r_frne_conta2.get(q).getSistemaId()));
                       fornecedorContaBancaria2.setBaseId(r_frne_conta2.get(q).getBaseId());
                       fornecedorContaBancaria2.setUnidadeId(null);
                       fornecedorContaBancaria2.setDtFinal(r_frne_conta2.get(q).getDtFinal());
                       fornecedorContaBancaria2.setUsuarioIdCriacao(r_frne_conta2.get(q).getUsuario());
                       fornecedorContaBancaria2.setDtHrCriacao(sysdate);
                       fornecedorContaBancaria2.setUsuarioIdAlter(r_frne_conta2.get(q).getUsuario());
                       fornecedorContaBancaria2.setDtHrAlter(sysdate);
                   }else{
                       // Update
                       // obj de resgate de dados das colunas da linha que será atualizada.
                       TwoFornecedorContaBancaria updateContaBancaria =
                               repositoryContaBancaria.getFornecedorContaBancaria(indexFornecedorConta2);

                       fornecedorContaBancaria2.setFornCtaBcoId(indexFornecedorConta2);
                       fornecedorContaBancaria2.setCodAgencia(updateContaBancaria.getCodAgencia()); ///
                       fornecedorContaBancaria2.setNomeAgencia(r_frne_conta2.get(q).getNomeAgencia());
                       fornecedorContaBancaria2.setCodBanco(updateContaBancaria.getCodBanco()); ///
                       fornecedorContaBancaria2.setNomeBanco(r_frne_conta2.get(q).getNomeBanco());
                       fornecedorContaBancaria2.setCodConta(updateContaBancaria.getCodConta()); ///
                       fornecedorContaBancaria2.setDigVerifConta(updateContaBancaria.getDigVerifConta());
                       fornecedorContaBancaria2.setFornId(updateContaBancaria.getFornId());
                       fornecedorContaBancaria2.setFornSiteId(updateContaBancaria.getFornSiteId());
                       fornecedorContaBancaria2.setSistemaId(updateContaBancaria.getSistemaId());
                       fornecedorContaBancaria2.setBaseId(updateContaBancaria.getBaseId());
                       fornecedorContaBancaria2.setUnidadeId(repositoryUnidade.getUnidade(r_frne_conta2.get(q).getSistemaId()));
                       fornecedorContaBancaria2.setDtFinal(r_frne_conta2.get(q).getDtFinal());
                       fornecedorContaBancaria2.setUsuarioIdCriacao(updateContaBancaria.getUsuarioIdCriacao()); ///
                       fornecedorContaBancaria2.setDtHrCriacao(updateContaBancaria.getDtHrCriacao()); ///
                       fornecedorContaBancaria2.setUsuarioIdAlter(r_frne_conta2.get(q).getUsuario());
                       fornecedorContaBancaria2.setDtHrAlter(sysdate);
                   }

                   // inserindo ou atualizando TWO_FORNECEDOR_CONTA_BANCARIA
                   repositoryContaBancaria.save(fornecedorContaBancaria2);
               } // for (r_frne_conta [2]) "q"

            } // for (r_frne) "i"
       }catch (Exception e){

            response.setStatus("ERROR");
            response.setMsg("Ocorreu um erro no processo de criacao/atualizacao de fonrecedor. Erro: " + e);

        }

        /// ENVIA EMAIL CASO DÊ ERRO.
        if(response.getStatus().equals("ERROR")){
            enviaEmail.sendSimpleMessage("gabrielhnasc@outlook.com"
                    ,"(API)ERRO AO PROCESSAR FORNECEDOR",  response.getMsg());
        }



        return response;
    }
//==============================================================================================\\
    public List<TwoFornecedor> getFornecedor(){
        return repositoryFornecedor.findAll();
    }
//==============================================================================================\\
    public List<TwoFornecedor> getFornecedorPorNome(String nome){
        return repositoryFornecedor.getPorNome(nome);
    }


}
