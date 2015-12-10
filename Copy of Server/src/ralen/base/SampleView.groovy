package ralen.base;

import java.util.Map;

import groovy.xml.MarkupBuilder;
import ralen.annotation.View;

@View("sample")
public class SampleView implements ViewBuilder{


	@Override
	public String getView(MarkupBuilder builder, def params) {
		builder.html{
		    table {
		        tr {
		            td(class:"row", "hello ${params.name}!")  
		        }
		    }
		}
	}

}
