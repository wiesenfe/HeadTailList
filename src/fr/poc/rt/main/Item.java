package fr.poc.rt.main;

public class Item  implements Comparable<Item>{

	private final String groupRef;
	private final String ItemRef;
	private final Double value;
	
	public Item(String groupRef, String ItemRef, String value){
		this.groupRef = groupRef;
		this.ItemRef = ItemRef;
		this.value = Double.parseDouble(value);		
	}	
	
	public String getReference(){
		return groupRef + ":" + ItemRef;
	}
	
	

	public String getGroupRef() {
		return groupRef;
	}

	public String getItemRef() {
		return ItemRef;
	}

	public Double getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ItemRef == null) ? 0 : ItemRef.hashCode());
		result = prime * result
				+ ((groupRef == null) ? 0 : groupRef.hashCode());
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
		Item other = (Item) obj;
		if (ItemRef == null) {
			if (other.ItemRef != null)
				return false;
		} else if (!ItemRef.equals(other.ItemRef))
			return false;
		if (groupRef == null) {
			if (other.groupRef != null)
				return false;
		} else if (!groupRef.equals(other.groupRef))
			return false;
		return true;
	}

	@Override
	public int compareTo(Item o) {
		return this.getValue().compareTo(o.getValue());
	}

	@Override
	public String toString() {
		return getReference()  + ":" + getValue();
	}			
	
}
