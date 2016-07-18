package biz.paluch.rest.test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyModel {
	@XmlElement
    private int result;

    public MyModel() {
        super();
    }

    public MyModel(int result) {
        super();
        this.result = result;
    }

    public int getResult() {
        return result;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.result;
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
		MyModel other = (MyModel) obj;
		if (result != other.result)
			return false;
		return true;
	}
    
    @Override
    public String toString() {
    	return "myModel[" + result + "]";
    }
}