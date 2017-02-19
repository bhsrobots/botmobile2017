package org.usfirst.frc.team5499.robot;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
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


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotSubsystems subsystems;
	RobotDrive myRobot;
	Joystick leftStick;
	Joystick rightStick;
	Autobot autobot;
	public CANTalon leftBack, rightBack, leftFront, rightFront, climber, intake;
	ADXRS450_Gyro gyro;
	Encoder encoderLeft;
	Timer timer = new Timer();
	Solenoid shifter;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		leftStick =  new Joystick(0);
		rightStick = new Joystick(1);
		
		// Drive Talons
		leftBack = new CANTalon(9);
		rightBack = new CANTalon(2);
		leftFront = new CANTalon(10);
		rightFront = new CANTalon(1);
		
		//Climber Talon
		climber = new CANTalon(6);
		
		// Intake Talon
		intake = new CANTalon(4); 
		
		//Gyro
		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		
		//Encoders
		encoderLeft = new Encoder(10, 11, false, Encoder.EncodingType.k2X);
		
		//Robot
		myRobot = new RobotDrive(leftBack, leftFront, rightBack, rightFront);
		
		shifter = new Solenoid(0);
		
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
	gyro.reset();
	encoderLeft.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//myRobot.tankDrive(-leftStick.getY(), -rightStick.getY());
		//myRobot.tankDrive(-rightStick.getX(), -rightStick.getY());
		//myRobot.arcadeDrive(-leftStick.getY(),-leftStick.getX()); // arcade drive
		myRobot.arcadeDrive(-leftStick.getY(), -rightStick.getX()); // throttle on one side and steering on the other
		
		if(leftStick.getRawButton(2)){
			climber.set(-1);
		}
		else{
			climber.set(0);
		}
		
		if(rightStick.getRawButton(1)){
			intake.set(-1);
		}
		else{
			intake.set(0);
		}
		
		if (rightStick.getRawButton(3) && shifter.get() == false){
			shifter.set(true);
			System.out.println("High Gear");
		}
		
		if (rightStick.getRawButton(4) && shifter.get() == true){
			shifter.set(false);
			System.out.println("Low Gear");
		}
		
		//System.out.println(gyro.getAngle());
		System.out.println(encoderLeft.getRaw()/360);	
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}
