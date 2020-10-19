<%--
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
--%>

<%@ include file="/publications/init.jsp" %>

<%
ViewChangesDisplayContext viewChangesDisplayContext = (ViewChangesDisplayContext)request.getAttribute(CTWebKeys.VIEW_CHANGES_DISPLAY_CONTEXT);

CTCollection ctCollection = viewChangesDisplayContext.getCtCollection();

Format format = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

renderResponse.setTitle(LanguageUtil.get(request, "changes"));

portletDisplay.setURLBack(viewChangesDisplayContext.getBackURL());
portletDisplay.setShowBackIcon(true);
%>

<nav class="component-tbar publications-tbar subnav-tbar-light tbar">
	<clay:container-fluid>
		<ul class="tbar-nav">
			<c:choose>
				<c:when test="<%= ctCollection.getStatus() == WorkflowConstants.STATUS_DRAFT %>">
					<li class="tbar-item tbar-item-expand text-left">
						<div class="publication-name">
							<span><%= HtmlUtil.escape(ctCollection.getName()) %></span>

							<clay:label
								displayType="<%= WorkflowConstants.getStatusStyle(ctCollection.getStatus()) %>"
								label="<%= viewChangesDisplayContext.getStatusLabel(ctCollection.getStatus()) %>"
							/>
						</div>

						<div class="publication-description"><%= HtmlUtil.escape(ctCollection.getDescription()) %></div>
					</li>

					<c:if test="<%= CTCollectionPermission.contains(permissionChecker, ctCollection, CTActionKeys.PUBLISH) %>">
						<c:if test="<%= PropsValues.SCHEDULER_ENABLED %>">
							<li class="tbar-item">
								<liferay-portlet:renderURL var="scheduleURL">
									<portlet:param name="mvcRenderCommandName" value="/publications/view_conflicts" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="ctCollectionId" value="<%= String.valueOf(ctCollection.getCtCollectionId()) %>" />
									<portlet:param name="schedule" value="<%= Boolean.TRUE.toString() %>" />
								</liferay-portlet:renderURL>

								<a class="btn btn-secondary btn-sm <%= viewChangesDisplayContext.hasChanges() ? StringPool.BLANK : "disabled" %>" href="<%= scheduleURL %>" type="button">
									<liferay-ui:message key="schedule" />
								</a>
							</li>
						</c:if>

						<li class="tbar-item">
							<liferay-portlet:renderURL var="publishURL">
								<portlet:param name="mvcRenderCommandName" value="/publications/view_conflicts" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="ctCollectionId" value="<%= String.valueOf(ctCollection.getCtCollectionId()) %>" />
							</liferay-portlet:renderURL>

							<a class="btn btn-primary btn-sm <%= viewChangesDisplayContext.hasChanges() ? StringPool.BLANK : "disabled" %>" href="<%= publishURL %>" type="button">
								<liferay-ui:message key="publish" />
							</a>
						</li>
					</c:if>

					<li class="tbar-item">
						<liferay-ui:icon-menu
							direction="left-side"
							icon="ellipsis-v"
							markupView="lexicon"
						>
							<c:if test="<%= ctCollection.getCtCollectionId() != publicationsDisplayContext.getCtCollectionId() %>">
								<liferay-portlet:actionURL name="/publications/checkout_ct_collection" var="checkoutURL">
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="ctCollectionId" value="<%= String.valueOf(ctCollection.getCtCollectionId()) %>" />
								</liferay-portlet:actionURL>

								<liferay-ui:icon
									message="work-on-publication"
									url="<%= checkoutURL %>"
								/>
							</c:if>

							<liferay-portlet:renderURL var="editURL">
								<portlet:param name="mvcRenderCommandName" value="/publications/edit_ct_collection" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="ctCollectionId" value="<%= String.valueOf(ctCollection.getCtCollectionId()) %>" />
							</liferay-portlet:renderURL>

							<liferay-ui:icon
								message="edit"
								url="<%= editURL %>"
							/>

							<li aria-hidden="true" class="dropdown-divider" role="presentation"></li>

							<liferay-portlet:actionURL name="/publications/delete_ct_collection" var="deleteURL">
								<portlet:param name="redirect" value="<%= viewChangesDisplayContext.getBackURL() %>" />
								<portlet:param name="ctCollectionId" value="<%= String.valueOf(ctCollection.getCtCollectionId()) %>" />
							</liferay-portlet:actionURL>

							<liferay-ui:icon-delete
								confirmation="are-you-sure-you-want-to-delete-this-publication"
								message="delete"
								url="<%= deleteURL %>"
							/>
						</liferay-ui:icon-menu>
					</li>
				</c:when>
				<c:when test="<%= ctCollection.getStatus() == WorkflowConstants.STATUS_SCHEDULED %>">
					<li class="tbar-item tbar-item-expand text-left">
						<div class="publication-name">
							<span><%= HtmlUtil.escape(ctCollection.getName()) %></span>

							<clay:label
								displayType="<%= WorkflowConstants.getStatusStyle(ctCollection.getStatus()) %>"
								label="<%= viewChangesDisplayContext.getStatusLabel(ctCollection.getStatus()) %>"
							/>
						</div>

						<div class="publication-description"><%= HtmlUtil.escape(viewChangesDisplayContext.getScheduledDescription()) %></div>
					</li>

					<c:if test="<%= CTCollectionPermission.contains(permissionChecker, ctCollection, CTActionKeys.PUBLISH) && PropsValues.SCHEDULER_ENABLED %>">
						<li class="tbar-item">
							<liferay-portlet:actionURL name="/publications/unschedule_publication" var="unscheduleURL">
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="ctCollectionId" value="<%= String.valueOf(ctCollection.getCtCollectionId()) %>" />
							</liferay-portlet:actionURL>

							<a class="btn btn-secondary btn-sm" href="<%= unscheduleURL %>" type="button">
								<liferay-ui:message key="unschedule" />
							</a>
						</li>
						<li class="tbar-item">
							<liferay-portlet:renderURL var="rescheduleURL">
								<portlet:param name="mvcRenderCommandName" value="/publications/schedule_publication" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="ctCollectionId" value="<%= String.valueOf(ctCollection.getCtCollectionId()) %>" />
							</liferay-portlet:renderURL>

							<a class="btn btn-primary btn-sm" href="<%= rescheduleURL %>" type="button">
								<liferay-ui:message key="reschedule" />
							</a>
						</li>
					</c:if>
				</c:when>
				<c:otherwise>
					<li class="tbar-item tbar-item-expand text-left">
						<div class="publication-name">
							<span><%= HtmlUtil.escape(ctCollection.getName()) %></span>

							<clay:label
								displayType="<%= WorkflowConstants.getStatusStyle(ctCollection.getStatus()) %>"
								label="<%= viewChangesDisplayContext.getStatusLabel(ctCollection.getStatus()) %>"
							/>
						</div>

						<%
						String description = ctCollection.getDescription();

						if (Validator.isNotNull(description)) {
							description = description.concat(" | ");
						}
						%>

						<div class="publication-description"><%= HtmlUtil.escape(description.concat(LanguageUtil.format(resourceBundle, "published-by-x-on-x", new Object[] {ctCollection.getUserName(), format.format(ctCollection.getStatusDate())}, false))) %></div>
					</li>
					<li class="tbar-item">
						<liferay-portlet:renderURL var="revertURL">
							<portlet:param name="mvcRenderCommandName" value="/publications/undo_ct_collection" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="ctCollectionId" value="<%= String.valueOf(ctCollection.getCtCollectionId()) %>" />
							<portlet:param name="revert" value="true" />
						</liferay-portlet:renderURL>

						<a class="btn btn-secondary btn-sm" href="<%= revertURL %>" type="button">
							<liferay-ui:message key="revert" />
						</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</clay:container-fluid>
</nav>

<div class="publications-view-changes-wrapper">
	<c:choose>
		<c:when test="<%= viewChangesDisplayContext.hasChanges() %>">
			<react:component
				module="publications/js/ChangeTrackingChangesView"
				props="<%= viewChangesDisplayContext.getReactData() %>"
			/>
		</c:when>
		<c:otherwise>
			<clay:container-fluid>
				<liferay-ui:empty-result-message
					message="no-changes-were-found"
				/>
			</clay:container-fluid>
		</c:otherwise>
	</c:choose>
</div>