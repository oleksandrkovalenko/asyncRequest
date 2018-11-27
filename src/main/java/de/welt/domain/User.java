package de.welt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        private class Geo {
            private String lat;
            private String lng;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private class Company {
        private String name;
        private String catchPhrase;
        private String bs;
    }


}
