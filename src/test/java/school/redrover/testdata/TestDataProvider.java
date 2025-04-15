package school.redrover.testdata;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    private static final String MAX_PROJECT_NAME = "a".repeat(255);

    @DataProvider(name = "provideInvalidCharacters")
    public static Object[][] provideInvalidCharacters() {
        return new Object[][]{
                {"!"}, {"?"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"},{"*"},
                {"["}, {"]"}, {"|"}, {";"}, {":"}, {"<"}, {">"}, {"/"}};
    }

    @DataProvider(name = "projectNames")
    public Object[][] projectNames() {
        return new Object[][]{
                {"A"},
                {"New1.Project2(name)with_all-valid+chars=test,test'a"},
                {MAX_PROJECT_NAME}
        };
    }

    @DataProvider(name = "itemTypes")
    public Object[][] itemTypes() {
        return new Object[][]{
                {"Freestyle project"},
                {"Pipeline"},
                {"Multi-configuration project"},
                {"Folder"},
                {"Multibranch Pipeline"},
                {"Organization Folder"}
        };
    }
}
