package com.theoahga.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.theoahga.model.FireType;

import java.util.List;

public abstract class VehicleMixIn {
    @JsonIgnore
    String name;
    @JsonIgnore
    String description;
    @JsonIgnore
    int placesNumber;
    @JsonIgnore
    List<FireType> associatedFireTypes;
    @JsonIgnore
    String type;
    @JsonIgnore
    boolean isAvaible;
}
