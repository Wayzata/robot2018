package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Pneumatic {
	Compressor compressor; //Air compressor
	DoubleSolenoid shooterSolenoid; // Solenoid for the shooter
	DoubleSolenoid leftArmSolenoid; // Solenoid for left intake arm
	DoubleSolenoid rightArmSolenoid;// Solenoid for right intake arm
	
	public Pneumatic() {
		compressor = new Compressor(1);
		shooterSolenoid = new DoubleSolenoid(1, 0, 1);
		leftArmSolenoid = new DoubleSolenoid(1, 4, 5);
		rightArmSolenoid = new DoubleSolenoid(1, 6, 7);
		
		compressor.setClosedLoopControl(true);
	}
	
	// raises the shooter
	public void raiseShooter() {
		shooterSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	// Lowers the shooter
	public void lowerShooter() {
		shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	// Opens the intake arms outwards
	public void extendArms() {
		leftArmSolenoid.set(DoubleSolenoid.Value.kForward);
		rightArmSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	// Pulls the intake arms in
	public void retractArms() {
		leftArmSolenoid.set(DoubleSolenoid.Value.kReverse);
		rightArmSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
}
