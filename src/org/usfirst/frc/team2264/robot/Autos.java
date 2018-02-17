package org.usfirst.frc.team2264.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

//@Authors
/* Drake, Lexi, Rafael, Cameron, Nathan, Julian, Preeti */

public class Autos {
	
	// Auto class that will contain the methods for each auto
	
	final static long SAME_SIDE_STOP_1 = 3100;
	final static long SAME_SIDE_STOP_2 = 9000;
	
	final static long OPP_SIDE_STOP_1 = 2000;
	final static long OPP_SIDE_STOP_2 = 6000;
	final static long OPP_SIDE_STOP_3 = 10000;
	
	final static long CENTER_STOP_1 = 2000;
	final static long CENTER_STOP_2 = 4000;
	final static long CENTER_STOP_3 = 10000;
	
	final static int RIGHT_DRIFT_ADJUSTMENT = -20; //-15
	final static int LEFT_DRIFT_ADJUSTMENT = 20; //15
	
	final static double LEFT_MOTOR_ADJUSTMENT = .25;
	final static double RIGHT_MOTOR_ADJUSTMENT = .35;
	
	final static int RIGHT = 0;
	final static int LEFT = 1;
	
	double motorPer = 1;
	double turnPer = 1;
	
	
// Starting Left
/* --------------------------- */
	
	public void leftLeft(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time,
							TalonSRX shooterLeft, TalonSRX shooterRight, TalonSRX conveyorLeft, TalonSRX conveyorRight, Shooter shooter, Conveyor conveyor, Pneumatic pneumatics) {
		
		if(time < SAME_SIDE_STOP_1) {
			driveForward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else if(time >= SAME_SIDE_STOP_1 && gyro.getAngle() < (90 + RIGHT_DRIFT_ADJUSTMENT)) {
			//turn(frontL, frontR, backL, backR, RIGHT);
		}
		
		else {
			stop(frontL, frontR, backL, backR);
			shooter.startShooter(shooterLeft, shooterRight, Variables.autoSwitchSpeed);
		}
	}
	
	public void leftRight(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time,
							TalonSRX shooterLeft, TalonSRX shooterRight, TalonSRX conveyorLeft, TalonSRX conveyorRight, Shooter shooter, Conveyor conveyor, Pneumatic pneumatics) {
		
		if(time < OPP_SIDE_STOP_1) {
			System.out.println("Driving forward 1");
			driveForward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else if(time >= OPP_SIDE_STOP_1 && gyro.getAngle() < (90 + RIGHT_DRIFT_ADJUSTMENT)) {
			System.out.println("1st turn");
			//turn(frontL, frontR, backL, backR, RIGHT);
		}
		
		else if(gyro.getAngle() >= (90 + RIGHT_DRIFT_ADJUSTMENT) && time < OPP_SIDE_STOP_2) {
			System.out.println("Driving forward 2");
			driveForward(frontL, frontR, backL, backR, gyro, 90);
		}
		
		else if(time >= OPP_SIDE_STOP_2 && gyro.getAngle() < (180 + RIGHT_DRIFT_ADJUSTMENT)) {
			System.out.println("2nd turn");
			//turn(frontL, frontR, backL, backR, RIGHT);
		}
		
		else if(gyro.getAngle() >= (180 + RIGHT_DRIFT_ADJUSTMENT) && time < OPP_SIDE_STOP_3) {
			System.out.println("Driving forward 3");
			driveForward(frontL, frontR, backL, backR, gyro, 180);
		}
		
		else {
			stop(frontL, frontR, backL, backR);
			shooter.startShooter(shooterLeft, shooterRight, Variables.autoSwitchSpeed);
		}
	}
	
// Starting Right
/* --------------------------- */
	
	public void rightRight(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time,
							TalonSRX shooterLeft, TalonSRX shooterRight, TalonSRX conveyorLeft, TalonSRX conveyorRight, Shooter shooter, Conveyor conveyor, Pneumatic pneumatics) {
		
		
		if(time < SAME_SIDE_STOP_1) {
			driveForward(frontL, frontR, backL, backR, gyro, 0);
			System.out.println("RR-Forward");
		}
		
		else if(time >= SAME_SIDE_STOP_1 && gyro.getAngle() > (-90 + LEFT_DRIFT_ADJUSTMENT)) {
			System.out.println(gyro.getAngle());
			turn(frontL, frontR, backL, backR, LEFT);
			System.out.println("RR-Turning");
		}
		
		else {
			System.out.println("RR-STOP");
			stop(frontL, frontR, backL, backR);
			shooter.startShooter(shooterLeft, shooterRight, Variables.autoSwitchSpeed);
		}
	}
	
	public void rightLeft(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time,
							TalonSRX shooterLeft, TalonSRX shooterRight, TalonSRX conveyorLeft, TalonSRX conveyorRight, Shooter shooter, Conveyor conveyor, Pneumatic pneumatics) {
		
		if(time < OPP_SIDE_STOP_1) {
			System.out.println("Driving forward 1");
			driveForward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else if(time >= OPP_SIDE_STOP_1 && gyro.getAngle() > (-90 + LEFT_DRIFT_ADJUSTMENT)) {
			System.out.println("1st turn");
			turn(frontL, frontR, backL, backR, LEFT);
		}
		
		else if(gyro.getAngle() <= (-90 + LEFT_DRIFT_ADJUSTMENT) && time < OPP_SIDE_STOP_2) {
			System.out.println("Driving forward 2");
			driveForward(frontL, frontR, backL, backR, gyro, -90);
		}
		
		else if(time >= OPP_SIDE_STOP_2 && gyro.getAngle() > (-180 + LEFT_DRIFT_ADJUSTMENT)) {
			System.out.println("2nd turn");
			turn(frontL, frontR, backL, backR, LEFT);
		}
		
		else if(gyro.getAngle() <= (-180 + LEFT_DRIFT_ADJUSTMENT) && time < OPP_SIDE_STOP_3) {
			System.out.println("Driving forward 3");
			driveForward(frontL, frontR, backL, backR, gyro, -180);
		}
		
		else {
			stop(frontL, frontR, backL, backR);
			shooter.startShooter(shooterLeft, shooterRight, Variables.autoSwitchSpeed);
		}
	}
	
// Center	
/* --------------------------- */
	
	public void centerLeft(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time,
							TalonSRX shooterLeft, TalonSRX shooterRight, TalonSRX conveyorLeft, TalonSRX conveyorRight, Shooter shooter, Conveyor conveyor, Pneumatic pneumatics) {
		
		if (time < CENTER_STOP_1) {
			driveForward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else if(time >= CENTER_STOP_1 && gyro.getAngle() > (-90 + LEFT_DRIFT_ADJUSTMENT)) {
			turn(frontL, frontR, backL, backR, LEFT);
		}
		
		else if(gyro.getAngle() <= (90 + LEFT_DRIFT_ADJUSTMENT) && time < CENTER_STOP_2) {
			driveForward(frontL, frontR, backL, backR, gyro, -90);
		}
		
		else if(time >= CENTER_STOP_2 && gyro.getAngle() < (0 + RIGHT_DRIFT_ADJUSTMENT)) {
			//turn(frontL, frontR, backL, backR, RIGHT);
		}
		
		else if(gyro.getAngle() >= (0 + RIGHT_DRIFT_ADJUSTMENT) && time < CENTER_STOP_3) {
			driveBackward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else {
			stop(frontL, frontR, backL, backR);
			shooter.startShooter(shooterLeft, shooterRight, Variables.autoSwitchSpeed);
		}
	}
	
	public void centerRight(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, long time,
							TalonSRX shooterLeft, TalonSRX shooterRight, TalonSRX conveyorLeft, TalonSRX conveyorRight, 
							Shooter shooter, Conveyor conveyor, Pneumatic pneumatics) {
		
		if (time < CENTER_STOP_1) {
			driveForward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else if(time >= CENTER_STOP_1 && gyro.getAngle() < (90 + RIGHT_DRIFT_ADJUSTMENT)) {
			//turn(frontL, frontR, backL, backR, RIGHT);
		}
		
		else if(gyro.getAngle() >= (90 + RIGHT_DRIFT_ADJUSTMENT) && time < CENTER_STOP_2) {
			driveForward(frontL, frontR, backL, backR, gyro, 90);
		}
		
		else if(time >= CENTER_STOP_2 && gyro.getAngle() > (0 + LEFT_DRIFT_ADJUSTMENT)) {
			turn(frontL, frontR, backL, backR, LEFT);
		}
		
		else if(gyro.getAngle() <= (0 + LEFT_DRIFT_ADJUSTMENT) && time < CENTER_STOP_3) {
			driveForward(frontL, frontR, backL, backR, gyro, 0);
		}
		
		else {
			stop(frontL, frontR, backL, backR);
			shooter.startShooter(shooterLeft, shooterRight, Variables.autoSwitchSpeed);
		}
	}
	
// Forward/Turn/Back/Stop
/* --------------------------- */
	
	public void driveForward(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR) {
		
		// Method to drive forward without using bearings
		
		frontL.set(ControlMode.PercentOutput, 1 * motorPer * LEFT_MOTOR_ADJUSTMENT);
		backL.set(ControlMode.PercentOutput, 1 * motorPer * LEFT_MOTOR_ADJUSTMENT);//ADD MOTOR ADJUSTMENT TO ALL OTHER METHODS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		frontR.set(ControlMode.PercentOutput, -1 * motorPer * RIGHT_MOTOR_ADJUSTMENT);
		backR.set(ControlMode.PercentOutput, -1 * motorPer * RIGHT_MOTOR_ADJUSTMENT);
		
	}
	
	public void driveForward(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, int bearing) {
		
		// Method to drive forward using bearings
		
		if(Math.abs(gyro.getAngle() - bearing) < 2) {
			driveForward(frontL, frontR, backL, backR);
		}
		
		else if(gyro.getAngle() < bearing) {
			frontL.set(ControlMode.PercentOutput, 1 * motorPer * LEFT_MOTOR_ADJUSTMENT);
			backL.set(ControlMode.PercentOutput, 1 * motorPer * LEFT_MOTOR_ADJUSTMENT);
			
			frontR.set(ControlMode.PercentOutput, -.9 * motorPer * RIGHT_MOTOR_ADJUSTMENT);
			backR.set(ControlMode.PercentOutput, -.9 * motorPer * RIGHT_MOTOR_ADJUSTMENT);
		}
		
		else if (gyro.getAngle() > bearing) {
			frontL.set(ControlMode.PercentOutput, .9 * motorPer * LEFT_MOTOR_ADJUSTMENT);
			backL.set(ControlMode.PercentOutput, .9 * motorPer * LEFT_MOTOR_ADJUSTMENT);
			
			frontR.set(ControlMode.PercentOutput, -1 * motorPer * RIGHT_MOTOR_ADJUSTMENT);
			backR.set(ControlMode.PercentOutput, -1 * motorPer * RIGHT_MOTOR_ADJUSTMENT);
		}
	}
	
	public void turn(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, int direction) {
		
		// Method to turn using angles
		
		switch(direction) {
		
		// Turns Right
		case RIGHT:
			System.out.println("TURN-RIGHT");
			frontL.set(ControlMode.PercentOutput, 1 * turnPer * LEFT_MOTOR_ADJUSTMENT);
			backL.set(ControlMode.PercentOutput, 1 * turnPer * LEFT_MOTOR_ADJUSTMENT);
			
			frontR.set(ControlMode.PercentOutput, 1 * turnPer * RIGHT_MOTOR_ADJUSTMENT);
			backR.set(ControlMode.PercentOutput, 1 * turnPer * RIGHT_MOTOR_ADJUSTMENT);
		break;
		
		// Turn Left
		case LEFT:
			System.out.println("TURN-LEFT");
			frontL.set(ControlMode.PercentOutput, -1 * turnPer * LEFT_MOTOR_ADJUSTMENT);
			backL.set(ControlMode.PercentOutput, -1 * turnPer * LEFT_MOTOR_ADJUSTMENT);
			
			frontR.set(ControlMode.PercentOutput, -1 * turnPer * RIGHT_MOTOR_ADJUSTMENT);
			backR.set(ControlMode.PercentOutput, -1 * turnPer * RIGHT_MOTOR_ADJUSTMENT);
		break;
		
		default:
			System.out.println("TURN-STOP");
			stop(frontL, frontR, backL, backR);
		}
		
	}
	
	public void driveBackward(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR) {
		
		frontL.set(ControlMode.PercentOutput, -1 * turnPer * LEFT_MOTOR_ADJUSTMENT);
		backL.set(ControlMode.PercentOutput, -1 * turnPer * LEFT_MOTOR_ADJUSTMENT);
		
		frontR.set(ControlMode.PercentOutput, 1 * turnPer * RIGHT_MOTOR_ADJUSTMENT);
		backR.set(ControlMode.PercentOutput, 1 * turnPer * RIGHT_MOTOR_ADJUSTMENT);
	}
	
	public void driveBackward(TalonSRX frontL, TalonSRX frontR, TalonSRX backL, TalonSRX backR, ADXRS450_Gyro gyro, int bearing) {
		
		if (Math.abs(gyro.getAngle() - bearing) < 2) {
			driveBackward(frontL, frontR, backL, backR);
		}
		
		else if (gyro.getAngle() - bearing > 0) {
			frontL.set(ControlMode.PercentOutput, -1 * turnPer * LEFT_MOTOR_ADJUSTMENT);
			backL.set(ControlMode.PercentOutput, -1 * turnPer * LEFT_MOTOR_ADJUSTMENT);
			
			frontR.set(ControlMode.PercentOutput, .9 * turnPer * RIGHT_MOTOR_ADJUSTMENT);
			backR.set(ControlMode.PercentOutput, .9 * turnPer * RIGHT_MOTOR_ADJUSTMENT);
		}
		
		else if (gyro.getAngle() - bearing < 0) {
			frontL.set(ControlMode.PercentOutput, -.9 * turnPer * LEFT_MOTOR_ADJUSTMENT);
			backL.set(ControlMode.PercentOutput, -.9 * turnPer * LEFT_MOTOR_ADJUSTMENT);
			
			frontR.set(ControlMode.PercentOutput, 1 * turnPer * RIGHT_MOTOR_ADJUSTMENT);
			backR.set(ControlMode.PercentOutput, 1 * turnPer * RIGHT_MOTOR_ADJUSTMENT);
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
		//0 IS LEFT
		
		char switchPlacement = data.charAt(0);
		
		if(side == 0 && switchPlacement =='L'){
			return true;
		}
		
		else if (side == 1 && switchPlacement == 'R') {
			return true;
		}
		else{
		return false;
		}
	}
}