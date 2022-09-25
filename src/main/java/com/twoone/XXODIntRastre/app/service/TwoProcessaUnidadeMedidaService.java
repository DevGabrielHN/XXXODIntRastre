package com.twoone.XXODIntRastre.app.service;


import com.twoone.XXODIntRastre.app.email.TwoEnviarEmail;
import com.twoone.XXODIntRastre.app.entities.*;
import com.twoone.XXODIntRastre.app.repository.*;
import com.twoone.XXODIntRastre.app.request.TwoLoteIdRequest;
import com.twoone.XXODIntRastre.app.response.TwoJsonResponse;
import com.twoone.XXODIntRastre.app.response.TwoLoteIdResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TwoProcessaUnidadeMedidaService {

    @Autowired
    private TwoUnidadeMedidaTmpRepository repositoryUnidadeMedidaTmp;
//
    @Autowired
    private TwoUnidadeMedidaRepository repositoryUnidadeMedida;
//
    @Autowired
    private TwoLotesUnidadeMedidaRepository repositoryLote;
//
    @Autowired
    private TwoBaseDadoRepository repositoryBaseDado;
//
    @Autowired
    private TwoEnviarEmail enviaEmail;
//
    public TwoLoteIdResponse postUnidadeMedidaTmp(List<TwoUnidadeMedidaTmp> bodyRequest){

        // Buscando data da execucao
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());
        //

        // "Instancia da tabela de lote"
        TwoLotesUnidadeMedida lote = new TwoLotesUnidadeMedida();

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
        lote.setCreatedBy(bodyRequest.get(0).getUsuario());
        lote.setLastUpdadeBy(bodyRequest.get(0).getUsuario());
        lote.setLastUpdateDate(sysdate);

        //

        // Lista de tmp para uso do save() no final do script.
        List<TwoUnidadeMedidaTmp> listaInsert = new ArrayList<>();

        for(int i = 0;i < bodyRequest.size();i++){

            // "Instancia da tabela tmp"
            TwoUnidadeMedidaTmp unidadeMedidaTmp = new TwoUnidadeMedidaTmp();
            //
            unidadeMedidaTmp.setUnidMedTmpId(null); // AUTO
            unidadeMedidaTmp.setCod(bodyRequest.get(i).getCod());
            unidadeMedidaTmp.setDescr(bodyRequest.get(i).getDescr());
            unidadeMedidaTmp.setBase(bodyRequest.get(i).getBase());
            unidadeMedidaTmp.setUsuario(bodyRequest.get(i).getUsuario());
            unidadeMedidaTmp.setDtFinal(bodyRequest.get(i).getDtFinal());
            unidadeMedidaTmp.setMsg(null);
            unidadeMedidaTmp.setStatus("NEW"); // DEIXA ASSIM?
            unidadeMedidaTmp.setInstanceId(bodyRequest.get(i).getInstanceId());
            unidadeMedidaTmp.setIntegrationDate(bodyRequest.get(i).getIntegrationDate());
            unidadeMedidaTmp.setLoteId(lote);

            // Salvando na lista para usar no metodo save().
            listaInsert.add(unidadeMedidaTmp);

        } // FOR

        repositoryLote.save(lote);

        for(int j = 0;j < listaInsert.size();j++){
            repositoryUnidadeMedidaTmp.save(listaInsert.get(j));
        }

        TwoLoteIdResponse response = new TwoLoteIdResponse();
        response.setIdLote(lote.getId());


        return response;
    }
//==============================================================================================\\
    public TwoJsonResponse postUnidadeMedida(TwoLoteIdRequest bodyRequest){

        TwoBaseDado lBaseId;
        Long unidMedIdIndex = null;
        String status = null;

        System.out.println("+==============================================================================+");

        // Buscando data da execucao
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());

        // status inicial;
        TwoJsonResponse response = new TwoJsonResponse();
        response.setStatus("SUCCESS");
        response.setMsg("Unidade de medida criada/atualizada com sucesso");
        //


        // Dados da tabela TWO_UNIDADE_MEDIDA_TMP
        List<TwoUnidadeMedidaTmp> cursorTmp
                = repositoryUnidadeMedidaTmp.getUnidadadeMedTmp(bodyRequest.getLoteId());

        // Lista da tabela TWO_UNIDADE_MEDIDA para realizar POST se status = "SUCCESS"
        List<TwoUnidadeMedida> listaUnidadeMedida = new ArrayList<>();


        for(int i = 0;i < cursorTmp.size();i++){

            if(cursorTmp.get(i).getCod() == null){
                response.setStatus("ERROR");
                response.setMsg("Campo \"COD\" obrigatorio.");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNID_MED_TMP_ID: "+ cursorTmp.get(i).getUnidMedTmpId() + " => " + "Campo \"COD\" obrigatorio.");
                continue;
            }

            if(cursorTmp.get(i).getDescr() == null){
                response.setStatus("ERROR");
                response.setMsg("Campo \"DESCR\" obrigatorio.");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNID_MED_TMP_ID: "+ cursorTmp.get(i).getUnidMedTmpId() + " => " + "Campo \"DESCR\" obrigatorio.");
                continue;
            }

            if(cursorTmp.get(i).getBase() == null){
                response.setStatus("ERROR");
                response.setMsg("Campo \"BASE\" obrigatorio.");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNID_MED_TMP_ID: "+ cursorTmp.get(i).getUnidMedTmpId() + " => " + "Campo \"BASE\" obrigatorio.");
                continue;
            }

            try{
                lBaseId = repositoryBaseDado.lBaseId(cursorTmp.get(i).getBase());
                if(lBaseId == null){
                    response.setStatus("ERROR");
                    response.setMsg("Base " + cursorTmp.get(i).getBase() + " nao encontrada no Rastreabilidade");
                    //
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! UNID_MED_TMP_ID: "+ cursorTmp.get(i).getUnidMedTmpId() + " => " + "Base " + cursorTmp.get(i).getBase() + " nao encontrada no Rastreabilidade");
                    continue;
                }
            }catch (Exception e){
                response.setStatus("ERROR");
                response.setMsg("Erro ao buscar Base " + cursorTmp.get(i).getBase() + ". Erro: " + e);
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNID_MED_TMP_ID: "+ cursorTmp.get(i).getUnidMedTmpId() + " => " + "Erro ao buscar Base " + cursorTmp.get(i).getBase() + ". Erro: " + e);
                continue;
            }



            // Verificando o index para ver se realiza o insert ou o update na tabela.
            try{
                unidMedIdIndex =
                        repositoryUnidadeMedida.getIndex(lBaseId.getBaseId(),cursorTmp.get(i).getCod());
            }catch (Exception e) {
                response.setStatus("ERROR");
                response.setMsg("Erro ao buscar UNID_MED_ID pelo index. Erro: " + e);
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNID_MED_TMP_ID: "+ cursorTmp.get(i).getUnidMedTmpId() + " => " + "Erro ao buscar UNID_MED_ID pelo index. Erro: " + e);
                continue;
            }

            // Inserindo / atualizando TWO_UNIDADE_MEDIDA
            // Insetancia da tabela TWO_UNIDADE_MEDIDA
            TwoUnidadeMedida unidadeMedida = new TwoUnidadeMedida();


            if(unidMedIdIndex == null){
                unidadeMedida.setUnidMedId(null); // AUTO
                unidadeMedida.setUsuarioIdCriacao(cursorTmp.get(i).getUsuario());
                unidadeMedida.setDtHrCriacao(sysdate);
            }else{
                unidadeMedida.setUnidMedId(unidMedIdIndex);
                unidadeMedida.setUsuarioIdCriacao(repositoryUnidadeMedida.getUsuarioIdCriacao(unidMedIdIndex));
                unidadeMedida.setDtHrCriacao(repositoryUnidadeMedida.getDtHrCriacao(unidMedIdIndex));
            }

            unidadeMedida.setCod(cursorTmp.get(i).getCod());
            unidadeMedida.setBaseId(lBaseId);
            unidadeMedida.setDescr(cursorTmp.get(i).getDescr());
            unidadeMedida.setDtFinal(cursorTmp.get(i).getDtFinal());


            unidadeMedida.setUsuarioIdAlter(cursorTmp.get(i).getUsuario()); // VERIFICAR ORIGEM DO USUARIO.
            unidadeMedida.setDtHrAlter(sysdate);

            listaUnidadeMedida.add(unidadeMedida);

        } // FOR (CURSOR)

        if(response.getStatus().equals("SUCCESS")){

            for(int j = 0; j < listaUnidadeMedida.size();j++){

                TwoUnidadeMedida unidadeMedida2 = listaUnidadeMedida.get(j);

                try{
                    if(unidadeMedida2.getUnidMedId() == null){
                        status = "CRIADO";
                    }else{
                        status = "ATUALIZADO";
                    }
                    repositoryUnidadeMedida.save(unidadeMedida2);
                    System.out.print(sysdate);
                    System.out.println(" - UNID_MED_ID: "  + unidadeMedida2.getUnidMedId() + " " +  status + " com sucesso!");
                }catch (Exception e){
                    response.setStatus("ERROR");
                    response.setMsg("Erro durante o processo de Insersao/Atualizado de unidade de medida. Erro: " + e);
                    //
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! UNID_MED_ID: "+ unidadeMedida2.getUnidMedId()   + " => " + "Erro durante o processo de Insersao/Atualizado de unidade de medida. Erro: " + e);

                }
                   }  // FOR

        }else {
            /// ENVIA EMAIL CASO DÊ ERRO.
            enviaEmail.sendSimpleMessage("gabrielhnasc@outlook.com"
                    ,"(API)ERRO AO PROCESSAR UNIDADE DE MEDIDA", response.getMsg());
        }



            return response;
    }
//==============================================================================================\\
    public List<TwoUnidadeMedida> getunidadeMedida(){
        return repositoryUnidadeMedida.findAll();
    }
//==============================================================================================\\
    public List<TwoUnidadeMedida> getUnidadeMedidaPorDescr(String descr){
        return repositoryUnidadeMedida.getPorDescr(descr);
    }







}
