package com.demoapp;

import com.demoapp.presenters.DataPresenter;
import com.demoapp.services.DataService;
import com.demoapp.services.DataServiceListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Config(constants = BuildConfig.class, sdk = 21)
@RunWith(MockitoJUnitRunner.class)
public class DataPresenterTest {

    DataPresenter subject;

    @Captor
    private ArgumentCaptor<DataServiceListener> argumentCaptor;


    @Before
    public void setUp() {
        subject = new DataPresenter(null, null);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadDataExpectNullList() {
        subject.loadData();

        DataService dataService = Mockito.mock(DataService.class);
        DataServiceListener dataServiceListener = Mockito.mock(DataServiceListener.class);
        dataService.loadData(dataServiceListener);

        verify(dataService, times(1)).loadData(argumentCaptor.capture());

        dataServiceListener.onSuccess(null);
        assertNull(subject.getList());
    }
    

@Test
    public void testErrorLoadData(Error error) {
        subject.loadData();

        DataService dataService = Mockito.mock(DataService.class);
        DataServiceListener dataServiceListener = Mockito.mock(DataServiceListener.class);
        dataService.loadData(dataServiceListener);

        verify(dataService, times(1)).loadData(argumentCaptor.capture());

        dataServiceListener.onError(error);
        assertTrue(error);
    }

}