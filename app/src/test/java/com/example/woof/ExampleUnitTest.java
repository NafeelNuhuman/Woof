package com.example.woof;

import com.example.woof.accessories.singleItemView;
import static  org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class ExampleUnitTest {
    private singleItemView singleItemView;

    @Before
    public void setup(){
        singleItemView = new singleItemView();
    }

    @Test
    public void testAddition(){
        double result = singleItemView.add(5,5);
        assertEquals(10.00,10.01,0.5);
    }

    @Test
    public void testSubstraction(){
        double result = singleItemView.substract(7,5);
        assertEquals(2.00,2.01,0.5);
    }
}
