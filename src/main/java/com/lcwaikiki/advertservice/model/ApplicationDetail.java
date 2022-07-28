package com.lcwaikiki.advertservice.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApplicationDetail {
    @Id
    private int advertID;
    private ApplicationStatus applicationStatus;
}
