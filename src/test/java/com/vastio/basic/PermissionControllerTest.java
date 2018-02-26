package com.vastio.basic;

import com.alibaba.fastjson.JSONObject;
import com.vastio.basic.entity.requset.PermissionRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class PermissionControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testQueryPermission() throws Exception {
        mvc.perform(get("/api/permission"))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryPermissionByRole() throws Exception {
        mvc.perform(get("/api/permission/3"))
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdatePermission() throws Exception {
        PermissionRequest permissionRequest = new PermissionRequest();
        permissionRequest.setRoleId(3);
        List<Integer> list = new ArrayList<>();
        list.add(3);
        permissionRequest.setPermission(list);
        mvc.perform(put("/api/permission")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(permissionRequest)))
                .andExpect(status().isOk());

    }


}
