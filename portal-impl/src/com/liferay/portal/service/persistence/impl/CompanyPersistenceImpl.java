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

package com.liferay.portal.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchCompanyException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyTable;
import com.liferay.portal.kernel.service.persistence.CompanyPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.impl.CompanyImpl;
import com.liferay.portal.model.impl.CompanyModelImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The persistence implementation for the company service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CompanyPersistenceImpl
	extends BasePersistenceImpl<Company> implements CompanyPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CompanyUtil</code> to access the company persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CompanyImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByWebId;
	private FinderPath _finderPathCountByWebId;

	/**
	 * Returns the company where webId = &#63; or throws a <code>NoSuchCompanyException</code> if it could not be found.
	 *
	 * @param webId the web ID
	 * @return the matching company
	 * @throws NoSuchCompanyException if a matching company could not be found
	 */
	@Override
	public Company findByWebId(String webId) throws NoSuchCompanyException {
		Company company = fetchByWebId(webId);

		if (company == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("webId=");
			sb.append(webId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCompanyException(sb.toString());
		}

		return company;
	}

	/**
	 * Returns the company where webId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param webId the web ID
	 * @return the matching company, or <code>null</code> if a matching company could not be found
	 */
	@Override
	public Company fetchByWebId(String webId) {
		return fetchByWebId(webId, true);
	}

	/**
	 * Returns the company where webId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param webId the web ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching company, or <code>null</code> if a matching company could not be found
	 */
	@Override
	public Company fetchByWebId(String webId, boolean useFinderCache) {
		webId = Objects.toString(webId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {webId};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByWebId, finderArgs, this);
		}

		if (result instanceof Company) {
			Company company = (Company)result;

			if (!Objects.equals(webId, company.getWebId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMPANY_WHERE);

			boolean bindWebId = false;

			if (webId.isEmpty()) {
				sb.append(_FINDER_COLUMN_WEBID_WEBID_3);
			}
			else {
				bindWebId = true;

				sb.append(_FINDER_COLUMN_WEBID_WEBID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindWebId) {
					queryPos.add(webId);
				}

				List<Company> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByWebId, finderArgs, list);
					}
				}
				else {
					Company company = list.get(0);

					result = company;

					cacheResult(company);
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
			return (Company)result;
		}
	}

	/**
	 * Removes the company where webId = &#63; from the database.
	 *
	 * @param webId the web ID
	 * @return the company that was removed
	 */
	@Override
	public Company removeByWebId(String webId) throws NoSuchCompanyException {
		Company company = findByWebId(webId);

		return remove(company);
	}

	/**
	 * Returns the number of companies where webId = &#63;.
	 *
	 * @param webId the web ID
	 * @return the number of matching companies
	 */
	@Override
	public int countByWebId(String webId) {
		webId = Objects.toString(webId, "");

		FinderPath finderPath = _finderPathCountByWebId;

		Object[] finderArgs = new Object[] {webId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPANY_WHERE);

			boolean bindWebId = false;

			if (webId.isEmpty()) {
				sb.append(_FINDER_COLUMN_WEBID_WEBID_3);
			}
			else {
				bindWebId = true;

				sb.append(_FINDER_COLUMN_WEBID_WEBID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindWebId) {
					queryPos.add(webId);
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_WEBID_WEBID_2 =
		"company.webId = ?";

	private static final String _FINDER_COLUMN_WEBID_WEBID_3 =
		"(company.webId IS NULL OR company.webId = '')";

	private FinderPath _finderPathFetchByMx;
	private FinderPath _finderPathCountByMx;

	/**
	 * Returns the company where mx = &#63; or throws a <code>NoSuchCompanyException</code> if it could not be found.
	 *
	 * @param mx the mx
	 * @return the matching company
	 * @throws NoSuchCompanyException if a matching company could not be found
	 */
	@Override
	public Company findByMx(String mx) throws NoSuchCompanyException {
		Company company = fetchByMx(mx);

		if (company == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("mx=");
			sb.append(mx);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCompanyException(sb.toString());
		}

		return company;
	}

	/**
	 * Returns the company where mx = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mx the mx
	 * @return the matching company, or <code>null</code> if a matching company could not be found
	 */
	@Override
	public Company fetchByMx(String mx) {
		return fetchByMx(mx, true);
	}

	/**
	 * Returns the company where mx = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mx the mx
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching company, or <code>null</code> if a matching company could not be found
	 */
	@Override
	public Company fetchByMx(String mx, boolean useFinderCache) {
		mx = Objects.toString(mx, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {mx};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByMx, finderArgs, this);
		}

		if (result instanceof Company) {
			Company company = (Company)result;

			if (!Objects.equals(mx, company.getMx())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMPANY_WHERE);

			boolean bindMx = false;

			if (mx.isEmpty()) {
				sb.append(_FINDER_COLUMN_MX_MX_3);
			}
			else {
				bindMx = true;

				sb.append(_FINDER_COLUMN_MX_MX_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindMx) {
					queryPos.add(mx);
				}

				List<Company> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByMx, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {mx};
							}

							_log.warn(
								"CompanyPersistenceImpl.fetchByMx(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Company company = list.get(0);

					result = company;

					cacheResult(company);
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
			return (Company)result;
		}
	}

	/**
	 * Removes the company where mx = &#63; from the database.
	 *
	 * @param mx the mx
	 * @return the company that was removed
	 */
	@Override
	public Company removeByMx(String mx) throws NoSuchCompanyException {
		Company company = findByMx(mx);

		return remove(company);
	}

	/**
	 * Returns the number of companies where mx = &#63;.
	 *
	 * @param mx the mx
	 * @return the number of matching companies
	 */
	@Override
	public int countByMx(String mx) {
		mx = Objects.toString(mx, "");

		FinderPath finderPath = _finderPathCountByMx;

		Object[] finderArgs = new Object[] {mx};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPANY_WHERE);

			boolean bindMx = false;

			if (mx.isEmpty()) {
				sb.append(_FINDER_COLUMN_MX_MX_3);
			}
			else {
				bindMx = true;

				sb.append(_FINDER_COLUMN_MX_MX_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindMx) {
					queryPos.add(mx);
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_MX_MX_2 = "company.mx = ?";

	private static final String _FINDER_COLUMN_MX_MX_3 =
		"(company.mx IS NULL OR company.mx = '')";

	private FinderPath _finderPathFetchByLogoId;
	private FinderPath _finderPathCountByLogoId;

	/**
	 * Returns the company where logoId = &#63; or throws a <code>NoSuchCompanyException</code> if it could not be found.
	 *
	 * @param logoId the logo ID
	 * @return the matching company
	 * @throws NoSuchCompanyException if a matching company could not be found
	 */
	@Override
	public Company findByLogoId(long logoId) throws NoSuchCompanyException {
		Company company = fetchByLogoId(logoId);

		if (company == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("logoId=");
			sb.append(logoId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCompanyException(sb.toString());
		}

		return company;
	}

	/**
	 * Returns the company where logoId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param logoId the logo ID
	 * @return the matching company, or <code>null</code> if a matching company could not be found
	 */
	@Override
	public Company fetchByLogoId(long logoId) {
		return fetchByLogoId(logoId, true);
	}

	/**
	 * Returns the company where logoId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param logoId the logo ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching company, or <code>null</code> if a matching company could not be found
	 */
	@Override
	public Company fetchByLogoId(long logoId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {logoId};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByLogoId, finderArgs, this);
		}

		if (result instanceof Company) {
			Company company = (Company)result;

			if (logoId != company.getLogoId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMPANY_WHERE);

			sb.append(_FINDER_COLUMN_LOGOID_LOGOID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(logoId);

				List<Company> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByLogoId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {logoId};
							}

							_log.warn(
								"CompanyPersistenceImpl.fetchByLogoId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Company company = list.get(0);

					result = company;

					cacheResult(company);
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
			return (Company)result;
		}
	}

	/**
	 * Removes the company where logoId = &#63; from the database.
	 *
	 * @param logoId the logo ID
	 * @return the company that was removed
	 */
	@Override
	public Company removeByLogoId(long logoId) throws NoSuchCompanyException {
		Company company = findByLogoId(logoId);

		return remove(company);
	}

	/**
	 * Returns the number of companies where logoId = &#63;.
	 *
	 * @param logoId the logo ID
	 * @return the number of matching companies
	 */
	@Override
	public int countByLogoId(long logoId) {
		FinderPath finderPath = _finderPathCountByLogoId;

		Object[] finderArgs = new Object[] {logoId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPANY_WHERE);

			sb.append(_FINDER_COLUMN_LOGOID_LOGOID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(logoId);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_LOGOID_LOGOID_2 =
		"company.logoId = ?";

	private FinderPath _finderPathWithPaginationFindBySystem;
	private FinderPath _finderPathWithoutPaginationFindBySystem;
	private FinderPath _finderPathCountBySystem;

	/**
	 * Returns all the companies where system = &#63;.
	 *
	 * @param system the system
	 * @return the matching companies
	 */
	@Override
	public List<Company> findBySystem(boolean system) {
		return findBySystem(system, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the companies where system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompanyModelImpl</code>.
	 * </p>
	 *
	 * @param system the system
	 * @param start the lower bound of the range of companies
	 * @param end the upper bound of the range of companies (not inclusive)
	 * @return the range of matching companies
	 */
	@Override
	public List<Company> findBySystem(boolean system, int start, int end) {
		return findBySystem(system, start, end, null);
	}

	/**
	 * Returns an ordered range of all the companies where system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompanyModelImpl</code>.
	 * </p>
	 *
	 * @param system the system
	 * @param start the lower bound of the range of companies
	 * @param end the upper bound of the range of companies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching companies
	 */
	@Override
	public List<Company> findBySystem(
		boolean system, int start, int end,
		OrderByComparator<Company> orderByComparator) {

		return findBySystem(system, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the companies where system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompanyModelImpl</code>.
	 * </p>
	 *
	 * @param system the system
	 * @param start the lower bound of the range of companies
	 * @param end the upper bound of the range of companies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching companies
	 */
	@Override
	public List<Company> findBySystem(
		boolean system, int start, int end,
		OrderByComparator<Company> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBySystem;
				finderArgs = new Object[] {system};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBySystem;
			finderArgs = new Object[] {system, start, end, orderByComparator};
		}

		List<Company> list = null;

		if (useFinderCache) {
			list = (List<Company>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Company company : list) {
					if (system != company.isSystem()) {
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

			sb.append(_SQL_SELECT_COMPANY_WHERE);

			sb.append(_FINDER_COLUMN_SYSTEM_SYSTEM_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CompanyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(system);

				list = (List<Company>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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
	 * Returns the first company in the ordered set where system = &#63;.
	 *
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company
	 * @throws NoSuchCompanyException if a matching company could not be found
	 */
	@Override
	public Company findBySystem_First(
			boolean system, OrderByComparator<Company> orderByComparator)
		throws NoSuchCompanyException {

		Company company = fetchBySystem_First(system, orderByComparator);

		if (company != null) {
			return company;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("system=");
		sb.append(system);

		sb.append("}");

		throw new NoSuchCompanyException(sb.toString());
	}

	/**
	 * Returns the first company in the ordered set where system = &#63;.
	 *
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company, or <code>null</code> if a matching company could not be found
	 */
	@Override
	public Company fetchBySystem_First(
		boolean system, OrderByComparator<Company> orderByComparator) {

		List<Company> list = findBySystem(system, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last company in the ordered set where system = &#63;.
	 *
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company
	 * @throws NoSuchCompanyException if a matching company could not be found
	 */
	@Override
	public Company findBySystem_Last(
			boolean system, OrderByComparator<Company> orderByComparator)
		throws NoSuchCompanyException {

		Company company = fetchBySystem_Last(system, orderByComparator);

		if (company != null) {
			return company;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("system=");
		sb.append(system);

		sb.append("}");

		throw new NoSuchCompanyException(sb.toString());
	}

	/**
	 * Returns the last company in the ordered set where system = &#63;.
	 *
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company, or <code>null</code> if a matching company could not be found
	 */
	@Override
	public Company fetchBySystem_Last(
		boolean system, OrderByComparator<Company> orderByComparator) {

		int count = countBySystem(system);

		if (count == 0) {
			return null;
		}

		List<Company> list = findBySystem(
			system, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the companies before and after the current company in the ordered set where system = &#63;.
	 *
	 * @param companyId the primary key of the current company
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next company
	 * @throws NoSuchCompanyException if a company with the primary key could not be found
	 */
	@Override
	public Company[] findBySystem_PrevAndNext(
			long companyId, boolean system,
			OrderByComparator<Company> orderByComparator)
		throws NoSuchCompanyException {

		Company company = findByPrimaryKey(companyId);

		Session session = null;

		try {
			session = openSession();

			Company[] array = new CompanyImpl[3];

			array[0] = getBySystem_PrevAndNext(
				session, company, system, orderByComparator, true);

			array[1] = company;

			array[2] = getBySystem_PrevAndNext(
				session, company, system, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Company getBySystem_PrevAndNext(
		Session session, Company company, boolean system,
		OrderByComparator<Company> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COMPANY_WHERE);

		sb.append(_FINDER_COLUMN_SYSTEM_SYSTEM_2);

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
			sb.append(CompanyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(system);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(company)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Company> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the companies where system = &#63; from the database.
	 *
	 * @param system the system
	 */
	@Override
	public void removeBySystem(boolean system) {
		for (Company company :
				findBySystem(
					system, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(company);
		}
	}

	/**
	 * Returns the number of companies where system = &#63;.
	 *
	 * @param system the system
	 * @return the number of matching companies
	 */
	@Override
	public int countBySystem(boolean system) {
		FinderPath finderPath = _finderPathCountBySystem;

		Object[] finderArgs = new Object[] {system};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPANY_WHERE);

			sb.append(_FINDER_COLUMN_SYSTEM_SYSTEM_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(system);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_SYSTEM_SYSTEM_2 =
		"company.system = ?";

	public CompanyPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("system", "system_");
		dbColumnNames.put("active", "active_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Company.class);

		setModelImplClass(CompanyImpl.class);
		setModelPKClass(long.class);

		setTable(CompanyTable.INSTANCE);
	}

	/**
	 * Caches the company in the entity cache if it is enabled.
	 *
	 * @param company the company
	 */
	@Override
	public void cacheResult(Company company) {
		EntityCacheUtil.putResult(
			CompanyImpl.class, company.getPrimaryKey(), company);

		FinderCacheUtil.putResult(
			_finderPathFetchByWebId, new Object[] {company.getWebId()},
			company);

		FinderCacheUtil.putResult(
			_finderPathFetchByMx, new Object[] {company.getMx()}, company);

		FinderCacheUtil.putResult(
			_finderPathFetchByLogoId, new Object[] {company.getLogoId()},
			company);
	}

	/**
	 * Caches the companies in the entity cache if it is enabled.
	 *
	 * @param companies the companies
	 */
	@Override
	public void cacheResult(List<Company> companies) {
		for (Company company : companies) {
			if (EntityCacheUtil.getResult(
					CompanyImpl.class, company.getPrimaryKey()) == null) {

				cacheResult(company);
			}
		}
	}

	/**
	 * Clears the cache for all companies.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(CompanyImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the company.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Company company) {
		EntityCacheUtil.removeResult(CompanyImpl.class, company);
	}

	@Override
	public void clearCache(List<Company> companies) {
		for (Company company : companies) {
			EntityCacheUtil.removeResult(CompanyImpl.class, company);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			EntityCacheUtil.removeResult(CompanyImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(CompanyModelImpl companyModelImpl) {
		Object[] args = new Object[] {companyModelImpl.getWebId()};

		FinderCacheUtil.putResult(
			_finderPathCountByWebId, args, Long.valueOf(1), false);
		FinderCacheUtil.putResult(
			_finderPathFetchByWebId, args, companyModelImpl, false);

		args = new Object[] {companyModelImpl.getMx()};

		FinderCacheUtil.putResult(
			_finderPathCountByMx, args, Long.valueOf(1), false);
		FinderCacheUtil.putResult(
			_finderPathFetchByMx, args, companyModelImpl, false);

		args = new Object[] {companyModelImpl.getLogoId()};

		FinderCacheUtil.putResult(
			_finderPathCountByLogoId, args, Long.valueOf(1), false);
		FinderCacheUtil.putResult(
			_finderPathFetchByLogoId, args, companyModelImpl, false);
	}

	/**
	 * Creates a new company with the primary key. Does not add the company to the database.
	 *
	 * @param companyId the primary key for the new company
	 * @return the new company
	 */
	@Override
	public Company create(long companyId) {
		Company company = new CompanyImpl();

		company.setNew(true);
		company.setPrimaryKey(companyId);

		return company;
	}

	/**
	 * Removes the company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyId the primary key of the company
	 * @return the company that was removed
	 * @throws NoSuchCompanyException if a company with the primary key could not be found
	 */
	@Override
	public Company remove(long companyId) throws NoSuchCompanyException {
		return remove((Serializable)companyId);
	}

	/**
	 * Removes the company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the company
	 * @return the company that was removed
	 * @throws NoSuchCompanyException if a company with the primary key could not be found
	 */
	@Override
	public Company remove(Serializable primaryKey)
		throws NoSuchCompanyException {

		Session session = null;

		try {
			session = openSession();

			Company company = (Company)session.get(
				CompanyImpl.class, primaryKey);

			if (company == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompanyException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(company);
		}
		catch (NoSuchCompanyException noSuchEntityException) {
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
	protected Company removeImpl(Company company) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(company)) {
				company = (Company)session.get(
					CompanyImpl.class, company.getPrimaryKeyObj());
			}

			if (company != null) {
				session.delete(company);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (company != null) {
			clearCache(company);
		}

		return company;
	}

	@Override
	public Company updateImpl(Company company) {
		boolean isNew = company.isNew();

		if (!(company instanceof CompanyModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(company.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(company);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in company proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Company implementation " +
					company.getClass());
		}

		CompanyModelImpl companyModelImpl = (CompanyModelImpl)company;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(company);
			}
			else {
				company = (Company)session.merge(company);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		EntityCacheUtil.putResult(
			CompanyImpl.class, companyModelImpl, false, true);

		cacheUniqueFindersCache(companyModelImpl);

		if (isNew) {
			company.setNew(false);
		}

		company.resetOriginalValues();

		return company;
	}

	/**
	 * Returns the company with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the company
	 * @return the company
	 * @throws NoSuchCompanyException if a company with the primary key could not be found
	 */
	@Override
	public Company findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompanyException {

		Company company = fetchByPrimaryKey(primaryKey);

		if (company == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompanyException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return company;
	}

	/**
	 * Returns the company with the primary key or throws a <code>NoSuchCompanyException</code> if it could not be found.
	 *
	 * @param companyId the primary key of the company
	 * @return the company
	 * @throws NoSuchCompanyException if a company with the primary key could not be found
	 */
	@Override
	public Company findByPrimaryKey(long companyId)
		throws NoSuchCompanyException {

		return findByPrimaryKey((Serializable)companyId);
	}

	/**
	 * Returns the company with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyId the primary key of the company
	 * @return the company, or <code>null</code> if a company with the primary key could not be found
	 */
	@Override
	public Company fetchByPrimaryKey(long companyId) {
		return fetchByPrimaryKey((Serializable)companyId);
	}

	/**
	 * Returns all the companies.
	 *
	 * @return the companies
	 */
	@Override
	public List<Company> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompanyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of companies
	 * @param end the upper bound of the range of companies (not inclusive)
	 * @return the range of companies
	 */
	@Override
	public List<Company> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompanyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of companies
	 * @param end the upper bound of the range of companies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of companies
	 */
	@Override
	public List<Company> findAll(
		int start, int end, OrderByComparator<Company> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompanyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of companies
	 * @param end the upper bound of the range of companies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of companies
	 */
	@Override
	public List<Company> findAll(
		int start, int end, OrderByComparator<Company> orderByComparator,
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

		List<Company> list = null;

		if (useFinderCache) {
			list = (List<Company>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMPANY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMPANY;

				sql = sql.concat(CompanyModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Company>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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
	 * Removes all the companies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Company company : findAll()) {
			remove(company);
		}
	}

	/**
	 * Returns the number of companies.
	 *
	 * @return the number of companies
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COMPANY);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(
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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return EntityCacheUtil.getEntityCache();
	}

	@Override
	protected String getPKDBName() {
		return "companyId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMPANY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CompanyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the company persistence.
	 */
	public void afterPropertiesSet() {
		Registry registry = RegistryUtil.getRegistry();

		_argumentsResolverServiceRegistration = registry.registerService(
			ArgumentsResolver.class, new CompanyModelArgumentsResolver(),
			HashMapBuilder.<String, Object>put(
				"model.class.name", Company.class.getName()
			).build());

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathFetchByWebId = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByWebId",
			new String[] {String.class.getName()}, new String[] {"webId"},
			true);

		_finderPathCountByWebId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByWebId",
			new String[] {String.class.getName()}, new String[] {"webId"},
			false);

		_finderPathFetchByMx = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByMx",
			new String[] {String.class.getName()}, new String[] {"mx"}, true);

		_finderPathCountByMx = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMx",
			new String[] {String.class.getName()}, new String[] {"mx"}, false);

		_finderPathFetchByLogoId = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByLogoId",
			new String[] {Long.class.getName()}, new String[] {"logoId"}, true);

		_finderPathCountByLogoId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLogoId",
			new String[] {Long.class.getName()}, new String[] {"logoId"},
			false);

		_finderPathWithPaginationFindBySystem = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySystem",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"system_"}, true);

		_finderPathWithoutPaginationFindBySystem = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySystem",
			new String[] {Boolean.class.getName()}, new String[] {"system_"},
			true);

		_finderPathCountBySystem = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySystem",
			new String[] {Boolean.class.getName()}, new String[] {"system_"},
			false);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CompanyImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	private static final String _SQL_SELECT_COMPANY =
		"SELECT company FROM Company company";

	private static final String _SQL_SELECT_COMPANY_WHERE =
		"SELECT company FROM Company company WHERE ";

	private static final String _SQL_COUNT_COMPANY =
		"SELECT COUNT(company) FROM Company company";

	private static final String _SQL_COUNT_COMPANY_WHERE =
		"SELECT COUNT(company) FROM Company company WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "company.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Company exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Company exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CompanyPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"system", "active"});

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			Registry registry = RegistryUtil.getRegistry();

			_serviceRegistrations.add(
				registry.registerService(
					FinderPath.class, finderPath,
					HashMapBuilder.<String, Object>put(
						"cache.name", cacheName
					).build()));
		}

		return finderPath;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;
	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();

	private static class CompanyModelArgumentsResolver
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

			CompanyModelImpl companyModelImpl = (CompanyModelImpl)baseModel;

			long columnBitmask = companyModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(companyModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						companyModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(companyModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			CompanyModelImpl companyModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = companyModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = companyModelImpl.getColumnValue(columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}