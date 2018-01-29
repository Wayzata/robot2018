//@author Cameron McCoy

package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter {
	
	public void startShooter(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, Variables.shooterSpeed);
		right.set(ControlMode.PercentOutput, Variables.shooterSpeed);
	}
	
	public void stopShooter(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}

}
