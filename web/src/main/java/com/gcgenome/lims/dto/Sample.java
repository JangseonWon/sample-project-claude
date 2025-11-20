package com.gcgenome.lims.dto;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
public final class Sample {
    public Double id;
    public Patient patient;
    public String type;
}
