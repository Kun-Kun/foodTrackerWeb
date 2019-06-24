package ua.tracker.food.component.norma.calories;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.tracker.food.component.Gender;
import ua.tracker.food.component.UserParameters;

public class HarrisBenedictBasalMetabolicRateCalculatorTest {


    private HarrisBenedictBasalMetabolicRateCalculator calculator ;

    @Before
    public  void init(){
        calculator = new HarrisBenedictBasalMetabolicRateCalculator();
    }

    @Test
    public void calculateBasalMetabolicRateForMale() throws Exception {
        UserParameters userParameters = new UserParameters();
        userParameters.setWeight(70);
        userParameters.setHeight(170);
        userParameters.setAge(36);
        userParameters.setGender(Gender.MALE);
        float result = calculator.calculateBasalMetabolicRate(userParameters);
        float expected = 1636.3f;
        Assert.assertEquals(expected,result,0.1f);
    }

    @Test
    public void calculateBasalMetabolicRateForFemale() throws Exception {
        UserParameters userParameters = new UserParameters();
        userParameters.setWeight(70);
        userParameters.setHeight(170);
        userParameters.setAge(36);
        userParameters.setGender(Gender.FEMALE);
        float result = calculator.calculateBasalMetabolicRate(userParameters);
        float expected = 1470.6f;
        Assert.assertEquals(expected,result,0.1f);
    }

    @Test
    public void calculateBasalMetabolicRateDefault() throws Exception {
        UserParameters userParameters = new UserParameters();
        userParameters.setWeight(70);
        userParameters.setHeight(170);
        userParameters.setAge(36);
        userParameters.setGender(Gender.DEFAULT);
        float result = calculator.calculateBasalMetabolicRate(userParameters);
        float expected = 1552.6f;
        Assert.assertEquals(expected,result,0.1f);
    }

    @Test(expected = NullPointerException.class)
    public void calculateBasalMetabolicRateNull() throws Exception {
        calculator.calculateBasalMetabolicRate(null);
    }

    @Test(expected = NullPointerException.class)
    public void calculateBasalMetabolicRateNullGender() throws Exception {
        UserParameters userParameters = new UserParameters();
        userParameters.setWeight(70);
        userParameters.setHeight(170);
        userParameters.setAge(36);
        calculator.calculateBasalMetabolicRate(userParameters);
    }

    @Test
    public void calculateForMale() throws Exception {
        UserParameters userParameters = new UserParameters();
        userParameters.setWeight(70);
        userParameters.setHeight(170);
        userParameters.setAge(36);
        userParameters.setGender(Gender.MALE);
        float result = calculator.calculateForMale(userParameters);
        float expected = calculator.calculateBasalMetabolicRate(userParameters);
        Assert.assertEquals(expected,result,0.01f);
    }

    @Test
    public void calculateForFemale() throws Exception {
        UserParameters userParameters = new UserParameters();
        userParameters.setWeight(70);
        userParameters.setHeight(170);
        userParameters.setAge(36);
        userParameters.setGender(Gender.FEMALE);
        float result = calculator.calculateForFemale(userParameters);
        float expected = calculator.calculateBasalMetabolicRate(userParameters);
        Assert.assertEquals(expected,result,0.01f);
    }

    @Test
    public void calculateForDefault() throws Exception {
        UserParameters userParameters = new UserParameters();
        userParameters.setWeight(70);
        userParameters.setHeight(170);
        userParameters.setAge(36);
        userParameters.setGender(Gender.DEFAULT);
        float result = calculator.calculateForDefault(userParameters);
        float expected = calculator.calculateBasalMetabolicRate(userParameters);
        Assert.assertEquals(expected,result,0.01f);
    }

}