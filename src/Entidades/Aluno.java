/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lucastoshitaka
 */
@Entity
@Table(name = "aluno")
@NamedQueries({
    @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a")})
public class Aluno implements Serializable {

    @JoinTable(name = "aluno_has_disciplinas", joinColumns = {
        @JoinColumn(name = "aluno_cpf", referencedColumnName = "cpf")}, inverseJoinColumns = {
        @JoinColumn(name = "disciplinas_id_disciplinas", referencedColumnName = "id_disciplinas")})
    @ManyToMany
    private List<Disciplinas> disciplinasList;
    @JoinTable(name = "aluno_has_modalidades", joinColumns = {
        @JoinColumn(name = "aluno_cpf", referencedColumnName = "cpf")}, inverseJoinColumns = {
        @JoinColumn(name = "modalidades_id_modalidades", referencedColumnName = "id_modalidades")})
    @ManyToMany
    private List<Modalidades> modalidadesList;
    @JoinColumn(name = "estado_sigla_estado", referencedColumnName = "sigla_estado")
    @ManyToOne(optional = false)
    private Estado estado;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @Column(name = "foto")
    private String foto;
    @Basic(optional = false)
    @Column(name = "data_inscricao")
    @Temporal(TemporalType.DATE)
    private Date dataInscricao;

    public Aluno() {
    }

    public Aluno(String cpf) {
        this.cpf = cpf;
    }

    public Aluno(String cpf, String nome, String senha, String foto, Date dataInscricao) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.foto = foto;
        this.dataInscricao = dataInscricao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(Date dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpf != null ? cpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.cpf == null && other.cpf != null) || (this.cpf != null && !this.cpf.equals(other.cpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
SimpleDateFormat dateDataInscricao = new SimpleDateFormat("");
return cpf + ";" + nome + ";" + senha + ";" + foto + ";" + dateDataInscricao.format(dataInscricao);    }

    public List<Disciplinas> getDisciplinasList() {
        return disciplinasList;
    }

    public void setDisciplinasList(List<Disciplinas> disciplinasList) {
        this.disciplinasList = disciplinasList;
    }

    public List<Modalidades> getModalidadesList() {
        return modalidadesList;
    }

    public void setModalidadesList(List<Modalidades> modalidadesList) {
        this.modalidadesList = modalidadesList;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
}
