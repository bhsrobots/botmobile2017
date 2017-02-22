package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.robot.*;

public class MovePeriod extends BasicAuto {
	double timeOut = 0;
	double speed = 0;

	public MovePeriod(RobotSubsystems x, double timetomove, double speedtomove) {
		super(x);
		//subsystems.timer.reset();
		//finished = false;
		timeOut = timetomove;
		speed = speedtomove;

	}

	public void init(){
		super.init();
	}

	public void execute(){
		subsystems.myRobot.tankDrive(speed,speed);
	}

	public boolean done(){
		if (subsystems.timer.get() > timeOut){
			finished = true;
		}

		if(finished){
			subsystems.myRobot.tankDrive(0,0);
		}

		return finished;
	}



}
