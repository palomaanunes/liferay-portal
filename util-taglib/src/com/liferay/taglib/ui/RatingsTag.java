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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.ratings.kernel.RatingsType;
import com.liferay.ratings.kernel.definition.PortletRatingsDefinitionUtil;
import com.liferay.ratings.kernel.model.RatingsEntry;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author     Brian Wing Shun Chan
 * @author     Shuyang Zhou
 * @author     Roberto Díaz
 * @deprecated As of Athanasius (7.3.x), replaced by {@link
 *             com.liferay.ratings.taglib.servlet.taglib.RatingsTag}
 */
@Deprecated
public class RatingsTag extends IncludeTag {

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public int getNumberOfStars() {
		return _numberOfStars;
	}

	public RatingsEntry getRatingsEntry() {
		return _ratingsEntry;
	}

	public RatingsStats getRatingsStats() {
		return _ratingsStats;
	}

	public String getUrl() {
		return _url;
	}

	public boolean isInTrash() {
		return _inTrash;
	}

	public boolean isRound() {
		return _round;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setInTrash(boolean inTrash) {
		_inTrash = inTrash;
	}

	public void setNumberOfStars(int numberOfStars) {
		_numberOfStars = numberOfStars;
	}

	public void setRatingsEntry(RatingsEntry ratingsEntry) {
		_ratingsEntry = ratingsEntry;

		_setRatingsEntry = true;
	}

	public void setRatingsStats(RatingsStats ratingsStats) {
		_ratingsStats = ratingsStats;

		_setRatingsStats = true;
	}

	public void setRound(boolean round) {
		_round = round;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setUrl(String url) {
		_url = url;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_className = null;
		_classPK = 0;
		_inTrash = null;
		_numberOfStars = _DEFAULT_NUMBER_OF_STARS;
		_ratingsEntry = null;
		_ratingsStats = null;
		_round = true;
		_setRatingsEntry = false;
		_setRatingsStats = false;
		_type = null;
		_url = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	protected String getType(HttpServletRequest httpServletRequest) {
		if (Validator.isNotNull(_type)) {
			return _type;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Group group = themeDisplay.getSiteGroup();

		if (group.isStagingGroup()) {
			group = group.getLiveGroup();
		}

		RatingsType ratingsType = null;

		if (group != null) {
			try {
				ratingsType = PortletRatingsDefinitionUtil.getRatingsType(
					themeDisplay.getCompanyId(), group.getGroupId(),
					_className);
			}
			catch (PortalException portalException) {
				_log.error(
					"Unable to get ratings type for group " +
						group.getGroupId(),
					portalException);
			}
		}

		if (ratingsType == null) {
			ratingsType = RatingsType.STARS;
		}

		return ratingsType.getValue();
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-ui:ratings:className", _className);
		httpServletRequest.setAttribute(
			"liferay-ui:ratings:classPK", String.valueOf(_classPK));

		if (_inTrash != null) {
			httpServletRequest.setAttribute(
				"liferay-ui:ratings:inTrash", _inTrash);
		}

		httpServletRequest.setAttribute(
			"liferay-ui:ratings:numberOfStars", String.valueOf(_numberOfStars));
		httpServletRequest.setAttribute(
			"liferay-ui:ratings:ratingsEntry", _ratingsEntry);
		httpServletRequest.setAttribute(
			"liferay-ui:ratings:ratingsStats", _ratingsStats);
		httpServletRequest.setAttribute(
			"liferay-ui:ratings:round", String.valueOf(_round));
		httpServletRequest.setAttribute(
			"liferay-ui:ratings:setRatingsEntry",
			String.valueOf(_setRatingsEntry));
		httpServletRequest.setAttribute(
			"liferay-ui:ratings:setRatingsStats",
			String.valueOf(_setRatingsStats));
		httpServletRequest.setAttribute(
			"liferay-ui:ratings:type", getType(httpServletRequest));
		httpServletRequest.setAttribute("liferay-ui:ratings:url", _url);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final int _DEFAULT_NUMBER_OF_STARS = GetterUtil.getInteger(
		PropsUtil.get(PropsKeys.RATINGS_DEFAULT_NUMBER_OF_STARS));

	private static final String _PAGE = "/html/taglib/ui/ratings/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(RatingsTag.class);

	private String _className;
	private long _classPK;
	private Boolean _inTrash;
	private int _numberOfStars = _DEFAULT_NUMBER_OF_STARS;
	private RatingsEntry _ratingsEntry;
	private RatingsStats _ratingsStats;
	private boolean _round = true;
	private boolean _setRatingsEntry;
	private boolean _setRatingsStats;
	private String _type;
	private String _url;

}