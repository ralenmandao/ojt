package ralen.base;

import groovy.xml.MarkupBuilder
import ralen.annotation.View

@View("hi")
public class HiView implements ViewBuilder{

	@Override
	public String getView(MarkupBuilder builder, def params) {
		builder.html{
			h1("Hi ${params.name}")
		}
	}

}
