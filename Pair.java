package zomato;

public class Pair<E, V> {
	
	private E e;
	private V v;
	
	Pair()
	{
		this.e = null;
		this.v = null;
	}
	
	Pair(E e, V v)
	{
		this.e = e;
		this.v = v;
	}
	
	public E getFirst()
	{
		return e;
	}
	
	public V getSecond()
	{
		return v;
	}
	
	public void setFirst(E e)
	{
		this.e = e;
	}
	
	public void setSecond(V v)
	{
		this.v = v;
	}

}
