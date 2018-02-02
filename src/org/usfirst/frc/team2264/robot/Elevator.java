package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Elevator {
//raises the shooter to 2 levels
	public void upElevator(TalonSRX liftMotor){
		liftMotor.set(ControlMode.PercentOutput, Variables.elevateSpeed);
	}
	
	public void downElevator(TalonSRX liftMotor){
		liftMotor.set(ControlMode.PercentOutput, -Variables.elevateSpeed);
		
	}
	}
