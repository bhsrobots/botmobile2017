package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.robot.*;

public class DoNothing extends BasicAuto {

    public DoNothing(RobotSubsystems x) {
	super(x);
    }

    public void execute() {
	subsystems.myRobot.tankDrive(0,0);
    }

    public boolean finished() { // this class never finishes
	return false;
    }

}
