package com.gcgenome.lims.client;

import com.gcgenome.lims.api.FetchApi;
import elemental2.dom.RequestInit;
import elemental2.dom.Response;
import elemental2.promise.Promise;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceApi {
    public Promise<JsPropertyMap<?>> service(String service) {
        RequestInit request = RequestInit.create();
        request.setMethod("GET");
        request.setHeaders(new String[][] {
                new String[] {"Accept", "application/json"},
                new String[] {"Content-Type", "application/vnd.lims.v1"}
        });
        return FetchApi.request("/services/" + service, request).then(Response::json).then(json->Promise.resolve(Js.asPropertyMap(json)));
    }
}
