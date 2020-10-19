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

package com.liferay.dispatch.service.impl;

import com.liferay.dispatch.constants.DispatchActionKeys;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.dispatch.service.base.DispatchTriggerServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"json.web.service.context.name=dispatch",
		"json.web.service.context.path=DispatchTrigger"
	},
	service = AopService.class
)
public class DispatchTriggerServiceImpl extends DispatchTriggerServiceBaseImpl {

	@Override
	public DispatchTrigger addDispatchTrigger(
			long userId, String name,
			UnicodeProperties taskSettingsUnicodeProperties, String taskType)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), DispatchActionKeys.ADD_DISPATCH_TRIGGER);

		return dispatchTriggerLocalService.addDispatchTrigger(
			userId, name, false, taskSettingsUnicodeProperties, taskType);
	}

	@Override
	public void deleteDispatchTrigger(long dispatchTriggerId)
		throws PortalException {

		_dispatchTriggerModelResourcePermission.check(
			getPermissionChecker(), dispatchTriggerId, ActionKeys.DELETE);

		dispatchTriggerLocalService.deleteDispatchTrigger(dispatchTriggerId);
	}

	@Override
	public List<DispatchTrigger> getDispatchTriggers(int start, int end)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (permissionChecker.isCompanyAdmin()) {
			return dispatchTriggerLocalService.getDispatchTriggers(
				permissionChecker.getCompanyId(), start, end);
		}

		return dispatchTriggerLocalService.getUserDispatchTriggers(
			permissionChecker.getCompanyId(), permissionChecker.getUserId(),
			start, end);
	}

	@Override
	public int getDispatchTriggersCount() throws PortalException {
		PermissionChecker permissionChecker = getPermissionChecker();

		if (permissionChecker.isCompanyAdmin()) {
			return dispatchTriggerLocalService.getDispatchTriggersCount(
				permissionChecker.getCompanyId());
		}

		return dispatchTriggerLocalService.getUserDispatchTriggersCount(
			permissionChecker.getCompanyId(), permissionChecker.getUserId());
	}

	@Override
	public DispatchTrigger updateDispatchTrigger(
			long dispatchTriggerId, boolean active, String cronExpression,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean neverEnd, boolean overlapAllowed,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute)
		throws PortalException {

		_dispatchTriggerModelResourcePermission.check(
			getPermissionChecker(), dispatchTriggerId, ActionKeys.UPDATE);

		return dispatchTriggerLocalService.updateDispatchTrigger(
			dispatchTriggerId, active, cronExpression, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, neverEnd, overlapAllowed,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute);
	}

	@Override
	public DispatchTrigger updateDispatchTrigger(
			long dispatchTriggerId, String name,
			UnicodeProperties taskSettingsUnicodeProperties)
		throws PortalException {

		_dispatchTriggerModelResourcePermission.check(
			getPermissionChecker(), dispatchTriggerId, ActionKeys.UPDATE);

		return dispatchTriggerLocalService.updateDispatchTrigger(
			dispatchTriggerId, name, taskSettingsUnicodeProperties);
	}

	private static volatile ModelResourcePermission<DispatchTrigger>
		_dispatchTriggerModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				DispatchTriggerServiceImpl.class,
				"_dispatchTriggerModelResourcePermission",
				DispatchTrigger.class);

}