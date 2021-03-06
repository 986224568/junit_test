package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {ParkingLot.class})
public class VipParkingStrategyPowerMockTest {


    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
        * by using PowerMock to mock static method */

        //given
        mockStatic(ParkingLot.class);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        //when
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(25);
        //then
        assertEquals(new Integer(50),vipParkingStrategy.calculateHourlyPrice());
    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using PowerMock to mock static method */
        //given
        mockStatic(ParkingLot.class);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        //when
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(20);
        //then
        assertEquals(new Integer(40),vipParkingStrategy.calculateHourlyPrice());

    }
}
