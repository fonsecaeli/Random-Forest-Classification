import java.util.ArrayList;
public class DataTable{
	private ArrayList <Record> data;
	private ArrayList <Attribute> attributes;
	
	public DataTable(){}
	
	public DataTable(Arraylist <Record> data, ArrayList <Attribute> attributes){
		this.data = data;
		this.attributes = attributes;
	}
	
	public ArrayList <Record> getData(){
		return data;
	}
	
	public ArrayList <Attribute> getAttributes(){
		return attributes;
	}
	
	public String toString(){
		String toReturn = "";
		for (Attribute a: attributes){
			toReturn+=a.getName()+": ";
			toReturn+=a.getValues().toString()+"\n";
		}
		for (Record a: data)
			for (int i=0; i<a.size(); i++)
				toReturn+=a.getData().get(attributes.get(i))+"\n";
		return toReturn;
	}
}
