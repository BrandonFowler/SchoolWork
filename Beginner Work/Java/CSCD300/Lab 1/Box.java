

//____________________________________________________________________________________________________
//Box class
//---------
//Extends from Package, calls super constructor, and constructs a Box Object
//---------
public class Box extends Package
{
	//Box Constructor
	public Box(int sWeight,int sHeight,int sLength,int sWidth,String sTrackingNumber,int type )
	{
		super();
		this.weight = sWeight;
		this.height = sHeight;
		this.length = sLength;
		this.width = sWidth;
		this.trackingNumber = Integer.parseInt(sTrackingNumber);
		this.type = type;
	}
}
//End Box class
//_____________________________________________________________________________________________________