package com.bid;

import com.crossal.bid.BidReader;
import com.crossal.bid.BidReaderFactory;
import com.crossal.bid.BidServiceImpl;
import com.crossal.bid.model.Bid;
import com.crossal.bid.model.BidResponse;
import com.crossal.bid.model.SeatBidResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class BidServiceImplTest {

    @InjectMocks
    private BidServiceImpl bidService;
    @Mock
    private BidReaderFactory bidReaderFactoryMock;
    @Mock
    private BidReader bidReaderMock;
    @Mock
    private File fileMock;

    private SeatBidResponse seatBidResponse;
    private List<Bid> bids;
    private Bid bidLow;
    private Bid bidHigh;

    @Before
    public void setupBefore() {
        seatBidResponse = new SeatBidResponse();
        List<BidResponse> bidResponses = new ArrayList<>();
        BidResponse bidResponse = new BidResponse();
        bids = new ArrayList<>();
        bidLow = new Bid();
        bidLow.setId("1234");
        bidLow.setPrice(123.5);
        bids.add(bidLow);
        bidHigh = new Bid();
        bidHigh.setId("2345");
        bidHigh.setPrice(234.5);
        bids.add(bidHigh);
        bidResponse.setBids(bids);
        bidResponses.add(bidResponse);
        seatBidResponse.setSeatBid(bidResponses);

        when(bidReaderFactoryMock.getBidReader()).thenReturn(bidReaderMock);
        when(bidReaderFactoryMock.getBidReader(any())).thenReturn(bidReaderMock);
        when(bidReaderMock.getSeatBid()).thenReturn(seatBidResponse);
    }

    @Test
    public void getHighestBidder_nullSeatBidResponse_returnsNull() {
        when(bidReaderMock.getSeatBid()).thenReturn(null);

        Bid bid = bidService.getHighestBidder();

        assertNull(bid);
    }

    @Test
    public void getHighestBidder_returnsBid() {
        Bid bid = bidService.getHighestBidder();

        verify(bidReaderFactoryMock, times(1)).getBidReader();
        verify(bidReaderFactoryMock, times(0)).getBidReader(any());

        assertEquals(bidHigh.getId(), bid.getId());
    }

    @Test
    public void getHighestBidder_differentBidsOrder_returnsBid() {
        bids.clear();
        bids.add(bidLow);
        bids.add(bidHigh);

        Bid bid = bidService.getHighestBidder();

        verify(bidReaderFactoryMock, times(1)).getBidReader();
        verify(bidReaderFactoryMock, times(0)).getBidReader(any());

        assertEquals(bidHigh.getId(), bid.getId());
    }

    @Test
    public void getHighestBidder_nullFile_returnsBid() {
        Bid bid = bidService.getHighestBidder(null);

        verify(bidReaderFactoryMock, times(1)).getBidReader();
        verify(bidReaderFactoryMock, times(0)).getBidReader(any());

        assertEquals(bidHigh.getId(), bid.getId());
    }

    @Test
    public void getHighestBidder_validFile_returnsBid() {
        Bid bid = bidService.getHighestBidder(fileMock);

        verify(bidReaderFactoryMock, times(0)).getBidReader();
        verify(bidReaderFactoryMock, times(1)).getBidReader(any());

        assertEquals(bidHigh.getId(), bid.getId());
    }
}
