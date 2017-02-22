package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.robot.*;

public class MoveDistance extends BasicAuto {
	double speed = 0;
	double encoderavg = 0;
	double distanceavg = 0;
	double targetDistance = 0;

	public MoveDistance(RobotSubsystems x, double distance, double speedtomove) {
		super(x);
		speed = speedtomove;
		targetDistance = distance;
	}

	public void init(){
		super.init();
	}

	public void execute(){
		subsystems.myRobot.tankDrive(speed,speed);
	}

	public boolean done(){
		// encoder distance calculation
		encoderavg = (subsystems.encoderLeft.get() - subsystems.encoderRight.get()) / 2.0; // forward is positive for the left motor encoder, negative right motor encoder
		distanceavg = encoderavg * CIRCUMFERENCE / 360.0; // distance in inches

		// distance condition
		if (distanceavg > targetDistance) {
			finished = true;
		}

		// timeout is checked in BasicAuto done()
		if (super.done()){
			finished = true; // I think this might be redundant but I'm not completely sure
		}

		if(finished){
			subsystems.myRobot.tankDrive(0,0);
		}

		return finished;
	}



}
