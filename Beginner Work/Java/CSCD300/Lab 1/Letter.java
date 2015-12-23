
//____________________________________________________________________________________________________
//Letter class
//---------
//Extends from Package, calls super constructor, and constructs a Box Object
//---------
public class Letter extends Package
{
	//Letter Constructor
	public Letter(int sWeight,int sLength,int sWidth,String sTrackingNumber,int type )
	{
		super();
		this.weight = sWeight;
		this.length = sLength;
		this.width = sWidth;
		this.trackingNumber = Integer.parseInt(sTrackingNumber);
		this.type = type;
	}
}
//End Letter class
//_____________________________________________________________________________________________________