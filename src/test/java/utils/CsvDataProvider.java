package utils;

import org.testng.annotations.DataProvider;

public class CsvDataProvider {

    @DataProvider(name = "testProductsCsv")
    public static Object[][] Authentication() throws Exception{

        Object[][] testObjArray = ExcelUtils.getTableArray("/Users/standoubt/IdeaProjects/template-testautomation-java-testng/src/test/resources/test_data/ExampleTestData.xls","ExampleTestData");

        return (testObjArray);

    }
}
