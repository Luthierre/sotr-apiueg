package br.ueg.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="03c_ordem_de_trafego")
public class OrdemTrafego {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "solicitante")
	private String solicitante;
	
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	
	
	@Column(name = "data_solicitacao")
	private LocalDate dataSolicitacao;
	
	@Column(name = "observacao")
	private String observacao;
	
	
	@ManyToOne
	@JoinColumn(name = "codigo_veiculo")
	private Veiculo veiculo;
	@ManyToOne
	@JoinColumn(name = "codigo_condutor")
	private Condutor condutor;
	@ManyToOne(optional=true)
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;
	
	@ManyToOne(optional=true)
	@JoinColumn(name = "codigo_campus")
	private Campus campus;
	
	@JsonIgnoreProperties("ordem")
	@Valid
	@OneToMany(mappedBy = "ordem", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Rota> rotas;
	
	@Column(name = "total_km", precision=2, scale=10)
	private BigDecimal totalKm;	
	
	@Column(name="ativo", length=7)
	private boolean ativo;	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Condutor getCondutor() {
		return condutor;
	}
	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Campus getCampus() {
		return campus;
	}
	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	public List<Rota> getRotas() {
		return rotas;
	}
	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}
	public BigDecimal getTotalKm() {
		return totalKm;
	}
	public void setTotalKm(BigDecimal totalKm) {
		this.totalKm = totalKm;
	}
	@Transient
    public boolean getAtivo() {
        return this.ativo;
    }
    public void setAtivo(boolean isAtivo) {
        this.ativo = isAtivo;
    }
    @Basic
    private String getIsAtivoValue() {
        if (getAtivo()) {
            return "T";
        } else {
            return "F";
        }
    }
    
    

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemTrafego other = (OrdemTrafego) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
