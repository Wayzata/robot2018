package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class utilities {

	public static double angleDifference(double currentAngle, double goalAngle){
		
		double rawDiff= (goalAngle-currentAngle)%360;
		if ((rawDiff)<=180){
			SmartDashboard.putNumber("Gyro-rawDifference", rawDiff);

			return rawDiff;
		}
		else{
			SmartDashboard.putNumber("Gyro-rawDifference", 360-rawDiff);

			return (360-rawDiff);
		}
		
	}
}
