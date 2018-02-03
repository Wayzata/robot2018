package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Elevator {
//raises the shooter to 2 levels
	public void upElevator(DoubleSolenoid solenoid){
		solenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void downElevator(DoubleSolenoid solenoid){
		solenoid.set(DoubleSolenoid.Value.kReverse);	
	}
	public void stopElevtor(DoubleSolenoid solenoid) {
		solenoid.set(DoubleSolenoid.Value.kOff);
	}
	}
