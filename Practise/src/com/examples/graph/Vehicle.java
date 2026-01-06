package com.examples.graph;
public interface Vehicle {
    
    // regular / default interface methods
	
	default String getCarPark() {
		return "TESLA";
	}
    
    static int getHorsePower(int rpm, int torque) {
        return (rpm * torque) / 5252;
    }
    
    void perform();
}