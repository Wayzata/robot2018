//@author Cameron McCoy

package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {
	
	public void startIntake(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, Variables.intakeSpeed);
		right.set(ControlMode.PercentOutput, Variables.intakeSpeed);
	}
	
	public void stopIntake(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}
  
}
