package org.example.api;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;

public class getPeopleTest {
    GetPeopleApi getPeopleApi = new GetPeopleApi();

    @Test
    public void getValidRequestPeopleApi() {
        String response = getPeopleApi.getValidRequestPeopleApi(1);
        System.out.println(response.toString());
    }
}
