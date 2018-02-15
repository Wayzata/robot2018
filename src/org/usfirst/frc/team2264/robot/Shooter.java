//@author Cameron McCoy

package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter {
	//wheels that shoot the cube out
	double currentSpeed;
	public void startShooter(TalonSRX left, TalonSRX right, double speed) {
		if(speed!=0){
			if(currentSpeed==0){
		left.set(ControlMode.PercentOutput, speed * -1);
		right.set(ControlMode.PercentOutput, speed);
		currentSpeed=speed;
		System.out.println(currentSpeed);
			}
		}
		else {
			if(currentSpeed!=0){
			left.set(ControlMode.PercentOutput, speed);
			right.set(ControlMode.PercentOutput, speed);
			currentSpeed=speed;
			System.out.println(currentSpeed);
			}
		}
	}
	
/*	public void stopShooter(TalonSRX left, TalonSRX right) {
		
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}
	*/
}
