//@author Cameron McCoy

package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter {
	//wheels that shoot the cube out
	
	// Starts the shooter motors
	public void startShooter(TalonSRX left, TalonSRX right, double speed) {
		left.set(ControlMode.PercentOutput, speed);
		right.set(ControlMode.PercentOutput, speed);
	}
	
	// Stops the shooter motors
	public void stopShooter(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}

}
