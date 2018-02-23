package com.goforit.firstapple.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by goforit on 18/2/4.
 */
public class BfReadBookProgressRecord implements Serializable {

    private Long id;

    private Long bookEventId;

    private Double readProgress;

    private String userNote;

    private Date createdTime;

    private Date updatedTime;
}
