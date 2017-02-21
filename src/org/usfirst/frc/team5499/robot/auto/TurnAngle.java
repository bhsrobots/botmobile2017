package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.robot.*;

public class TurnAngle extends BasicAuto {
	public final double LEFT = 1;
	public final double RIGHT = -1;

	double speed = 0;
	double encoderavg = 0;
	double distanceavg = 0;
	double targetAngle = 0;
	double turnDirection = LEFT;

	public TurnAngle(RobotSubsystems x, double angle, double speedtoturn) {
		super(x);
		speed = speedtoturn;
		targetAngle = angle;

		if (targetAngle > 0){
			turnDirection = LEFT;
		}
		else if(targetAngle < 0){
			turnDirection = RIGHT;
		}
		else{ //you specified turn zero degrees
			turnDirection = 0;
			finished = true;
		}

		speed = speedtoturn * turnDirection;
	}

	public void init(){
		super.init();
		subsystems.gyro.reset();

	}

	public void execute(){
		subsystems.myRobot.tankDrive(-speed,speed); // turn in place to the left if speed is positive
	}

	public boolean done(){
		// angle done condition - changes based on which way you turn
		if (turnDirection == LEFT && subsystems.gyro.getAngle() > targetAngle) {
			finished = true;
		}
		else if (turnDirection == RIGHT && subsystems.gyro.getAngle() < targetAngle) {
			finished = true;
		}

		// timeout is checked in BasicAuto done()
		if (super.done()){
			finished = true; // redundant?
		}

		if(finished){
			subsystems.myRobot.tankDrive(0,0);
		}

		return finished;
	}



}
