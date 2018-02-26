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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class LogControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testQueryLoginLog() throws Exception {
        mvc.perform(get("/api/log/login")
                .param("curPage", "1")
                .param("pageSize", "2")
                .param("orgId", "3201")
                .param("startTime", "1419457222941")
                .param("endTime", "1519457222941"))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryOptionalLog() throws Exception {
        mvc.perform(get("/api/log/optional")
                .param("curPage", "1")
                .param("pageSize", "2")
                .param("startTime", "1419457222941")
                .param("endTime", "1519457222941"))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryErrorLog() throws Exception {
        mvc.perform(get("/api/log/error")
                .param("curPage", "1")
                .param("pageSize", "2")
                .param("startTime", "1419457222941")
                .param("endTime", "1519457222941"))
                .andExpect(status().isOk());
    }

}
