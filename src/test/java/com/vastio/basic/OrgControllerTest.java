package com.vastio.basic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class OrgControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testQueryOrgById() throws Exception {
        mvc.perform(get("/api/organizations/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryBranch() throws Exception {
        mvc.perform(get("/api/branch"))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryOrgTree() throws Exception {
        mvc.perform(get("/api/organizationTree"))
                .andExpect(status().isOk());
    }

}
