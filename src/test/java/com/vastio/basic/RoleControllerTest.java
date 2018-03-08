package com.vastio.basic;

import com.alibaba.fastjson.JSONObject;
import com.vastio.basic.entity.requset.RoleRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class RoleControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testQueryRole() throws Exception {
        mvc.perform(get("/api/roles")
                .param("roleName", "分局派出所")
                .param("roleDesc", ""))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteRole() throws Exception {
        mvc.perform(delete("/api/role/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void testAddRole() throws Exception {
        RoleRequest roleRequest = new RoleRequest();
        roleRequest.setDescription("ss");
        roleRequest.setName("ee");
        mvc.perform(post("/api/role")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(roleRequest)))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateRole() throws Exception {
        RoleRequest roleRequest = new RoleRequest();
        roleRequest.setDescription("");
        roleRequest.setName("ee");
        mvc.perform(put("/api/role/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(roleRequest)))
                .andExpect(status().isOk());

    }

    @Test
    public void testAddUser() throws Exception {
        RoleRequest roleRequest = new RoleRequest();
        roleRequest.setDescription("ss");
        roleRequest.setName("ee");
        mvc.perform(post("/api/role")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(roleRequest)))
                .andExpect(status().isOk());

    }

}
