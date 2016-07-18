package biz.paluch.rest.test;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;


public abstract class PessoaComJsonIgnoreMixinNomeAsNomePessoa {
    public PessoaComJsonIgnoreMixinNomeAsNomePessoa(
					    		@JsonProperty("id")int id, 
					    		@JsonProperty("nomePessoa") String nome, 
					    		@JsonProperty("result") int result) {}
    @JsonIgnore
    abstract int getId();

    @JsonProperty("nomePessoa")
    abstract String getNome();
    
    @JsonIgnore
    abstract int getResult();
    
}
