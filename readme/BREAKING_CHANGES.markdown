# What are the Breaking Changes for Liferay 7.4?

This document presents a chronological list of changes that break existing
functionality, APIs, or contracts with third party Liferay developers or users.
We try our best to minimize these disruptions, but sometimes they are
unavoidable.

Here are some of the types of changes documented in this file:

* Functionality that is removed or replaced
* API incompatibilities: Changes to public Java or JavaScript APIs
* Changes to context variables available to templates
* Changes in CSS classes available to Liferay themes and portlets
* Configuration changes: Changes in configuration files, like
  `portal.properties`, `system.properties`, etc.
* Execution requirements: Java version, J2EE Version, browser versions, etc.
* Deprecations or end of support: For example, warning that a certain
  feature or API will be dropped in an upcoming version.

*This document has been reviewed through commit `4334fc6cc349`.*

## Breaking Changes Contribution Guidelines

Each change must have a brief descriptive title and contain the following
information:

* **[Title]** Provide a brief descriptive title. Use past tense and follow
  the capitalization rules from
  <http://en.wikibooks.org/wiki/Basic_Book_Design/Capitalizing_Words_in_Titles>.
* **Date:** Specify the date you submitted the change. Format the date as
  *YYYY-MMM-DD* (e.g., 2014-Feb-25).
* **JIRA Ticket:** Reference the related JIRA ticket (e.g., LPS-12345)
  (Optional).
* **What changed?** Identify the affected component and the type of change that
  was made.
* **Who is affected?** Are end-users affected? Are developers affected? If the
  only affected people are those using a certain feature or API, say so.
* **How should I update my code?** Explain any client code changes required.
* **Why was this change made?** Explain the reason for the change. If
  applicable, justify why the breaking change was made instead of following a
  deprecation process.

Here's the template to use for each breaking change (note how it ends with a
horizontal rule):

```
### Title
- **Date:**
- **JIRA Ticket:**

#### What changed?

#### Who is affected?

#### How should I update my code?

#### Why was this change made?

---------------------------------------

```
**80 Columns Rule:** Text should not exceed 80 columns. Keeping text within 80
columns makes it easier to see the changes made between different versions of
the document. Titles, links, and tables are exempt from this rule. Code samples
must follow the column rules specified in Liferay's
[Development Style](http://www.liferay.com/community/wiki/-/wiki/Main/Liferay+development+style).

The remaining content of this document consists of the breaking changes listed
in ascending chronological order.

## Breaking Changes List

### The tag liferay-ui:flash is no longer available
- **Date:** 2020-Oct-13
- **JIRA Ticket:** [LPS-121732](https://issues.liferay.com/browse/LPS-121732)

#### What changed?

The tag `liferay-ui:flash` has been deleted and is no longer available.

#### Who is affected?

This affects any development that uses the `liferay-ui:flash` tag to embed
Adobe Flash movies in a page.

#### How should I update my code?

If you still need to embed Adobe Flash content in a page, you would need to
write your own code using one of the standard mechanisms such as `SWFObject`.

#### Why was this change made?

This change was made to align with [Adobe dropping support for Flash](https://www.adobe.com/products/flashplayer/end-of-life.html)
in December 31, 2020 and browsers removing Flash support in upcoming versions.

---------------------------------------

### The /portal/flash path is no longer available
- **Date:** 2020-Oct-13
- **JIRA Ticket:** [LPS-121733](https://issues.liferay.com/browse/LPS-121733)

#### What changed?

The public path `/portal/flash` that could be used to play an Adobe Flash movie
passing the movie URL as a parameter has been removed.

Additionally, the property and accessors have been removed from `ThemeDisplay`
and are no longer accesible.

#### Who is affected?

This affects people that were using the path `/c/portal/flash` directly to show
pages with Adobe Flash content.

#### How should I update my code?

A direct code update is not possible. One possible solution would be to create
a custom page simulating to simulate the old behaviour and read the different
movie parameters from the URL and then instantiate it using the common means
for Adobe Flash reproduction.

#### Why was this change made?

This change was made to align with [Adobe dropping support for Flash](https://www.adobe.com/products/flashplayer/end-of-life.html)
in December 31, 2020 and browsers removing Flash support in upcoming versions.

---------------------------------------

### The AUI module `swfobject` is no longer available
- **Date:** 2020-Oct-13
- **JIRA Ticket:** [LPS-121736](https://issues.liferay.com/browse/LPS-121736)

#### What changed?

The AUI module `swfobject` that provided a way to load the library SWFObject
commonly used to embed Adobe Flash content has been removed.

#### Who is affected?

This affects people that were requiring the AUI `swfobject` module as a way to
make the library available globally.

#### How should I update my code?

If you still need to embed Adobe Flash content, you can inject the SWFObject
library directly in your application using any of the available mechanisms.

#### Why was this change made?

This change was made to align with [Adobe dropping support for Flash](https://www.adobe.com/products/flashplayer/end-of-life.html)
in December 31, 2020 and browsers removing Flash support in upcoming versions.

---------------------------------------