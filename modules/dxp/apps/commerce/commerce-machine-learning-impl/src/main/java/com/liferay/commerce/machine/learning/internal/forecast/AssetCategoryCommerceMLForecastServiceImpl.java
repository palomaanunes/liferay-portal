/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.machine.learning.internal.forecast;

import com.liferay.commerce.machine.learning.forecast.model.AssetCategoryCommerceMLForecast;
import com.liferay.commerce.machine.learning.forecast.service.AssetCategoryCommerceMLForecastService;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastField;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastPeriod;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastScope;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastTarget;
import com.liferay.commerce.machine.learning.internal.forecast.model.AssetCategoryCommerceMLForecastImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.engine.adapter.search.CountSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	enabled = false, immediate = true,
	service = AssetCategoryCommerceMLForecastService.class
)
public class AssetCategoryCommerceMLForecastServiceImpl
	extends BaseCommerceMLForecastServiceImpl<AssetCategoryCommerceMLForecast>
	implements AssetCategoryCommerceMLForecastService {

	@Override
	public AssetCategoryCommerceMLForecast addAssetCategoryCommerceMLForecast(
			AssetCategoryCommerceMLForecast assetCategoryCommerceMLForecast)
		throws PortalException {

		long commerceMLForecastId = getHash(
			assetCategoryCommerceMLForecast.getAssetCategoryId(),
			assetCategoryCommerceMLForecast.getCommerceAccountId(),
			assetCategoryCommerceMLForecast.getPeriod(),
			assetCategoryCommerceMLForecast.getScope(),
			assetCategoryCommerceMLForecast.getTarget(),
			assetCategoryCommerceMLForecast.getTimestamp());

		assetCategoryCommerceMLForecast.setForecastId(commerceMLForecastId);

		return addCommerceMLForecast(assetCategoryCommerceMLForecast);
	}

	@Override
	public AssetCategoryCommerceMLForecast create() {
		return new AssetCategoryCommerceMLForecastImpl();
	}

	@Override
	public AssetCategoryCommerceMLForecast getAssetCategoryCommerceMLForecast(
			long companyId, long forecastId)
		throws PortalException {

		return getCommerceMLForecast(companyId, forecastId);
	}

	@Override
	public List<AssetCategoryCommerceMLForecast>
			getMonthlyRevenueAssetCategoryCommerceMLForecasts(
				long companyId, long[] assetCategoryIds,
				long[] commerceAccountIds, Date actualDate, int historyLength,
				int forecastLength)
		throws PortalException {

		int size =
			assetCategoryIds.length * commerceAccountIds.length *
				(historyLength + forecastLength);

		return getMonthlyRevenueAssetCategoryCommerceMLForecasts(
			companyId, assetCategoryIds, commerceAccountIds, actualDate,
			historyLength, forecastLength, 0, size);
	}

	@Override
	public List<AssetCategoryCommerceMLForecast>
			getMonthlyRevenueAssetCategoryCommerceMLForecasts(
				long companyId, long[] assetCategoryIds,
				long[] commerceAccountIds, Date actualDate, int historyLength,
				int forecastLength, int start, int end)
		throws PortalException {

		Query query = _getMonthlyRevenueQuery(
			actualDate, assetCategoryIds, commerceAccountIds, historyLength,
			forecastLength);

		int size = end - start;

		SearchSearchRequest searchSearchRequest = getSearchSearchRequest(
			commerceMLIndexer.getIndexName(companyId), query, start, size,
			getDefaultSort(true));

		return getSearchResults(searchSearchRequest);
	}

	@Override
	public long getMonthlyRevenueAssetCategoryCommerceMLForecastsCount(
			long companyId, long[] assetCategoryIds, long[] commerceAccountIds,
			Date actualDate, int historyLength, int forecastLength)
		throws PortalException {

		Query query = _getMonthlyRevenueQuery(
			actualDate, assetCategoryIds, commerceAccountIds, historyLength,
			forecastLength);

		CountSearchRequest countSearchRequest = getCountSearchRequest(
			commerceMLIndexer.getIndexName(companyId), query);

		return getCountResult(countSearchRequest);
	}

	@Override
	protected Document toDocumentModel(
		AssetCategoryCommerceMLForecast assetCategoryCommerceMLForecast) {

		Document document = getBaseDocument(assetCategoryCommerceMLForecast);

		document.addNumber(
			CommerceMLForecastField.COMMERCE_ACCOUNT_ID,
			assetCategoryCommerceMLForecast.getCommerceAccountId());
		document.addNumber(
			Field.ASSET_CATEGORY_ID,
			assetCategoryCommerceMLForecast.getAssetCategoryId());

		return document;
	}

	@Override
	protected AssetCategoryCommerceMLForecast toForecastModel(
		Document document) {

		AssetCategoryCommerceMLForecast assetCategoryCommerceMLForecast =
			getBaseCommerceMLForecastModel(
				new AssetCategoryCommerceMLForecastImpl(), document);

		assetCategoryCommerceMLForecast.setAssetCategoryId(
			GetterUtil.getLong(document.get(Field.ASSET_CATEGORY_ID)));

		assetCategoryCommerceMLForecast.setCommerceAccountId(
			GetterUtil.getLong(
				document.get(CommerceMLForecastField.COMMERCE_ACCOUNT_ID)));

		return assetCategoryCommerceMLForecast;
	}

	private Query _getMonthlyRevenueQuery(
			Date actualDate, long[] assetCategoryIds, long[] commerceAccountIds,
			int historyLength, int forecastLength)
		throws ParseException {

		CommerceMLForecastPeriod commerceMLForecastPeriod =
			CommerceMLForecastPeriod.MONTH;

		CommerceMLForecastTarget commerceMLForecastTarget =
			CommerceMLForecastTarget.REVENUE;

		Date endDate = getEndDate(
			actualDate, commerceMLForecastPeriod, forecastLength);

		Date startDate = getStartDate(
			actualDate, commerceMLForecastPeriod, historyLength);

		BooleanQuery booleanQuery = getBaseQuery(
			_commerceMLForecastScope.getLabel(),
			commerceMLForecastPeriod.getLabel(),
			commerceMLForecastTarget.getLabel(), startDate, endDate);

		BooleanFilter preBooleanFilter = booleanQuery.getPreBooleanFilter();

		if (assetCategoryIds.length > 0) {
			TermsFilter assetCategoryIdsTermsFilter = new TermsFilter(
				Field.ASSET_CATEGORY_ID);

			assetCategoryIdsTermsFilter.addValues(
				ArrayUtil.toStringArray(assetCategoryIds));

			preBooleanFilter.add(
				assetCategoryIdsTermsFilter, BooleanClauseOccur.MUST);
		}

		TermsFilter commerceAccountIdsTermsFilter = new TermsFilter(
			CommerceMLForecastField.COMMERCE_ACCOUNT_ID);

		commerceAccountIdsTermsFilter.addValues(
			ArrayUtil.toStringArray(commerceAccountIds));

		preBooleanFilter.add(
			commerceAccountIdsTermsFilter, BooleanClauseOccur.MUST);

		return booleanQuery;
	}

	private static final CommerceMLForecastScope _commerceMLForecastScope =
		CommerceMLForecastScope.ASSET_CATEGORY;

}