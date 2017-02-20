package org.usfirst.frc.team5499.robot;

public class BasicAuto {

	RobotSubsystems subsystem;
	public boolean init = true;
	public boolean finished;
	
	public BasicAuto (RobotSubsystems x) {
		subsystem = x;
	}
	
	public void init (){
		// reset timer
		// reset encoders
		subsystem.timer.reset();
		subsystem.encoderLeft.reset();
		init = false;
	}
	
	public boolean execute() {
		return false;
	}
}
