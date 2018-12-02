package com.oocl.web.sampleWebApp;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingLotResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.oocl.web.sampleWebApp.WebTestUtil.getContentAsObject;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotTests {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void should_get_parking_lots() throws Exception {

        // Given
        final ParkingLot parkingLot = parkingLotRepository.save(new ParkingLot("parkingLot", 80));

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/parkinglots")).andReturn();

        // Then
        assertEquals(200, result.getResponse().getStatus());
        final ParkingLotResponse[] parkingLots = getContentAsObject(result, ParkingLotResponse[].class);
        assertEquals(1, parkingLots.length);
        assertEquals("parkingLot", parkingLots[0].getParkingLotId());
        assertEquals(80, parkingLots[0].getCapacity());
    }

    @Test
    public void should_post_parking_lots() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot("01", 80);
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(parkingLot);
        // When
        final MvcResult result = mvc.perform(post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent)).andDo(print())
                .andExpect(status().isCreated()).andReturn();
        // Then
        ParkingLot addLot = parkingLotRepository.findAll().get(0);
        assertEquals(201, result.getResponse().getStatus());
        assertEquals("01", addLot.getParkingLotId());
        assertEquals(80, addLot.getCapacity());
    }

}
