package utils;

import org.testng.annotations.DataProvider;

public class CsvDataProvider {

    @DataProvider(name = "testProductsCsv")
    public static Object[][] Authentication() throws Exception{

        Object[][] testObjArray = ExcelUtils.getTableArray("src/test/resources/test_data/ExampleTestData.xls","ExampleTestData");

        return (testObjArray);

    }
}
