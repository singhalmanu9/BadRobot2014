/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.subsystems.interfaces.IVisionTracking;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
/**
 *
 * @author Steve and Scott
 */
public class VisionTracking extends BadSubsystem implements IVisionTracking {

    private AxisCamera camera;
    
    private static VisionTracking instance;

    private final int HIGH_RED = 0;
    private final int HIGH_BLUE = 0;
    private final int HIGH_GREEN = 0;
    private final int LOW_RED = 0;
    private final int LOW_BLUE = 0;
    private final int LOW_GREEN = 0;//for our color we are shooting at retrotape
    
    public VisionTracking()//should be called camera
    {

    }

        
    public static VisionTracking getInstance()
    {
        if (instance == null)
        {
            instance = new VisionTracking();
        }
        return instance;
    }
    
    protected void initialize() 
    {
        camera.writeResolution(AxisCamera.ResolutionT.k160x120);
        camera.writeMaxFPS(30);
    }

    public String getConsoleIdentity()
    {
        return "VisionTrackingSubsystem";
    }

    protected void initDefaultCommand() 
    {
    
    }


    public BinaryImage processedImage()  
    {
        BinaryImage filteredImage = null;
        try {
            ColorImage image = null;
            try {  
                image = camera.getImage();
            } catch (AxisCameraException ex) {
                ex.printStackTrace();
            } catch (NIVisionException ex) {
                ex.printStackTrace();
            }
            filteredImage = image.thresholdRGB(LOW_RED, HIGH_RED, LOW_GREEN, HIGH_GREEN, LOW_BLUE, HIGH_BLUE);//needs to be the color we send out
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
        
        return filteredImage;
    }

    public boolean isHot(BinaryImage c)
    {
        boolean isHot = false;
        try {
            if(c.getNumberParticles() > 0)
            {
                isHot = true;
            }
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
        
        return isHot;
    }

    public boolean currentImageIsHot()
    {
        return isHot(processedImage());
    }
    
}
