package com.name_template.name_template.healt;

import org.springframework.stereotype.Component;

@Component
public class HealthManager {


    public HealthStatus getStatus(){

        /*
            Manage Health here

         */


        return HealthStatus.HEALTHY;
    }


}
