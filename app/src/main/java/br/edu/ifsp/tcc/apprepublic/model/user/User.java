package br.edu.ifsp.tcc.apprepublic.model.user;

//import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Objects;

/*import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

//@Entity
//@Table(name = "user")
public class User {

  //  @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("id")
    @Expose
    private Long id;
    //@NotNull
    //@Size(min = 3, max = 50)
    @SerializedName("name")
    @Expose
    private String name;
    //@NotNull
    //@Email
    @SerializedName("email")
    @Expose
    private String email;
    //@NotNull
    //@Size(min = 6, max = 8)
    @SerializedName("password")
    @Expose
    private String password;
    //@NotNull
    //@Digits(integer=9, fraction = 0)
    @SerializedName("telefone")
    @Expose
    private String telefone;
    //@NotNull
    //@Column(name = "nascimento")
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @SerializedName("nascimento")
    @Expose
    private LocalDate dataNascimento;
    //@Enumerated(EnumType.STRING)
    //@NotNull
    @SerializedName("gender")
    @Expose
    private Gender gender;
   // @NotNull
    //@Size(min = 3, max = 10)
    @SerializedName("login")
    @Expose
    private String login;
    //@NotNull
    //@CPF
    @SerializedName("Cpf")
    @Expose
    private String Cpf;
    //@NotNull
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
        this.telefone = telefone;
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
        Cpf = cpf;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(id, other.id);
    }

}