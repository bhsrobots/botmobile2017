package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.robot.*;

public class TurnTime extends BasicAuto {
    double speed = 0;
    
    public TurnTime(RobotSubsystems x, double timetoturn, double speedtoturn) {
	super(x);
	speed = speedtoturn;
	timeOut = timetoturn;
    }

    public void init(){
	super.init();
    }

    public void execute(){
	subsystems.myRobot.tankDrive(-speed,speed); // turn to the left if speed is positive (I think)
    }

    public boolean done(){

	// timeout condition
	if (subsystems.timer.get() > timeOut){
	    finished = true;
	}

	// stop the motors if the action is finished
	if(finished){
	    subsystems.myRobot.tankDrive(0,0);
	}
	
	return finished;
    }

    
    
}
