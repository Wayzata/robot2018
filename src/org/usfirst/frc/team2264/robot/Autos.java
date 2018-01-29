package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

//@Authors
/* Drake, Lexi, Rafael, Cameron, Nathan, Julian, Preeti */

public class Autos {
	
	// Auto class that will contain the methods for each auto
	
	final static long SAME_SIDE_STOP_1 = 2000;
	final static long SAME_SIDE_STOP_2 = 6000;
	
	final static long OPP_SIDE_STOP_1 = 2000;
	final static long OPP_SIDE_STOP_2 = 6000;
	final static long OPP_SIDE_STOP_3 = 10000;
	
	double motorPer = .4;
	double turnPer = .2;
	
	
// Starting Left
/* --------------------------- */
	
	public void leftLeft(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time) {
		
		if(time < SAME_SIDE_STOP_1) {
			driveForward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else if(time >= SAME_SIDE_STOP_1 && gyro.getAngle() < 75) {
			turn(frontL, frontR, backL, backR, 0);
		}
		
		else if(gyro.getAngle() >= 75 && time < SAME_SIDE_STOP_2) {
			driveForward(frontL, frontR, backL, backR, gyro, 90);
		}
		else {
			stop(frontL, frontR, backL, backR);
		}
		
	}
	
	public void leftRight(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time) {
		
		if(time < OPP_SIDE_STOP_1) {
			driveForward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else if(time >= OPP_SIDE_STOP_1 && gyro.getAngle() < 75) {
			turn(frontL, frontR, backL, backR, 0);
		}
		
		else if(gyro.getAngle() >= 75 && time < OPP_SIDE_STOP_2) {
			driveForward(frontL, frontR, backL, backR, gyro, 90);
		}
		
		else if(time >= OPP_SIDE_STOP_2 && gyro.getAngle() < 165) {
			turn(frontL, frontR, backL, backR, 0);
		}
		
		else if(gyro.getAngle() >= 165 && time < OPP_SIDE_STOP_3) {
			driveForward(frontL, frontR, backL, backR, gyro, 180);
		}
		
		else {
			stop(frontL, frontR, backL, backR);
		}
	}
	
// Starting Right
/* --------------------------- */
	
	public void rightRight(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time) {
		
		if(time < SAME_SIDE_STOP_1) {
			driveForward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else if(time >= SAME_SIDE_STOP_1 && gyro.getAngle() < -75) {
			turn(frontL, frontR, backL, backR, 1);
		}
		
		else if(gyro.getAngle() >= -75 && time < SAME_SIDE_STOP_2) {
			driveForward(frontL, frontR, backL, backR, gyro, -90);
		}
		else {
			stop(frontL, frontR, backL, backR);
		}
	
	}
	
	public void rightLeft(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time) {
		
		if(time < OPP_SIDE_STOP_1) {
			driveForward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else if(time >= OPP_SIDE_STOP_1 && gyro.getAngle() < -75) {
			turn(frontL, frontR, backL, backR, 1);
		}
		
		else if(gyro.getAngle() >= -75 && time < OPP_SIDE_STOP_2) {
			driveForward(frontL, frontR, backL, backR, gyro, -90);
		}
		
		else if(time >= OPP_SIDE_STOP_2 && gyro.getAngle() < -165) {
			turn(frontL, frontR, backL, backR, 1);
		}
		
		else if(gyro.getAngle() >= -165 && time < OPP_SIDE_STOP_3) {
			driveForward(frontL, frontR, backL, backR, gyro, -180);
		}
		
		else {
			stop(frontL, frontR, backL, backR);
		}
		
	}
	
// Center	
/* --------------------------- */
	
	public void center(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time) {
		
		
	}
	
// Forward/Turn
/* --------------------------- */
	
	public void driveForward(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR) {
		
		// Method to drive forward without using bearings
		
		frontL.set(ControlMode.PercentOutput, -1 * motorPer);
		backL.set(ControlMode.PercentOutput, -1 * motorPer);
		
		frontR.set(ControlMode.PercentOutput, 1 * motorPer);
		backR.set(ControlMode.PercentOutput, 1 * motorPer);
		
	}
	
	public void driveForward(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, int bearing) {
		
		// Method to drive forward using bearings
		
		if(Math.abs(gyro.getAngle() - bearing) < 2) {
			driveForward(frontL, frontR, backL, backR);
		}
		
		else if(gyro.getAngle() < bearing) {
			frontL.set(ControlMode.PercentOutput, -1 * motorPer);
			backL.set(ControlMode.PercentOutput, -1 * motorPer);
			
			frontR.set(ControlMode.PercentOutput, .9 * motorPer);
			backR.set(ControlMode.PercentOutput, .9 * motorPer);
		}
		
		else if (gyro.getAngle() > bearing) {
			frontL.set(ControlMode.PercentOutput, -.9 * motorPer);
			backL.set(ControlMode.PercentOutput, -.9 * motorPer);
			
			frontR.set(ControlMode.PercentOutput, 1 * motorPer);
			backR.set(ControlMode.PercentOutput, 1 * motorPer);
		}
	}
	
	public void turn(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, int angle) {
		
		// Method to turn using angles
		
		switch(angle) {
		
		// Turns Right
		case 0:
		
			frontL.set(ControlMode.PercentOutput, -1 * turnPer);
			backL.set(ControlMode.PercentOutput, -1 * turnPer);
			
			frontR.set(ControlMode.PercentOutput, -1 * turnPer);
			backR.set(ControlMode.PercentOutput, -1 * turnPer);
		break;
		
		// Turn Left
		case 1:
			
			frontL.set(ControlMode.PercentOutput, 1 * turnPer);
			backL.set(ControlMode.PercentOutput, 1 * turnPer);
			
			frontR.set(ControlMode.PercentOutput, 1 * turnPer);
			backR.set(ControlMode.PercentOutput, 1 * turnPer);
		break;
		
		default:
			stop(frontL, frontR, backL, backR);
		}
		
	}
	
	public void stop(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR) {
		
		// Method to stop the robot in autonomous
		
		frontL.set(ControlMode.PercentOutput, 0);
		backL.set(ControlMode.PercentOutput, 0);
		
		frontR.set(ControlMode.PercentOutput, 0);
		backR.set(ControlMode.PercentOutput, 0);
	}
	
// Switch
/* -------------------------- */

	public boolean getSwitch(String data, int side) {
		
		// Getting the side of the switches on the field
		
		char switchPlacement = data.charAt(0);
		
		if(side == 0 && switchPlacement == 'L'){
			return true;
		}
		
		else if (side == 1 && switchPlacement == 'R') {
			return true;
		}
		
		return false;
	}
	public void crossLineAuto(){
		
	}

}
