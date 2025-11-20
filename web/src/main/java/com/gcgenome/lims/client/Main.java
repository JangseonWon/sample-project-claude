package com.gcgenome.lims.client;

import com.gcgenome.lims.api.SampleApi;
import com.gcgenome.lims.api.ServiceApi;
import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import elemental2.promise.Promise;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HTMLContainerBuilder;

public class Main implements EntryPoint {
	private final HTMLContainerBuilder<HTMLDivElement> div = Elements.div().css("top");
	@Override
	public void onModuleLoad() {
		Elements.body().add(div);
		String hash = DomGlobal.window.location.hash;
		if(hash.contains("-")) hash = hash.replace("-", "");
		long sid = Long.parseLong(hash.substring(1));
		SampleApi.sample(sid)
		.then(sample -> {
			div.add(SampleElement.build(sample));
			return Promise.resolve(sample);
		}).then(s->ServiceApi.services(sid))
		.then(services->{
			ServiceTabElement tab = ServiceTabElement.build(sid, services);
			div.add(tab);
			return Promise.resolve(services);
		});
		DomGlobal.window.addEventListener("hashchange", evt->DomGlobal.window.location.reload(), false);
	}
}
