/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
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
 * @author igor
 */
@Entity
@Table(name = "PAPEL")
@NamedQueries({
  //  @NamedQuery(name = "Papel.findAll", query = "SELECT u FROM PAPEL u")
//,
//@NamedQuery(name = "Automovel.findByMontadora", query = "SELECT a FROM Automovel a WHERE a.montadora = :montadora")
//,
// @NamedQuery(name = "Automovel.findByDono", query = "SELECT a FROM Automovel a WHERE a.dono = :dono")
})
public class Papel implements Comparable<Papel>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private long id;
    private String nome;

    @ManyToMany(mappedBy = "papeis")
    private Set<Usuario> usuarios = new HashSet<>();

    public Papel() {
    }

    public Papel(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }

    @Override
    public int compareTo(Papel o) {
        return this.getNome().compareToIgnoreCase(o.getNome());
    }
}
