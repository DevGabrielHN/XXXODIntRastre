package com.twoone.XXODIntRastre;

import com.twoone.XXODIntRastre.app.entities.*;
import com.twoone.XXODIntRastre.app.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.Timestamp;



@SpringBootTest
class XxodIntRastreApplicationTests {

	@Autowired
	private TwoUnidadeTmpRepository tut;

	@Autowired
	private TwoLoteUnidadeRepository tlut;

	@Autowired
	private TwoBaseDadoRepository tbd;

	@Autowired
	private TwoCidadeRepository tc;

	@Autowired
	private TwoPaisRepository tp;

	@Autowired
	private TwoUnidadeFederativaRepository tuf;

	@Autowired
	private XxodDeParaRepository xdp;

	@Autowired
	private XxodDeParaDetailRepository xdpd;

	@Autowired
	private TwoUnidadeRepository tu;

	@Autowired
	private TwoSistemaOrigemRepository tso;

	@Test
	public void LoteUnidade(){


	    Timestamp timestamp;
		timestamp = new Timestamp(System.currentTimeMillis());
/*
// ===================== TWO_UNIDADE_TMP =====================  \\
		TwoUnidadeTmp objUnidadeTmp1 = new TwoUnidadeTmp();
		TwoUnidadeTmp objUnidadeTmp2 = new TwoUnidadeTmp();
		TwoUnidadeTmp objUnidadeTmp3 = new TwoUnidadeTmp();
		TwoUnidadeTmp objUnidadeTmp4 = new TwoUnidadeTmp();
		TwoUnidadeTmp objUnidadeTmp5 = new TwoUnidadeTmp();


		objUnidadeTmp1.setUnidadeTmpId(1L);
		objUnidadeTmp1.setCod("00992245");
		objUnidadeTmp1.setDescr("UO TESTE3");
		objUnidadeTmp1.setTipo("UO");
		objUnidadeTmp1.setIdentificador(null);
		objUnidadeTmp1.setDtFinal(null);
		objUnidadeTmp1.setUoDessaOi("0215009");
		objUnidadeTmp1.setOrganizationCode(null);
		objUnidadeTmp1.setBase("EBSBR");
		objUnidadeTmp1.setCidade("CAMPINAS");
		objUnidadeTmp1.setCodCidade(null);
		objUnidadeTmp1.setEstado("SP");
		objUnidadeTmp1.setCodEstado("SP");
		objUnidadeTmp1.setPais("BRASIL");
		objUnidadeTmp1.setCodPais("BR");
		objUnidadeTmp1.setUsuario("GABRIEL");
		objUnidadeTmp1.setIntegrationDate(timestamp);
		objUnidadeTmp1.setInstanceId(1L);
		objUnidadeTmp1.setMsg(null);
		objUnidadeTmp1.setStatus("NEW");
		objUnidadeTmp1.setCreatedDate(timestamp);
		objUnidadeTmp1.setCreatedBy("GABRIEL");
		objUnidadeTmp1.setLastUpdadeBy("GABRIEL");
		objUnidadeTmp1.setLastUpdateDate(timestamp);
//
		objUnidadeTmp2.setUnidadeTmpId(2L);
		objUnidadeTmp2.setCod("00112233");
		objUnidadeTmp2.setDescr("UO GABRIEL");
		objUnidadeTmp2.setTipo("UO");
		objUnidadeTmp2.setIdentificador(null);
		objUnidadeTmp2.setDtFinal(null);
		objUnidadeTmp2.setUoDessaOi("0215007");
		objUnidadeTmp2.setOrganizationCode(null);
		objUnidadeTmp2.setBase("EBSBR");
		objUnidadeTmp2.setCidade("CAMPINAS");
		objUnidadeTmp2.setCodCidade(null);
		objUnidadeTmp2.setEstado("SP");
		objUnidadeTmp2.setCodEstado("SP");
		objUnidadeTmp2.setPais("BRASIL");
		objUnidadeTmp2.setCodPais("BR");
		objUnidadeTmp2.setUsuario("GABRIEL");
		objUnidadeTmp2.setIntegrationDate(timestamp);
		objUnidadeTmp2.setInstanceId(1L);
		objUnidadeTmp2.setMsg("Integracao Executada");
		objUnidadeTmp2.setStatus("NEW");
		objUnidadeTmp2.setCreatedDate(timestamp);
		objUnidadeTmp2.setCreatedBy("GABRIEL");
		objUnidadeTmp2.setLastUpdadeBy("GABRIEL");
		objUnidadeTmp2.setLastUpdateDate(timestamp);

		// Esse obj não será validado para a tabela principal
		objUnidadeTmp3.setUnidadeTmpId(3L);
		objUnidadeTmp3.setCod(null);
		objUnidadeTmp3.setDescr("UO TESTE4");
		objUnidadeTmp3.setTipo("UO");
		objUnidadeTmp3.setIdentificador(null);
		objUnidadeTmp3.setDtFinal(null);
		objUnidadeTmp3.setUoDessaOi("0215009");
		objUnidadeTmp3.setOrganizationCode(null);
		objUnidadeTmp3.setBase("EBSBR");
		objUnidadeTmp3.setCidade(null);
		objUnidadeTmp3.setCodCidade(null);
		objUnidadeTmp3.setEstado("SP");
		objUnidadeTmp3.setCodEstado("SP");
		objUnidadeTmp3.setPais("BRASIL");
		objUnidadeTmp3.setCodPais("BR");
		objUnidadeTmp3.setUsuario("GABRIEL");
		objUnidadeTmp3.setIntegrationDate(timestamp);
		objUnidadeTmp3.setInstanceId(1L);
		objUnidadeTmp3.setMsg(null);
		objUnidadeTmp3.setStatus("NEW");
		objUnidadeTmp3.setCreatedDate(timestamp);
		objUnidadeTmp3.setCreatedBy("GABRIEL");
		objUnidadeTmp3.setLastUpdadeBy("GABRIEL");
		objUnidadeTmp3.setLastUpdateDate(timestamp);

		// Esse obj não será validado para a tabela principal
		objUnidadeTmp4.setUnidadeTmpId(4L);
		objUnidadeTmp4.setCod("00142233");
		objUnidadeTmp4.setDescr(null);
		objUnidadeTmp4.setTipo("UO");
		objUnidadeTmp4.setIdentificador(null);
		objUnidadeTmp4.setDtFinal(null);
		objUnidadeTmp4.setUoDessaOi("0215009");
		objUnidadeTmp4.setOrganizationCode(null);
		objUnidadeTmp4.setBase("EBSBR");
		objUnidadeTmp4.setCidade(null);
		objUnidadeTmp4.setCodCidade(null);
		objUnidadeTmp4.setEstado("SP");
		objUnidadeTmp4.setCodEstado("SP");
		objUnidadeTmp4.setPais("BRASIL");
		objUnidadeTmp4.setCodPais("BR");
		objUnidadeTmp4.setUsuario("GABRIEL");
		objUnidadeTmp4.setIntegrationDate(timestamp);
		objUnidadeTmp4.setInstanceId(1L);
		objUnidadeTmp4.setMsg(null);
		objUnidadeTmp4.setStatus("NEW");
		objUnidadeTmp4.setCreatedDate(timestamp);
		objUnidadeTmp4.setCreatedBy("GABRIEL");
		objUnidadeTmp4.setLastUpdadeBy("GABRIEL");
		objUnidadeTmp4.setLastUpdateDate(timestamp);


		objUnidadeTmp5.setUnidadeTmpId(5L);
		objUnidadeTmp5.setCod("00112733");
		objUnidadeTmp5.setDescr("UO GABRIEL");
		objUnidadeTmp5.setTipo("UO");
		objUnidadeTmp5.setIdentificador(null);
		objUnidadeTmp5.setDtFinal(null);
		objUnidadeTmp5.setUoDessaOi("0215007");
		objUnidadeTmp5.setOrganizationCode(null);
		objUnidadeTmp5.setBase("EBSBR");
		objUnidadeTmp5.setCidade("CAMPINAS");
		objUnidadeTmp5.setCodCidade(null);
		objUnidadeTmp5.setEstado("SP");
		objUnidadeTmp5.setCodEstado("SP");
		objUnidadeTmp5.setPais("BRASIL");
		objUnidadeTmp5.setCodPais("BR");
		objUnidadeTmp5.setUsuario("GABRIEL");
		objUnidadeTmp5.setIntegrationDate(timestamp);
		objUnidadeTmp5.setInstanceId(1L);
		objUnidadeTmp5.setMsg("Integracao Executada");
		objUnidadeTmp5.setStatus("NEW");
		objUnidadeTmp5.setCreatedDate(timestamp);
		objUnidadeTmp5.setCreatedBy("GABRIEL");
		objUnidadeTmp5.setLastUpdadeBy("GABRIEL");
		objUnidadeTmp5.setLastUpdateDate(timestamp);



		tut.save(objUnidadeTmp1);
		tut.save(objUnidadeTmp2);
		tut.save(objUnidadeTmp3);
		tut.save(objUnidadeTmp4);
		tut.save(objUnidadeTmp5);
*/
// ==========================================================  \\
// =================== TWO_SISTEMA_ORIGEM ====================  \\

		TwoSistemaOrigem objSistemaOrigem1 = new TwoSistemaOrigem();
		TwoSistemaOrigem objSistemaOrigem2 = new TwoSistemaOrigem();

		objSistemaOrigem1.setSistemaId(1L);
		objSistemaOrigem1.setNome("EBS");
		objSistemaOrigem1.setUsuarioIdCriacao("GABRIEL");
		objSistemaOrigem1.setDtHrCriacao(timestamp);
		objSistemaOrigem1.setUsuarioIdAlter("GABRIEL");
		objSistemaOrigem1.setDtHrAlter(timestamp);

		objSistemaOrigem2.setSistemaId(2L);
		objSistemaOrigem2.setNome("MWD");
		objSistemaOrigem2.setUsuarioIdCriacao("GABRIEL");
		objSistemaOrigem2.setDtHrCriacao(timestamp);
		objSistemaOrigem2.setUsuarioIdAlter("GABRIEL");
		objSistemaOrigem2.setDtHrAlter(timestamp);

		tso.save(objSistemaOrigem1);
		tso.save(objSistemaOrigem2);


// ==========================================================  \\
// ==================== TWO_BASE_DADO ========================  \\
		TwoBaseDado objBaseDado1 = new TwoBaseDado();
		TwoBaseDado objBaseDado2 = new TwoBaseDado();
		TwoBaseDado objBaseDado3 = new TwoBaseDado();

		objBaseDado1.setBaseId(1L);
		objBaseDado1.setNome("EBSBR");
		objBaseDado1.setSistemaId(objSistemaOrigem1);
		objBaseDado1.setUsuarioIdCriacao("GABRIEL");
		objBaseDado1.setDtHrCriacao(timestamp);
		objBaseDado1.setUsuarioIdAlter("GABRIEL");
		objBaseDado1.setDtHrAlter(timestamp);
		objBaseDado1.setSistemIntWs("EBSOOG");
		objBaseDado1.setApiIntWs("INTERFACE_RI_RASTRE");
		objBaseDado1.setDyqrServerName("PGENOOG");
		objBaseDado1.setLookupIdDocumentModel(4749L);
//
		objBaseDado2.setBaseId(2L);
		objBaseDado2.setNome("MWDBR");
		objBaseDado2.setSistemaId(objSistemaOrigem2);
		objBaseDado2.setUsuarioIdCriacao("GABRIEL");
		objBaseDado2.setDtHrCriacao(timestamp);
		objBaseDado2.setUsuarioIdAlter("GABRIEL");
		objBaseDado2.setDtHrAlter(timestamp);
		objBaseDado2.setSistemIntWs(null);
		objBaseDado2.setApiIntWs(null);
		objBaseDado2.setDyqrServerName(null);
		objBaseDado2.setLookupIdDocumentModel(4749L);

		objBaseDado3.setBaseId(3L);
		objBaseDado3.setNome("EBSOG");
		objBaseDado3.setSistemaId(objSistemaOrigem2);
		objBaseDado3.setUsuarioIdCriacao("GABRIEL");
		objBaseDado3.setDtHrCriacao(timestamp);
		objBaseDado3.setUsuarioIdAlter("GABRIEL");
		objBaseDado3.setDtHrAlter(timestamp);
		objBaseDado3.setSistemIntWs(null);
		objBaseDado3.setApiIntWs(null);
		objBaseDado3.setDyqrServerName(null);
		objBaseDado3.setLookupIdDocumentModel(4749L);


		tbd.save(objBaseDado1);
		tbd.save(objBaseDado2);
		tbd.save(objBaseDado3);
// ==========================================================  \\
// ===================== TWO_PAIS ===========================  \\
		/*TwoPais objPais = new TwoPais();

		objPais.setPaisId(null); // AUTO
		objPais.setNome("BRASIL");
		objPais.setSigla("BR");
		objPais.setUsuarioIdCriacao("GABRIEL");
		objPais.setDtHrCriacao(timestamp);
		objPais.setUsuarioIdAlter("GABRIEL");
		objPais.setDtHrAlter(timestamp);

		tp.save(objPais);*/

// ==========================================================  \\
// ================== TWO_UNIDADE_FEDERATIVA ================  \\

		/*TwoUnidadeFederativa objUnidadeFederativa = new TwoUnidadeFederativa();

		objUnidadeFederativa.setUfId(null); // AUTO
		objUnidadeFederativa.setNome("SAO PAULO");
		objUnidadeFederativa.setSigla("SP");
		objUnidadeFederativa.setUsuarioIdCriacao("GABRIEL");
		objUnidadeFederativa.setDtHrCriacao(timestamp);
		objUnidadeFederativa.setUsuarioIdAlter("GABRIEL");
		objUnidadeFederativa.setDtHrAlter(timestamp);
		objUnidadeFederativa.setPaisId(objPais);

		tuf.save(objUnidadeFederativa);*/
// ==========================================================  \\
// ====================== TWO_CIDADE ========================  \\

		/*TwoCidade objCidade1 = new TwoCidade();
		TwoCidade objCidade2 = new TwoCidade();

		objCidade1.setCidId(null); // AUTO
		objCidade1.setNome("CAMPINAS");
		objCidade1.setUsuarioIdCriacao("GABRIEL");
		objCidade1.setDtHrCriacao(timestamp);
		objCidade1.setUsuarioIdAlter("GABRIEL");
		objCidade1.setDtHrAlter(timestamp);
		objCidade1.setUfId(objUnidadeFederativa);
		objCidade1.setUrlPrefeitura(null);
		objCidade1.setStatusImpotacao(null);
		objCidade1.setDtFinal(null);
//
		objCidade2.setCidId(null); // AUTO
		objCidade2.setNome("PAULINIA");
		objCidade2.setUsuarioIdCriacao("GABRIEL");
		objCidade2.setDtHrCriacao(timestamp);
		objCidade2.setUsuarioIdAlter("GABRIEL");
		objCidade2.setDtHrAlter(timestamp);
		objCidade2.setUfId(objUnidadeFederativa);
		objCidade2.setUrlPrefeitura(null);
		objCidade2.setStatusImpotacao(null);
		objCidade2.setDtFinal(null);

		tc.save(objCidade1);
		tc.save(objCidade2);*/
// ==========================================================  \\
// ====================== XXOD_DE_PARA =======================  \\

		XxodDePara objDePara = new XxodDePara();

		objDePara.setIdDePara(1L);
		objDePara.setReferencia("RASTRE_UF");
		objDePara.setNome("UF");
		objDePara.setDescricao("Cadastro de de-para para codido e nome dos estados do BR");

		xdp.save(objDePara);
// ==========================================================  \\
// =================== XXOD_DE_PARA_DETAIL ===================  \\

		XxodDeParaDetail objDeParaDetail = new XxodDeParaDetail();

		objDeParaDetail.setIdDeParaDetail(25L);
		objDeParaDetail.setIdDePara(objDePara);
		objDeParaDetail.setOrigem01("BR");
		objDeParaDetail.setOrigem02("SP");
		objDeParaDetail.setOrigem03(null);
		objDeParaDetail.setOrigem04(null);
		objDeParaDetail.setOrigem05(null);
		objDeParaDetail.setOrigem06(null);
		objDeParaDetail.setOrigem07(null);
		objDeParaDetail.setOrigem08(null);
		objDeParaDetail.setOrigem09(null);
		objDeParaDetail.setOrigem10(null);
		objDeParaDetail.setDestino01("SAO PAULO");
		objDeParaDetail.setDestino02(null);
		objDeParaDetail.setDestino03(null);
		objDeParaDetail.setDestino04(null);
		objDeParaDetail.setDestino05(null);
		objDeParaDetail.setDestino06(null);
		objDeParaDetail.setDestino07(null);
		objDeParaDetail.setDestino08(null);
		objDeParaDetail.setDestino09(null);
		objDeParaDetail.setDestino10(null);
		objDeParaDetail.setHabilitado(null);

		xdpd.save(objDeParaDetail);
// ==========================================================  \\
// ================== TWO_LOTE_UNIDADE ==================  \\

		TwoLotesUnidades objLoteUnidade = new TwoLotesUnidades();

		objLoteUnidade.setId(1L);
		objLoteUnidade.setNumeroDoLote(1L);
		objLoteUnidade.setCreatedBy("GABRIEL");
		objLoteUnidade.setCreatedDate(timestamp);
		objLoteUnidade.setLastUpdadeBy("GABRIEL");
		objLoteUnidade.setLastUpdateDate(timestamp);


		//tlut.save(objLoteUnidade); // <========

// ==========================================================  \\
	}



}
