//@author Cameron McCoy

package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {
	//intake of the rectangular prism for floor pickup
	
	//Pulls the box inwards
	public void doubleIntake(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, Variables.intakeSpeed);
		right.set(ControlMode.PercentOutput, Variables.intakeSpeed);
	}
	
	//Stops the intake motors
	public void stop(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}
	
	//Pushes the box outwards
	public void doubleOutput(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, -Variables.intakeSpeed);
		right.set(ControlMode.PercentOutput, -Variables.intakeSpeed);
	}
	
	//Turns the box
	public void turn(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, Variables.intakeSpeed);
		right.set(ControlMode.PercentOutput, -Variables.intakeSpeed);	}
  
}
