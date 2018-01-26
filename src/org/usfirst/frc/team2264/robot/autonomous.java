/**
* @author RafaelDubeau
* @author DrakeJohnson
* @author Preeti Pidatala
* */
package org.usfirst.frc.team2264.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class autonomous {
	char switchPlacement;
	double motorPer=.2;
	int driveTime=4000;
	final static long sameSideStop1 = 2000, sameSideStop2 = 6000;//determines for how long the robot will drive forward during the leftLeft and rightRight autos
	final static long oppSideStop1 = 2000, oppSideStop2 = 6000, oppSideStop3 = 10000;//determines for how long the robot will drive forward during the leftRight and rightLeft autos
	
	//returns true if the the robot is on the same side as its switch and false if they are on the opposite side or in the middle
	public boolean getSwitch(String data, int side) {
		switchPlacement = data.charAt(0);
		if ((side==0) && (switchPlacement=='L')) {
			return true;
		}
		else if ((side==1) && (switchPlacement=='R')) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	//causes the robot to move forward in a straight line
	public void driveForward(TalonSRX left, TalonSRX right) {
		left.set(ControlMode.PercentOutput, 1 * motorPer);
		right.set(ControlMode.PercentOutput, -.943 * motorPer);
	}
		
	//Causes the the robot to turn at a fixed rate
	//if angle = 0, the robot will turn right
	//if angle = 1, the robot will turn left
	public void turn(TalonSRX left, TalonSRX right,int angle) {
		switch(angle) {
		 
		case 0:
			//turn right
			left.set(ControlMode.PercentOutput, 1 * motorPer);
			right.set(ControlMode.PercentOutput, .943 * motorPer);
			break;
		case 1:
			//turn left
			left.set(ControlMode.PercentOutput, -motorPer);
			right.set(ControlMode.PercentOutput, .943 * motorPer);
			break;
		}
	}
	
	//if driver station is on the left and our switch is on the left
	public void leftLeft(TalonSRX left, TalonSRX right, long time, ADXRS450_Gyro gyro){
		if(time < sameSideStop1) {
			driveForward(left, right);
		}
		else if (time >= sameSideStop1 && gyro.getAngle() < 90) {
			turn(left, right, 0);
		}
		else if (gyro.getAngle() >= 90 && time < sameSideStop2) {
			driveForward(left, right);
		}
		else {
			left.set(ControlMode.PercentOutput, 0);
			right.set(ControlMode.PercentOutput, 0);
			//drop the cube
		}
	} 
	
	//if driver station is on the left and our switch is on the right
	public void leftRight(TalonSRX left, TalonSRX right, long time, ADXRS450_Gyro gyro){
		if(time < oppSideStop1) {
			driveForward(left, right);
		}
		else if (time >= oppSideStop1 && gyro.getAngle() < 90) {
			turn(left, right, 0);
		}
		else if (gyro.getAngle() >= 90 && time < oppSideStop2) {
				driveForward(left, right);
		}
		else if (time >= oppSideStop2 && gyro.getAngle() < 180) {
			turn(left, right, 0);
		}
		else if (gyro.getAngle() >= 180 && time < oppSideStop3) {
			driveForward(left, right);
		}
		else {
			left.set(ControlMode.PercentOutput, 0);
			right.set(ControlMode.PercentOutput, 0);
			//drop the cube
		}
	}
	
	//if driver station is on the right and our switch is on the right
	public void rightRight(TalonSRX left, TalonSRX right, long time, ADXRS450_Gyro gyro){
		if(time < sameSideStop1) {
			driveForward(left, right);
		}
		else if (time >= sameSideStop1 && gyro.getAngle() > 270) {
			turn(left, right, 1);
		}
		else if (gyro.getAngle() <= 270 && time < sameSideStop2) {
			driveForward(left, right);
		}
		else {
			left.set(ControlMode.PercentOutput, 0);
			right.set(ControlMode.PercentOutput, 0);
			//drop the cube
		}
	}
	
	//if driver station is on the right and our switch is on the left
	public void rightLeft(TalonSRX left, TalonSRX right, long time, ADXRS450_Gyro gyro){
		if(time < oppSideStop1) {
			driveForward(left, right);
		}
		else if (time >= oppSideStop1 && gyro.getAngle() > 270) {
			turn(left, right, 1);
		}
		else if (gyro.getAngle() <= 270 && time < oppSideStop2) {
			driveForward(left, right);
		}
		else if (time >= oppSideStop2 && gyro.getAngle() > 180) {
			turn(left, right, 1);
		}
		else if (gyro.getAngle() <= 180 && time < oppSideStop3) {
			driveForward(left, right);
		}
		else {
			left.set(ControlMode.PercentOutput, 0);
			right.set(ControlMode.PercentOutput, 0);
			//drop the cube			
		}
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
		