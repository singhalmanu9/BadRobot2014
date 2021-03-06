/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems.interfaces;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Interface for the drive train subsystem;
 * 
 * @author Isaac
 */
public interface IDriveTrain 
{
    /**
     * Drives the robot in tank drive--two sticks represent the left and right
     * sides of the robot; pushing forward on the left stick moves the left side
     * forward, pushing backwards on the right stick moves the right side of the
     * robot backwards.
     * 
     * @param left the left side joystick value (-1 to 1)
     * @param right the right joystick value (-1 to 1)
     */
    public void tankDrive(double left, double right);
    
    public void shiftUp();
    
    public void shiftDown();
    
    public Gyro getGyro();
    
    public Encoder getRightEncoder();
    
    public Encoder getLeftEncoder();
    
    public double getDistanceToWall();
    
    public RobotDrive getTrain();
    
    public double getEncoderDistancePerPulse();
    
    public void setEncoderDistancePerPulse(double d);
}
