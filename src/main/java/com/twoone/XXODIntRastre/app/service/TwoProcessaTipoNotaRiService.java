package com.twoone.XXODIntRastre.app.service;

import com.twoone.XXODIntRastre.app.email.TwoEnviarEmail;
import com.twoone.XXODIntRastre.app.entities.*;
import com.twoone.XXODIntRastre.app.repository.TwoBaseDadoRepository;
import com.twoone.XXODIntRastre.app.repository.TwoLotesTipoNotaRiRepository;
import com.twoone.XXODIntRastre.app.repository.TwoTipoNotaRiRepository;
import com.twoone.XXODIntRastre.app.repository.TwoTipoNotaRiTmpRepository;
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
public class TwoProcessaTipoNotaRiService {

    @Autowired
    private TwoTipoNotaRiRepository repositoryTipoNotaRi;
    //
    @Autowired
    private TwoTipoNotaRiTmpRepository repositoryTipoNotaRiTmp;
    //
    @Autowired
    private TwoLotesTipoNotaRiRepository repositoryLote;
    //
    @Autowired
    private TwoBaseDadoRepository repositoryBaseDado;
//
    @Autowired
    private TwoEnviarEmail enviaEmail;
//
    public TwoLoteIdResponse postTipoNotaRiTmp(List<TwoTipoNotaRiTmp> bodyRequest){

        // Instancia da response.
        TwoLoteIdResponse response = new TwoLoteIdResponse();

        // Buscando data da execucao
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());
        //

        // Obj. da tabela de lote (instancia)
        TwoLotesTipoNotaRi lote = new TwoLotesTipoNotaRi();
        //

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
        List<TwoTipoNotaRiTmp> listaInsert = new ArrayList<>();


        try{

            for(int i = 0; i < bodyRequest.size(); i++){

                // "Instancia da tabela tmp"
                TwoTipoNotaRiTmp tipoNotaRiTmp = new TwoTipoNotaRiTmp();

                //
                tipoNotaRiTmp.setTipoNfTmpId(null); // AUTO
                tipoNotaRiTmp.setOrganizationCode(bodyRequest.get(i).getOrganizationCode());
                tipoNotaRiTmp.setInvoiceTypeId(bodyRequest.get(i).getInvoiceTypeId());
                tipoNotaRiTmp.setInvoiceTypeCode(bodyRequest.get(i).getInvoiceTypeCode());
                tipoNotaRiTmp.setOrganizationId(bodyRequest.get(i).getOrganizationId());
                tipoNotaRiTmp.setPaymentFlag(bodyRequest.get(i).getPaymentFlag());
                tipoNotaRiTmp.setParentFlag(bodyRequest.get(i).getParentFlag());
                tipoNotaRiTmp.setPriceAdjustFlag(bodyRequest.get(i).getPriceAdjustFlag());
                tipoNotaRiTmp.setTaxAdjustFlag(bodyRequest.get(i).getTaxAdjustFlag());
                tipoNotaRiTmp.setRequisitionType(bodyRequest.get(i).getRequisitionType());
                tipoNotaRiTmp.setFiscalFlag(bodyRequest.get(i).getFiscalFlag());
                tipoNotaRiTmp.setInvoiceTypeLookupCode(bodyRequest.get(i).getInvoiceTypeLookupCode());
                tipoNotaRiTmp.setCreditDebitFlag(bodyRequest.get(i).getCreditDebitFlag());
                tipoNotaRiTmp.setIncludeIcmsFlag(bodyRequest.get(i).getIncludeIcmsFlag());
                tipoNotaRiTmp.setIcmsReductionPercent(bodyRequest.get(i).getIcmsReductionPercent());
                tipoNotaRiTmp.setIncludeIpiFlag(bodyRequest.get(i).getIncludeIpiFlag());
                tipoNotaRiTmp.setFreightFlag(bodyRequest.get(i).getFreightFlag());
                tipoNotaRiTmp.setTriangleOperation(bodyRequest.get(i).getTriangleOperation());
                tipoNotaRiTmp.setContabFlag(bodyRequest.get(i).getContabFlag());
                tipoNotaRiTmp.setTransferType(bodyRequest.get(i).getTransferType());
                tipoNotaRiTmp.setOperationType(bodyRequest.get(i).getOperationType());
                tipoNotaRiTmp.setCrCodeCombinationId(bodyRequest.get(i).getCrCodeCombinationId());
                tipoNotaRiTmp.setIcmsCodeCombinationId(bodyRequest.get(i).getIcmsCodeCombinationId());
                tipoNotaRiTmp.setIpiCodeCombinationId(bodyRequest.get(i).getIpiCodeCombinationId());
                tipoNotaRiTmp.setDiffIcmsCodeCombinationId(bodyRequest.get(i).getDiffIcmsCodeCombinationId());
                tipoNotaRiTmp.setIssCodeCombinationId(bodyRequest.get(i).getIssCodeCombinationId());
                tipoNotaRiTmp.setIrCodeCombinationId(bodyRequest.get(i).getIrCodeCombinationId());
                tipoNotaRiTmp.setImportTaxCcid(bodyRequest.get(i).getImportTaxCcid());
                tipoNotaRiTmp.setImportInsuranceCcid(bodyRequest.get(i).getImportInsuranceCcid());
                tipoNotaRiTmp.setImportFreightCcid(bodyRequest.get(i).getImportExpenseCccid());
                tipoNotaRiTmp.setImportExpenseCccid(bodyRequest.get(i).getImportExpenseCccid());
                tipoNotaRiTmp.setIcmsStCcid(bodyRequest.get(i).getIcmsStCcid());
                tipoNotaRiTmp.setReturnFlag(bodyRequest.get(i).getReturnFlag());
                tipoNotaRiTmp.setConstAdjustFlag(bodyRequest.get(i).getConstAdjustFlag());
                tipoNotaRiTmp.setForeignCurrencyUsage(bodyRequest.get(i).getForeignCurrencyUsage());
                tipoNotaRiTmp.setOperationFiscalType(bodyRequest.get(i).getOperationFiscalType());
                tipoNotaRiTmp.setSymbolicReturnCcid(bodyRequest.get(i).getSymbolicReturnCcid());
                tipoNotaRiTmp.setDescription(bodyRequest.get(i).getDescription());
                tipoNotaRiTmp.setInactiveDate(bodyRequest.get(i).getInactiveDate());
                tipoNotaRiTmp.setInssCodeCombinationId(bodyRequest.get(i).getInssCodeCombinationId());
                tipoNotaRiTmp.setBonusFlag(bodyRequest.get(i).getBonusFlag());
                tipoNotaRiTmp.setProjectFlag(bodyRequest.get(i).getProjectFlag());
                tipoNotaRiTmp.setEncumbranceFlag(bodyRequest.get(i).getEncumbranceFlag());
                tipoNotaRiTmp.setAccountReceivableCcid(bodyRequest.get(i).getAccountReceivableCcid());
                tipoNotaRiTmp.setCustomerCcid(bodyRequest.get(i).getCustomerCcid());
                tipoNotaRiTmp.setRmaIpiReductionCcid(bodyRequest.get(i).getRmaIpiReductionCcid());
                tipoNotaRiTmp.setRmaIpiLiabilityCccid(bodyRequest.get(i).getRmaIpiLiabilityCccid());
                tipoNotaRiTmp.setRmaIcmsReductionCcid(bodyRequest.get(i).getRmaIcmsReductionCcid());
                tipoNotaRiTmp.setRmaIcmsLiabilityCcid(bodyRequest.get(i).getRmaIcmsLiabilityCcid());
                tipoNotaRiTmp.setFixedAssentsFlag(bodyRequest.get(i).getFixedAssentsFlag());
                tipoNotaRiTmp.setIpiLiabilityCcid(bodyRequest.get(i).getIpiLiabilityCcid());
                tipoNotaRiTmp.setFunruralCcid(bodyRequest.get(i).getFunruralCcid());
                tipoNotaRiTmp.setSestSenatCcid(bodyRequest.get(i).getSestSenatCcid());
                tipoNotaRiTmp.setImportIcmsFlag(bodyRequest.get(i).getImportIcmsFlag());
                tipoNotaRiTmp.setIncludeIssFlag(bodyRequest.get(i).getIncludeIssFlag());
                tipoNotaRiTmp.setReturnCustomerFlag(bodyRequest.get(i).getReturnCustomerFlag());
                tipoNotaRiTmp.setPayGroupLookupCode(bodyRequest.get(i).getPayGroupLookupCode());
                tipoNotaRiTmp.setGenerateReturnInvoice(bodyRequest.get(i).getGenerateReturnInvoice());
                tipoNotaRiTmp.setArTransactionTypeId(bodyRequest.get(i).getArTransactionTypeId());
                tipoNotaRiTmp.setArSourceId(bodyRequest.get(i).getArSourceId());
                tipoNotaRiTmp.setArCredIcmsCategoryId(bodyRequest.get(i).getArCredIcmsCategoryId());
                tipoNotaRiTmp.setArCredIcmsStCategory(bodyRequest.get(i).getArCredIcmsStCategory());
                tipoNotaRiTmp.setArCredIpiCategoryId(bodyRequest.get(i).getArCredIpiCategoryId());
                tipoNotaRiTmp.setArDebIcmsCategoryId(bodyRequest.get(i).getArDebIcmsCategoryId());
                tipoNotaRiTmp.setArDebIcmsStCategoryId(bodyRequest.get(i).getArDebIcmsStCategoryId());
                tipoNotaRiTmp.setArDebIpiCategoryId(bodyRequest.get(i).getArDebIpiCategoryId());
                tipoNotaRiTmp.setRmaIcmsStLiabilityCcid(bodyRequest.get(i).getRmaIcmsStLiabilityCcid());
                tipoNotaRiTmp.setRmaIcmsStReductionCcid(bodyRequest.get(i).getRmaIcmsStReductionCcid());
                tipoNotaRiTmp.setPisCodeCombinationId(bodyRequest.get(i).getPisCodeCombinationId());
                tipoNotaRiTmp.setPisFlag(bodyRequest.get(i).getPisFlag());
                tipoNotaRiTmp.setInssExpenseCcid(bodyRequest.get(i).getInssExpenseCcid());
                tipoNotaRiTmp.setVariationCostDevolutionCcid(bodyRequest.get(i).getVariationCostDevolutionCcid());
                tipoNotaRiTmp.setSiscomexCcid(bodyRequest.get(i).getSiscomexCcid());
                tipoNotaRiTmp.setRmaPisReductionCcid(bodyRequest.get(i).getRmaPisReductionCcid());
                tipoNotaRiTmp.setRmaCofinsReductionCcid(bodyRequest.get(i).getRmaCofinsReductionCcid());
                tipoNotaRiTmp.setExcludeIcmsStFlag(bodyRequest.get(i).getExcludeIcmsStFlag());
                tipoNotaRiTmp.setCofinsCodeCombinationId(bodyRequest.get(i).getCofinsCodeCombinationId());
                tipoNotaRiTmp.setCofinsFlag(bodyRequest.get(i).getCofinsFlag());
                tipoNotaRiTmp.setPaProcessDataFrom(bodyRequest.get(i).getPaProcessDataFrom());
                tipoNotaRiTmp.setImportPisCcid(bodyRequest.get(i).getImportPisCcid());
                tipoNotaRiTmp.setImportCofinsCcid(bodyRequest.get(i).getImportCofinsCcid());
                tipoNotaRiTmp.setPermanentActiveCreditFlag(bodyRequest.get(i).getPermanentActiveCreditFlag());
                tipoNotaRiTmp.setRmaPisRedCcid(bodyRequest.get(i).getRmaPisRedCcid());
                tipoNotaRiTmp.setRmaConfinsRedCcid(bodyRequest.get(i).getRmaConfinsRedCcid());
                tipoNotaRiTmp.setReceiveBy(bodyRequest.get(i).getReceiveBy());
                tipoNotaRiTmp.setInssCalculationFlag(bodyRequest.get(i).getInssCalculationFlag());
                tipoNotaRiTmp.setCustomsExpenseCcid(bodyRequest.get(i).getCustomsExpenseCcid());
                tipoNotaRiTmp.setIcmsStAntCcid(bodyRequest.get(i).getIcmsStAntCcid());
                tipoNotaRiTmp.setIcmsStAntCcidRecup(bodyRequest.get(i).getIcmsStAntCcidRecup());
                tipoNotaRiTmp.setCreationDate(sysdate); ///// DATA ATUAL
                tipoNotaRiTmp.setCreatedBy(bodyRequest.get(i).getCreatedBy());
                tipoNotaRiTmp.setLastUpdateDate(sysdate); ///// DATA ATUAL
                tipoNotaRiTmp.setLastUpdatedBy(bodyRequest.get(i).getLastUpdatedBy());
                tipoNotaRiTmp.setLastUpdateLogin(bodyRequest.get(i).getLastUpdateLogin());
                tipoNotaRiTmp.setRequestId(bodyRequest.get(i).getRequestId());
                tipoNotaRiTmp.setProgramApplicationId(bodyRequest.get(i).getProgramApplicationId());
                tipoNotaRiTmp.setProgramId(bodyRequest.get(i).getProgramId());
                tipoNotaRiTmp.setProgramUpdateDate(bodyRequest.get(i).getProgramUpdateDate());
                tipoNotaRiTmp.setAttributeCategory(bodyRequest.get(i).getAttributeCategory());
                tipoNotaRiTmp.setAttribute1(bodyRequest.get(i).getAttribute1());
                tipoNotaRiTmp.setAttribute2(bodyRequest.get(i).getAttribute2());
                tipoNotaRiTmp.setAttribute3(bodyRequest.get(i).getAttribute3());
                tipoNotaRiTmp.setAttribute4(bodyRequest.get(i).getAttribute4());
                tipoNotaRiTmp.setAttribute5(bodyRequest.get(i).getAttribute5());
                tipoNotaRiTmp.setAttribute6(bodyRequest.get(i).getAttribute6());
                tipoNotaRiTmp.setAttribute7(bodyRequest.get(i).getAttribute7());
                tipoNotaRiTmp.setAttribute8(bodyRequest.get(i).getAttribute8());
                tipoNotaRiTmp.setAttribute9(bodyRequest.get(i).getAttribute9());
                tipoNotaRiTmp.setAttribute10(bodyRequest.get(i).getAttribute10());
                tipoNotaRiTmp.setAttribute11(bodyRequest.get(i).getAttribute11());
                tipoNotaRiTmp.setAttribute12(bodyRequest.get(i).getAttribute12());
                tipoNotaRiTmp.setAttribute13(bodyRequest.get(i).getAttribute13());
                tipoNotaRiTmp.setAttribute14(bodyRequest.get(i).getAttribute14());
                tipoNotaRiTmp.setAttribute15(bodyRequest.get(i).getAttribute15());
                tipoNotaRiTmp.setAttribute16(bodyRequest.get(i).getAttribute16());
                tipoNotaRiTmp.setAttribute17(bodyRequest.get(i).getAttribute17());
                tipoNotaRiTmp.setAttribute18(bodyRequest.get(i).getAttribute18());
                tipoNotaRiTmp.setAttribute19(bodyRequest.get(i).getAttribute19());
                tipoNotaRiTmp.setAttribute20(bodyRequest.get(i).getAttribute20());
                tipoNotaRiTmp.setWmsFlag(bodyRequest.get(i).getWmsFlag());
                tipoNotaRiTmp.setComplexServiceFlag(bodyRequest.get(i).getComplexServiceFlag());
                tipoNotaRiTmp.setDocumentType(bodyRequest.get(i).getDocumentType());
                tipoNotaRiTmp.setIpiTributaryCodeFlag(bodyRequest.get(i).getIpiTributaryCodeFlag());
                tipoNotaRiTmp.setIncludeIrFlag(bodyRequest.get(i).getIncludeIrFlag());
                tipoNotaRiTmp.setIncludeSestSenatFlag(bodyRequest.get(i).getIncludeSestSenatFlag());
                tipoNotaRiTmp.setIrVendor(bodyRequest.get(i).getIrVendor());
                tipoNotaRiTmp.setIrCateg(bodyRequest.get(i).getIrCateg());
                tipoNotaRiTmp.setIrTax(bodyRequest.get(i).getIrTax());
                tipoNotaRiTmp.setInssSubstituteFlag(bodyRequest.get(i).getInssSubstituteFlag());
                tipoNotaRiTmp.setInssTax(bodyRequest.get(i).getInssTax());
                tipoNotaRiTmp.setInssAutonomousTax(bodyRequest.get(i).getInssAutonomousTax());
                tipoNotaRiTmp.setInssAdditionalTax1(bodyRequest.get(i).getInssAdditionalTax1());
                tipoNotaRiTmp.setInssAdditionalTax2(bodyRequest.get(i).getInssAdditionalTax2());
                tipoNotaRiTmp.setInssAdditionalTax3(bodyRequest.get(i).getInssAdditionalTax3());
                tipoNotaRiTmp.setRemunerationFreight(bodyRequest.get(i).getRemunerationFreight());
                tipoNotaRiTmp.setSestSenatTax(bodyRequest.get(i).getSestSenatTax());
                tipoNotaRiTmp.setUtilitiesFlag(bodyRequest.get(i).getUtilitiesFlag());
                tipoNotaRiTmp.setWorkerCategory(bodyRequest.get(i).getWorkerCategory());
                tipoNotaRiTmp.setCollectPisCcid(bodyRequest.get(i).getCollectPisCcid());
                tipoNotaRiTmp.setCollectCofinsCcid(bodyRequest.get(i).getCollectCofinsCcid());
                tipoNotaRiTmp.setAwtGroupId(bodyRequest.get(i).getAwtGroupId());
                tipoNotaRiTmp.setIncomeCode(bodyRequest.get(i).getIncomeCode());
                tipoNotaRiTmp.setBase(bodyRequest.get(i).getBase());
                tipoNotaRiTmp.setBaseId(bodyRequest.get(i).getBaseId());
                tipoNotaRiTmp.setUsuario(bodyRequest.get(i).getUsuario());
                tipoNotaRiTmp.setIntegrationDate(bodyRequest.get(i).getIntegrationDate());
                tipoNotaRiTmp.setInstanceId(bodyRequest.get(i).getInstanceId());
                tipoNotaRiTmp.setMsg(null); // DEIXA ASSIM?
                tipoNotaRiTmp.setStatus("NEW"); // DEIXA ASSIM?
                tipoNotaRiTmp.setLoteId(lote);

                // Salvando na lista para usar no metodo save().
                listaInsert.add(tipoNotaRiTmp);

            } // FOR

            repositoryLote.save(lote);

            for(int j = 0;j < listaInsert.size();j++){
                repositoryTipoNotaRiTmp.save(listaInsert.get(j));
            }

            response.setIdLote(lote.getId());


        }catch (Exception e){
            System.out.print(sysdate);
            System.out.println(" - ### ERRO! Algo deu errado ao savar tipoNotaRiTmp  Erro: " + e);

        }

        return response;
    }
//==============================================================================================\\
    public TwoJsonResponse postTipoNotaRi(TwoLoteIdRequest bodyRequest){

        System.out.println("bodyRequest: " +bodyRequest);

        // Buscando data da execucao
        Timestamp sysdate;
        sysdate = new Timestamp(System.currentTimeMillis());
        //

        // Variaveis
        TwoBaseDado lBaseId;
        Long IndexTipoNfId;

        // Lista para add. linhas validas para fazer o insert/update se der tudo ok
        List<TwoTipoNotaRi> listaFinal = new ArrayList<>();

        // Obj. de resposta.
        TwoJsonResponse response = new TwoJsonResponse();

        // Status inicial.
        response.setStatus("SUCCESS");
        response.setMsg("Tipo Nota RI criado/atualizado com sucesso.");

        try{

            // Cursor r_tp_nota.
            List<TwoTipoNotaRiTmp> r_tp_nota =
                    repositoryTipoNotaRiTmp.r_tp_nota(bodyRequest.getLoteId());

            System.out.println("Lote_id: " + bodyRequest.getLoteId());
            System.out.println("r_tp_nota: " + r_tp_nota);

            for(int i = 0; i < r_tp_nota.size();i++){

                try{
                    lBaseId =
                            repositoryBaseDado.lBaseId(r_tp_nota.get(i).getBase());
                    if(lBaseId == null){
                        response.setStatus("ERROR");
                        response.setMsg("Base " +  r_tp_nota.get(i).getBase() + " nao encontrada no Rastreabilidade.");
                        //
                        System.out.print(sysdate);
                        System.out.println(" - ### ERRO! Base " +  r_tp_nota.get(i).getBase() + " nao encontrada no Rastreabilidade.");
                         break;
                    }
                }catch (Exception e){
                    response.setStatus("ERROR");
                    response.setMsg("TIPO_NF_TMP_ID: " + r_tp_nota.get(i).getTipoNfTmpId() + ", Erro ao verificar se base " + r_tp_nota.get(i).getBase() + "  existe no Rastreabilidade. Erro: " + e);
                    //
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! TIPO_NF_TMP_ID: " + r_tp_nota.get(i).getTipoNfTmpId() + ", Erro ao verificar se base " + r_tp_nota.get(i).getBase() + "  existe no Rastreabilidade. Erro: " + e);
                    break;
                }

                try{
                    IndexTipoNfId =
                            repositoryTipoNotaRi.IndexTipoNfId(r_tp_nota.get(i).getInvoiceTypeId()
                                                                    ,lBaseId.getBaseId());
                }catch (Exception e){
                    response.setStatus("ERROR");
                    response.setMsg("TIPO_NF_TMP_ID: " + r_tp_nota.get(i).getTipoNfTmpId() + ", Erro ao buscar TIPO_NF_ID com o index. Erro: " + e);
                    //
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! TIPO_NF_TMP_ID: " + r_tp_nota.get(i).getTipoNfTmpId() + ",Erro ao buscar TIPO_NF_ID com o index. Erro: " + e);
                    break;
                }

                if(r_tp_nota.get(i).getUsuario() == null){
                    response.setStatus("ERROR");
                    response.setMsg("Usuario não informado");
                    //
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! TIPO_NF_TMP_ID: " + r_tp_nota.get(i).getTipoNfTmpId() + ",TIPO_NF_TMP_ID: " + r_tp_nota.get(i).getTipoNfTmpId() + ", Usuario não informado");
                    break;
                }

                if(r_tp_nota.get(i).getOrganizationCode() == null){
                    response.setStatus("ERROR");
                    response.setMsg("Organization Code não informado");
                    //
                    System.out.print(sysdate);
                    System.out.println(" - ### ERRO! TIPO_NF_TMP_ID: " + r_tp_nota.get(i).getTipoNfTmpId() + ",TIPO_NF_TMP_ID: " + r_tp_nota.get(i).getTipoNfTmpId() + ", Organization Code não informado");
                    break;
                }





                // Inserindo ou atualizando dados na tabela TWO_TIPO_NOTA_RI.
                TwoTipoNotaRi tipoNotaRi = new TwoTipoNotaRi();

                if(IndexTipoNfId == null){
                    // INSERT
                    tipoNotaRi.setTipoNfId(null); // AUTO
                    tipoNotaRi.setBaseId(lBaseId.getBaseId());
                    tipoNotaRi.setInvoiceTypeId(r_tp_nota.get(i).getInvoiceTypeId());
                    tipoNotaRi.setUsuarioIdCriacao(r_tp_nota.get(i).getUsuario());
                    tipoNotaRi.setDtHrCricao(sysdate);

                }else {
                    // UPDATE
                    tipoNotaRi.setTipoNfId(IndexTipoNfId);

                    tipoNotaRi.setBaseId(repositoryTipoNotaRi.getTipoNotaRiPorId(IndexTipoNfId).getBaseId());
                    tipoNotaRi.setInvoiceTypeId(repositoryTipoNotaRi.getTipoNotaRiPorId(IndexTipoNfId).getInvoiceTypeId());
                    tipoNotaRi.setUsuarioIdCriacao(repositoryTipoNotaRi.getTipoNotaRiPorId(IndexTipoNfId).getUsuarioIdCriacao());
                    tipoNotaRi.setDtHrCricao(repositoryTipoNotaRi.getTipoNotaRiPorId(IndexTipoNfId).getDtHrCricao());

                } /// END IF;


                tipoNotaRi.setOrganizationCode(r_tp_nota.get(i).getOrganizationCode());
                tipoNotaRi.setInvoiceTypeCode(r_tp_nota.get(i).getInvoiceTypeCode());
                tipoNotaRi.setOrganizationId(r_tp_nota.get(i).getOrganizationId());
                tipoNotaRi.setPaymentFlag(r_tp_nota.get(i).getPaymentFlag());
                tipoNotaRi.setParentFlag(r_tp_nota.get(i).getParentFlag());
                tipoNotaRi.setPriceAdjustFlag(r_tp_nota.get(i).getPriceAdjustFlag());
                tipoNotaRi.setTaxAdjustFlag(r_tp_nota.get(i).getTaxAdjustFlag());
                tipoNotaRi.setRequisitionType(r_tp_nota.get(i).getRequisitionType());
                tipoNotaRi.setFiscalFlag(r_tp_nota.get(i).getFiscalFlag());
                tipoNotaRi.setInvoiceTypeLookupCode(r_tp_nota.get(i).getInvoiceTypeLookupCode());
                tipoNotaRi.setCreditDebitFlag(r_tp_nota.get(i).getCreditDebitFlag());
                tipoNotaRi.setIncludeIcmsFlag(r_tp_nota.get(i).getIncludeIcmsFlag());
                tipoNotaRi.setIcmsReductionPercent(r_tp_nota.get(i).getIcmsReductionPercent());
                tipoNotaRi.setIncludeIpiFlag(r_tp_nota.get(i).getIncludeIpiFlag());
                tipoNotaRi.setFreightFlag(r_tp_nota.get(i).getFreightFlag());
                tipoNotaRi.setTriangleOperation(r_tp_nota.get(i).getTriangleOperation());
                tipoNotaRi.setContabFlag(r_tp_nota.get(i).getContabFlag());
                tipoNotaRi.setTransferType(r_tp_nota.get(i).getTransferType());
                tipoNotaRi.setOperationType(r_tp_nota.get(i).getOperationType());
                tipoNotaRi.setCrCodeCombinationId(r_tp_nota.get(i).getCrCodeCombinationId());
                tipoNotaRi.setIcmsCodeCombinationId(r_tp_nota.get(i).getIcmsCodeCombinationId());
                tipoNotaRi.setIpiCodeCombinationId(r_tp_nota.get(i).getIpiCodeCombinationId());
                tipoNotaRi.setDiffIcmsCodeCombinationId(r_tp_nota.get(i).getDiffIcmsCodeCombinationId());
                tipoNotaRi.setIssCodeCombinationId(r_tp_nota.get(i).getIssCodeCombinationId());
                tipoNotaRi.setIrCodeCombinationId(r_tp_nota.get(i).getIrCodeCombinationId());
                tipoNotaRi.setImportTaxCcid(r_tp_nota.get(i).getImportTaxCcid());
                tipoNotaRi.setImportInsuranceCcid(r_tp_nota.get(i).getImportInsuranceCcid());
                tipoNotaRi.setImportFreightCcid(r_tp_nota.get(i).getImportFreightCcid());
                tipoNotaRi.setImportExpenseCcid(r_tp_nota.get(i).getImportExpenseCccid());
                tipoNotaRi.setIcmsStCcid(r_tp_nota.get(i).getIcmsStCcid());
                tipoNotaRi.setReturnFlag(r_tp_nota.get(i).getReturnFlag());
                tipoNotaRi.setConstAdjustFlag(r_tp_nota.get(i).getConstAdjustFlag());
                tipoNotaRi.setForeignCurrencyUsage(r_tp_nota.get(i).getForeignCurrencyUsage());
                tipoNotaRi.setOperationFiscalType(r_tp_nota.get(i).getOperationFiscalType());
                tipoNotaRi.setSymbolicReturnCcid(r_tp_nota.get(i).getSymbolicReturnCcid());
                tipoNotaRi.setDescription(r_tp_nota.get(i).getDescription());
                tipoNotaRi.setInactiveDate(r_tp_nota.get(i).getInactiveDate());
                tipoNotaRi.setInssCodeCombinationId(r_tp_nota.get(i).getInssCodeCombinationId());
                tipoNotaRi.setBonusFlag(r_tp_nota.get(i).getBonusFlag());
                tipoNotaRi.setProjectFlag(r_tp_nota.get(i).getProjectFlag());
                tipoNotaRi.setEncumbranceFlag(r_tp_nota.get(i).getEncumbranceFlag());
                tipoNotaRi.setAccountReceivableCcid(r_tp_nota.get(i).getAccountReceivableCcid());
                tipoNotaRi.setCustomerCcid(r_tp_nota.get(i).getCustomerCcid());
                tipoNotaRi.setRmaIpiReductionCcid(r_tp_nota.get(i).getRmaIpiReductionCcid());
                tipoNotaRi.setRmaIpiLiabilityCcid(r_tp_nota.get(i).getRmaIpiLiabilityCccid());
                tipoNotaRi.setRmaIcmsReductionCcid(r_tp_nota.get(i).getRmaIcmsReductionCcid());
                tipoNotaRi.setRmaIcmsLiabulityCcid(r_tp_nota.get(i).getRmaIcmsLiabilityCcid());
                tipoNotaRi.setFixedAssetsFlag(r_tp_nota.get(i).getFixedAssentsFlag());
                tipoNotaRi.setIpiLiabilityCcid(r_tp_nota.get(i).getIpiLiabilityCcid());
                tipoNotaRi.setFunruralCcid(r_tp_nota.get(i).getFunruralCcid());
                tipoNotaRi.setSestSenatCcid(r_tp_nota.get(i).getSestSenatCcid());
                tipoNotaRi.setImportIcmsFlag(r_tp_nota.get(i).getImportIcmsFlag());
                tipoNotaRi.setIncludeIssFlag(r_tp_nota.get(i).getIncludeIssFlag());
                tipoNotaRi.setReturnCustomerFlag(r_tp_nota.get(i).getReturnCustomerFlag());
                tipoNotaRi.setPayGroupLookupCode(r_tp_nota.get(i).getPayGroupLookupCode());
                tipoNotaRi.setGenerateReturnInvoice(r_tp_nota.get(i).getGenerateReturnInvoice());
                tipoNotaRi.setArTransactionTypeId(r_tp_nota.get(i).getArTransactionTypeId());
                tipoNotaRi.setArSourceId(r_tp_nota.get(i).getArSourceId());
                tipoNotaRi.setArCredIcmsCategoryId(r_tp_nota.get(i).getArCredIcmsCategoryId());
                tipoNotaRi.setArCredIcmsStCategoryId(r_tp_nota.get(i).getArDebIcmsStCategoryId());
                tipoNotaRi.setArCredIpiCategoryId(r_tp_nota.get(i).getArCredIpiCategoryId());
                tipoNotaRi.setArDebIcmsCategoryOId(r_tp_nota.get(i).getArDebIcmsCategoryId());
                tipoNotaRi.setArDebIcmsStCategoryId(r_tp_nota.get(i).getArDebIcmsStCategoryId());
                tipoNotaRi.setArDebIpiCategoryId(r_tp_nota.get(i).getArDebIpiCategoryId());
                tipoNotaRi.setRmaIcmsStLiabilityCcid(r_tp_nota.get(i).getRmaIcmsStLiabilityCcid());
                tipoNotaRi.setRmaIcmsStReduction(r_tp_nota.get(i).getRmaIcmsReductionCcid());
                tipoNotaRi.setPisCodeCombinationId(r_tp_nota.get(i).getPisCodeCombinationId());
                tipoNotaRi.setPisFlag(r_tp_nota.get(i).getPisFlag());
                tipoNotaRi.setInssExpenseCcid(r_tp_nota.get(i).getInssExpenseCcid());
                tipoNotaRi.setVariationCostDevolutionCcid(r_tp_nota.get(i).getVariationCostDevolutionCcid());
                tipoNotaRi.setSiscomexCcid(r_tp_nota.get(i).getSiscomexCcid());
                tipoNotaRi.setRmaPisReductionCcid(r_tp_nota.get(i).getRmaPisReductionCcid());
                tipoNotaRi.setRmaCofinsReductionCcid(r_tp_nota.get(i).getRmaCofinsReductionCcid());
                tipoNotaRi.setExcludeIcmsStFlag(r_tp_nota.get(i).getExcludeIcmsStFlag());
                tipoNotaRi.setCofinsCodeCombinationId(r_tp_nota.get(i).getCofinsCodeCombinationId());
                tipoNotaRi.setCofinsFlag(r_tp_nota.get(i).getCofinsFlag());
                tipoNotaRi.setPaProcessDateFrom(r_tp_nota.get(i).getPaProcessDataFrom());
                tipoNotaRi.setImportPisCcid(r_tp_nota.get(i).getImportPisCcid());
                tipoNotaRi.setImportCofinsCcid(r_tp_nota.get(i).getImportCofinsCcid());
                tipoNotaRi.setPermanentActiveCreditFlag(r_tp_nota.get(i).getPermanentActiveCreditFlag());
                tipoNotaRi.setRmaPisRedCcid(r_tp_nota.get(i).getRmaPisRedCcid());
                tipoNotaRi.setRmaCofinsRedCcid(r_tp_nota.get(i).getRmaConfinsRedCcid());
                tipoNotaRi.setReceivedBy(r_tp_nota.get(i).getReceiveBy());
                tipoNotaRi.setInssCalculationFlag(r_tp_nota.get(i).getInssCalculationFlag());
                tipoNotaRi.setCustomsExpenseCcid(r_tp_nota.get(i).getCustomsExpenseCcid());
                tipoNotaRi.setIcmsStAntCcid(r_tp_nota.get(i).getIcmsStAntCcid());
                tipoNotaRi.setIcmsStAntCcidRecup(r_tp_nota.get(i).getIcmsStAntCcidRecup());
                tipoNotaRi.setCreationDate(r_tp_nota.get(i).getCreationDate());
                tipoNotaRi.setCreatedBy(r_tp_nota.get(i).getCreatedBy());
                tipoNotaRi.setLastUpdateDate(r_tp_nota.get(i).getLastUpdateDate());
                tipoNotaRi.setLastUpdatedBy(r_tp_nota.get(i).getLastUpdatedBy());
                tipoNotaRi.setLastUpdateLogin(r_tp_nota.get(i).getLastUpdateLogin());
                tipoNotaRi.setRequestId(r_tp_nota.get(i).getRequestId());
                tipoNotaRi.setProgramApplicationId(r_tp_nota.get(i).getProgramApplicationId());
                tipoNotaRi.setProgramId(r_tp_nota.get(i).getProgramId());
                tipoNotaRi.setProgramUpdateDate(r_tp_nota.get(i).getProgramUpdateDate());
                tipoNotaRi.setAttributeCategory(r_tp_nota.get(i).getAttributeCategory());
                tipoNotaRi.setAttribute1(r_tp_nota.get(i).getAttribute1());
                tipoNotaRi.setAttribute2(r_tp_nota.get(i).getAttribute2());
                tipoNotaRi.setAttribute3(r_tp_nota.get(i).getAttribute3());
                tipoNotaRi.setAttribute4(r_tp_nota.get(i).getAttribute4());
                tipoNotaRi.setAttribute5(r_tp_nota.get(i).getAttribute5());
                tipoNotaRi.setAttribute6(r_tp_nota.get(i).getAttribute6());
                tipoNotaRi.setAttribute7(r_tp_nota.get(i).getAttribute7());
                tipoNotaRi.setAttribute8(r_tp_nota.get(i).getAttribute8());
                tipoNotaRi.setAttribute9(r_tp_nota.get(i).getAttribute9());
                tipoNotaRi.setAttribute10(r_tp_nota.get(i).getAttribute10());
                tipoNotaRi.setAttribute11(r_tp_nota.get(i).getAttribute11());
                tipoNotaRi.setAttribute12(r_tp_nota.get(i).getAttribute12());
                tipoNotaRi.setAttribute13(r_tp_nota.get(i).getAttribute13());
                tipoNotaRi.setAttribute14(r_tp_nota.get(i).getAttribute14());
                tipoNotaRi.setAttribute15(r_tp_nota.get(i).getAttribute15());
                tipoNotaRi.setAttribute16(r_tp_nota.get(i).getAttribute16());
                tipoNotaRi.setAttribute17(r_tp_nota.get(i).getAttribute17());
                tipoNotaRi.setAttribute18(r_tp_nota.get(i).getAttribute18());
                tipoNotaRi.setAttribute19(r_tp_nota.get(i).getAttribute19());
                tipoNotaRi.setAttribute20(r_tp_nota.get(i).getAttribute20());
                tipoNotaRi.setWmsFlag(r_tp_nota.get(i).getWmsFlag());
                tipoNotaRi.setComplexServiceFlag(r_tp_nota.get(i).getComplexServiceFlag());
                tipoNotaRi.setDocumentType(r_tp_nota.get(i).getDocumentType());
                tipoNotaRi.setIpiTributaryCodeFlag(r_tp_nota.get(i).getIpiTributaryCodeFlag());
                tipoNotaRi.setIncludeIrFlag(r_tp_nota.get(i).getIncludeIrFlag());
                tipoNotaRi.setIncludeSestSenatFlag(r_tp_nota.get(i).getIncludeSestSenatFlag());
                tipoNotaRi.setIrVendor(r_tp_nota.get(i).getIrVendor());
                tipoNotaRi.setIrCateg(r_tp_nota.get(i).getIrCateg());
                tipoNotaRi.setIrTax(r_tp_nota.get(i).getIrTax());
                tipoNotaRi.setInssSubstituteFlag(r_tp_nota.get(i).getInssSubstituteFlag());
                tipoNotaRi.setInssTax(r_tp_nota.get(i).getInssTax());
                tipoNotaRi.setInssAutonomousTax(r_tp_nota.get(i).getInssAutonomousTax());
                tipoNotaRi.setInssAutonomousTax1(r_tp_nota.get(i).getInssAdditionalTax1());
                tipoNotaRi.setInssAutonomousTax2(r_tp_nota.get(i).getInssAdditionalTax2());
                tipoNotaRi.setInssAutonomousTax3(r_tp_nota.get(i).getInssAdditionalTax3());
                tipoNotaRi.setRemunerationFreight(r_tp_nota.get(i).getRemunerationFreight());
                tipoNotaRi.setSestSenatTax(r_tp_nota.get(i).getSestSenatTax());
                tipoNotaRi.setUtilitiesFlag(r_tp_nota.get(i).getUtilitiesFlag());
                tipoNotaRi.setWorkerCategory(r_tp_nota.get(i).getWorkerCategory());
                tipoNotaRi.setCollectPisCcid(r_tp_nota.get(i).getCollectPisCcid());
                tipoNotaRi.setCollectCofinsCcid(r_tp_nota.get(i).getCollectCofinsCcid());
                tipoNotaRi.setAwtGroupId(r_tp_nota.get(i).getAwtGroupId());
                tipoNotaRi.setIncomeCode(r_tp_nota.get(i).getIncomeCode());
                tipoNotaRi.setUsuarioIdAlter(r_tp_nota.get(i).getUsuario());
                tipoNotaRi.setDtHrAlter(sysdate);

                // adicionando na lista o(s) obj(s)
                listaFinal.add(tipoNotaRi);

            } // cursor (r_tp_nota) "i"

            System.out.println("listaFinal: " + listaFinal);
        }catch (Exception e){
            response.setStatus("ERROR");
            response.setMsg("LOTE_ID: " + bodyRequest.getLoteId() + ", Ocorreu um erro ao procesar lote. ERRO: " + e);
            //
            System.out.print(sysdate);
            System.out.println(" - ### ERRO! LOTE_ID: " + bodyRequest.getLoteId() + ", Ocorreu um erro ao procesar lote. ERRO: " + e);
        }
        //
        if(!response.getStatus().equals("ERROR")){

            for (int j = 0;j < listaFinal.size(); j++){
                repositoryTipoNotaRi.save(listaFinal.get(j));
            }

        }else {
            /// ENVIA EMAIL CASO DÊ ERRO.
            enviaEmail.sendSimpleMessage("gabrielhnasc@outlook.com"
                    ,"(API)ERRO AO PROCESSAR TIPO NOTA RI",  response.getMsg());
        }

        return response;
    }
//==============================================================================================\\
    public List<TwoTipoNotaRi> getTipoNotaRi(){
        return repositoryTipoNotaRi.findAll();
    }
//==============================================================================================\\
    public List<TwoTipoNotaRi> getTipoNotaRiPorNome(String buscar){
        return repositoryTipoNotaRi.getTipoNotaRiPorNome(buscar);
    }
//==============================================================================================\\


}
