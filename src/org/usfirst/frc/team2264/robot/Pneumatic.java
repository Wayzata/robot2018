package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Pneumatic {
	Compressor compressor;
	DoubleSolenoid shooterSolenoid;
	DoubleSolenoid leftArmSolenoid;
	DoubleSolenoid rightArmSolenoid;
	
	public Pneumatic() {
		compressor = new Compressor(1);
		shooterSolenoid = new DoubleSolenoid(1, 0, 1);
		leftArmSolenoid = new DoubleSolenoid(1, 4, 5);
		rightArmSolenoid = new DoubleSolenoid(1, 6, 7);
		
		compressor.setClosedLoopControl(true);
	}
	
	public void raiseShooter() {
		shooterSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void lowerShooter() {
		shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void extendArms() {
		leftArmSolenoid.set(DoubleSolenoid.Value.kForward);
		rightArmSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractArms() {
		leftArmSolenoid.set(DoubleSolenoid.Value.kReverse);
		rightArmSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
}
