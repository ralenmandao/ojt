package com.ralen.di;

import java.util.Collection;

public interface ApplicationContext {
	public <T> T getBean(String beanName);
	public <T> T getBean(Class<?> clazz);
	public Collection<Object> getBeans();
}
