package com.gcgenome.lims.api;

import com.gcgenome.lims.dto.Sample;
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
public class SampleApi {
	public Promise<Sample> sample(long sampleId) {
		ProgressApi.open(true);
		RequestInit request = RequestInit.create();
		request.setHeaders(new String[][] {
				new String[] {"Accept", "application/json"},
				new String[] {"Content-Type", "application/vnd.lims.v1"}
		});
		return DomGlobal.fetch("/samples/" + sampleId, request).finally_(ProgressApi::close).then(Response::json).then(Js::cast);
	}
	public Promise<Sample[]> siblings(long sampleId) {
		ProgressApi.open(true);
		RequestInit request = RequestInit.create();
		request.setHeaders(new String[][] {
				new String[] {"Accept", "application/json"},
				new String[] {"Content-Type", "application/vnd.lims.v1"}
		});
		return DomGlobal.fetch("/samples/" + sampleId + "/siblings", request).finally_(ProgressApi::close).then(Response::json).then(Js::cast);
	}
	public Promise<List<String>> subjects(long sample, String service) {
		ProgressApi.open(true);
		RequestInit request = RequestInit.create();
		request.setHeaders(new String[][] {
				new String[] {"Accept", "application/json"},
				new String[] {"Content-Type", "application/vnd.lims.v1"}
		});
		return DomGlobal.fetch("/samples/" + sample + "/services/" + service + "/subjects", request).finally_(ProgressApi::close)
				.then(Response::json).<JsArray<Object>>then(Js::cast)
				.then(array->Promise.resolve(array.asList().stream().<String>map(Js::cast).collect(Collectors.toList())));
	}
}
