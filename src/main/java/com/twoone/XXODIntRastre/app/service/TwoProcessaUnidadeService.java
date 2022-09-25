package com.twoone.XXODIntRastre.app.service;

import com.twoone.XXODIntRastre.app.email.TwoEnviarEmail;
import com.twoone.XXODIntRastre.app.entities.*;
import com.twoone.XXODIntRastre.app.repository.TwoBaseDadoRepository;
import com.twoone.XXODIntRastre.app.repository.TwoLoteUnidadeRepository;
import com.twoone.XXODIntRastre.app.repository.TwoUnidadeRepository;
import com.twoone.XXODIntRastre.app.repository.TwoUnidadeTmpRepository;
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
public class TwoProcessaUnidadeService {

    @Autowired
    private TwoUnidadeRepository repository;
    //
    @Autowired
    private TwoUnidadeTmpRepository repositoryTmp;
    //

    @Autowired
    private TwoBaseDadoRepository repositoryBaseDado;
    //
    @Autowired
    private TwoLoteUnidadeRepository repositoryLote;
    //
    @Autowired
    private TwoEnviarEmail enviaEmail;
    //
    public TwoLoteIdResponse postUnidadeTmp(List<TwoUnidadeTmp> bodyRequest){

        // Pegando a data de execucao.
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());
        //

        // "Instancia da tabela de lote"
        TwoLotesUnidades lote = new TwoLotesUnidades();

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
        List<TwoUnidadeTmp> listaInsert = new ArrayList<>();


        for(int i = 0;i < bodyRequest.size();i++){

            // "Instancia da tabela tmp"
            TwoUnidadeTmp unidadeTmp = new TwoUnidadeTmp();

            unidadeTmp.setUnidadeTmpId(null); // AUTO
            unidadeTmp.setCod(bodyRequest.get(i).getCod());
            unidadeTmp.setDescr(bodyRequest.get(i).getDescr());
            unidadeTmp.setTipo(bodyRequest.get(i).getTipo());
            unidadeTmp.setIdentificador(bodyRequest.get(i).getIdentificador());
            unidadeTmp.setDtFinal(bodyRequest.get(i).getDtFinal());
            unidadeTmp.setUoDessaOi(bodyRequest.get(i).getUoDessaOi());
            unidadeTmp.setOrganizationCode(bodyRequest.get(i).getOrganizationCode());
            unidadeTmp.setBase(bodyRequest.get(i).getBase());
            unidadeTmp.setCidade(bodyRequest.get(i).getCidade());
            unidadeTmp.setCodCidade(bodyRequest.get(i).getCodCidade());
            unidadeTmp.setEstado(bodyRequest.get(i).getEstado());
            unidadeTmp.setCodEstado(bodyRequest.get(i).getCodEstado());
            unidadeTmp.setPais(bodyRequest.get(i).getPais());
            unidadeTmp.setCodPais(bodyRequest.get(i).getCodPais());
            unidadeTmp.setUsuario(bodyRequest.get(i).getUsuario());
            unidadeTmp.setIntegrationDate(bodyRequest.get(i).getIntegrationDate());
            unidadeTmp.setInstanceId(bodyRequest.get(i).getInstanceId());
            unidadeTmp.setMsg(null);
            unidadeTmp.setStatus("NEW");
            unidadeTmp.setCreatedBy(bodyRequest.get(i).getUsuario());
            unidadeTmp.setCreatedDate(sysdate);
            unidadeTmp.setLastUpdadeBy(bodyRequest.get(i).getUsuario());
            unidadeTmp.setLastUpdateDate(sysdate);
            unidadeTmp.setLoteId(lote);

            // Salvando na lista para usar no metodo save().
            listaInsert.add(unidadeTmp);

        } // FOR

        repositoryLote.save(lote);

        for(int j = 0;j < listaInsert.size();j++){
            repositoryTmp.save(listaInsert.get(j));
        }

        TwoLoteIdResponse response = new TwoLoteIdResponse();
        response.setIdLote(lote.getId());


        return response;
    }
//==============================================================================================\\
    public TwoJsonResponse postUnidade(TwoLoteIdRequest bodyRequest){

        TwoBaseDado lBaseId;
        Long lCidId;
        String lEstado;
        Long lUnidadeIdUo;
        Long unidadeIdIndex;

        // Pegando a data de execucao.
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());

        // Retorno da requisicao(Json).
        List<TwoUnidade> listaRetorno = new ArrayList<>();

        // Linhas do cursor.
        List<TwoUnidadeTmp> unidadeTmp =
                repositoryTmp.getUnidadeTmp(bodyRequest.getLoteId());

        // Linha do cursor.
        TwoUnidadeTmp twoUnidadeTmp;

        // Lista para guardar dados e fazer insert/updade se der tudo ok.
        List<TwoUnidade> listaUnidades = new ArrayList<>();

        // obj de responsa.
        TwoJsonResponse response = new TwoJsonResponse();
        response.setStatus("SUCCESS");
        response.setMsg("Unidade criada/atualizada com sucesso.");


        for (int i = 0; i < unidadeTmp.size(); i++) {

            twoUnidadeTmp = unidadeTmp.get(i);

            //  Validação de campos obrigatorios.
            if(twoUnidadeTmp.getCod() == null){
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + ",Valor obrigatorio para COD");
                //
                response.setStatus("ERROR");
                response.setMsg("Valor obrigatorio para COD");
                break;
            }

            if(twoUnidadeTmp.getDescr() == null){
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + ",Valor obrigatorio para DESCR");
                //
                response.setStatus("ERROR");
                response.setMsg("Valor obrigatorio para DESCR");
                break;
            }

            if(twoUnidadeTmp.getTipo() == null){
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + ",Valor obrigatorio para TIPO");
                //
                response.setStatus("ERROR");
                response.setMsg("Valor obrigatorio para TIPO");
                break;
            }

            if(twoUnidadeTmp.getBase() == null){
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + ",Valor obrigatorio para BASE");
                //
                response.setStatus("ERROR");
                response.setMsg("Valor obrigatorio para BASE");
                break;
            }

            if(twoUnidadeTmp.getUsuario() == null){
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + ",Valor obrigatorio para USUARIO");
                //
                response.setStatus("ERROR");
                response.setMsg("Valor obrigatorio para USUARIO");
                break;
            }

            try{
                lBaseId =
                        repositoryBaseDado.lBaseId(twoUnidadeTmp.getBase());
                if(lBaseId == null){
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + ".  Base " + twoUnidadeTmp.getBase() + " nao encontrada." );
                    //
                    response.setStatus("ERROR");
                    response.setMsg("Base " + twoUnidadeTmp.getBase() + " nao encontrada.");
                    break;
                }
            }catch (Exception e){
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + ". Erro ao buscar Base de dados de nome " + twoUnidadeTmp.getBase() + ". Erro: " + e);
                //
                response.setStatus("ERROR");
                response.setMsg("Erro ao buscar Base de dados de nome " + twoUnidadeTmp.getBase() + ". Erro: " + e);
                break;
            }


            if(twoUnidadeTmp.getTipo().toUpperCase().equals("OI")){

                if(twoUnidadeTmp.getUoDessaOi() == null){
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + ",Valor \"UO DA OI\" obrigatorio para UO");
                    //
                    response.setStatus("ERROR");
                    response.setMsg("Valor \"UO DA OI\" obrigatorio para UO");
                    break;
                }

                if(twoUnidadeTmp.getOrganizationCode() == null){
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + ",Valor \"ORGANIZATION CODE\" obrigatorio para TIPO");
                    //
                    response.setStatus("ERROR");
                    response.setMsg("Valor \"ORGANIZATION CODE\" obrigatorio para TIPO");
                    break;
                }

                try{
                    lUnidadeIdUo =
                            repository.lUnidadeIdUo(twoUnidadeTmp.getUoDessaOi(),lBaseId.getBaseId());
                    if(lUnidadeIdUo == null){
                        System.out.print(sysdate);
                        System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + ",UO \"" + twoUnidadeTmp.getUoDessaOi() + "\" nao encontrada.");
                        //
                        response.setStatus("ERROR");
                        response.setMsg("UO \"" + twoUnidadeTmp.getUoDessaOi() + "\" nao encontrada.");
                        break;
                    }
                }catch (Exception e){
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + " Erro ao buscar UO  \"" +   twoUnidadeTmp.getUoDessaOi()  +"\". Erro: " + e);
                    //
                    response.setStatus("ERROR");
                    response.setMsg(" Erro ao buscar UO  \"" +   twoUnidadeTmp.getUoDessaOi()  +"\". Erro: " + e);
                    break;
                }

            } // IF("OI)

            // Valida Cidade.

            lCidId = null;

            if(twoUnidadeTmp.getTipo().toUpperCase().equals("UO")|| twoUnidadeTmp.getTipo().toUpperCase().equals("UE")){

                if(twoUnidadeTmp.getCidade() == null || twoUnidadeTmp.getEstado() == null || twoUnidadeTmp.getPais() == null){
                    lCidId = -1L;
                }else{
                    lEstado = twoUnidadeTmp.getEstado();

                    if(twoUnidadeTmp.getCodPais().equals("BR")){

                        try{
                            lEstado = repository.lEstado(twoUnidadeTmp.getPais(),twoUnidadeTmp.getEstado());
                        }catch (Exception e){
                            lEstado = twoUnidadeTmp.getEstado();
                        }
                    }
                    //
                    try{
                        lCidId = repository.lCidId(twoUnidadeTmp.getCidade(),lEstado,twoUnidadeTmp.getPais());
                    }catch (Exception e){
                        lCidId = -1L;
                    }

                }
            }

            // Instancia para realizar comando DML.
            TwoUnidade twoUnidade = new TwoUnidade();


            // Verificando o index para ver se realiza o insert ou o update na tabela.
            try{
                unidadeIdIndex =
                        repository.getIndex(twoUnidadeTmp.getCod(),twoUnidadeTmp.getTipo(),lBaseId.getBaseId());
            }catch (Exception e){
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! UNIDADE_TMP_ID: "+ twoUnidadeTmp.getUnidadeTmpId() + " => " + e);
                //
                response.setStatus("ERROR");
                response.setMsg(" Erro ao buscar UNIDADE_ID como o as colunas indesx. Erro: " + e);
                break;
            }

            if(unidadeIdIndex == null){
                twoUnidade.setUnidadeId(null); // AUTO
                twoUnidade.setUsuarioIdCriacao(twoUnidadeTmp.getUsuario());
                twoUnidade.setDtHrCriacao(sysdate);
                twoUnidade.setCod(twoUnidadeTmp.getCod());
                twoUnidade.setTipo(twoUnidadeTmp.getTipo().toUpperCase());
                twoUnidade.setBaseId(lBaseId);

            }else{
                twoUnidade.setUnidadeId(unidadeIdIndex);
                twoUnidade.setUsuarioIdCriacao(repository.getUnidade(unidadeIdIndex).getUsuarioIdCriacao());
                twoUnidade.setDtHrCriacao(repository.getUnidade(unidadeIdIndex).getDtHrCriacao());
                twoUnidade.setCod(repository.getUnidade(unidadeIdIndex).getCod());
                twoUnidade.setTipo(repository.getUnidade(unidadeIdIndex).getTipo().toUpperCase());
                twoUnidade.setBaseId(repository.getUnidade(unidadeIdIndex).getBaseId());
            }

            twoUnidade.setIdentificador(twoUnidadeTmp.getIdentificador());
            twoUnidade.setUoDessaOi(twoUnidadeTmp.getUoDessaOi());
            twoUnidade.setDescr(twoUnidadeTmp.getDescr());
            twoUnidade.setOrganizationCode(twoUnidadeTmp.getOrganizationCode());
            twoUnidade.setCidId(lCidId);
            twoUnidade.setUsuarioIdAlter(twoUnidadeTmp.getUsuario());
            twoUnidade.setDtHrAlter(sysdate);
            twoUnidade.setDtFinal(twoUnidadeTmp.getDtFinal());


            // Guardando obj na lista.
            listaUnidades.add(twoUnidade);

        }

        // Salvando dados se nao houver erro.
        if(!response.getStatus().equals("ERROR")){
            for(int j = 0; j < listaUnidades.size(); j++){
                repository.save(listaUnidades.get(j));
            }
        }

        /// ENVIA EMAIL CASO DÊ ERRO.
        if(response.getStatus().equals("ERROR")){
            enviaEmail.sendSimpleMessage("gabrielhnasc@outlook.com"
                    ,"(API)ERRO AO PROCESSAR UNIDADE",  response.getMsg());
        }

        return response;
    }
//==============================================================================================\\
    public List<TwoUnidade> getUnidade(){
        return repository.findAll();
    }
//==============================================================================================\\
    public List<TwoUnidade> getUnidadePorBusca(String busca){
        return repository.getUnidadePorBusca(busca);
    }
}
