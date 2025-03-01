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

package com.liferay.commerce.product.service.persistence.impl;

import com.liferay.commerce.product.exception.NoSuchCPTaxCategoryException;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.model.CPTaxCategoryTable;
import com.liferay.commerce.product.model.impl.CPTaxCategoryImpl;
import com.liferay.commerce.product.model.impl.CPTaxCategoryModelImpl;
import com.liferay.commerce.product.service.persistence.CPTaxCategoryPersistence;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * The persistence implementation for the cp tax category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
public class CPTaxCategoryPersistenceImpl
	extends BasePersistenceImpl<CPTaxCategory>
	implements CPTaxCategoryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPTaxCategoryUtil</code> to access the cp tax category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPTaxCategoryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the cp tax categories where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp tax categories
	 */
	@Override
	public List<CPTaxCategory> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp tax categories where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPTaxCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp tax categories
	 * @param end the upper bound of the range of cp tax categories (not inclusive)
	 * @return the range of matching cp tax categories
	 */
	@Override
	public List<CPTaxCategory> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp tax categories where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPTaxCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp tax categories
	 * @param end the upper bound of the range of cp tax categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp tax categories
	 */
	@Override
	public List<CPTaxCategory> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPTaxCategory> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp tax categories where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPTaxCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp tax categories
	 * @param end the upper bound of the range of cp tax categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp tax categories
	 */
	@Override
	public List<CPTaxCategory> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPTaxCategory> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<CPTaxCategory> list = null;

		if (useFinderCache) {
			list = (List<CPTaxCategory>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPTaxCategory cpTaxCategory : list) {
					if (companyId != cpTaxCategory.getCompanyId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_CPTAXCATEGORY_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CPTaxCategoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<CPTaxCategory>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cp tax category in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp tax category
	 * @throws NoSuchCPTaxCategoryException if a matching cp tax category could not be found
	 */
	@Override
	public CPTaxCategory findByCompanyId_First(
			long companyId, OrderByComparator<CPTaxCategory> orderByComparator)
		throws NoSuchCPTaxCategoryException {

		CPTaxCategory cpTaxCategory = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (cpTaxCategory != null) {
			return cpTaxCategory;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCPTaxCategoryException(sb.toString());
	}

	/**
	 * Returns the first cp tax category in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp tax category, or <code>null</code> if a matching cp tax category could not be found
	 */
	@Override
	public CPTaxCategory fetchByCompanyId_First(
		long companyId, OrderByComparator<CPTaxCategory> orderByComparator) {

		List<CPTaxCategory> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp tax category in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp tax category
	 * @throws NoSuchCPTaxCategoryException if a matching cp tax category could not be found
	 */
	@Override
	public CPTaxCategory findByCompanyId_Last(
			long companyId, OrderByComparator<CPTaxCategory> orderByComparator)
		throws NoSuchCPTaxCategoryException {

		CPTaxCategory cpTaxCategory = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (cpTaxCategory != null) {
			return cpTaxCategory;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCPTaxCategoryException(sb.toString());
	}

	/**
	 * Returns the last cp tax category in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp tax category, or <code>null</code> if a matching cp tax category could not be found
	 */
	@Override
	public CPTaxCategory fetchByCompanyId_Last(
		long companyId, OrderByComparator<CPTaxCategory> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CPTaxCategory> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp tax categories before and after the current cp tax category in the ordered set where companyId = &#63;.
	 *
	 * @param CPTaxCategoryId the primary key of the current cp tax category
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp tax category
	 * @throws NoSuchCPTaxCategoryException if a cp tax category with the primary key could not be found
	 */
	@Override
	public CPTaxCategory[] findByCompanyId_PrevAndNext(
			long CPTaxCategoryId, long companyId,
			OrderByComparator<CPTaxCategory> orderByComparator)
		throws NoSuchCPTaxCategoryException {

		CPTaxCategory cpTaxCategory = findByPrimaryKey(CPTaxCategoryId);

		Session session = null;

		try {
			session = openSession();

			CPTaxCategory[] array = new CPTaxCategoryImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, cpTaxCategory, companyId, orderByComparator, true);

			array[1] = cpTaxCategory;

			array[2] = getByCompanyId_PrevAndNext(
				session, cpTaxCategory, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPTaxCategory getByCompanyId_PrevAndNext(
		Session session, CPTaxCategory cpTaxCategory, long companyId,
		OrderByComparator<CPTaxCategory> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_CPTAXCATEGORY_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CPTaxCategoryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpTaxCategory)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CPTaxCategory> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp tax categories where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CPTaxCategory cpTaxCategory :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpTaxCategory);
		}
	}

	/**
	 * Returns the number of cp tax categories where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp tax categories
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CPTAXCATEGORY_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"cpTaxCategory.companyId = ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the cp tax category where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchCPTaxCategoryException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching cp tax category
	 * @throws NoSuchCPTaxCategoryException if a matching cp tax category could not be found
	 */
	@Override
	public CPTaxCategory findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchCPTaxCategoryException {

		CPTaxCategory cpTaxCategory = fetchByC_ERC(
			companyId, externalReferenceCode);

		if (cpTaxCategory == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("companyId=");
			sb.append(companyId);

			sb.append(", externalReferenceCode=");
			sb.append(externalReferenceCode);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCPTaxCategoryException(sb.toString());
		}

		return cpTaxCategory;
	}

	/**
	 * Returns the cp tax category where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching cp tax category, or <code>null</code> if a matching cp tax category could not be found
	 */
	@Override
	public CPTaxCategory fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the cp tax category where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp tax category, or <code>null</code> if a matching cp tax category could not be found
	 */
	@Override
	public CPTaxCategory fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {companyId, externalReferenceCode};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_ERC, finderArgs, this);
		}

		if (result instanceof CPTaxCategory) {
			CPTaxCategory cpTaxCategory = (CPTaxCategory)result;

			if ((companyId != cpTaxCategory.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					cpTaxCategory.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_CPTAXCATEGORY_WHERE);

			sb.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExternalReferenceCode) {
					queryPos.add(externalReferenceCode);
				}

				List<CPTaxCategory> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_ERC, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									companyId, externalReferenceCode
								};
							}

							_log.warn(
								"CPTaxCategoryPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CPTaxCategory cpTaxCategory = list.get(0);

					result = cpTaxCategory;

					cacheResult(cpTaxCategory);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CPTaxCategory)result;
		}
	}

	/**
	 * Removes the cp tax category where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the cp tax category that was removed
	 */
	@Override
	public CPTaxCategory removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchCPTaxCategoryException {

		CPTaxCategory cpTaxCategory = findByC_ERC(
			companyId, externalReferenceCode);

		return remove(cpTaxCategory);
	}

	/**
	 * Returns the number of cp tax categories where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching cp tax categories
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_CPTAXCATEGORY_WHERE);

			sb.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExternalReferenceCode) {
					queryPos.add(externalReferenceCode);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 =
		"cpTaxCategory.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"cpTaxCategory.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(cpTaxCategory.externalReferenceCode IS NULL OR cpTaxCategory.externalReferenceCode = '')";

	public CPTaxCategoryPersistenceImpl() {
		setModelClass(CPTaxCategory.class);

		setModelImplClass(CPTaxCategoryImpl.class);
		setModelPKClass(long.class);

		setTable(CPTaxCategoryTable.INSTANCE);
	}

	/**
	 * Caches the cp tax category in the entity cache if it is enabled.
	 *
	 * @param cpTaxCategory the cp tax category
	 */
	@Override
	public void cacheResult(CPTaxCategory cpTaxCategory) {
		entityCache.putResult(
			CPTaxCategoryImpl.class, cpTaxCategory.getPrimaryKey(),
			cpTaxCategory);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				cpTaxCategory.getCompanyId(),
				cpTaxCategory.getExternalReferenceCode()
			},
			cpTaxCategory);
	}

	/**
	 * Caches the cp tax categories in the entity cache if it is enabled.
	 *
	 * @param cpTaxCategories the cp tax categories
	 */
	@Override
	public void cacheResult(List<CPTaxCategory> cpTaxCategories) {
		for (CPTaxCategory cpTaxCategory : cpTaxCategories) {
			if (entityCache.getResult(
					CPTaxCategoryImpl.class, cpTaxCategory.getPrimaryKey()) ==
						null) {

				cacheResult(cpTaxCategory);
			}
		}
	}

	/**
	 * Clears the cache for all cp tax categories.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPTaxCategoryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp tax category.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPTaxCategory cpTaxCategory) {
		entityCache.removeResult(CPTaxCategoryImpl.class, cpTaxCategory);
	}

	@Override
	public void clearCache(List<CPTaxCategory> cpTaxCategories) {
		for (CPTaxCategory cpTaxCategory : cpTaxCategories) {
			entityCache.removeResult(CPTaxCategoryImpl.class, cpTaxCategory);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CPTaxCategoryImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CPTaxCategoryModelImpl cpTaxCategoryModelImpl) {

		Object[] args = new Object[] {
			cpTaxCategoryModelImpl.getCompanyId(),
			cpTaxCategoryModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, cpTaxCategoryModelImpl, false);
	}

	/**
	 * Creates a new cp tax category with the primary key. Does not add the cp tax category to the database.
	 *
	 * @param CPTaxCategoryId the primary key for the new cp tax category
	 * @return the new cp tax category
	 */
	@Override
	public CPTaxCategory create(long CPTaxCategoryId) {
		CPTaxCategory cpTaxCategory = new CPTaxCategoryImpl();

		cpTaxCategory.setNew(true);
		cpTaxCategory.setPrimaryKey(CPTaxCategoryId);

		cpTaxCategory.setCompanyId(CompanyThreadLocal.getCompanyId());

		return cpTaxCategory;
	}

	/**
	 * Removes the cp tax category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPTaxCategoryId the primary key of the cp tax category
	 * @return the cp tax category that was removed
	 * @throws NoSuchCPTaxCategoryException if a cp tax category with the primary key could not be found
	 */
	@Override
	public CPTaxCategory remove(long CPTaxCategoryId)
		throws NoSuchCPTaxCategoryException {

		return remove((Serializable)CPTaxCategoryId);
	}

	/**
	 * Removes the cp tax category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp tax category
	 * @return the cp tax category that was removed
	 * @throws NoSuchCPTaxCategoryException if a cp tax category with the primary key could not be found
	 */
	@Override
	public CPTaxCategory remove(Serializable primaryKey)
		throws NoSuchCPTaxCategoryException {

		Session session = null;

		try {
			session = openSession();

			CPTaxCategory cpTaxCategory = (CPTaxCategory)session.get(
				CPTaxCategoryImpl.class, primaryKey);

			if (cpTaxCategory == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPTaxCategoryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpTaxCategory);
		}
		catch (NoSuchCPTaxCategoryException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CPTaxCategory removeImpl(CPTaxCategory cpTaxCategory) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpTaxCategory)) {
				cpTaxCategory = (CPTaxCategory)session.get(
					CPTaxCategoryImpl.class, cpTaxCategory.getPrimaryKeyObj());
			}

			if (cpTaxCategory != null) {
				session.delete(cpTaxCategory);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (cpTaxCategory != null) {
			clearCache(cpTaxCategory);
		}

		return cpTaxCategory;
	}

	@Override
	public CPTaxCategory updateImpl(CPTaxCategory cpTaxCategory) {
		boolean isNew = cpTaxCategory.isNew();

		if (!(cpTaxCategory instanceof CPTaxCategoryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpTaxCategory.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					cpTaxCategory);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpTaxCategory proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPTaxCategory implementation " +
					cpTaxCategory.getClass());
		}

		CPTaxCategoryModelImpl cpTaxCategoryModelImpl =
			(CPTaxCategoryModelImpl)cpTaxCategory;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpTaxCategory.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpTaxCategory.setCreateDate(now);
			}
			else {
				cpTaxCategory.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!cpTaxCategoryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpTaxCategory.setModifiedDate(now);
			}
			else {
				cpTaxCategory.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(cpTaxCategory);
			}
			else {
				cpTaxCategory = (CPTaxCategory)session.merge(cpTaxCategory);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CPTaxCategoryImpl.class, cpTaxCategoryModelImpl, false, true);

		cacheUniqueFindersCache(cpTaxCategoryModelImpl);

		if (isNew) {
			cpTaxCategory.setNew(false);
		}

		cpTaxCategory.resetOriginalValues();

		return cpTaxCategory;
	}

	/**
	 * Returns the cp tax category with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp tax category
	 * @return the cp tax category
	 * @throws NoSuchCPTaxCategoryException if a cp tax category with the primary key could not be found
	 */
	@Override
	public CPTaxCategory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPTaxCategoryException {

		CPTaxCategory cpTaxCategory = fetchByPrimaryKey(primaryKey);

		if (cpTaxCategory == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPTaxCategoryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpTaxCategory;
	}

	/**
	 * Returns the cp tax category with the primary key or throws a <code>NoSuchCPTaxCategoryException</code> if it could not be found.
	 *
	 * @param CPTaxCategoryId the primary key of the cp tax category
	 * @return the cp tax category
	 * @throws NoSuchCPTaxCategoryException if a cp tax category with the primary key could not be found
	 */
	@Override
	public CPTaxCategory findByPrimaryKey(long CPTaxCategoryId)
		throws NoSuchCPTaxCategoryException {

		return findByPrimaryKey((Serializable)CPTaxCategoryId);
	}

	/**
	 * Returns the cp tax category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPTaxCategoryId the primary key of the cp tax category
	 * @return the cp tax category, or <code>null</code> if a cp tax category with the primary key could not be found
	 */
	@Override
	public CPTaxCategory fetchByPrimaryKey(long CPTaxCategoryId) {
		return fetchByPrimaryKey((Serializable)CPTaxCategoryId);
	}

	/**
	 * Returns all the cp tax categories.
	 *
	 * @return the cp tax categories
	 */
	@Override
	public List<CPTaxCategory> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp tax categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPTaxCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp tax categories
	 * @param end the upper bound of the range of cp tax categories (not inclusive)
	 * @return the range of cp tax categories
	 */
	@Override
	public List<CPTaxCategory> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp tax categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPTaxCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp tax categories
	 * @param end the upper bound of the range of cp tax categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp tax categories
	 */
	@Override
	public List<CPTaxCategory> findAll(
		int start, int end,
		OrderByComparator<CPTaxCategory> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp tax categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPTaxCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp tax categories
	 * @param end the upper bound of the range of cp tax categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cp tax categories
	 */
	@Override
	public List<CPTaxCategory> findAll(
		int start, int end, OrderByComparator<CPTaxCategory> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CPTaxCategory> list = null;

		if (useFinderCache) {
			list = (List<CPTaxCategory>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CPTAXCATEGORY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CPTAXCATEGORY;

				sql = sql.concat(CPTaxCategoryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CPTaxCategory>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the cp tax categories from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPTaxCategory cpTaxCategory : findAll()) {
			remove(cpTaxCategory);
		}
	}

	/**
	 * Returns the number of cp tax categories.
	 *
	 * @return the number of cp tax categories
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CPTAXCATEGORY);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "CPTaxCategoryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CPTAXCATEGORY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CPTaxCategoryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp tax category persistence.
	 */
	public void afterPropertiesSet() {
		Bundle bundle = FrameworkUtil.getBundle(
			CPTaxCategoryPersistenceImpl.class);

		_bundleContext = bundle.getBundleContext();

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new CPTaxCategoryModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", CPTaxCategory.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByCompanyId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"companyId"}, true);

		_finderPathWithoutPaginationFindByCompanyId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			true);

		_finderPathCountByCompanyId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			false);

		_finderPathFetchByC_ERC = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "externalReferenceCode"}, true);

		_finderPathCountByC_ERC = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "externalReferenceCode"}, false);
	}

	public void destroy() {
		entityCache.removeCache(CPTaxCategoryImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	private BundleContext _bundleContext;

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CPTAXCATEGORY =
		"SELECT cpTaxCategory FROM CPTaxCategory cpTaxCategory";

	private static final String _SQL_SELECT_CPTAXCATEGORY_WHERE =
		"SELECT cpTaxCategory FROM CPTaxCategory cpTaxCategory WHERE ";

	private static final String _SQL_COUNT_CPTAXCATEGORY =
		"SELECT COUNT(cpTaxCategory) FROM CPTaxCategory cpTaxCategory";

	private static final String _SQL_COUNT_CPTAXCATEGORY_WHERE =
		"SELECT COUNT(cpTaxCategory) FROM CPTaxCategory cpTaxCategory WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cpTaxCategory.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPTaxCategory exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPTaxCategory exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPTaxCategoryPersistenceImpl.class);

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;
	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();

	private static class CPTaxCategoryModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			CPTaxCategoryModelImpl cpTaxCategoryModelImpl =
				(CPTaxCategoryModelImpl)baseModel;

			long columnBitmask = cpTaxCategoryModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(cpTaxCategoryModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						cpTaxCategoryModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(cpTaxCategoryModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			CPTaxCategoryModelImpl cpTaxCategoryModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						cpTaxCategoryModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = cpTaxCategoryModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}