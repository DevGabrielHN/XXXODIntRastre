package com.twoone.XXODIntRastre.app.service;

import com.twoone.XXODIntRastre.app.email.TwoEnviarEmail;
import com.twoone.XXODIntRastre.app.entities.TwoCidade;
import com.twoone.XXODIntRastre.app.entities.TwoPais;
import com.twoone.XXODIntRastre.app.entities.TwoUnidadeFederativa;
import com.twoone.XXODIntRastre.app.repository.TwoCidadeRepository;
import com.twoone.XXODIntRastre.app.repository.TwoPaisRepository;
import com.twoone.XXODIntRastre.app.repository.TwoUnidadeFederativaRepository;
import com.twoone.XXODIntRastre.app.repository.XxodDeParaDetailRepository;
import com.twoone.XXODIntRastre.app.request.TwoProcessRequest;
import com.twoone.XXODIntRastre.app.response.TwoJsonResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
@NoArgsConstructor
@AllArgsConstructor
public class TwoProcessService {

    @Autowired
    private XxodDeParaDetailRepository repositoryDeParaDetail;
//
    @Autowired
    private TwoPaisRepository repositoryPais;
//
    @Autowired
    private TwoUnidadeFederativaRepository repositoryUf;
//
    @Autowired
    private TwoCidadeRepository repositoryCidade;
//
    @Autowired
    private TwoEnviarEmail enviaEmail;
//
    public TwoJsonResponse postProcessP(TwoProcessRequest bodyRequest){

        System.out.println("+==============================================================================+");

        // Variaveis
        String paisStatus;
        Long paisIdIndex;

        TwoJsonResponse response = new TwoJsonResponse();
        response.setStatus("SUCCESS");
        response.setMsg("Pais, uf e cidade criados/atualizados com sucesso.");

// "For" para descontinuar("break") o script quando der um erro, e gerar o status do primeiro erro.
        for(int p = 0;p < 1; p++) {

            // Pegando a data de execucao.
            Timestamp sysdate;
            sysdate = new Timestamp(System.currentTimeMillis());

            Long lPaisId;
            Long lUfId;
            String pCodCidade = bodyRequest.getCodCidade();
            String pCidade = bodyRequest.getCidade();
            String pCodEstado = bodyRequest.getCodEstado();
            String pEstado = bodyRequest.getEstado();
            String pCodPais = bodyRequest.getCodPais();
            String pPais = bodyRequest.getPais();
            String pUser = bodyRequest.getUser();

            // Removendo os assentos.
            final char[] comAcento =
                    "ÃÂÀÁÄÊÈÉËÎÌÍÏÕÔÒÓÖÛÚÙÜÃÂÀÁÄÊÈÉËÎÌÍÏÕÔÒÓÖÛÙÚÜÇÇÑÑ".toCharArray();

            final char[] semAcento =
                    "AAAAAEEEEIIIIOOOOOUUUUAAAAAEEEEIIIIOOOOOUUUUCCNN".toCharArray();

            for (int i = 0; i < comAcento.length; i++) {

                pCodCidade = pCodCidade.toUpperCase().replace(comAcento[i], semAcento[i]);
                pCidade = pCidade.toUpperCase().replace(comAcento[i], semAcento[i]);
                pCodEstado = pCodEstado.toUpperCase().replace(comAcento[i], semAcento[i]);
                pEstado = pEstado.toUpperCase().replace(comAcento[i], semAcento[i]);
                pCodPais = pCodPais.toUpperCase().replace(comAcento[i], semAcento[i]);
                pPais = pPais.toUpperCase().replace(comAcento[i], semAcento[i]);
                pUser = pUser.toUpperCase().replace(comAcento[i], semAcento[i]);

            }
            //

            if (pCidade == null) {
                response.setStatus("ERROR");
                response.setMsg("Nome da cidade é obrigatório");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Nome da cidade é obrigatório");
                break;
            }

            if (pCodEstado == null) {
                response.setStatus("ERROR");
                response.setMsg("Codido do estado é obrigatório");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Codido do estado é obrigatório");
                break;
            }

            if (pEstado == null) {
                response.setStatus("ERROR");
                response.setMsg("Nome do estado é obrigatório");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Nome do estado é obrigatório");
                break;
            }

            if (pPais == null) {
                response.setStatus("ERROR");
                response.setMsg("Nome do pais é obrigatório");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Nome do pais é obrigatório");
                break;
            }

            if (pCodPais == null) {
                response.setStatus("ERROR");
                response.setMsg("Codigo do pais é obrigatório");
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Codigo do pais é obrigatório");
                break;
            }

            // DePara de Estados brasileiros
            String auxEstado = pEstado;
            if (pCodPais.equals("BR")) {

                try {
                        pEstado = repositoryDeParaDetail.getLEstado(pCodPais, pCodEstado);

                        // "if" acrescentado
                        if(pEstado == null){
                            pEstado = auxEstado;
                        }
                        //

                } catch (Exception e) {
                    pEstado = auxEstado;
                }
            } else {
                pEstado = auxEstado;
            }
            //
            // Inserindo Pais
            TwoPais pais = new TwoPais();

            try {
                paisIdIndex = repositoryPais.indexPais(pPais, pCodPais);
            } catch (Exception e) {
                response.setStatus("ERROR");
                response.setMsg("Ocorreu um erro ao consultar PAIS_ID (INDEX: PAIS_U1). Erro: " + e);
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Ocorreu um erro ao consultar PAIS_ID (INDEX: PAIS_U1). Erro: " + e);
                break;
            }

            if (paisIdIndex == null) {
                pais.setPaisId(null); // AUTO
                pais.setUsuarioIdCriacao(pUser);
                pais.setDtHrCriacao(sysdate);
                paisStatus = "CRIADO";
            } else {
                pais.setPaisId(paisIdIndex);
                pais.setUsuarioIdCriacao(repositoryPais.getUsuarioIdCriacao(paisIdIndex));
                pais.setDtHrCriacao(repositoryPais.getDtHrCriacao(paisIdIndex));
                paisStatus = "ATUALIZADO";
            }

            pais.setNome(pPais);
            pais.setSigla(pCodPais);
            pais.setUsuarioIdAlter(pUser);
            pais.setDtHrAlter(sysdate);


            try {
                repositoryPais.save(pais);
                System.out.print(sysdate);
                System.out.println(" - PAIS_ID: " + pais.getPaisId() + " " + paisStatus + " com sucesso!");
                lPaisId = pais.getPaisId();
            } catch (Exception e) {
                response.setStatus("ERROR");
                response.setMsg("Ocorreu um erro ao inserir pais. Erro: " + e);
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Ocorreu um erro ao inserir pais. Erro: " + e);
                break;
            }
            //
            // Inserindo estado
            TwoUnidadeFederativa estado = new TwoUnidadeFederativa();

            String ufStatus;
            Long ufIdIndex = null;

            try {
                ufIdIndex = repositoryUf.indexUf(pEstado, lPaisId, pCodEstado);
            } catch (Exception e) {
                response.setStatus("ERROR");
                response.setMsg("Ocorreu um erro ao consultar UF_ID (INDEX: UNIDADE_FEDERATIVA_U1). Erro: " + e);
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Ocorreu um erro ao consultar UF_ID (INDEX: UNIDADE_FEDERATIVA_U1). Erro: " + e);
                break;
            }
            //
            if (ufIdIndex == null) {
                estado.setUfId(null); // AUTO
                estado.setUsuarioIdCriacao(pUser);
                estado.setDtHrCriacao(sysdate);
                ufStatus = "CRIADO";
            } else {
                estado.setUfId(ufIdIndex);
                estado.setUsuarioIdCriacao(repositoryUf.getUsuarioIdCriacao(ufIdIndex));
                estado.setDtHrCriacao(repositoryUf.getDtHrCriacao(ufIdIndex));
                ufStatus = "ATUALIZADO";
            }

            estado.setNome(pEstado);
            estado.setSigla(pCodEstado);
            estado.setPaisId(pais);
            estado.setDtHrAlter(sysdate);
            estado.setUsuarioIdAlter(pUser);

            try {
                repositoryUf.save(estado);
                System.out.print(sysdate);
                System.out.println(" - UF_ID: " + estado.getUfId() + " " + ufStatus + " com sucesso!");
                lUfId = estado.getUfId();
            } catch (Exception e) {
                response.setStatus("ERROR");
                response.setMsg("Ocorreu um erro ao inserir estado. Erro: " + e);
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Ocorreu um erro ao inserir estado. Erro: " + e);
                break;
            }
            //
            // Inserindo cidade
            TwoCidade cidade = new TwoCidade();

            String cidadeStatus;
            Long cidadeIndex = null;

            try {
                cidadeIndex = repositoryCidade.indexCidade(pCidade, lUfId);
            } catch (Exception e) {
                response.setStatus("ERROR");
                response.setMsg("Ocorreu um erro ao consultar UF_ID (INDEX: UNIDADE_FEDERATIVA_U1). Erro: " + e);
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Ocorreu um erro ao consultar CID_ID (INDEX: CIDADE_U1). Erro: " + e);
                break;
            }

            if (cidadeIndex == null) {
                cidade.setCidId(null); // AUTO
                cidade.setUsuarioIdCriacao(pUser);
                cidade.setDtHrCriacao(sysdate);
                cidadeStatus = "CRIADO";
            } else {
                cidade.setCidId(cidadeIndex);
                cidade.setUsuarioIdCriacao(repositoryCidade.getUsuarioIdCriacao(cidadeIndex));
                cidade.setDtHrCriacao(repositoryCidade.getDtHrCriacao(cidadeIndex));
                cidadeStatus = "ATUALIZADO";
            }

            cidade.setNome(pCidade);
            cidade.setUsuarioIdAlter(pUser);
            cidade.setDtHrAlter(sysdate);
            cidade.setUfId(estado);
            cidade.setUrlPrefeitura(null); // PASSAR PARAMENTRO?
            cidade.setStatusImpotacao(null); // PASSAR PARAMENTRO?
            cidade.setDtFinal(null); // PASSAR PARAMENTRO?

            try {
                repositoryCidade.save(cidade);
                System.out.print(sysdate);
                System.out.println(" - CID_ID: " + cidade.getCidId() + " " + cidadeStatus + " com sucesso!");
            } catch (Exception e) {
                response.setStatus("ERROR");
                response.setMsg("Ocorreu um erro ao inserir cidade. Erro: " + e);
                //
                System.out.print(sysdate);
                System.out.println(" - ### ERRO! Ocorreu um erro ao inserir cidade. Erro: " + e);
            }
        }

        /// ENVIA EMAIL CASO DÊ ERRO.
        if(response.getStatus().equals("ERROR")){
            enviaEmail.sendSimpleMessage("gabrielhnasc@outlook.com"
                ,"(API)ERRO AO INSERIR PAIS/ESTADO/CIDADE",  response.getMsg());
        }


        System.out.println("+==============================================================================+");
        return response;
    }



}
