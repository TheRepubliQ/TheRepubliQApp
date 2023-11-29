package br.edu.ifsp.tcc.apprepublic.model.home;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//import jakarta.persistence.Embeddable;
//import jakarta.validation.constraints.NotNull;

//@Embeddable
public class Address {

    //@NotNull
    @SerializedName("cep")
    @Expose
    private String cep;
   // @NotNull
    @SerializedName("pais")
    @Expose
    private String pais;
 //   @NotNull
    @SerializedName("estado")
    @Expose
    private String estado;
 //   @NotNull
    @SerializedName("numero")
    @Expose
    private String numero;
  //  @NotNull
    @SerializedName("rua")
    @Expose
    private String rua;
   // @NotNull
    @SerializedName("bairro")
    @Expose
    private String bairro;
   // @NotNull
    @SerializedName("cidade")
    @Expose
    private String cidade;
    @SerializedName("complemento")
    @Expose
    private String complemento;


    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return "{" +
                "\"pais\": \"" + pais + "\"," +
                "\"estado\": \"" + estado + "\"," +
                "\"numero\": \"" + numero + "\"," +
                "\"rua\": \"" + rua + "\"," +
                "\"bairro\": \"" + bairro + "\"," +
                "\"cidade\": \"" + cidade + "\"," +
                "\"cep\": \"" + cep + "\"," +
                "\"complemento\": \"" + complemento + "\"" +
                "}";
    }

    public String Forma() {
        return "Endereço{" +
                "cep='" + cep + '\'' +
                ", pais='" + pais + '\'' +
                ", estado='" + estado + '\'' +
                ", numero='" + numero + '\'' +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }

}
