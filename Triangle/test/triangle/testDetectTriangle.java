package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testDetectTriangle {
    @DataProvider(name = "dataForPositiveTests")
    public Object[][] createDataForPositiveTests() {
        return new Object[][]{
                {5, 5, 5, 3}, // equilateral triangle
                {5, 7.3, 7.3, 2}, // isosceles triangle
                {3, 6, 7, 4}, // simple triangle
                {3, 4, 5, 8} // rectangular triangle
        };
    }

    @Test(dataProvider = "dataForPositiveTests")
    public void testDetectTriangleWithCorrectData(double a, double b, double c, int result) {
        Triangle triangle = new Triangle(a,b,c);
        Assert.assertEquals(triangle.detectTriangle(), result);
    }

    @DataProvider(name = "dataForNegativeTests")
    public Object[][] createDataForNegativeTests(){
        return new Object[][]{
                {Double.NEGATIVE_INFINITY,5.2,10.3}, //one side of triangle equals negative infinity
                {5.0,Double.NEGATIVE_INFINITY,3.2},
                {4.3,2.2,Double.NEGATIVE_INFINITY},
                {Double.POSITIVE_INFINITY,4.5,6.0}, //one side of triangle equals positive infinity
                {2.3,Double.POSITIVE_INFINITY, 7.0},
                {4.4,4.7,Double.POSITIVE_INFINITY},
                {0,2.3,4.6}, //one side of triangle equals zero
                {2.3,0,4.7},
                {2.3,2.4,0},
                {-1.3,4,6.6}, //one side of triangle less than zero
                {2.5,-5.0,4.8},
                {2.7,2.8,-2.0},
                {5,2.5,2.5}, //one side of triangle equals sum of others
                {3.1,6.4,3.3},
                {2.2,3.3,5.5},
                {6,2.5,2.5}, //one side of triangle less than sum of others
                {3.1,7.0,3.2},
                {2.6,2.7,6.0},
        };
    }

    @Test(dataProvider = "dataForNegativeTests",expectedExceptions = Exception.class)
    public void testDetectTriangleWithIncorrectData(double a, double b, double c){
        Triangle triangle = new Triangle(a, b, c);
        triangle.detectTriangle();
    }
}
