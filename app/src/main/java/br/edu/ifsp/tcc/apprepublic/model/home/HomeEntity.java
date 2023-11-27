    package br.edu.ifsp.tcc.apprepublic.model.home;

    import androidx.annotation.NonNull;

    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

    import br.edu.ifsp.tcc.apprepublic.model.user.User;

    public class HomeEntity {

      //  @Id
       // @GeneratedValue(strategy = GenerationType.IDENTITY)
        @SerializedName("id")
        @Expose
        private Long id;
        //@NotNull
        //@Size(min = 3, max = 200)
        @SerializedName("titulo")
        @Expose
        private String titulo;

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
        private Address endereco;

        @SerializedName("user")
        @Expose
        private User user;  // Mude para o tipo correto que representa um usuário no Android


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
        public Address getEndereco() {
            return endereco;
        }
        public void setEndereco(Address endereco) {
            this.endereco = endereco;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }


        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
        @NonNull
        @Override
        public String toString() {
            return  "id:" + getId() +
                    "\ntitulo:'" + getTitulo() +
                    "\ndescr:'" + getDescr()+
                    "\ntipo:" + getTitulo() +
                    "\npreco:" + getPreco() +
                    "\nofertado:" + getOfertado() +
                    "\nendereco:" + getEndereco() +
                    "\nuser:" + getUser();
        }


    }

