package org.usfirst.frc.team5499.robot;
import com.ctre.CANTalon;  
import edu.wpi.first.wpilibj.IterativeRobot; 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.TalonSRX;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot;
	Joystick leftStick;
	Joystick rightStick;
	Autobot autobot;
	public CANTalon leftBack, rightBack, leftFront, rightFront;
	
	Timer timer = new Timer();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Joysticks
		leftStick =  new Joystick(0);
		rightStick = new Joystick(1);
		
		// Drive Talons
		leftBack = new CANTalon(9);
		rightBack = new CANTalon(2);
		leftFront = new CANTalon(10);
		rightFront = new CANTalon(1);
		
		myRobot = new RobotDrive(leftBack, leftFront, rightBack, rightFront);


	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
		timer.reset();
		timer.start();
		autobot = new Autobot(myRobot);
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


	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		myRobot.tankDrive(-leftStick.getY(), -rightStick.getY());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}