package com.twoone.XXODIntRastre.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="TWO_TIPO_NOTA_RI_TMP")
public class TwoTipoNotaRiTmp implements Serializable {

    @Id
    @Column(name="TIPO_NF_TMP_ID",length=15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tipoNfTmpId;

    @Column(name="ORGANIZATION_CODE",length=3)
    private String organizationCode;

    @Column(name="INVOICE_TYPE_ID")
    private Long invoiceTypeId;

    @Column(name="INVOICE_TYPE_CODE",length=15)
    private String invoiceTypeCode;

    @Column(name="ORGANIZATION_ID")
    private Long organizationId;

    @Column(name="PAYMENT_FLAG",length=1)
    private String paymentFlag;

    @Column(name="PARENT_FLAG",length=1)
    private String parentFlag;

    @Column(name="PRICE_ADJUST_FLAG",length=1)
    private String priceAdjustFlag;

    @Column(name="TAX_ADJUST_FLAG",length=1)
    private String taxAdjustFlag;

    @Column(name="REQUISITION_TYPE",length=30)
    private String requisitionType;

    @Column(name="FISCAL_FLAG",length=1)
    private String fiscalFlag;

    @Column(name="INVOICE_TYPE_LOOKUP_CODE",length=30)
    private String invoiceTypeLookupCode;

    @Column(name="CREDIT_DEBIT_FLAG",length=30)
    private String creditDebitFlag;

    @Column(name="INCLUDE_ICMS_FLAG",length=1)
    private String includeIcmsFlag;

    @Column(name="ICMS_REDUCTION_PERCENT")
    private Long icmsReductionPercent;

    @Column(name="INCLUDE_IPI_FLAG",length=1)
    private String includeIpiFlag;

    @Column(name="FREIGHT_FLAG",length=1)
    private String freightFlag;

    @Column(name="TRIANGLE_OPERATION",length=1)
    private String triangleOperation;

    @Column(name="CONTAB_FLAG",length=1)
    private String contabFlag;

    @Column(name="TRANSFER_TYPE",length=30)
    private String transferType;

    @Column(name="OPERATION_TYPE",length=30)
    private String operationType;

    @Column(name="CR_CODE_COMBINATION_ID")
    private Long crCodeCombinationId;

    @Column(name="ICMS_CODE_COMBINATION_ID")
    private Long icmsCodeCombinationId;

    @Column(name="IPI_CODE_COMBINATION_ID")
    private Long ipiCodeCombinationId;

    @Column(name="DIFF_ICMS_CODE_COMBINATION_ID")
    private Long diffIcmsCodeCombinationId;

    @Column(name="ISS_CODE_COMBINATION_ID")
    private Long issCodeCombinationId;

    @Column(name="IR_CODE_COMBINATION_ID")
    private Long irCodeCombinationId;

    @Column(name="IMPORT_TAX_CCID")
    private Long importTaxCcid;

    @Column(name="IMPORT_INSURANCE_CCID")
    private Long importInsuranceCcid;

    @Column(name="IMPORT_FREIGHT_CCID")
    private Long importFreightCcid;

    @Column(name="IMPORT_EXPENSE_CCID")
    private Long importExpenseCccid;

    @Column(name="ICMS_ST_CCID")
    private Long icmsStCcid;

    @Column(name="RETURN_FLAG",length=1)
    private String returnFlag;

    @Column(name="COST_ADJUST_FLAG",length=1)
    private String constAdjustFlag;

    @Column(name="FOREIGN_CURRENCY_USAGE",length=1)
    private String foreignCurrencyUsage;

    @Column(name="OPERATION_FISCAL_TYPE",length=30)
    private String operationFiscalType;

    @Column(name="SYMBOLIC_RETURN_CCID")
    private Long symbolicReturnCcid;

    @Column(name="DESCRIPTION",length=240)
    private String Description;

    @Column(name="INACTIVE_DATE")
    private Timestamp inactiveDate;

    @Column(name="INSS_CODE_COMBINATION_ID")
    private Long inssCodeCombinationId;

    @Column(name="BONUS_FLAG",length=1)
    private String bonusFlag;

    @Column(name="PROJECT_FLAG",length=1)
    private String projectFlag;

    @Column(name="ENCUMBRANCE_FLAG",length=1)
    private String encumbranceFlag;

    @Column(name="ACCOUNT_RECEIVABLE_CCID")
    private Long accountReceivableCcid;

    @Column(name="CUSTOMER_CCID")
    private Long customerCcid;

    @Column(name="RMA_IPI_REDUCTION_CCID")
    private Long rmaIpiReductionCcid;

    @Column(name="RMA_IPI_LIABILITY_CCID")
    private Long rmaIpiLiabilityCccid;

    @Column(name="RMA_ICMS_REDUCTION_CCID")
    private Long rmaIcmsReductionCcid;

    @Column(name="RMA_ICMS_LIABILITY_CCID")
    private Long rmaIcmsLiabilityCcid;

    @Column(name="FIXED_ASSETS_FLAG",length=1)
    private String fixedAssentsFlag;

    @Column(name="IPI_LIABILITY_CCID")
    private Long ipiLiabilityCcid;

    @Column(name="FUNRURAL_CCID")
    private Long funruralCcid;

    @Column(name="SEST_SENAT_CCID")
    private Long sestSenatCcid;

    @Column(name="IMPORT_ICMS_FLAG",length=1)
    private String importIcmsFlag;

    @Column(name="INCLUDE_ISS_FLAG",length=1)
    private String includeIssFlag;

    @Column(name="RETURN_CUSTOMER_FLAG",length=1)
    private String returnCustomerFlag;

    @Column(name="PAY_GROUP_LOOKUP_CODE",length=30)
    private String payGroupLookupCode;

    @Column(name="GENERATE_RETURN_INVOICE",length=1)
    private String generateReturnInvoice;

    @Column(name="AR_TRANSACTION_TYPE_ID")
    private Long arTransactionTypeId;

    @Column(name="AR_SOURCE_ID")
    private Long arSourceId;

    @Column(name="AR_CRED_ICMS_CATEGORY_ID")
    private Long arCredIcmsCategoryId;

    @Column(name="AR_CRED_ICMS_ST_CATEGORY_ID")
    private Long arCredIcmsStCategory;

    @Column(name="AR_CRED_IPI_CATEGORY_ID")
    private Long arCredIpiCategoryId;

    @Column(name="AR_DEB_ICMS_CATEGORY_ID")
    private Long arDebIcmsCategoryId;

    @Column(name="AR_DEB_ICMS_ST_CATEGORY_ID")
    private Long arDebIcmsStCategoryId;

    @Column(name="AR_DEB_IPI_CATEGORY_ID")
    private Long arDebIpiCategoryId;

    @Column(name="RMA_ICMS_ST_LIABILITY_CCID")
    private Long rmaIcmsStLiabilityCcid;

    @Column(name="RMA_ICMS_ST_REDUCTION_CCID")
    private Long rmaIcmsStReductionCcid;

    @Column(name="PIS_CODE_COMBINATION_ID")
    private Long pisCodeCombinationId;

    @Column(name="PIS_FLAG",length=1)
    private String pisFlag;

    @Column(name="INSS_EXPENSE_CCID")
    private Long inssExpenseCcid;

    @Column(name="VARIATION_COST_DEVOLUTION_CCID")
    private Long variationCostDevolutionCcid;

    @Column(name="SISCOMEX_CCID")
    private Long siscomexCcid;

    @Column(name="RMA_PIS_REDUCTION_CCID")
    private Long rmaPisReductionCcid;

    @Column(name="RMA_COFINS_REDUCTION_CCID")
    private Long rmaCofinsReductionCcid;

    @Column(name="EXCLUDE_ICMS_ST_FLAG",length=1)
    private String excludeIcmsStFlag;

    @Column(name="COFINS_CODE_COMBINATION_ID")
    private Long cofinsCodeCombinationId;

    @Column(name="COFINS_FLAG",length=1)
    private String cofinsFlag;

    @Column(name="PA_PROCESS_DATE_FROM")
    private Timestamp paProcessDataFrom;

    @Column(name="IMPORT_PIS_CCID")
    private Long importPisCcid;

    @Column(name="IMPORT_COFINS_CCID")
    private Long importCofinsCcid;

    @Column(name="PERMANENT_ACTIVE_CREDIT_FLAG",length=1)
    private String permanentActiveCreditFlag;

    @Column(name="RMA_PIS_RED_CCID")
    private Long rmaPisRedCcid;

    @Column(name="RMA_COFINS_RED_CCID")
    private Long rmaConfinsRedCcid;

    @Column(name="RECEIVED_BY",length=30)
    private String receiveBy;

    @Column(name="INSS_CALCULATION_FLAG",length=1)
    private String inssCalculationFlag;

    @Column(name="CUSTOMS_EXPENSE_CCID")
    private Long customsExpenseCcid;

    @Column(name="ICMS_ST_ANT_CCID")
    private Long icmsStAntCcid;

    @Column(name="ICMS_ST_ANT_CCID_RECUP")
    private Long icmsStAntCcidRecup;

    @Column(name="CREATION_DATE")
    private Timestamp creationDate;

    @Column(name="CREATED_BY")
    private Long createdBy;

    @Column(name="LAST_UPDATE_DATE")
    private Timestamp lastUpdateDate;

    @Column(name="LAST_UPDATED_BY")
    private Long lastUpdatedBy;

    @Column(name="LAST_UPDATE_LOGIN")
    private Long lastUpdateLogin;

    @Column(name="REQUEST_ID")
    private Long requestId;

    @Column(name="PROGRAM_APPLICATION_ID")
    private Long programApplicationId;

    @Column(name="PROGRAM_ID")
    private Long programId;

    @Column(name="PROGRAM_UPDATE_DATE")
    private Timestamp programUpdateDate;

    @Column(name="ATTRIBUTE_CATEGORY",length=30)
    private String attributeCategory;

    @Column(name="ATTRIBUTE1",length=150)
    private String attribute1;

    @Column(name="ATTRIBUTE2",length=150)
    private String attribute2;

    @Column(name="ATTRIBUTE3",length=150)
    private String attribute3;

    @Column(name="ATTRIBUTE4",length=150)
    private String attribute4;

    @Column(name="ATTRIBUTE5",length=150)
    private String attribute5;

    @Column(name="ATTRIBUTE6",length=150)
    private String attribute6;

    @Column(name="ATTRIBUTE7",length=150)
    private String attribute7;

    @Column(name="ATTRIBUTE8",length=150)
    private String attribute8;

    @Column(name="ATTRIBUTE9",length=150)
    private String attribute9;

    @Column(name="ATTRIBUTE10",length=150)
    private String attribute10;

    @Column(name="ATTRIBUTE11",length=150)
    private String attribute11;

    @Column(name="ATTRIBUTE12",length=150)
    private String attribute12;

    @Column(name="ATTRIBUTE13",length=150)
    private String attribute13;

    @Column(name="ATTRIBUTE14",length=150)
    private String attribute14;

    @Column(name="ATTRIBUTE15",length=150)
    private String attribute15;

    @Column(name="ATTRIBUTE16",length=150)
    private String attribute16;

    @Column(name="ATTRIBUTE17",length=150)
    private String attribute17;

    @Column(name="ATTRIBUTE18",length=150)
    private String attribute18;

    @Column(name="ATTRIBUTE19",length=150)
    private String attribute19;

    @Column(name="ATTRIBUTE20",length=150)
    private String attribute20;

    @Column(name="WMS_FLAG",length=1)
    private String wmsFlag;

    @Column(name="COMPLEX_SERVICE_FLAG",length=1)
    private String complexServiceFlag;

    @Column(name="DOCUMENT_TYPE",length=25)
    private String documentType;

    @Column(name="IPI_TRIBUTARY_CODE_FLAG",length=1)
    private String ipiTributaryCodeFlag;

    @Column(name="INCLUDE_IR_FLAG",length=1)
    private String includeIrFlag;

    @Column(name="INCLUDE_SEST_SENAT_FLAG",length=1)
    private String includeSestSenatFlag;

    @Column(name="IR_VENDOR", length = 30)
    private String irVendor;

    @Column(name="IR_CATEG",length=30)
    private String irCateg;

    @Column(name="IR_TAX")
    private Long irTax;

    @Column(name="INSS_SUBSTITUTE_FLAG",length=1)
    private String inssSubstituteFlag;

    @Column(name="INSS_TAX")
    private Long inssTax;

    @Column(name="INSS_AUTONOMOUS_TAX")
    private Long inssAutonomousTax;

    @Column(name="INSS_ADDITIONAL_TAX_1")
    private Long inssAdditionalTax1;

    @Column(name="INSS_ADDITIONAL_TAX_2")
    private Long inssAdditionalTax2;

    @Column(name="INSS_ADDITIONAL_TAX_3")
    private Long inssAdditionalTax3;

    @Column(name="REMUNERATION_FREIGHT")
    private Long remunerationFreight;

    @Column(name="SEST_SENAT_TAX")
    private Long sestSenatTax;

    @Column(name="UTILITIES_FLAG",length=1)
    private String utilitiesFlag;

    @Column(name="WORKER_CATEGORY",length=30)
    private String workerCategory;

    @Column(name="COLLECT_PIS_CCID")
    private Long collectPisCcid;

    @Column(name="COLLECT_COFINS_CCID")
    private Long collectCofinsCcid;

    @Column(name="AWT_GROUP_ID",length=15)
    private Long awtGroupId;

    @Column(name="INCOME_CODE",length=30)
    private String incomeCode;

    @Column(name="BASE",length=240)
    private String base;

    @Column(name="BASE_ID")
    private Long baseId;

    @Column(name="USUARIO",length=50)
    private String usuario;

    @Column(name="INTEGRATION_DATE")
    private Timestamp integrationDate;

    @Column(name="INSTANCE_ID")
    private Long instanceId;
    
    @Column(name="MSG",length=4000)
    private String msg;

    @Column(name="STATUS",length=25)
    private String status;

    @ManyToOne
    @JoinColumn(name="ID",nullable = false)
    private TwoLotesTipoNotaRi loteId;


}
