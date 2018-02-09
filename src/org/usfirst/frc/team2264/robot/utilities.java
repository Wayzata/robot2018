package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class utilities {

	public static double angleDifference(double currentAngle, double goalAngle){
		
		double rawDiff= (goalAngle-currentAngle)%360;
		System.out.println(rawDiff);
		SmartDashboard.putNumber("Gyro- Raw difference", rawDiff+360);
		if ((rawDiff)<=-180){
			return rawDiff+360;
		}
		else if(rawDiff>=180){
			SmartDashboard.putNumber("Gyro- Raw different", rawDiff-360);
			return rawDiff-360;
		}
		else{
			SmartDashboard.putNumber("Gyro- Raw different", rawDiff);
			return (rawDiff);
		}
		
	}
}
