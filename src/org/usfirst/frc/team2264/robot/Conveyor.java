//@author Cameron McCoy

package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Conveyor {
	
	public void startConveyor(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, Variables.conveyorSpeed);
		right.set(ControlMode.PercentOutput, Variables.conveyorSpeed);
	}
	
	public void stopConveyor(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}
	
	public void reverseConveyor(TalonSRX left, TalonSRX right){
		left.set(ControlMode.PercentOutput, Variables.conveyorSpeed * -1);
		right.set(ControlMode.PercentOutput, Variables.conveyorSpeed * -1);
		
	}

}
