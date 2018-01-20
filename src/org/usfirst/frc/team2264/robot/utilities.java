package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class utilities {

	public static double angleDifference(double currentAngle, double goalAngle){
		
		double rawDiff= (goalAngle-currentAngle)%360;
		SmartDashboard.putNumber("Gyro- Raw difference", rawDiff);
		if ((rawDiff)<=180){
			return rawDiff;
		}
		else{
			SmartDashboard.putNumber("Gyro- Raw different", 360-rawDiff);
			return (360-rawDiff);
		}
		
	}
}
