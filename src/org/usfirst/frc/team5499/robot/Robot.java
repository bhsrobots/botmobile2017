package org.usfirst.frc.team5499.robot;
import java.util.ArrayList;

import com.ctre.CANTalon;

//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot; 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.SPI;
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
	
	double calibrationTime = 3.0;
	
	
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
		
	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
		timer.reset();
		timer.start();
		autobot = new Autobot(subsystems);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
			
		autobot.movePeriod(5, 0.5, 0.5);
	
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
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		if (teleopTimer.get() <= calibrationTime){
			subsystems.gyro.calibrate();
			subsystems.gyroCalibrating = true;
		}
		else{
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
			
			// Gear intake Control
			if(rightStick.getRawButton(1)){
				if (subsystems.gearProximity.getVoltage() < 0.7){
					subsystems.intake.set(-1);
				}
			}
			else{
				subsystems.intake.set(0);
			}
			
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
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}
