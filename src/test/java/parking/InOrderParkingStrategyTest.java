package parking;

import mocking.CustomerDao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static parking.ParkingStrategy.NO_PARKING_LOT;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */

        //given
        Car car = mock(Car.class);
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        //when
        Receipt receipt = inOrderParkingStrategy.park(parkingLotList, car);
        //then
        assertNotNull(receipt);
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */

        //given
        ParkingLot parkingLot = new ParkingLot("p", 0);
        Car car = mock(Car.class);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        //when
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.park(parkingLotList, car);
        //then
        assertEquals(NO_PARKING_LOT, receipt.getParkingLotName());

    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        //given
        ParkingLot parkingLot = new ParkingLot("p", 0);
        Car car = new Car("1");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        //when
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        inOrderParkingStrategy.park(parkingLotList, car);
        //then
        verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(any());
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        //given
        ParkingLot parkingLot = new ParkingLot("p", 1);
        Car car = new Car("1");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        //when
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        inOrderParkingStrategy.park(parkingLotList, car);
        //then
        verify(inOrderParkingStrategy, times(1)).createReceipt(parkingLot, car);

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        //given
        ParkingLot parkingLot = new ParkingLot("p", 0);
        Car car = new Car("1");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        //when
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        inOrderParkingStrategy.park(parkingLotList, car);
        //then
        verify(inOrderParkingStrategy, times(1)).park(parkingLotList, car);
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
        //given
        ParkingLot parkingLot = new ParkingLot("p", 0);
        ParkingLot parkingLot1 = new ParkingLot("p", 1);
        ParkingLot parkingLot2 = new ParkingLot("p", 1);
        ParkingLot parkingLot3 = new ParkingLot("p", 1);
        Car car = new Car("1");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        parkingLotList.add(parkingLot3);
        //when
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        inOrderParkingStrategy.park(parkingLotList, car);
        //then
        verify(inOrderParkingStrategy, times(1)).park(parkingLotList, car);


    }


}
