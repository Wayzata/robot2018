//@author Cameron McCoy

package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {
	//intake of the cube for floor pickup
	public void doubleIntake(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, -Variables.intakeSpeed);
		right.set(ControlMode.PercentOutput, Variables.intakeSpeed);
	}
	
	public void stop(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}
	public void doubleOutput(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, Variables.intakeSpeed);
		right.set(ControlMode.PercentOutput, -Variables.intakeSpeed);
	}
	public void turn(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, -Variables.intakeSpeed);
		right.set(ControlMode.PercentOutput, -Variables.intakeSpeed);	}
  
}
