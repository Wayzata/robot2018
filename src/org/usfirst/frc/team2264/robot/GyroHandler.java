// @author Cameron McCoy

package org.usfirst.frc.team2264.robot;
import  edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroHandler {

	boolean checkIfGyroIsPluggedIn(ADXRS450_Gyro Gyro) {
		
		double gyroInitial = Gyro.getAngle();
		double gyroTrack = 0;
		for(int i = 0; i < 5; i++) {
			gyroTrack = gyroTrack + Gyro.getAngle();
		}
		if(gyroTrack == (5 * gyroInitial)) {
			SmartDashboard.putString("Gyro Is", " NOT PLUGGED IN");
			return false;
		}
		else {
			SmartDashboard.putString("Gyro is", " plugged in");
			return true;
		}
		
		/* try {
		double gyroInitial = Gyro.getAngle();
		double gyroTrack = 0;
		for(int i = 0; i < 5; i++) {
			gyroTrack = gyroTrack + Gyro.getAngle();
		}
		if(gyroTrack == (5 * gyroInitial)) {
			throw new GyroNotPluggedInException("The Gyro Sensor is not plugged into the robot.");
		}
		else {
			return Gyro.getAngle();
		}
		}catch(GyroNotPluggedInException e) {
			e.printStackTrace();
		} */
	} 
}
