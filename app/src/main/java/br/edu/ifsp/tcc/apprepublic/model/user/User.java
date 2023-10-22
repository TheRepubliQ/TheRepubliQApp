package br.edu.ifsp.tcc.apprepublic.model.user;

//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.utils.Mascara;


public class User {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("telefone")
    @Expose
    private String telefone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @SerializedName("nascimento")
    @Expose
    private LocalDate dataNascimento;

    @SerializedName("gender")
    @Expose
    private Gender gender;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("Cpf")
    @Expose
    private String Cpf;
    @SerializedName("prop")
    @Expose
    private Boolean prop;

    public Boolean getIsProp() {
        return prop;
    }
    public void setIsProp(Boolean prop) {
        this.prop = prop;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        // Aplicar a máscara para o campo telefone
        this.telefone = Mascara.mask(Mascara.MASCARA_TELEFONE, telefone);
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getCpf() {
        return Cpf;
    }
    public void setCpf(String cpf) {
        // Aplicar a máscara para o campo Cpf
        this.Cpf = Mascara.mask(Mascara.MASCARA_CPF, cpf);
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "id:" + getId() +
                "\nnome:" + getName()+
                "\nemail:" + getEmail()+
                "\npassword" + getPassword()+
                "\ntelefone:" + getTelefone()+
                "\ndataNascimento:"+ getDataNascimento()+
                "\ngender" + getGender()+
                "\nlogin:"+ getLogin()+
                "\nCpf:" + getCpf()+
                "\nprop" + getIsProp();
    }
}