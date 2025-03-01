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

package com.liferay.portal.workflow.kaleo.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for KaleoDefinition. This utility wraps
 * <code>com.liferay.portal.workflow.kaleo.service.impl.KaleoDefinitionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinitionLocalService
 * @generated
 */
public class KaleoDefinitionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.workflow.kaleo.service.impl.KaleoDefinitionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void activateKaleoDefinition(
			long kaleoDefinitionId, long kaleoDefinitionVersionId,
			long startKaleoNodeId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().activateKaleoDefinition(
			kaleoDefinitionId, kaleoDefinitionVersionId, startKaleoNodeId,
			serviceContext);
	}

	public static void activateKaleoDefinition(
			long kaleoDefinitionId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().activateKaleoDefinition(kaleoDefinitionId, serviceContext);
	}

	public static void activateKaleoDefinition(
			String name, int version,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().activateKaleoDefinition(name, version, serviceContext);
	}

	/**
	 * Adds the kaleo definition to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoDefinition the kaleo definition
	 * @return the kaleo definition that was added
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
		addKaleoDefinition(
			com.liferay.portal.workflow.kaleo.model.KaleoDefinition
				kaleoDefinition) {

		return getService().addKaleoDefinition(kaleoDefinition);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 #addKaleoDefinition(String, String, String, String, String,
	 int, ServiceContext)}
	 */
	@Deprecated
	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
			addKaleoDefinition(
				String name, String title, String description, String content,
				int version,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addKaleoDefinition(
			name, title, description, content, version, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
			addKaleoDefinition(
				String name, String title, String description, String content,
				String scope, int version,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addKaleoDefinition(
			name, title, description, content, scope, version, serviceContext);
	}

	/**
	 * Creates a new kaleo definition with the primary key. Does not add the kaleo definition to the database.
	 *
	 * @param kaleoDefinitionId the primary key for the new kaleo definition
	 * @return the new kaleo definition
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
		createKaleoDefinition(long kaleoDefinitionId) {

		return getService().createKaleoDefinition(kaleoDefinitionId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deactivateKaleoDefinition(
			String name, int version,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deactivateKaleoDefinition(name, version, serviceContext);
	}

	public static void deleteCompanyKaleoDefinitions(long companyId) {
		getService().deleteCompanyKaleoDefinitions(companyId);
	}

	/**
	 * Deletes the kaleo definition from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoDefinition the kaleo definition
	 * @return the kaleo definition that was removed
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
		deleteKaleoDefinition(
			com.liferay.portal.workflow.kaleo.model.KaleoDefinition
				kaleoDefinition) {

		return getService().deleteKaleoDefinition(kaleoDefinition);
	}

	/**
	 * Deletes the kaleo definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition
	 * @return the kaleo definition that was removed
	 * @throws PortalException if a kaleo definition with the primary key could not be found
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
			deleteKaleoDefinition(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteKaleoDefinition(kaleoDefinitionId);
	}

	public static void deleteKaleoDefinition(
			String name,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteKaleoDefinition(name, serviceContext);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return getService().dslQuery(dslQuery);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
		fetchKaleoDefinition(long kaleoDefinitionId) {

		return getService().fetchKaleoDefinition(kaleoDefinitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
		fetchKaleoDefinition(
			String name,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().fetchKaleoDefinition(name, serviceContext);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the kaleo definition with the primary key.
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition
	 * @return the kaleo definition
	 * @throws PortalException if a kaleo definition with the primary key could not be found
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
			getKaleoDefinition(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getKaleoDefinition(kaleoDefinitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
			getKaleoDefinition(
				String name,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getKaleoDefinition(name, serviceContext);
	}

	public static java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoDefinition>
			getKaleoDefinitions(
				boolean active, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.portal.workflow.kaleo.model.KaleoDefinition>
						orderByComparator,
				com.liferay.portal.kernel.service.ServiceContext
					serviceContext) {

		return getService().getKaleoDefinitions(
			active, start, end, orderByComparator, serviceContext);
	}

	/**
	 * Returns a range of all the kaleo definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of kaleo definitions
	 */
	public static java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoDefinition>
			getKaleoDefinitions(int start, int end) {

		return getService().getKaleoDefinitions(start, end);
	}

	public static java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoDefinition>
			getKaleoDefinitions(
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.portal.workflow.kaleo.model.KaleoDefinition>
						orderByComparator,
				com.liferay.portal.kernel.service.ServiceContext
					serviceContext) {

		return getService().getKaleoDefinitions(
			start, end, orderByComparator, serviceContext);
	}

	/**
	 * Returns the number of kaleo definitions.
	 *
	 * @return the number of kaleo definitions
	 */
	public static int getKaleoDefinitionsCount() {
		return getService().getKaleoDefinitionsCount();
	}

	public static int getKaleoDefinitionsCount(
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().getKaleoDefinitionsCount(active, serviceContext);
	}

	public static int getKaleoDefinitionsCount(
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().getKaleoDefinitionsCount(serviceContext);
	}

	public static int getKaleoDefinitionsCount(
		String name, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().getKaleoDefinitionsCount(
			name, active, serviceContext);
	}

	public static int getKaleoDefinitionsCount(
		String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().getKaleoDefinitionsCount(name, serviceContext);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoDefinition>
			getScopeKaleoDefinitions(
				String scope, boolean active, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.portal.workflow.kaleo.model.KaleoDefinition>
						orderByComparator,
				com.liferay.portal.kernel.service.ServiceContext
					serviceContext) {

		return getService().getScopeKaleoDefinitions(
			scope, active, start, end, orderByComparator, serviceContext);
	}

	public static int getScopeKaleoDefinitionsCount(
		String scope, boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().getScopeKaleoDefinitionsCount(
			scope, active, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
			updatedKaleoDefinition(
				long kaleoDefinitionId, String title, String description,
				String content,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updatedKaleoDefinition(
			kaleoDefinitionId, title, description, content, serviceContext);
	}

	/**
	 * Updates the kaleo definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoDefinition the kaleo definition
	 * @return the kaleo definition that was updated
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition
		updateKaleoDefinition(
			com.liferay.portal.workflow.kaleo.model.KaleoDefinition
				kaleoDefinition) {

		return getService().updateKaleoDefinition(kaleoDefinition);
	}

	public static KaleoDefinitionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<KaleoDefinitionLocalService, KaleoDefinitionLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			KaleoDefinitionLocalService.class);

		ServiceTracker<KaleoDefinitionLocalService, KaleoDefinitionLocalService>
			serviceTracker =
				new ServiceTracker
					<KaleoDefinitionLocalService, KaleoDefinitionLocalService>(
						bundle.getBundleContext(),
						KaleoDefinitionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}