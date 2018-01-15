/**
* @author RafaelDubeau
* @author DrakeJohnson
* @author Preeti Pidatala
* */
package org.usfirst.frc.team2264.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;

public class autonomous {
	char switchPlacement;
	double motorPer=.6;
	int driveTime=4000;
	
	public boolean getSwitch(String data, int side){
		switchPlacement=data.charAt(0);
		if ((side==0)&&(switchPlacement=='L')){
			return true;
		}
		else if ((side==1)&&(switchPlacement=='R')){
			return true;
		}
		else{
			return false;
		}
	}
		public void driveForward(TalonSRX left, TalonSRX right){
			
			left.set(ControlMode.PercentOutput, motorPer);
			right.set(ControlMode.PercentOutput,motorPer);
		}
		
		public void turn(TalonSRX left, TalonSRX right,int angle){
		switch(angle){
		case 0:
			left.set(ControlMode.PercentOutput, motorPer);
			right.set(ControlMode.PercentOutput,-1*motorPer);

			//turn right
			break;
		case 1:
		}
		}
		
		//side = driver station placement, 0=left side, 1=right side
		public void leftLeft(TalonSRX left, TalonSRX right){
		
				//if distance travelled<10{
				driveForward(left,right);
				//else{
				turn(left, right, 20);
				
				//drop cube
			}
		public void leftRight(TalonSRX left, TalonSRX right){
			//if driver station is on the left and our switch is on the right
		}
		public void rightRight(TalonSRX left, TalonSRX right){
			//if driver station is on the right and our switch is on the right
		}
		public void rightLeft(TalonSRX left, TalonSRX right){
			//if driver station is on the right and our switch is on the left
		}
			
		public void noAuto(TalonSRX left, TalonSRX right){
				left.set(ControlMode.PercentOutput,0);
				right.set(ControlMode.PercentOutput,0);
			}
			public void crossLineAuto(TalonSRX left,TalonSRX right, long time){
				if(time<=driveTime){
				left.set(ControlMode.PercentOutput,0);
				right.set(ControlMode.PercentOutput,0);
			}
			}

}
		
