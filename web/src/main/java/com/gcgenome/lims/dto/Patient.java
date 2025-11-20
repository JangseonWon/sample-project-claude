package com.gcgenome.lims.dto;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
public final class Patient {
    public Organization organization;
    public String mrn;
    public String name;
    public String code;
    public String sex;
    public String birth;
}
