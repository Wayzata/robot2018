// @author Cameron

package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickAdjustment {

	static double sensitivityAdjustment(double position) {
		double adjustment = Math.pow(position, 2);
		
		if(position >= 0) {
			return adjustment;
		}
		else {
			return -1 * adjustment;
		}
	}
	
	static double getLeft(Joystick leftJoystick, Joystick rightJoystick) {
		
		if(rightJoystick.getY(GenericHID.Hand.kRight) - leftJoystick.getY(GenericHID.Hand.kLeft) <= 0.10) {
			
			SmartDashboard.putNumber("Left Joystick: ", rightJoystick.getY(GenericHID.Hand.kRight));
			return rightJoystick.getY(GenericHID.Hand.kRight);
		}
		else {
			SmartDashboard.putNumber("Left Joystick: ", leftJoystick.getY(GenericHID.Hand.kLeft));
			return leftJoystick.getY(GenericHID.Hand.kLeft);
		}
	}
	
	static double getRight(Joystick rightJoystick) {
		SmartDashboard.putNumber("Right Joystick: ", rightJoystick.getY(GenericHID.Hand.kRight));
		return rightJoystick.getY(GenericHID.Hand.kRight);
	}
}