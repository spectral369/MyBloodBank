package com.bloodbank.MyBloodBank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class SerializableResourceBundle extends ResourceBundle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, String> combinedResources = new HashMap<>();
	private List<String> bundleNames;
	private Locale locale;
	private Control control;

	public SerializableResourceBundle(List<String> bundleNames, Locale locale, Control control) {
		this.bundleNames = bundleNames;
		this.locale = locale;
		this.control = control;
	}

	public void load() {
		bundleNames.forEach(bundleName -> {
			ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, control);
			Enumeration<String> keysEnumeration = bundle.getKeys();
			ArrayList<String> keysList = Collections.list(keysEnumeration);
			keysList.forEach(key -> combinedResources.put(key, bundle.getString(key)));
		});
	}

	@Override
	protected Object handleGetObject(String key) {

		return combinedResources.get(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return Collections.enumeration(combinedResources.keySet());
	}

}
