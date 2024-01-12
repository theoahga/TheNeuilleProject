package com.theoahga.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.theoahga.model.FireMan;
import com.theoahga.model.FireType;

import java.util.List;

public class UnitMixIn {
    @JsonIgnore
    List<FireMan> fireMen;
    @JsonIgnore
    List<FireType> specialities;
    @JsonIgnore
    Boolean isAvailable;
}
