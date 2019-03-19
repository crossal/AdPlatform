package com.ad;

import com.crossal.ad.AdServiceImpl;
import com.crossal.ad.model.Ad;
import com.crossal.bid.BidService;
import com.crossal.bid.model.Bid;
import com.crossal.helpers.FileWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AdServiceImplTest {

    @InjectMocks
    private AdServiceImpl adService;
    @Mock
    private BidService bidServiceMock;
    @Mock
    private FileWriter fileWriterMock;

    private Bid bid;

    @Before
    public void setupBefore() {
        ReflectionTestUtils.setField(adService, "defaultAdFilePath", "filepath.txt");

        bid = new Bid();
        bid.setId("1234");
        bid.setAdMarkup("<p>some markup</p>");

        when(bidServiceMock.getHighestBidder()).thenReturn(bid);
        when(bidServiceMock.getHighestBidder(any(File.class))).thenReturn(bid);
    }

    @Test
    public void getAd_nullBid_returnsNull() {
        when(bidServiceMock.getHighestBidder()).thenReturn(null);

        Ad ad = adService.getAd();

        verify(bidServiceMock, times(1)).getHighestBidder();
        verify(bidServiceMock, times(0)).getHighestBidder(any());
        assertNull(ad);
    }

    @Test
    public void getAd_nullFilePath_returnsAd() throws IOException {
        Ad ad = adService.getAd(null);

        verify(bidServiceMock, times(1)).getHighestBidder();
        verify(bidServiceMock, times(0)).getHighestBidder(any());
        verify(fileWriterMock, times(1)).writeToFile(anyString(), anyString());

        assertEquals(bid.getAdMarkup(), ad.getMarkup());
    }

    @Test
    public void getAd_invalidFilePath_returnsAd() throws IOException {
        Ad ad = adService.getAd("some string");

        verify(bidServiceMock, times(0)).getHighestBidder();
        verify(bidServiceMock, times(1)).getHighestBidder(any());
        verify(fileWriterMock, times(1)).writeToFile(anyString(), anyString());

        assertEquals(bid.getAdMarkup(), ad.getMarkup());
    }

    @Test
    public void getAd_validFilePath_returnsAd() throws IOException {
        Ad ad = adService.getAd("file.txt");

        verify(bidServiceMock, times(0)).getHighestBidder();
        verify(bidServiceMock, times(1)).getHighestBidder(any());
        verify(fileWriterMock, times(1)).writeToFile(anyString(), anyString());

        assertEquals(bid.getAdMarkup(), ad.getMarkup());
    }

    @Test
    public void getAd_returnsAd() throws IOException {
        Ad ad = adService.getAd();

        verify(bidServiceMock, times(1)).getHighestBidder();
        verify(bidServiceMock, times(0)).getHighestBidder(any());
        verify(fileWriterMock, times(1)).writeToFile(anyString(), anyString());

        assertEquals(bid.getAdMarkup(), ad.getMarkup());
    }
}
