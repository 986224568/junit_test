package parking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VipParkingStrategyTest {

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */

        //given
        Car car = new Car("A");
        ParkingLot parkingLot = new ParkingLot("1", 0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(any());
        //when
        Receipt receipt = vipParkingStrategy.park(parkingLotList, car);
        //then
        assertEquals("1",receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */

        //given
        ParkingLot parkingLot = new ParkingLot("p", 0);
        Car car = new Car("1");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        //when
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(any());
        vipParkingStrategy.park(parkingLotList, car);

        //then
        verify(vipParkingStrategy, times(1)).park(parkingLotList, car);

    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        //given
        Car car = new Car("A");
        ParkingLot parkingLot = new ParkingLot("1", 0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        CarDaoImpl carDao = mock(CarDaoImpl.class);
        when(carDao.isVip(any())).thenReturn(true);
        vipParkingStrategy.carDao = carDao;
        //when
        Receipt receipt = vipParkingStrategy.park(parkingLotList, car);
        //then
        assertEquals("1",receipt.getParkingLotName());
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
