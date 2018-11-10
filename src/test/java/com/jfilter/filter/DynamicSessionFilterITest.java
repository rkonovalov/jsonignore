package com.jfilter.filter;

import com.jfilter.components.DynamicFilterProvider;
import com.jfilter.mock.MockClasses;
import com.jfilter.mock.MockHttpRequest;
import com.jfilter.mock.MockMethods;
import com.jfilter.mock.MockUser;
import com.jfilter.mock.config.WSConfigurationEnabled;
import com.jfilter.request.RequestSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.Arrays;
import static com.jfilter.components.DynamicSessionFilter.ATTRIBUTE_FILTER_FIELDS;
import static com.jfilter.mock.webservice.WSMethod.MAPPING_SIGN_IN_DYNAMIC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = WSConfigurationEnabled.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration("src/main/resources")
public class DynamicSessionFilterITest {

    @Autowired
    private DynamicFilterProvider dynamicFilterProvider;

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testOnGetFilterFieldsNotNull() {
        MethodParameter methodParameter = MockMethods.dynamicSessionFilter();
        ServletServerHttpRequest request = MockHttpRequest.getMockDynamicFilterRequest(null);
        FilterFields found = dynamicFilterProvider.getFields(methodParameter, new RequestSession(request));

        assertNotNull(found);
    }
}