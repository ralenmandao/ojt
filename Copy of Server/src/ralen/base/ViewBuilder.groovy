package ralen.base;

import java.util.Map;

import groovy.xml.MarkupBuilder;

public interface ViewBuilder {
	public String getView(MarkupBuilder builder, def params);
}
