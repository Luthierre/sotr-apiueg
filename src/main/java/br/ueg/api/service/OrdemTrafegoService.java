package br.ueg.api.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.ueg.api.dto.OrdemTrafegoEstatisticaCampusDia;
import br.ueg.api.dto.OrdemTrafegoEstatisticaVeiculo;
import br.ueg.api.dto.OrdemTrafegoRelatorio;
import br.ueg.api.model.Campus;
import br.ueg.api.model.OrdemTrafego;
import br.ueg.api.model.Rota;
import br.ueg.api.repository.CampusRepository;
import br.ueg.api.repository.OrdemTrafegoRepository;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class OrdemTrafegoService {

	@Autowired
	private OrdemTrafegoRepository repository;
	
	@Autowired
	private CampusRepository campusRepository;
	
	
	
	
	public byte[] relatorioOrdemTrafegoImpressao(Long id, String usuario) throws Exception {		
		OrdemTrafego ordem = new OrdemTrafego();
		ordem = repository.porOrdem(id);	
	
		Date dataSolicitacao = null;
		Date dataInicioRota = null;
		BigDecimal kmInicioRota = null;
		String horaInicioRota = null;
		
		Date dataFimRota = null;
		BigDecimal kmFimRota = null;
		String horaFimRota = null;
		
		dataSolicitacao = Date.valueOf(ordem.getDataSolicitacao());
		
		List<OrdemTrafegoRelatorio> dados = new ArrayList<>();
		dados.addAll(converterListaRotasEmListOrdemTrafegoSaida(ordem.getRotas()));	
		
		if(ordem.getRotas()!=null && !ordem.getRotas().isEmpty()) {
			dataInicioRota = Date.valueOf(ordem.getRotas().get(0).getDataSaida());
			kmInicioRota = ordem.getRotas().get(0).getKmSaida();
			horaInicioRota = ordem.getRotas().get(0).getHoraSaida();
			
			dataFimRota = Date.valueOf(ordem.getRotas().get(dados.size()-1).getDataChegada());
			kmFimRota = ordem.getRotas().get(dados.size()-1).getKmChegada();
			horaFimRota = ordem.getRotas().get(dados.size()-1).getHoraChegada();
			
		}		
		Map<String, Object> parametros = new HashMap<>();		
		parametros.put("ORDEM", ordem);		
		parametros.put("DATASOLICITACAO", dataSolicitacao);
		parametros.put("DATAINICIOROTA", dataInicioRota);
		parametros.put("KMINICIOROTA", kmInicioRota);
		parametros.put("HORAINICIOROTA", horaInicioRota);
		parametros.put("DATAFIMROTA", dataFimRota);
		parametros.put("KMFIMROTA", kmFimRota);
		parametros.put("HORAFIMROTA", horaFimRota);
		parametros.put("USUARIO_IMPRESSAO", usuario);		
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		InputStream inpuStream = this.getClass().getResourceAsStream("/relatorios/ordem-trafego-impressao.jasper");
		InputStream logoUeg = this.getClass().getResourceAsStream("/relatorios/logoueg.png");
		InputStream logoGoias = this.getClass().getResourceAsStream("/relatorios/logogoias.png");
		parametros.put("IMAGEM_LOGO_UEG", logoUeg);
		parametros.put("IMAGEM_LOGO_GOIAS", logoGoias);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inpuStream, parametros, new JRBeanCollectionDataSource(dados));
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	public OrdemTrafego atualizar(Long codigo, OrdemTrafego ordem) {
		OrdemTrafego ordemSalvo = repository.findOne(codigo);
		ordemSalvo.getRotas().clear();
		ordemSalvo.getRotas().addAll(ordem.getRotas());
		ordemSalvo.getRotas().forEach(c -> c.setOrdem(ordemSalvo));
		ordemSalvo.setTotalKm(calcularKmPercorrido(ordemSalvo));
		BeanUtils.copyProperties(ordem, ordemSalvo, "codigo", "rotas");	
		
		return repository.save(ordemSalvo);
	}
	
	public OrdemTrafego salvar(OrdemTrafego ordem) {
		if(ordem.getDataCadastro() == null) {
			ordem.setDataCadastro(LocalDate.now());
		}
		if(ordem.getRotas() !=null ) {
			ordem.getRotas()
			.forEach(c -> c.setOrdem(ordem));
		}
		//ordem.setTotalKm(calcularKmPercorrido(ordem));
		return repository.save(ordem);
	}
	
	private List<OrdemTrafegoRelatorio> converterListaRotasEmListOrdemTrafegoSaida(List<Rota> rotas) {
		List<OrdemTrafegoRelatorio> dados = new ArrayList<>();
		
		if(rotas!=null && !rotas.isEmpty() ) {
			for (Rota rota : rotas) {
				OrdemTrafegoRelatorio dado = new OrdemTrafegoRelatorio(rota.getCodigo(), rota.getLocalSaida(),
						Date.valueOf(rota.getDataSaida()), rota.getHoraSaida() , rota.getKmSaida(), rota.getLocalChegada(), 
						Date.valueOf(rota.getDataChegada()), rota.getHoraChegada() , rota.getKmChegada(), rota.getKmPercorrido(), 
						rota.getNome(), rota.getServico(), rota.getObservacao());
				dados.add(dado);				
			}
		}
		return dados;
	}
	
	public BigDecimal calcularKmPercorrido(OrdemTrafego ordem) {
		BigDecimal total = BigDecimal.ZERO;
		if(ordem.getRotas()!= null && !ordem.getRotas().isEmpty()) {
			ordem.getRotas().forEach(r -> total.add(r.getKmPercorrido()));
		}
		return total;
	}
	
	public OrdemTrafego atualizarPropriedadeAtivo(Long codigo, boolean ativo) {
		
		OrdemTrafego ordemSalva = buscarOrdemPeloCodigo(codigo);	
		System.out.println(ativo);
		System.out.println(ordemSalva.getAtivo());
		ordemSalva.setAtivo(ativo);
		System.out.println(ordemSalva.getAtivo());
		return repository.save(ordemSalva);
		
	}
	public OrdemTrafego buscarOrdemPeloCodigo(Long codigo) {
		OrdemTrafego ordemSalva = repository.findOne(codigo);
		if (ordemSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return ordemSalva;
	}

	public List<OrdemTrafegoEstatisticaVeiculo> ordemTrafegoVeiculoPorMes(String campus, LocalDate now) {
		List<OrdemTrafegoEstatisticaVeiculo> lista = new ArrayList<>();
		Optional<Campus> optional = campusRepository.findByNomeContaining(campus);
		if(optional.isPresent()) {			
			lista = repository.ordemTrafegoVeiculoPorMes(optional.get(), now);
		}	
		return lista;
			
	} 
	
	public List<OrdemTrafegoEstatisticaCampusDia> ordemTrafegoCampusPorDia(String campus, LocalDate now) {
		List<OrdemTrafegoEstatisticaCampusDia> lista = new ArrayList<>();
		Optional<Campus> optional = campusRepository.findByNomeContaining(campus);
		if(optional.isPresent()) {			
			lista = repository.ordemTrafegoCampusPorDia(optional.get(), now);
		}	
		return lista;
			
	} 
	
}
