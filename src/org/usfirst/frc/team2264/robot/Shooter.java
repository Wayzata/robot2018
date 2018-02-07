//@author Cameron McCoy

package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter {
	//wheels that shoot the cube out
	
	public static final double SWITCH_SPEED = .3;
	public static final double SCALE_SPEED = 1.0;
	
	public void startShooter(TalonSRX left, TalonSRX right, double speed) {
		left.set(ControlMode.PercentOutput, speed);
		right.set(ControlMode.PercentOutput, speed);
	}
	
	public void stopShooter(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}

}
