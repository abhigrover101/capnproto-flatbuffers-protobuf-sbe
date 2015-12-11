struct car{
	serialNumber @0 :UInt32;
	modelYear @1 :UInt16;
	available @2 :Bool;
	code @3:Text;
	someNumbers @4:List(Int32);
	vehicleCode @5:Text;
	extras @6:List(Extras);
	enum Extras{
		sunRoof @1;
		sportsPack @2;
		cruiseControl @3;
	}
	engine @7:Engine;
	fuelfigure @8:FuelFigures;
	performance @9:PerformanceFigures;
	make @10:Text;
	model @11:Text;
}

struct Engine{
	capacity @0:UInt16;
	numCylinders @1:Uint8;
	maxRpm @2:UInt16=9000;
	manufacturerCode @3:Text;
	fuel @4:Text;
}

struct FuelFigures{
	speed @0:UInt16;
	mpg @1:Float32;
}

struct PerformanceFigures{
	octaneRating @0:UInt16;
	acceleration @1:List(Acceleration);
	
	struct Acceleration{
		mph @0: UInt16;
		seconds @1:Float32;
	}
}

