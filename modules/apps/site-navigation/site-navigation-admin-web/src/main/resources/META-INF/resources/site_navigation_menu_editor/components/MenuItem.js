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

import {ClayButtonWithIcon} from '@clayui/button';
import ClayCard from '@clayui/card';
import {ClayCheckbox} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import ClayLayout from '@clayui/layout';
import classNames from 'classnames';
import {fetch, objectToFormData, openToast} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React from 'react';
import {useDrag} from 'react-dnd';

import {ACCEPTING_ITEM_TYPE} from '../constants/acceptingItemType';
import {SIDEBAR_PANEL_IDS} from '../constants/sidebarPanelIds';
import {useConstants} from '../contexts/ConstantsContext';
import {useItems, useSetItems} from '../contexts/ItemsContext';
import {
	useSelectedMenuItemId,
	useSetSelectedMenuItemId,
} from '../contexts/SelectedMenuItemIdContext';
import {useSetSidebarPanelId} from '../contexts/SidebarPanelIdContext';

export const MenuItem = ({item}) => {
	const {deleteSiteNavigationMenuItemURL, portletNamespace} = useConstants();
	const {siteNavigationMenuItemId, title, type} = item;
	const items = useItems();
	const setItems = useSetItems();
	const setSelectedMenuItemId = useSetSelectedMenuItemId();
	const setSidebarPanelId = useSetSidebarPanelId();

	const selected = useSelectedMenuItemId() === siteNavigationMenuItemId;

	const deleteMenuItem = () => {
		fetch(deleteSiteNavigationMenuItemURL, {
			body: objectToFormData({
				[`${portletNamespace}siteNavigationMenuItemId`]: siteNavigationMenuItemId,
			}),
			method: 'POST',
		})
			.then(() => {
				const newItems = deleteItem(items, siteNavigationMenuItemId);

				setItems(newItems);
			})
			.catch(() => {
				openToast({
					message: Liferay.Language.get(
						'an-unexpected-error-occurred'
					),
					type: 'danger',
				});
			});
	};

	const [, handlerRef] = useDrag({
		item: {
			id: siteNavigationMenuItemId,
			type: ACCEPTING_ITEM_TYPE,
		},
	});

	return (
		<ClayCard
			className={classNames('site_navigation_menu_editor_MenuItem', {
				'site_navigation_menu_editor_MenuItem--selected': selected,
			})}
			horizontal
			selectable
		>
			<ClayCheckbox
				checked={selected}
				onChange={() => {
					setSelectedMenuItemId(siteNavigationMenuItemId);
					setSidebarPanelId(SIDEBAR_PANEL_IDS.menuItemSettings);
				}}
			>
				<ClayCard.Body className="px-0">
					<ClayCard.Row>
						<ClayLayout.ContentCol gutters ref={handlerRef}>
							<ClayIcon symbol="drag" />
						</ClayLayout.ContentCol>

						<ClayLayout.ContentCol expand>
							<ClayCard.Description displayType="title">
								{title}
							</ClayCard.Description>

							<ClayCard.Description displayType="subtitle">
								{type}
							</ClayCard.Description>
						</ClayLayout.ContentCol>

						<ClayLayout.ContentCol gutters>
							<ClayButtonWithIcon
								displayType="unstyled"
								onClick={deleteMenuItem}
								small
								symbol="times-circle"
							/>
						</ClayLayout.ContentCol>
					</ClayCard.Row>
				</ClayCard.Body>
			</ClayCheckbox>
		</ClayCard>
	);
};

MenuItem.propTypes = {
	item: PropTypes.shape({
		children: PropTypes.array.isRequired,
		siteNavigationMenuItemId: PropTypes.string.isRequired,
		title: PropTypes.string.isRequired,
		type: PropTypes.string.isRequired,
	}),
};

const deleteItem = (items, itemId) =>
	items.reduce(
		(acc, item) =>
			item.siteNavigationMenuItemId === itemId
				? [...acc, ...item.children]
				: [
						...acc,
						{...item, children: deleteItem(item.children, itemId)},
				  ],
		[]
	);
