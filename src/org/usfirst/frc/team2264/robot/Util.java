package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//@Authors
/* Drake, Lexi, Rafael, Cameron, Nathan, Julian, Preeti */

public class Util {
	
	// Util class that will contain the sensors or other smaller methods
	
	static boolean gyroCheck(ADXRS450_Gyro Gyro) {
		
		// Debugger for gyro
		
		double gyroInitial = Gyro.getAngle();
		double gyroTrack = 1;
		for(int i = 0; i < 5; i++) {
			
			gyroTrack = gyroTrack + Gyro.getAngle();
			
		}
		
		if(gyroTrack == (5 * gyroInitial)){
			
			//SmartDashboard.putString("Gyro is:", "NOT plugged in");
			return false;
			
		}
		else {
			
			//SmartDashboard.putString("Gyro is:", "plugged in");
			return true;
			
		}
		
	}
	
	public static double encoderReading() {
		
		// Method to get the readings off the encoder
		
		return 0;
	}

}
