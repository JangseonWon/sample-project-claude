package com.gcgenome.lims.dto;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
public final class Service {
    public String code;
    public String name;
    public String displayName;
}