package biz.paluch.rest.test;


//essa classe eh um POJO normal sem anotações JAXB, como por exemplo: @XmlRootElement, @XmlElement
public class Pessoa {
	private int id;
	private String nome;
    private int result;

    public Pessoa() {
        super();
    }

    public Pessoa(int id, String nome, int result) {
        super();
        this.id=id;
        this.nome=nome;
        this.result = result;
    }
   
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
    public String toString() {
    	return "myModel[id/nome/result:" + id + "/" + nome + "/" + result + "]";
    }
}