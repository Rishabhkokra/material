package com.examples.graph;

public abstract class VehicleTest implements Vehicle, Parking {
	
	
	public static void main(String args[]) {
		
		System.out.println(Vehicle.getHorsePower(10000000 ,300000000));
	}

	public abstract void perform() ;
	

}
