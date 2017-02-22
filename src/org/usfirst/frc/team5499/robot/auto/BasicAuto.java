package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.robot.*;

public class BasicAuto {

	RobotSubsystems subsystems;
	double timeOut = 15; // too long to matter for now
	public final double CIRCUMFERENCE = 12.56637; // The circumference of the wheels for calculating travel distance
	boolean finished = false;

	public BasicAuto (RobotSubsystems x) {
		subsystems = x;
	}

	public void init (){
		// reset timer
		// reset encoders
		subsystems.timer.reset();
		subsystems.timer.start();
		subsystems.encoderLeft.reset();
		subsystems.encoderRight.reset();
		finished = false;
	}

	public void execute() {
	}

	public boolean done() {
		if (subsystems.timer.get() > timeOut){
			subsystems.myRobot.tankDrive(0,0);
			finished = true;
		}

		return finished;
	}


}
