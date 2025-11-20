package com.gcgenome.lims.client;

import com.gcgenome.lims.api.SampleApi;
import com.gcgenome.lims.api.ServiceApi;
import com.gcgenome.lims.dto.DataTransformUtil;
import com.gcgenome.lims.dto.Sample;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLLabelElement;
import elemental2.dom.HTMLTableElement;
import net.sayaya.ui.HTMLElementBuilder;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HTMLContainerBuilder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.jboss.elemento.Elements.*;

public class SampleElement extends HTMLElementBuilder<HTMLDivElement, SampleElement> {
	public static SampleElement build(Sample dto) {
		return new SampleElement(dto, div());
	}
	private final HTMLContainerBuilder<HTMLTableElement> table = table().style("width: 50%;");
	private final HTMLContainerBuilder<HTMLLabelElement> patientName = label();
	private final HTMLContainerBuilder<HTMLLabelElement> id = label().css("id");
	private final HTMLContainerBuilder<HTMLLabelElement> birth = label();
	private final HTMLContainerBuilder<HTMLLabelElement> sex = label();
	private final HTMLContainerBuilder<HTMLLabelElement> customer = label();
	private final HTMLContainerBuilder<HTMLLabelElement> mrn = label();
	private final HTMLContainerBuilder<HTMLLabelElement> lblSex = label().css("label").add("Sex:");
	private final HTMLContainerBuilder<HTMLTableElement> table2 = table().style("width: 50%;");
	private SampleElement(Sample dto, HTMLContainerBuilder<HTMLDivElement> e) {
		super(e.css("sample").style("height: 112px; border-bottom-left-radius: 0; display: flex; justify-content: space-between;"));
		e.add(table.add(colgroup().add(col().style("width: 20%;")).add(col().style("width: 30%;"))
						.add(col().style("width: 20%;")).add(col().style("width: 30%;")))
				.add(thead().add(tr().add(th().add(label().css("label").add("Sample ID:"))).add(th().attr("colspan", "2").add(id.add(DataTransformUtil.formatSampleId(dto.id))))))
				.add(tbody().add(tr().add(td().add(label().css("label").add("Patient Name:"))).add(td().attr("colspan", "2").style("text-align: left;").add(patientName)))
						.add(tr().add(td().add(label().css("label").add("Birthday:"))).add(td().add(birth)).add(td().add(lblSex)).add(td().add(sex)))
						.add(tr().add(td().add(label().css("label").add("Institution:"))).add(td().add(customer)).add(td().add(label().css("label").add("MRN:"))).add(td().add(mrn)))))
				.add(table2.add(colgroup().add(col().style("width: 140px;"))));
		var patient = dto.patient;
		if(patient!=null) {
			if(patient.name!=null) this.patientName.add(patient.name);
			else this.patientName.add("-");
			if(patient.mrn!=null) this.mrn.add(patient.mrn);
			else this.mrn.add("-");
			if(patient.sex!=null) this.sex.add(patient.sex);
			else {
				lblSex.style("color: #AD1747");
				this.sex.style("color: #AD1747");
				this.sex.add("-");
			}
			if(patient.birth!=null) this.birth.add(patient.birth);
			else this.birth.add("-");
			var organization = patient.organization;
			if(organization!=null) {
				if(organization.name!=null) customer.add(organization.name);
				else customer.add("-");
			}
		}
		var tr = tr();
		var td = td().style("cursor: pointer;").add(label().css("label").add("Related Samples")).add(sup().add("ⓘ").style("color: #F00")).add(":");
		var info = div().style("" +
				"position: absolute;\n" +
				"    width: 395px;\n" +
				"    padding: 1rem;\n" +
				"    background: #FFF;\n" +
				"    border: 1px solid #ddd; z-index: 999999999;\n" +
				"    border-radius: 1rem; display: none;flex-direction: column;")
				.add(label().add("- 동일 수검자 판별 조건: 의뢰기관(+부속거래처), 생년월일, MRN"))
				.add(label().add("- MRN이나 생년월일이 없는 의뢰는 해당 기능이 제공되지 않습니다."))
				.add(label().add("- 의뢰기관 및 의뢰 방식이 동일해야 합니다. 재단을 거친 거래처와 직거래처는 다른 의뢰로 구분됩니다."));
		td.add(info);
		table2.add(tbody().add(tr.add(td)));
		td.on(EventType.mouseover, evt->{
			info.element().style.display = "flex";
		});
		td.on(EventType.mouseout, evt->{
			info.element().style.display = "none";
		});
		SampleApi.siblings(dto.id.longValue()).then(siblings->{
			if(siblings!=null && siblings.length > 0) {
				siblings = Arrays.stream(siblings).sorted(Comparator.comparing(a -> a.id)).toArray(Sample[]::new);
				var rows = div().style("display: flex;" +
						"flex-direction: column;" +
						"max-width: 500px;" +
						"max-height: 112px;" +
						"overflow-x: hidden;" +
						"overflow-y: auto;" +
						"text-wrap: nowrap;");
				tr.add(td().style("text-align: left;").add(rows));

				for(var sibling: siblings) {
					String suffix = "　 ";
					var anchor = a().attr("href", "#"+sibling.id);
					if(sibling.id.longValue() == dto.id.longValue()) suffix = "▶ ";
					rows.add(span().add(suffix).add(anchor));
					ServiceApi.services(sibling.id.longValue()).then(services->{
						if(services!=null && !services.isEmpty()) {
							var serviceNames = services.stream().map(s->s.name).collect(Collectors.joining(", "));
							anchor.add(DataTransformUtil.formatSampleId(sibling.id) + " (" + serviceNames + ")");
						}
						return null;
					});
				}
			}
			return null;
		});
	}

	@Override
	public SampleElement that() {
		return this;
	}
}
