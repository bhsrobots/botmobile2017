package org.usfirst.frc.team5499.robot;
import org.usfirst.frc.team5499.robot.auto.*;
import java.util.ArrayList;
import java.util.*;

import com.ctre.CANTalon;

//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot; 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotSubsystems subsystems;
	Autobot autobot;
	Joystick leftStick;
	Joystick rightStick;
	/*
      RobotDrive myRobot;
      public CANTalon leftBack, rightBack, leftFront, rightFront, climber, intake;
      ADXRS450_Gyro gyro;
      Encoder encoderLeft;
	 */
	Timer timer = new Timer();
	Timer teleopTimer = new Timer();
	Solenoid shifter;
	RobotDashboard dashboard;
	BasicAuto currentAction;
	List<BasicAuto> actions = new ArrayList<BasicAuto>();
	Double intakeSpeed = 0.0;


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		subsystems =  new RobotSubsystems(); // all of the robot subsystems are in this class so it's easy to pass values around
		subsystems.inits();
		dashboard = new RobotDashboard(subsystems); // pass the subsystems to the smartdashboard handling class

		leftStick =  new Joystick(0);
		rightStick = new Joystick(1);
		subsystems.gyro.calibrate(); // calibrates the gyro when the robot turns on. ROBOT CANNOT MOVE or it won't work

		
		autobot = new Autobot(subsystems);
		
		//autobot.init(subsystems.autoSwitch1.get(),subsystems.autoSwitch2.get()); // use the switches on the robot  to decide which auto routine to do
		boolean switch1 = subsystems.autoSwitch1.get();
		boolean switch2 = subsystems.autoSwitch2.get();
		
		// Auto for driving straight forward to gear
		if(switch1==false && switch2==false){
			BasicAuto highgear = new ShiftGears(subsystems, subsystems.HIGH); // shifts to high gear
			BasicAuto moveDistance = new MoveDistance(subsystems, 75, 0.25); //Subtracted 4 in. cause of peg length
			BasicAuto stop = new DoNothing(subsystems);
			
			actions.clear();
			actions.add(moveDistance);
			actions.add(highgear);
			actions.add(stop);
		}
		//Auto for gear on right, need to start in corner
		else if(switch1==true && switch2==false){
			BasicAuto highgear = new ShiftGears(subsystems, subsystems.HIGH); // shifts to high gearf
			BasicAuto move1 = new MoveDistance(subsystems, 16.0468785, 0.25); // move for 1 second at half speed
			BasicAuto turn1 = new TurnAngle(subsystems, -30, 0.25); //Not sure if this turns left or right
			BasicAuto move2 = new MoveDistance(subsystems, 120.5039178, 0.5); //Subtracted 4 in. cause of peg length
			BasicAuto move3 = new MoveDistance(subsystems, 12, 0.25);
			BasicAuto stop = new DoNothing(subsystems);

	
			actions.clear();
			actions.add(move1);
			actions.add(turn1);
			actions.add(move2);
			actions.add(move3);
			actions.add(highgear);
			actions.add(stop);
		}
		//Cross base line
		else if(switch1==false && switch2==true){
			BasicAuto highgear = new ShiftGears(subsystems, subsystems.HIGH); // shifts to high gear
			BasicAuto stop = new DoNothing(subsystems);
			BasicAuto move1 = new MoveDistance(subsystems, 143.5, 0.5);
			
			actions.clear();
			actions.add(move1);
			actions.add(highgear);
			actions.add(stop);
		}
		//Do nothing
		else {
			BasicAuto stop = new DoNothing(subsystems);
			BasicAuto highgear = new ShiftGears(subsystems, subsystems.HIGH); // shifts to high gear

			actions.clear();
			actions.add(highgear);
			actions.add(stop);
		}
		

	}
	
	public void robotPeriodic() {
		dashboard.execute(); // Displaying SmartDashboard
	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
		timer.reset();
		timer.start();
		autobot = new Autobot(subsystems);
		subsystems.autoState = 0;
		
		currentAction = actions.get(subsystems.autoState); // the first init has to happen here
		currentAction.init();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		dashboard.execute(); // Displaying SmartDashboard
		currentAction = actions.get(subsystems.autoState);
		currentAction.execute(); // execute the current action

		if (currentAction.done()){ // if the current action is done
			subsystems.autoState++; // increment to the next action
			currentAction = actions.get(subsystems.autoState); 
			currentAction.init(); // initialize the next action
		}

	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	@Override
	public void teleopInit() {
		//subsystems.gyro.reset();
		subsystems.encoderLeft.reset();
		teleopTimer.reset();
		teleopTimer.start();
		subsystems.intakeEnabled = true;
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		subsystems.execute(); // this function checks the proximity sensor

		subsystems.gyroCalibrating = false;

		subsystems.myRobot.arcadeDrive(-leftStick.getY(), -rightStick.getX()); // throttle on one side and steering on the other

		dashboard.execute(); // Displaying SmartDashboard

		// Climber Control
		if(leftStick.getRawButton(2)){
			subsystems.climber.set(-1);
		}
		else{
			subsystems.climber.set(0);
		}




		
		// INTAKE - this works!
		if(subsystems.rightStick.getRawButton(1)){
			intakeSpeed = -0.6;
			//subsystems.intake.set(-.8);
		}
		else{
			intakeSpeed = 0.0;
			//subsystems.intake.set(0);
		}
		
		if(subsystems.stopWithGear){
			intakeSpeed = 0.0;
		}
		
		if(subsystems.leftStick.getRawButton(1) && !subsystems.rightStick.getRawButton(1)){
			intakeSpeed = 0.6;
		}
		
		subsystems.intake.set(intakeSpeed);
		


		// SHIFTER CONTROL
		// - High Gear
		if (rightStick.getRawButton(3) && subsystems.shifter.get() == false){
			subsystems.shifter.set(true);
			System.out.println("High Gear");
		}

		// - Low Gear
		if (rightStick.getRawButton(4) && subsystems.shifter.get() == true){
			subsystems.shifter.set(false);
			System.out.println("Low Gear");
		}


		//System.out.println(gyro.getAngle());
		//System.out.println(encoderLeft.getRaw()/360);	


	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}
}
