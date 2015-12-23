
//__________________________________________________________________________
//Package class
//-------------
//Contians variables, and constructors for a comparable Package object
//-------------
public class Package implements Comparable
{
	protected int weight;
	protected int trackingNumber;
	protected String status;
	protected int loaded;
	protected int valid;
	protected int type;
	protected int height;
	protected int width;
	protected int length;
	
	//Package Constructor
	public Package()
	{
		this.weight = 0;
		this.height = 0;
		this.width = 0;
		this.length = 0;
		this.trackingNumber = 0;
		this.loaded = 0;
		this.valid = 1;
		this.type = 0;
		this.status = null;
	}
	
	//Simple compareTo
	public int compareTo(Package that)
	{
		return this.weight-that.weight;
	}
	
	//Dumby compareTo method(Satisfies comparable interface)
	public int compareTo(Object N)
	{
		return -50;
	}
	
}
//End Package class
//____________________________________________________________________