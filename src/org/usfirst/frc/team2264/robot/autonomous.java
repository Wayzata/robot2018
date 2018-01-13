/**
* @author RafaelDubeau
* @author DrakeJohnson
* @author Preeti Pidatala
* */
package org.usfirst.frc.team2264.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;

public class autonomous {
	double motorSpeed= .6;
	String gameData= DriverStation.getInstance().getGameSpecificMessage();
		public void center(CANTalon left, CANTalon right){
			
		}
		public void driveForward(CANTalon left, CANTalon right){
			
			left.set(motorSpeed);
			right.set(motorSpeed);
		}
		public void turn(CANTalon left, CANTalon right,int angle){
		switch(angle){
		case 0:
			left.set(motorSpeed);
			right.set(-1*motorSpeed);

			//turn right
			break;
		case 1:
		}
		}
		
		//side = driver station placement, 0=left side, 1=right side
		public void sidechoice(CANTalon left, CANTalon right, int side){
		
			if ((side==0)&&(gameData.charAt(0) == 'L')){
				//if distance travelled<10{
				driveForward(left,right);
				//else{
				turn(left, right, side);
				
				//drop cube
			}

}
