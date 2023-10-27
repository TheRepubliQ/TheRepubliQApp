package br.edu.ifsp.tcc.apprepublic.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

//Entity
//@Table(name = "home")
public class HomeEntity {

  //  @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("id")
    @Expose
    private Long id;
    //@NotNull
    //@Size(min = 3, max = 200)
    @SerializedName("descr")
    @Expose
    private String descr;
   // @Enumerated(EnumType.STRING)
   // @NotNull
   @SerializedName("tipo")
   @Expose
    private Tipo tipo;
   // @NotNull
   @SerializedName("preco")
   @Expose
    private float preco;
   // @NotNull
   @SerializedName("ofertado")
   @Expose
    private Boolean ofertado;
    //@Embedded
    @SerializedName("endereco")
    @Expose
    private Endereco endereco;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
    public Boolean getOfertado() {
        return ofertado;
    }
    public void setOfertado(Boolean ofertado) {
        this.ofertado = ofertado;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}

