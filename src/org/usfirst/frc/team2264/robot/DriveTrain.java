package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;

//@Authors
/* Drake, Lexi, Rafael, Cameron, Nathan, Julian, Preeti */

public class DriveTrain {
	
	// Drive-train class that will contain methods reading the joysticks and setting the motors
	
	public static void MotorSet(Joystick leftJ, Joystick rightJ, 
								TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR) {
			
		// Method to set the motors to the joystick readings
		
		frontL.set(ControlMode.PercentOutput, -0.4 * sensitivityAdjustment(getLeft(leftJ)));
		backL.set(ControlMode.PercentOutput, -0.4 * sensitivityAdjustment(getLeft(leftJ)));
		
		frontR.set(ControlMode.PercentOutput, 0.4 * sensitivityAdjustment(getRight(rightJ)));
		backR.set(ControlMode.PercentOutput, 0.4 * sensitivityAdjustment(getLeft(rightJ)));
		
	}
	
	public static double getLeft(Joystick leftJ) {
	
		// Method to get the left joystick reading
		
		SmartDashboard.putNumber("LeftJoystick", leftJ.getY(GenericHID.Hand.kLeft));
		return leftJ.getY(GenericHID.Hand.kLeft);
		
	}
	
	public static double getRight(Joystick rightJ) {
		
		// Method to get the right joystick reading
		
		SmartDashboard.putNumber("RightJoystick", rightJ.getY(GenericHID.Hand.kRight));
		return rightJ.getY(GenericHID.Hand.kRight);
	
	}
	
	public static double sensitivityAdjustment(double position) {
		
		// Method to adjust the motor speeds
		
		double adjustment = Math.pow(position, 2);
	
		if (position >= 0){
		 
			return adjustment;
		}
		else {
		
			return -1 * adjustment;
		}
		
	
	}
	
}
