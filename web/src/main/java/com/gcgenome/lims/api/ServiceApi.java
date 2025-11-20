package com.gcgenome.lims.api;

import com.gcgenome.lims.dto.Service;
import elemental2.core.JsArray;
import elemental2.dom.DomGlobal;
import elemental2.dom.RequestInit;
import elemental2.dom.Response;
import elemental2.promise.Promise;
import jsinterop.base.Js;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ServiceApi {
	public Promise<List<Service>> services(long sampleId) {
		ProgressApi.open(true);
		RequestInit request = RequestInit.create();
		request.setHeaders(new String[][] {
				new String[] {"Accept", "application/json"},
				new String[] {"Content-Type", "application/vnd.lims.v1"}
		});
		return DomGlobal.fetch("/samples/" + sampleId + "/services", request).finally_(ProgressApi::close).then(Response::json).<JsArray<Object>>then(Js::cast)
				.then(array->Promise.resolve(array.asList().stream().<Service>map(Js::cast).collect(Collectors.toList())));
	}
}
