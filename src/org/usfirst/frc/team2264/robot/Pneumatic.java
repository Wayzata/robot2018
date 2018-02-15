package org.usfirst.frc.team2264.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pneumatic {
	Compressor compressor;
	DoubleSolenoid shooterSolenoid;
	DoubleSolenoid armsSolenoid;
	
	boolean armsOut = false;
	
	public Pneumatic() {
		compressor = new Compressor(1);
		armsSolenoid = new DoubleSolenoid(1, 2, 3);
		shooterSolenoid = new DoubleSolenoid(1, 0, 1);
		
		compressor.setClosedLoopControl(true);
	}
	
	public void raiseShooter() {
		shooterSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void lowerShooter() {
		shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void extendArms() {
		armsOut = true;
		armsSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractArms() {
		armsOut = false;
		armsSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
}
