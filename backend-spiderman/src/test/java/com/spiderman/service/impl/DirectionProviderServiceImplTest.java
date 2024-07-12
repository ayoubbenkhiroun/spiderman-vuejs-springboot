package com.spiderman.service.impl;

import com.spiderman.model.Position;
import com.spiderman.service.GameInitializerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DirectionProviderServiceImplTest {
    private DirectionProviderServiceImpl directionProviderService;

    @Mock
    private GameInitializerService gameInitializerService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        directionProviderService = new DirectionProviderServiceImpl(gameInitializerService);
    }

    @Test
    public void testGetDirection_PlayerAboveRightOfBomb() {
        Mockito.when(gameInitializerService.getBombPosition()).thenReturn(new Position(5, 5));
        String direction = directionProviderService.getDirection(new Position(3, 7));
        Assert.assertEquals("UR", direction);
    }
}
