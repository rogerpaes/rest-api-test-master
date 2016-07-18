package biz.paluch.rest.test;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public abstract class PessoaComJsonIgnoreMixinOverrideResult extends PessoaComJsonIgnore {
	
	@Override
	@JsonIgnore
	public abstract int getId();
	
	@Override
	@JsonProperty("nomeIndividuo")
	public abstract String getNome();
	
	@Override
	@JsonProperty
	public abstract int getResult();

}
