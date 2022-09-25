package com.twoone.XXODIntRastre.app.service;

import com.twoone.XXODIntRastre.app.email.TwoEnviarEmail;
import com.twoone.XXODIntRastre.app.entities.TwoBaseDado;
import com.twoone.XXODIntRastre.app.entities.TwoLotesTipoServico;
import com.twoone.XXODIntRastre.app.entities.TwoTipoServico;
import com.twoone.XXODIntRastre.app.entities.TwoTipoServicoTmp;
import com.twoone.XXODIntRastre.app.repository.TwoBaseDadoRepository;
import com.twoone.XXODIntRastre.app.repository.TwoLotesTipoServicoRepository;
import com.twoone.XXODIntRastre.app.repository.TwoTipoServicoRepository;
import com.twoone.XXODIntRastre.app.repository.TwoTipoServicoTmpRepository;
import com.twoone.XXODIntRastre.app.request.TwoLoteIdRequest;
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
public class TwoProcessaTipoServicoService {

    @Autowired
    private TwoTipoServicoRepository repositoryTipoServico;
//
    @Autowired
    private TwoLotesTipoServicoRepository repositoryLote;
//
    @Autowired
    private TwoTipoServicoTmpRepository repositoryTmp;
//
    @Autowired
    private TwoBaseDadoRepository repositoryBaseDado;
//
    @Autowired
    private TwoEnviarEmail enviaEmail;
//
    public TwoLoteIdResponse postTipoServicoTmp(List<TwoTipoServicoTmp> bodyRequest){

        // Buscando data da execucao
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());
        //

        // "Instancia da tabela de lote"
        TwoLotesTipoServico lote = new TwoLotesTipoServico();

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
        lote.setCreatedBy(bodyRequest.get(0).getUsuario()); // PEGANDO O USUARIO DO PRIMEIRO TIPO DE SERVICO DO REQUEST
        lote.setLastUpdadeBy(bodyRequest.get(0).getUsuario()); // PEGANDO O USUARIO DO PRIMEIRO TIPO DE SERVICO DO REQUEST
        lote.setLastUpdateDate(sysdate);


        // Lista de tmp para uso do save() no final do script.
        List<TwoTipoServicoTmp> listaInsert = new ArrayList<>();

        for(int i = 0; i < bodyRequest.size(); i++){


            // "Instancia da tabela tmp"
            TwoTipoServicoTmp tipoServicoTmp = new TwoTipoServicoTmp();
            //

            tipoServicoTmp.setTpServTmpId(null); // AUTO
            tipoServicoTmp.setDescr(bodyRequest.get(i).getDescr());
            tipoServicoTmp.setCod(bodyRequest.get(i).getCod());

            tipoServicoTmp.setDtFinal(bodyRequest.get(i).getDtFinal());

            if(bodyRequest.get(i).getOrigem() == null){
                tipoServicoTmp.setOrigem("");
            }else{
                tipoServicoTmp.setOrigem(bodyRequest.get(i).getOrigem());
            }



            tipoServicoTmp.setTpServIdIntegracao(bodyRequest.get(i).getTpServIdIntegracao());
            tipoServicoTmp.setPercMat(bodyRequest.get(i).getPercMat());
            tipoServicoTmp.setPercEqp(bodyRequest.get(i).getPercEqp());
            tipoServicoTmp.setPercServ(bodyRequest.get(i).getPercServ());
            tipoServicoTmp.setBase(bodyRequest.get(i).getBase());
            tipoServicoTmp.setBaseId(bodyRequest.get(i).getBaseId());
            tipoServicoTmp.setUsuario(bodyRequest.get(i).getUsuario());
            tipoServicoTmp.setIntegrationDate(sysdate); // Data atual?
            tipoServicoTmp.setInstanceId(bodyRequest.get(i).getInstanceId());
            tipoServicoTmp.setMsg(null);
            tipoServicoTmp.setStatus("NEW");
            tipoServicoTmp.setLoteId(lote);
            // ?
            tipoServicoTmp.setCreatedDate(sysdate);
            tipoServicoTmp.setCreatedBy(bodyRequest.get(i).getUsuario()); // CERTO?
            tipoServicoTmp.setLastUpdateDate(sysdate);
            tipoServicoTmp.setLastUpdadeBy(bodyRequest.get(i).getUsuario()); // CERTO?



            // Salvando na lista para usar no metodo save().
            listaInsert.add(tipoServicoTmp);










        } //FOR

        repositoryLote.save(lote);

        for(int j = 0;j < listaInsert.size();j++){
            repositoryTmp.save(listaInsert.get(j));
        }

        TwoLoteIdResponse response = new TwoLoteIdResponse();
        response.setIdLote(lote.getId());

        return response;
    }
//==============================================================================================\\
    public TwoJsonResponse postTipoServico(TwoLoteIdRequest loteid){

        System.out.println("+==============================================================================+");

        TwoBaseDado lBaseId;
        Long tpServIdIndex;
        String status = null;

        // Buscando data da execucao
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());

        // Dados da query do cursor.
        List<TwoTipoServicoTmp> cursorTmp =
                repositoryTmp.getTipoServicoTmp(loteid.getLoteId());

        // Lista da tabela TWO_TIPO_SERVICO para realizar POST se status = "SUCCESS"
        List<TwoTipoServico> listaTipoServico = new ArrayList<>();

        // Obj de retorno.
        TwoJsonResponse response = new TwoJsonResponse();
        response.setStatus("SUCCESS");
        response.setMsg("Tipo Servico criado/atualizado com sucesso.");

        // Cursor.
        for(int i = 0; i < cursorTmp.size(); i++){

            if(cursorTmp.get(i).getCod() == null){
                response.setStatus("ERROR");
                response.setMsg("Campo \"COD\" Obrigatorio.");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! TP_SERV_TMP_ID: "+ cursorTmp.get(i).getTpServTmpId() + " => " + "Campo \"COD\" obrigatorio.");
                continue;
            }

            if(cursorTmp.get(i).getDescr() == null){
                response.setStatus("ERROR");
                response.setMsg("Campo \"DESCR\" Obrigatorio.");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! TP_SERV_TMP_ID: "+ cursorTmp.get(i).getTpServTmpId() + " => " + "Campo \"DESCR\" Obrigatorio.");
                continue;
            }

            if(cursorTmp.get(i).getUsuario() == null){
                response.setStatus("ERROR");
                response.setMsg("Campo \"USUARIO\" Obrigatorio.");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! TP_SERV_TMP_ID: "+ cursorTmp.get(i).getTpServTmpId() + " => Campo \"USUARIO\" Obrigatorio.");
                continue;
            }

            try{
                lBaseId = repositoryBaseDado.lBaseId(cursorTmp.get(i).getBase());
                if(lBaseId == null){
                    response.setStatus("ERROR");
                    response.setMsg("Base " + cursorTmp.get(i).getBase() + " nao encontrada no Rastreabilidade");
                    //
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! TP_SERV_TMP_ID: "+ cursorTmp.get(i).getTpServTmpId() + " => " + "Base " + cursorTmp.get(i).getBase() + " nao encontrada no Rastreabilidade");
                    continue;
                }
            }catch (Exception e){
                response.setStatus("ERROR");
                response.setMsg("Erro ao buscar Base " + cursorTmp.get(i).getBase() + ". Erro: " + e);
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! TP_SERV_TMP_ID: "+ cursorTmp.get(i).getTpServTmpId() + " => Erro ao buscar Base " + cursorTmp.get(i).getBase() + ". Erro: " + e);
                continue;
            }

            try{
                tpServIdIndex = repositoryTipoServico.getIndex(cursorTmp.get(i).getCod(),cursorTmp.get(i).getOrigem());
            }catch (Exception e){
                response.setStatus("ERROR");
                response.setMsg("Erro ao buscar TP_SERV_ID pelo index. Erro: " + e);
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! TP_SERV_TMP_ID: " + cursorTmp.get(i).getTpServTmpId() + " => Erro ao buscar UNID_MED_ID pelo index. Erro: " + e);
                continue;
            }

            // Insetancia da tabela TWO_TIPO_SERVICO.
            TwoTipoServico tipoServico = new TwoTipoServico();


            if(tpServIdIndex == null){
                tipoServico.setTpServId(null); // AUTO
                tipoServico.setDescr(cursorTmp.get(i).getDescr());
                tipoServico.setUsuarioIdCriacao(cursorTmp.get(i).getUsuario());
                tipoServico.setDtHrCriacao(sysdate);
                tipoServico.setUsuarioIdAlter(cursorTmp.get(i).getUsuario());
                tipoServico.setDtHrAlter(sysdate);
                tipoServico.setCod(cursorTmp.get(i).getCod());
                tipoServico.setDtFinal(cursorTmp.get(i).getDtFinal());
                tipoServico.setOrigem("1"); // ORIGEM ORACLE
                tipoServico.setTpServIdIntegracao(null);
                tipoServico.setPercMat(null);
                tipoServico.setPercEqp(null);
                tipoServico.setPercServ(null);
            }else{
                // Buscando dados da linha para atualizar.
                TwoTipoServico attTipoServico = new TwoTipoServico();
                attTipoServico = repositoryTipoServico.getAll(tpServIdIndex);
                //
                tipoServico.setTpServId(tpServIdIndex);
                tipoServico.setDescr(attTipoServico.getDescr());
                tipoServico.setUsuarioIdCriacao(attTipoServico.getUsuarioIdCriacao());
                tipoServico.setDtHrCriacao(attTipoServico.getDtHrCriacao());
                tipoServico.setUsuarioIdAlter(cursorTmp.get(i).getUsuario());
                tipoServico.setDtHrAlter(sysdate);
                tipoServico.setCod(attTipoServico.getCod());
                tipoServico.setDtFinal(cursorTmp.get(i).getDtFinal());
                tipoServico.setOrigem(attTipoServico.getOrigem()); // ORIGEM ORACLE
                tipoServico.setTpServIdIntegracao(attTipoServico.getTpServIdIntegracao());
                tipoServico.setPercMat(attTipoServico.getPercMat());
                tipoServico.setPercEqp(attTipoServico.getPercEqp());
                tipoServico.setPercServ(attTipoServico.getPercServ());
            }

            listaTipoServico.add(tipoServico);

        } // FOR


        if(response.getStatus().equals("SUCCESS")){


            for(int j = 0;j < listaTipoServico.size();j++){

                TwoTipoServico tipoServico2 = listaTipoServico.get(j);
                try{
                    if(tipoServico2.getTpServId() == null){
                        status = "CRIADO";
                    }else{
                        status = "ATUALIZADO";
                    }
                    repositoryTipoServico.save(tipoServico2);
                    System.out.print(sysdate);
                    System.out.println(" - TP_SERV_ID: "  + tipoServico2.getTpServId() + " " +  status + " com sucesso!");
                }catch (Exception e){
                    response.setStatus("ERROR");
                    response.setMsg("Erro durante o processo de Insersao/Atualizado de tipo de servico. Erro: " + e);
                    //
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! TP_SERV_ID: "+ tipoServico2.getTpServId()  + " => Erro durante o processo de Insersao/Atualizado de tipo de servico. Erro: " + e);
                }
            } // FOR

            // Atualizando tabela tmp.
            for(int k = 0; k < cursorTmp.size();k++){

                try{
                    lBaseId = repositoryBaseDado.lBaseId(cursorTmp.get(k).getBase());
                    cursorTmp.get(k).setBaseId(lBaseId.getBaseId());
                }catch (Exception e){
                    System.out.print(""); // Não fazer nada.
                }

                cursorTmp.get(k).setStatus(response.getStatus());
                cursorTmp.get(k).setMsg(response.getMsg());


                repositoryTmp.save(cursorTmp.get(k));
            }



        } else {
            /// ENVIA EMAIL CASO DÊ ERRO.
            enviaEmail.sendSimpleMessage("gabrielhnasc@outlook.com"
                    ,"(API)ERRO AO PROCESSAR TIPO DE SERVICO",  response.getMsg());
        }




        System.out.println("+==============================================================================+");

        return response;
    }
//==============================================================================================\\
    public List<TwoTipoServico> getTipoServico(){
        return repositoryTipoServico.findAll();
    }
//==============================================================================================\\
    public List<TwoTipoServico> getTipoServicoPorBusca(String busca){
        return repositoryTipoServico.getTipoServicoPorBusca(busca);
    }
}
