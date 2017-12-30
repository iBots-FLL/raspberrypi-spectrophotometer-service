package org.terry.pizertest2;

import com.pi4j.io.gpio.*;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AnalysisService {
    @Async
    public void analyze() throws Exception {
        I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_1);
        I2CDevice device = i2c.getDevice(0x04);
        final GpioController gpio = GpioFactory.getInstance();
        System.out.println("Hello World");
        String startMsg = "RGB";
        int lightLevel = -1;
        for (int wavelength=400; wavelength < 700; wavelength+=10) {
            int[] ints = RGBCalc.waveLengthToRGB(wavelength);
//            device.write(startMsg.getBytes(), 0, startMsg.length());
            byte[] vals = new byte[3];
            vals[0] = (byte)ints[0];
            vals[1] = (byte)ints[1];
            vals[2] = (byte)ints[2];
            for (byte val : vals) {
                device.write(vals, 0, vals.length);
                Thread.sleep(100);
//                device.write(2, (byte) 84);
//                Thread.sleep(100);
                lightLevel = device.read();
            }
//            System.out.println(String.format("Wavelength: %d, R: %d, G: %d, B: %d = Light Level: %d", wavelength, ints[0], ints[1], ints[2], lightLevel));
            System.out.println(String.format("%d %d", wavelength, lightLevel));
        }
    }
}
