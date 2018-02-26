package com.vastio.basic;

import com.alibaba.fastjson.JSONObject;
import com.vastio.basic.entity.requset.UserRequest;
import com.vastio.basic.entity.requset.UserUpdateRequest;
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
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testQueryUsers() throws Exception {
        mvc.perform(get("/api/user")
                .param("curPage", "1")
                .param("pageSize","2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryUser() throws Exception {
        mvc.perform(get("/api/user/5"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        mvc.perform(delete("/api/user/4"))
                .andExpect(status().isOk());

    }



    @Test
    public void testUpdateUser() throws Exception {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setId(4);
        userUpdateRequest.setUsername("sss");
        userUpdateRequest.setOrgId(3211l);
        mvc.perform(put("/api/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(userUpdateRequest)))
                .andExpect(status().isOk());
        userUpdateRequest.setUsername("二二");
        mvc.perform(put("/api/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(userUpdateRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUser() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setFullName("sss");
        userRequest.setUsername("saf");
        userRequest.setOrgId(3201l);
        userRequest.setRoleId(1);
        userRequest.setPassword("asfaf");
        mvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(userRequest)))
                .andExpect(status().isOk());
        userRequest.setUsername("235");
        mvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(userRequest)))
                .andExpect(status().isOk());
    }

}
