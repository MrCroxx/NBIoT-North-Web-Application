package com.croxx.nbiot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class DebugController {
    @RequestMapping("/json")
    //@PreAuthorize("hasRole('USER')")
    public Egg json(){
        return new Egg("Croxx","gg");
    }

    class Egg{
        private String name;
        private String value;

        public Egg(){
        }

        public Egg(String name,String value){
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
