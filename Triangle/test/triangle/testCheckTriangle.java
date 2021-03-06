package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testCheckTriangle {
    @DataProvider(name = "dataForPositiveTests")
    public Object[][] createDataForPositiveTests() {
        return new Object[][]{
                {5, 5, 5}, // equilateral triangle
                {5, 7.3, 7.3}, // isosceles triangle
                {3, 6, 7}, // simple triangle
                {3, 4, 5} // rectangular triangle
        };
    }

    @Test(dataProvider = "dataForPositiveTests")
    public void testCheckTriangleWithCorrectData(double a, double b, double c) {
        Triangle triangle = new Triangle(a,b,c);
        Assert.assertTrue(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), "");
    }

    @DataProvider(name = "dataForNegativeTestsThrowsException")
    public Object[][] createDataForNegativeTestsThrowsException(){
        return new Object[][]{
            {Double.NEGATIVE_INFINITY,5.2,10.3}, //one side of triangle equals negative infinity
            {5.0,Double.NEGATIVE_INFINITY,3.2},
            {4.3,2.2,Double.NEGATIVE_INFINITY,},
            {Double.POSITIVE_INFINITY,4.5,6.0,}, //one side of triangle equals positive infinity
            {2.3,Double.POSITIVE_INFINITY, 7.0,},
            {4.4,4.7,Double.POSITIVE_INFINITY,},
        };
    }

    @Test(dataProvider = "dataForNegativeTestsThrowsException", expectedExceptions = Exception.class)
    public void testCheckTriangleWithDataThrowsException(double a, double b, double c){
        Triangle triangle = new Triangle(a, b, c);
        triangle.checkTriangle();
    }

    @DataProvider(name = "dataForNegativeTests")
    public Object[][] createDataForNegativeTests() {
        return new Object[][]{
                {0,2.3,4.6,"a<=0"}, //one side of triangle equals zero
                {2.3,0,4.7,"b<=0"},
                {2.3,2.4,0,"c<=0"},
                {-1.3,2,4.6,"a<=0"}, //one side of triangle less than zero
                {2.5,-5.0,4.8,"b<=0"},
                {2.7,2.8,-2.0,"c<=0"},
                {5,2.5,2.5,"b+c<=a"}, //one side of triangle equals sum of others
                {3.1,6.4,3.3,"a+c<=b"},
                {2.2,3.3,5.5,"a+b<=c"},
                {6,2.5,2.5,"b+c<=a"}, //one side of triangle less than sum of others
                {3.1,7.0,3.2,"a+c<=b"},
                {2.6,2.7,6.0,"a+b<=c"},
        };
    }

    @Test(dataProvider = "dataForNegativeTests")
    public void testCheckTriangleWithIncorrectData(double a, double b, double c, String message) {
        Triangle triangle = new Triangle(a,b,c);
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), message);
    }
}
