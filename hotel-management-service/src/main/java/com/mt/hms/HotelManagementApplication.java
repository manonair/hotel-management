package com.mt.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author m1015830
 * HotelManagementApplication !
 *
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
@EnableDiscoveryClient
public class HotelManagementApplication 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(HotelManagementApplication.class, args);
    }
}
