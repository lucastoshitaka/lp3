/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author lucastoshitaka
 */
@Entity
@Table(name = "modalidades")
@NamedQueries({
    @NamedQuery(name = "Modalidades.findAll", query = "SELECT m FROM Modalidades m")})
public class Modalidades implements Serializable {

    @ManyToMany(mappedBy = "modalidadesList")
    private List<Aluno> alunoList;
    @ManyToMany(mappedBy = "modalidadesList")
    private List<Disciplinas> disciplinasList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_modalidades")
    private Integer idModalidades;
    @Basic(optional = false)
    @Column(name = "nome_modalidades")
    private String nomeModalidades;

    public Modalidades() {
    }

    public Modalidades(Integer idModalidades) {
        this.idModalidades = idModalidades;
    }

    public Modalidades(Integer idModalidades, String nomeModalidades) {
        this.idModalidades = idModalidades;
        this.nomeModalidades = nomeModalidades;
    }

    public Integer getIdModalidades() {
        return idModalidades;
    }

    public void setIdModalidades(Integer idModalidades) {
        this.idModalidades = idModalidades;
    }

    public String getNomeModalidades() {
        return nomeModalidades;
    }

    public void setNomeModalidades(String nomeModalidades) {
        this.nomeModalidades = nomeModalidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModalidades != null ? idModalidades.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modalidades)) {
            return false;
        }
        Modalidades other = (Modalidades) object;
        if ((this.idModalidades == null && other.idModalidades != null) || (this.idModalidades != null && !this.idModalidades.equals(other.idModalidades))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
return idModalidades + ";" + nomeModalidades;    }

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    public List<Disciplinas> getDisciplinasList() {
        return disciplinasList;
    }

    public void setDisciplinasList(List<Disciplinas> disciplinasList) {
        this.disciplinasList = disciplinasList;
    }
    
}
