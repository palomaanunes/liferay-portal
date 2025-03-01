/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.dynamic.data.mapping.internal.io;

import com.liferay.dynamic.data.mapping.io.DDMFormLayoutDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.model.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.model.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.util.LocalizedValueUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = "ddm.form.layout.deserializer.type=json",
	service = DDMFormLayoutDeserializer.class
)
public class DDMFormLayoutJSONDeserializer
	implements DDMFormLayoutDeserializer {

	@Override
	public DDMFormLayoutDeserializerDeserializeResponse deserialize(
		DDMFormLayoutDeserializerDeserializeRequest
			ddmFormLayoutDeserializerDeserializeRequest) {

		DDMFormLayout ddmFormLayout = new DDMFormLayout();

		DDMFormLayoutDeserializerDeserializeResponse.Builder builder =
			DDMFormLayoutDeserializerDeserializeResponse.Builder.newBuilder(
				ddmFormLayout);

		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(
				ddmFormLayoutDeserializerDeserializeRequest.getContent());

			if (Validator.isNotNull(
					jsonObject.getString("definitionSchemaVersion"))) {

				ddmFormLayout.setDefinitionSchemaVersion(
					jsonObject.getString("definitionSchemaVersion"));
			}

			setDDMFormLayoutDefaultLocale(
				jsonObject.getString("defaultLanguageId"), ddmFormLayout);
			setDDMFormLayoutPages(
				jsonObject.getJSONArray("pages"), ddmFormLayout);

			setDDMFormLayoutPageTitlesDefaultLocale(ddmFormLayout);

			String paginationMode = jsonObject.getString("paginationMode");

			if (Validator.isNotNull(paginationMode)) {
				setDDMFormLayoutPaginationMode(paginationMode, ddmFormLayout);
			}
			else {
				setDDMFormLayoutPaginationMode(
					DDMFormLayout.WIZARD_MODE, ddmFormLayout);
			}

			setDDMFormRules(jsonObject.getJSONArray("rules"), ddmFormLayout);

			return builder.build();
		}
		catch (JSONException jsonException) {
			if (_log.isWarnEnabled()) {
				_log.warn(jsonException, jsonException);
			}

			builder = builder.exception(jsonException);
		}

		return builder.build();
	}

	protected static void setDDMFormRules(
		JSONArray jsonArray, DDMFormLayout ddmFormLayout) {

		if ((jsonArray == null) || (jsonArray.length() == 0)) {
			return;
		}

		ddmFormLayout.setDDMFormRules(
			DDMFormRuleJSONDeserializer.deserialize(jsonArray));
	}

	protected DDMFormLayoutColumn getDDMFormLayoutColumn(
		JSONObject jsonObject) {

		DDMFormLayoutColumn ddmFormLayoutColumn = new DDMFormLayoutColumn(
			jsonObject.getInt("size"));

		setDDMFormLayouColumnFieldNames(
			jsonObject.getJSONArray("fieldNames"), ddmFormLayoutColumn);

		return ddmFormLayoutColumn;
	}

	protected List<String> getDDMFormLayoutColumnFieldNames(
		JSONArray jsonArray) {

		List<String> ddmFormFieldNames = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ddmFormFieldNames.add(jsonArray.getString(i));
		}

		return ddmFormFieldNames;
	}

	protected List<DDMFormLayoutColumn> getDDMFormLayoutColumns(
		JSONArray jsonArray) {

		List<DDMFormLayoutColumn> ddmFormLayoutColumns = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDMFormLayoutColumn ddmFormLayoutColumn = getDDMFormLayoutColumn(
				jsonArray.getJSONObject(i));

			ddmFormLayoutColumns.add(ddmFormLayoutColumn);
		}

		return ddmFormLayoutColumns;
	}

	protected DDMFormLayoutPage getDDMFormLayoutPage(JSONObject jsonObject) {
		DDMFormLayoutPage ddmFormLayoutPage = new DDMFormLayoutPage();

		setDDMFormLayoutPageDescription(
			jsonObject.getJSONObject("description"), ddmFormLayoutPage);
		setDDMFormLayoutPageRows(
			jsonObject.getJSONArray("rows"), ddmFormLayoutPage);
		setDDMFormLayoutPageTitle(
			jsonObject.getJSONObject("title"), ddmFormLayoutPage);

		return ddmFormLayoutPage;
	}

	protected List<DDMFormLayoutPage> getDDMFormLayoutPages(
		JSONArray jsonArray) {

		List<DDMFormLayoutPage> ddmFormLayoutPages = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDMFormLayoutPage ddmFormLayoutPage = getDDMFormLayoutPage(
				jsonArray.getJSONObject(i));

			ddmFormLayoutPages.add(ddmFormLayoutPage);
		}

		return ddmFormLayoutPages;
	}

	protected DDMFormLayoutRow getDDMFormLayoutRow(JSONObject jsonObject) {
		DDMFormLayoutRow ddmFormLayoutRow = new DDMFormLayoutRow();

		setDDMFormLayoutRowColumns(
			jsonObject.getJSONArray("columns"), ddmFormLayoutRow);

		return ddmFormLayoutRow;
	}

	protected List<DDMFormLayoutRow> getDDMFormLayoutRows(JSONArray jsonArray) {
		List<DDMFormLayoutRow> ddmFormLayoutRows = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDMFormLayoutRow ddmFormLayoutRow = getDDMFormLayoutRow(
				jsonArray.getJSONObject(i));

			ddmFormLayoutRows.add(ddmFormLayoutRow);
		}

		return ddmFormLayoutRows;
	}

	protected void setDDMFormLayouColumnFieldNames(
		JSONArray jsonArray, DDMFormLayoutColumn ddmFormLayoutColumn) {

		List<String> ddmFormLayoutColumnNames =
			getDDMFormLayoutColumnFieldNames(jsonArray);

		ddmFormLayoutColumn.setDDMFormFieldNames(ddmFormLayoutColumnNames);
	}

	protected void setDDMFormLayoutDefaultLocale(
		String defaultLanguageId, DDMFormLayout ddmFormLayout) {

		Locale defaultLocale = LocaleUtil.fromLanguageId(defaultLanguageId);

		ddmFormLayout.setDefaultLocale(defaultLocale);
	}

	protected void setDDMFormLayoutPageDescription(
		JSONObject jsonObject, DDMFormLayoutPage ddmFormLayoutPage) {

		LocalizedValue description = LocalizedValueUtil.toLocalizedValue(
			jsonObject);

		if (description == null) {
			return;
		}

		ddmFormLayoutPage.setDescription(description);
	}

	protected void setDDMFormLayoutPageRows(
		JSONArray jsonArray, DDMFormLayoutPage ddmFormLayoutPage) {

		ddmFormLayoutPage.setDDMFormLayoutRows(getDDMFormLayoutRows(jsonArray));
	}

	protected void setDDMFormLayoutPages(
		JSONArray jsonArray, DDMFormLayout ddmFormLayout) {

		ddmFormLayout.setDDMFormLayoutPages(getDDMFormLayoutPages(jsonArray));
	}

	protected void setDDMFormLayoutPageTitle(
		JSONObject jsonObject, DDMFormLayoutPage ddmFormLayoutPage) {

		LocalizedValue title = LocalizedValueUtil.toLocalizedValue(jsonObject);

		if (title == null) {
			return;
		}

		ddmFormLayoutPage.setTitle(title);
	}

	protected void setDDMFormLayoutPageTitlesDefaultLocale(
		DDMFormLayout ddmFormLayout) {

		for (DDMFormLayoutPage ddmFormLayoutPage :
				ddmFormLayout.getDDMFormLayoutPages()) {

			LocalizedValue title = ddmFormLayoutPage.getTitle();

			title.setDefaultLocale(ddmFormLayout.getDefaultLocale());
		}
	}

	protected void setDDMFormLayoutPaginationMode(
		String paginationMode, DDMFormLayout ddmFormLayout) {

		ddmFormLayout.setPaginationMode(paginationMode);
	}

	protected void setDDMFormLayoutRowColumns(
		JSONArray jsonArray, DDMFormLayoutRow ddmFormLayoutRow) {

		ddmFormLayoutRow.setDDMFormLayoutColumns(
			getDDMFormLayoutColumns(jsonArray));
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFormLayoutJSONDeserializer.class);

	private JSONFactory _jsonFactory;

}