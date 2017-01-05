package com.sysgears.example;

import com.sysgears.example.controller.StreamController;

import com.sysgears.example.history.HistoryDAO;
import com.sysgears.example.service.InputException;
import com.sysgears.example.service.MyExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;


public class TestForApp {

    MyExecutor executor = new MyExecutor(new HistoryDAO());



    @DataProvider
    public Object[][] correctExpression() {
        return new Object[][]{
                {2d, "2"},
                {1d, "3 - 2"},
                {2+(0-13d/3), "2 + -13 / 3"},
                {Math.sin(30), "sin ( 30 )"},
                {3d, "log ( 2 , 8 )"},
                {8d, "root ( 2 , 3 )"},
                {8d, "root ( 2 , log 2 8 )"},
                {3.0d, "log ( 2 , root ( 2 , 3 ) )"},
                {2d, "( 2 )"},
                {-1d, "( 2 + -3 )"},
                {21d, "( 2 + ( 4 * 3 ) + 4 ) + 3"},
                {22d, "2 + 4 * 3 + root ( 2 , 3 )"},
                {20d, "( 2 + 4 ) * 3 + root ( 2 , 3 ) + ( 1 + -7 )"}
        };
    }

    @DataProvider
    public Object[][] throwArithmeticException() {
        return new Object[][]{
                {NaN, "0 / 0"},
                {Infinity, "log ( -1 , 2 )"},
                {Infinity, "root ( 0 , -3 )"}
        };
    }

    @DataProvider
    public Object[][] throwInputException() {
        return new Object[][]{
                {0d, "0/ 0"}
        };
    }

    @Test(dataProvider = "correctExpression")
    public void testCorrectExpression(final Double result, final String expression) {
        Assert.assertEquals(result, executor.execute(expression));
    }

    @Test(dataProvider = "throwArithmeticException", expectedExceptions = ArithmeticException.class)
    public void testArithmeticException(final Double result, final String expression) {
        Assert.assertEquals(result, executor.execute(expression));
    }

    @Test(dataProvider = "throwInputException", expectedExceptions = InputException.class)
    public void testInputException(final Double result, final String expression) {
        Assert.assertEquals(result, executor.execute(expression));
    }

}
