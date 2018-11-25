package com.socialbook.users.api.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@RegisterService
@ApplicationPath("/v1")
@CrossOrigin
public class UserApplication extends Application {}
